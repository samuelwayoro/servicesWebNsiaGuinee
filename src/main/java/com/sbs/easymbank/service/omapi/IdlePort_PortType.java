/**
 * IdlePort_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.easymbank.service.omapi;

public interface IdlePort_PortType extends java.rmi.Remote {

    /**
     * Dispo de la banque
     * @param idle
     * @return 
     * @throws java.rmi.RemoteException
     */
    public boolean setIdle(java.lang.String idle) throws java.rmi.RemoteException;
}
