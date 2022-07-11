
package com.xnett.fasyl.deploy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour FundTransferMobileToAccountInquiryResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="FundTransferMobileToAccountInquiryResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://deploy.fasyl.xnett.com/}fundTransferMobileToAccountInquiryXResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FundTransferMobileToAccountInquiryResponse", propOrder = {
    "_return"
})
public class FundTransferMobileToAccountInquiryResponse {

    @XmlElement(name = "return")
    protected FundTransferMobileToAccountInquiryXResponse _return;

    /**
     * Obtient la valeur de la propriété return.
     * 
     * @return
     *     possible object is
     *     {@link FundTransferMobileToAccountInquiryXResponse }
     *     
     */
    public FundTransferMobileToAccountInquiryXResponse getReturn() {
        return _return;
    }

    /**
     * Définit la valeur de la propriété return.
     * 
     * @param value
     *     allowed object is
     *     {@link FundTransferMobileToAccountInquiryXResponse }
     *     
     */
    public void setReturn(FundTransferMobileToAccountInquiryXResponse value) {
        this._return = value;
    }

}
