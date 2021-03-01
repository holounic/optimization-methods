package lab.one.util;

import lab.one.algorithm.*;

import java.util.List;
import java.util.function.DoubleFunction;

public class Task {
    private static final DoubleFunction<Double> FUNC =
            x -> -5 * Math.pow(x, 5) + 4 * Math.pow(x, 4) - 12 * Math.pow(x, 3) + 11 * x * x - 2 * x + 1;
    private static final double FROM = -0.5;
    private static final double TO = 0.5;

    private final Algorithm algorithm;

    public Task(AlgoName algoName) {
        this.algorithm = switch (algoName) {
            case BRENT -> new Brent(FUNC);
            case FIBONACCI -> new Fibonacci(FUNC);
            case DICHOTOMY -> new Dichotomy(FUNC);
            case PARABOLIC -> new Parabolic(FUNC);
            case GOLDEN_RATIO -> new GoldenRatio(FUNC);
        };
    }

    public double f(double x) {
        return FUNC.apply(x);
    }

    public double runAlgorithm() {
        return algorithm.apply(new Segment(FROM, TO));
    }

    public List<Segment> getStats() {
        return algorithm.getSegments();
    }

}
