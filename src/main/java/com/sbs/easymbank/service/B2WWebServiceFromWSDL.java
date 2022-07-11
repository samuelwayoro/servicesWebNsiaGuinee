/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.easymbank.service;

import com.banktowallet.b2w.b2w.AccountToWalletTransfer;
import com.banktowallet.b2w.b2w.AccountToWalletTransferResponse;
import com.sbs.b2wservice.dao.ResponsePersistor;
import com.sbs.b2wservice.dao.RequestValidator;
import com.banktowallet.b2w.b2w._1.HeaderResponse;
import com.banktowallet.b2w.b2w._1.TransactionDetail;
import com.sbs.b2wservice.dao.CodesFacade;
import com.sbs.b2wservice.dao.CommissionsFacade;
import com.sbs.b2wservice.dao.LimitestransactionsFacade;
import com.sbs.b2wservice.dao.ParametresFacade;
import com.sbs.b2wservice.dao.StoredProcedureCaller;
import com.sbs.b2wservice.dao.TarifsProfilsOperateursFacade;
import com.sbs.b2wservice.dao.TransactionsFacade;
import com.sbs.b2wservice.dao.WebproccorFacade;
import com.sbs.b2wservice.entities.ABIFunctionResponse;
import com.sbs.b2wservice.entities.Abonnements;
import com.sbs.b2wservice.entities.Commissions;
import com.sbs.b2wservice.entities.Limitestransactions;
import com.sbs.b2wservice.entities.Parametres;
import com.sbs.b2wservice.entities.TarifsProfilsOperateurs;
import com.sbs.b2wservice.entities.Webproccor;
import com.sbs.b2wservice.t24.T24Handler;
import com.sbs.b2wservice.utils.AutoAnnulation;
import com.sbs.easymbank.other.PaymentServiceCaller;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author SOCITECH-
 */
@WebService(serviceName = "IB2WService", portName = "B2WServicesPort", endpointInterface = "com.banktowallet.b2w.b2w.B2WServices", targetNamespace = "http://b2w.banktowallet.com/b2w", wsdlLocation = "WEB-INF/wsdl/partner.wsdl")
public class B2WWebServiceFromWSDL {

    @EJB
    private CommissionsFacade commissionsFacade;

//    private Abonnements abonnements;
    private Commissions commissions;
    private static final int delais_attente = 20; //timeur d'attente sur les transactions B2W et W2B envoyee au SIB ...
    private GregorianCalendar cal = new GregorianCalendar();
    @EJB
    private CodesFacade codesFacade;
    @EJB
    private ParametresFacade parametresFacade;
    @EJB
    private TransactionsFacade transactionsFacade;
    @EJB
    private StoredProcedureCaller storedProcedureCaller;
    @EJB
    private RequestValidator requestValidator;
    private final DecimalFormat dcf = new DecimalFormat("###.##");
    private double taxe = 0;
    private double charge = 0;
    private String profilClient;
    private String colonneCle;
    private Limitestransactions laLimite;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yy HH:mm");
    private final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
    private final SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");

    Parametres url_auto_test = null;
    @EJB
    ResponsePersistor responsePersistor;
    @EJB
    private TarifsProfilsOperateursFacade tarifsProfilsOperateursFacade;
    @EJB
    private T24Handler t24Handler;
    @EJB
    private WebproccorFacade webproccorFacade;
    @EJB
    private AutoAnnulation auto;
    @EJB
    private LimitestransactionsFacade limiteTransactionsFacade;

    public String getColonneCle() {
        return colonneCle;
    }

    public void setColonneCle(String colonneCle) {
        this.colonneCle = colonneCle;
    }

    public WebproccorFacade getWebproccorFacade() {
        return webproccorFacade;
    }

    public void setWebproccorFacade(WebproccorFacade webproccorFacade) {
        this.webproccorFacade = webproccorFacade;
    }

    public AutoAnnulation getAuto() {
        return auto;
    }

    public void setAuto(AutoAnnulation auto) {
        this.auto = auto;
    }

    public LimitestransactionsFacade getLimiteTransactionsFacade() {
        return limiteTransactionsFacade;
    }

    public void setLimiteTransactionsFacade(LimitestransactionsFacade limiteTransactionsFacade) {
        this.limiteTransactionsFacade = limiteTransactionsFacade;
    }

    public Limitestransactions getLaLimite() {
        return laLimite;
    }

    public void setLaLimite(Limitestransactions laLimite) {
        this.laLimite = laLimite;
    }

    public CommissionsFacade getCommissionsFacade() {
        return commissionsFacade;
    }

    public void setCommissionsFacade(CommissionsFacade commissionsFacade) {
        this.commissionsFacade = commissionsFacade;
    }

    public Commissions getCommissions() {
        return commissions;
    }

    public void setCommissions(Commissions commissions) {
        this.commissions = commissions;
    }

    public GregorianCalendar getCal() {
        return cal;
    }

