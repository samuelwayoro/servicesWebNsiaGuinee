/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.sbs.b2wservice.entities.Transactions;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SOCITECH-
 */
@Stateless
public class TransactionsFacade extends AbstractFacade<Transactions> {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @PersistenceContext(unitName = "B2WServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransactionsFacade() {
        super(Transactions.class);
    }

    public boolean idAlreadyExist(String requestId) {
        try {
            em.createNamedQuery("Transactions.findByRequestid").setParameter("requestid", requestId).getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        } catch (NonUniqueResultException e) {
            return true;
        }
    }

    /**
     * update d'une requette
     *
     * @param t
     * @return
     */
    public Transactions updateTransaction(Transactions t) {
        return em.merge(t);
    }

    /**
     * requette ecrite afin de repondre avec un etat E11 d'une requette en time
     * out renvoi une transaction en fonction de son id
     *
     * @param id
     * @return
     */
    public Transactions findTransactionById(String id) {
        Query q = em.createNamedQuery("Transactions.findByRequestid").setParameter("requestid", id);
        try {
            return (Transactions) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    //renvoi le nbre de transaction d'un client le jour en cours 
    public long nombreTransactionJour(String alias) {
        List<String> typeTransact = new ArrayList<>();
        typeTransact.add("bank2wallet");
        typeTransact.add("wallet2bank");
        return (long) em.createNamedQuery("Transactions.findCountByAliasAndDate").setParameter("accountalias", alias).setParameter("trandate", sdf.format(Calendar.getInstance().getTime())).setParameter("responsecode", "000").setParameter("requesttype", typeTransact).getSingleResult();
    }

    //renvoi le nbre de transaction d'un client sur le mois en cours 
    public long nombreTransactionMois(String alias) {
        List<String> typeTransact = new ArrayList<>();
        typeTransact.add("bank2wallet");
        typeTransact.add("wallet2bank");
        return (long) em.createNamedQuery("Transactions.findCountByAliasAndDateOnMounth").setParameter("accountalias", alias).setParameter("trandate", sdf.format(Calendar.getInstance().getTime())).setParameter("responsecode", "000").setParameter("requesttype", typeTransact).getSingleResult();
    }

    //renvoi le total de la somme de toutes les transaction du jour d'un client 
    public long sommeTransactionJour(String alias) {
        List<String> typeTransact = new ArrayList<>();
        typeTransact.add("bank2wallet");
        typeTransact.add("wallet2bank");
        long total = 0;
        List<Transactions> lt = em.createNamedQuery("Transactions.findSumByAliasAndDate").setParameter("accountalias", alias)
                .setParameter("trandate", sdf.format(Calendar.getInstance().getTime())).setParameter("responsecode", "000").setParameter("requesttype", typeTransact).getResultList();
        for (Transactions tr : lt) {
            if (tr.getAmount() != null) {
                total += Long.valueOf(tr.getAmount());
            }
        }
        return total;
    }

    //renvoi le total de la somme de toutes les transactions du mois d'un client 
    public Long sommeTransactionMois(String alias) {
        List<String> typeTransact = new ArrayList<>();
        typeTransact.add("bank2wallet");
        typeTransact.add("wallet2bank");
        long total = 0;
        List<Transactions> lt = em.createNamedQuery("Transactions.findMounthlySumByAliasAndDate").setParameter("accountalias", alias).setParameter("trandate", sdf.format(Calendar.getInstance().getTime())).setParameter("responsecode", "000").setParameter("requesttype", typeTransact).getResultList();
        for (Transactions transactions : lt) {
            if (transactions.getAmount() != null) {
                total += Long.valueOf(transactions.getAmount());
            }
        }
        return total;
    }

}
