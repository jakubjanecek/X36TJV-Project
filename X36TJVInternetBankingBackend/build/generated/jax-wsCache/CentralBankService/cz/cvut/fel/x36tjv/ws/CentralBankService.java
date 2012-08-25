
package cz.cvut.fel.x36tjv.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2-hudson-752-
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CentralBankService", targetNamespace = "http://ws.x36tjv.fel.cvut.cz/", wsdlLocation = "http://localhost:8080/X36TJVCentralBankWS/CentralBankService?wsdl")
public class CentralBankService
    extends Service
{

    private final static URL CENTRALBANKSERVICE_WSDL_LOCATION;
    private final static WebServiceException CENTRALBANKSERVICE_EXCEPTION;
    private final static QName CENTRALBANKSERVICE_QNAME = new QName("http://ws.x36tjv.fel.cvut.cz/", "CentralBankService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/X36TJVCentralBankWS/CentralBankService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CENTRALBANKSERVICE_WSDL_LOCATION = url;
        CENTRALBANKSERVICE_EXCEPTION = e;
    }

    public CentralBankService() {
        super(__getWsdlLocation(), CENTRALBANKSERVICE_QNAME);
    }

    public CentralBankService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CENTRALBANKSERVICE_QNAME, features);
    }

    public CentralBankService(URL wsdlLocation) {
        super(wsdlLocation, CENTRALBANKSERVICE_QNAME);
    }

    public CentralBankService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CENTRALBANKSERVICE_QNAME, features);
    }

    public CentralBankService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CentralBankService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CentralBank
     */
    @WebEndpoint(name = "CentralBankPort")
    public CentralBank getCentralBankPort() {
        return super.getPort(new QName("http://ws.x36tjv.fel.cvut.cz/", "CentralBankPort"), CentralBank.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CentralBank
     */
    @WebEndpoint(name = "CentralBankPort")
    public CentralBank getCentralBankPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.x36tjv.fel.cvut.cz/", "CentralBankPort"), CentralBank.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CENTRALBANKSERVICE_EXCEPTION!= null) {
            throw CENTRALBANKSERVICE_EXCEPTION;
        }
        return CENTRALBANKSERVICE_WSDL_LOCATION;
    }

}
