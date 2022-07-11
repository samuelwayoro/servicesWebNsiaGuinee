/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import javax.ejb.EJB;
import com.banktowallet.b2w.b2w._1.HeaderResponse;
import com.banktowallet.b2w.b2w.AccountToWalletTransfer;
import com.banktowallet.b2w.b2w.GetAccountBalance;
import com.banktowallet.b2w.b2w.GetMiniStatement;
import com.banktowallet.b2w.b2w.WalletToAccountTransfer;
import com.banktowallet.b2w.b2w._1.HeaderRequest;
import com.sbs.b2wservice.entities.ABIFunctionResponse;
import com.sbs.b2wservice.entities.Abonnements;
import com.sbs.b2wservice.entities.Codes;
import com.sbs.b2wservice.entities.Limitestransactions;
import com.sbs.b2wservice.entities.Operateurs;
import com.sbs.b2wservice.entities.Parametres;
import com.sbs.b2wservice.utils.Config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author alex
 */
@Stateless
public class RequestValidator {

    @EJB
    private CodesFacade codesFacade;
    @EJB
    private ParametresFacade parametresFacade;
    @EJB
    private AbonnementsFacade abonnementsFacade;
    @EJB
    private TransactionsFacade transactionsFacade;
    @EJB
    private OperateursFacade operateursFacade;
    @EJB
    LimitestransactionsFacade limitesFacade;

    private static final String[] codePattern = {"E", "0"};
    private static final String[] paraPattern = {"EOD",
        "CASH_IN",
        "CASH_OUT",
        "CASH_IN-CASH_OUT",
        "MODEIDLE",
        "IDLE_NON",
        "IDLE_OUI",
        "ACTIVATION_T24",
        "PREFIXE_B2W",
        "PREFIXE_W2B",
        "PREFIXE_STATEMENT",
        "PREFIXE_ACCOUNTBALANCE",
        "PREFIXE_CANCEL",
        "PREFIXE_TSI",
        "IDLE_NON",
        "LIBELLE_DEVISE",
        "OTHER_SERVICE_WSDL",
        "ACTIVATION_DELTA",
        "CODE_OPER_B2W",
        "CODE_OPER_W2B",
        "CODE_OPER_FRAIS",
        "NOMBRE_MAX_TRANSACTION",
        "MONTANT_MAX_TRANSACTION",
        "CUMUL_MAX"};
    private List<Codes> listCodes;
    private List<Parametres> listPara;
    private List<Limitestransactions> listLimitesTransactions;
    private Parametres token;
    private Parametres eodStatus;
    private Parametres cashIn;
    private Parametres cashOut;
    private Parametres cash;
    private Parametres idleEnabled;
    private Parametres idleDisabled;
    private Parametres modeIdle;
    private Parametres operatorCode;
    private Parametres affiliateCode;
    private Parametres autorisationBalance;
    private Parametres autorisationB2W;
    private Parametres autorisationW2B;
    private Parametres autorisationMiniStatement;
    private Parametres modeTarification;
    private Parametres tarificationMiniStement;
    private Parametres tarificationBalance;
    private Parametres tarificationB2W;
    private Parametres tarificationW2B;
    private Parametres codeOperationB2W_T24;
    private Parametres codeOperationW2B_T24;
    private Parametres codeOperationFrais_T24;
    private Codes interfaceError;
    private Codes erreurToken;
    private Codes erreurEod;
    private Codes erreurInvalidNoCompte;
    private Codes erreurAccountNotRegistered;
    private Codes erreurExternalRefAlreadyExists;
    private Codes erreurCBA;
    private Codes erreurInternalCompteNumber;
    private Codes erreurMontantMaxJournalier;
    private Codes erreurCumulTransactionsJournaliers;
    private Codes erreurNbreTrsxJournaliers;
    private Codes erreurTransactionNotAllowed;
    private Codes codeSucces;
    private Codes codeSuccesValidation;
    private Codes codeInvalidRequest;
    private Codes codeStatut;
    private Codes erreurDoublonId;
    private Codes erreurInsuficientBalance;
    private Codes erreurTimeOut;
    private Codes erreurNoData;
    private Codes insufficientBalance;
    private Codes plafonMoisB2w;
    private Codes nbresMoisW2b;
    private Codes nbresJoursW2b;
    private Codes plafonMoisW2b;
    private Codes nbresMoisB2w;
    private Codes nbresJoursB2w;
    private Codes limiteTrxJoursW2b;
    private Codes limiteTrxJoursB2w;
    private Abonnements abonnements;
    private Operateurs operateurs;
    private Parametres facturier;
    private Parametres compteFacturier;
    private Parametres activationT24;
    private Parametres prefixeB2W;
    private Parametres prefixeW2B;
    private Parametres prefixeBalance;
    private Parametres prefixeStatement;
    private Parametres prefixeCancel;
    private Parametres prefixeTSI;
    private Parametres libelleDevise;
    private Parametres activationDelta;
    private Parametres otherService;
    private Parametres nombreMaxTransaction;
    private Parametres montantMaxTransaction;
    private Parametres montantCumulMaxTransaction;
    private String scriptCompta;

