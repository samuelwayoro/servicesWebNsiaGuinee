package com.sbs.b2wservice.entities;

import com.sbs.b2wservice.entities.Abonnements;
import com.sbs.b2wservice.entities.Commissions;
import com.sbs.b2wservice.entities.Transactions;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20131113-rNA", date="2022-07-05T21:20:09")
@StaticMetamodel(Operateurs.class)
public class Operateurs_ { 

    public static volatile ListAttribute<Operateurs, Commissions> commissionsList;
    public static volatile SingularAttribute<Operateurs, String> pageSouscription;
    public static volatile SingularAttribute<Operateurs, BigDecimal> idOperateur;
    public static volatile ListAttribute<Operateurs, Abonnements> abonnementsList;
    public static volatile SingularAttribute<Operateurs, String> designationOperateur;
    public static volatile SingularAttribute<Operateurs, String> bic;
    public static volatile ListAttribute<Operateurs, Transactions> transactionsList;
    public static volatile SingularAttribute<Operateurs, String> pageParametrage;

}