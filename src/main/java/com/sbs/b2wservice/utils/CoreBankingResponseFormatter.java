/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.utils;

/**
 *
 * @author hp
 */
public class CoreBankingResponseFormatter {
    public static String format(int status, String message, String statusData){
        String s1 = "{\"RESULTSET\":[{\"Statut\":\"";
        String s2 = "\",\"StatutMsg\":\"";
        String s3 = "\",\"StatutData\":[";
        String s4 = "]}]}";
        
        return s1 + status + s2 + message  +s3 + statusData + s4;
    }
}
