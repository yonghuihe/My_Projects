package com.eloan.business.domain;

import org.springframework.util.StringUtils;

import com.eloan.base.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户绑定银行卡
 * 
 * @author yonghui
 *
 */
@Setter
@Getter
public class UserBankinfo extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private String bankName; //银行名称
	private String bankNumber; //银行账户
	private String forkName; //支行名称
	private String accountName; //开户人姓名
	private Long userinfoId; //用户id
	
	/**
	 * 获取开户人姓名的隐藏字符串，只显示姓氏 *
	 * 
	 * @param accountName
	 *            开户人姓名
	 * @return
	 */
	public String getAnonymousAccountName() {
		if (StringUtils.hasLength(accountName)) {
			int len = accountName.length();
			String replace = "";
			replace += accountName.charAt(0);
			for (int i = 1; i < len; i++) {
				replace += "*";
			}
			return replace;
		}
		return accountName;
	}

	/**
	 * 获取银行账户的隐藏字符串 *
	 * 
	 * @param bankNumber
	 * @return
	 */
	public String getAnonymousBankNumber() {
		if (StringUtils.hasLength(bankNumber)) {
			int len = bankNumber.length();
			String replace = "";
			for (int i = 0; i < len; i++) {
				if ((i > 5 && i < 10) || (i > len - 5)) {
					replace += "*";
				} else {
					replace += bankNumber.charAt(i);
				}
			}
			return replace;
		}
		return bankNumber;
	}
}
