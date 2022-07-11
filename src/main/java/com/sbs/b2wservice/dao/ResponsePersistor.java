/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.banktowallet.b2w.b2w.AccountToWalletTransfer;
import com.banktowallet.b2w.b2w.AccountToWalletTransferResponse;
import com.banktowallet.b2w.b2w.CancelTransfer;
import com.banktowallet.b2w.b2w.CancelTransferResponse;
import com.banktowallet.b2w.b2w.GetAccountBalance;
import com.banktowallet.b2w.b2w.GetAccountBalanceResponse;
import com.banktowallet.b2w.b2w.GetMiniStatement;
import com.banktowallet.b2w.b2w.GetMiniStatementResponse;
import com.banktowallet.b2w.b2w.TransferStatusInquiry;
import com.banktowallet.b2w.b2w.TransferStatusInquiryResponse;
import com.banktowallet.b2w.b2w.WalletToAccountTransfer;
import com.banktowallet.b2w.b2w.WalletToAccountTransferResponse;
import com.sbs.b2wservice.dao.TransactionsFacade;
import com.sbs.b2wservice.entities.ABIFunctionResponse;
import com.sbs.b2wservice.entities.Operateurs;
import com.sbs.b2wservice.entities.Transactions;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author alex
 */
@Stateless
public class ResponsePersistor {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @EJB
    private TransactionsFacade transactionsFacade;
    private Transactions transactions;

    public void persist(Object response, Object request, Operateurs op, ABIFunctionResponse afr) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        if (request instanceof AccountToWalletTransfer.MobileTransferRequest && response instanceof AccountToWalletTransferResponse.Return) {
            AccountToWalletTransfer.MobileTransferRequest req = (AccountToWalletTransfer.MobileTransferRequest) request;
            AccountToWalletTransferResponse.Return res = (AccountToWalletTransferResponse.Return) response;
            // calendar.setTimeInMillis(req.getTranDate().toGregorianCalendar().getTime());
            calendar.setTime(new Date());
            transactions = new Transactions((AccountToWalletTransferResponse.Return) res);
            transactions.setTrandate(sdf.format(calendar.getTimeInMillis()));
            transactions.setAmount(String.valueOf((int) req.getAmount()));
            transactions.setAccountalias(req.getAccountAlias());
            transactions.setMobileno(req.getMobileNo());
            transactions.setAccountno(req.getAccountNo());
            transactions.setCommission(BigInteger.valueOf((int) req.getCharge()));
            transactions.setCcy(req.getCcy());
            transactions.setUdf1(afr.getStatutMsg());
        } else if (request instanceof WalletToAccountTransfer.MobileTransferRequest && response instanceof WalletToAccountTransferResponse.Return) {
            WalletToAccountTransfer.MobileTransferRequest req = (WalletToAccountTransfer.MobileTransferRequest) request;
            WalletToAccountTransferResponse.Return res = (WalletToAccountTransferResponse.Return) response;
            //  calendar.setTimeInMillis(req.getTranDate().toGregorianCalendar().getTimeInMillis());
            calendar.setTime(new Date());
            transactions = new Transactions((WalletToAccountTransferResponse.Return) res);
            transactions.setTrandate(sdf.format(calendar.getTimeInMillis()));
            transactions.setAmount(String.valueOf((int) req.getAmount()));
            transactions.setAccountalias(req.getAccountAlias());
            transactions.setMobileno(req.getMobileNo());
            transactions.setAccountno(req.getAccountNo());
            transactions.setCommission(BigInteger.valueOf((int) req.getCharge()));
            transactions.setCcy(req.getCcy());
            transactions.setUdf1(afr.getStatutMsg());
        } else if (response instanceof GetAccountBalanceResponse.Return && request instanceof GetAccountBalance.AccountBalanceInquiryRequest) {
            calendar.setTime(new Date());
            transactions = new Transactions((GetAccountBalanceResponse.Return) response);
            transactions.setTrandate(sdf.format(calendar.getTimeInMillis()));
            transactions.setAccountno(((GetAccountBalance.AccountBalanceInquiryRequest) request).getAccountNo());
        } else if (response instanceof GetMiniStatementResponse.Return && request instanceof GetMiniStatement.MiniStatementRequest) {
            calendar.setTime(new Date());
            GetMiniStatement.MiniStatementRequest req = (GetMiniStatement.MiniStatementRequest) request;
            transactions = new Transactions((GetMiniStatementResponse.Return) response);
            transactions.setAccountalias(req.getAccountAlias());
            transactions.setAccountno(req.getAccountNo());
            transactions.setTrandate(sdf.format(calendar.getTimeInMillis()));
        } else if (response instanceof CancelTransferResponse.Return && request instanceof CancelTransfer.TranRequestInfo) {
            calendar.setTime(new Date());
            transactions = new Transactions((CancelTransferResponse.Return) response, (CancelTransfer.TranRequestInfo) request);
            transactions.setTrandate(sdf.format(calendar.getTimeInMillis()));
        } else if (response instanceof TransferStatusInquiryResponse.Return && request instanceof TransferStatusInquiry.TranRequestInfo) {
            calendar.setTime(new Date());
            transactions = new Transactions((TransferStatusInquiryResponse.Return) response);
            transactions.setTrandate(sdf.format(calendar.getTimeInMillis()));
        }

