package com.xmg.mgrsite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmg.api.domain.LoginInfo;
import com.xmg.api.service.ILoginInfoService;
import com.xmg.api.vo.JSONResult;
import com.xmg.mgrsite.util.UserContext;

@Controller
public class LoginController {

	@Autowired
	ILoginInfoService loginInfoService;
	
	@ResponseBody
	@RequestMapping("/login.do")
	public JSONResult login(String userName,String password){
		JSONResult jSONResult = null;
		try {
			jSONResult = new JSONResult();
			LoginInfo loginInfo = loginInfoService.selectLoginInfo(userName, password);
			if(loginInfo == null){
				jSONResult.setErrorMsg("账号或密码错误");
			}else{
				UserContext.setCurrentUser(loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jSONResult.setErrorMsg(e.getMessage());
		}
		return jSONResult;
	}
}
