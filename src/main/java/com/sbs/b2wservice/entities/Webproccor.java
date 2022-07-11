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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SOCITECH-
 */
@Entity
@Table(name = "webproccor")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Webproccor.findAll", query = "SELECT w FROM Webproccor w")
    ,
    @NamedQuery(name = "Webproccor.findByWebservicemethod", query = "SELECT w FROM Webproccor w WHERE w.webservicemethod = :webservicemethod and w.operateurs.idOperateur= :operateur order by w.refproc")
    ,
    @NamedQuery(name = "Webproccor.findByWebServiceMethodOnly", query = "SELECT w FROM Webproccor w WHERE w.webservicemethod = :webservicemethod order by w.refproc")
    ,
    @NamedQuery(name = "Webproccor.findByParaweb", query = "SELECT w FROM Webproccor w WHERE w.paraweb = :paraweb")
    ,
    @NamedQuery(name = "Webproccor.findByValeur", query = "SELECT w FROM Webproccor w WHERE w.valeur = :valeur")
    ,
    @NamedQuery(name = "Webproccor.findByRefweb", query = "SELECT w FROM Webproccor w WHERE w.refweb = :refweb")
    ,
    @NamedQuery(name = "Webproccor.findByParaproc", query = "SELECT w FROM Webproccor w WHERE w.paraproc = :paraproc")
    ,
    @NamedQuery(name = "Webproccor.findByTypeparam", query = "SELECT w FROM Webproccor w WHERE w.typeparam = :typeparam")
    ,
    @NamedQuery(name = "Webproccor.findByRefproc", query = "SELECT w FROM Webproccor w WHERE w.refproc = :refproc")
    ,
    @NamedQuery(name = "Webproccor.findById", query = "SELECT w FROM Webproccor w WHERE w.id = :id")
    ,
    @NamedQuery(name = "Webproccor.findByProc", query = "SELECT w FROM Webproccor w WHERE w.proc = :proc")
    ,
    @NamedQuery(name = "Webproccor.findRefProc", query = "SELECT w FROM Webproccor w WHERE w.paraweb = :paraweb and w.webservicemethod = :webservicemethod")
    ,
    @NamedQuery(name = "Webproccor.findParaConst", query = "SELECT w FROM Webproccor w WHERE w.paraweb IS NULL and w.webservicemethod = :webservicemethod")})
public class Webproccor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 30)
    @Column(name = "webservicemethod")
    private String webservicemethod;
    @Size(max = 50)
    @Column(name = "paraweb")
    private String paraweb;
    @Size(max = 10)
    @Column(name = "valeur")
    private String valeur;
    @Size(max = 10)
    @Column(name = "refweb")
    private String refweb;
    @Size(max = 50)
    @Column(name = "paraproc")
    private String paraproc;
    @Size(max = 50)
    @Column(name = "typeparam")
    private String typeparam;
    @Size(max = 50)
    @Column(name = "refproc")
    private String refproc;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "proc")
    private String proc;
    @JoinColumn(name = "OPERATEURS", referencedColumnName = "ID_OPERATEUR")
    @ManyToOne
    private Operateurs operateurs;

    public Webproccor() {
    }

    public Webproccor(Integer id) {
        this.id = id;
    }

    public String getWebservicemethod() {
        return webservicemethod;
    }

    public void setWebservicemethod(String webservicemethod) {
        this.webservicemethod = webservicemethod;
    }

    public String getParaweb() {
        return paraweb;
    }

    public void setParaweb(String paraweb) {
        this.paraweb = paraweb;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getRefweb() {
        return refweb;
    }

    public void setRefweb(String refweb) {
        this.refweb = refweb;
    }

    public String getParaproc() {
        return paraproc;
    }

    public void setParaproc(String paraproc) {
        this.paraproc = paraproc;
    }

    public String getTypeparam() {
        return typeparam;
    }

    public void setTypeparam(String typeparam) {
        this.typeparam = typeparam;
    }

    public String getRefproc() {
        return refproc;
    }

    public void setRefproc(String refproc) {
        this.refproc = refproc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProc() {
        return proc;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }

    public Operateurs getOperateur() {
        return operateurs;
    }

    public void setOperateur(Operateurs operateur) {
        this.operateurs = operateur;
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
        if (!(object instanceof Webproccor)) {
            return false;
        }
        Webproccor other = (Webproccor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sbs.b2wservice.entities.Webproccor[ id=" + id + " ]";
    }

}
