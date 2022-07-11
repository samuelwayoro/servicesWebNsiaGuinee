/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.sbs.b2wservice.entities.Webproccor;
import java.math.BigDecimal;
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
public class WebproccorFacade extends AbstractFacade<Webproccor> {

    @PersistenceContext(unitName = "B2WServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WebproccorFacade() {
        super(Webproccor.class);
    }

    public List<Webproccor> findByWebServiceMethod(String webServiceMethod, String operateur) {
        Query query = getEntityManager().createNamedQuery("Webproccor.findByWebservicemethod").setParameter("webservicemethod", webServiceMethod).setParameter("operateur", new BigDecimal(operateur));
        return query.getResultList();
    }

    public Webproccor findRefProc(String procName, String propertyName) {
        Query query = getEntityManager().createNamedQuery("Webproccor.findRefProc").setParameter("paraweb", propertyName).setParameter("webservicemethod", procName);

        Webproccor result = null;
        try {
            result = (Webproccor) query.getSingleResult();

        } catch (NoResultException | NonUniqueResultException ex) {
            // ex.printStackTrace();
            return result;
        }

        return result;
    }

    public List<Webproccor> findParaConst(String webServiceMethod) {
        Query query = getEntityManager().createNamedQuery("Webproccor.findParaConst").setParameter("webservicemethod", webServiceMethod);

        List<Webproccor> result;

        result = (List<Webproccor>) query.getResultList();

        return result;
    }

    public List<Webproccor> findByWebServiceMethodOnly(String webServiceMethod) {
        Query query = getEntityManager().createNamedQuery("Webproccor.findByWebServiceMethodOnly").setParameter("webservicemethod", webServiceMethod);
        return query.getResultList();
    }

}
