package com.company.wx.web.controller;

import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.wx.util.HttpUtil;
import com.company.wx.util.WeixinUtil;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index(String code) {
		if (StringUtils.isNotBlank(code)) {
			System.out.println("code:" + code);
			// 发送请求获取网页授权的AccessToken
			String accessToken = HttpUtil.get(WeixinUtil.GET_WEB_ACCESS_TOKEN_URL
					.replace("SECRET", "e469b7c3f988b2127ee246cb0aa61479").replace("CODE", code));
			System.out.println("accessToken:" + accessToken);
			// 转换为json
			JSONObject json = JSON.parseObject(accessToken);
			String openId = (String) json.get("openid");
			String webAccessToken = (String) json.get("access_token");

			System.out.println("openId:" + openId);
			System.out.println("webAccessToken:" + webAccessToken);

			// 根据普通AccessToken获取jsapi_ticket
			String ticket = HttpUtil
					.get(WeixinUtil.GET_TICKET_URL.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken()));
			System.out.println(ticket);
			// 刷新access_token（如果需要）

			// 通过access_token和openid拉取用户信息（网页授权作用域为snsapi_userinfo）
			String userInfo = HttpUtil
					.get(WeixinUtil.GET_USERINFO_URL.replace("ACCESS_TOKEN", webAccessToken).replace("OPENID", openId));
			System.out.println("userInfo:" + userInfo);
		}
		return "index"; // 返回的是视图
	}
	
	@RequestMapping("/person")
	public String person(String code, Model model) {
		// 用户同意授权
		if (code != null){
			// 通过code获取access_token
			JSONObject json = WeixinUtil.getWebAccessToken(code);
			// 获取网页授权专用的access_token凭据
			String webAccessToken = json.getString("access_token");
			// 获取用户openID
			String openid = json.getString("openid");
			// 拉取用户信息
			JSONObject userInfo = WeixinUtil.getUserInfo(webAccessToken,openid);
			// 获取json对象中的键值对
			Set<Entry<String,Object>> entrySets = userInfo.entrySet();
			for (Entry<String, Object> entry : entrySets) {
				System.out.println(entry.getKey()+"-->"+entry.getValue());
				// 把键值作为属性设置到model中
				model.addAttribute(entry.getKey(), entry.getValue());
			}
		}
		return "person";
	}
	
	

}
