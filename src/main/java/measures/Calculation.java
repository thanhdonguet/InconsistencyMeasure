package measures;

import lpsolve.*;

import java.io.IOException;
import java.util.List;
import java.lang.Math;
import java.util.Scanner;

public class Calculation {
    static Scanner sc = new Scanner(System.in);

    /**
     * Caculate Drastic Inconsistency Measure by simple probabilistic logic constraint
     * deductive from Bayes theorem
     */
    public static double DrasticIM(KnowledgeBase k) {
        List<Constraint> cs = k.getConstraints();
        if (cs.size() == 3) {
            double p1 = cs.get(0).getProbability();
            double p2 = cs.get(1).getProbability();
            double p3 = cs.get(2).getProbability();
            if (p1 >= p2 * p3)
                return 0;
            else
                return 1;
        } else if (cs.size() == 2)
            if (Function.InconsistentTwoElementsConstraint(k))
                return 1;
        return 0;
    }

    /**
     * Calculate MI Inconsistency Measure by counting the element CMP in SMI(K)
     * that fulfills SMI(K) condition.
     */
    public static double MIIM(KnowledgeBase k) {
        List<Constraint> cs = k.getConstraints();
        KnowledgeBase cmp1 = new KnowledgeBase();
        KnowledgeBase cmp2 = new KnowledgeBase();
        KnowledgeBase cmp3 = new KnowledgeBase();

        cmp1 = k;
        cmp2.add(cs.get(0));
        cmp2.add(cs.get(2));
        cmp3.add(cs.get(1));
        cmp3.add(cs.get(2));

        int count = 0;
        if (DrasticIM(cmp1) == 1)
            count++;
        if (Function.InconsistentTwoElementsConstraint(cmp2))
            count++;
        if (Function.InconsistentTwoElementsConstraint(cmp3))
            count++;
        return count;
    }

    /**
     * Consider those fulfilled CMP above, calculate the SMIc Inconsistency Measure
     * by summing 1/ (the number of constraint of each fulfilled CMP)
     */
    public static double SMIcIM(KnowledgeBase k) {
        List<Constraint> cs = k.getConstraints();
        KnowledgeBase cmp1 = new KnowledgeBase();
        KnowledgeBase cmp2 = new KnowledgeBase();
        KnowledgeBase cmp3 = new KnowledgeBase();

        cmp1 = k;
        cmp2.add(cs.get(0));
        cmp2.add(cs.get(2));
        cmp3.add(cs.get(1));
        cmp3.add(cs.get(2));

        double im = 0;
        if (DrasticIM(k) == 1)
            im += (double) 1 / cmp1.getConstraints().size();
        if (Function.InconsistentTwoElementsConstraint(cmp2))
            im += (double) 1 / cmp2.getConstraints().size();
        ;
        if (Function.InconsistentTwoElementsConstraint(cmp3))
            im += (double) 1 / cmp3.getConstraints().size();
        ;
        return im;
    }

    /**
     * l-Inconsistency Measure
     * Solved by calculating Probability Function
     */
    public static double lIM(KnowledgeBase k) {
        return 1 - Function.lProbabilityFunction(k);
    }

    public static double XIM(KnowledgeBase k) {
        return (Function.SMC(k) + Function.SCC(k) - 1);
    }

    /**
     * μ- Inconsistency Measure
     * Solved by calculating Maximal Consistent subsets
     */
    public static double μIM(KnowledgeBase k) {
        List<Constraint> cs = k.getConstraints();
        double countk = cs.size();
        KnowledgeBase k1 = new KnowledgeBase();
        KnowledgeBase k2 = new KnowledgeBase();
        KnowledgeBase k3 = new KnowledgeBase();

        k1.add(cs.get(0));
        k1.add(cs.get(1));
        k2.add(cs.get(0));
        k2.add(cs.get(2));
        k3.add(cs.get(1));
        k3.add(cs.get(2));

        return countk - Function.MaxConsistentConstraint(k);
    }

    /**
     * Calculating Inconsistency Measure using Distance-based Measures
     * given a Consistent Knowledge Base
     */
    public static double DMIM(KnowledgeBase k, KnowledgeBase kc, int p) throws IOException {
        double z1, z2, z3;
        List<Constraint> cs = k.getConstraints();
        List<Constraint> csc = kc.getConstraints();

        z1 = Math.pow(Math.abs(cs.get(0).getProbability() - csc.get(0).getProbability()), p);
        z2 = Math.pow(Math.abs(cs.get(1).getProbability() - csc.get(1).getProbability()), p);
        z3 = Math.pow(Math.abs(cs.get(2).getProbability() - csc.get(2).getProbability()), p);
        return Math.pow((z1 + z2 + z3), (double) 1 / p);


    }

