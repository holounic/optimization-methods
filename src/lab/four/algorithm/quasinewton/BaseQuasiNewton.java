package lab.four.algorithm.quasinewton;

import lab.four.util.DoubleMultiFunction;
import lab.four.util.LinearUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseQuasiNewton {
    protected static final double EPS = 0.00001;
    protected final DoubleMultiFunction function;
    protected double[] x;
    protected double[] deltaX;
    protected double[] p;
    protected double[] w;
    protected double[] deltaW;
    protected double[][] gMatrix;
    protected int iterations = 0;
    protected List<double[]> computedValues = new ArrayList<>();


    protected abstract void firstStep();
    protected abstract void step();

    public BaseQuasiNewton(DoubleMultiFunction function, double[] x) {
        this.function = function;
        this.x = x;
        this.gMatrix = LinearUtils.I(x.length);
    }

    public double[] optimize() {
        firstStep();
        do {
            step();
            iterations++;
        } while (LinearUtils.norm(deltaX) > EPS);
        return x;
    }

    public int getIterations() {
        return iterations;
    }
}
