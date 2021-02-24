package lab.one;

import lab.one.algorithm.Algorithm;
import lab.one.algorithm.Dichotomy;
import lab.one.algorithm.Fibonacci;
import lab.one.algorithm.GoldenRatio;
import lab.one.util.Segment;
import java.util.List;
import java.util.function.DoubleFunction;

public class LabRunner {

    private static final DoubleFunction<Double> FUNC =
            x -> -5 * Math.pow(x, 5) + 4 * Math.pow(x, 4) - 12 * Math.pow(x, 3) + 11 * x * x - 2 * x + 1;

    private static final double FROM = -0.5;
    private static final double TO = 0.5;

    public static void main(String[] args) {
        List<Algorithm> algorithms = List.of(new Dichotomy(FUNC), new GoldenRatio(FUNC), new Fibonacci(FUNC));

        for (Algorithm algorithm : algorithms) {
            algorithm.apply(new Segment(FROM, TO));
            System.out.println(algorithm.getClass().getName());
            System.out.println(algorithm.getStats());
        }
    }
}
