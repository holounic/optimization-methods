package lab.one.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class Segment {
    private final double from;
    private final double to;
    private List<Double> computedXs;

    public Segment(double from, double to) {
        if (from > to) {
            throw new IllegalArgumentException("segment length must be positive");
        }
        this.from = from;
        this.to = to;
        this.computedXs = new ArrayList<>();
    }

    public double from() {
        return from;
    }

    public double to() {
        return to;
    }

    public double computeX(DoubleBinaryOperator form) {
        double x = form.applyAsDouble(from, to);
        computedXs.add(x);
        return x;
    }

    public double length() {
        return to - from;
    }

    @Override
    public String toString() {
        return String.format("{segment: [%f, %f], computed: %s}", from, to, computedXs.toString());
    }
}
