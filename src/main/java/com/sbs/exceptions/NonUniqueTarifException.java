/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.exceptions;

/**
 *
 * @author alex
 */
public class NonUniqueTarifException extends Exception {

    public NonUniqueTarifException() {
    }

    public NonUniqueTarifException(String service, String profil) {
        super("Plus tarifs sont définis pour le service " + service + " aux clients " + profil);
    }

}