    public void init() {

        //recuperation de la liste des limites de transactions depuis la table limitestransactions
        listLimitesTransactions = new ArrayList<>();
        listLimitesTransactions = limitesFacade.findAll();
        //parcours de la liste des limites 
        System.out.println("liste des limites de transactions parametree dans la base ");
        for (Limitestransactions lt : listLimitesTransactions) {
            System.out.println("-" + lt.getDesignation() + " " + lt.getValeur() + " " + lt.getProfilclient() + " " + lt.getProfilclient() + " " + lt.getTypeTransaction() + " " + lt.getKeyColumn() + " " + lt.getTypelimite());
        }

        List<String> lPara = new ArrayList<>();
        Config config = new Config();
        scriptCompta = config.getScriptCompta(); //chargement des donnees du fichier config.properties
        lPara.addAll(Arrays.asList(paraPattern));
        lPara.addAll(Arrays.asList(config.getListParam()));//ajout des libelles parametres se trouvant dns le fichier config ...

        // parcourir lPara pour logging
        System.out.println("parcours de la liste des donnees recuperees depuis le fichier config et du tableau de liste des parametres ");
        for (int i = 0; i < lPara.size(); i++) {
            System.out.println(i + "  ------------->" + lPara.get(i));
        }

        listCodes = codesFacade.findCodesByPatten(codePattern);
        listPara = parametresFacade.findByListCodeparam(lPara);
        List<Operateurs> listOp = operateursFacade.findByDesignation(config.getDesigantionOperateur());

        if (!listOp.isEmpty()) {
            operateurs = listOp.get(0);
        }

        for (Parametres p : listPara) {
            if (p.getCodeparam().startsWith("TOKEN") || p.getCodeparam().equals("BANKID")) {
                token = p;
                System.out.println("token ->  " + "codeparam->" + token.getCodeparam() + "   valeur: " + token.getValeur());
            } else if (p.getCodeparam().startsWith("OPERATOR_CODE") || p.getCodeparam().equals("BANKNAME_AIRTEL") || p.getCodeparam().equals("USERNAME_MOOV")) {
                operatorCode = p;
                System.out.println("operatorCode ->  " + "codeparam->" + operatorCode.getCodeparam() + "   valeur: " + operatorCode.getValeur());
            } else if (p.getCodeparam().startsWith("AFFILIATE_CODE") || p.getCodeparam().equals("BRANCHCODE") || p.getCodeparam().equals("CLE_MOOV")) {
                affiliateCode = p;
                System.out.println("affiliateCode ->  " + "codeparam->" + affiliateCode.getCodeparam() + "   valeur: " + affiliateCode.getValeur());
            } else if (p.getCodeparam().startsWith("AUTORISATION_BALANCE")) {
                autorisationBalance = p;
                System.out.println("autorisationBalance ->  " + "codeparam->" + autorisationBalance.getCodeparam() + "   valeur: " + autorisationBalance.getValeur());
            } else if (p.getCodeparam().startsWith("AUTORISATION_MINISTATEMENT")) {
                autorisationMiniStatement = p;
                //System.out.println("autorisationMinistatement ->  " + "codeparam->" + autorisationMiniStatement.getCodeparam() + "   valeur: " + autorisationMiniStatement.getValeur());
            } else if (p.getCodeparam().startsWith("AUTORISATION_B2W")) {
                autorisationB2W = p;
                //System.out.println("autorisationB2W ->  " + "codeparam->" + autorisationB2W.getCodeparam() + "   valeur: " + autorisationB2W.getValeur());
            } else if (p.getCodeparam().startsWith("AUTORISATION_W2B")) {
                autorisationW2B = p;
                //System.out.println("autorisationW2B ->  " + "codeparam->" + autorisationW2B.getCodeparam() + "   valeur: " + autorisationW2B.getValeur());
            } else if (p.getCodeparam().startsWith("TARIFICATION_ACCOUNTBALANCE")) {
                tarificationBalance = p;
                //System.out.println("tarificationBalance ->  " + "codeparam->" + tarificationBalance.getCodeparam() + "   valeur: " + tarificationBalance.getValeur());
            } else if (p.getCodeparam().startsWith("TARIFICATION_MINISTATEMENT")) {
                tarificationMiniStement = p;
                //System.out.println("tarificationMiniStement ->  " + "codeparam->" + tarificationMiniStement.getCodeparam() + "   valeur: " + tarificationMiniStement.getValeur());
            } else if (p.getCodeparam().startsWith("TARIFICATION_B2W")) {
                tarificationB2W = p;
                //System.out.println("tarificationB2W ->  " + "codeparam->" + tarificationB2W.getCodeparam() + "   valeur: " + tarificationB2W.getValeur());
            } else if (p.getCodeparam().startsWith("TARIFICATION_W2B")) {
                tarificationW2B = p;
                //System.out.println("tarificationW2B ->  " + "codeparam->" + tarificationW2B.getCodeparam() + "   valeur: " + tarificationW2B.getValeur());
            } else if (p.getCodeparam().startsWith("TARIFICATION")) {
                modeTarification = p;
                //System.out.println("modeTarification ->  " + "codeparam->" + modeTarification.getCodeparam() + "   valeur: " + modeTarification.getValeur());
            } else if (p.getCodeparam().startsWith("FACTURIER")) {
                facturier = p;
                //System.out.println("facturier ->  " + "codeparam->" + facturier.getCodeparam() + "   valeur: " + facturier.getValeur());
            } else if (p.getCodeparam().startsWith("COMPTE_FACTURIER")) {
                compteFacturier = p;
                //System.out.println("compteFacturier ->  " + "codeparam->" + compteFacturier.getCodeparam() + "   valeur: " + compteFacturier.getValeur());
            } else if (p.getCodeparam().equals("IDLE_NON")) {
                idleDisabled = p;
                //System.out.println("idleDisabled ->  " + "codeparam->" + idleDisabled.getCodeparam() + "   valeur: " + idleDisabled.getValeur());
            } else if (p.getCodeparam().equals("IDLE_OUI")) {
                idleEnabled = p;
                //System.out.println("idleEnabled ->  " + "codeparam->" + idleDisabled.getCodeparam() + "   valeur: " + idleEnabled.getValeur());
            } else if (p.getCodeparam().equals("CASH_IN")) {
                cashIn = p;
                //System.out.println("cashIn ->  " + "codeparam->" + cashIn.getCodeparam() + "   valeur: " + cashIn.getValeur());
            } else if (p.getCodeparam().equals("CASH_OUT")) {
                cashOut = p;
                //System.out.println("cashOut ->  " + "codeparam->" + cashOut.getCodeparam() + "   valeur: " + cashOut.getValeur());
            } else if (p.getCodeparam().equals("CASH_IN-CASH_OUT")) {
                cash = p;
                //System.out.println("cash ->  " + "codeparam->" + cash.getCodeparam() + "   valeur: " + cash.getValeur());
            } else if (p.getCodeparam().equals("MODEIDLE")) {
                modeIdle = p;
                //System.out.println("modeIdle ->  " + "codeparam->" + modeIdle.getCodeparam() + "   valeur: " + modeIdle.getValeur());
            } else if (p.getCodeparam().equals("EOD")) {
                eodStatus = p;
                //System.out.println("eodStatus ->  " + "codeparam->" + eodStatus.getCodeparam() + "   valeur: " + eodStatus.getValeur());
            } else if (p.getCodeparam().equals("ACTIVATION_T24")) {
                activationT24 = p;
                //System.out.println("activationT24 ->  " + "codeparam->" + activationT24.getCodeparam() + "   valeur: " + activationT24.getValeur());
            } else if (p.getCodeparam().equals("ACTIVATION_DELTA")) {
                activationDelta = p;
                //System.out.println("activationDelta ->  " + "codeparam->" + activationDelta.getCodeparam() + "   valeur: " + activationDelta.getValeur());
            } else if (p.getCodeparam().equals("PREFIXE_B2W")) {
                prefixeB2W = p;
                //System.out.println("prefixeB2W ->  " + "codeparam->" + prefixeB2W.getCodeparam() + "   valeur: " + prefixeB2W.getValeur());
            } else if (p.getCodeparam().equals("PREFIXE_W2B")) {
                prefixeW2B = p;
                //System.out.println("prefixeW2B ->  " + "codeparam->" + prefixeW2B.getCodeparam() + "   valeur: " + prefixeW2B.getValeur());
            } else if (p.getCodeparam().equals("PREFIXE_STATEMENT")) {
                prefixeStatement = p;
                // System.out.println("prefixeStatement ->  " + "codeparam->" + prefixeStatement.getCodeparam() + "   valeur: " + prefixeStatement.getValeur());
            } else if (p.getCodeparam().equals("PREFIXE_ACCOUNTBALANCE")) {
                prefixeBalance = p;
                //System.out.println("prefixeBalance ->  " + "codeparam->" + prefixeBalance.getCodeparam() + "   valeur: " + prefixeBalance.getValeur());
            } else if (p.getCodeparam().equals("PREFIXE_CANCEL")) {
                prefixeCancel = p;
                //System.out.println("prefixeCancel ->  " + "codeparam->" + prefixeCancel.getCodeparam() + "   valeur: " + prefixeCancel.getValeur());
            } else if (p.getCodeparam().equals("PREFIXE_TSI")) {
                prefixeTSI = p;
                //System.out.println("prefixeTSI ->  " + "codeparam->" + prefixeTSI.getCodeparam() + "   valeur: " + prefixeTSI.getValeur());
            } else if (p.getCodeparam().equals("LIBELLE_DEVISE")) {
                libelleDevise = p;
                //System.out.println("libelleDevise ->  " + "codeparam->" + libelleDevise.getCodeparam() + "   valeur: " + libelleDevise.getValeur());
            } else if (p.getCodeparam().equals("OTHER_SERVICE_WSDL")) {
                otherService = p;
                //System.out.println("otherService ->  " + "codeparam->" + otherService.getCodeparam() + "   valeur: " + otherService.getValeur());
            } else if (p.getCodeparam().equals("CODE_OPER_B2W")) {
                codeOperationB2W_T24 = p;
                //System.out.println("CODE_OPER_B2W ->  " + "codeparam->" + codeOperationB2W_T24.getCodeparam() + "   valeur: " + codeOperationB2W_T24.getValeur());
            } else if (p.getCodeparam().equals("CODE_OPER_W2B")) {
                codeOperationW2B_T24 = p;
                //System.out.println("codeOperationW2B_T24 ->  " + "codeparam->" + codeOperationW2B_T24.getCodeparam() + "   valeur: " + codeOperationW2B_T24.getValeur());
            } else if (p.getCodeparam().equals("CODE_OPER_FRAIS")) {
                codeOperationFrais_T24 = p;
                // System.out.println("codeOperationFrais_T24 ->  " + "codeparam->" + codeOperationFrais_T24.getCodeparam() + "   valeur: " + codeOperationFrais_T24.getValeur());
            } else if (p.getCodeparam().equals("NOMBRE_MAX_TRANSACTION")) {
                nombreMaxTransaction = p;
                //System.out.println("nombreMaxTransaction ->  " + "codeparam->" + nombreMaxTransaction.getCodeparam() + "   valeur: " + nombreMaxTransaction.getValeur());
            } else if (p.getCodeparam().equals("MONTANT_MAX_TRANSACTION")) {
                montantMaxTransaction = p;
                //System.out.println("montantMaxTransaction ->  " + "codeparam->" + montantMaxTransaction.getCodeparam() + "   valeur: " + montantMaxTransaction.getValeur());
            } else if (p.getCodeparam().equals("CUMUL_MAX")) {
                montantCumulMaxTransaction = p;
                System.out.println("montantCumulMaxTransaction ->  " + "codeparam->" + montantCumulMaxTransaction.getCodeparam() + "   valeur: " + montantCumulMaxTransaction.getValeur());
            }
        }
        System.out.println("logging sur les listes de code en base  --- qte trouvee en bd" + listCodes.size() + "  qte  ");
        for (Codes c : listCodes) {
            switch (c.getCodevalue()) {
                case "200"://
                    codeSuccesValidation = c;
                    break;
                case "302"://
                    codeInvalidRequest = c;
                    break;
                case "000"://
                    codeSucces = c;
                    break;
                case "010"://
                    erreurEod = c;
                    break;
                case "E01"://
                    erreurInternalCompteNumber = c;
                    break;
                case "E02"://
                    erreurInvalidNoCompte = c;
                    break;
                case "E03"://
                    erreurAccountNotRegistered = c;
                    break;
                case "E04"://
                    interfaceError = c;
                    break;
                case "E05"://
                    erreurExternalRefAlreadyExists = c;
                    break;
                case "E13"://
                    erreurNbreTrsxJournaliers = c;
                    break;
                case "E14"://
                    erreurCumulTransactionsJournaliers = c;
                    break;
                case "E07":
                    erreurCBA = c;
                    break;
                case "E06":
                    erreurTransactionNotAllowed = c;
                    break;
                case "E08":
                    erreurToken = c;
                    break;
                case "E22":
                    erreurMontantMaxJournalier = c;
                    break;
                case "E10":
                    erreurNoData = c;
                    break;
                case "E11":
                    erreurTimeOut = c;
                case "E12":
                    erreurDoublonId = c;
                    break;
                case "E16":
                    erreurInsuficientBalance = c;
                    break;
                case "E09":
                    plafonMoisB2w = c;
                    break;
                case "E17":
                    nbresMoisW2b = c;
                    break;
                case "E18":
                    nbresJoursW2b = c;
                    break;
                case "E19":
                    limiteTrxJoursW2b = c;
                    break;
                case "E20":
                    plafonMoisW2b = c;
                    break;
                case "E21":
                    nbresJoursB2w = c;
                    break;
                case "E35":
                    nbresJoursB2w = c;
                    break;
                case "E99":
                    limiteTrxJoursB2w = c;
                    break;
                default:
                    break;
            }
        }
    }

