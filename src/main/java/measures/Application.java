//package measures;
//
//import java.util.List;
//import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Application {
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("1. Drastic IM            7. DM-IM");
//        System.out.println("2. MI IM                 8. SUM-IM");
//        System.out.println("3. SMI-c IM              9. Probabilistic Shapley Inconsisten Value");
//        System.out.println("4. l-IM                  10. SV-IM");
//        System.out.println("5. X-IM                  11. Unnormalized IM");
//        System.out.println("6. μ-IM");
//        System.out.print("Input the Inconsistency Measure code from 1 to 11:");
//
//
//        int code;
//        double p;
//        code = sc.nextInt();
//
//        try {
//            KnowledgeBaseReader reader = new KnowledgeBaseReader();
//            List<KnowledgeBase> kbs =  reader.read("1.txt");
//            for(KnowledgeBase k: kbs){
//                System.out.println(k);
//
//                switch (code){
//                    case 1:
//                        System.out.println("Drastic Inconsistency Measure: "+Calculation.DrasticIM(k));
//                        break;
//                    case 2:
//                        System.out.println("MI Inconsistency Measure: "+Calculation.MIIM(k));
//                        break;
//                    case 3:
//                        System.out.println("SMI-c Inconsistency Measure: "+Calculation.SMIcIM(k));
//                        break;
//                    case 4:
//                        System.out.println("l-Inconsistency Measure: "+Calculation.lIM(k));
//                        break;
//                    case 5:
//                        System.out.println("X-Inconsistency Measure: "+Calculation.XIM(k));
//                        break;
//                    case 6:
//                        System.out.println("μ-Inconsistency Measure: "+Calculation.μIM(k));
//                        break;
//                    case 7:
//                        System.out.println("DM Inconsistency Measure: "+Calculation.DMIM(k));
//                        break;
//                    case 8:
//                        System.out.println("SUM Inconsistency Measure: "+Calculation.SUMIM(k));
//                        break;
//                    case 9:
//                        System.out.print("Probabilistic Shapley Inconsisten Value. Input the order of the constraint you want to evaluate:");
//                        int i = sc.nextInt();
//                        System.out.println("SIV-Constraint["+i+"]: "+Calculation.ProbalilisticShapley(k,i));
//                        break;
//                    case 10:
//                        System.out.println("SV-Inconsistency Measure: "+Calculation.SVIM(k));
//                        break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
