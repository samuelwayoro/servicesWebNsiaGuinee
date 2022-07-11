/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "COMMISSIONS")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Commissions.findAll", query = "SELECT c FROM Commissions c")
    ,
    @NamedQuery(name = "Commissions.findByIdPalier", query = "SELECT c FROM Commissions c WHERE c.idPalier = :idPalier")
    ,
    @NamedQuery(name = "Commissions.findByMinimum", query = "SELECT c FROM Commissions c WHERE c.minimum = :minimum")
    ,
    @NamedQuery(name = "Commissions.findByMaximum", query = "SELECT c FROM Commissions c WHERE c.maximum = :maximum")
    ,
    @NamedQuery(name = "Commissions.findByTarif", query = "SELECT c FROM Commissions c WHERE c.tarif = :tarif")
    ,
    @NamedQuery(name = "Commissions.findByTaux", query = "SELECT c FROM Commissions c WHERE c.taux = :taux")
    ,
    @NamedQuery(name = "Commissions.findB2WMontantCommission", query = "SELECT c FROM Commissions c WHERE c.sens = :sens ")
    ,
    @NamedQuery(name = "Commissions.findCommissionByMontantBrut", query = "SELECT c FROM Commissions c WHERE (c.minimum+c.tarif) <= :tarif AND (c.maximum+c.tarif)>= :tarif and c.sens= :sens")
    ,
    @NamedQuery(name = "Commissions.findCommissionByMontantNet", query = "SELECT c FROM Commissions c WHERE c.minimum <= :tarif AND c.maximum >= :tarif and c.sens= :sens and c.profils.idProfils = :profil and c.operateurs.idOperateur = :operateur")})
public class Commissions implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PALIER")
    private BigDecimal idPalier;
    @Column(name = "MINIMUM")
    private BigInteger minimum;
    @Column(name = "MAXIMUM")
    private BigInteger maximum;
    @Column(name = "TARIF")
    private BigInteger tarif;
    @Column(name = "TAUX")
    private BigInteger taux;
    @Size(max = 5)
    @Column(name = "SENS")
    private String sens;
    @JoinColumn(name = "PROFILS", referencedColumnName = "ID_PROFILS")
    @ManyToOne
    private ProfilsClients profils;
    @JoinColumn(name = "OPERATEURS", referencedColumnName = "ID_OPERATEUR")
    @ManyToOne
    private Operateurs operateurs;

    public Commissions() {
    }

    public Commissions(BigDecimal idPalier) {
        this.idPalier = idPalier;
    }

    public BigDecimal getIdPalier() {
        return idPalier;
    }

    public void setIdPalier(BigDecimal idPalier) {
        this.idPalier = idPalier;
    }

    public BigInteger getMinimum() {
        return minimum;
    }

    public void setMinimum(BigInteger minimum) {
        this.minimum = minimum;
    }

    public BigInteger getMaximum() {
        return maximum;
    }

    public void setMaximum(BigInteger maximum) {
        this.maximum = maximum;
    }

    public BigInteger getTarif() {
        return tarif;
    }

    public void setTarif(BigInteger tarif) {
        this.tarif = tarif;
    }

    public BigInteger getTaux() {
        return taux;
    }

    public void setTaux(BigInteger taux) {
        this.taux = taux;
    }

    public String getSens() {
        return sens;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPalier != null ? idPalier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commissions)) {
            return false;
        }
        Commissions other = (Commissions) object;
        if ((this.idPalier == null && other.idPalier != null) || (this.idPalier != null && !this.idPalier.equals(other.idPalier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sbs.b2wservice.entities.Commissions[ idPalier=" + idPalier + " ]";
    }

}
