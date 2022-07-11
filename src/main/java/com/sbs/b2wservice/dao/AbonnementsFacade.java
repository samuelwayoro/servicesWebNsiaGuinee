/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.sbs.b2wservice.entities.Abonnements;
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
public class AbonnementsFacade extends AbstractFacade<Abonnements> {

    @PersistenceContext(unitName = "B2WServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AbonnementsFacade() {
        super(Abonnements.class);
    }

    public Abonnements findByAlias(String alias) {
        try {
            Query query = em.createNamedQuery("Abonnements.findByAlias").setParameter("alias", alias);
            return (Abonnements) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }

    }

    public Abonnements findByActifByAlias(String alias, Boolean actif) {
        try {
            Query query = em.createNamedQuery("Abonnements.findByActifByAlias").setParameter("alias", alias).setParameter("actif", actif);
            //System.out.println("ENTREE BASE BD "+query.toString());
            return (Abonnements) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }

    }

    public Abonnements findByAliasAndPhone(String alias, String phone) {
        try {
            Query query = em.createNamedQuery("Abonnements.findByAliasAndPhone").setParameter("alias", alias).setParameter("numerotelephone", phone);
            //    System.out.println("ENTREE BASE");
            return (Abonnements) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }

    }

    public List<Abonnements> findActifByPhone(String phone) {
        Query query = em.createNamedQuery("Abonnements.findActifByPhone").setParameter("numerotelephone", phone);
        return (List<Abonnements>) query.getResultList();
    }
    
    //verif d'un abonne actif : Samuel 
    public Abonnements findActifAbonneByPhoneNumber(String msisdn){
        Query q = em.createNamedQuery("Abonnements.findActifByPhone").setParameter("msisdn", msisdn);
        return (Abonnements) q.getSingleResult();
    }

}
