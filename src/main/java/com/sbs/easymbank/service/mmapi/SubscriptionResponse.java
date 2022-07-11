/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.easymbank.service.mmapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "subscriptionResponse",
   propOrder = {"accountAlias", "operatorCode", "responseCode"}
)
public class SubscriptionResponse extends Abonnekyc {
   protected String accountAlias;
   protected String operatorCode;
   protected String responseCode;

   public String getAccountAlias() {
      return this.accountAlias;
   }

   public void setAccountAlias(String value) {
      this.accountAlias = value;
   }

   public String getOperatorCode() {
      return this.operatorCode;
   }

   public void setOperatorCode(String value) {
      this.operatorCode = value;
   }

   public String getResponseCode() {
      return this.responseCode;
   }

   public void setResponseCode(String value) {
      this.responseCode = value;
   }
}
