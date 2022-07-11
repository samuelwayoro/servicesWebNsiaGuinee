/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.sbs.b2wservice.entities.Operateurs;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alex
 */
@Stateless
public class OperateursFacade extends AbstractFacade<Operateurs> {

    @PersistenceContext(unitName = "B2WServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperateursFacade() {
        super(Operateurs.class);
    }

    public List<Operateurs> findByDesignation(String designation) {
        return em.createNamedQuery("Operateurs.findByDesignationOperateur").setParameter("designationOperateur", designation).getResultList();
    }

}
