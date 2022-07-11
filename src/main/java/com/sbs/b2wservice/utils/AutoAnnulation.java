/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.utils;

import com.banktowallet.b2w.b2w.AccountToWalletTransfer;
import com.banktowallet.b2w.b2w.B2WServices;
import com.banktowallet.b2w.b2w.CancelTransfer;
import com.banktowallet.b2w.b2w.CancelTransferResponse;
import com.banktowallet.b2w.b2w.IB2WService;
import com.banktowallet.b2w.b2w.WalletToAccountTransfer;
import com.banktowallet.b2w.b2w._1.HeaderRequest;
import com.sbs.b2wservice.dao.ParametresFacade;
import com.sbs.b2wservice.dao.RequestValidator;
import com.sbs.b2wservice.entities.Parametres;
import com.xnett.fasyl.deploy.AccountToMobileXResponse;
import com.xnett.fasyl.deploy.FundTransferMobileToAccountXResponse;
import java.net.URL;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mmwservice.BankRequest;
import mmwservice.BankResponse;
import org.apache.commons.lang.RandomStringUtils;

@Stateless
public class AutoAnnulation {

    Parametres url_auto_test = null;

    @EJB
    private ParametresFacade parametresFacade;

    public CancelTransferResponse.Return annulationB2WService(AccountToWalletTransfer.MobileTransferRequest mobileTransferRequest) {
        HeaderRequest headerRequest = new HeaderRequest();
        CancelTransfer.TranRequestInfo cancelTransferRequest = new CancelTransfer.TranRequestInfo();
        cancelTransferRequest.setMmHeaderInfo(headerRequest);
        cancelTransferRequest.getMmHeaderInfo().setOperatorCode(mobileTransferRequest.getMmHeaderInfo().getOperatorCode());
        cancelTransferRequest.getMmHeaderInfo().setAffiliateCode(mobileTransferRequest.getMmHeaderInfo().getAffiliateCode());
        cancelTransferRequest.setExternalRefNo(mobileTransferRequest.getMmHeaderInfo().getRequestId());
        CancelTransferResponse.Return resultat = null;
        int i = 0;
        if (i < 3) {
            try {
                String generatedString = RandomStringUtils.randomAlphanumeric(13).toUpperCase();
                String txtRefIDnew = "AA-" + generatedString;
                cancelTransferRequest.getMmHeaderInfo().setRequestId(txtRefIDnew);
                List<Parametres> list_url_auto = this.parametresFacade.findByCodeParam("URL_AUTO_B2W");
                if (!list_url_auto.isEmpty()) {
                    this.url_auto_test = list_url_auto.get(0);
                }
                B2WServices b2WServices1 = (new IB2WService(new URL(this.url_auto_test.getValeur()))).getB2WServicesPort();
                resultat = b2WServices1.cancelTransfer(cancelTransferRequest);
                return resultat;
            } catch (Exception ex1) {
                ex1.printStackTrace();
                return resultat;
            } finally {
                Exception exception = null;
            }
        }
        return resultat;
    }

    public void testannulation(AccountToWalletTransfer.MobileTransferRequest mobileTransferRequest) {
        System.out.println("---Debut Demande d'Annulation B2W--- ");
        HeaderRequest headerRequest = new HeaderRequest();
        CancelTransfer.TranRequestInfo cancelTransferRequest = new CancelTransfer.TranRequestInfo();
        String generatedString = RandomStringUtils.randomAlphanumeric(13).toUpperCase();
        String txtRefIDnew = "OR" + generatedString;
        cancelTransferRequest.setMmHeaderInfo(headerRequest);
        cancelTransferRequest.getMmHeaderInfo().setOperatorCode(mobileTransferRequest.getMmHeaderInfo().getOperatorCode());
        cancelTransferRequest.getMmHeaderInfo().setRequestId(txtRefIDnew);
        cancelTransferRequest.getMmHeaderInfo().setAffiliateCode(mobileTransferRequest.getMmHeaderInfo().getAffiliateCode());
        cancelTransferRequest.setExternalRefNo(mobileTransferRequest.getMmHeaderInfo().getOperatorCode());
        try {
            List<Parametres> list_url_auto = this.parametresFacade.findByCodeParam("URL_AUTO_B2W");
            if (!list_url_auto.isEmpty()) {
                this.url_auto_test = list_url_auto.get(0);
            }
            B2WServices b2WServices1 = (new IB2WService(new URL(this.url_auto_test.getValeur()))).getB2WServicesPort();
            CancelTransferResponse.Return resultat = b2WServices1.cancelTransfer(cancelTransferRequest);
            if (resultat.getMmHeaderInfo().getResponseCode() != null && resultat.getMmHeaderInfo().getResponseCode().equals("000")) {
                System.out.println("Annulation effectuavec succes B2W " + txtRefIDnew);
            } else {
                System.out.println("Echec Annulation W2B " + mobileTransferRequest.getMmHeaderInfo().getRequestId());
            }
        } catch (Exception ex1) {
            ex1.printStackTrace();
            System.out.println("Exception lors de la demande d'annulation B2W");
        }
        System.out.println("---Fin de la demande d'Annulation B2W--- ");
    }