    public void setCal(GregorianCalendar cal) {
        this.cal = cal;
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

    public StoredProcedureCaller getStoredProcedureCaller() {
        return storedProcedureCaller;
    }

    public void setStoredProcedureCaller(StoredProcedureCaller storedProcedureCaller) {
        this.storedProcedureCaller = storedProcedureCaller;
    }

    public RequestValidator getRequestValidator() {
        return requestValidator;
    }

    public void setRequestValidator(RequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }

    public double getTaxe() {
        return taxe;
    }

    public void setTaxe(double taxe) {
        this.taxe = taxe;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public Parametres getUrl_auto_test() {
        return url_auto_test;
    }

    public void setUrl_auto_test(Parametres url_auto_test) {
        this.url_auto_test = url_auto_test;
    }

    public ResponsePersistor getResponsePersistor() {
        return responsePersistor;
    }

    public void setResponsePersistor(ResponsePersistor responsePersistor) {
        this.responsePersistor = responsePersistor;
    }

    public TarifsProfilsOperateursFacade getTarifsProfilsOperateursFacade() {
        return tarifsProfilsOperateursFacade;
    }

    public void setTarifsProfilsOperateursFacade(TarifsProfilsOperateursFacade tarifsProfilsOperateursFacade) {
        this.tarifsProfilsOperateursFacade = tarifsProfilsOperateursFacade;
    }

    public T24Handler getT24Handler() {
        return t24Handler;
    }

    public void setT24Handler(T24Handler t24Handler) {
        this.t24Handler = t24Handler;
    }

    public String getProfilClient() {
        return profilClient;
    }

    public void setProfilClient(String profilClient) {
        this.profilClient = profilClient;
    }

    public com.banktowallet.b2w.b2w.CancelTransferResponse.Return cancelTransfer(final com.banktowallet.b2w.b2w.CancelTransfer.TranRequestInfo tranRequestInfo) {
        //TODO implement this method
        // throw new UnsupportedOperationException("Not implemented yet.");
        Object object;
        requestValidator.init();
        ABIFunctionResponse afr = new ABIFunctionResponse();
        com.banktowallet.b2w.b2w.CancelTransferResponse.Return response = new com.banktowallet.b2w.b2w.CancelTransferResponse.Return();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse.setResponseCode(" ");
            headerResponse.setOperatorCode(tranRequestInfo.getMmHeaderInfo().getOperatorCode());
            headerResponse.setRequestId(tranRequestInfo.getMmHeaderInfo().getRequestId());
            headerResponse.setAffiliateCode(tranRequestInfo.getMmHeaderInfo().getAffiliateCode());
            response.setMmHeaderInfo(headerResponse);

            requestValidator.validate(headerResponse, tranRequestInfo.getMmHeaderInfo(), tranRequestInfo);
            if (!headerResponse.getResponseCode().isEmpty() && !headerResponse.getResponseCode().equals(" ")) {
                responsePersistor.persist(response, tranRequestInfo, requestValidator.getOperateurs(), afr);
                return response;
            }
            Callable<Object> valeur = new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    if (requestValidator.getActivationT24() != null && requestValidator.getActivationT24().getValeur().equals("OUI")) {
                        return t24Handler.callT24(requestValidator.getPrefixeCancel().getValeur(), requestValidator.getFacturier().getValeur(), sdf.format(Calendar.getInstance().getTime()), tranRequestInfo.getMmHeaderInfo().getRequestId(), tranRequestInfo.getExternalRefNo());
                    } else {
                        //object = storedProcedureCaller.callFunction("cancelTransfer", tranRequestInfo, "REFREL", tranRequestInfo.getMmHeaderInfo().getRequestId(), requestValidator.getOperateurs().getIdOperateur().toString());
                        return storedProcedureCaller.callFunction("cancelTransfer", tranRequestInfo, "REFREL", tranRequestInfo.getMmHeaderInfo().getRequestId(), requestValidator.getOperateurs().getIdOperateur().toString());
                    }
                }
            };

            FutureTask<Object> futureTask = new FutureTask<>(valeur);
            Thread thread = new Thread(futureTask);
            thread.start();
            object = futureTask.get(delais_attente, TimeUnit.SECONDS);

            afr = storedProcedureCaller.getABIFunctionResponse(object);

            requestValidator.responseCodeMaker(afr, headerResponse);
            response.setCBAReferenceNo(afr.getNoOper());

            response.setExternalRefNo(headerResponse.getRequestId());

            responsePersistor.persist(response, tranRequestInfo, requestValidator.getOperateurs(), afr);
            return response;
        } catch (Exception ex) {
            if (afr.getStatut() != null && afr.getStatut().equals("0")) {
                System.out.println("TRANSACTION REUSSIE MAIS PEUT-ETRE INTRACABLE");
                headerResponse.setResponseCode(requestValidator.getCodeSucces().getCodevalue());
                headerResponse.setResponseMessage(requestValidator.getCodeSucces().getCodedescription());
            } else {
                headerResponse.setResponseCode("E04");
                headerResponse.setResponseMessage("Interface Error");
            }
            responsePersistor.persist(response, tranRequestInfo, requestValidator.getOperateurs(), afr);
        } finally {
            return response;
        }

    }

    public AccountToWalletTransferResponse.Return accountToWalletTransfer(final AccountToWalletTransfer.MobileTransferRequest mobileTransferRequest) {

        System.out.println("DEBUT DE LA REQUETTE DE BANK TO WALLET");
        AccountToWalletTransferResponse.Return response = new AccountToWalletTransferResponse.Return();
        HeaderResponse headerResponse = new HeaderResponse();
        ABIFunctionResponse afr = new ABIFunctionResponse();
        Object object = null;

        try {

            this.requestValidator.init();//chargement de tous les parametres utils aux transactions dans la base 
            headerResponse.setResponseCode(" ");
            headerResponse.setOperatorCode(mobileTransferRequest.getMmHeaderInfo().getOperatorCode());
            headerResponse.setRequestId(mobileTransferRequest.getMmHeaderInfo().getRequestId());
            headerResponse.setAffiliateCode(mobileTransferRequest.getMmHeaderInfo().getAffiliateCode());

            response.setMmHeaderInfo(headerResponse);
            System.out.println("verifications des parametres de la transactions ...");
            this.requestValidator.validate(headerResponse, mobileTransferRequest.getMmHeaderInfo(), mobileTransferRequest);
            System.out.println("fin de verifications des parametres pr transactions ...");

            if (!headerResponse.getResponseCode().isEmpty() && !headerResponse.getResponseCode().equals(" ")) {
                System.out.println("certains parametres manque dans la base de donnees - voir table parametres... ");
                this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                return response;
            }

            System.out.println("recup de l_abonnee");
            final Abonnements abonnements = this.requestValidator.getAbonnementsFacade().findByActifByAlias(mobileTransferRequest.getAccountAlias(), true);
            //System.out.println("fin de la recup de l_abonnee");

            if (abonnements == null && this.requestValidator.getErreurInvalidNoCompte() != null) {
                System.out.println("l'abonne n'existe pas dans la bd ");
                headerResponse.setResponseCode(this.requestValidator.getErreurInvalidNoCompte().getCodevalue());
                headerResponse.setResponseMessage(this.requestValidator.getErreurInvalidNoCompte().getCodedescription());
                this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                return response;
            }

            //verif de type de transaction 
            if (this.requestValidator.getCashOut() != null && abonnements.getService().trim().equals(this.requestValidator.getCashOut().getValeur().trim())) {
                System.out.println("erreur le client ne peux pas faire ce type de transaction ...");
                headerResponse.setResponseCode(this.requestValidator.getErreurTransactionNotAllowed().getCodevalue());
                headerResponse.setResponseMessage(this.requestValidator.getErreurTransactionNotAllowed().getCodedescription());
                this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                return response;
            }

            //nbre d'operation journalier pour les bank to wallet en fonction du profil client 
            //recup du profil client 
            profilClient = abonnements.getProfil().getDesignationProfils();
            //recuperer la limite en question grace  a la facade
            laLimite = new Limitestransactions();

            try {

                //concatenantion de Nombre d'operation Journalier+profil client+BANK TO WALLET  doit donner =  Nombre d'operation JournalierSTANDARDBANK TO WALLET
                colonneCle = "Nombre d'operation Journalier" + profilClient + "BANK TO WALLET";
                try {
                    laLimite = limiteTransactionsFacade.findLimiteByKeycolumn(colonneCle);
                    System.out.println("verirication de la valeur de la limite suivante :" + colonneCle + " valeur  :  " + laLimite.getValeur());
                    //si superieur a cette valeur retourner une reponse d'erreur 
                    if ((laLimite.getValeur() != null) && (this.transactionsFacade.nombreTransactionJour(mobileTransferRequest.getAccountAlias()) > Long.parseLong(laLimite.getValeur()))) {
                        System.out.println("nombre maximum des transactions par jours atteint pour ce client  " + abonnements.getNom() + " " + abonnements.getNumerotelephone());
                        headerResponse.setResponseCode(this.requestValidator.getNbresJoursB2w().getCodevalue());
                        headerResponse.setResponseMessage(this.requestValidator.getNbresJoursB2w().getCodedescription());
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        String message = "Nombre limite de transactions (" + laLimite.getValeur() + ") atteint pour ce compte  ";
                        afr.setStatut(message);
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        System.out.println(message);
                        return response;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //concatenantion de Nombre d'operation mensuel+profil client+BANK TO WALLET  doit donner =  Nombre d'operation JournalierSTANDARDBANK TO WALLET
                colonneCle = "Nombre d'operation mensuel" + profilClient + "BANK TO WALLET";
                //recuperer la limite en question grace  a la facade
                laLimite = new Limitestransactions();
                try {
                    laLimite = limiteTransactionsFacade.findLimiteByKeycolumn(colonneCle);
                    System.out.println("valeur de la limite trouvee  :  " + laLimite.getValeur());
                    //si superieur a cette valeur retourner une reponse d'erreur 
                    if ((laLimite.getValeur() != null) && (this.transactionsFacade.nombreTransactionMois(mobileTransferRequest.getAccountAlias()) > Long.parseLong(laLimite.getValeur()))) {
                        System.out.println("nombre maximum des transactions par maois atteint pour ce client  " + abonnements.getNom() + " " + abonnements.getNumerotelephone());
                        headerResponse.setResponseCode(this.requestValidator.getNbresMoisB2w().getCodevalue());//a chnger par un nouveau code erreur ...
                        headerResponse.setResponseMessage(this.requestValidator.getNbresMoisB2w().getCodedescription());// a changer par un nouveau libelle d'erreur 
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        String message = "Nombre limite de transactions mensuelle  (" + laLimite.getValeur() + ") atteint pour ce compte  ";
                        afr.setStatut(message);
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        System.out.println(message);
                        return response;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //concatenantion de plafonnement mensuel+profil client+BANK TO WALLET  doit donner =  Nombre d'operation JournalierSTANDARDBANK TO WALLET
                colonneCle = "plafonnement mensuel" + profilClient + "BANK TO WALLET";
                //recuperer la limite en question grace  a la facade
                laLimite = new Limitestransactions();
                try {
                    laLimite = limiteTransactionsFacade.findLimiteByKeycolumn(colonneCle);
                    System.out.println("valeur de la limite trouvee  :  " + laLimite.getValeur());
                    double cumul_client_mois = transactionsFacade.sommeTransactionMois(mobileTransferRequest.getAccountAlias());
                    System.out.println("cumul mensuel actuel de l'abonne  " + mobileTransferRequest.getAccountAlias() + "    =   " + cumul_client_mois);
                    System.out.println("cumul mensuel a ne pas franchir   :  " + laLimite.getValeur());

                    if ((laLimite.getValeur() != null) && (((cumul_client_mois + mobileTransferRequest.getAmount()) > Long.parseLong(laLimite.getValeur())))) {

                        System.out.println("attention cumul mensuel de montant franchi par le client ");
                        //  double montant_max_autorisee = Long.parseLong(requestValidator.getMontantCumulMaxTransaction().getValeur()) - cumul_client;
                        headerResponse.setResponseCode(requestValidator.getPlafonMoisB2w().getCodevalue());
                        headerResponse.setResponseMessage(requestValidator.getPlafonMoisB2w().getCodedescription());
                        responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                        String message = "Le client a effectue des transactions au dela du montant cumule , qui est de " + cumul_client_mois + "par mois ";
                        afr.setStatutMsg(message);
                        responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                        System.out.println(message);
                        return response;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //VERIFICATION SUR LA LIMITE TRANSACTION JOURNALIERE (montant a ne pas franchir pour le profil client)
                //concatenantion de plafonnement mensuel+profil client+BANK TO WALLET  doit donner =  Nombre d'operation JournalierSTANDARDBANK TO WALLET
                colonneCle = "Transaction Limite journaliere" + profilClient + "BANK TO WALLET";
                //recuperer la limite en question grace  a la facade
                laLimite = new Limitestransactions();
                try {
                    laLimite = limiteTransactionsFacade.findLimiteByKeycolumn(colonneCle);
                    System.out.println("valeur de la limite trouvee  :  " + laLimite.getValeur());
                    //si superieur a cette valeur retourner une reponse d'erreur 
                    if ((laLimite.getValeur() != null) && (mobileTransferRequest.getAmount() > Long.parseLong(laLimite.getValeur()))) {
                        System.out.println("attention le montant de la transaction est superieur au montant limite permis par jour pour le profil de ce client  ");
                        headerResponse.setResponseCode(this.requestValidator.getLimiteTrxJoursB2w().getCodevalue());//a chnger par un nouveau code erreur ...
                        headerResponse.setResponseMessage(this.requestValidator.getLimiteTrxJoursB2w().getCodedescription());// a changer par un nouveau libelle d'erreur 
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        String message = "le montant de la transaction est superieur au montant limite permis  ";
                        afr.setStatut(message);
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        System.out.println(message);
                        return response;
                    }

                } catch (NumberFormatException e) {
                }
                //dev la traitement ici 

            } catch (Exception e) {

                //retourne un msg d'erreur avec un code erreur preci si la limite ne pas trouvee 
                if (laLimite == null) {
                    System.out.println("erreur le client ne peux pas faire ce type de transaction car son profil client n'est pas parametre dans les limites de transactions ...");
                    headerResponse.setResponseCode(this.requestValidator.getErreurTransactionNotAllowed().getCodevalue());
                    headerResponse.setResponseMessage(this.requestValidator.getErreurTransactionNotAllowed().getCodedescription());
                    this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                    return response;

                }
            }

            //GESTION DE LA TARIFICATION
            System.out.println("VALEUR TARIFICATION  " + this.requestValidator.getTarificationB2W().getValeur());
            if (this.requestValidator.getTarificationB2W().getValeur().equals("TARIFICATION_AUTORISEE")) {
                System.out.println("recup de la tarification");
                List<Commissions> listCom = this.commissionsFacade.findCommissionByMontantNet(mobileTransferRequest.getAmount(), "B2W", abonnements);
                System.out.println(" nbre de commissions recuperee dans la bd " + listCom.size());
                if (!listCom.isEmpty()) {

//                    if (this.requestValidator.getModeTarification().getValeur().equals("TARIFICATION_FIXE")) {
//                        mobileTransferRequest.setCharge(((Commissions) listCom.get(0)).getTarif().longValue());
//                        System.out.println("la charge est   " + mobileTransferRequest.getCharge());
//                    } else {
//                        System.out.println("la tarification nes pas fixe ************** ");
//                        double charge = ((Commissions) listCom.get(0)).getTaux().longValue() * mobileTransferRequest.getAmount() / 100.0D;
//                        String s = this.dcf.format(charge);
//                        mobileTransferRequest.setCharge((new Double(s)).doubleValue());
//                    }
                    //calcul de la charge et rajout sur le montant de la transaction
                    if (requestValidator.getModeTarification().getValeur().equals("TARIFICATION_FIXE")) {
                        //charge=listCom.get(0).getTarif().longValue(); //valeur par defaut de charge
                        charge = (listCom.get(0).getTarif().longValue() + (0.19 * listCom.get(0).getTarif().longValue()));
                        mobileTransferRequest.setCharge(charge);
                        mobileTransferRequest.setAmount(mobileTransferRequest.getAmount() + charge);
                    } else {
                        charge = listCom.get(0).getTaux().longValue() * mobileTransferRequest.getAmount() / 100;
                        String s = dcf.format(charge);
                        mobileTransferRequest.setCharge(charge);//2 chiffres après la virgule
                    }

                } else {
                    System.out.println("Le Montant " + mobileTransferRequest.getAmount() + " n'est pas pris en compte dans la grille de commissions");
                    System.out.println("Transaction " + mobileTransferRequest.getMmHeaderInfo().getRequestId());
                    this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                    return response;
                }
            }

            try {
                mobileTransferRequest.setAccountNo(abonnements.getCompte());
                cal.setTime(new Date());
                XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                mobileTransferRequest.setTranDate(xcal);
            } catch (Exception e) {
                System.out.println("ATTENTION : " + e.getMessage());
            }

            //    System.out.println("COMMISSION: " + mobileTransferRequest.getCharge());
            mobileTransferRequest.setAccountNo(abonnements.getCompte());
            Callable<Object> valeur = new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return storedProcedureCaller.callFunction("accountToWalletTransfer", mobileTransferRequest, abonnements.getOperateur().getIdOperateur().toString());
                }
            };

            FutureTask<Object> futureTask = new FutureTask<>(valeur);
            Thread thread = new Thread(futureTask);
            thread.start();
            object = futureTask.get(delais_attente, TimeUnit.SECONDS);

            afr = this.storedProcedureCaller.getABIFunctionResponse(object);
            this.requestValidator.responseCodeMaker(afr, headerResponse);
            response.setCBAReferenceNo(afr.getNoOper());
            response.setExternalRefNo(headerResponse.getRequestId());
            this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            headerResponse.setResponseCode("E11");
            headerResponse.setResponseMessage("Operation in timeout . Use transferStatusInquery to check status");
            System.out.println("erreur de communication avec le SIB  - connexion time out - une demande d'annulation sera envoyé par l'opérateur ");
            response.setCBAReferenceNo("");
            response.setExternalRefNo(headerResponse.getRequestId());
            System.out.println("probleme lors tu traitement normal ... nous n'avons pas recu la reponse en 30 seconds !");
            this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), new ABIFunctionResponse());
            return response;
        }
    }

    public com.banktowallet.b2w.b2w.WalletToAccountTransferResponse.Return walletToAccountTransfer(final com.banktowallet.b2w.b2w.WalletToAccountTransfer.MobileTransferRequest mobileTransferRequest) {

        ABIFunctionResponse afr = new ABIFunctionResponse();
        Object object;
        HeaderResponse headerResponse = new HeaderResponse();

        com.banktowallet.b2w.b2w.WalletToAccountTransferResponse.Return response = new com.banktowallet.b2w.b2w.WalletToAccountTransferResponse.Return();

        try {

            requestValidator.init();
            headerResponse.setResponseCode(" ");
            headerResponse.setOperatorCode(mobileTransferRequest.getMmHeaderInfo().getOperatorCode());
            headerResponse.setRequestId(mobileTransferRequest.getMmHeaderInfo().getRequestId());
            headerResponse.setAffiliateCode(mobileTransferRequest.getMmHeaderInfo().getAffiliateCode());

            response.setMmHeaderInfo(headerResponse);
            requestValidator.validate(headerResponse, mobileTransferRequest.getMmHeaderInfo(), mobileTransferRequest);

            if (!headerResponse.getResponseCode().isEmpty() && !headerResponse.getResponseCode().equals(" ")) {
                responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                return response;
            }

            final Abonnements abonnements = requestValidator.getAbonnementsFacade().findByActifByAlias(mobileTransferRequest.getAccountAlias(), true);

            if (abonnements == null) {
                if (requestValidator.getErreurInvalidNoCompte() != null) {
                    headerResponse.setResponseCode(requestValidator.getErreurInvalidNoCompte().getCodevalue());
                    headerResponse.setResponseMessage(requestValidator.getErreurInvalidNoCompte().getCodedescription());
                    responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                    return response;
                }
            }
            //verification de la posibilite de faire des cashin
            if (requestValidator.getCashIn() != null && abonnements.getService().trim().equals(requestValidator.getCashIn().getValeur().trim())) {
                headerResponse.setResponseCode(requestValidator.getErreurTransactionNotAllowed().getCodevalue());
                headerResponse.setResponseMessage(requestValidator.getErreurTransactionNotAllowed().getCodedescription());
                responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                return response;
            }

            //Vérification des limites du montant maximum de transactions / jrs 
//            if (requestValidator.getMontantMaxTransaction() != null && Double.parseDouble(requestValidator.getMontantMaxTransaction().getValeur()) < mobileTransferRequest.getAmount()) {
//                headerResponse.setResponseCode(this.requestValidator.getErreurmontantMaxJournalier().getCodevalue());
//                headerResponse.setResponseMessage(this.requestValidator.getErreurmontantMaxJournalier().getCodedescription());
//                String message = "Montant " + mobileTransferRequest.getAmount() + " supérieur à la limite autorisée: " + requestValidator.getMontantMaxTransaction().getValeur();
//                afr.setStatutMsg(message);
//                responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
//                System.out.println(message);
//                return response;
//            }
            //nbre d'operation journalier pour les bank to wallet en fonction du profil client 
            //recup du profil client 
            profilClient = abonnements.getProfil().getDesignationProfils();
            //recuperer la limite en question grace  a la facade
            laLimite = new Limitestransactions();

            try {

                //concatenantion de Nombre d'operation Journalier+profil client+BANK TO WALLET  doit donner =  Nombre d'operation JournalierSTANDARDBANK TO WALLET
                colonneCle = "Nombre d'operation Journalier" + profilClient + "WALLET TO BANK";
                try {
                    System.out.println("verification de la limite     " + colonneCle);
                    laLimite = limiteTransactionsFacade.findLimiteByKeycolumn(colonneCle);
                    System.out.println("valeur de la limite trouvee  :  " + laLimite.getValeur());
                    //si superieur a cette valeur retourner une reponse d'erreur 
                    if ((laLimite.getValeur() != null) && (this.transactionsFacade.nombreTransactionJour(mobileTransferRequest.getAccountAlias()) > Long.parseLong(laLimite.getValeur()))) {
                        System.out.println("nombre maximum des transactions par jours atteint pour ce client  " + abonnements.getNom() + " " + abonnements.getNumerotelephone());
                        headerResponse.setResponseCode(this.requestValidator.getNbresJoursW2b().getCodevalue());
                        headerResponse.setResponseMessage(this.requestValidator.getNbresJoursW2b().getCodedescription());
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        String message = "Nombre limite de transactions (" + laLimite.getValeur() + ") atteint pour ce compte  ";
                        afr.setStatut(message);
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        System.out.println(message);
                        return response;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    colonneCle = "";
                }

                //concatenantion de Nombre d'operation mensuel+profil client+BANK TO WALLET  doit donner =  Nombre d'operation JournalierSTANDARDBANK TO WALLET
                colonneCle = "Nombre d'operation mensuel" + profilClient + "WALLET TO BANK";
                //recuperer la limite en question grace  a la facade
                laLimite = new Limitestransactions();
                try {
                    laLimite = limiteTransactionsFacade.findLimiteByKeycolumn(colonneCle);
                    System.out.println("verification de la limite     " + colonneCle);
                    System.out.println("valeur de la limite trouvee  :  " + laLimite.getValeur());
                    //si superieur a cette valeur retourner une reponse d'erreur 
                    if ((laLimite.getValeur() != null) && (this.transactionsFacade.nombreTransactionMois(mobileTransferRequest.getAccountAlias()) > Long.parseLong(laLimite.getValeur()))) {
                        System.out.println("nombre maximum des transactions par maois atteint pour ce client  " + abonnements.getNom() + " " + abonnements.getNumerotelephone());
                        headerResponse.setResponseCode(this.requestValidator.getNbresMoisB2w().getCodevalue());//a chnger par un nouveau code erreur ...
                        headerResponse.setResponseMessage(this.requestValidator.getNbresMoisB2w().getCodedescription());// a changer par un nouveau libelle d'erreur 
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        String message = "Nombre limite de transactions mensuelle  (" + laLimite.getValeur() + ") atteint pour ce compte  ";
                        afr.setStatut(message);
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        System.out.println(message);
                        return response;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    colonneCle = "";
                }

                //concatenantion de plafonnement mensuel+profil client+BANK TO WALLET  doit donner =  Nombre d'operation JournalierSTANDARDBANK TO WALLET
                colonneCle = "plafonnement mensuel" + profilClient + "WALLET TO BANK";
                //recuperer la limite en question grace  a la facade
                laLimite = new Limitestransactions();
                try {
                    laLimite = limiteTransactionsFacade.findLimiteByKeycolumn(colonneCle);
                    System.out.println("verification de la limite     " + colonneCle);
                    System.out.println("valeur de la limite trouvee  :  " + laLimite.getValeur());
                    double cumul_client_mois = transactionsFacade.sommeTransactionMois(mobileTransferRequest.getAccountAlias());
                    System.out.println("cumul mensuel actuel de l'abonne  " + mobileTransferRequest.getAccountAlias() + "    =   " + cumul_client_mois);
                    System.out.println("cumul mensuel a ne pas franchir   :  " + laLimite.getValeur());

                    if ((laLimite.getValeur() != null) && ((cumul_client_mois + mobileTransferRequest.getAmount()) > Long.parseLong(laLimite.getValeur()))) {

                        System.out.println("attention cumul mensuel de montant franchi par le client ");
                        //  double montant_max_autorisee = Long.parseLong(requestValidator.getMontantCumulMaxTransaction().getValeur()) - cumul_client;
                        headerResponse.setResponseCode(requestValidator.getPlafonMoisW2b().getCodevalue());
                        headerResponse.setResponseMessage(requestValidator.getPlafonMoisW2b().getCodedescription());
                        responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                        String message = "Le client a effectue des transactions au dela du montant cumule , qui est de " + cumul_client_mois + "par mois ";
                        afr.setStatutMsg(message);
                        responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                        System.out.println(message);
                        return response;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    colonneCle = "";
                }

                //VERIFICATION SUR LA LIMITE TRANSACTION JOURNALIERE (montant a ne pas franchir pour le profil client)
                //concatenantion de plafonnement mensuel+profil client+BANK TO WALLET  doit donner =  Nombre d'operation JournalierSTANDARDBANK TO WALLET
                colonneCle = "Transaction Limite journaliere" + profilClient + "WALLET TO BANK";
                //recuperer la limite en question grace  a la facade
                laLimite = new Limitestransactions();
                try {
                    System.out.println("verification de la limite     " + colonneCle);
                    laLimite = limiteTransactionsFacade.findLimiteByKeycolumn(colonneCle);
                    System.out.println("valeur de la limite trouvee  :  " + laLimite.getValeur());
                    //si superieur a cette valeur retourner une reponse d'erreur 
                    if ((laLimite.getValeur() != null) && (mobileTransferRequest.getAmount() > Long.parseLong(laLimite.getValeur()))) {
                        System.out.println("attention le montant de la transaction est superieur au montant limite permis par jour pour le profil de ce client  ");
                        headerResponse.setResponseCode(this.requestValidator.getLimiteTrxJoursW2b().getCodevalue());//a chnger par un nouveau code erreur ...
                        headerResponse.setResponseMessage(this.requestValidator.getLimiteTrxJoursW2b().getCodedescription());// a changer par un nouveau libelle d'erreur 
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        String message = "le montant de la transaction est superieur au montant limite permis  ";
                        afr.setStatut(message);
                        this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                        System.out.println(message);
                        return response;
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } finally {
                    colonneCle = "";
                }

            } catch (Exception e) {

                //retourne un msg d'erreur avec un code erreur preci si la limite ne pas trouvee 
                if (laLimite == null) {
                    System.out.println("erreur le client ne peux pas faire ce type de transaction car son profil client n'est pas parametre dans les limites de transactions ...");
                    headerResponse.setResponseCode(this.requestValidator.getErreurTransactionNotAllowed().getCodevalue());
                    headerResponse.setResponseMessage(this.requestValidator.getErreurTransactionNotAllowed().getCodedescription());
                    this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), afr);
                    return response;

                }
            }

            //verification des limites du nbre de transactions a ne pas franchire par jrs
            /*
            if (requestValidator.getNombreMaxTransaction() != null && transactionsFacade.nombreTransactionJour(mobileTransferRequest.getAccountAlias()) >= Long.parseLong(requestValidator.getNombreMaxTransaction().getValeur())) {
                System.out.println("attention nombre maximum des transactions par jours atteint ...");
                headerResponse.setResponseCode(this.requestValidator.getErreurNbreTrsxJournaliers().getCodevalue());
                headerResponse.setResponseMessage(this.requestValidator.getErreurNbreTrsxJournaliers().getCodedescription());
                responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                String message = "Nombre limite de transactions (" + requestValidator.getNombreMaxTransaction().getValeur() + ") atteint pour ce compte ";
                afr.setStatutMsg(message);
                responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                System.out.println(message);
                return response;
            }
             */
            //verification du cumul des montant de transactions journalieres franchies
            /*
            double cumul_client = transactionsFacade.sommeTransactionJour(mobileTransferRequest.getAccountAlias());
            System.out.println("cumul actuel de l'abonne  " + mobileTransferRequest.getAccountAlias() + "    =   " + cumul_client);
            System.out.println("cumul /jrs a ne pas franchir   :  " + requestValidator.getMontantCumulMaxTransaction().getValeur());
            if (requestValidator.getMontantCumulMaxTransaction() != null && ((cumul_client + mobileTransferRequest.getAmount()) > Long.parseLong(requestValidator.getMontantCumulMaxTransaction().getValeur()))) {
                System.out.println("attention cumul journalier de montant franchi... ");
                //  double montant_max_autorisee = Long.parseLong(requestValidator.getMontantCumulMaxTransaction().getValeur()) - cumul_client;
                headerResponse.setResponseCode(requestValidator.getErreurCumulTransactionsJournaliers().getCodevalue());
                headerResponse.setResponseMessage(requestValidator.getErreurCumulTransactionsJournaliers().getCodedescription());
                responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                String message = "Le client a effectue des transactions au dela du montant cumule , qui est de " + cumul_client + "par jours ";
                afr.setStatutMsg(message);
                responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                System.out.println(message);
                return response;
            }
             */
            if (requestValidator.getTarificationW2B() != null && requestValidator.getTarificationW2B().getValeur().equals("TARIFICATION_AUTORISEE")) {
                List<Commissions> listCom = commissionsFacade.findCommissionByMontantNet(mobileTransferRequest.getAmount(), "W2B", abonnements);
                if (!listCom.isEmpty()) {
                    if (requestValidator.getModeTarification().getValeur().equals("TARIFICATION_FIXE")) {
                        charge = (listCom.get(0).getTarif().longValue() + (0.19 * listCom.get(0).getTarif().longValue()));
                        mobileTransferRequest.setCharge(charge);
                    } else {
                        charge = listCom.get(0).getTaux().longValue() * mobileTransferRequest.getAmount() / 100;
                        String s = dcf.format(charge);
                        mobileTransferRequest.setCharge(charge);//2 chiffres après la virgule
                    }
                } else {
                    System.out.println("Le Montant " + mobileTransferRequest.getAmount() + " n'est pas pris en compte dans la grille de commissions");
                    System.out.println("Transaction " + mobileTransferRequest.getMmHeaderInfo().getRequestId() + " échouée");
                    headerResponse.setResponseCode("E09");
                    headerResponse.setResponseMessage("Transaction not supported for Wallet");
                    responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
                    return response;
                }
            }

            try {
                mobileTransferRequest.setAccountNo(abonnements.getCompte());
                cal.setTime(new Date());
                XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                mobileTransferRequest.setTranDate(xcal);
            } catch (Exception e) {
                System.out.println("ATTENTION : " + e.getMessage());
            }

            //Pour un WalletToBank, on met à jour le numéro de compte dans l'objet entrant
            //Pour un WalletToOtherBank, pas besoin
            if (mobileTransferRequest.getMmHeaderInfo().getRequestType() == null || !mobileTransferRequest.getMmHeaderInfo().getRequestType().equals("W2OB")) {
                mobileTransferRequest.setAccountNo(abonnements.getCompte());
            }
            Callable<Object> valeur = new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    if (requestValidator.getActivationT24() != null && requestValidator.getActivationT24().getValeur().equals("OUI")) {
                        //new taxe pour Wizall
                        taxe = Math.round(charge - (charge / 1.1));
                        return t24Handler.callT24(requestValidator.getPrefixeW2B().getValeur(), requestValidator.getFacturier().getValeur(),
                                sdf.format(Calendar.getInstance().getTime()), requestValidator.getCodeOperationW2B_T24().getValeur(), mobileTransferRequest.getMmHeaderInfo().getRequestId(),
                                requestValidator.getCompteFacturier().getValeur(), mobileTransferRequest.getAccountNo(),
                                String.valueOf(Double.valueOf(mobileTransferRequest.getAmount()).intValue()),
                                String.valueOf(Double.valueOf(mobileTransferRequest.getAmount() - mobileTransferRequest.getCharge()).intValue()), String.valueOf(Double.valueOf(mobileTransferRequest.getCharge()).intValue() - new Double(taxe).intValue()),
                                "0", String.valueOf(new Double(taxe).intValue()), "W2B " + requestValidator.getOperateurs().getDesignationOperateur() + " " + mobileTransferRequest.getMmHeaderInfo().getRequestId() + " " + abonnements.getNumerotelephone() + " " + sdf3.format(Calendar.getInstance().getTime()), "", "");
                    } else {
                        return storedProcedureCaller.callFunction("walletToAccountTransfer", mobileTransferRequest, abonnements.getOperateur().getIdOperateur().toString());
                    }
                }
            };
            FutureTask<Object> futureTask = new FutureTask<>(valeur);
            Thread thread = new Thread(futureTask);
            thread.start();
            object = futureTask.get(delais_attente, TimeUnit.SECONDS);

            afr = storedProcedureCaller.getABIFunctionResponse(object);
            requestValidator.responseCodeMaker(afr, headerResponse);
            response.setCBAReferenceNo(afr.getNoOper());
            response.setExternalRefNo(headerResponse.getRequestId());

            //  response.setMmHeaderInfo(headerResponse);
            responsePersistor.persist(response, mobileTransferRequest, requestValidator.getOperateurs(), afr);
            return response;

        } catch (Exception e) {

            e.printStackTrace();

            headerResponse.setResponseCode("E11");
            headerResponse.setResponseMessage("Operation in timeout . Use transferStatusInquery to check status");
            System.out.println("erreur de communication avec le SIB  - connexion time out - une demande d'annulation sera envoyé par l'opérateur ");
            response.setCBAReferenceNo("");
            response.setExternalRefNo(headerResponse.getRequestId());
            System.out.println("probleme lors tu traitement normal ... nous n'avons recu la reponse en 30 seconds !");
            this.responsePersistor.persist(response, mobileTransferRequest, this.requestValidator.getOperateurs(), new ABIFunctionResponse());
            return response;
        }

    }

    public com.banktowallet.b2w.b2w.GetAccountBalanceResponse.Return getAccountBalance(final com.banktowallet.b2w.b2w.GetAccountBalance.AccountBalanceInquiryRequest accountBalanceInquiryRequest) {
        System.out.println("DEBUT DE DEMANDE DE SOLDE ");
        Object object;
        requestValidator.init(); //recup de tous les parametres necessaire pour le bon fonctionnement des services web
        com.banktowallet.b2w.b2w.GetAccountBalanceResponse.Return response = new com.banktowallet.b2w.b2w.GetAccountBalanceResponse.Return();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse.setResponseCode(" ");
            headerResponse.setOperatorCode(accountBalanceInquiryRequest.getMmHeaderInfo().getOperatorCode());
            headerResponse.setRequestId(accountBalanceInquiryRequest.getMmHeaderInfo().getRequestId());
            headerResponse.setAffiliateCode(accountBalanceInquiryRequest.getMmHeaderInfo().getAffiliateCode());
            response.setMmHeaderInfo(headerResponse);
            requestValidator.validate(headerResponse, accountBalanceInquiryRequest.getMmHeaderInfo(), accountBalanceInquiryRequest);

            if (!headerResponse.getResponseCode().isEmpty() && !headerResponse.getResponseCode().equals(" ")) {
                responsePersistor.persist(response, accountBalanceInquiryRequest, requestValidator.getOperateurs(), new ABIFunctionResponse());
                return response;
            }

            final Abonnements abonnements = requestValidator.getAbonnementsFacade().findByActifByAlias(accountBalanceInquiryRequest.getAccountAlias(), true);
            if (abonnements == null) {
                if (requestValidator.getErreurInvalidNoCompte() != null) {
                    headerResponse.setResponseCode(requestValidator.getErreurInvalidNoCompte().getCodevalue());
                    headerResponse.setResponseMessage(requestValidator.getErreurInvalidNoCompte().getCodedescription());
                    responsePersistor.persist(response, accountBalanceInquiryRequest, requestValidator.getOperateurs(), new ABIFunctionResponse());
                    return response;
                }
            }
            accountBalanceInquiryRequest.setAccountNo(abonnements.getCompte());

            if (requestValidator.getActivationT24() == null || !requestValidator.getActivationT24().getValeur().equals("OUI")) {
                //System.out.println("####requestValidator####");
                object = storedProcedureCaller.callFunction("getAccountBalance", accountBalanceInquiryRequest, abonnements.getOperateur().getIdOperateur().toString());
            } else {
                object = t24Handler.callT24(requestValidator.getPrefixeBalance().getValeur(), abonnements.getRacine(), abonnements.getCompte());
            }
//            Callable<Object> valeur = new Callable<Object>() {
//                @Override
//                public Object call() throws Exception {
//                    if (requestValidator.getActivationT24() == null || !requestValidator.getActivationT24().getValeur().equals("OUI")) {
//                        System.out.println("####requestValidator####");
//                        return storedProcedureCaller.callFunction("getAccountBalance", accountBalanceInquiryRequest, abonnements.getOperateur().getIdOperateur().toString());
//                    } else {
//                        return t24Handler.callT24(requestValidator.getPrefixeBalance().getValeur(), abonnements.getRacine(), abonnements.getCompte());
//                    }
//                }
//            };
//            FutureTask<Object> futureTask = new FutureTask<>(valeur);
//            Thread thread = new Thread(futureTask);
//            thread.start();
//            object = futureTask.get(45, TimeUnit.SECONDS);
            ABIFunctionResponse afr = storedProcedureCaller.getABIFunctionResponse(object);
            Map<String, String> compte;
            //Récupération du solde sous Orion
            if (afr.getStatut().equals("0") && !afr.getComptes().isEmpty()) {
                System.out.println("####SoldeD####");
                compte = afr.getComptes().get(0);
                response.setCurrentBalance(new Long(compte.get("SoldeD")));
                response.setAvailableBalance(new Long(compte.get("SoldeV")));
                System.out.println("####SoldeD#### " + new Double(compte.get("SoldeD")));
                System.out.println("SoldeV " + new BigDecimal(compte.get("SoldeV")));
                //Récupération du solde sous T24
            } else if (afr.getStatut().equals("0") && afr.getSolde().containsKey("Compte") && afr.getSolde().containsKey("Montant")) {
                compte = afr.getSolde();
                response.setCurrentBalance(new Long(compte.get("Montant")));
                response.setAvailableBalance(new Long(compte.get("Montant")));
                System.out.println("Montant " + new Long(compte.get("Montant")));
                // System.out.println("Montant "+new BigDecimal(compte.get("Montant")).intValue());
            }
            response.setCcy(requestValidator.getLibelleDevise().getValeur());
            response.setAccountType("C");
            response.setAccountAlias(accountBalanceInquiryRequest.getAccountAlias());
//¨Prélevement de la commission sur demande de solde            
            if (requestValidator.getTarificationBalance().getValeur().equals("TARIFICATION_AUTORISEE")) {
                TarifsProfilsOperateurs tarif = tarifsProfilsOperateursFacade.findTarif(abonnements, "SOLDE");
                System.out.println("Tarif : " + tarif.getTarif());
                PaymentServiceCaller.payForService(requestValidator.getOtherService().getValeur(), accountBalanceInquiryRequest, String.valueOf(tarif.getTarif()), requestValidator.getOperateurs().getIdOperateur().longValue());
            }
            requestValidator.responseCodeMaker(afr, headerResponse);
            //  response.setMmHeaderInfo(headerResponse);
            responsePersistor.persist(response, accountBalanceInquiryRequest, requestValidator.getOperateurs(), new ABIFunctionResponse());
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            headerResponse.setResponseCode("E04");
            headerResponse.setResponseMessage("Interface Error");
            responsePersistor.persist(response, accountBalanceInquiryRequest, requestValidator.getOperateurs(), new ABIFunctionResponse());
        } finally {
            System.out.println("FIN DE LA DEMANDE DE SOLDE");

            return response;
        }

    }

    public com.banktowallet.b2w.b2w.TransferStatusInquiryResponse.Return transferStatusInquiry(com.banktowallet.b2w.b2w.TransferStatusInquiry.TranRequestInfo tranRequestInfo) {
        Object object;
        requestValidator.init();
        ABIFunctionResponse afr;
        com.banktowallet.b2w.b2w.TransferStatusInquiryResponse.Return response = new com.banktowallet.b2w.b2w.TransferStatusInquiryResponse.Return();
        HeaderResponse headerResponse = new HeaderResponse();

        headerResponse.setResponseCode(" ");
        headerResponse.setOperatorCode(tranRequestInfo.getMmHeaderInfo().getOperatorCode());
        headerResponse.setRequestId(tranRequestInfo.getMmHeaderInfo().getRequestId());
        headerResponse.setAffiliateCode(tranRequestInfo.getMmHeaderInfo().getAffiliateCode());
        response.setMmHeaderInfo(headerResponse);

        requestValidator.validate(headerResponse, tranRequestInfo.getMmHeaderInfo(), tranRequestInfo);
        if (!headerResponse.getResponseCode().isEmpty() && !headerResponse.getResponseCode().equals(" ")) {
            responsePersistor.persist(response, tranRequestInfo, requestValidator.getOperateurs(), new ABIFunctionResponse());
            return response;
        }

       
            object = storedProcedureCaller.callFunction("transferStatusInquiry", tranRequestInfo, "REFREL", tranRequestInfo.getMmHeaderInfo().getRequestId(), requestValidator.getOperateurs().getIdOperateur().toString());
            afr = storedProcedureCaller.getABIFunctionResponse(object);

            if (afr == null) {
                System.out.println("**************attention pas de reponse du SIB ...  il y a une reponse dans **********");
                

            } else {
                requestValidator.responseCodeMaker(afr, headerResponse);
                response.setCBAReferenceNo(afr != null ? afr.getNoOper() : "");
                response.setExternalRefNo(headerResponse.getRequestId());
                try {
                    //persistance de la transaction de TSI
                    this.responsePersistor.persist(response, tranRequestInfo, this.requestValidator.getOperateurs(), afr);
                    //update de la transaction dont on avait pas eu le denouement avant 
                    this.responsePersistor.updateTransaction(response, tranRequestInfo, this.requestValidator.getOperateurs(), afr);
                } catch (Exception e) {
                    System.out.println("erreur lors de l'enregistrement du TSI dans la base oracle en base ...");
                }
            }

        
        return response;
    }

    public com.banktowallet.b2w.b2w.GetMiniStatementResponse.Return getMiniStatement(final com.banktowallet.b2w.b2w.GetMiniStatement.MiniStatementRequest miniStatementRequest) {
        //TODO implement this method
        // throw new UnsupportedOperationException("Not implemented yet.");
        Object object;
        requestValidator.init();
        com.banktowallet.b2w.b2w.GetMiniStatementResponse.Return response = new com.banktowallet.b2w.b2w.GetMiniStatementResponse.Return();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse.setResponseCode(" ");
            headerResponse.setOperatorCode(miniStatementRequest.getMmHeaderInfo().getOperatorCode());
            headerResponse.setRequestId(miniStatementRequest.getMmHeaderInfo().getRequestId());
            headerResponse.setAffiliateCode(miniStatementRequest.getMmHeaderInfo().getAffiliateCode());
            response.setMmHeaderInfo(headerResponse);
            requestValidator.validate(headerResponse, miniStatementRequest.getMmHeaderInfo(), miniStatementRequest);
            if (!headerResponse.getResponseCode().isEmpty() && !headerResponse.getResponseCode().equals(" ")) {
                responsePersistor.persist(response, miniStatementRequest, requestValidator.getOperateurs(), new ABIFunctionResponse());
                return response;
            }

            final Abonnements abonnements = requestValidator.getAbonnementsFacade().findByActifByAlias(miniStatementRequest.getAccountAlias(), true);
            if (abonnements == null) {
                if (requestValidator.getErreurInvalidNoCompte() != null) {
                    headerResponse.setResponseCode(requestValidator.getErreurInvalidNoCompte().getCodevalue());
                    headerResponse.setResponseMessage(requestValidator.getErreurInvalidNoCompte().getCodedescription());
                    responsePersistor.persist(response, miniStatementRequest, requestValidator.getOperateurs(), new ABIFunctionResponse());
                    return response;
                }
            }
            miniStatementRequest.setAccountNo(abonnements.getCompte());
            Callable<Object> valeur = new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    if (requestValidator.getActivationT24() == null || !requestValidator.getActivationT24().getValeur().equals("OUI")) {
                        return storedProcedureCaller.callFunction("getMiniStatement", miniStatementRequest, abonnements.getOperateur().getIdOperateur().toString());
                    } else {
                        return t24Handler.callT24(requestValidator.getPrefixeStatement().getValeur(), abonnements.getRacine(), abonnements.getCompte());
                    }
                }
            };

            FutureTask<Object> futureTask = new FutureTask<>(valeur);
            Thread thread = new Thread(futureTask);
            thread.start();
            object = futureTask.get(delais_attente, TimeUnit.SECONDS);
            ABIFunctionResponse afr = storedProcedureCaller.getABIFunctionResponse(object);

            requestValidator.responseCodeMaker(afr, headerResponse);

            //  response.setMmHeaderInfo(headerResponse);
            TransactionDetail td;
            Map<String, String> tr;
            if (afr.getStatut().equals("0")) {
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (int i = 0; i < afr.getTransactions().size(); i++) {
                    td = new TransactionDetail();
                    tr = afr.getTransactions().get(i);
                    td.setAmount(new BigDecimal(tr.get("amount")).doubleValue());
                    td.setCcy(tr.get("ccy"));
                    td.setNarration(tr.get("narration"));
                    td.setTranRefNo(tr.get("tranrefno"));
                    td.setCrDr(tr.get("crdr"));
                    td.setTranType(tr.get("trantype"));
                    Date date;
                    try {
                        //la date et l'heure se lisent dans la description de l'opération
                        date = sdf2.parse(tr.get("trandate"));
                        GregorianCalendar gc = new GregorianCalendar();
                        gc.setTime(date);
                        XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                        td.setTranDate(xgc);
                    } catch (ParseException | DatatypeConfigurationException ex) {
                        ex.printStackTrace();
                    }

                    response.getTransactionList().add(td);
                }
            }
//        response.setMmHeaderInfo(headerResponse);

//¨Prélevement de la commission sur demande de mini-relevé            
            if (requestValidator.getTarificationMiniStement().getValeur().equals("TARIFICATION_AUTORISEE")) {
                TarifsProfilsOperateurs tarif = tarifsProfilsOperateursFacade.findTarif(abonnements, "MINI-RELEVE");
                PaymentServiceCaller.payForService(requestValidator.getOtherService().getValeur(), miniStatementRequest, String.valueOf(tarif.getTarif()), requestValidator.getOperateurs().getIdOperateur().longValue());
            }
            responsePersistor.persist(response, miniStatementRequest, requestValidator.getOperateurs(), new ABIFunctionResponse());
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            headerResponse.setResponseCode("E04");
            headerResponse.setResponseMessage("Interface Error");
            responsePersistor.persist(response, miniStatementRequest, requestValidator.getOperateurs(), new ABIFunctionResponse());
        } finally {
            return response;
        }

    }

}
