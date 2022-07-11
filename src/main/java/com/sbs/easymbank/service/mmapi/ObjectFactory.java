/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.easymbank.service.mmapi;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public UnsubscriptionResponse createUnsubscriptionResponse() {
      return new UnsubscriptionResponse();
   }

   public SubscriptionResponse createSubscriptionResponse() {
      return new SubscriptionResponse();
   }

   public KyCrequestResponse createKyCrequestResponse() {
      return new KyCrequestResponse();
   }

   public Abonnekyc createAbonnekyc() {
      return new Abonnekyc();
   }
}
