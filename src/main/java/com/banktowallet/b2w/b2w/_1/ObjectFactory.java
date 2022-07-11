/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banktowallet.b2w.b2w._1;

import javax.xml.bind.annotation.XmlRegistry;

/**
 *
 * @author samuel
 */
public class ObjectFactory {
    public TransactionDetail createTransactionDetail() {
    return new TransactionDetail();
  }
  
  public HeaderResponse createHeaderResponse() {
    return new HeaderResponse();
  }
  
  public HeaderRequest createHeaderRequest() {
    return new HeaderRequest();
  }
}
