
package cz.cvut.fel.x36tjv.ws;

import java.math.BigDecimal;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2-hudson-752-
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CentralBank", targetNamespace = "http://ws.x36tjv.fel.cvut.cz/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CentralBank {


    /**
     * 
     * @param amount
     * @param description
     * @param toBank
     * @param fromBank
     * @param toAccount
     * @param fromAccount
     * @param currency
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "transfer", targetNamespace = "http://ws.x36tjv.fel.cvut.cz/", className = "cz.cvut.fel.x36tjv.ws.Transfer")
    @ResponseWrapper(localName = "transferResponse", targetNamespace = "http://ws.x36tjv.fel.cvut.cz/", className = "cz.cvut.fel.x36tjv.ws.TransferResponse")
    @Action(input = "http://ws.x36tjv.fel.cvut.cz/CentralBank/transferRequest", output = "http://ws.x36tjv.fel.cvut.cz/CentralBank/transferResponse")
    public Boolean transfer(
        @WebParam(name = "fromBank", targetNamespace = "")
        Short fromBank,
        @WebParam(name = "fromAccount", targetNamespace = "")
        Long fromAccount,
        @WebParam(name = "toBank", targetNamespace = "")
        Short toBank,
        @WebParam(name = "toAccount", targetNamespace = "")
        Long toAccount,
        @WebParam(name = "amount", targetNamespace = "")
        BigDecimal amount,
        @WebParam(name = "currency", targetNamespace = "")
        String currency,
        @WebParam(name = "description", targetNamespace = "")
        String description);

}