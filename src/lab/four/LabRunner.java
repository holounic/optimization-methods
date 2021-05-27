package lab.four;

import lab.four.algorithm.ClassyNewton;
import lab.four.algorithm.util.DoubleMultiFunction;

import java.util.Arrays;

public class LabRunner {
    static final DoubleMultiFunction f1 = (x) -> x[0] * x[0] + x[1] * x[1] - 1.2 * x[1] * x[0];
    static final double[] x1 = new double[]{4, 1};

    static final DoubleMultiFunction f2 = (x) -> 100 * Math.pow((x[1] - x[0] * x[0]), 2) + Math.pow((1 - x[0] * x[0]), 2);
    static final double[] x2 = new double[]{-1.2, 1};


    private static void testClassyNewton(DoubleMultiFunction f, double[] x) {
        ClassyNewton newton = new ClassyNewton(f, x);
        double[] ans = newton.optimize();
        System.out.println(Arrays.toString(ans));
        System.out.println("Num iterations" + newton.getIterations());
    }


    public static void main(String[] args) {
        testClassyNewton(f1, x1);
    }
}
