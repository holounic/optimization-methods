package lab.one;

import lab.one.algorithm.*;
import lab.one.util.Segment;
import latex.TableBuilder;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleFunction;

public class LabRunner {

    private static final PrintStream out = System.out;
    private static final TableBuilder tableBuilder = new TableBuilder();

    private static final DoubleFunction<Double> FUNC =
            x -> -5 * Math.pow(x, 5) + 4 * Math.pow(x, 4) - 12 * Math.pow(x, 3) + 11 * x * x - 2 * x + 1;

    private static final double FROM = -0.5;
    private static final double TO = 0.5;
    private static final double EPS = 0.00001;

    private static void printAlgoStats(Algorithm performedAlgo, double answer) {
        out.println(performedAlgo.getClass().getName());
        out.println("min x = " + answer);
        out.println("F(x) = " + FUNC.apply(answer));
        out.println("Num updates " + performedAlgo.getNumUpdates());
        tableBuilder.algoTable(out, performedAlgo, FUNC);
        out.println("==========================");
    }

    private static final List<Algorithm> algorithms = List.of(
            new Parabolic(FUNC, EPS),
            new Dichotomy(FUNC, EPS),
            new GoldenRatio(FUNC, EPS),
            new Fibonacci(FUNC, 8));

    private static void runAllAlgorithms() {
        for (Algorithm algorithm : algorithms) {
            runAlgorithm(algorithm);
        }
    }

    private static void runAlgorithm(Algorithm algorithm) {
        out.println(algorithm.getClass().getName());
        double answer = algorithm.apply(new Segment(FROM, TO));
        printAlgoStats(algorithm, answer);
    }

    private static final double MIN_EPS = 0.000001;
    private static final double MAX_EPS = 0.1;

    private static void epsAndNumUpdates(int numSamples) {
        double epsDiff = (MAX_EPS - MIN_EPS) / numSamples;
        List<Double> epsValues = new ArrayList<>();
        List<Integer> numUpdValues = new ArrayList<>();
        Algorithm algorithm;

        double curEps = MIN_EPS;
        while (curEps < MAX_EPS) {
            algorithm = new Dichotomy(FUNC);
            Segment segment = new Segment(FROM, TO);
            algorithm.apply(segment);
            epsValues.add(Math.log(curEps));
            numUpdValues.add(algorithm.getNumUpdates());
            curEps += epsDiff;
        }
        tableBuilder.epsNumItersTable(out, numUpdValues, epsValues);
    }


    public static void main(String[] args) {
        runAlgorithm(new Parabolic(FUNC, EPS));
    }
}
