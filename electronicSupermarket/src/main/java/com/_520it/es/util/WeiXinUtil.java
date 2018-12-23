package com._520it.es.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Properties;

public class WeiXinUtil {

	
	// 自定义的token（和配置的）
	public static String TOKEN;			// 测试号，接口配置信息的TOKEN
	public static String APPID="wxe4d269cdb2398004";			// 测试号的URL
	public static String SECRET="9bb36f62ba0b55885cc1794b6bf152c2";		// 测试号的SECRET
	
	// 创建菜单的URL
	public static String CREATE_MENU_URL;
	// 获取access的url
	public static String ACCESS_TOKEN_URL;

	/*
	 *  从配置文件读微信姓名的基本信息 和请求接口的url
	 *  配置步骤：
	 *  	1>在weixin.properties添加
	 *  	2>添加该全局的成员变量
	 *  	3>在static中赋值——》p.getProperty("xxx");
	 *  	4>测试下是否能正确获取
	 */
	static{
		Properties p = new Properties();
		try {
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("weixin.properties"));
			TOKEN = p.getProperty("TOKEN");
			//APPID = p.getProperty("APPID");
			//SECRET = p.getProperty("SECRET");
			//APPID ="wx0ab6a6c7c758caf0";

			CREATE_MENU_URL = p.getProperty("CREATE_MENU_URL");
			ACCESS_TOKEN_URL = p.getProperty("ACCESS_TOKEN_URL").replace("APPID", APPID).replace("SECRET", SECRET);
			// 新加入的字段测试下,是否能正确获取
			//System.out.println("------------"+xxx+"---------------");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//----------------------用于自定义菜单使用----------------------------------
	// 全局accessToke值
	public static String accessToke=null;
	// 获取 acess_token
	public static String getAccessToken(){
		
		String result = HttpUtil.get(ACCESS_TOKEN_URL);
		System.out.println(result);
		JSONObject parseObject = JSON.parseObject(result);
		System.out.println(parseObject.getLong("expires_in"));
		 
		accessToke = parseObject.getString("access_token");
		return accessToke;
	}
	//
	public static void main(String[] args) {
		//getAccessToken();
	}
}
