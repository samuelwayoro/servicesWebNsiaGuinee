/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.easymbank.service.mmapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "abonnekyc",
   propOrder = {"dateExpiration", "imsi", "kyCrspDOB", "kyCrspFirstname", "kyCrspIdRefNo", "kyCrspLastname", "kyCrspType", "nameFilePieceRecto", "nameFilePieceVerso", "number", "pieceRecto", "pieceVerso"}
)
@XmlSeeAlso({SubscriptionResponse.class, KyCrequestResponse.class})
public class Abonnekyc {
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar dateExpiration;
   protected String imsi;
   @XmlElement(
      name = "KYCrspDOB"
   )
   protected String kyCrspDOB;
   @XmlElement(
      name = "KYCrspFirstname"
   )
   protected String kyCrspFirstname;
   @XmlElement(
      name = "KYCrspIdRefNo"
   )
   protected String kyCrspIdRefNo;
   @XmlElement(
      name = "KYCrspLastname"
   )
   protected String kyCrspLastname;
   @XmlElement(
      name = "KYCrspType"
   )
   protected String kyCrspType;
   protected String nameFilePieceRecto;
   protected String nameFilePieceVerso;
   protected String number;
   protected String pieceRecto;
   protected String pieceVerso;

   public XMLGregorianCalendar getDateExpiration() {
      return this.dateExpiration;
   }

   public void setDateExpiration(XMLGregorianCalendar value) {
      this.dateExpiration = value;
   }

   public String getImsi() {
      return this.imsi;
   }

   public void setImsi(String value) {
      this.imsi = value;
   }

   public String getKYCrspDOB() {
      return this.kyCrspDOB;
   }

   public void setKYCrspDOB(String value) {
      this.kyCrspDOB = value;
   }

   public String getKYCrspFirstname() {
      return this.kyCrspFirstname;
   }

   public void setKYCrspFirstname(String value) {
      this.kyCrspFirstname = value;
   }

   public String getKYCrspIdRefNo() {
      return this.kyCrspIdRefNo;
   }

   public void setKYCrspIdRefNo(String value) {
      this.kyCrspIdRefNo = value;
   }

   public String getKYCrspLastname() {
      return this.kyCrspLastname;
   }

   public void setKYCrspLastname(String value) {
      this.kyCrspLastname = value;
   }

   public String getKYCrspType() {
      return this.kyCrspType;
   }

   public void setKYCrspType(String value) {
      this.kyCrspType = value;
   }

   public String getNameFilePieceRecto() {
      return this.nameFilePieceRecto;
   }

   public void setNameFilePieceRecto(String value) {
      this.nameFilePieceRecto = value;
   }

   public String getNameFilePieceVerso() {
      return this.nameFilePieceVerso;
   }

   public void setNameFilePieceVerso(String value) {
      this.nameFilePieceVerso = value;
   }

   public String getNumber() {
      return this.number;
   }

   public void setNumber(String value) {
      this.number = value;
   }

   public String getPieceRecto() {
      return this.pieceRecto;
   }

   public void setPieceRecto(String value) {
      this.pieceRecto = value;
   }

   public String getPieceVerso() {
      return this.pieceVerso;
   }

   public void setPieceVerso(String value) {
      this.pieceVerso = value;
   }
}