    public LimitestransactionsFacade getLimitesFacade() {
        return limitesFacade;
    }

    public void setLimitesFacade(LimitestransactionsFacade limitesFacade) {
        this.limitesFacade = limitesFacade;
    }

    public Codes getPlafonMoisB2w() {
        return plafonMoisB2w;
    }

    public void setPlafonMoisB2w(Codes plafonMoisB2w) {
        this.plafonMoisB2w = plafonMoisB2w;
    }

    public Codes getNbresMoisW2b() {
        return nbresMoisW2b;
    }

    public void setNbresMoisW2b(Codes nbresMoisW2b) {
        this.nbresMoisW2b = nbresMoisW2b;
    }

    public Codes getNbresJoursW2b() {
        return nbresJoursW2b;
    }

    public void setNbresJoursW2b(Codes nbresJoursW2b) {
        this.nbresJoursW2b = nbresJoursW2b;
    }

    public Codes getPlafonMoisW2b() {
        return plafonMoisW2b;
    }

    public void setPlafonMoisW2b(Codes plafonMoisW2b) {
        this.plafonMoisW2b = plafonMoisW2b;
    }

    public Codes getNbresMoisB2w() {
        return nbresMoisB2w;
    }

    public void setNbresMoisB2w(Codes nbresMoisB2w) {
        this.nbresMoisB2w = nbresMoisB2w;
    }

