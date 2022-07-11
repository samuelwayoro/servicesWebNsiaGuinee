package b2w;

/**
 *
 * @author alexa
 */
public class MyTestRunner {
//    private static BktelcliJpaController bktelcliJpaController= new BktelcliJpaController(Persistence.createEntityManagerFactory("B2WServicePUOrionTestInformix"));
//    private static BkcomJpaController bkcomJpaController= new BkcomJpaController(Persistence.createEntityManagerFactory("B2WServicePUOrionTestInformix"));
////    private static ParametresJpaController parametresJpaController= new ParametresJpaController(Persistence.createEntityManagerFactory("B2WServicePU"));
//
//
//    
//    
//
//    public static void main(String[] args) {
//    //DeltaFacade deltaFacade = lookupDeltaFacadeBean();
//        
//        System.out.println("KYC TESTING: "); 
//        System.out.println(getSignaletique("016927"));
//    }
//
//    public static String getSignaletique(String racine) {
////        List<Parametres> lPara = parametresJpaController.findByCodeParam("CODE_DEVISE");
////        Parametres devise = lPara.get(0);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        int status = 0;
//        String message = "";
//        String statusData = "";
//
//        List<Bkcom> lCom = bkcomJpaController.findByCliChaSufAndDev(racine, "952");
//        if (lCom.isEmpty()) {
//            status = 1;
//            message = "CLIENT INEXISTANT OU COMPTE FERME";
//        } else {
//            Bkcom bkcom = lCom.get(0);
//        List<Bktelcli> lBtelcli = bktelcliJpaController.findByCli(bkcom.getCli());
//        String listeTel = "";
//            for (Bktelcli bktelcli1 : lBtelcli) {
//                listeTel = listeTel + "##" + bktelcli1.getNum();
//            }
//            listeTel = listeTel.replaceFirst("##", "");
//            status = 0;
//            Map<String, String> infosGenerales = new HashMap<>();
//            infosGenerales.put("Client", bkcom.getCli().trim());
//            infosGenerales.put("Sexe", bkcom.getBkcli().getSext().toString());
//            infosGenerales.put("NomPrenom", bkcom.getBkcli().getNomrest().trim());
//            infosGenerales.put("Nom", bkcom.getBkcli().getTcli() == '1' ? bkcom.getBkcli().getNomrest() : bkcom.getBkcli().getRso());
//            infosGenerales.put("Prenom", bkcom.getBkcli().getPre().trim());
//            infosGenerales.put("DateNais", sdf.format(bkcom.getBkcli().getDna()));
//            infosGenerales.put("Agec", bkcom.getBkcli().getTcli() == '1' ? "PAR" : "ENT");
//            infosGenerales.put("Tel", listeTel);
//            infosGenerales.put("PieceId", bkcom.getBkcli().getNid());
//            infosGenerales.put("DateExpir", sdf.format(bkcom.getBkcli().getVid()));
//            infosGenerales.put("DateDelivr", sdf.format(bkcom.getBkcli().getDid()));
//            List<HashMap<String, String>> lComptes = new ArrayList<>();
//            Map<String, String> currentAccount;
//            for (Bkcom cptBkcom : lCom) {
//                currentAccount = new HashMap<>();
//                currentAccount.put("Compte", cptBkcom.getBkcomPK().getNcp().trim());
//                currentAccount.put("Agence", cptBkcom.getBkcomPK().getAge().trim());
//                currentAccount.put("Ncg", cptBkcom.getCha().trim());
//                currentAccount.put("Libncg", cptBkcom.getBkchap().getLib().trim());
//                currentAccount.put("Expl", cptBkcom.getBkcli().getGes().trim());
//                lComptes.add((HashMap)currentAccount);
//            }
//            JSONArray jSONArrayComptes = new JSONArray(lComptes);
//            infosGenerales.put("Comptes", jSONArrayComptes.toString());
//
//            JSONObject jsonObject = new JSONObject(infosGenerales);
//            statusData = jsonObject.toString().replace("\\", "");
//            status = 0;
//        }
//        return CoreBankingResponseFormatter.format(status, message, statusData);
//    }
//    


}
