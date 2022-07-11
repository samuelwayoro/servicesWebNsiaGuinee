/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.sbs.b2wservice.entities.Codes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SOCITECH-
 */
@Stateless
public class CodesFacade extends AbstractFacade<Codes> {

    @PersistenceContext(unitName = "B2WServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CodesFacade() {
        super(Codes.class);
    }

    public Codes findByCodeValue(String codeValue) {
        try {
            return (Codes) getEntityManager().createNamedQuery("Codes.findByCodevalue").setParameter("codevalue", codeValue).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Codes> findCodesByPatten(String[] pattern) {
        // System.out.println("ENTREE FINDCODESBYPATTERN");
        StringBuilder requete = new StringBuilder("SELECT c FROM Codes c WHERE c.codevalue LIKE :codevalue0");
        //  requete.append(" :codevalue0");
        for (int i = 1; i < pattern.length; i++) {
            requete.append(" OR c.codevalue LIKE :codevalue" + i);
        }
        Query query = this.em.createQuery(requete.toString()).setParameter("codevalue0", pattern[0] + "%");
        for (int i = 1; i < pattern.length; i++) {
            query.setParameter("codevalue" + i, pattern[i] + "%");
        }
        return query.getResultList();
    }

}
