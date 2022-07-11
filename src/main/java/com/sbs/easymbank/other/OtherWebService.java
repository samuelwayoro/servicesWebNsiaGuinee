/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.easymbank.other;

import com.sbs.b2wservice.dao.ParametresFacade;
import com.sbs.b2wservice.dao.RequestValidator;
import com.sbs.b2wservice.dao.StoredProcedureCaller;
import com.sbs.b2wservice.dao.WebproccorFacade;
import com.sbs.b2wservice.entities.ABIFunctionResponse;
import com.sbs.b2wservice.entities.Parametres;
import com.sbs.b2wservice.entities.Webproccor;
import com.sbs.b2wservice.t24.T24Handler;
import com.sbs.exceptions.ParameterNotFoundException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.StoredFunctionCall;
import org.eclipse.persistence.queries.ValueReadQuery;

/**
 *
 * @author SOCITECH-
 */
@WebService
public class OtherWebService {

    @PersistenceContext(unitName = "B2WServicePUOrion")
    private EntityManager em;
    @EJB
    private StoredProcedureCaller storedProcedureCaller;
    @EJB
    private ParametresFacade parametresFacade;
    @EJB
    private T24Handler t24Handler;
    @EJB
    private RequestValidator requestValidator;
    @EJB
    private WebproccorFacade webproccorFacade;

    private final DecimalFormat dcf = new DecimalFormat("###.##");
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    protected EntityManager getEntityManager() {
        return em;
    }

    @WebMethod
    public com.sbs.easymbank.other.Client getSignaletique(java.lang.String client) {
        //TODO implement this method
        Client retour = null;//afin de s'assurer que l'objet client est non nul seulement si le retour SIB ne signale pas une erreur 
        List<Parametres> listT24Activ = parametresFacade.findByCodeParam("ACTIVATION_T24");
        Parametres t24Activ = null;
        if (!listT24Activ.isEmpty()) {
            t24Activ = listT24Activ.get(0);
        }
        List<Parametres> listPrefixeSignaletique = parametresFacade.findByCodeParam("PREFIXE_SIGNALETIQUE");
        Parametres prefixeSignaletique = null;
        if (!listPrefixeSignaletique.isEmpty()) {
            prefixeSignaletique = listPrefixeSignaletique.get(0);
        }
        List<Parametres> deltaPara = parametresFacade.findByCodeParam("ACTIVATION_DELTA");
        Parametres deltaAutorisation = null;
        if (deltaPara != null && !deltaPara.isEmpty()) {
            deltaAutorisation = deltaPara.get(0);
        }

        List<Webproccor> lParaSignaletiqueMethod = webproccorFacade.findByWebServiceMethodOnly("getSignaletique");
        Webproccor signaletiqueMethod = null;
        if (lParaSignaletiqueMethod != null && !lParaSignaletiqueMethod.isEmpty()) {
            signaletiqueMethod = lParaSignaletiqueMethod.get(0);
        }
        System.out.println("le contenu de getsignaletique : paraproc  " + signaletiqueMethod.getParaproc() + " paraweb  " + signaletiqueMethod.getParaweb() + " proc " + signaletiqueMethod.getProc() + " webservicemethod " + signaletiqueMethod.getWebservicemethod());
        Object object = new Object();

        if (t24Activ != null && t24Activ.getValeur().equals("OUI")) {
            if (prefixeSignaletique != null) {
                object = t24Handler.callT24(prefixeSignaletique.getValeur(), client);
            } else {
                System.out.println("LE PREFIXE T24 DE LA SIGNALETIQUE CLIENT N'EST PAS RENSEIGNE");
            }
        } else {
            String procName = signaletiqueMethod.getProc();
            //Object object = storedProcedureCaller.callFunction("getSignaletique", client);
            StoredFunctionCall functionCall = new StoredFunctionCall();
            System.out.println("etape 1");
            functionCall.setProcedureName(procName);//val attendu GETS
            System.out.println("etape 2");
            functionCall.setResult("RESULT", String.class);
            System.out.println("etape 3");
            System.out.println("val 1 " + signaletiqueMethod.getParaproc() + " client " + client);
            functionCall.addNamedArgumentValue(signaletiqueMethod.getParaproc(), client);//vat
            System.out.println("etape 4");
            ValueReadQuery valQuery = new ValueReadQuery();
            System.out.println("etape 5");
            valQuery.setCall(functionCall);
            System.out.println("LA REQUETTE EXECUTEE *****************"+valQuery.getCall());
            System.out.println("etape 6");
            Query query = ((JpaEntityManager) em.getDelegate()).createQuery(valQuery);
            System.out.println("etape 7");
            try {
                object = query.getSingleResult();

            } catch (Exception e) {
                e.printStackTrace();
            }
//deltaFacade.getSignaletique(client);
        }
        ABIFunctionResponse afr = storedProcedureCaller.getABIFunctionResponse(object);
        if (afr == null) {
            System.out.println("la reponse du SIB est vide ...");
            retour = null ;
        }else{
            if (afr.getStatut().equals("0")) {
            retour = new Client();
            retour.setNom(afr.getSignaletique().get("Nom"));
            retour.setPrenom(afr.getSignaletique().get("Prenom"));
            retour.setTelephone(afr.getSignaletique().get("Tel"));
            retour.setDateNaiss(afr.getSignaletique().get("DateNais"));
            retour.setCni(afr.getSignaletique().get("cni"));
            Comptes comptes;
            for (int i = 0; i < afr.getComptes().size(); i++) {
                comptes = new Comptes();
                comptes.setAgence(afr.getComptes().get(i).get("Agence"));
                comptes.setCoddci(afr.getComptes().get(i).get("Coddci"));
                comptes.setExpl(afr.getComptes().get(i).get("Expl"));
                comptes.setLibNcg(afr.getComptes().get(i).get("Libncg"));
                comptes.setNcg(afr.getComptes().get(i).get("Ncg"));
                comptes.setNumero(afr.getComptes().get(i).get("Compte"));
                retour.getComptes().add(comptes);
            }
            retour.setRacine(afr.getSignaletique().get("Client"));

        }
        }
        
        return retour;
    }

