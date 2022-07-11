
package com.sbs.easymbank.service.omapi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MethodeService", targetNamespace = "http://service.ws.mobileBanking.digi/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MethodeService {


    /**
     * 
     * @param accountAlias
     * @param unsubDate
     * @param unsubBy
     * @param narration
     * @return
     *     returns com.sbs.easymbank.service.mmapi.UnsubscriptionResponse
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.ws.mobileBanking.digi/MethodeService/unsubscriptionRequest", output = "http://service.ws.mobileBanking.digi/MethodeService/unsubscriptionResponse")
    public UnsubscriptionResponse unsubscription(
        @WebParam(name = "accountAlias", partName = "accountAlias")
        String accountAlias,
        @WebParam(name = "unsubDate", partName = "unsubDate")
        XMLGregorianCalendar unsubDate,
        @WebParam(name = "unsubBy", partName = "unsubBy")
        String unsubBy,
        @WebParam(name = "narration", partName = "narration")
        String narration);

    /**
     * 
     * @param msisdn
     * @param keyOperator
     * @return
     *     returns com.sbs.easymbank.service.mmapi.KyCrequestResponse
     */
    @WebMethod(operationName = "KYCrequest")
    @WebResult(partName = "return")
    @Action(input = "http://service.ws.mobileBanking.digi/MethodeService/KYCrequestRequest", output = "http://service.ws.mobileBanking.digi/MethodeService/KYCrequestResponse")
    public KyCrequestResponse kyCrequest(
        @WebParam(name = "msisdn", partName = "msisdn")
        String msisdn,
        @WebParam(name = "keyOperator", partName = "keyOperator")
        String keyOperator);

    /**
     * 
     * @param accountAlias
     * @param accountName
     * @param services
     * @param msisdn
     * @param devise
     * @param keyOperator
     * @return
     *     returns com.sbs.easymbank.service.mmapi.SubscriptionResponse
     */
    @WebMethod(operationName = "Subscription")
    @WebResult(partName = "return")
    @Action(input = "http://service.ws.mobileBanking.digi/MethodeService/SubscriptionRequest", output = "http://service.ws.mobileBanking.digi/MethodeService/SubscriptionResponse")
    public SubscriptionResponse subscription(
        @WebParam(name = "msisdn", partName = "msisdn")
        String msisdn,
        @WebParam(name = "services", partName = "services")
        String services,
        @WebParam(name = "accountAlias", partName = "accountAlias")
        String accountAlias,
        @WebParam(name = "accountName", partName = "accountName")
        String accountName,
        @WebParam(name = "devise", partName = "devise")
        String devise,
        @WebParam(name = "keyOperator", partName = "keyOperator")
        String keyOperator);

}