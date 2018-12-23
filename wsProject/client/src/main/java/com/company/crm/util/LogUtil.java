package com.company.crm.util;

import org.springframework.stereotype.Component;

/**
 * 日志工具类
 * @author XMG
 *
 */
@Component
public class LogUtil {
	/*
	@Autowired
	private ISystemlogService logService;
	
	public void writeLog(JoinPoint joinPoint) throws Exception{
		//防止日志logService自己切自己(死循环)
		if(joinPoint.getTarget() instanceof ISystemlogService){
			return;
		}
		System.out.println("-----写入日志------");
		//创建日志对象并填充数据
		Systemlog log = new Systemlog();
		log.setOptime(new Date());
		
		String target = joinPoint.getTarget().getClass().getName();
		String method = joinPoint.getSignature().getName();
		//使用功能
		log.setFunction(target+":"+method);
		//通过ObjectMapper来把参数对象装成json字符串
		ObjectMapper om = new ObjectMapper();
		//操作参数信息
		String params = om.writeValueAsString(joinPoint.getArgs());
		log.setParams(params);
		
		HttpServletRequest request = UserContext.getRequest();
		//设置ip
		log.setOpip(request.getRemoteAddr());
		
		Object object = request.getSession().getAttribute(UserContext.USERINSSESION);
		if(object!=null){
			//设置操作人
			log.setOpuser((Employee)object);
		}
		//保存日志到数据库
		logService.insert(log);
		
	}*/
}
