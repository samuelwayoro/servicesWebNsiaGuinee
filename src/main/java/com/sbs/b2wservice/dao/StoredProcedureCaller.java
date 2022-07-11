/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbs.b2wservice.entities.ABIFunctionResponse;
import com.sbs.b2wservice.entities.Webproccor;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.StoredFunctionCall;
import org.eclipse.persistence.queries.ValueReadQuery;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.patware.bean.BeanInfoFactory;
import org.patware.bean.Property;

/**
 *
 * @author SOCITECH-
 */
@Stateless
public class StoredProcedureCaller {

    @EJB
    private WebproccorFacade webproccorFacade;

    @PersistenceContext(unitName = "B2WServicePUOrion")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return this.em;
    }

    public Object callFunction(String webServiceMethod, Object paramObjet, String operator) {
        System.out.println("Entrer de callFunction de la transaction  " + webServiceMethod + " en cours ...");
        List<Webproccor> list = this.webproccorFacade.findByWebServiceMethod(webServiceMethod, operator);
        int j = 1;
        Object object = null;
        String procName = ((Webproccor) list.get(0)).getProc();
        //System.out.println("PROCNAME RECUPERE " + procName);
        StoredFunctionCall functionCall = new StoredFunctionCall();
        functionCall.setProcedureName(procName);
        functionCall.setResult("RESULT", String.class);
        Property[] properties = BeanInfoFactory.getProperties(paramObjet);
        //System.out.println("LISTE DES DONNEES DE PROPERTIES : ");

//        for (int p = 0; p < properties.length; p++) {
//            System.out.println("PROPERTIE NUMERO   " + p + "  VALEUR ----> " + properties[p].getPropertyName() + " values " + properties[p].getPropertyValue());
//        }
        if (((Webproccor) list.get(0)).getParaproc() != null && !((Webproccor) list.get(0)).getParaproc().isEmpty()) {
//            System.out.println("LISTE DES WEBPROCCORS TRAITES");

//            for (Webproccor wp : list) {
//                System.out.println("PARAPROC " + wp.getParaproc() + " PARAWEB " + wp.getParaweb() + " PROC " + wp.getRefproc() + " REFWEB    " + wp.getRefweb() + " TYPEPARAM    " + wp.getTypeparam() + " VALEUR  " + wp.getValeur() + "  WEBSERVICEMETHOD   " + wp.getWebservicemethod() + "  OPERATEUR  " + wp.getOperateur());
//            }
            for (Webproccor w : list) {

                if (w.getParaweb() == null) {
//                    System.out.println("paraweb null");
                    functionCall.addNamedArgumentValue(w.getParaproc().trim(), w.getValeur());
                    //System.out.println(w.getParaproc().trim() + " : " + w.getValeur());
                    continue;
                }

                for (int i = 0; i < properties.length; i++) {
                    if (w.getParaweb().equals(properties[i].getPropertyName())) {
//                        System.out.println("le paraweb en cours est  :----->"+w.getParaweb());
//                        System.out.println("le propertie en cours --->  "+properties[i].getPropertyName()+"  sa valeur  --->"+properties[i].getPropertyValue());
                        if (!properties[i].getPropertyName().equals("amount") && !properties[i].getPropertyName().equals("charge")) {
//                            try {
//                                System.out.println("w.getParaproc    " + w.getParaproc().trim());
//                            } catch (Exception e) {
//                                System.out.println("le wgetParaproc est vide");
//                            }
//                            try {
//                                System.out.println("properties[i].getPropertyValue.toString()   " + properties[i].getPropertyValue().toString());
//                            } catch (Exception e) {
//                                System.out.println("le properties est vide ...");
//                            }
                            functionCall.addNamedArgumentValue(w.getParaproc().trim(), properties[i].getPropertyValue().toString());
                            break;
                        }
                        functionCall.addNamedArgumentValue(w.getParaproc().trim(), new BigDecimal(((Double) properties[i].getPropertyValue()).doubleValue()));
                        break;
                    }
                }
            }
        }
        ValueReadQuery valQuery = new ValueReadQuery();
        valQuery.setCall(functionCall);
        Query query = ((JpaEntityManager) this.em.getDelegate()).createQuery(valQuery);
        object = query.getSingleResult();
        System.out.println("*******************fin de la transaction  " + webServiceMethod + " en cours ...*****************");
        return object;
    }

