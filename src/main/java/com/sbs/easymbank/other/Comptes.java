package com.sbs.easymbank.other;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for comptes complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="comptes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coddci" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="libNcg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ncg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comptes", propOrder = {
    "agence",
    "coddci",
    "expl",
    "libNcg",
    "ncg",
    "numero"
})
public class Comptes {

    protected String agence;
    protected String coddci;
    protected String expl;
    protected String libNcg;
    protected String ncg;
    protected String numero;

    /**
     * Gets the value of the agence property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getAgence() {
        return agence;
    }

    /**
     * Sets the value of the agence property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setAgence(String value) {
        this.agence = value;
    }

    /**
     * Gets the value of the coddci property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCoddci() {
        return coddci;
    }

    /**
     * Sets the value of the coddci property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCoddci(String value) {
        this.coddci = value;
    }

    /**
     * Gets the value of the expl property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getExpl() {
        return expl;
    }

    /**
     * Sets the value of the expl property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setExpl(String value) {
        this.expl = value;
    }

    /**
     * Gets the value of the libNcg property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLibNcg() {
        return libNcg;
    }

    /**
     * Sets the value of the libNcg property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setLibNcg(String value) {
        this.libNcg = value;
    }

    /**
     * Gets the value of the ncg property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNcg() {
        return ncg;
    }

    /**
     * Sets the value of the ncg property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNcg(String value) {
        this.ncg = value;
    }

    /**
     * Gets the value of the numero property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNumero(String value) {
        this.numero = value;
    }

}