    public CancelTransferResponse.Return annulationW2BService(WalletToAccountTransfer.MobileTransferRequest mobileTransferRequest) {
        HeaderRequest headerRequest = new HeaderRequest();
        CancelTransfer.TranRequestInfo cancelTransferRequest = new CancelTransfer.TranRequestInfo();
        cancelTransferRequest.setMmHeaderInfo(headerRequest);
        cancelTransferRequest.getMmHeaderInfo().setOperatorCode(mobileTransferRequest.getMmHeaderInfo().getOperatorCode());
        cancelTransferRequest.getMmHeaderInfo().setAffiliateCode(mobileTransferRequest.getMmHeaderInfo().getAffiliateCode());
        cancelTransferRequest.setExternalRefNo(mobileTransferRequest.getMmHeaderInfo().getRequestId());
        CancelTransferResponse.Return resultat = null;
        int i = 0;
        if (i < 3) {
            try {
                String generatedString = RandomStringUtils.randomAlphanumeric(13).toUpperCase();
                String txtRefIDnew = "AA-" + generatedString;
                cancelTransferRequest.getMmHeaderInfo().setRequestId(txtRefIDnew);
                List<Parametres> list_url_auto = this.parametresFacade.findByCodeParam("URL_AUTO_B2W");
                if (!list_url_auto.isEmpty()) {
                    this.url_auto_test = list_url_auto.get(0);
                }
                B2WServices b2WServices1 = (new IB2WService(new URL(this.url_auto_test.getValeur()))).getB2WServicesPort();
                resultat = b2WServices1.cancelTransfer(cancelTransferRequest);
                return resultat;
            } catch (Exception ex1) {
                ex1.printStackTrace();
                return resultat;
            } finally {
                Exception exception = null;
            }
        }
        return resultat;
    }

    public AccountToMobileXResponse annulationB2WAirtelBK(AccountToMobileXResponse accountToMobileXResponse, Parametres url_auto, AccountToWalletTransfer.MobileTransferRequest mobileTransferRequest) {
        System.out.println("Exception demande d'annulation B2W " + mobileTransferRequest.getMmHeaderInfo().getRequestId());
        accountToMobileXResponse.setStatus(1);
        accountToMobileXResponse.setTransactionAmount("" + mobileTransferRequest.getAmount());
        accountToMobileXResponse.setTransactionID(mobileTransferRequest.getMmHeaderInfo().getRequestId());
        accountToMobileXResponse.setTransactionDetails("INTERFACE ERROR");
        HeaderRequest headerRequest = new HeaderRequest();
        CancelTransfer.TranRequestInfo cancelTransferRequest = new CancelTransfer.TranRequestInfo();
        cancelTransferRequest.setMmHeaderInfo(headerRequest);
        cancelTransferRequest.getMmHeaderInfo().setOperatorCode(mobileTransferRequest.getMmHeaderInfo().getOperatorCode());
        cancelTransferRequest.getMmHeaderInfo().setAffiliateCode(mobileTransferRequest.getMmHeaderInfo().getAffiliateCode());
        cancelTransferRequest.setExternalRefNo(mobileTransferRequest.getMmHeaderInfo().getRequestId());
        int i = 0;
        if (i < 3) {
            try {
                String generatedString = RandomStringUtils.randomAlphanumeric(13).toUpperCase();
                String txtRefIDnew = "AA" + generatedString;
                cancelTransferRequest.getMmHeaderInfo().setRequestId(txtRefIDnew);
                B2WServices b2WServices1 = (new IB2WService(new URL(url_auto.getValeur()))).getB2WServicesPort();
                CancelTransferResponse.Return resultat = b2WServices1.cancelTransfer(cancelTransferRequest);
                return accountToMobileXResponse;
            } catch (Exception ex1) {
                ex1.printStackTrace();
                return accountToMobileXResponse;
            } finally {
                Exception exception = null;
            }
        }
        return accountToMobileXResponse;
    }