    @WebMethod
    public PaymentResponse payForService(PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = new PaymentResponse();
        requestValidator.init();
        List<Parametres> wsdlPara = parametresFacade.findByCodeParam("OTHER_SERVICE_WSDL");

        Object object = new Object();
        try {
            if (requestValidator.getActivationT24() != null && requestValidator.getActivationT24().getValeur().equals("OUI")) //T24 DESACTIVE
            {
                object = t24Handler.callT24(requestValidator.getPrefixeB2W().getValeur(), requestValidator.getFacturier().getValeur(), sdf.format(Calendar.getInstance()), requestValidator.getCodeOperationFrais_T24().getValeur(), paymentRequest.getAlias(), paymentRequest.getCompte(),
                        requestValidator.getCompteFacturier().getValeur(), String.valueOf(Double.valueOf(paymentRequest.getMontant()).intValue()),
                        String.valueOf(Double.valueOf(paymentRequest.getMontant()).intValue()), "0",
                        "0", "0", "FRAIS SERVICE B2W/W2B " + paymentRequest.getAlias(), "", "");
            } else {
                object = storedProcedureCaller.callFunction("payForService", paymentRequest, requestValidator.getOperateurs().getIdOperateur().toString());
//                System.out.println("PARAMETRES T24 MANQUANTS");

            }

            ABIFunctionResponse afr = storedProcedureCaller.getABIFunctionResponse(object);
            paymentResponse = new PaymentResponse(afr);
        } catch (Exception ex) {
            ex.printStackTrace();
            paymentResponse = new PaymentResponse("1", ex.getMessage(), null);
        } finally {
            System.out.println("PAYMENT RESPONSE: " + paymentResponse.toString());
            return paymentResponse;

        }
    }

}
