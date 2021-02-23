package lab.one;

import java.util.function.DoubleFunction;

public class LabRunner {

    private static final DoubleFunction<Double> FUNC =
            x -> -5 * Math.pow(x, 5) + 4 * Math.pow(x, 4) - 12 * Math.pow(x, 3) + 11 * x * x - 2 * x + 1;

    private static final double FROM = -0.5;
    private static final double TO = 0.5;

    public static void main(String[] args) {

    }
}
