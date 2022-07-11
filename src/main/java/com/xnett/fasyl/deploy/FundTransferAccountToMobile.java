
package com.xnett.fasyl.deploy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour fundTransferAccountToMobile complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="fundTransferAccountToMobile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="txtBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtBranchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtBFName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtBLName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtRefID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mydate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtAuthNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "fundTransferAccountToMobile", propOrder = {
    "txtBankName",
    "txtBranchCode",
    "txtAccountNumber",
    "txtBFName",
    "txtBLName",
    "txtAmount",
    "txtCurrency",
    "txtRefID",
    "mydate",
    "txtCountryCode",
    "txtAuthNum",
    "txtExternal1",
    "txtExternal2"
})
public class FundTransferAccountToMobile {

    protected String txtBankName;
    protected String txtBranchCode;
    protected String txtAccountNumber;
    protected String txtBFName;
    protected String txtBLName;
    protected String txtAmount;
    protected String txtCurrency;
    protected String txtRefID;
    protected String mydate;
    protected String txtCountryCode;
    protected String txtAuthNum;
    protected String txtExternal1;
    protected String txtExternal2;

    /**
     * Obtient la valeur de la propriété txtBankName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtBankName() {
        return txtBankName;
    }

    /**
     * Définit la valeur de la propriété txtBankName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtBankName(String value) {
        this.txtBankName = value;
    }

    /**
     * Obtient la valeur de la propriété txtBranchCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtBranchCode() {
        return txtBranchCode;
    }

    /**
     * Définit la valeur de la propriété txtBranchCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtBranchCode(String value) {
        this.txtBranchCode = value;
    }

    /**
     * Obtient la valeur de la propriété txtAccountNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtAccountNumber() {
        return txtAccountNumber;
    }

    /**
     * Définit la valeur de la propriété txtAccountNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtAccountNumber(String value) {
        this.txtAccountNumber = value;
    }

    /**
     * Obtient la valeur de la propriété txtBFName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtBFName() {
        return txtBFName;
    }

    /**
     * Définit la valeur de la propriété txtBFName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtBFName(String value) {
        this.txtBFName = value;
    }

    /**
     * Obtient la valeur de la propriété txtBLName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtBLName() {
        return txtBLName;
    }

    /**
     * Définit la valeur de la propriété txtBLName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtBLName(String value) {
        this.txtBLName = value;
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
     * Obtient la valeur de la propriété txtAuthNum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtAuthNum() {
        return txtAuthNum;
    }

    /**
     * Définit la valeur de la propriété txtAuthNum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtAuthNum(String value) {
        this.txtAuthNum = value;
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
