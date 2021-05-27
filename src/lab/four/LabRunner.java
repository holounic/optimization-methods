package lab.four;

import lab.four.algorithm.newton.ClassyNewton;
import lab.four.algorithm.newton.DescentDirectionNewton;
import lab.four.algorithm.newton.UnarySearchNewton;
import lab.four.algorithm.quasinewton.DFP;
import lab.four.util.DoubleMultiFunction;
import java.util.Arrays;

public class LabRunner {
    static final DoubleMultiFunction f1 = (x) -> x[0] * x[0] + x[1] * x[1] - 1.2 * x[1] * x[0];
    static final double[] x1 = new double[]{4, 1};

    static final DoubleMultiFunction f2 = (x) -> 100 * Math.pow((x[1] - x[0] * x[0]), 2) + Math.pow((1 - x[0] * x[0]), 2);
    static final double[] x2 = new double[]{-1.2, 1};

    static final DoubleMultiFunction f3 = (x) -> Math.pow(x[1] + x[0], 2);
    static final double[] x3 = new double[]{2, 2};


    private static void testClassyNewton(DoubleMultiFunction f, double[] x) {
        ClassyNewton newton = new ClassyNewton(f, x);
        double[] ans = newton.optimize();
        System.out.println(Arrays.toString(ans));
        System.out.println("Num iterations: " + newton.getIterations());
    }

    private static void testUnarySearchNewton(DoubleMultiFunction f, double[] x) {
        UnarySearchNewton newton = new UnarySearchNewton(f, x);
        double[] ans = newton.optimize();
        System.out.println(Arrays.toString(ans));
        System.out.println("Num iterations: " + newton.getIterations());
    }

    private static void testDescentDirectionNewton(DoubleMultiFunction f, double[] x) {
        DescentDirectionNewton newton = new DescentDirectionNewton(f, x);
        double[] ans = newton.optimize();
        System.out.println(Arrays.toString(ans));
        System.out.println("Num iterations: " + newton.getIterations());
    }

    private static DoubleMultiFunction f4 = (x) -> Math.pow(x[0] * x[0] + x[1] - 11, 2) + Math.pow(x[0] + x[1] * x[1] - 7, 2);
    private static DoubleMultiFunction f5 = (x) -> Math.pow(x[0] + 10 * x[1], 2) + 5 * Math.pow(x[2] - x[3], 2)
            + Math.pow(x[1] - 2 * x[2], 4) + 10 * Math.pow(x[0] - x[3], 4);
    private static DoubleMultiFunction f6 = (x) -> 100 - 2 / (1 + Math.pow((x[0] - 1) / 2, 2) + Math.pow((x[1] - 2) / 2, 2))
            - 2 / (1 + Math.pow((x[0] - 2) / 2, 2) + Math.pow((x[1] - 1) / 3, 2));

    private static double[] x0 = new double[]{0, 0};

    private static void testDFP(DoubleMultiFunction f, double[] x) {
        DFP dfp = new DFP(f, x);
        double[] ans = dfp.optimize();
        System.out.println(Arrays.toString(ans));
        System.out.println("Num iterations: " + dfp.getIterations());
    }


    public static void main(String[] args) {
//        testClassyNewton(f1, x1);
//        testClassyNewton(f2, x2);
//        testClassyNewton(f3, x3);

//        testUnarySearchNewton(f1, x1);
//        testUnarySearchNewton(f2, x2);
//        testUnarySearchNewton(f3, x3);

//        testDescentDirectionNewton(f1, x1);
//        testDescentDirectionNewton(f2, x2);
        testDFP(f1, x1);
    }
}