    public Codes getNbresJoursB2w() {
        return nbresJoursB2w;
    }

    public void setNbresJoursB2w(Codes nbresJoursB2w) {
        this.nbresJoursB2w = nbresJoursB2w;
    }

    public Codes getLimiteTrxJoursW2b() {
        return limiteTrxJoursW2b;
    }

    public void setLimiteTrxJoursW2b(Codes limiteTrxJoursW2b) {
        this.limiteTrxJoursW2b = limiteTrxJoursW2b;
    }

    public Codes getLimiteTrxJoursB2w() {
        return limiteTrxJoursB2w;
    }

    public void setLimiteTrxJoursB2w(Codes limiteTrxJoursB2w) {
        this.limiteTrxJoursB2w = limiteTrxJoursB2w;
    }

    public Codes getErreurNbreTrsxJournaliers() {
        return erreurNbreTrsxJournaliers;
    }

    public void setErreurNbreTrsxJournaliers(Codes erreurNbreTrsxJournaliers) {
        this.erreurNbreTrsxJournaliers = erreurNbreTrsxJournaliers;
    }

    public Codes getErreurInsuficientBalance() {
        return erreurInsuficientBalance;
    }

    public void setErreurInsuficientBalance(Codes erreurInsuficientBalance) {
        this.erreurInsuficientBalance = erreurInsuficientBalance;
    }

    public Codes getErreurAccountNotRegistered() {
        return erreurAccountNotRegistered;
    }

    public void setErreurAccountNotRegistered(Codes erreurAccountNotRegistered) {
        this.erreurAccountNotRegistered = erreurAccountNotRegistered;
    }

    public Codes getErreurExternalRefAlreadyExists() {
        return erreurExternalRefAlreadyExists;
    }

    public void setErreurExternalRefAlreadyExists(Codes erreurExternalRefAlreadyExists) {
        this.erreurExternalRefAlreadyExists = erreurExternalRefAlreadyExists;
    }

    public Codes getErreurInternalCompteNumber() {
        return erreurInternalCompteNumber;
    }

    public void setErreurInternalCompteNumber(Codes erreurInternalCompteNumber) {
        this.erreurInternalCompteNumber = erreurInternalCompteNumber;
    }

    public Codes getCodeSuccesValidation() {
        return codeSuccesValidation;
    }

    public void setCodeSuccesValidation(Codes codeSuccesValidation) {
        this.codeSuccesValidation = codeSuccesValidation;
    }

    public Codes getCodeInvalidRequest() {
        return codeInvalidRequest;
    }

    public void setCodeInvalidRequest(Codes codeInvalidRequest) {
        this.codeInvalidRequest = codeInvalidRequest;
    }

    public Codes getErreurMontantMaxJournalier() {
        return erreurMontantMaxJournalier;
    }

    public void setErreurMontantMaxJournalier(Codes erreurMontantMaxJournalier) {
        this.erreurMontantMaxJournalier = erreurMontantMaxJournalier;
    }

    public Codes getErreurCumulTransactionsJournaliers() {
        return erreurCumulTransactionsJournaliers;
    }

    public void setErreurCumulTransactionsJournaliers(Codes erreurCumulTransactionsJournaliers) {
        this.erreurCumulTransactionsJournaliers = erreurCumulTransactionsJournaliers;
    }