        transactions.setReconcilie(null);
        transactions.setOperateurs(op);
        transactionsFacade.create(transactions);
    }

    //mise a jour de l'etat de la transaction dont on a pas eu de reponse a cause d'un time Out du SIB 
    public void updateTransaction(TransferStatusInquiryResponse.Return response, TransferStatusInquiry.TranRequestInfo tranRequestInfo, Operateurs operateurs, ABIFunctionResponse afr) {
        Transactions tr = new Transactions();
        //recup l'external ref number 
        String idTransaction = tranRequestInfo.getExternalRefNo();
        System.out.println("le request id de la transaction a mettre a jour est : " + idTransaction);
        //recup de la transaction dans la bd 
        try {
            tr = transactionsFacade.findTransactionById(idTransaction);
            System.out.println("la transaction a ete trouvee    id    " + tr.getIdtransactions());
            if (tr == null) {
                System.out.println("la transaction nas pas ete trouvee ");
            } else {
                System.out.println("la transaction a ete trouvee ");
            }

//            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            //la transaction a ete recuperee , afficher son type 
            System.out.println("le type de la transaction recuperee pour la maj est  " + tr.getRequesttype());
            //faire la mise a jour de la transaction en fonction de son type ...
            if (tr.getRequesttype().equalsIgnoreCase("bank2wallet")) {//si b2w

                System.out.println(" il s'agit d'une transaction de type   " + tr.getRequesttype() + "  a mettre a jour ...");

//                calendar.setTime(new Date());
//
//                // transactions = new Transactions((AccountToWalletTransferResponse.Return) res);
//                tr.setTrandate(sdf.format(calendar.getTimeInMillis()));

                //si le TSI a eu une reponse a succes sur le b2w demandee par le TSI
                if (afr.getStatut().equalsIgnoreCase("0")) {

                    System.out.println("mise a jour a succes de la transaction demandee par le TSI d'id  :    " + tr.getIdtransactions() + " - il s'agit d'une transaction de type   " + tr.getRequesttype() + " le numero Orion de ce b2w est  " + afr.getNoOper());
                    tr.setResponsemessage("Succes");
                    tr.setResponsecode("000");
                    tr.setUdf1("00 OK Traitement en ligne");
                    //tr.setUdf1(afr.getStatutMsg());
                    tr.setCbareferenceno(afr.getNoOper());

                } else {//setter avec des infos d'erreur de transaction 

                    tr.setResponsemessage("ERROR");
                    tr.setResponsecode("50-50");
                    tr.setCbareferenceno(" ");

                }

                //faire le update maintenant 
                transactionsFacade.updateTransaction(tr);

            } else {//si w2b
                //System.out.println("le type de la transaction recuperee pour la maj est  " + tr.getRequesttype() + "   je dois coder maintenant le traitement ...");

                System.out.println(" il s'agit d'une transaction de type   " + tr.getRequesttype() + "  a mettre a jour ...");

//                calendar.setTime(new Date());
//
//                // transactions = new Transactions((AccountToWalletTransferResponse.Return) res);
//                tr.setTrandate(sdf.format(calendar.getTimeInMillis()));

                //si le TSI a eu une reponse a succes sur le b2w demande par le TSI
                if (afr.getStatut().equalsIgnoreCase("0")) {

                    System.out.println("mise a jour a succes de la transaction demandee par le TSI d'id  :    " + tr.getIdtransactions() + " - il s'agit d'une transaction de type   " + tr.getRequesttype() + " le numero Orion de ce b2w est  " + afr.getNoOper());
                    tr.setResponsemessage("Succes");
                    tr.setResponsecode("000");
                    tr.setUdf1("00 OK Traitement en ligne");
                    //tr.setUdf1(afr.getStatutMsg());
                    tr.setCbareferenceno(afr.getNoOper());

                } else {//setter avec des infos d'erreur de transaction 

                    tr.setResponsemessage("ERROR");
                    tr.setResponsecode("50-50");
                    tr.setCbareferenceno(" ");

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("attention la transaction n'existe mm pas dns la table transactions ... la mise a jour de son etat est impossible ...");

        }

    }

}
