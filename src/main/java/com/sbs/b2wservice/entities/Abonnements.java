/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SOCITECH-
 */
@Entity
@Table(name = "ABONNEMENTS")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Abonnements.findAll", query = "SELECT a FROM Abonnements a")
    ,
    @NamedQuery(name = "Abonnements.findByIdabonnements", query = "SELECT a FROM Abonnements a WHERE a.idabonnements = :idabonnements")
    ,
    @NamedQuery(name = "Abonnements.findByActif", query = "SELECT a FROM Abonnements a WHERE a.actif = :actif")
    ,
    @NamedQuery(name = "Abonnements.findByActivation", query = "SELECT a FROM Abonnements a WHERE a.activation = :activation")
    ,
    @NamedQuery(name = "Abonnements.findByAgence", query = "SELECT a FROM Abonnements a WHERE a.agence = :agence")
    ,
    @NamedQuery(name = "Abonnements.findByAlias", query = "SELECT a FROM Abonnements a WHERE a.alias = :alias")
    ,
    @NamedQuery(name = "Abonnements.findByCni", query = "SELECT a FROM Abonnements a WHERE a.cni = :cni")
    ,
    @NamedQuery(name = "Abonnements.findByCoderetour", query = "SELECT a FROM Abonnements a WHERE a.coderetour = :coderetour")
    ,
    @NamedQuery(name = "Abonnements.findByCoderetourresiliation", query = "SELECT a FROM Abonnements a WHERE a.coderetourresiliation = :coderetourresiliation")
    ,
    @NamedQuery(name = "Abonnements.findByCompte", query = "SELECT a FROM Abonnements a WHERE a.compte = :compte")
    ,
    @NamedQuery(name = "Abonnements.findByDatecreation", query = "SELECT a FROM Abonnements a WHERE a.datecreation = :datecreation")
    ,
    @NamedQuery(name = "Abonnements.findByDatemodification", query = "SELECT a FROM Abonnements a WHERE a.datemodification = :datemodification")
    ,
    @NamedQuery(name = "Abonnements.findByDatenaissance", query = "SELECT a FROM Abonnements a WHERE a.datenaissance = :datenaissance")
    ,
    @NamedQuery(name = "Abonnements.findByDateresiliation", query = "SELECT a FROM Abonnements a WHERE a.dateresiliation = :dateresiliation")
    ,
    @NamedQuery(name = "Abonnements.findByDatesouscription", query = "SELECT a FROM Abonnements a WHERE a.datesouscription = :datesouscription")
    ,
    @NamedQuery(name = "Abonnements.findByDatevalidation", query = "SELECT a FROM Abonnements a WHERE a.datevalidation = :datevalidation")
    ,
    @NamedQuery(name = "Abonnements.findByDevise", query = "SELECT a FROM Abonnements a WHERE a.devise = :devise")
    ,
    @NamedQuery(name = "Abonnements.findByLabel", query = "SELECT a FROM Abonnements a WHERE a.label = :label")
    ,
    @NamedQuery(name = "Abonnements.findByMotif", query = "SELECT a FROM Abonnements a WHERE a.motif = :motif")
    ,
    @NamedQuery(name = "Abonnements.findByNom", query = "SELECT a FROM Abonnements a WHERE a.nom = :nom")
    ,
    @NamedQuery(name = "Abonnements.findByNumerotelephone", query = "SELECT a FROM Abonnements a WHERE a.numerotelephone = :numerotelephone")
    ,
    @NamedQuery(name = "Abonnements.findByOrigine", query = "SELECT a FROM Abonnements a WHERE a.origine = :origine")
    ,
    @NamedQuery(name = "Abonnements.findByOrigineAbonnement", query = "SELECT a FROM Abonnements a WHERE a.origineAbonnement = :origineAbonnement")
    ,
    @NamedQuery(name = "Abonnements.findByPrenoms", query = "SELECT a FROM Abonnements a WHERE a.prenoms = :prenoms")
    ,
    @NamedQuery(name = "Abonnements.findByRacine", query = "SELECT a FROM Abonnements a WHERE a.racine = :racine")
    ,
    @NamedQuery(name = "Abonnements.findByReconcilie", query = "SELECT a FROM Abonnements a WHERE a.reconcilie = :reconcilie")
    ,
    @NamedQuery(name = "Abonnements.findByResilie", query = "SELECT a FROM Abonnements a WHERE a.resilie = :resilie")
    ,
    @NamedQuery(name = "Abonnements.findByService", query = "SELECT a FROM Abonnements a WHERE a.service = :service")
    ,
    @NamedQuery(name = "Abonnements.findByUsercreate", query = "SELECT a FROM Abonnements a WHERE a.usercreate = :usercreate")
    ,
    @NamedQuery(name = "Abonnements.findByUserdesactiv", query = "SELECT a FROM Abonnements a WHERE a.userdesactiv = :userdesactiv")
    ,
    @NamedQuery(name = "Abonnements.findByUsermodif", query = "SELECT a FROM Abonnements a WHERE a.usermodif = :usermodif")
    ,
    @NamedQuery(name = "Abonnements.findByUserrejet", query = "SELECT a FROM Abonnements a WHERE a.userrejet = :userrejet")
    ,
    @NamedQuery(name = "Abonnements.findByUservalidate", query = "SELECT a FROM Abonnements a WHERE a.uservalidate = :uservalidate")
    ,
    @NamedQuery(name = "Abonnements.findByActifByAlias", query = "SELECT a FROM Abonnements a WHERE a.alias = :alias and a.actif = :actif")
    ,
    @NamedQuery(name = "Abonnements.findByAliasAndPhone", query = "SELECT a FROM Abonnements a WHERE a.alias = :alias  and a.numerotelephone = :numerotelephone and a.actif = TRUE")
    ,
    @NamedQuery(name = "Abonnements.findActifByPhone", query = "SELECT a FROM Abonnements a WHERE a.numerotelephone = :numerotelephone and a.actif = TRUE")})
