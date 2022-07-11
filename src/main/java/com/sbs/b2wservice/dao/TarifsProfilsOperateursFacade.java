/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.sbs.b2wservice.entities.Abonnements;
import com.sbs.b2wservice.entities.TarifsProfilsOperateurs;
import com.sbs.exceptions.FeesNotDefinedException;
import com.sbs.exceptions.NonUniqueTarifException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alex
 */
@Stateless
public class TarifsProfilsOperateursFacade extends AbstractFacade<TarifsProfilsOperateurs> {

    @PersistenceContext(unitName = "B2WServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarifsProfilsOperateursFacade() {
        super(TarifsProfilsOperateurs.class);
    }

    public TarifsProfilsOperateurs findTarif(Abonnements ab, String service) throws Exception {
        try {
            return (TarifsProfilsOperateurs) em.createNamedQuery("").setParameter("operateur", ab.getOperateur().getIdOperateur()).setParameter("profil", ab.getProfil().getIdProfils()).setParameter("service", service).getSingleResult();
        } catch (NoResultException ex) {
            throw new FeesNotDefinedException(ab.getOperateur().getDesignationOperateur(), ab.getProfil().getDesignationProfils(), service);
        } catch (NonUniqueResultException ex) {
            throw new NonUniqueTarifException(service, ab.getProfil().getDesignationProfils());
        }
    }

}
