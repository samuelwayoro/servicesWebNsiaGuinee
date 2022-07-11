/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b2w;

import com.sbs.b2wservice.entities.Parametres;
import com.sbs.b2wservice.utils.CoreBankingResponseFormatter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Persistence;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class TestMiniStatement {
//    private static BkcomJpaController bkcomJpaController= new BkcomJpaController(Persistence.createEntityManagerFactory("B2WServicePUOrionTestInformix"));
//    private static BknomJpaController bknomJpaController= new BknomJpaController(Persistence.createEntityManagerFactory("B2WServicePUOrionTestInformix"));
//    private static BkhisJpaController bkhisJpaController= new BkhisJpaController(Persistence.createEntityManagerFactory("B2WServicePUOrionTestInformix"));
//
//    public static void main(String[] args){
//        System.out.println("TEST MINISTATEMENT:");
//        System.out.println(getMiniStatement("0100126201000165", 5));
//
//    
//
//    }
//    
//    
//        public static String getMiniStatement(String compte, int nbLigne) {
//    //    List<Parametres> lPara = em.createNamedQuery("Parametres.findByCodeparam", Parametres.class).setParameter("codeparam", "CODE_DEVISE").getResultList();
//    //    Parametres devise = lPara.get(0);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        int status = 1;
//        String message = "ERREUR";
//        String statusData = "";
//        List<Bkcom> lCom = bkcomJpaController.findByNcpAndAge(compte.substring(5, 16), compte.substring(0, 5));
//        if (lCom.isEmpty() || !lCom.get(0).getBkcomPK().getDev().equals("952") || !(lCom.get(0).getCfe() == 'N')) {
//            message = "LE COMPTE EST INEXISTANT OU FERME";
//            return CoreBankingResponseFormatter.format(status, message, statusData);
//        }
//        Bkcom bkcom = lCom.get(0);
//        List<Bknom> lBnom = bknomJpaController.findByCtabAndCacc("005", "952");
//        Bknom bkNomDevise = lBnom.get(0);
//        
//        List<Bkhis> lBkhis = bkhisJpaController.findByNcpAndSufDesc(compte.substring(5, 16), " ", nbLigne);
//
//        status = 0;
//        message = "";
//        List<Map<String, String>> mvts = new ArrayList<>();
//            Map<String, String> currentMvt;
//            for (Bkhis bkhis : lBkhis) {
//                currentMvt = new HashMap<>();
//                currentMvt.put("Compte", bkhis.getNcp().trim());
//                currentMvt.put("tranrefno", bkhis.getPie().trim());
//                currentMvt.put("trandate", sdf.format(bkhis.getDco()));
//                currentMvt.put("trantype", bkhis.getOpe().trim());
//                currentMvt.put("ccy", bkNomDevise.getLib2().trim());
//                currentMvt.put("crdr", bkhis.getSen().toString());
//                currentMvt.put("amount", bkhis.getMon().toString());
//                currentMvt.put("narration", bkhis.getLib());
//                mvts.add(currentMvt);
//            }
//            JSONArray jSONArrayMvts = new JSONArray(mvts);
//            Map<String, String> mp = new HashMap<>();
//            mp.put("mvts", jSONArrayMvts.toString());
//            JSONObject jsonObject = new JSONObject(mp);
//            statusData = jsonObject.toString().replace("\\", "");
//            
//            return CoreBankingResponseFormatter.format(status, message, statusData);
//
//    }
//
//    
}
