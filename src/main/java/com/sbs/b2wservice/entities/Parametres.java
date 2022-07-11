/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SOCITECH-
 */
@Entity
@Table(name = "PARAMETRES")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Parametres.findAll", query = "SELECT p FROM Parametres p")
    ,
    @NamedQuery(name = "Parametres.findById", query = "SELECT p FROM Parametres p WHERE p.id = :id")
    ,
    @NamedQuery(name = "Parametres.findByCodeparam", query = "SELECT p FROM Parametres p WHERE p.codeparam = :codeparam")
    ,
    @NamedQuery(name = "Parametres.findByListCodeparam", query = "SELECT p FROM Parametres p WHERE p.codeparam in :listcodeparam")
    ,
    @NamedQuery(name = "Parametres.findByDescription", query = "SELECT p FROM Parametres p WHERE p.description = :description")
    ,
    @NamedQuery(name = "Parametres.findByTypeparam", query = "SELECT p FROM Parametres p WHERE p.typeparam = :typeparam")
    ,
    @NamedQuery(name = "Parametres.findByValeur", query = "SELECT p FROM Parametres p WHERE p.valeur = :valeur")})
public class Parametres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "CODEPARAM")
    private String codeparam;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 255)
    @Column(name = "TYPEPARAM")
    private String typeparam;
    @Size(max = 255)
    @Column(name = "VALEUR")
    private String valeur;

    public Parametres() {
    }

    public Parametres(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeparam() {
        return codeparam;
    }

    public void setCodeparam(String codeparam) {
        this.codeparam = codeparam;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeparam() {
        return typeparam;
    }

    public void setTypeparam(String typeparam) {
        this.typeparam = typeparam;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametres)) {
            return false;
        }
        Parametres other = (Parametres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//       //return "Parametres[ id=" + id + " ]:"+this.codeparam +;
//    }
    @Override
    public String toString() {
        return "Parametres{" + "id=" + id + ", codeparam=" + codeparam + ", description=" + description + ", typeparam=" + typeparam + ", valeur=" + valeur + '}';
    }

}
