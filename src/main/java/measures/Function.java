package measures;

import util.Utils;

import java.util.*;

public class Function {

    /**
     * Measure Inconsistency Degree of Two Elements measures.Constraint
     */
    public static boolean InconsistentTwoElementsConstraint(KnowledgeBase cmp){
        List<Constraint> cs = cmp.getConstraints();
        if (cs.get(0).getStatement().equals(cs.get(1).getStatement())) {
            if (cs.get(0).getProbability() != (cs.get(1).getProbability()))
                return true;
        }
        return false;
    }

    public static double lProbabilityFunction(KnowledgeBase k){
        List<Constraint> cs = k.getConstraints();
        KnowledgeBase k1 = new KnowledgeBase();
        KnowledgeBase k2 = new KnowledgeBase();
        KnowledgeBase k3 = new KnowledgeBase();
        KnowledgeBase kx = new KnowledgeBase();

        k1.add(cs.get(0));
        k1.add(cs.get(1));
        k2.add(cs.get(0));
        k2.add(cs.get(2));
        k3.add(cs.get(1));
        k3.add(cs.get(2));
        kx.add(cs.get(0));
        double p1 = 0;
        double p2 = 0;
        double p3= 0;

        if (!Function.InconsistentTwoElementsConstraint(k1) && !Function.InconsistentTwoElementsConstraint(k2) && !Function.InconsistentTwoElementsConstraint(k3)){
            p1 = (double)1/3;
            p2 = (double)1/3;
            p3 = (double)1/3;
        }
        double l = 0;
        if (Function.KnowledgeBaseIncludeFulfill(k1,kx))
            l+= p1;
        if (Function.KnowledgeBaseIncludeFulfill(k2,kx))
            l+= p2;
        if (Function.KnowledgeBaseIncludeFulfill(k3,kx))
            l+= p3;
        return l;
    }

    public static KnowledgeBase Minus(KnowledgeBase k, Constraint c){
        KnowledgeBase k1 = new KnowledgeBase();
        k1.add(c);
        if (k.getConstraints().size() != 1) {
            if (!k.contain(c))
                return k;
            else {
                k.remove(c);
                return k;
            }
        }else
            return k;
    }
    /**
     * Determine Probability measures.Function fulfill each other or not.
     */
    public static boolean KnowledgeBaseIncludeFulfill(KnowledgeBase k1, KnowledgeBase k2){
        List<Constraint> c2 = k2.getConstraints();
        for (int i = 0; i < c2.size(); i++ ){
            if (!k1.contain(c2.get(i)))
                return false;
        }
        return true;
    }

    /**
     * Set of the maximal consistent subsets of K
     * count number of element in set
     */
    public static double SMC(KnowledgeBase k){
        List<Constraint> cs = k.getConstraints();
        KnowledgeBase mcs1 = new KnowledgeBase();
        KnowledgeBase mcs2 = new KnowledgeBase();
        KnowledgeBase mcs3 = new KnowledgeBase();

        mcs1.add(cs.get(0));
        mcs1.add(cs.get(1));
        mcs2.add(cs.get(0));
        mcs2.add(cs.get(2));
        mcs3.add(cs.get(1));
        mcs3.add(cs.get(2));

        double smc = 0;
        if (Function.KnowledgeBaseIncludeFulfill(k,mcs1))
            smc++;
        if (Function.KnowledgeBaseIncludeFulfill(k,mcs2))
            smc++;
        if (Function.KnowledgeBaseIncludeFulfill(k,mcs3))
            smc++;
        return  smc;
    }

    /**
     * Inconsistency Measure of single constraint in Knowledge Base
     */
    public static boolean ConsistentFirstConstraint(KnowledgeBase k) {
        List<Constraint> cs = k.getConstraints();
        KnowledgeBase k1 = new KnowledgeBase();
        KnowledgeBase k2 = new KnowledgeBase();
        k1.add(cs.get(0));
        k1.add(cs.get(1));
        k2.add(cs.get(0));
        k2.add(cs.get(2));

        if ((InconsistentTwoElementsConstraint(k1) == true) || (InconsistentTwoElementsConstraint(k2)))
            return false;
        else
            return true;
    }


