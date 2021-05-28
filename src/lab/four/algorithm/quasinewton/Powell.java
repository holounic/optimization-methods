package lab.four.algorithm.quasinewton;

import lab.four.util.DoubleMultiFunction;
import lab.four.util.LinearUtils;

public class Powell extends DFP {
    private double[] deltaXDot;

    public Powell(DoubleMultiFunction function, double[] x) {
        super(function, x);
    }

    @Override
    protected void updateX() {
        super.updateX();
        deltaXDot = LinearUtils.sum(deltaX, LinearUtils.mulMatrixVector(gMatrix, deltaW));
    }

    @Override
    protected void updateGMatrix(double[] v) {
        double[][] s = LinearUtils.mul(
                LinearUtils.mulMatrixMatrix(
                        LinearUtils.wrapEach(deltaXDot), LinearUtils.wrap(deltaXDot)),
                1 / LinearUtils.scalar(deltaW, deltaX));
        gMatrix = LinearUtils.sub(gMatrix, s);
    }
}
