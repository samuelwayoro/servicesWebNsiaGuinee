/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.sbs.b2wservice.entities.Limitestransactions;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author samuel
 */
@Stateless
public class LimitestransactionsFacade extends AbstractFacade<Limitestransactions> {

    @PersistenceContext(unitName = "B2WServicePU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LimitestransactionsFacade() {
        super(Limitestransactions.class);
    }
    
    public List<Limitestransactions>findAllTransactions(){
        return em.createNamedQuery("Limitestransactions.findAll").getResultList();
    }

    public Limitestransactions findLimiteByKeycolumn(String colonneCle) {
        return (Limitestransactions) em.createNamedQuery("Limitestransactions.findByKeycolumn").setParameter("keycolumn", colonneCle).getSingleResult();
    }
    
}
