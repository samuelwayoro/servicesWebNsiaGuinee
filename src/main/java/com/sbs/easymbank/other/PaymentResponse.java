/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.easymbank.other;

import com.sbs.b2wservice.entities.ABIFunctionResponse;

/**
 *
 * @author alex
 */
public class PaymentResponse {

    private String statut;
    private String statutMsg;
    private String statutData;

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getStatutMsg() {
        return statutMsg;
    }

    public void setStatutMsg(String statutMsg) {
        this.statutMsg = statutMsg;
    }

    public String getStatutData() {
        return statutData;
    }

    public void setStatutData(String statutData) {
        this.statutData = statutData;
    }

    public PaymentResponse(ABIFunctionResponse abifr) {
        statut = abifr.getStatut();
        statutMsg = abifr.getStatutMsg();
    }

    public PaymentResponse() {
    }

    public PaymentResponse(String statut, String statutMsg, String statutData) {
        this.statut = statut;
        this.statutMsg = statutMsg;
        this.statutData = statutData;
    }

    @Override
    public String toString() {
        return "statut: " + statut + " statutMsg: " + statutMsg + " statutData: " + statutData;
    }

}
