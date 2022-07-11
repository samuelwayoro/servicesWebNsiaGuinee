/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.easymbank.other;

import com.sbs.b2wservice.entities.Parametres;
import com.sbs.easymbank.other.client.OtherWebServiceService;
import com.sbs.exceptions.FeesNotDefinedException;
import com.sbs.exceptions.ParameterNotFoundException;
import com.sbs.exceptions.PaymentException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import com.sbs.easymbank.other.client.OtherWebService;
import com.sbs.easymbank.other.client.PaymentRequest;
import com.sbs.easymbank.other.client.PaymentResponse;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.TimeZone;
import org.patware.bean.BeanInfoFactory;
import org.patware.bean.Property;

/**
 *
 * @author alexa
 */
public class PaymentServiceCaller {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void payForService(String url, Object object, String montantCom, long operateurs) throws PaymentException {
        Property[] properties = BeanInfoFactory.getProperties(object);
        String alias = "";
        String compte = "";
        for (Property p : properties) {
            if (p.getPropertyName().equals("accountAlias")) {
                alias = (String) p.getPropertyValue();
            } else if (p.getPropertyName().equals("accountNo")) {
                compte = (String) p.getPropertyValue();
            }
        }

        try {
            OtherWebServiceService oss = new OtherWebServiceService(new URL(url));
            OtherWebService otherService = oss.getOtherWebServicePort();
            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setAlias(alias);
            paymentRequest.setCommissions(operateurs);  /// operateur à la place de commissions
            paymentRequest.setCompte(compte);
            paymentRequest.setMontant(Double.parseDouble(montantCom));
            paymentRequest.setDatePaiment(sdf.format(Calendar.getInstance(TimeZone.getDefault()).getTime()));
            PaymentResponse paymentResponse = otherService.payForService(paymentRequest);
            if (paymentResponse.getStatut().equals("1")) {
                throw new PaymentException(paymentResponse.getStatutMsg());
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

    }

}
