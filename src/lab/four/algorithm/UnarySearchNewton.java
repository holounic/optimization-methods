package lab.four.algorithm;

import lab.four.algorithm.util.DoubleMultiFunction;
import lab.four.algorithm.util.ScalarUtils;
import lab.one.algorithm.Dichotomy;
import lab.one.util.Segment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleFunction;

public class UnarySearchNewton extends ClassyNewton {
    private List<Double> params = new ArrayList<>();

    public UnarySearchNewton(DoubleMultiFunction function, double[] x) {
        super(function, x);
    }

    protected void updateArgs(double[] p) {
        DoubleFunction<Double> func = (a) -> function.apply(ScalarUtils.sum(x, ScalarUtils.mul(p, a)));
        double alpha = new Dichotomy(func).apply(new Segment(-100, 100));
        params.add(alpha);
        double[] res = ScalarUtils.mul(p, alpha);
        for (int i = 0; i < x.length; i++) {
            x[i] += res[i];
        }
    }

    public List<Double> getParams() {
        return params;
    }

}
