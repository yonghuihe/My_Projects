package com.company.wx.web.controller;

import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.wx.domain.ArticleItem;
import com.company.wx.domain.InMsgEntity;
import com.company.wx.domain.OutImageMsgEntity;
import com.company.wx.domain.OutNewsMsgEntity;
import com.company.wx.domain.OutTextMsgEntity;
import com.company.wx.util.SecurityUtil;
import com.company.wx.util.WeixinUtil;

/**
 * url 根据url的请求方式不同做不同的业务逻辑 get请求时，url接入校验 post请求时，接收消息和回复消息
 * 
 * @author yonghui
 *
 */
@Controller
public class WXController {

	/**
	 * 接入校验：为了提高安全性，避免别人直接拿我们的url添加到别人的公众号上去 代码可以抽取到业务层
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value = "/weixin", method = RequestMethod.GET)
	@ResponseBody
	public String validate(String signature, String timestamp, String nonce, String echostr) {
		/*
		 * 加密/校验流程如下：
		 */
		// 1）将token、timestamp、nonce三个参数进行字典序排序
		String[] arr = { WeixinUtil.TOKEN, timestamp, nonce };
		Arrays.sort(arr);

		StringBuilder result = new StringBuilder();
		// 2）将三个参数字符串拼接成一个字符串进行sha1加密
		for (String string : arr) {
			result.append(string);
		}
		// 加密后的字符串
		String mySignature = SecurityUtil.SHA1(result.toString());
		// 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		if (mySignature.equals(signature)) {
			System.out.println("验证成功！");
			return echostr;
		}
		System.out.println("验证失败！");
		return null;
	}

	/**
	 * 用于接收和回复消息，注意RequestMapping的参数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/weixin", method = RequestMethod.POST)
	@ResponseBody
	public Object handleMessage(@RequestBody InMsgEntity entity) {
		// 根据接收的消息类型设置返回的消息类型
		String msgType = entity.getMsgType();
		if ("text".equals(msgType)) {
			return outTextMsgEntity(entity);
		} else if ("image".equals(msgType)) {
			return outImageMsgEntity(entity);
		} else if ("event".equals(msgType)) {
			// 如果是第一次关注，回复消息：欢迎关注
			if ("subscribe".equals(entity.getEvent())) {
				// 回复文本信息
				entity.setContent("欢迎关注");
				return outTextMsgEntity(entity);

				// 如果是取消关注，就更新粉丝的状态为"已取消关注"
			} else if ("unsubscribe".equals(entity.getEvent())) {
				System.out.println("已取消关注");
			} else if ("CLICK".equals(entity.getEvent())) {
				// 获取按钮的key值
				String eventKey = entity.getEventKey();
				// 根据按钮返回对应的信息
				if ("classinfo".equals(eventKey)) {
					entity.setContent("上海Java基础班第05期于2018/05/10开班\n" + "广州Java基础班第24期于2018/04/02开班");
					return outTextMsgEntity(entity);
				} else if ("address".equals(eventKey)) {
					entity.setContent("北京校区：北京昌平区沙河镇万家灯火装饰城2楼8077号\n" + "广州校区：广州市天河区棠下涌东路大地工业区D栋六楼\n"
							+ "上海校区：上海市青浦区华新镇华隆路1777号E通世界商务园华新园A座4楼402");
					return outTextMsgEntity(entity);
				}
				System.out.println(entity);
			}
		}
		return null;
	}

	private Object outNewsMsgEntity(InMsgEntity entity) {
		OutNewsMsgEntity outNewsMsgEntity = new OutNewsMsgEntity();

		// 设置返回类型
		outNewsMsgEntity.setMsgType("news");
		// 设置图文消息个数
		outNewsMsgEntity.setArticleCount(1);
		// 设置响应内容，响应图文信息
		ArticleItem item = new ArticleItem();
		item.setTitle("Solr中国");
		item.setDescription("Solr Community of China");
		item.setPicUrl("https://avatars2.githubusercontent.com/u/6977184?s=200&v=4");
		item.setUrl("https://github.com/solrcn/");
		outNewsMsgEntity.setItem(new ArticleItem[] { item });
		// 设置发送方：测试号
		outNewsMsgEntity.setFromUserName(entity.getToUserName());
		// 设置接收方：用户
		outNewsMsgEntity.setToUserName(entity.getFromUserName());
		// 设置响应时间
		outNewsMsgEntity.setCreateTime(new Date().getTime());
		System.out.println(outNewsMsgEntity);
		return outNewsMsgEntity;
	}

	private Object outImageMsgEntity(InMsgEntity entity) {
		OutImageMsgEntity outImageMsgEntity = new OutImageMsgEntity();

		// 设置返回类型
		outImageMsgEntity.setMsgType("image");
		// 设置响应内容，原样返回图片
		outImageMsgEntity.setMediaId(new String[] { entity.getMediaId() });
		// 设置发送方：测试号
		outImageMsgEntity.setFromUserName(entity.getToUserName());
		// 设置接收方：用户
		outImageMsgEntity.setToUserName(entity.getFromUserName());
		// 设置响应时间
		outImageMsgEntity.setCreateTime(new Date().getTime());
		System.out.println(outImageMsgEntity);
		return outImageMsgEntity;
	}

	private Object outTextMsgEntity(InMsgEntity entity) {
		OutTextMsgEntity outTextMsgEntity = new OutTextMsgEntity();

		// 设置返回类型
		outTextMsgEntity.setMsgType("text");
		// 设置响应内容，如果包含关键字，去查询数据库，否则原样返回文本，表情
		String content = entity.getContent();
		String outContent = null;
		if (content.contains("地址")) {
			outContent = "查询数据库，关键字是地址";
		} else if (content.contains("时间")) {
			outContent = "查询数据库，关键字是时间";
		} else if (content.contains("solr")) {
			// 回复图文信息
			return outNewsMsgEntity(entity);
		} else {
			outContent = content;
		}
		outTextMsgEntity.setContent(outContent);
		// 设置发送方：测试号
		outTextMsgEntity.setFromUserName(entity.getToUserName());
		// 设置接收方：用户
		outTextMsgEntity.setToUserName(entity.getFromUserName());
		// 设置响应时间
		outTextMsgEntity.setCreateTime(new Date().getTime());
		System.out.println(outTextMsgEntity);
		return outTextMsgEntity;
	}
}
