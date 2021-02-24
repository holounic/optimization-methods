package lab.one.algorithm;

import lab.one.util.Segment;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;

public class GoldenRatio extends Algorithm {

    private static final double SQRT_FIVE = Math.sqrt(5);
    private static final double THETA = (SQRT_FIVE - 1) / 2;

    public GoldenRatio(DoubleFunction<Double> func, double eps) {
        super(func, eps);
    }

    public GoldenRatio(DoubleFunction<Double> func) {
        this(func, PRECISENESS);
    }

    private double x1, x2;
    private static final DoubleBinaryOperator xForm = (x, y) -> y - THETA * (y - x);

    @Override
    protected Segment step(Segment segment) {
        double f1 = func.apply(x1);
        double f2 = func.apply(x2);

        if (f1 <= f2) {
            double updatedFrom = segment.from();
            double updatedTo = x2;

            x2 = x1;
            x1 = segment.computeX(xForm);
            new Segment(updatedFrom, updatedTo);
        }

        double updatedFrom = x1;
        double updatedTo = segment.to();

        x1 = x2;
        x2 = segment.computeX(xForm);

        return new Segment(updatedFrom, updatedTo);

    }

    @Override
    protected boolean done(Segment segment) {
        return THETA * segment.length() <= this.eps;
    }

    @Override
    protected double getMinX(Segment segment) {
        return (segment.from() + segment.from()) / 2;
    }

    @Override
    protected void init(Segment segment) {
        x1 = segment.computeX((x, y) -> (y - x) * (3 - SQRT_FIVE) / 2);
        x2 = segment.computeX((x, y) -> (y - x) * THETA);
    }
}
