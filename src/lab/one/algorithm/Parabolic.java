package lab.one.algorithm;

import lab.one.util.Segment;


import java.util.function.DoubleFunction;

public class Parabolic extends Algorithm {

    public Parabolic(DoubleFunction<Double> func, double eps) {
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
