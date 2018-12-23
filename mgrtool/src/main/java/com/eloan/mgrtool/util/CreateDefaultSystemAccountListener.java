package com.eloan.mgrtool.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.eloan.business.service.ISystemAccountService;

@Component
public class CreateDefaultSystemAccountListener implements
		ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private ISystemAccountService systemAccountService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		this.systemAccountService.initSystemAccount();
	}

}
