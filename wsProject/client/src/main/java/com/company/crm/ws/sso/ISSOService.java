package com.company.crm.ws.sso;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.9
 * 2018-08-26T22:00:46.749+08:00
 * Generated source version: 3.0.9
 * 
 */
@WebService(targetNamespace = "http://sso.ws.crm.company.com/", name = "ISSOService")
@XmlSeeAlso({ObjectFactory.class})
public interface ISSOService {

    @WebMethod
    @RequestWrapper(localName = "checkToken", targetNamespace = "http://sso.ws.crm.company.com/", className = "com.company.crm.ws.sso.CheckToken")
    @ResponseWrapper(localName = "checkTokenResponse", targetNamespace = "http://sso.ws.crm.company.com/", className = "com.company.crm.ws.sso.CheckTokenResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String checkToken(
        @WebParam(name = "token", targetNamespace = "")
        java.lang.String token,
        @WebParam(name = "url", targetNamespace = "")
        java.lang.String url
    );
}