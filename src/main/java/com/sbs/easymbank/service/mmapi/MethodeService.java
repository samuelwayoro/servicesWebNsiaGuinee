/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.easymbank.service.mmapi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;

@WebService(
   name = "MethodeService",
   targetNamespace = "http://service.ws.mobileBanking.digi/"
)
@SOAPBinding(
   style = Style.RPC
)
@XmlSeeAlso({ObjectFactory.class})
public interface MethodeService {
   @WebMethod
   @WebResult(
      partName = "return"
   )
   @Action(
      input = "http://service.ws.mobileBanking.digi/MethodeService/unsubscriptionRequest",
      output = "http://service.ws.mobileBanking.digi/MethodeService/unsubscriptionResponse"
   )
   UnsubscriptionResponse unsubscription(@WebParam(name = "accountAlias",partName = "accountAlias") String var1, @WebParam(name = "unsubDate",partName = "unsubDate") XMLGregorianCalendar var2, @WebParam(name = "unsubBy",partName = "unsubBy") String var3, @WebParam(name = "narration",partName = "narration") String var4);

   @WebMethod(
      operationName = "KYCrequest"
   )
   @WebResult(
      partName = "return"
   )
   @Action(
      input = "http://service.ws.mobileBanking.digi/MethodeService/KYCrequestRequest",
      output = "http://service.ws.mobileBanking.digi/MethodeService/KYCrequestResponse"
   )
   KyCrequestResponse kyCrequest(@WebParam(name = "msisdn",partName = "msisdn") String var1, @WebParam(name = "keyOperator",partName = "keyOperator") String var2);

   @WebMethod(
      operationName = "Subscription"
   )
   @WebResult(
      partName = "return"
   )
   @Action(
      input = "http://service.ws.mobileBanking.digi/MethodeService/SubscriptionRequest",
      output = "http://service.ws.mobileBanking.digi/MethodeService/SubscriptionResponse"
   )
   SubscriptionResponse subscription(@WebParam(name = "msisdn",partName = "msisdn") String var1, @WebParam(name = "services",partName = "services") String var2, @WebParam(name = "accountAlias",partName = "accountAlias") String var3, @WebParam(name = "accountName",partName = "accountName") String var4, @WebParam(name = "devise",partName = "devise") String var5, @WebParam(name = "keyOperator",partName = "keyOperator") String var6);
}
