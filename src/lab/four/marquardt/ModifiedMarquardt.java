package lab.four.marquardt;

import lab.four.util.*;
import java.util.Arrays;

public class ModifiedMarquardt extends Marquardt {
    protected double tau;
    protected double[] p;
    protected double[] y;

    public ModifiedMarquardt(DoubleMultiFunction function, double[] x) {
        super(function, x, 0);
    }

    @Override
    protected void step() {
        double[] grad = FunctionUtils.gradient(function, x);
        double[][] h = FunctionUtils.hessian(function, x);
        p = new LES(LinearUtils.sum(h, LinearUtils.I(n, tau)), LinearUtils.negate(grad)).solve();
        y = LinearUtils.sum(x, p);
        updateX();
        double[][] right = LinearUtils.sum(h, LinearUtils.I(n, tau));
        double[][] L = new Cholesky(right).decompose();
        while (LinearUtils.equal(right, LinearUtils.mulMatrixMatrix(L, LinearUtils.transpose(L)))) {
            tau = Math.max(1, 2 * tau);
        }
    }
}