
package com.company.crm.ws.sso;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.company.crm.ws.sso package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CheckToken_QNAME = new QName("http://sso.ws.crm.company.com/", "checkToken");
    private final static QName _CheckTokenResponse_QNAME = new QName("http://sso.ws.crm.company.com/", "checkTokenResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.company.crm.ws.sso
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckToken }
     * 
     */
    public CheckToken createCheckToken() {
        return new CheckToken();
    }

    /**
     * Create an instance of {@link CheckTokenResponse }
     * 
     */
    public CheckTokenResponse createCheckTokenResponse() {
        return new CheckTokenResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sso.ws.crm.company.com/", name = "checkToken")
    public JAXBElement<CheckToken> createCheckToken(CheckToken value) {
        return new JAXBElement<CheckToken>(_CheckToken_QNAME, CheckToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sso.ws.crm.company.com/", name = "checkTokenResponse")
    public JAXBElement<CheckTokenResponse> createCheckTokenResponse(CheckTokenResponse value) {
        return new JAXBElement<CheckTokenResponse>(_CheckTokenResponse_QNAME, CheckTokenResponse.class, null, value);
    }

}
