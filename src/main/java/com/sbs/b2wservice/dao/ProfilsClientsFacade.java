/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.b2wservice.dao;

import com.sbs.b2wservice.entities.ProfilsClients;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alex
 */
@Stateless
public class ProfilsClientsFacade extends AbstractFacade<ProfilsClients> {

    @PersistenceContext(unitName = "B2WServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfilsClientsFacade() {
        super(ProfilsClients.class);
    }

}
