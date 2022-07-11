/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.entities;

/**
 *
 * @author SOCITECH-
 */
public class Comptes {

    private String numero;
    private String agence;
    private String ncg;
    private String libNcg;
    private String coddci;
    private String expl;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getNcg() {
        return ncg;
    }

    public void setNcg(String ncg) {
        this.ncg = ncg;
    }

    public String getLibNcg() {
        return libNcg;
    }

    public void setLibNcg(String libNcg) {
        this.libNcg = libNcg;
    }

    public String getCoddci() {
        return coddci;
    }

    public void setCoddci(String coddci) {
        this.coddci = coddci;
    }

    public String getExpl() {
        return expl;
    }

    public void setExpl(String expl) {
        this.expl = expl;
    }

}