    public static double SUMIM(KnowledgeBase k, KnowledgeBase kc, int p) throws IOException {
        List<Constraint> cs = k.getConstraints();
        KnowledgeBase cmp1 = new KnowledgeBase();
        KnowledgeBase cmp2 = new KnowledgeBase();
        KnowledgeBase cmp3 = new KnowledgeBase();
        cmp1 = k;
        cmp2.add(cs.get(0));
        cmp2.add(cs.get(2));
        cmp3.add(cs.get(1));
        cmp3.add(cs.get(2));
        double sum = 0;
        if (DrasticIM(cmp1) == 1) {
            System.out.println(cmp1);
            sum += DMIM(cmp1, kc, p);
        }
        if (Function.InconsistentTwoElementsConstraint(cmp2)) {
            System.out.println(cmp2);
            sum += DMIM(cmp2, kc, p);
        }
        if (Function.InconsistentTwoElementsConstraint(cmp3)) {
            System.out.println(cmp3);
            sum += DMIM(cmp3, kc, p);
        }
        return sum;
    }

    public static double ProbalilisticShapley(KnowledgeBase k, int i) {
        List<KnowledgeBase> childkb = Function.SplitKnowledgeBase(k);
        int n = k.getConstraints().size();
        double result = 0;
        for (KnowledgeBase sk : childkb) {
            int m = sk.getConstraints().size();
            double temp1 = Function.Factorial(m - 1) * Function.Factorial(n - m);
            double temp2 = Function.Factorial(n);
            double temp3 = DrasticIM(sk) - DrasticIM(Function.Minus(sk, k.getConstraints().get(i - 1)));
            result += (double) temp1 * temp3 / temp2;
        }
        return result;
    }

    public static double SVIM(KnowledgeBase k) {
        int n = k.getConstraints().size();
        double max = 0;
        for (int i = 1; i <= n; i++) {
            if (ProbalilisticShapley(k, i) > max)
                max = ProbalilisticShapley(k, i);
        }
        return max;
    }

    /**
     * Unnormalized Inconsistency Measure
     * Using the Solver
     */
    public static double UnIM(KnowledgeBase k) {
        List<Constraint> cs = k.getConstraints();
        double p1 = cs.get(0).getProbability();
        double p2 = cs.get(1).getProbability();
        double p3 = cs.get(2).getProbability();

        try {
            LpSolve solver = LpSolve.makeLp(0, 10);

            solver.strAddConstraint("0 1 0 0 -1 0 0 0 0 0", LpSolve.LE, 1 - p1);
            solver.strAddConstraint("1 0 0 -1 0 0 0 0 0 0", LpSolve.LE, 1 - p2);
            solver.strAddConstraint("0 0 1 0 0 -1 0 0 0 0", LpSolve.LE, 1 - p3);
            solver.strAddConstraint("0 1 0 0 -1 0 0 0 0 0", LpSolve.GE, 0 - p1);
            solver.strAddConstraint("1 0 0 -1 0 0 0 0 0 0", LpSolve.GE, 0 - p2);
            solver.strAddConstraint("0 0 1 0 0 -1 0 0 0 0", LpSolve.GE, 0 - p3);

            solver.strAddConstraint("0 1 0 0 -1 0 -1 -1 0 0", LpSolve.EQ, 0 - p1);
            solver.strAddConstraint("1 0 0 -1 0 0 -1 0 -1 0", LpSolve.EQ, 0 - p2);
            solver.strAddConstraint("0 0 1 0 0 -1 -1 -1 0 0", LpSolve.EQ, 0 - p3);

            solver.strAddConstraint("0 0 0 0 0 0 1 1 1 1", LpSolve.EQ, 1);

            solver.strAddConstraint("0 0 0 0 0 0 1 0 0 0", LpSolve.GE, 0);
            solver.strAddConstraint("0 0 0 0 0 0 0 1 0 0", LpSolve.GE, 0);
            solver.strAddConstraint("0 0 0 0 0 0 0 0 1 0", LpSolve.GE, 0);
            solver.strAddConstraint("0 0 0 0 0 0 0 0 0 1", LpSolve.GE, 0);

            solver.strSetObjFn("1 1 1 1 1 1 0 0 0 0");

            solver.solve();


            return solver.getObjective();
        }
        catch (LpSolveException e) {
            e.printStackTrace();
        }
    return 0;
    }
}


