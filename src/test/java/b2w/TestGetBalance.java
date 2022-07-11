/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b2w;

import com.sbs.b2wservice.utils.CoreBankingResponseFormatter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Persistence;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class TestGetBalance {
//   private static BkcomJpaController bkcomJpaController= new BkcomJpaController(Persistence.createEntityManagerFactory("B2WServicePUOrionTestInformix"));
// 
//    public static void main(String[] args){
//        System.out.println("TEST BALANCE:");
//        System.out.println(getBalance("0100126201003651"));
//
//    }
//    
//    
//    public static String getBalance(String compte) {
////        List<Parametres> lPara = em.createNamedQuery("Parametres.findByCodeparam", Parametres.class).setParameter("codeparam", "CODE_DEVISE").getResultList();
////        Parametres devise = lPara.get(0);
//        int status = 1;
//        String message = "ERREUR";
//        String statusData = "";
//        List<Bkcom> lCom = bkcomJpaController.findByNcpAndAge(compte.substring(5, 16), compte.substring(0, 5));
//        if (lCom.isEmpty() || !lCom.get(0).getBkcomPK().getDev().equals("952") || !(lCom.get(0).getCfe() == 'N')) {
//            message = "LE COMPTE EST INEXISTANT OU FERME";
//            return CoreBankingResponseFormatter.format(status, message, statusData);
//        }
//        Map<String, String> infosSolde = new HashMap<>();
//        infosSolde.put("Compte", compte);
//        infosSolde.put("Montant", new BigDecimal(lCom.get(0).getSin().doubleValue() - (lCom.get(0).getMind().doubleValue() + lCom.get(0).getMinds().doubleValue() + lCom.get(0).getMinj().doubleValue() + lCom.get(0).getMinjs().doubleValue())).toString());
//        JSONObject jsonObject = new JSONObject(infosSolde);
//        statusData = jsonObject.toString();
//        status = 0;
//        message = "DEMANDE ACCEPTEE";
//        return CoreBankingResponseFormatter.format(status, message, statusData);
//    }

    
}