//    public Object callFunction(String webServiceMethod, Object paramObjet, String operator) {
//        System.out.println("debut de la fonction  callFunction");
//        List<Webproccor> list = this.webproccorFacade.findByWebServiceMethod(webServiceMethod, operator);
//        int j = 1;
//        Object object = null;
//        String procName = ((Webproccor) list.get(0)).getProc(); 
//        System.out.println("procName " + procName);//SETPOST
//        StoredFunctionCall functionCall = new StoredFunctionCall();
//        functionCall.setProcedureName(procName);
//        functionCall.setResult("RESULT", String.class);
//        Property[] properties = BeanInfoFactory.getProperties(paramObjet);
//        if (((Webproccor) list.get(0)).getParaproc() != null && !((Webproccor) list.get(0)).getParaproc().isEmpty()) {
//            for (Webproccor w : list) {
//                if (w.getParaweb() == null) {
//                    System.out.println("le paraweb est null pour ce webproccor : PARAPROC     "+w.getParaproc()+"     les parametres passees  la methode sont :  w.getparaproc.trim =       "+w.getParaproc().trim()+"  lautre :         "+w.getValeur());
//                    functionCall.addNamedArgumentValue(w.getParaproc().trim(), w.getValeur()); //
//                    System.out.println("#####################"+w.getParaproc().trim() + " : " + w.getValeur()+"######################");
//                    System.out.println("on retourne a la boucle ....");
//                    continue;
//                }
//                for (int i = 0; i < properties.length; i++) {
//                    System.out.println("propertie  :    "+i+"    =>  "+properties[i].getPropertyName());
//                    if (w.getParaweb().equals(properties[i].getPropertyName())) {
//                        System.out.println("PARAWEB EST EGAL AU PROPERTYNAME  :   LE VOICI  =>   "+w.getParaweb()+ "   ET   "+properties[i].getPropertyName());
//                        if (!properties[i].getPropertyName().equals("amount") && !properties[i].getPropertyName().equals("charge")) {
//                            System.out.println("CE PROPERTYNAME EST BIEN DIFFERENT DE AMOUNT ET CHARGE , POUR PREUVE :    "+properties[i].getPropertyName());
//                            try {
//                                System.out.println("les parametres passes a functionCall sont :   "+w.getParaproc().trim()+"    "+properties[i].getPropertyValue().toString());
//                                functionCall.addNamedArgumentValue(w.getParaproc().trim(), properties[i].getPropertyValue().toString());
//                            } catch (Exception e) {
//                                System.out.println("ATTENTION IL Y A UN SOUCI , --->" + e.getMessage());
//                            }
//                            break;
//                        }
//                        System.out.println("le propertiname est egal a amount ou charge  preuve  :  "+properties[i].getPropertyName());
//                         System.out.println("paraproc :  "+w.getParaproc().trim()+"   et    propertie  : "+properties[i].getPropertyValue());
//                        try {
//                            functionCall.addNamedArgumentValue(w.getParaproc().trim(), properties[i].getPropertyValue());
//                        } catch (Exception e) {
//                            System.out.println("OUPS ->" + e.getMessage());
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        ValueReadQuery valQuery = new ValueReadQuery();
//        valQuery.setCall(functionCall);
//
//        System.out.println("Paraweb " + ((Webproccor) list.get(0)).getParaweb());
//        System.out.println("functionCall " + functionCall.toString());
//        System.out.println("functionCall " + functionCall.getProcedureArgumentNames());
//        System.out.println("valQuery " + valQuery.getCall().getQueryString());
//        Query query = ((JpaEntityManager) this.em.getDelegate()).createQuery(valQuery);
//
//        object = query.getSingleResult();
//        return object;
//    }

    /*
    public Object callFunction(String webServiceMethod, Object paramObjet, String operator) {
        System.out.println("****************************DEBUT DE LA FONCTION CALLFUNCTION****************");
        System.out.println("LES PARAMETRES ENTRES SONT : " + webServiceMethod + "   " + paramObjet.toString() + " " + operator);
        List<Webproccor> list = this.webproccorFacade.findByWebServiceMethod(webServiceMethod, operator);
        //System.out.println("la liste des webproccor recupere pour cet operateur sont : ");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("element " + i + " ----> " + list.get(i).getParaproc());
//        }
        Object object = null;
        String procName = ((Webproccor) list.get(0)).getProc();
        //System.out.println("recuperation du procName " + procName + "  de  : " + list.get(0).getParaproc());
        StoredFunctionCall functionCall = new StoredFunctionCall();
        functionCall.setProcedureName(procName);//FACTURIER
        functionCall.setResult("RESULT", String.class);//
        Property[] properties = BeanInfoFactory.getProperties(paramObjet);
        //System.out.println("LA TAILLE DE PROPERTIES : " + properties.length);
       // System.out.println("***************************SON CONTENU  : ***********************************************");
//        for (Property p : properties) {
//            System.out.println("***********" + p.getPropertyName() + " ********************");
//        }
       // System.out.println("fin de la boucle sur la liste des properties ******************");
        if (list.get(0).getParaproc() != null && !list.get(0).getParaproc().isEmpty()) {
            for (Webproccor w : list) {
                if (w.getParaweb() == null) {
                    System.out.println("LE WEBPROCCOR SELECTIONNEE : " + w.getParaproc() + " " + w.getProc() + " " + w.getRefproc() + " " + w.getTypeparam() + " " + w.getValeur() + " " + w.getWebservicemethod() + "  " + w.getOperateur());
                    //System.out.println("LES PARAMETRES ENVOYES A FUNCTIONCALL" + w.getParaproc().trim() + " ET  " + w.getValeur());
                    functionCall.addNamedArgumentValue(w.getParaproc().trim(), w.getValeur());
                    continue;
                }
                for (int i = 0; i < properties.length; i++) {
                    //afficher la liste des properties
                    System.out.println("######## PROPERTIE NUMERO " + i + "----------->" + properties[i].getPropertyName() + " son value ----------->" + properties[i].getPropertyValue().toString());
                    
                    if (w.getParaweb().equals(properties[i].getPropertyName())) {
                        
                        System.out.println("****************LE PROPERTI EN COURS EST : " + properties[i].getPropertyName());
                        
                        
                        if (!properties[i].getPropertyName().equals("amount") && !properties[i].getPropertyName().equals("charge")) {
                            
                            System.out.println("****************LE PROPERTI EN TESTER DIFFERENT DE AMOUNT ET CHARGE : " + properties[i].getPropertyName());

                            functionCall.addNamedArgumentValue(w.getParaproc().trim(), properties[i].getPropertyValue().toString());
                            break;
                        }
                        System.out.println("****************LE PROPERTI EN COURS TESTER NON DIFFERENT DE AMOUNT ET CHARGE : " + properties[i].getPropertyName());

                        functionCall.addNamedArgumentValue(w.getParaproc().trim(), properties[i].getPropertyValue().toString());
                        break;
                        
                    }
                    System.out.println("difference entre ce webproccor  "+w.getParaweb()+"  et   le propertie suivant :  "+properties[i].getPropertyName());
                }
            }
        }
        ValueReadQuery valQuery = new ValueReadQuery();
        valQuery.setCall(functionCall);
        Query query = ((JpaEntityManager) this.em.getDelegate()).createQuery(valQuery);
        object = query.getSingleResult();
        return object;
    } */
    public Object callFunction(String webServiceMethod, Object paramObjet, String paraProc, String valeur, String operator) {
        System.out.println("recuperation de la procedure :  "+webServiceMethod+"     de l'operateur "+operator);
        List<Webproccor> list = this.webproccorFacade.findByWebServiceMethod(webServiceMethod, operator);
        if (!list.isEmpty()) {
            System.out.println("une procedure a ete trouvee dans la table webproccor");
            System.out.println("---->"+list.get(0).getProc());
        }
        Object object = null;
        String procName = (list.get(0)).getProc();
        StoredFunctionCall functionCall = new StoredFunctionCall();
        functionCall.setProcedureName(procName);
        functionCall.setResult("RESULT", String.class);
        functionCall.addNamedArgumentValue(paraProc, valeur);
        Property[] properties = BeanInfoFactory.getProperties(paramObjet);
        if ((list.get(0)).getParaproc() != null && !(list.get(0)).getParaproc().isEmpty()) {
            for (Webproccor w : list) {
                if (w.getParaweb() == null) {
                    functionCall.addNamedArgumentValue(w.getParaproc().trim(), w.getValeur());
                    continue;
                }
                for (int i = 0; i < properties.length; i++) {
                    if (w.getParaweb().equals(properties[i].getPropertyName())) {
                        functionCall.addNamedArgumentValue(w.getParaproc().trim(), properties[i].getPropertyValue().toString());
                    }
                }
            }
        }
        ValueReadQuery valQuery = new ValueReadQuery();
        valQuery.setCall(functionCall);
        Query query = ((JpaEntityManager) this.em.getDelegate()).createQuery(valQuery);
        object = query.getSingleResult();
        return object;
    }

    public ABIFunctionResponse getABIFunctionResponse(Object object){ 

        ABIFunctionResponse abifr = new ABIFunctionResponse();

        try {
            String response = (String) object;
            System.out.println("REPONSE - CORE BANKING : " + response);
            JSONObject jSONObject = new JSONObject(response);
            ObjectMapper mapper = new ObjectMapper();
            JSONArray jsonArray = jSONObject.getJSONArray("RESULTSET");

            abifr.setStatut((String) jsonArray.getJSONObject(0).get("Statut").toString());
            abifr.setStatutMsg((String) jsonArray.getJSONObject(0).get("StatutMsg"));

            JSONArray statutData = jsonArray.getJSONObject(0).optJSONArray("StatutData");
            //Sous Orion la clé "Comptes" réference un tableau d'objets JSON
            if (statutData != null && statutData.optJSONObject(0).has("Comptes")) {
                JSONArray lesComptes = statutData.getJSONObject(0).getJSONArray("Comptes");
                //Map<String,String> cpte=new HashMap<>();
                for (int i = 0; i < lesComptes.length(); i++) {
                    abifr.getComptes().add((HashMap) mapper.readValue(lesComptes.get(i).toString(), new TypeReference<HashMap<String, String>>() {
                    }));
                }
            }//Sous T24 la clé "Compte" (sans s) réference un objet JSON
            else if (statutData != null && statutData.optJSONObject(0).has("Compte")) {
                abifr.setSolde((HashMap) mapper.readValue(statutData.getJSONObject(0).toString(), new TypeReference<HashMap<String, String>>() {
                }));
            }//La clé "mvts" référence une liste de transactions
            else if (statutData != null && statutData.optJSONObject(0).has("mvts")) {
                JSONArray lesMvts = statutData.getJSONObject(0).getJSONArray("mvts");
                for (int i = 0; i < lesMvts.length(); i++) {
                    abifr.getTransactions().add((HashMap) mapper.readValue(lesMvts.get(i).toString(), new TypeReference<HashMap<String, String>>() {
                    }));
                }
            }

            if (statutData != null && statutData.optJSONObject(0).has("Nooper")) {
                abifr.setNoOper(statutData.getJSONObject(0).get("Nooper").toString());
            }
            if (statutData != null && statutData.optJSONObject(0).has("Agec") && (statutData.getJSONObject(0).get("Agec").equals("PAR") || statutData.getJSONObject(0).get("Agec").equals("PER"))) {
                if (statutData.optJSONObject(0).has("Nom")) {
                    abifr.getSignaletique().put("Nom", statutData.getJSONObject(0).get("Nom").toString().trim());
                }
                // A DECOMMENTER APRES CORRECTION DE LA METHODE ORION PAR CEDRIC 
//                if (statutData.optJSONObject(0).has("Prenom")) {
//                    abifr.getSignaletique().put("Prenom", statutData.getJSONObject(0).get("Prenom").toString().trim());
//                }
            }
            if (statutData != null && statutData.optJSONObject(0).has("Agec") && !statutData.getJSONObject(0).get("Agec").equals("PAR") && !statutData.getJSONObject(0).get("Agec").equals("PER")) {
                if (statutData.optJSONObject(0).has("NomPrenom")) {
                    abifr.getSignaletique().put("Nom", statutData.getJSONObject(0).get("NomPrenom").toString().trim());
                    abifr.getSignaletique().put("Prenom", "");
                }
            }
            if (statutData != null && statutData.optJSONObject(0).has("Email")) {
                abifr.getSignaletique().put("Email", statutData.getJSONObject(0).get("Email").toString());
            }
            if (statutData != null && statutData.optJSONObject(0).has("Tel")) {
                abifr.getSignaletique().put("Tel", statutData.getJSONObject(0).get("Tel").toString());
            }
            if (statutData != null && statutData.optJSONObject(0).has("Client")) {
                abifr.getSignaletique().put("Client", statutData.getJSONObject(0).get("Client").toString());
            }
            if (statutData != null && statutData.optJSONObject(0).has("PieceId")) {
                abifr.getSignaletique().put("cni", statutData.getJSONObject(0).get("PieceId").toString());
            }
            if (statutData != null && statutData.optJSONObject(0).has("DateNais")) {
                abifr.getSignaletique().put("DateNais", statutData.getJSONObject(0).get("DateNais").toString());
            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(StoredProcedureCaller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("contenu de la reponse envoyee au back office   "+abifr.getSignaletique());
            return abifr;
        }
    }

    //fermeture de la connexion a Orion 
    @PreDestroy

    public void destruct() {
        em.close();
    }
}
