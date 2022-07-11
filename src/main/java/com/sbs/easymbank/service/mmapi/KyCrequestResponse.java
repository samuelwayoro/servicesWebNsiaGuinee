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
   name = "kyCrequestResponse",
   propOrder = {"responseCode"}
)
public class KyCrequestResponse extends Abonnekyc {
   protected String responseCode;

   public String getResponseCode() {
      return this.responseCode;
   }

   public void setResponseCode(String value) {
      this.responseCode = value;
   }
}