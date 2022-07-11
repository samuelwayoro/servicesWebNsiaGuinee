/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.t24;

import com.sbs.b2wservice.dao.ParametresFacade;
import com.sbs.b2wservice.entities.Parametres;
import com.sbs.exceptions.ParameterNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.ws.Holder;
import t24.*;

/**
 *
 * @author alexa
 */
@Stateless
public class T24Handler {

    @EJB
    private ParametresFacade parametresFacade;
    
    private Parametres company;
    private Parametres password;
    private Parametres username;
    private Parametres usernameCriteria;
    private Parametres columnName;
    private Parametres operand;
    private Parametres url;
    T24WebServicesImplService twsis;
    T24WebServicesImpl twsi;
    ObjectFactory objfact = new ObjectFactory();
    WebRequestCommon wrqc = objfact.createWebRequestCommon();

    private void setParameters() {
        List<Parametres> listPara = parametresFacade.findByTypeParam("T24_PARAM");
        for (Parametres p : listPara) {
            System.out.println(p.getCodeparam()+" : "+p.getValeur());
            switch (p.getCodeparam()) {
                case "COMPANY":
                    company = p;
                    break;
                case "PASSWORD":
                    password = p;
                    break;
                case "USERNAME":
                    username = p;
                    break;
                case "USERNAME_CRITERIA":
                    usernameCriteria = p;
                    break;
                case "COLUMNNAME":
                    columnName = p;
                    break;
                case "OPERAND":
                    operand = p;
                    break;
                case "URL_T24":
                    url = p;
                    break;
                default:
                    break;

            }
        }
    }

    private void validate() throws ParameterNotFoundException {
        if (company == null) {
            throw new ParameterNotFoundException("COMPANY");
        } else if (password == null) {
            throw new ParameterNotFoundException("PASSWORD");
        } else if (username == null) {
            throw new ParameterNotFoundException("USERNAME");
        }else if (usernameCriteria == null) {
            throw new ParameterNotFoundException("USERNAME_CRITERIA");
        } else if (columnName == null) {
            throw new ParameterNotFoundException("COLUMNNAME");
        } else if (operand == null) {
            throw new ParameterNotFoundException("OPERAND");
        } else if (url == null) {
            throw new ParameterNotFoundException("URL_T24");
        }

    }

    public Object callT24(String prefixeFonction, String... para)  {
        setParameters();
        try {
            validate();
        } catch (ParameterNotFoundException ex) {
            Logger.getLogger(T24Handler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("--->"+ex.getMessage());
        }
        wrqc.setUserName(username.getValeur());
        wrqc.setPassword(password.getValeur());
        wrqc.setCompany(company.getValeur());

        String resultat = "";
        List<String> retour = new ArrayList<>();
        EnquiryInput enqIn = objfact.createEnquiryInput();
        EnquiryInputCollection enqColl = objfact.createEnquiryInputCollection();
        enqColl.setColumnName(columnName.getValeur());
        enqColl.setOperand(operand.getValeur());
        StringBuilder criteriaValue = new StringBuilder(prefixeFonction + "|" + usernameCriteria.getValeur());
        for (String str : para) {
            criteriaValue.append("|").append(str);
        }
        enqColl.setCriteriaValue(criteriaValue.toString());
        enqIn.getEnquiryInputCollection().add(enqColl);
        Holder<List<TNAEFACTUREMAINType>> tnaefacturemainType0 = new Holder<>();
        Holder<Status> status = new Holder<>();

        try {
            twsis = new T24WebServicesImplService(new URL(url.getValeur()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(T24Handler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("====>"+ex.getMessage());
        }
        twsi = twsis.getT24WebServicesImplPort();
        twsi.tnaefacturemain(wrqc, enqIn, status, tnaefacturemainType0);
        resultat = tnaefacturemainType0.value.get(0).getGTNAEFACTUREMAINDetailType().getMTNAEFACTUREMAINDetailType().get(0).getREPONSEEFACTURE();
        return resultat;
    }

}
