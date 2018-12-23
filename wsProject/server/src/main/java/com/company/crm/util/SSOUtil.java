package com.company.crm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SSOUtil {
	// 验证token是否合法时，需要使用token对应的session
	public static final Map<String, HttpSession> TOKEN_SESSION_MAP = new HashMap<String, HttpSession>();
	// key和token的map，key为随机数，只能使用一次，使用之后从map集合中移除掉
	public static final Map<String, String> KEY_TOKEN_MAP = new HashMap<String, String>();
	// token和urls的map，令牌有效，就将url保存起来
	public static final Map<String, List<String>> TOKEN_URLS_MAP = new HashMap<>();
}