public class Abonnements implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDABONNEMENTS")
    private Long idabonnements;
    @Column(name = "ACTIF")
    private Boolean actif;
    @Size(max = 255)
    @Column(name = "ACTIVATION")
    private String activation;
    @Size(max = 255)
    @Column(name = "AGENCE")
    private String agence;
    @Size(max = 255)
    @Column(name = "ALIAS")
    private String alias;
    @Size(max = 255)
    @Column(name = "CNI")
    private String cni;
    @Size(max = 255)
    @Column(name = "CODERETOUR")
    private String coderetour;
    @Size(max = 255)
    @Column(name = "CODERETOURRESILIATION")
    private String coderetourresiliation;
    @Size(max = 255)
    @Column(name = "COMPTE")
    private String compte;
    @Size(max = 255)
    @Column(name = "DATECREATION")
    private String datecreation;
    @Size(max = 255)
    @Column(name = "DATEMODIFICATION")
    private String datemodification;
    @Size(max = 255)
    @Column(name = "DATENAISSANCE")
    private String datenaissance;
    @Size(max = 255)
    @Column(name = "DATERESILIATION")
    private String dateresiliation;
    @Size(max = 255)
    @Column(name = "DATESOUSCRIPTION")
    private String datesouscription;
    @Size(max = 255)
    @Column(name = "DATEVALIDATION")
    private String datevalidation;
    @Size(max = 255)
    @Column(name = "DEVISE")
    private String devise;
    @Size(max = 255)
    @Column(name = "LABEL")
    private String label;
    @Size(max = 255)
    @Column(name = "MOTIF")
    private String motif;
    @Size(max = 255)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 255)
    @Column(name = "NUMEROTELEPHONE")
    private String numerotelephone;
    @Size(max = 255)
    @Column(name = "ORIGINE")
    private String origine;
    @Size(max = 255)
    @Column(name = "ORIGINE_ABONNEMENT")
    private String origineAbonnement;
    @Size(max = 255)
    @Column(name = "PRENOMS")
    private String prenoms;
    @Size(max = 255)
    @Column(name = "RACINE")
    private String racine;
    @Column(name = "RECONCILIE")
    private Short reconcilie;
    @Column(name = "RESILIE")
    private Short resilie;
    @Size(max = 255)
    @Column(name = "SERVICE")
    private String service;
    @Size(max = 255)
    @Column(name = "USERCREATE")
    private String usercreate;
    @Size(max = 255)
    @Column(name = "USERDESACTIV")
    private String userdesactiv;
    @Size(max = 255)
    @Column(name = "USERMODIF")
    private String usermodif;
    @Size(max = 255)
    @Column(name = "USERREJET")
    private String userrejet;
    @Size(max = 255)
    @Column(name = "USERVALIDATE")
    private String uservalidate;
    @JoinColumn(name = "PROFIL", referencedColumnName = "ID_PROFILS")
    @ManyToOne
    private ProfilsClients profil;
    @JoinColumn(name = "OPERATEUR", referencedColumnName = "ID_OPERATEUR")
    @ManyToOne
    private Operateurs operateur;
    @Size(max = 255)
    @Column(name = "TYPECOMPTE")
    private String typecompte;
    @Size(max = 255)
    @Column(name = "NATIONALITE")
    private String nationalite;
    @Size(max = 255)
    @Column(name = "VILLE")
    private String ville;
    @Size(max = 255)
    @Column(name = "PAYS")
    private String pays;
    @Size(max = 255)
    @Column(name = "REGION")
    private String region;
    @Size(max = 10)
    @Column(name = "GENRE")
    private String genre;

    public Abonnements() {
    }

    public Abonnements(Long idabonnements) {
        this.idabonnements = idabonnements;
    }

    public Long getIdabonnements() {
        return idabonnements;
    }

    public void setIdabonnements(Long idabonnements) {
        this.idabonnements = idabonnements;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getCoderetour() {
        return coderetour;
    }

    public void setCoderetour(String coderetour) {
        this.coderetour = coderetour;
    }

    public String getCoderetourresiliation() {
        return coderetourresiliation;
    }

    public void setCoderetourresiliation(String coderetourresiliation) {
        this.coderetourresiliation = coderetourresiliation;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(String datecreation) {
        this.datecreation = datecreation;
    }

    public String getDatemodification() {
        return datemodification;
    }

    public void setDatemodification(String datemodification) {
        this.datemodification = datemodification;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getDateresiliation() {
        return dateresiliation;
    }

    public void setDateresiliation(String dateresiliation) {
        this.dateresiliation = dateresiliation;
    }

    public String getDatesouscription() {
        return datesouscription;
    }

    public void setDatesouscription(String datesouscription) {
        this.datesouscription = datesouscription;
    }

    public String getDatevalidation() {
        return datevalidation;
    }

    public void setDatevalidation(String datevalidation) {
        this.datevalidation = datevalidation;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumerotelephone() {
        return numerotelephone;
    }

    public void setNumerotelephone(String numerotelephone) {
        this.numerotelephone = numerotelephone;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getOrigineAbonnement() {
        return origineAbonnement;
    }

    public void setOrigineAbonnement(String origineAbonnement) {
        this.origineAbonnement = origineAbonnement;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getRacine() {
        return racine;
    }

    public void setRacine(String racine) {
        this.racine = racine;
    }

    public Short getReconcilie() {
        return reconcilie;
    }

    public void setReconcilie(Short reconcilie) {
        this.reconcilie = reconcilie;
    }

    public Short getResilie() {
        return resilie;
    }

    public void setResilie(Short resilie) {
        this.resilie = resilie;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public String getUserdesactiv() {
        return userdesactiv;
    }

    public void setUserdesactiv(String userdesactiv) {
        this.userdesactiv = userdesactiv;
    }

    public String getUsermodif() {
        return usermodif;
    }

    public void setUsermodif(String usermodif) {
        this.usermodif = usermodif;
    }

    public String getUserrejet() {
        return userrejet;
    }

    public void setUserrejet(String userrejet) {
        this.userrejet = userrejet;
    }

    public String getUservalidate() {
        return uservalidate;
    }

    public void setUservalidate(String uservalidate) {
        this.uservalidate = uservalidate;
    }

    public Operateurs getOperateur() {
        return operateur;
    }

    public void setOperateur(Operateurs operateur) {
        this.operateur = operateur;
    }

    public ProfilsClients getProfil() {
        return profil;
    }

    public void setProfil(ProfilsClients profil) {
        this.profil = profil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idabonnements != null ? idabonnements.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Abonnements)) {
            return false;
        }
        Abonnements other = (Abonnements) object;
        if ((this.idabonnements == null && other.idabonnements != null) || (this.idabonnements != null && !this.idabonnements.equals(other.idabonnements))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sbs.b2wservice.entities.Abonnements[ idabonnements=" + idabonnements + " ]";
    }

}
