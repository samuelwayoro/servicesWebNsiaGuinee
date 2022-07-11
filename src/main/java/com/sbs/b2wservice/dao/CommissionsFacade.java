/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.sbs.b2wservice.entities.Abonnements;
import com.sbs.b2wservice.entities.Commissions;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alex
 */
@Stateless
public class CommissionsFacade extends AbstractFacade<Commissions> {

    @PersistenceContext(unitName = "B2WServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommissionsFacade() {
        super(Commissions.class);
    }

    public List<Commissions> findCommissionByMontantNet(double montant, String sens, Abonnements ab) {
        return (List<Commissions>) em.createNamedQuery("Commissions.findCommissionByMontantNet").setParameter("tarif", montant).setParameter("sens", sens).setParameter("operateur", ab.getOperateur().getIdOperateur()).setParameter("profil", ab.getProfil().getIdProfils()).getResultList();
    }

    public List<Commissions> findCommissionByMontantBrut(double montant, String sens) {
        return (List<Commissions>) em.createNamedQuery("Commissions.findCommissionByMontantBrut").setParameter("tarif", montant).setParameter("sens", sens).getResultList();
    }

    public List<Commissions> findB2WMontantCommission() {
        return (List<Commissions>) em.createNamedQuery("Commissions.findB2WMontantCommission").setParameter("sens", "B2W").getResultList();
    }

}
