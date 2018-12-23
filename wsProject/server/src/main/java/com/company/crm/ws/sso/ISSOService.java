package com.company.crm.ws.sso;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ISSOService {
	public String checkToken(@WebParam(name = "token") String token, @WebParam(name = "url") String url);
}
