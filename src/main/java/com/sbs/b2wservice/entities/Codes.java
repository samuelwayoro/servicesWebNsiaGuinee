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

/**
 *
 * @author SOCITECH-
 */
@Entity
@Table(name = "CODES")
@XmlRootElement

@NamedQueries({
    @NamedQuery(name = "Codes.findAll", query = "SELECT c FROM Codes c")
    ,
    @NamedQuery(name = "Codes.findByIdcodes", query = "SELECT c FROM Codes c WHERE c.idcodes = :idcodes")
    ,
    @NamedQuery(name = "Codes.findByCodedescription", query = "SELECT c FROM Codes c WHERE c.codedescription = :codedescription")
    ,
    @NamedQuery(name = "Codes.findByCodevalue", query = "SELECT c FROM Codes c WHERE c.codevalue = :codevalue")})
public class Codes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCODES")
    private Long idcodes;
    @Size(max = 255)
    @Column(name = "CODEDESCRIPTION")
    private String codedescription;
    @Size(max = 255)
    @Column(name = "CODEVALUE")
    private String codevalue;

    public Codes() {
    }

    public Codes(Long idcodes) {
        this.idcodes = idcodes;
    }

    public Long getIdcodes() {
        return idcodes;
    }

    public void setIdcodes(Long idcodes) {
        this.idcodes = idcodes;
    }

    public String getCodedescription() {
        return codedescription;
    }

    public void setCodedescription(String codedescription) {
        this.codedescription = codedescription;
    }

    public String getCodevalue() {
        return codevalue;
    }

    public void setCodevalue(String codevalue) {
        this.codevalue = codevalue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcodes != null ? idcodes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Codes)) {
            return false;
        }
        Codes other = (Codes) object;
        if ((this.idcodes == null && other.idcodes != null) || (this.idcodes != null && !this.idcodes.equals(other.idcodes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sbs.b2wservice.entities.Codes[ idcodes=" + idcodes + " ]";
    }

}
