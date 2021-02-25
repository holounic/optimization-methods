package lab.one;

import lab.one.algorithm.Algorithm;
import lab.one.algorithm.Dichotomy;
import lab.one.algorithm.Fibonacci;
import lab.one.algorithm.GoldenRatio;
import lab.one.util.Segment;
import latex.TableBuilder;

import java.io.PrintStream;
import java.util.List;
import java.util.function.DoubleFunction;

public class LabRunner {

    private static final DoubleFunction<Double> FUNC =
            x -> -5 * Math.pow(x, 5) + 4 * Math.pow(x, 4) - 12 * Math.pow(x, 3) + 11 * x * x - 2 * x + 1;

    private static final double FROM = -0.5;
    private static final double TO = 0.5;

    private static void printAlgoStats(PrintStream out, Algorithm performedAlgo, double answer) {
        out.println(performedAlgo.getClass().getName());
        out.println("min x = " + answer);
        out.println("F(x) = " + FUNC.apply(answer));
        out.println("Num updates " + performedAlgo.getNumUpdates());
        new TableBuilder().algoTableBuilder(out, performedAlgo, FUNC);
        out.println("==========================");
    }
    private static final double eps = 0.00001;
    public static void main(String[] args) {
        List<Algorithm> algorithms = List.of(new Dichotomy(FUNC, eps), new GoldenRatio(FUNC, eps), new Fibonacci(FUNC, 8));

        for (Algorithm algorithm : algorithms) {
            double answer = algorithm.apply(new Segment(FROM, TO));
            printAlgoStats(System.out, algorithm, answer);
        }
    }
}
