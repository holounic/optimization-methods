package lab.one.algorithm;

import lab.one.util.Segment;
import java.util.function.DoubleFunction;
import java.util.function.LongToDoubleFunction;

public class Fibonacci extends Algorithm {

    private static final double SQRT_FIVE = Math.sqrt(5);
    private static final double FIRST_FIB_SUM = (1 + SQRT_FIVE) / 2;
    private static final double SECOND_FIB_SUM = (1 - SQRT_FIVE) / 2;

    private final long n;
    private static final LongToDoubleFunction nthFib =
            x -> (Math.pow(FIRST_FIB_SUM, x) - Math.pow(SECOND_FIB_SUM, x)) / SQRT_FIVE;
    private static final LongToDoubleFunction nthDivNp2Fib =
            x -> nthFib.applyAsDouble(x) / nthFib.applyAsDouble(x + 2);
    private static final LongToDoubleFunction np1DivNp2Fib =
            x -> nthFib.applyAsDouble(x + 1) / nthFib.applyAsDouble(x + 2);

    public Fibonacci(DoubleFunction<Double> func, double eps, long n) {
        super(func, eps);
        this.n = n;
    }

    private double x1, x2;

    @Override
    protected Segment step(Segment segment) {
        double f1 = func.apply(x1);
        double f2 = func.apply(x2);

        if (f1 <= f2) {
            double updatedFrom = segment.from();
            double updatedTo = x2;
            x1 = x2;
            x2 = segment.computeX((x, y) -> x + np1DivNp2Fib.applyAsDouble(n - numUpdates + 1) * (y - x));
            return new Segment(updatedFrom, updatedTo);
        }

        double updatedFrom = x1;
        double updatedTo = segment.to();
        x2 = x1;
        x1 = segment.computeX((x, y) -> x + nthDivNp2Fib.applyAsDouble(n - numUpdates + 1) * (y - x));
        return new Segment(updatedFrom, updatedTo);
    }

    @Override
    protected boolean done(Segment segment) {
        return numUpdates == n;
    }

    @Override
    protected double getMinX(Segment segment) {
        return (segment.to() + segment.from()) / 2;
    }

    @Override
    protected void init(Segment segment) {
        x1 = segment.from()
                + (nthDivNp2Fib.applyAsDouble(n)) * (segment.to() - segment.to());
        x2 = segment.from() + segment.to() - x1;
    }
}
