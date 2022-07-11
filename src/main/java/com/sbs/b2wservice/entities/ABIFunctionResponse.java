/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SOCITECH-
 */
public class ABIFunctionResponse {

    private String statut;
    private String statutMsg;
    private HashMap<String, String> signaletique = new HashMap<>();
    //Propriété pour récupérer une liste de comptes
    private List<HashMap<String, String>> comptes = new ArrayList<>();
    //Propriété pour récupérer une liste de transactions (dans le cas de mini-relevé par exemple)
    private List<HashMap<String, String>> transactions = new ArrayList<>();
    private String NoOper;
    //Sous T24 le solde vient sous la forme {Compte:"",Montant:""}, beaucoup différent d'Orion
    //Cet objet permet de recupérer le solde sous T24
    private HashMap<String, String> solde = new HashMap<>();

    public ABIFunctionResponse() {
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getStatutMsg() {
        return statutMsg;
    }

    public void setStatutMsg(String StatutMsg) {
        this.statutMsg = StatutMsg;
    }

    public Map<String, String> getSignaletique() {
        return signaletique;
    }

    public void setSignaletique(HashMap<String, String> signaletique) {
        this.signaletique = signaletique;
    }

    public List<HashMap<String, String>> getComptes() {
        return comptes;
    }

    public void setComptes(List<HashMap<String, String>> comptes) {
        this.comptes = comptes;
    }

    public List<HashMap<String, String>> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<HashMap<String, String>> transactions) {
        this.transactions = transactions;
    }

    public String getNoOper() {
        return NoOper;
    }

    public void setNoOper(String NoOper) {
        this.NoOper = NoOper;
    }

    public Map<String, String> getSolde() {
        return solde;
    }

    public void setSolde(HashMap<String, String> solde) {
        this.solde = solde;
    }

}