    public Codes getErreurNoData() {
        return erreurNoData;
    }

    public void setErreurNoData(Codes erreurNoData) {
        this.erreurNoData = erreurNoData;
    }

    public Codes getErreurmontantMaxJournalier() {
        return erreurMontantMaxJournalier;
    }

    public void setErreurmontantMaxJournalier(Codes erreurMontantMaxJournalier) {
        this.erreurMontantMaxJournalier = erreurMontantMaxJournalier;
    }

    public CodesFacade getCodesFacade() {
        return codesFacade;
    }

    public void setCodesFacade(CodesFacade codesFacade) {
        this.codesFacade = codesFacade;
    }

    public ParametresFacade getParametresFacade() {
        return parametresFacade;
    }

    public void setParametresFacade(ParametresFacade parametresFacade) {
        this.parametresFacade = parametresFacade;
    }

    public TransactionsFacade getTransactionsFacade() {
        return transactionsFacade;
    }

    public void setTransactionsFacade(TransactionsFacade transactionsFacade) {
        this.transactionsFacade = transactionsFacade;
    }

    public OperateursFacade getOperateursFacade() {
        return operateursFacade;
    }

    public void setOperateursFacade(OperateursFacade operateursFacade) {
        this.operateursFacade = operateursFacade;
    }

    public Parametres getIdleEnabled() {
        return idleEnabled;
    }

    public void setIdleEnabled(Parametres idleEnabled) {
        this.idleEnabled = idleEnabled;
    }

    public Parametres getIdleDisabled() {
        return idleDisabled;
    }

    public void setIdleDisabled(Parametres idleDisabled) {
        this.idleDisabled = idleDisabled;
    }

    public Parametres getModeIdle() {
        return modeIdle;
    }

    public void setModeIdle(Parametres modeIdle) {
        this.modeIdle = modeIdle;
    }

    public Parametres getAutorisationBalance() {
        return autorisationBalance;
    }

    public void setAutorisationBalance(Parametres autorisationBalance) {
        this.autorisationBalance = autorisationBalance;
    }

    public Parametres getAutorisationB2W() {
        return autorisationB2W;
    }

    public void setAutorisationB2W(Parametres autorisationB2W) {
        this.autorisationB2W = autorisationB2W;
    }

    public Parametres getAutorisationW2B() {
        return autorisationW2B;
    }

    public void setAutorisationW2B(Parametres autorisationW2B) {
        this.autorisationW2B = autorisationW2B;
    }

    public Parametres getAutorisationMiniStatement() {
        return autorisationMiniStatement;
    }

    public void setAutorisationMiniStatement(Parametres autorisationMiniStatement) {
        this.autorisationMiniStatement = autorisationMiniStatement;
    }

    public Codes getInterfaceError() {
        return interfaceError;
    }

    public void setInterfaceError(Codes interfaceError) {
        this.interfaceError = interfaceError;
    }

    public Codes getErreurTransactionNotAllowed() {
        return erreurTransactionNotAllowed;
    }

    public void setErreurTransactionNotAllowed(Codes erreurTransactionNotAllowed) {
        this.erreurTransactionNotAllowed = erreurTransactionNotAllowed;
    }

    public Codes getErreurTimeOut() {
        return erreurTimeOut;
    }

    public void setErreurTimeOut(Codes erreurTimeOut) {
        this.erreurTimeOut = erreurTimeOut;
    }

    public Codes getInsufficientBalance() {
        return insufficientBalance;
    }

    public void setInsufficientBalance(Codes insufficientBalance) {
        this.insufficientBalance = insufficientBalance;
    }

    public Abonnements getAbonnements() {
        return abonnements;
    }

    public void setAbonnements(Abonnements abonnements) {
        this.abonnements = abonnements;
    }

    public List<Codes> getListCodes() {
        return listCodes;
    }

    public void setListCodes(List<Codes> listCodes) {
        this.listCodes = listCodes;
    }

    public List<Parametres> getListPara() {
        return listPara;
    }

    public void setListPara(List<Parametres> listPara) {
        this.listPara = listPara;
    }

    public Parametres getToken() {
        return token;
    }

    public void setToken(Parametres token) {
        this.token = token;
    }

    public Parametres getEodStatus() {
        return eodStatus;
    }

    public void setEodStatus(Parametres eodStatus) {
        this.eodStatus = eodStatus;
    }

    public Codes getErreurToken() {
        return erreurToken;
    }

    public void setErreurToken(Codes erreurToken) {
        this.erreurToken = erreurToken;
    }

    public Codes getErreurEod() {
        return erreurEod;
    }

    public void setErreurEod(Codes erreurEod) {
        this.erreurEod = erreurEod;
    }

    public Codes getErreurInvalidNoCompte() {
        return erreurInvalidNoCompte;
    }

    public void setErreurInvalidNoCompte(Codes erreurInvalidNoCompte) {
        this.erreurInvalidNoCompte = erreurInvalidNoCompte;
    }

    public Codes getErreurCBA() {
        return erreurCBA;
    }

    public void setErreurCBA(Codes erreurCBA) {
        this.erreurCBA = erreurCBA;
    }

    public Codes getCodeSucces() {
        return codeSucces;
    }

    public Codes getCodeStatut() {
        return codeStatut;
    }

    public void setCodeStatut(Codes codeStatut) {
        this.codeStatut = codeStatut;
    }

    public void setCodeSucces(Codes codeSucces) {
        this.codeSucces = codeSucces;
    }

    public AbonnementsFacade getAbonnementsFacade() {
        return abonnementsFacade;
    }

    public void setAbonnementsFacade(AbonnementsFacade abonnementsFacade) {
        this.abonnementsFacade = abonnementsFacade;
    }

    public Parametres getCashIn() {
        return cashIn;
    }

    public void setCashIn(Parametres cashIn) {
        this.cashIn = cashIn;
    }

    public Parametres getCashOut() {
        return cashOut;
    }

    public void setCashOut(Parametres cashOut) {
        this.cashOut = cashOut;
    }

