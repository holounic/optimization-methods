package lab.four.algorithm;

import lab.four.algorithm.util.DoubleMultiFunction;
import lab.four.algorithm.util.FunctionUtils;
import lab.four.algorithm.util.ScalarUtils;

import java.util.Arrays;

public class ClassyNewton {

    protected static final double EPS = 0.000000000001;
    protected final DoubleMultiFunction function;
    protected final double[] x;
    protected int iterations = 0;

    public ClassyNewton(DoubleMultiFunction function, double[] x) {
        this.function = function;
        this.x = x;
    }

    protected void updateArgs(double[] p) {
        for (int i = 0; i < p.length; i++) {
            x[i] += p[i];
        }
    }

    public double[] optimize() {
        double[] p, prevX;
        do {
            System.out.println(Arrays.toString(x));
            double[] grad = FunctionUtils.gradient(function, x);

            double[][] h = FunctionUtils.hessian(function, x);

            p = new LES(h, ScalarUtils.negate(grad)).solve();

            prevX = Arrays.copyOf(x, x.length);
            this.updateArgs(p);

            iterations++;
        } while (ScalarUtils.norm(ScalarUtils.diff(prevX, x)) > EPS);
        return x;
    }

    public int getIterations() {
        return iterations;
    }
}
