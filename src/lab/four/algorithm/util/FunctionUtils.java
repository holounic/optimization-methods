package lab.four.algorithm.util;

import java.util.Arrays;

public class FunctionUtils {
    private FunctionUtils() {}
    private static final double GRADIENT_STEP = 0.01;


    public static double[] gradient(DoubleMultiFunction function, double[] vals) {
        double baseVal = function.apply(vals);
        double[] g = new double[vals.length];
        double[] plus = Arrays.copyOf(vals, vals.length);
        double[] minus = Arrays.copyOf(vals, vals.length);

        for (int i = 0; i < vals.length; i++) {
            plus[i] += GRADIENT_STEP;
            minus[i] -= GRADIENT_STEP;
            g[i] = (function.apply(plus) - function.apply(minus)) / (2 * GRADIENT_STEP);
            plus[i] -= GRADIENT_STEP;
            minus[i] += GRADIENT_STEP;
        }
        return g;
    }

    public static double[][] hessian(DoubleMultiFunction function, double[] vals) {
        double baseVal = function.apply(vals);
        double[] plusShift = new double[vals.length];
        double[] minusShift = new double[vals.length];

        double[][] hesse = new double[vals.length][vals.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] += GRADIENT_STEP;
            plusShift[i] = function.apply(vals);
            vals[i] -=  2 * GRADIENT_STEP;
            minusShift[i] = function.apply(vals);
            vals[i] += GRADIENT_STEP;
            hesse[i][i] = (plusShift[i] + 2 * baseVal + minusShift[i]) / (GRADIENT_STEP * GRADIENT_STEP);
        }
        for (int i = 0; i < vals.length; i++) {
            for (int j = i + 1; j < vals.length; j++) {
                vals[i] += GRADIENT_STEP;
                vals[j] += GRADIENT_STEP;
                double x = function.apply(vals);
                vals[i] -= GRADIENT_STEP;
                vals[j] -= GRADIENT_STEP;
                hesse[i][j] = (x - plusShift[i] - plusShift[j] + baseVal) / (GRADIENT_STEP * GRADIENT_STEP);
                hesse[j][i] = hesse[i][j];
            }
        }
        return hesse;
    }
}
