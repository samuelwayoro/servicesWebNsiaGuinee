/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import com.banktowallet.b2w.b2w.*;
import com.xnett.fasyl.deploy.FundTransferMobileToAccountXResponse;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Cacheable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author SOCITECH-
 */
@Entity
@Table(name = "TRANSACTIONS")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t")
    ,
    @NamedQuery(name = "Transactions.findByIdtransactions", query = "SELECT t FROM Transactions t WHERE t.idtransactions = :idtransactions")
    ,
    @NamedQuery(name = "Transactions.findByAccountalias", query = "SELECT t FROM Transactions t WHERE t.accountalias = :accountalias")
    ,
    @NamedQuery(name = "Transactions.findByAccountclass", query = "SELECT t FROM Transactions t WHERE t.accountclass = :accountclass")
    ,
    @NamedQuery(name = "Transactions.findByAccountname", query = "SELECT t FROM Transactions t WHERE t.accountname = :accountname")
    ,
    @NamedQuery(name = "Transactions.findByAccountno", query = "SELECT t FROM Transactions t WHERE t.accountno = :accountno")
    ,
    @NamedQuery(name = "Transactions.findByAccountstatus", query = "SELECT t FROM Transactions t WHERE t.accountstatus = :accountstatus")
    ,
    @NamedQuery(name = "Transactions.findByAccounttype", query = "SELECT t FROM Transactions t WHERE t.accounttype = :accounttype")
    ,
    @NamedQuery(name = "Transactions.findByAffiliatecode", query = "SELECT t FROM Transactions t WHERE t.affiliatecode = :affiliatecode")
    ,
    @NamedQuery(name = "Transactions.findByAmount", query = "SELECT t FROM Transactions t WHERE t.amount = :amount")
    ,
    @NamedQuery(name = "Transactions.findByAvailablebalance", query = "SELECT t FROM Transactions t WHERE t.availablebalance = :availablebalance")
    ,
    @NamedQuery(name = "Transactions.findByBranchcode", query = "SELECT t FROM Transactions t WHERE t.branchcode = :branchcode")
    ,
    @NamedQuery(name = "Transactions.findByCbareferenceno", query = "SELECT t FROM Transactions t WHERE t.cbareferenceno = :cbareferenceno")
    ,
    @NamedQuery(name = "Transactions.findByCcy", query = "SELECT t FROM Transactions t WHERE t.ccy = :ccy")
    ,
    @NamedQuery(name = "Transactions.findByCharge", query = "SELECT t FROM Transactions t WHERE t.charge = :charge")
    ,
    @NamedQuery(name = "Transactions.findByCrdr", query = "SELECT t FROM Transactions t WHERE t.crdr = :crdr")
    ,
    @NamedQuery(name = "Transactions.findByCurrentbalance", query = "SELECT t FROM Transactions t WHERE t.currentbalance = :currentbalance")
    ,
    @NamedQuery(name = "Transactions.findByExternalrefno", query = "SELECT t FROM Transactions t WHERE t.externalrefno = :externalrefno")
    ,
    @NamedQuery(name = "Transactions.findByMobilealias", query = "SELECT t FROM Transactions t WHERE t.mobilealias = :mobilealias")
    ,
    @NamedQuery(name = "Transactions.findByMobilename", query = "SELECT t FROM Transactions t WHERE t.mobilename = :mobilename")
    ,
    @NamedQuery(name = "Transactions.findByMobileno", query = "SELECT t FROM Transactions t WHERE t.mobileno = :mobileno")
    ,
    @NamedQuery(name = "Transactions.findByNarration", query = "SELECT t FROM Transactions t WHERE t.narration = :narration")
    ,
    @NamedQuery(name = "Transactions.findByOperatorcode", query = "SELECT t FROM Transactions t WHERE t.operatorcode = :operatorcode")
    ,
    @NamedQuery(name = "Transactions.findByOrigine", query = "SELECT t FROM Transactions t WHERE t.origine = :origine")
    ,
    @NamedQuery(name = "Transactions.findByReason", query = "SELECT t FROM Transactions t WHERE t.reason = :reason")
    ,
    @NamedQuery(name = "Transactions.findByReconcilie", query = "SELECT t FROM Transactions t WHERE t.reconcilie = :reconcilie")
    ,
    @NamedQuery(name = "Transactions.findByRequestid", query = "SELECT t FROM Transactions t WHERE t.requestid = :requestid")
    ,
    @NamedQuery(name = "Transactions.findByRequesttoken", query = "SELECT t FROM Transactions t WHERE t.requesttoken = :requesttoken")
    ,
    @NamedQuery(name = "Transactions.findByRequesttype", query = "SELECT t FROM Transactions t WHERE t.requesttype = :requesttype")
    ,
    @NamedQuery(name = "Transactions.findByResponsecode", query = "SELECT t FROM Transactions t WHERE t.responsecode = :responsecode")
    ,
    @NamedQuery(name = "Transactions.findByResponsemessage", query = "SELECT t FROM Transactions t WHERE t.responsemessage = :responsemessage")
    ,
    @NamedQuery(name = "Transactions.findByTrandate", query = "SELECT t FROM Transactions t WHERE t.trandate = :trandate")
    ,
    @NamedQuery(name = "Transactions.findCountByAliasAndDate", query = "SELECT count(t.idtransactions) FROM Transactions t WHERE SUBSTRING(t.trandate,1,10) = :trandate and t.accountalias = :accountalias and t.responsecode = :responsecode and t.requesttype in :requesttype")
    ,
    @NamedQuery(name = "Transactions.findCountByAliasAndDateOnMounth", query = "SELECT count(t.idtransactions) FROM Transactions t WHERE SUBSTRING(t.trandate,1,7) = :trandate and t.accountalias = :accountalias and t.responsecode = :responsecode and t.requesttype in :requesttype")
    ,
    @NamedQuery(name = "Transactions.findSumByAliasAndDate", query = "SELECT t FROM Transactions t WHERE SUBSTRING(t.trandate,1,10) = :trandate and t.accountalias = :accountalias and t.responsecode = :responsecode and t.requesttype in :requesttype")
    ,
     @NamedQuery(name = "Transactions.findMounthlySumByAliasAndDate", query = "SELECT t FROM Transactions t WHERE SUBSTRING(t.trandate,1,7) = :trandate and t.accountalias = :accountalias and t.responsecode = :responsecode and t.requesttype in :requesttype")
    ,
    @NamedQuery(name = "Transactions.findByTranrefno", query = "SELECT t FROM Transactions t WHERE t.tranrefno = :tranrefno")
    ,
    @NamedQuery(name = "Transactions.findByTransferdescription", query = "SELECT t FROM Transactions t WHERE t.transferdescription = :transferdescription")
    ,
    @NamedQuery(name = "Transactions.findByTrantype", query = "SELECT t FROM Transactions t WHERE t.trantype = :trantype")
    ,
    @NamedQuery(name = "Transactions.findByUdf1", query = "SELECT t FROM Transactions t WHERE t.udf1 = :udf1")
    ,
    @NamedQuery(name = "Transactions.findByUdf2", query = "SELECT t FROM Transactions t WHERE t.udf2 = :udf2")
    ,
    @NamedQuery(name = "Transactions.findByUdf3", query = "SELECT t FROM Transactions t WHERE t.udf3 = :udf3")})
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "TRANSACTIONS_SEQ", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TRANSACTIONS_SEQ")

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTRANSACTIONS")
    private Long idtransactions;
    @Size(max = 255)
    @Column(name = "ACCOUNTALIAS")
    private String accountalias;
    @Size(max = 255)
    @Column(name = "ACCOUNTCLASS")
    private String accountclass;
    @Size(max = 255)
    @Column(name = "ACCOUNTNAME")
    private String accountname;
    @Size(max = 255)
    @Column(name = "ACCOUNTNO")
    private String accountno;
    @Size(max = 255)
    @Column(name = "ACCOUNTSTATUS")
    private String accountstatus;
    @Size(max = 255)
    @Column(name = "ACCOUNTTYPE")
    private String accounttype;
    @Size(max = 255)
    @Column(name = "AFFILIATECODE")
    private String affiliatecode;
    @Size(max = 255)
    @Column(name = "AMOUNT")
    private String amount;
    @Size(max = 255)
    @Column(name = "AVAILABLEBALANCE")
    private String availablebalance;
    @Size(max = 255)
    @Column(name = "BRANCHCODE")
    private String branchcode;
    @Size(max = 255)
    @Column(name = "CBAREFERENCENO")
    private String cbareferenceno;
    @Size(max = 255)
    @Column(name = "CCY")
    private String ccy;
    @Size(max = 255)
    @Column(name = "CHARGE")
    private String charge;
    @Size(max = 255)
    @Column(name = "CRDR")
    private String crdr;
    @Size(max = 255)
    @Column(name = "CURRENTBALANCE")
    private String currentbalance;
    @Size(max = 255)
    @Column(name = "EXTERNALREFNO")
    private String externalrefno;
    @Size(max = 255)
    @Column(name = "MOBILEALIAS")
    private String mobilealias;
    @Size(max = 255)
    @Column(name = "MOBILENAME")
    private String mobilename;
    @Size(max = 255)
    @Column(name = "MOBILENO")
    private String mobileno;
    @Size(max = 255)
    @Column(name = "NARRATION")
    private String narration;
    @Size(max = 255)
    @Column(name = "OPERATORCODE")
    private String operatorcode;
    @Size(max = 255)
    @Column(name = "ORIGINE")
    private String origine;
    @Size(max = 255)
    @Column(name = "REASON")
    private String reason;
    @Column(name = "RECONCILIE")
    private Boolean reconcilie;
    @Size(max = 255)
    @Column(name = "REQUESTID")
    private String requestid;
    @Size(max = 255)
    @Column(name = "REQUESTTOKEN")
    private String requesttoken;
    @Size(max = 255)
    @Column(name = "REQUESTTYPE")
    private String requesttype;
    @Size(max = 255)
    @Column(name = "RESPONSECODE")
    private String responsecode;
    @Size(max = 255)
    @Column(name = "RESPONSEMESSAGE")
    private String responsemessage;
    @Size(max = 255)
    @Column(name = "TRANDATE")
    private String trandate;
    @Size(max = 255)
    @Column(name = "TRANREFNO")
    private String tranrefno;
    @Size(max = 255)
    @Column(name = "TRANSFERDESCRIPTION")
    private String transferdescription;
    @Size(max = 255)
    @Column(name = "TRANTYPE")
    private String trantype;
    @Size(max = 255)
    @Column(name = "UDF1")
    private String udf1;
    @Size(max = 255)
    @Column(name = "UDF2")
    private String udf2;
    @Size(max = 255)
    @Column(name = "UDF3")
    private String udf3;
    @Column(name = "COMMISSION")
    private BigInteger commission;
    @JoinColumn(name = "OPERATEURS", referencedColumnName = "ID_OPERATEUR")
    @ManyToOne
    private Operateurs operateurs;

    public Transactions() {
    }

    public Transactions(GetAccountBalanceResponse.Return retour) {
        this.accountalias = retour.getAccountAlias();
        this.requestid = retour.getMmHeaderInfo().getRequestId();
        this.responsecode = retour.getMmHeaderInfo().getResponseCode();
        //this.origine=tr.getOrigine();
        //this.reconcilie=;
        // this.idtransactions=tr.getIdtransactions();
        //this.trandate=tr.getTrandate();
        this.requesttype = "balance";

        this.accountname = retour.getAccountName();
        this.accountclass = retour.getAccountClass();

        this.accountno = retour.getAccountNo();
        this.accountstatus = retour.getAccountStatus();
        this.accounttype = retour.getAccountType();
        this.affiliatecode = retour.getMmHeaderInfo().getAffiliateCode();
        this.availablebalance = String.valueOf(retour.getAvailableBalance());
        this.branchcode = retour.getBranchCode();
        // this.cbareferenceno=tr.getCbareferenceno();
        this.ccy = retour.getCcy();
        //  this.charge=tr.getCharge();
        //  this.crdr=tr.getCrdr();
        this.currentbalance = String.valueOf(retour.getCurrentBalance());
        // this.externalrefno=tr.getExternalrefno();

//        this.mobilealias=tr.getMobilealias();
//        this.mobilename=tr.getMobilename();
//        this.mobileno=tr.getMobileno();
//        this.narration=tr.getNarration();
        this.operatorcode = retour.getMmHeaderInfo().getOperatorCode();

        // this.reason=tr.getReason();
        this.responsemessage = retour.getMmHeaderInfo().getResponseMessage();
        // this.tranrefno=tr.getTranrefno();
        // this.transferdescription=tr.getTransferdescription();
        // this.trantype=tr.getTrantype();
        this.udf1 = retour.getUdf1();
        this.udf2 = retour.getUdf2();
        this.udf3 = retour.getUdf3();

    }

    public Transactions(WalletToAccountTransferResponse.Return retour) {

        this.requestid = retour.getMmHeaderInfo().getRequestId();
        this.responsecode = retour.getMmHeaderInfo().getResponseCode();

        this.requesttype = "wallet2bank";

        this.affiliatecode = retour.getMmHeaderInfo().getAffiliateCode();

        this.externalrefno = retour.getExternalRefNo();

        this.operatorcode = retour.getMmHeaderInfo().getOperatorCode();

        // this.reason=tr.getReason();
        this.responsemessage = retour.getMmHeaderInfo().getResponseMessage();
        this.cbareferenceno = retour.getCBAReferenceNo();

    }

    public Transactions(AccountToWalletTransferResponse.Return retour) {

        this.requestid = retour.getMmHeaderInfo().getRequestId();
        this.responsecode = retour.getMmHeaderInfo().getResponseCode();

        this.requesttype = "bank2wallet";

        this.affiliatecode = retour.getMmHeaderInfo().getAffiliateCode();

        this.externalrefno = retour.getExternalRefNo();

        this.operatorcode = retour.getMmHeaderInfo().getOperatorCode();

        // this.reason=tr.getReason();
        this.responsemessage = retour.getMmHeaderInfo().getResponseMessage();
        this.cbareferenceno = retour.getCBAReferenceNo();

    }

    public Transactions(String id, String requesttype, String responseCode, String alias, String operatorCode, String affiliateCode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.requestid = id;

        this.requesttype = requesttype;
        this.accountalias = alias;

        this.affiliatecode = affiliateCode;
        this.trandate = sdf.format(Calendar.getInstance().getTime());

        this.operatorcode = operatorCode;

        // this.reason=tr.getReason();
    }

    public Transactions(GetMiniStatementResponse.Return retour) {

        this.requestid = retour.getMmHeaderInfo().getRequestId();
        this.responsecode = retour.getMmHeaderInfo().getResponseCode();

        this.requesttype = "statement";

        this.affiliatecode = retour.getMmHeaderInfo().getAffiliateCode();

        //  this.externalrefno=retour.getMmHeaderInfo().;
        this.operatorcode = retour.getMmHeaderInfo().getOperatorCode();

        // this.reason=tr.getReason();
        this.responsemessage = retour.getMmHeaderInfo().getResponseMessage();
        // this.cbareferenceno=retour.getCBAReferenceNo();
    }

    public Transactions(CancelTransferResponse.Return retour, CancelTransfer.TranRequestInfo requete) {
        this.requestid = retour.getMmHeaderInfo().getRequestId();
        this.responsecode = retour.getMmHeaderInfo().getResponseCode();

        this.requesttype = "canceltran";

        this.affiliatecode = retour.getMmHeaderInfo().getAffiliateCode();
        //la reference bancaire de l'opération à annuler
        this.externalrefno = requete.getExternalRefNo();

        this.operatorcode = retour.getMmHeaderInfo().getOperatorCode();

        // this.reason=tr.getReason();
        this.responsemessage = retour.getMmHeaderInfo().getResponseMessage();
        this.cbareferenceno = retour.getCBAReferenceNo();
    }

    public Transactions(TransferStatusInquiryResponse.Return retour) {
        this.requestid = retour.getMmHeaderInfo().getRequestId();
        this.responsecode = retour.getMmHeaderInfo().getResponseCode();
        this.requesttype = "transferStatusInquiry";
        this.affiliatecode = retour.getMmHeaderInfo().getAffiliateCode();
        this.externalrefno = retour.getExternalRefNo();
        this.operatorcode = retour.getMmHeaderInfo().getOperatorCode();
        this.responsemessage = retour.getMmHeaderInfo().getResponseMessage();
        this.cbareferenceno = retour.getCBAReferenceNo();
    }

    public Transactions(Long idtransactions) {
        this.idtransactions = idtransactions;
    }

    public Long getIdtransactions() {
        return idtransactions;
    }

    public void setIdtransactions(Long idtransactions) {
        this.idtransactions = idtransactions;
    }

    public String getAccountalias() {
        return accountalias;
    }

    public void setAccountalias(String accountalias) {
        this.accountalias = accountalias;
    }

    public String getAccountclass() {
        return accountclass;
    }

    public void setAccountclass(String accountclass) {
        this.accountclass = accountclass;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getAccountstatus() {
        return accountstatus;
    }

    public void setAccountstatus(String accountstatus) {
        this.accountstatus = accountstatus;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getAffiliatecode() {
        return affiliatecode;
    }

    public void setAffiliatecode(String affiliatecode) {
        this.affiliatecode = affiliatecode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAvailablebalance() {
        return availablebalance;
    }

    public void setAvailablebalance(String availablebalance) {
        this.availablebalance = availablebalance;
    }

    public String getBranchcode() {
        return branchcode;
    }

    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    public String getCbareferenceno() {
        return cbareferenceno;
    }

    public void setCbareferenceno(String cbareferenceno) {
        this.cbareferenceno = cbareferenceno;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getCrdr() {
        return crdr;
    }

    public void setCrdr(String crdr) {
        this.crdr = crdr;
    }

    public String getCurrentbalance() {
        return currentbalance;
    }

    public void setCurrentbalance(String currentbalance) {
        this.currentbalance = currentbalance;
    }

    public String getExternalrefno() {
        return externalrefno;
    }

    public void setExternalrefno(String externalrefno) {
        this.externalrefno = externalrefno;
    }

    public String getMobilealias() {
        return mobilealias;
    }

    public void setMobilealias(String mobilealias) {
        this.mobilealias = mobilealias;
    }

    public String getMobilename() {
        return mobilename;
    }

    public void setMobilename(String mobilename) {
        this.mobilename = mobilename;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getOperatorcode() {
        return operatorcode;
    }

    public void setOperatorcode(String operatorcode) {
        this.operatorcode = operatorcode;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getReconcilie() {
        return reconcilie;
    }

    public void setReconcilie(Boolean reconcilie) {
        this.reconcilie = reconcilie;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getRequesttoken() {
        return requesttoken;
    }

    public void setRequesttoken(String requesttoken) {
        this.requesttoken = requesttoken;
    }

    public String getRequesttype() {
        return requesttype;
    }

    public void setRequesttype(String requesttype) {
        this.requesttype = requesttype;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }

    public String getTrandate() {
        return trandate;
    }

    public void setTrandate(String trandate) {
        this.trandate = trandate;
    }

    public String getTranrefno() {
        return tranrefno;
    }

    public void setTranrefno(String tranrefno) {
        this.tranrefno = tranrefno;
    }

    public String getTransferdescription() {
        return transferdescription;
    }

    public void setTransferdescription(String transferdescription) {
        this.transferdescription = transferdescription;
    }

    public String getTrantype() {
        return trantype;
    }

    public void setTrantype(String trantype) {
        this.trantype = trantype;
    }

    public String getUdf1() {
        return udf1;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public BigInteger getCommission() {
        return commission;
    }

    public void setCommission(BigInteger commission) {
        this.commission = commission;
    }

    public Operateurs getOperateurs() {
        return operateurs;
    }

    public void setOperateurs(Operateurs operateurs) {
        this.operateurs = operateurs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtransactions != null ? idtransactions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.idtransactions == null && other.idtransactions != null) || (this.idtransactions != null && !this.idtransactions.equals(other.idtransactions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sbs.b2wservice.entities.Transactions[ idtransactions=" + idtransactions + " ]";
    }

}