    public FundTransferMobileToAccountXResponse annulationW2BAirtelBK(FundTransferMobileToAccountXResponse accountToMobileXResponse, Parametres url_auto, WalletToAccountTransfer.MobileTransferRequest mobileTransferRequest) {
        System.out.println("Exception demande d'annulation B2W " + mobileTransferRequest.getMmHeaderInfo().getRequestId());
        accountToMobileXResponse.setStatus(1);
        accountToMobileXResponse.setTransactionAmount("" + mobileTransferRequest.getAmount());
        accountToMobileXResponse.setTransactionID(mobileTransferRequest.getMmHeaderInfo().getRequestId());
        accountToMobileXResponse.setTransactionDetails("INTERFACE ERROR");
        HeaderRequest headerRequest = new HeaderRequest();
        CancelTransfer.TranRequestInfo cancelTransferRequest = new CancelTransfer.TranRequestInfo();
        cancelTransferRequest.setMmHeaderInfo(headerRequest);
        cancelTransferRequest.getMmHeaderInfo().setOperatorCode(mobileTransferRequest.getMmHeaderInfo().getOperatorCode());
        cancelTransferRequest.getMmHeaderInfo().setAffiliateCode(mobileTransferRequest.getMmHeaderInfo().getAffiliateCode());
        cancelTransferRequest.setExternalRefNo(mobileTransferRequest.getMmHeaderInfo().getRequestId());
        int i = 0;
        if (i < 3) {
            try {
                String generatedString = RandomStringUtils.randomAlphanumeric(13).toUpperCase();
                String txtRefIDnew = "AA-" + generatedString;
                cancelTransferRequest.getMmHeaderInfo().setRequestId(txtRefIDnew);
                B2WServices b2WServices1 = (new IB2WService(new URL(url_auto.getValeur()))).getB2WServicesPort();
                CancelTransferResponse.Return resultat = b2WServices1.cancelTransfer(cancelTransferRequest);
                return accountToMobileXResponse;
            } catch (Exception ex1) {
                ex1.printStackTrace();
                return accountToMobileXResponse;
            } finally {
                Exception exception = null;
            }
        }
        return accountToMobileXResponse;
    }

    public BankResponse annulationMooBN(BankResponse bankResponse, RequestValidator requestValidator, Parametres url_auto, BankRequest request) {
        bankResponse.setStatus(95);
        bankResponse.setTransamount(request.getTransamount());
        bankResponse.setTransid(request.getBanktransactionreferenceid());
        bankResponse.setMessage("INTERFACE ERROR");
        CancelTransfer.TranRequestInfo cancelTransferRequest = new CancelTransfer.TranRequestInfo();
        HeaderRequest headerRequest = new HeaderRequest();
        cancelTransferRequest.setMmHeaderInfo(headerRequest);
        cancelTransferRequest.getMmHeaderInfo().setOperatorCode(requestValidator.getOperatorCode().getValeur());
        cancelTransferRequest.getMmHeaderInfo().setAffiliateCode(requestValidator.getAffiliateCode().getValeur());
        cancelTransferRequest.setExternalRefNo(request.getBanktransactionreferenceid());
        int i = 0;
        if (i < 3) {
            try {
                String generatedString = RandomStringUtils.randomAlphanumeric(13).toUpperCase();
                String txtRefIDnew = "AA-" + generatedString;
                cancelTransferRequest.getMmHeaderInfo().setRequestId(txtRefIDnew);
                B2WServices b2WServices = (new IB2WService(new URL(url_auto.getValeur()))).getB2WServicesPort();
                CancelTransferResponse.Return resultat = b2WServices.cancelTransfer(cancelTransferRequest);
                return bankResponse;
            } catch (Exception ex1) {
                return bankResponse;
            } finally {
                Exception exception = null;
            }
        }
        return bankResponse;
    }
}
