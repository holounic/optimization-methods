package lab.four.algorithm;

import lab.four.algorithm.util.DoubleMultiFunction;
import lab.four.algorithm.util.ScalarUtils;

public class DescentDirectionNewton extends UnarySearchNewton {
    public DescentDirectionNewton(DoubleMultiFunction function, double[] x) {
        super(function, x);
    }

    @Override
    protected void updateArgs(double[] p) {
        if (ScalarUtils.scalar(p, grad) < 0) {
            p = ScalarUtils.negate(grad);
        }
        super.updateArgs(p);
    }
}
