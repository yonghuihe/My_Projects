package com.company.wx.util;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WeixinUtil {

	// 该token和接口url中的token进行对比，从而验证安全性
	public static final String TOKEN = "company";

	public static final String APPID = "wx8c5959ca0d5544a9";

	public static final String SECRET = "e469b7c3f988b2127ee246cb0aa61479";

	// 创建菜单的url
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 获取AccessToken的url
	public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
			+ APPID + "&secret=" + SECRET + "";

	// 删除自定义菜单url
	public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	// 发送模板消息的url
	public static final String POST_SEND_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	// 网页授权的获取code的url
	public static final String GET_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID
			+ "&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	// 网页授权获取AccessToken的url
	public static final String GET_WEB_ACCESS_TOKEN_URL = " https://api.weixin.qq.com/sns/oauth2/access_token?appid="
			+ APPID + "&secret=" + SECRET + "&code=CODE&grant_type=authorization_code";
	// 刷新网页授权的AccessToken的url
	public static final String REFRESH_WEB_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="
			+ APPID + "&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

	// 网页授权获取用户信息的url
	public static final String GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	// 根据普通AccessToken，发送get请求获取jsapi_ticket的url
	public static final String GET_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	// 失效时间
	public static Long expiresTime = 0L;

	// 凭证有效时间，单位：秒
	public static Long expiresIn = 0L;

	// 获取到的凭证
	public static String accessToken = null;

	// 获取AccessToken
	public static String getAccessToken() {
		// 如果AccessToken没有失效，就直接获取(第一次访问时，会获取AccessToken，并设置失效时间（当前时间+7200*1000），当过了7200*1000时会进入该方法，重新获取AccessToken

		// 如果失效，就重新去微信服务器获取(当前时间大于失效时间)，如果accessToken为空，也重新获取accessToken
		if (accessToken != null || new Date().getTime() > expiresTime) {
			String result = HttpUtil.get(GET_ACCESS_TOKEN_URL);
			System.out.println(result);
			// 将JSON字符串转换为JSON数组
			JSONObject json = JSON.parseObject(result);
			// 获取凭证有效时间，单位：秒
			expiresIn = json.getLong("expires_in");// 7200s
			// 获取AccessToken
			accessToken = json.getString("access_token");
			// 设置过期时间
			expiresTime = new Date().getTime() + expiresIn * 1000;
		}
		return accessToken;
	}

	/**
	 * 获取多次，查看是否在有效期内，accessToken是否是同一个
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(getAccessToken());
		System.out.println(getTicket());
	}

	/**
	 * 获取jsapi_ticket
	 */
	public static JSONObject getTicket() {
		// 发起请求到指定的接口
		String result = HttpUtil.get(GET_TICKET_URL.replace("ACCESS_TOKEN", getAccessToken()));
		JSONObject json = JSONObject.parseObject(result);
		return json;
	}

	/**
	 * 获取网页授权的access_token
	 * @param code
	 * @return
	 */
	public static JSONObject getWebAccessToken(String code) {
		String webAccessToken = HttpUtil.get(GET_WEB_ACCESS_TOKEN_URL.replace("CODE", code));
		// 转换为json格式
		JSONObject json = JSONObject.parseObject(webAccessToken);
		return json;
	}

	/**
	 * 拉取用户信息
	 * 
	 * @param webAccessToken
	 * @param openid
	 * @return
	 */
	public static JSONObject getUserInfo(String webAccessToken, String openid) {
		String userInfo = HttpUtil.get(GET_USERINFO_URL.replace("ACCESS_TOKEN", webAccessToken).replace("OPENID", openid));
		JSONObject json = JSONObject.parseObject(userInfo);
		return json;
	}
	
	/**
     * 计算jssdk-config的签名
     * @param jsapi_ticket
     * @param timestamp
     * @param noncestr
     * @param url
     * @return
     */
    public static String getSignature(String jsapi_ticket,Long timestamp,String noncestr,String url ){
        //对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）
        Map<String,Object> map = new TreeMap<>();
        map.put("jsapi_ticket",jsapi_ticket);
        map.put("timestamp",timestamp);
        map.put("noncestr",noncestr);
        map.put("url",url);
        //使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1
        StringBuilder sb = new StringBuilder();
        Set<String> set = map.keySet();
        for (String key : set) {
            sb.append(key+"="+map.get(key)).append("&");
        }
        //去掉最后一个&符号
        String temp = sb.substring(0,sb.length()-1);
        //使用sha1加密
        String signature = SecurityUtil.SHA1(temp);
        return signature;
    }
}
