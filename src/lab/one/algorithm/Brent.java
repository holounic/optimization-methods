package lab.one.algorithm;

import lab.one.util.Segment;
import java.util.function.DoubleFunction;

public class Brent extends Algorithm {
    private static final double COEFF = (3 - Math.sqrt(5)) / 2;

    public Brent(DoubleFunction<Double> func, double eps) {
        super(func, eps);
    }

    @Override
    protected Segment step(Segment segment) {
        return null;
    }

    @Override
    protected boolean done(Segment segment) {
        return false;
    }

    @Override
    protected double getMinX(Segment segment) {
        return 0;
    }

    @Override
    protected void init(Segment segment) {

    }
}