    public Parametres getCash() {
        return cash;
    }

    public void setCash(Parametres cash) {
        this.cash = cash;
    }

    public Codes getErreurDoublonId() {
        return erreurDoublonId;
    }

    public void setErreurDoublonId(Codes erreurDoublonId) {
        this.erreurDoublonId = erreurDoublonId;
    }

    public Parametres getModeTarification() {
        return modeTarification;
    }

    public void setModeTarification(Parametres modeTarification) {
        this.modeTarification = modeTarification;
    }

    public Parametres getTarificationMiniStement() {
        return tarificationMiniStement;
    }

    public void setTarificationMiniStement(Parametres tarificationMiniStement) {
        this.tarificationMiniStement = tarificationMiniStement;
    }

    public Parametres getTarificationBalance() {
        return tarificationBalance;
    }

    public void setTarificationBalance(Parametres tarificationBalance) {
        this.tarificationBalance = tarificationBalance;
    }

    public Operateurs getOperateurs() {
        return operateurs;
    }

    public void setOperateurs(Operateurs operateurs) {
        this.operateurs = operateurs;
    }

    public Parametres getFacturier() {
        return facturier;
    }

    public void setFacturier(Parametres facturier) {
        this.facturier = facturier;
    }

    public Parametres getCompteFacturier() {
        return compteFacturier;
    }

    public void setCompteFacturier(Parametres compteFacturier) {
        this.compteFacturier = compteFacturier;
    }

    public Parametres getActivationT24() {
        return activationT24;
    }

    public void setActivationT24(Parametres activationT24) {
        this.activationT24 = activationT24;
    }

    public Parametres getPrefixeB2W() {
        return prefixeB2W;
    }

    public void setPrefixeB2W(Parametres prefixeB2W) {
        this.prefixeB2W = prefixeB2W;
    }

    public Parametres getPrefixeW2B() {
        return prefixeW2B;
    }

    public void setPrefixeW2B(Parametres prefixeW2B) {
        this.prefixeW2B = prefixeW2B;
    }

    public Parametres getPrefixeBalance() {
        return prefixeBalance;
    }

    public void setPrefixeBalance(Parametres prefixeBalance) {
        this.prefixeBalance = prefixeBalance;
    }

    public Parametres getPrefixeStatement() {
        return prefixeStatement;
    }

    public void setPrefixeStatement(Parametres prefixeStatement) {
        this.prefixeStatement = prefixeStatement;
    }

    public Parametres getPrefixeCancel() {
        return prefixeCancel;
    }

    public void setPrefixeCancel(Parametres prefixeCancel) {
        this.prefixeCancel = prefixeCancel;
    }

    public Parametres getPrefixeTSI() {
        return prefixeTSI;
    }

    public void setPrefixeTSI(Parametres prefixeTSI) {
        this.prefixeTSI = prefixeTSI;
    }

    public Parametres getLibelleDevise() {
        return libelleDevise;
    }

    public void setLibelleDevise(Parametres libelleDevise) {
        this.libelleDevise = libelleDevise;
    }

    public Parametres getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(Parametres operatorCode) {
        this.operatorCode = operatorCode;
    }

    public Parametres getAffiliateCode() {
        return affiliateCode;
    }

    public void setAffiliateCode(Parametres affiliateCode) {
        this.affiliateCode = affiliateCode;
    }

    public Parametres getActivationDelta() {
        return activationDelta;
    }

    public void setActivationDelta(Parametres activationDelta) {
        this.activationDelta = activationDelta;
    }

    public String getScriptCompta() {
        return scriptCompta;
    }

    public void setScriptCompta(String scriptCompta) {
        this.scriptCompta = scriptCompta;
    }

    public Parametres getOtherService() {
        return otherService;
    }

    public void setOtherService(Parametres otherService) {
        this.otherService = otherService;
    }

    public Parametres getTarificationB2W() {
        return tarificationB2W;
    }

    public void setTarificationB2W(Parametres tarificationB2W) {
        this.tarificationB2W = tarificationB2W;
    }

    public Parametres getTarificationW2B() {
        return tarificationW2B;
    }

    public void setTarificationW2B(Parametres tarificationW2B) {
        this.tarificationW2B = tarificationW2B;
    }

    public Parametres getCodeOperationB2W_T24() {
        return codeOperationB2W_T24;
    }

    public void setCodeOperationB2W_T24(Parametres codeOperationB2W_T24) {
        this.codeOperationB2W_T24 = codeOperationB2W_T24;
    }

    public Parametres getCodeOperationW2B_T24() {
        return codeOperationW2B_T24;
    }

    public void setCodeOperationW2B_T24(Parametres codeOperationW2B_T24) {
        this.codeOperationW2B_T24 = codeOperationW2B_T24;
    }

    public Parametres getCodeOperationFrais_T24() {
        return codeOperationFrais_T24;
    }

    public void setCodeOperationFrais_T24(Parametres codeOperationFrais_T24) {
        this.codeOperationFrais_T24 = codeOperationFrais_T24;
    }

    public Parametres getNombreMaxTransaction() {
        return nombreMaxTransaction;
    }

    public void setNombreMaxTransaction(Parametres nombreMaxTransaction) {
        this.nombreMaxTransaction = nombreMaxTransaction;
    }

    public Parametres getMontantMaxTransaction() {
        return montantMaxTransaction;
    }

    public void setMontantMaxTransaction(Parametres montantMaxTransaction) {
        this.montantMaxTransaction = montantMaxTransaction;
    }

    public Parametres getMontantCumulMaxTransaction() {
        return montantCumulMaxTransaction;
    }

    public void setMontantCumulMaxTransaction(Parametres montantCumulMaxTransaction) {
        this.montantCumulMaxTransaction = montantCumulMaxTransaction;
    }

    public List<Limitestransactions> getListLimitesTransactions() {
        return listLimitesTransactions;
    }

    public void setListLimitesTransactions(List<Limitestransactions> listLimitesTransactions) {
        this.listLimitesTransactions = listLimitesTransactions;
    }

