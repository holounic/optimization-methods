package lab.one.algorithm;

import lab.one.util.Segment;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleFunction;

public abstract class Algorithm {
    protected int numUpdates;
    protected final double eps;
    protected final List<Segment> segments;
    protected final DoubleFunction<Double> func;

    protected abstract Segment step(Segment segment);
    protected abstract boolean done(Segment segment);
    protected abstract double getMinX(Segment segment);

    public double apply(Segment segment) {
        Segment currentSegment = segment;
        while (!done(currentSegment)) {
            currentSegment = step(currentSegment);
            segments.add(currentSegment);
            ++numUpdates;
        }
        return getMinX(currentSegment);
    }

    public Algorithm(DoubleFunction<Double> func, double eps) {
        this.eps = eps;
        this.numUpdates = 0;
        this.func = func;
        segments = new ArrayList<>();
    }

    public String getStats() {
        return String.format("==============\nNum updates:%s\nSegments:\n%s", numUpdates, segments.toString());
    }

}
