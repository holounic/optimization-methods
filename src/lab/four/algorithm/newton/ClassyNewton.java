package lab.four.algorithm.newton;

import lab.four.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassyNewton implements MultiOptimizationMethod {

    protected static final double EPS = 0.00001;
    protected final DoubleMultiFunction function;
    protected final double[] x;
    protected double[] grad;
    protected int iterations = 0;
    protected List<double[]> computedValues = new ArrayList<>();

    public ClassyNewton(DoubleMultiFunction function, double[] x) {
        this.function = function;
        this.x = x;
    }

    protected void updateArgs(double[] p) {
        for (int i = 0; i < p.length; i++) {
            x[i] += p[i];
        }
    }

    @Override
    public double[] optimize() {
        double[] p, prevX;
        do {
            System.out.println(Arrays.toString(x));
            grad = FunctionUtils.gradient(function, x);

            double[][] h = FunctionUtils.hessian(function, x);

            p = new LES(h, LinearUtils.negate(grad)).solve();

            prevX = Arrays.copyOf(x, x.length);
            computedValues.add(x);
            this.updateArgs(p);

            iterations++;
        } while (LinearUtils.norm(LinearUtils.sub(prevX, x)) > EPS);
        return x;
    }

    public int getIterations() {
        return iterations;
    }

    public List<double[]> getComputedValues() {
        return computedValues;
    }
}
