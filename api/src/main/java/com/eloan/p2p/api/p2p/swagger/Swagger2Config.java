package com.eloan.p2p.api.p2p.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger的配置对象
 * 
 * @author yonghui
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Bean
	public Docket petApi(){
		return new Docket(DocumentationType.SWAGGER_2).select().build();
	}

}
