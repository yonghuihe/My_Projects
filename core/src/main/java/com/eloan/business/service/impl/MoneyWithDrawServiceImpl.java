package com.eloan.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.query.PageResult;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Account;
import com.eloan.business.domain.MoneyWithDraw;
import com.eloan.business.domain.UserBankinfo;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.MoneyWithDrawMapper;
import com.eloan.business.query.MoneyWithDrawQueryObject;
import com.eloan.business.service.IAccountFlowService;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IMoneyWithDrawService;
import com.eloan.business.service.ISystemAccountService;
import com.eloan.business.service.IUserBankinfoService;
import com.eloan.business.service.IUserService;
import com.eloan.business.util.BidConst;
import com.eloan.business.util.BitStatesUtils;

@Service
public class MoneyWithDrawServiceImpl implements IMoneyWithDrawService {

	@Autowired
	private MoneyWithDrawMapper moneyWithDrawMapper;

	@Autowired
	private IUserService userService;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IUserBankinfoService userBankinfoService;

	@Autowired
	private IAccountFlowService accountFlowService;
	
	@Autowired
	private ISystemAccountService systemAccountService;

	@Override
	public void apply(BigDecimal moneyAmount) {
		Logininfo current = UserContext.getCurrent();
		Userinfo userinfo = this.userService.get(current.getId());// 提现用户
		Account account = this.accountService.get(userinfo.getId());// 提现账户
		// 1、判断(已经绑定银行卡，没有提现申请，提现金额>=500，提现金额<=账户可用余额）
		if (userinfo.getBindBankinfo() && !userinfo.getMoneyWithDraw()
				&& moneyAmount.compareTo(BidConst.MIN_WITHDRAW_AMOUNT) >= 0
				&& moneyAmount.compareTo(account.getUsableAmount()) <= 0) {
			// 2、创建MoneyWithDraw，设置属性，并保存
			MoneyWithDraw draw = new MoneyWithDraw();
			UserBankinfo bankinfo = this.userBankinfoService.get(userinfo.getId());
			draw.setAccountName(bankinfo.getAccountName());
			draw.setAmount(moneyAmount);
			draw.setApplier(current);
			draw.setApplyTime(new Date());
			draw.setBankName(bankinfo.getBankName());
			draw.setBankNumber(bankinfo.getBankNumber());
			draw.setForkName(bankinfo.getForkName());
			draw.setRemark("提现申请");

			draw.setState(MoneyWithDraw.STATE_APPLY);
			draw.setChargeFee(BidConst.MONEY_WITHDRAW_CHARGEFEE);
			this.moneyWithDrawMapper.insert(draw);

			// 3、提现账户的可用金额减少，冻结金额增加，生成流水
			account.setUsableAmount(account.getUsableAmount().subtract(moneyAmount));
			account.setFreezedAmount(account.getFreezedAmount().add(moneyAmount));
			this.accountService.update(account);
			this.accountFlowService.creatMoneyWithDrawFlow(draw, account);

			// 4、提现用户增加一个处于提现申请的状态
			userinfo.setBitState(BitStatesUtils.OP_MONEYWITHDRAW_PROCESS);
			this.userService.update(userinfo);
		}
	}

	@Override
	public PageResult query(MoneyWithDrawQueryObject qo) {
		int count = this.moneyWithDrawMapper.queryForCount(qo);
		if (count > 0) {
			List<MoneyWithDraw> list = this.moneyWithDrawMapper.query(qo);
			return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(), list);
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void audit(Long id, String remark, int state) {
		// 1、查询出提现申请对象，并判断
		MoneyWithDraw draw = this.moneyWithDrawMapper.selectByPrimaryKey(id);
		if (draw != null && draw.getState() == MoneyWithDraw.STATE_APPLY) {
			// 2、设置相关审核信息，并修改
			draw.setAuditor(UserContext.getCurrent());
			draw.setAuditTime(new Date());
			draw.setRemark(remark);
			draw.setState(state);
			this.moneyWithDrawMapper.updateByPrimaryKey(draw);
			Account account = this.accountService.get(draw.getApplier().getId());// 提现账户
			if (draw.getState() == MoneyWithDraw.STATE_PASS) {
				// 3、如果审核通过（提现成功），提现账户的冻结金额减少（不包含手续费），发生一条流水，扣除手续费并发生一条流水，系统账户收取手续费并流水
				account.setFreezedAmount(
						account.getFreezedAmount().subtract(draw.getAmount()).add(draw.getChargeFee()));
				this.accountFlowService.createMoneyWithDrawSuccessFlow(account, draw);
				account.setFreezedAmount(account.getFreezedAmount().subtract(draw.getChargeFee()));
				this.accountFlowService.createChargeFeeFlow(account, draw);
				this.systemAccountService.chargeMoneyWithDrawFee(draw);
			} else if (draw.getState() == MoneyWithDraw.STATE_REJECT) {
				// 4、提现审核拒绝（提现失败），冻结金额减少，可用金额增加，增加一条流水
				account.setFreezedAmount(account.getFreezedAmount().subtract(draw.getAmount()));
				account.setUsableAmount(account.getUsableAmount().add(draw.getAmount()));
				this.accountFlowService.createMoneyWithDrawFailedFlow(account,draw);
			}
			this.accountService.update(account);
			//5、取消用户提现状态
			Userinfo userinfo = this.userService.get(account.getId());
			userinfo.removeState(BitStatesUtils.OP_MONEYWITHDRAW_PROCESS);
			this.userService.update(userinfo);
		}
	}

}