    public HeaderResponse validate(HeaderResponse headerResponse, HeaderRequest headerRequest, Object request) {
        System.out.println("operator code de la requete :  " + headerRequest.getOperatorCode() + " operator code de la bd " + this.operatorCode.getValeur());

        if (!this.operatorCode.getValeur().equals(headerRequest.getOperatorCode())) {
            System.out.println("OPERATOR CODE INVALIDE");
            headerResponse.setResponseCode(interfaceError.getCodevalue());
            headerResponse.setResponseMessage(interfaceError.getCodedescription() + " operator code invalid ");
            return headerResponse;
        }

        if (!affiliateCode.getValeur().equals(headerRequest.getAffiliateCode())) {
            System.out.println("AFFILIATE CODE INVALIDE");
            headerResponse.setResponseCode(interfaceError.getCodevalue());
            headerResponse.setResponseMessage(interfaceError.getCodedescription() + " affiliate code invalid ");
            return headerResponse;
        }

        if (erreurCBA == null || erreurDoublonId == null || erreurEod == null || erreurInvalidNoCompte == null || erreurToken == null || erreurTransactionNotAllowed == null || erreurTimeOut == null || erreurMontantMaxJournalier == null || erreurCumulTransactionsJournaliers == null || erreurNoData == null || erreurInsuficientBalance == null) {

            headerResponse.setResponseCode(interfaceError.getCodevalue());
            headerResponse.setResponseMessage(interfaceError.getCodedescription());
            System.out.println("CERTAINS CODES ERREURS NE SONT PAS PRESENTS DANS LA BASE DE DONNEES");
            return headerResponse;

        }

//        if (activationT24 != null && activationT24.getValeur().equals("OUI")) {
//            if (prefixeB2W == null || prefixeW2B == null || prefixeBalance == null || prefixeCancel == null || prefixeStatement == null || prefixeTSI == null) {
//                System.out.println("LES PREFIXES DE CERTAINES FONCTIONS T24 NE SONT PAS RENSEIGNES DANS LA BD - PRIERE LES AJOUTER ");
//                headerResponse.setResponseCode(interfaceError.getCodevalue());
//                headerResponse.setResponseMessage(interfaceError.getCodedescription());
//                return headerResponse;
//            }
//
//            if (facturier == null || compteFacturier == null) {
//                System.out.println("LES INFORMATIONS DU FACTURIER T24 SONT INCOMPLETES - PRIERE LES RENSEIGNER ");
//                headerResponse.setResponseCode(interfaceError.getCodevalue());
//                headerResponse.setResponseMessage(interfaceError.getCodedescription());
//                return headerResponse;
//            }
//        }
        if (eodStatus == null || cashIn == null || cashOut == null || cash == null || idleDisabled == null
                || idleEnabled == null || autorisationBalance == null || autorisationMiniStatement == null
                || autorisationB2W == null || autorisationW2B == null || modeIdle == null || tarificationBalance == null || tarificationMiniStement == null || modeTarification == null) {
            headerResponse.setResponseCode(interfaceError.getCodevalue());
            headerResponse.setResponseMessage(interfaceError.getCodedescription());
            System.out.println("CERTAINS PARAMETRES NECESSAIRES A LA VERIFICATION DES TRANSACTIONS NE SONT PAS PRESENTS DANS LA BASE DE DONNEES");
            return headerResponse;
        }

        if (modeIdle.getValeur().equals(idleEnabled.getValeur())) {
            System.out.println("le mode idle est active");
            headerResponse.setResponseCode(erreurCBA.getCodevalue());
            headerResponse.setResponseMessage(erreurCBA.getCodedescription());
            return headerResponse;
        }

        if (eodStatus.getValeur().equals("EOD_PROCESSING_YES")) {
            System.out.println("le fichier EOD est en cours de lecture...");
            headerResponse.setResponseCode(erreurEod.getCodevalue());
            headerResponse.setResponseMessage(erreurEod.getCodedescription());
            return headerResponse;
        }

        if (transactionsFacade.idAlreadyExist(headerRequest.getRequestId())) {
            System.out.println("une transaction avec cet ID existe deja ....");
            headerResponse.setResponseCode(erreurDoublonId.getCodevalue());
            headerResponse.setResponseMessage(erreurDoublonId.getCodedescription());
            return headerResponse;
        }

        if (request instanceof AccountToWalletTransfer.MobileTransferRequest) {
            AccountToWalletTransfer.MobileTransferRequest req = (AccountToWalletTransfer.MobileTransferRequest) request;
            String stringToTokenize = "";
            System.out.println("il s'agit d'une transaction de BANK TO WALLET....");
            if (autorisationB2W.getValeur().equals("OPERATION_NON_AUTORISEE")) {
                System.out.println("LES BANK TO WALLET NE SONT PAS AUTORISEES ");
                headerResponse.setResponseCode(erreurTransactionNotAllowed.getCodevalue());
                headerResponse.setResponseMessage(erreurTransactionNotAllowed.getCodedescription());
                return headerResponse;
            }
            if (token != null) {
                stringToTokenize = req.getAccountAlias() + req.getMmHeaderInfo().getRequestId() + req.getMmHeaderInfo().getOperatorCode() + token.getValeur() + (int) req.getAmount();
                System.out.println("TOKEN ENVOYE PAR LA TRANSACTION : " + req.getMmHeaderInfo().getRequestToken() + " " + " VALEUR CALCULEE PAR NOUS : " + DigestUtils.sha512Hex(stringToTokenize));
                if (!DigestUtils.sha512Hex(stringToTokenize).equalsIgnoreCase(req.getMmHeaderInfo().getRequestToken())) {
                    System.out.println("le token est invalide");
                    headerResponse.setResponseCode(erreurToken.getCodevalue());
                    headerResponse.setResponseMessage(erreurToken.getCodedescription());
                    return headerResponse;
                }
            }
        } else if (request instanceof WalletToAccountTransfer.MobileTransferRequest) {
            WalletToAccountTransfer.MobileTransferRequest req = (WalletToAccountTransfer.MobileTransferRequest) request;
            String stringToTokenize = "";
            System.out.println("il s'agit d'une transaction de WALLET TO BANK....");

            if (autorisationW2B.getValeur().equals("OPERATION_NON_AUTORISEE")) {
                System.out.println("LES WALLET TO BANK NE SONT PAS AUTORISEES ");
                headerResponse.setResponseCode(erreurTransactionNotAllowed.getCodevalue());
                headerResponse.setResponseMessage(erreurTransactionNotAllowed.getCodedescription());
                return headerResponse;
            }
            if (token != null) {
                stringToTokenize = req.getAccountAlias() + req.getMmHeaderInfo().getRequestId() + req.getMmHeaderInfo().getOperatorCode() + token.getValeur() + (int) req.getAmount();
                System.out.println("TOKEN ENVOYEE PAR ORANGE " + req.getMmHeaderInfo().getRequestToken() + " " + "CALCULATED BY EASYMBANK  " + DigestUtils.sha512Hex(stringToTokenize));
                if (!DigestUtils.sha512Hex(stringToTokenize).equalsIgnoreCase(req.getMmHeaderInfo().getRequestToken())) {
                    System.out.println("erreur de cryptage de la requette en sha512 ");
                    headerResponse.setResponseCode(erreurToken.getCodevalue());
                    headerResponse.setResponseMessage(erreurToken.getCodedescription());
                    return headerResponse;
                }
            }

        } else if (request instanceof GetAccountBalance.AccountBalanceInquiryRequest) {
            //GetAccountBalance.AccountBalanceInquiryRequest req = (GetAccountBalance.AccountBalanceInquiryRequest) request;
            if (autorisationBalance.getValeur().equals("OPERATION_NON_AUTORISEE")) {
                System.out.println("LES DEMANDES DE SOLDE NE SONT PAS AUTORISEES ");
                headerResponse.setResponseCode(erreurTransactionNotAllowed.getCodevalue());
                headerResponse.setResponseMessage(erreurTransactionNotAllowed.getCodedescription());
                return headerResponse;
            }
        } else if (request instanceof GetMiniStatement.MiniStatementRequest) {
            //GetAccountBalance.AccountBalanceInquiryRequest req = (GetAccountBalance.AccountBalanceInquiryRequest) request;
            if (autorisationMiniStatement.getValeur().equals("OPERATION_NON_AUTORISEE")) {
                System.out.println("LES MINI STATEMENT NE SONT PAS AUTORISEES ");
                headerResponse.setResponseCode(erreurTransactionNotAllowed.getCodevalue());
                headerResponse.setResponseMessage(erreurTransactionNotAllowed.getCodedescription());
                return headerResponse;
            }
        }
        // System.out.println("fin de la validation de la requette ");
        return headerResponse;
    }

