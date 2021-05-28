package lab.four.marquardt;

import lab.four.util.*;
import java.util.Arrays;

public class Marquardt extends MultiOptimizationMethod {
    protected static final double BETA = 0.0001;
    protected double tau;
    protected double[] p;
    protected double[] y;

    public Marquardt(DoubleMultiFunction function, double[] x, double tau) {
        super(function, x);
        this.tau = tau;
    }

    @Override
    protected void step() {
        double[] grad = FunctionUtils.gradient(function, x);
        double[][] h = FunctionUtils.hessian(function, x);
        double tauDot = tau * BETA;
        double baseVal = function.apply(x);
        do {
            tauDot /= BETA;
            p = new LES(LinearUtils.sum(h, LinearUtils.I(n, tauDot)), LinearUtils.negate(grad)).solve();
            y = LinearUtils.sum(x, p);
        } while (function.apply(y) > baseVal);
        updateX();
        tau *= BETA;
    }

    @Override
    protected void firstStep() { }

    @Override
    protected boolean done() {
        return LinearUtils.norm(p) < EPS;
    }

    @Override
    protected void updateXInner() {
        x = Arrays.copyOf(y, y.length);
    }
}
