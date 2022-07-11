/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.easymbank.service.mmapi;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(
   name = "mbInscriptionWsService",
   targetNamespace = "http://service.ws.mobileBanking.digi/",
   wsdlLocation = "file:/C:/Users/ALEXAUGUSTE/Documents/EASYMBANK/MTN/MTN/mbInscriptionWs.wsdl"
)
public class MbInscriptionWsService extends Service {
   private static final URL MBINSCRIPTIONWSSERVICE_WSDL_LOCATION;
   private static final WebServiceException MBINSCRIPTIONWSSERVICE_EXCEPTION;
   private static final QName MBINSCRIPTIONWSSERVICE_QNAME = new QName("http://service.ws.mobileBanking.digi/", "mbInscriptionWsService");

   public MbInscriptionWsService() {
      super(__getWsdlLocation(), MBINSCRIPTIONWSSERVICE_QNAME);
   }

   public MbInscriptionWsService(WebServiceFeature... features) {
      super(__getWsdlLocation(), MBINSCRIPTIONWSSERVICE_QNAME, features);
   }

   public MbInscriptionWsService(URL wsdlLocation) {
      super(wsdlLocation, MBINSCRIPTIONWSSERVICE_QNAME);
   }

   public MbInscriptionWsService(URL wsdlLocation, WebServiceFeature... features) {
      super(wsdlLocation, MBINSCRIPTIONWSSERVICE_QNAME, features);
   }

   public MbInscriptionWsService(URL wsdlLocation, QName serviceName) {
      super(wsdlLocation, serviceName);
   }

   public MbInscriptionWsService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
      super(wsdlLocation, serviceName, features);
   }

   @WebEndpoint(
      name = "mbInscriptionWsPort"
   )
   public MethodeService getMbInscriptionWsPort() {
      return (MethodeService)super.getPort(new QName("http://service.ws.mobileBanking.digi/", "mbInscriptionWsPort"), MethodeService.class);
   }

   @WebEndpoint(
      name = "mbInscriptionWsPort"
   )
   public MethodeService getMbInscriptionWsPort(WebServiceFeature... features) {
      return (MethodeService)super.getPort(new QName("http://service.ws.mobileBanking.digi/", "mbInscriptionWsPort"), MethodeService.class, features);
   }

   private static URL __getWsdlLocation() {
      if (MBINSCRIPTIONWSSERVICE_EXCEPTION != null) {
         throw MBINSCRIPTIONWSSERVICE_EXCEPTION;
      } else {
         return MBINSCRIPTIONWSSERVICE_WSDL_LOCATION;
      }
   }

   static {
      URL url = null;
      WebServiceException e = null;

      try {
         url = new URL("file:/C:/Users/ALEXAUGUSTE/Documents/EASYMBANK/MTN/MTN/mbInscriptionWs.wsdl");
      } catch (MalformedURLException var3) {
         e = new WebServiceException(var3);
      }

      MBINSCRIPTIONWSSERVICE_WSDL_LOCATION = url;
      MBINSCRIPTIONWSSERVICE_EXCEPTION = e;
   }
}
