package com.sbs.b2wservice.entities;

import com.sbs.b2wservice.entities.Operateurs;
import com.sbs.b2wservice.entities.ProfilsClients;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20131113-rNA", date="2022-07-05T21:20:08")
@StaticMetamodel(Commissions.class)
public class Commissions_ { 

    public static volatile SingularAttribute<Commissions, BigDecimal> idPalier;
    public static volatile SingularAttribute<Commissions, BigInteger> tarif;
    public static volatile SingularAttribute<Commissions, String> sens;
    public static volatile SingularAttribute<Commissions, BigInteger> taux;
    public static volatile SingularAttribute<Commissions, BigInteger> maximum;
    public static volatile SingularAttribute<Commissions, ProfilsClients> profils;
    public static volatile SingularAttribute<Commissions, Operateurs> operateurs;
    public static volatile SingularAttribute<Commissions, BigInteger> minimum;

}