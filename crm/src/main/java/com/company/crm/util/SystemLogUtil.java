package com.company.crm.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.crm.domain.Employee;
import com.company.crm.domain.SystemLog;
import com.company.crm.service.ISystemLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SystemLogUtil {
	
	@Autowired
	private ISystemLogService service;

	public void writeLog(JoinPoint joinPoint) throws JsonProcessingException {
		
		if(joinPoint.getTarget() instanceof ISystemLogService){
			return;
		}
		System.out.println("--记录日志--");
		// 创建SystemLog对象，将封装好的对象插入到数据库中
		SystemLog log = new SystemLog();
		log.setOpTime(new Date());

		log.setOpUser((Employee) SecurityUtils.getSubject().getPrincipal());
		HttpServletRequest request = RequestUtil.getRequest();
		if (request != null) {
			log.setOpIp(request.getRemoteAddr());
		}
		
		String name = joinPoint.getTarget().getClass().getName();
		String signature = joinPoint.getSignature().getName();
		String function = name + ":" + signature;
		
		log.setFunction(function);
		
		String args = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
		log.setParam(args);

		service.insert(log);
	}
}