    public static boolean ConsistentSecondConstraint(KnowledgeBase k){
        List<Constraint> cs = k.getConstraints();
        KnowledgeBase k1 = new KnowledgeBase();
        KnowledgeBase k2 = new KnowledgeBase();
        k1.add(cs.get(1));
        k1.add(cs.get(2));
        k2.add(cs.get(1));
        k2.add(cs.get(0));

        if ((InconsistentTwoElementsConstraint(k1) == true) || (InconsistentTwoElementsConstraint(k2)))
            return false;
        else
            return true;
    }

    public static boolean ConsistentThirdConstraint(KnowledgeBase k){
        List<Constraint> cs = k.getConstraints();
        KnowledgeBase k1 = new KnowledgeBase();
        KnowledgeBase k2 = new KnowledgeBase();
        k1.add(cs.get(2));
        k1.add(cs.get(1));
        k2.add(cs.get(2));
        k2.add(cs.get(0));

        if ((InconsistentTwoElementsConstraint(k1) == true) || (InconsistentTwoElementsConstraint(k2)))
            return false;
        else
            return true;
    }

    /**
     * Set of self-contradictory constraints of K
     * count number of element in set
     */
    public static double SCC(KnowledgeBase k){
        int count = 0;
        if (!ConsistentFirstConstraint(k))
            count++;
        if (!ConsistentSecondConstraint(k))
            count++;
        if (!ConsistentThirdConstraint(k))
            count++;
        return count;
    }

    /**
     * The maximum number of all MCS
     */
    public static double MaxConsistentConstraint(KnowledgeBase k){
        List<Constraint> cs = k.getConstraints();
        if (Calculation.DrasticIM(k)==0)
            return cs.size();
        else
            return cs.size()-1;

    }

    public static KnowledgeBase toKnowledgeBase(String str){
        KnowledgeBase k = new KnowledgeBase();
        String[] contraints = str.split(" ");
        for (String c : contraints) {
            String[] elements = c.split(":");
            Constraint c1 = new Constraint(elements[0], Double.valueOf(elements[1]));
            k.add(c1);
        }
        return k;
    }

    public static boolean SimilarKnowledgeBase(KnowledgeBase k1, KnowledgeBase k2){
        List<Constraint> cs1 = k1.getConstraints();
        List<Constraint> cs2 = k2.getConstraints();
        if ((cs1.get(0).getStatement().equals(cs2.get(0).getStatement())) && (cs1.get(1).getStatement().equals(cs2.get(1).getStatement())) && (cs1.get(2).getStatement().equals(cs2.get(2).getStatement())))
            return true;
        return false;
    }

    /**
     * Split a Knowledge Base to subsets Knowledge Base using function powerSet in class Sets.class
     * imported by Maven:
     *             <groupId>com.google.guava</groupId>
     *             <artifactId>guava</artifactId>
     *             <version>24.1-jre</version>
     */
    public static List<KnowledgeBase> SplitKnowledgeBase(KnowledgeBase kb){
        List<KnowledgeBase> kbs = new ArrayList<>();

        List<Constraint> cs = kb.getConstraints();
        Set<Set<Constraint>> childkb = Utils.powerSet(new HashSet<>(cs));

        for (Set<Constraint> set : childkb)
            if (set.size() != 0)
                kbs.add(new KnowledgeBase(set));
        return kbs;
    }

    public static long Factorial(long n){
        if (n <= 1)
            return 1;
        else
            return n * Factorial(n - 1);
    }


//    public static void main(String[] args) throws Exception{
//        Set<Integer> s = new HashSet<>();
//        s.add(1);
//        s.add(2);
//        s.add(3);
//        System.out.println(Sets.powerSet(s));
//    }
}
