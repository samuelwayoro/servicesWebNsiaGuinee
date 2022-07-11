/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.sbs.b2wservice.entities.Parametres;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SOCITECH-
 */
@Stateless
public class ParametresFacade extends AbstractFacade<Parametres> {

    @PersistenceContext(unitName = "B2WServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametresFacade() {
        super(Parametres.class);
    }

    public List<Parametres> findByCodeParam(String codeParam) {
        //   try{
        List<Parametres> listpara = getEntityManager().createNamedQuery("Parametres.findByCodeparam").setParameter("codeparam", codeParam).getResultList();
        return listpara;

    }
    //renvoi un parametre a partir de son codeparam
    public Parametres findParamByCodeParam (String codeParam){
        Parametres p = (Parametres) getEntityManager().createQuery("SELECT p FROM Parametres p WHERE p.codeparam = :codeP").setParameter("codeP", codeParam);
        return p;
    }

    public List<Parametres> findParametresByPatten(String[] pattern) {
        // System.out.println("ENTREE FINDPARAMETRESBYPATTERN");
        StringBuilder requete = new StringBuilder("SELECT p FROM Parametres p WHERE p.codeparam LIKE  :codeparam0");
        // requete.append(" :codeparam0");
        for (int i = 1; i < pattern.length; i++) {
            requete.append(" OR p.codeparam LIKE :codeparam").append(i);
        }
        Query query = this.em.createQuery(requete.toString()).setParameter("codeparam0", pattern[0] + "%");
        for (int i = 1; i < pattern.length; i++) {
            query.setParameter("codeparam" + i, pattern[i] + "%");
        }
        query.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return query.getResultList();
    }

    public List<Parametres> findByListCodeparam(List<String> listCodeParam) {
        Query query = getEntityManager().createNamedQuery("Parametres.findByListCodeparam").setParameter("listcodeparam", listCodeParam);
        query.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return query.getResultList();
    }

    public List<Parametres> findByTypeParam(String typeParam) {
        return getEntityManager().createNamedQuery("Parametres.findByTypeparam").setParameter("typeparam", typeParam).getResultList();
    }

}