    public void responseCodeMaker(ABIFunctionResponse afr, HeaderResponse headerResponse) {

        if (afr != null && afr.getStatut().equals("0")) {

             if (afr.getStatutMsg().equalsIgnoreCase("99 - NO DATA")) {//renvoi un E10 a Orange 
                System.out.println("la transaction recherchee n'a pas ete trouvee dans le SIB ");
                headerResponse.setResponseCode(erreurNoData.getCodevalue());
                headerResponse.setResponseMessage(erreurNoData.getCodedescription());

            } else {

                System.out.println("la transaction a ete un succes ");
                headerResponse.setResponseCode(codeSucces.getCodevalue());
                headerResponse.setResponseMessage(codeSucces.getCodedescription());

            }

        } else if (afr != null && afr.getStatutMsg().equalsIgnoreCase("99 - NO DATA")) {//renvoi un E10 a Orange 

            System.out.println("la transaction recherchee n'a pas ete trouvee dans le SIB ");
            headerResponse.setResponseCode(erreurNoData.getCodevalue());
            headerResponse.setResponseMessage(erreurNoData.getCodedescription());

        } else if (afr != null && afr.getStatut().contains("50")) {
            System.out.println("le solde bancaire du client est insuffisant");
            headerResponse.setResponseCode(erreurInsuficientBalance.getCodevalue());
            headerResponse.setResponseMessage(erreurInsuficientBalance.getCodedescription());
        } else if (afr != null && afr.getStatut().equalsIgnoreCase("-1")) {
            
            if (afr.getStatutMsg().equalsIgnoreCase("21 OCI U30 # OPERATION NON PARAMETRE")) {
                
                System.out.println("ce code operation n'est pas parametre dans le codeopsc de orion ");
                headerResponse.setResponseCode(erreurNoData.getCodevalue());
                headerResponse.setResponseMessage(erreurCBA.getCodedescription());
            }
            
        } else {

            System.out.println("soucis au niveau du code erreur de la transaction ....");
            headerResponse.setResponseCode(erreurCBA.getCodevalue());
            headerResponse.setResponseMessage(erreurCBA.getCodedescription());

        }
    }

    public void responseCodeMaker(ABIFunctionResponse afr, HeaderResponse headerResponse, String statut) {
        System.out.println("responseCodeMakerStatut");
        if (afr != null && afr.getStatut().equals("0") && !afr.getNoOper().equals("")) {
            headerResponse.setResponseCode(codeSucces.getCodevalue());
            headerResponse.setResponseMessage(codeSucces.getCodedescription());
        } else if (afr != null && afr.getStatut().equals("0") && afr.getNoOper().equals("")) {
            System.out.println("codeStatut.getCodevalue() " + codeStatut.getCodevalue());
            System.out.println("codeStatut.getCodedescription() " + codeStatut.getCodedescription());
            headerResponse.setResponseCode(codeStatut.getCodevalue());
            headerResponse.setResponseMessage(codeStatut.getCodedescription());
        } else {
            headerResponse.setResponseCode(erreurCBA.getCodevalue());
            headerResponse.setResponseMessage(erreurCBA.getCodedescription());

        }
    }

}
