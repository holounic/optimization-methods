package lab.four.algorithm.quasinewton;

import lab.four.util.DoubleMultiFunction;
import lab.four.util.FunctionUtils;
import lab.four.util.LinearUtils;
import lab.one.algorithm.Dichotomy;
import lab.one.util.Segment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleFunction;

public class DFP extends BaseQuasiNewton {
    private double alpha;
    private final List<Double> alphas = new ArrayList<>();

    private void updateAlpha() {
        DoubleFunction<Double> func = (a) -> function.apply(LinearUtils.sum(x, LinearUtils.mul(p, a)));
        alpha = new Dichotomy(func).apply(new Segment(-100, 100));
        alphas.add(alpha);
    }

    @Override
    protected void firstStep() {
        w = LinearUtils.negate(FunctionUtils.gradient(function, x));
        p = Arrays.copyOf(w, w.length);
        updateAlpha();
        updateX();
    }

    public DFP(DoubleMultiFunction function, double[] x) {
        super(function, x);
    }

    protected void updateX() {
        double[] prevX = Arrays.copyOf(x, x.length);
        x = LinearUtils.sum(x, LinearUtils.mul(p, alpha));
        deltaX = LinearUtils.sub(x, prevX);
    }

    protected void updateGMatrix(double[] v) {
        double[][] first = LinearUtils.mul(
                LinearUtils.mulMatrixMatrix(
                LinearUtils.wrap(deltaX), LinearUtils.wrapEach(deltaX)),
                1 / LinearUtils.scalar(deltaW, deltaX));
        double[][] second = LinearUtils.mul(LinearUtils.mulMatrixMatrix(
                LinearUtils.wrap(v), LinearUtils.wrapEach(v)),
                1 / LinearUtils.scalar(v, deltaW));
        gMatrix = LinearUtils.sub(LinearUtils.sub(gMatrix, first), second);
    }

    @Override
    protected void step() {
        double[] prevW = Arrays.copyOf(w, w.length);
        w = LinearUtils.negate(FunctionUtils.gradient(function, x));
        deltaW = LinearUtils.sub(w, prevW);
        double[] v = LinearUtils.mulMatrixVector(gMatrix, deltaW);
        updateGMatrix(v);

        p = LinearUtils.mulMatrixVector(gMatrix, w);
        updateAlpha();
    }

    public List<Double> getAlphas() {
        return alphas;
    }
}
