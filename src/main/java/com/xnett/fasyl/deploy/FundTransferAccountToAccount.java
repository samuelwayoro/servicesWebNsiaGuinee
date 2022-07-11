
package com.xnett.fasyl.deploy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour FundTransferAccountToAccount complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FundTransferAccountToAccount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dBranchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dFName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dLName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cBranchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cFName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cLName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtRefID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mydate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsSourceMsisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtExternal1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtExternal2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FundTransferAccountToAccount", propOrder = {
    "dBankName",
    "dBranchCode",
    "dAccountNumber",
    "dfName",
    "dlName",
    "cBankName",
    "cBranchCode",
    "cAccountNumber",
    "cfName",
    "clName",
    "txtAmount",
    "txtCurrency",
    "txtRefID",
    "mydate",
    "txtCountryCode",
    "smsSourceMsisdn",
    "txtExternal1",
    "txtExternal2"
})
public class FundTransferAccountToAccount {

    protected String dBankName;
    protected String dBranchCode;
    protected String dAccountNumber;
    @XmlElement(name = "dFName")
    protected String dfName;
    @XmlElement(name = "dLName")
    protected String dlName;
    protected String cBankName;
    protected String cBranchCode;
    protected String cAccountNumber;
    @XmlElement(name = "cFName")
    protected String cfName;
    @XmlElement(name = "cLName")
    protected String clName;
    protected String txtAmount;
    protected String txtCurrency;
    protected String txtRefID;
    protected String mydate;
    protected String txtCountryCode;
    protected String smsSourceMsisdn;
    protected String txtExternal1;
    protected String txtExternal2;

    /**
     * Obtient la valeur de la propriété dBankName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDBankName() {
        return dBankName;
    }

    /**
     * Définit la valeur de la propriété dBankName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDBankName(String value) {
        this.dBankName = value;
    }

    /**
     * Obtient la valeur de la propriété dBranchCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDBranchCode() {
        return dBranchCode;
    }

    /**
     * Définit la valeur de la propriété dBranchCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDBranchCode(String value) {
        this.dBranchCode = value;
    }

    /**
     * Obtient la valeur de la propriété dAccountNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDAccountNumber() {
        return dAccountNumber;
    }

    /**
     * Définit la valeur de la propriété dAccountNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDAccountNumber(String value) {
        this.dAccountNumber = value;
    }

    /**
     * Obtient la valeur de la propriété dfName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDFName() {
        return dfName;
    }

    /**
     * Définit la valeur de la propriété dfName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDFName(String value) {
        this.dfName = value;
    }

    /**
     * Obtient la valeur de la propriété dlName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDLName() {
        return dlName;
    }

    /**
     * Définit la valeur de la propriété dlName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDLName(String value) {
        this.dlName = value;
    }

    /**
     * Obtient la valeur de la propriété cBankName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCBankName() {
        return cBankName;
    }

    /**
     * Définit la valeur de la propriété cBankName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCBankName(String value) {
        this.cBankName = value;
    }

    /**
     * Obtient la valeur de la propriété cBranchCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCBranchCode() {
        return cBranchCode;
    }

    /**
     * Définit la valeur de la propriété cBranchCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCBranchCode(String value) {
        this.cBranchCode = value;
    }

    /**
     * Obtient la valeur de la propriété cAccountNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCAccountNumber() {
        return cAccountNumber;
    }

    /**
     * Définit la valeur de la propriété cAccountNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCAccountNumber(String value) {
        this.cAccountNumber = value;
    }

    /**
     * Obtient la valeur de la propriété cfName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCFName() {
        return cfName;
    }

    /**
     * Définit la valeur de la propriété cfName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCFName(String value) {
        this.cfName = value;
    }

    /**
     * Obtient la valeur de la propriété clName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLName() {
        return clName;
    }

    /**
     * Définit la valeur de la propriété clName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLName(String value) {
        this.clName = value;
    }

    /**
     * Obtient la valeur de la propriété txtAmount.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtAmount() {
        return txtAmount;
    }

    /**
     * Définit la valeur de la propriété txtAmount.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtAmount(String value) {
        this.txtAmount = value;
    }

    /**
     * Obtient la valeur de la propriété txtCurrency.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtCurrency() {
        return txtCurrency;
    }

    /**
     * Définit la valeur de la propriété txtCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtCurrency(String value) {
        this.txtCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété txtRefID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtRefID() {
        return txtRefID;
    }

    /**
     * Définit la valeur de la propriété txtRefID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtRefID(String value) {
        this.txtRefID = value;
    }

    /**
     * Obtient la valeur de la propriété mydate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMydate() {
        return mydate;
    }

    /**
     * Définit la valeur de la propriété mydate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMydate(String value) {
        this.mydate = value;
    }

    /**
     * Obtient la valeur de la propriété txtCountryCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtCountryCode() {
        return txtCountryCode;
    }

    /**
     * Définit la valeur de la propriété txtCountryCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtCountryCode(String value) {
        this.txtCountryCode = value;
    }

    /**
     * Obtient la valeur de la propriété smsSourceMsisdn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsSourceMsisdn() {
        return smsSourceMsisdn;
    }

    /**
     * Définit la valeur de la propriété smsSourceMsisdn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsSourceMsisdn(String value) {
        this.smsSourceMsisdn = value;
    }

    /**
     * Obtient la valeur de la propriété txtExternal1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtExternal1() {
        return txtExternal1;
    }

    /**
     * Définit la valeur de la propriété txtExternal1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtExternal1(String value) {
        this.txtExternal1 = value;
    }

    /**
     * Obtient la valeur de la propriété txtExternal2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtExternal2() {
        return txtExternal2;
    }

    /**
     * Définit la valeur de la propriété txtExternal2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtExternal2(String value) {
        this.txtExternal2 = value;
    }

}
