/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banktowallet.b2w.b2w;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author samuel
 */
@Path("/verifyfinancialresourceinformationsResource")
public class VerifyfinancialresourceinformationsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VerifyfinancialresourceinformationsResource
     */
    public VerifyfinancialresourceinformationsResource() {
    }

    /**
     * Retrieves representation of an instance of com.banktowallet.b2w.b2w.VerifyfinancialresourceinformationsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

}
