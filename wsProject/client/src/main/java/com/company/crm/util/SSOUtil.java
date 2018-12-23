package com.company.crm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SSOUtil {
	// token和urls的map，令牌有效，就将url保存起来
	public static final Map<String,List<String>> TOKEN_URLS_MAP = new HashMap<>();
	// token和session的map
	public static final Map<String, HttpSession> TOKEN_SESSION_MAP = new HashMap<String, HttpSession>();
}
