/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletContext;

/**
 *
 * @author ALEXAUGUSTE
 */
public class Config {
    // private  Properties prop ;
    //  public  Config instance;

    private String desigantionOperateur;
    private String filiale;
    private String tokenParam;
    private String operaotorCodeParam;
    private String affiliateCodeParam;
    private String autorisationBalanceParam;
    private String autorisationMinistatementParam;
    private String autorisationB2WParam;
    private String autorisationW2BParam;
    private String tarificationParam;
    private String tarificationBalanceParam;
    private String tarificationMinistatementParam;
    private String tarificationB2WParam;
    private String tarificationW2BParam;
    private String modeIdle;
    private String facturier;
    private String compteFacturier;
    private String scriptCompta;

//    public static Config getInstance() {
//        return instance;
//    }
    public Config() {
        try {
            InputStream ips = Config.class.getClassLoader().getResourceAsStream("/config.properties");
            Properties prop = new Properties();
            prop.load(ips);
            desigantionOperateur = prop.getProperty("operator");
            filiale = prop.getProperty("filiale");
            tokenParam = prop.getProperty("token");
            operaotorCodeParam = prop.getProperty("operatorCode");
            affiliateCodeParam = prop.getProperty("affiliateCode");
            autorisationBalanceParam = prop.getProperty("autorisationBalance");
            autorisationMinistatementParam = prop.getProperty("autorisationMiniStatement");
            autorisationB2WParam = prop.getProperty("autorisationB2W");
            autorisationW2BParam = prop.getProperty("autorisationW2B");
            tarificationParam = prop.getProperty("tarification");
            tarificationBalanceParam = prop.getProperty("tarificationBalance");
            tarificationMinistatementParam = prop.getProperty("tarificationMiniStatement");
            tarificationB2WParam = prop.getProperty("tarificationB2W");
            tarificationW2BParam = prop.getProperty("tarificationW2B");
            modeIdle = prop.getProperty("modeIdle");
            facturier = prop.getProperty("facturier");
            compteFacturier = prop.getProperty("compteFacturier");
            scriptCompta = prop.getProperty("scriptCompta");
        } catch (IOException ex) {
        }

    }

    public String getDesigantionOperateur() {
        return desigantionOperateur;
    }

    public void setDesigantionOperateur(String desigantionOperateur) {
        this.desigantionOperateur = desigantionOperateur;
    }

    public String getFiliale() {
        return filiale;
    }

    public void setFiliale(String filiale) {
        this.filiale = filiale;
    }

    public String getTokenParam() {
        return tokenParam;
    }

    public void setTokenParam(String tokenParam) {
        this.tokenParam = tokenParam;
    }

    public String getOperaotorCodeParam() {
        return operaotorCodeParam;
    }

    public void setOperaotorCodeParam(String OperaotorCodeParam) {
        this.operaotorCodeParam = OperaotorCodeParam;
    }

    public String getAffiliateCodeParam() {
        return affiliateCodeParam;
    }

    public void setAffiliateCodeParam(String affiliateCodeParam) {
        this.affiliateCodeParam = affiliateCodeParam;
    }

    public String getAutorisationBalanceParam() {
        return autorisationBalanceParam;
    }

    public void setAutorisationBalanceParam(String autorisationBalanceParam) {
        this.autorisationBalanceParam = autorisationBalanceParam;
    }

    public String getAutorisationMinistatementParam() {
        return autorisationMinistatementParam;
    }

    public void setAutorisationMinistatementParam(String autorisationMinistatementParam) {
        this.autorisationMinistatementParam = autorisationMinistatementParam;
    }

    public String getAutorisationB2WParam() {
        return autorisationB2WParam;
    }

    public void setAutorisationB2WParam(String autorisationB2WParam) {
        this.autorisationB2WParam = autorisationB2WParam;
    }

    public String getAutorisationW2BParam() {
        return autorisationW2BParam;
    }

    public void setAutorisationW2BParam(String autorisationW2BParam) {
        this.autorisationW2BParam = autorisationW2BParam;
    }

    public String getTarificationParam() {
        return tarificationParam;
    }

    public void setTarificationParam(String tarificationParam) {
        this.tarificationParam = tarificationParam;
    }

    public String getTarificationBalanceParam() {
        return tarificationBalanceParam;
    }

    public void setTarificationBalanceParam(String tarificationBalanceParam) {
        this.tarificationBalanceParam = tarificationBalanceParam;
    }

    public String getTarificationMinistatementParam() {
        return tarificationMinistatementParam;
    }

    public void setTarificationMinistatementParam(String tarificationMinistatementParam) {
        this.tarificationMinistatementParam = tarificationMinistatementParam;
    }

    public String getModeIdle() {
        return modeIdle;
    }

    public void setModeIdle(String modeIdle) {
        this.modeIdle = modeIdle;
    }

    public String getFacturier() {
        return facturier;
    }

    public void setFacturier(String facturier) {
        this.facturier = facturier;
    }

    public String getCompteFacturier() {
        return compteFacturier;
    }

    public void setCompteFacturier(String compteFacturier) {
        this.compteFacturier = compteFacturier;
    }

    public String getScriptCompta() {
        return scriptCompta;
    }

    public void setScriptCompta(String scriptCompta) {
        this.scriptCompta = scriptCompta;
    }

    public String getTarificationB2WParam() {
        return tarificationB2WParam;
    }

    public void setTarificationB2WParam(String tarificationB2WParam) {
        this.tarificationB2WParam = tarificationB2WParam;
    }

    public String getTarificationW2BParam() {
        return tarificationW2BParam;
    }

    public void setTarificationW2BParam(String tarificationW2BParam) {
        this.tarificationW2BParam = tarificationW2BParam;
    }

    public String[] getListParam() {
        String[] tab = {filiale, tokenParam, operaotorCodeParam, affiliateCodeParam, autorisationBalanceParam,
            autorisationMinistatementParam, autorisationB2WParam, tarificationParam, tarificationBalanceParam,
            tarificationMinistatementParam, tarificationB2WParam, tarificationW2BParam,
            modeIdle, autorisationW2BParam, facturier, compteFacturier};
        return tab;
    }
}
