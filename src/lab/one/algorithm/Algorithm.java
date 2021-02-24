package lab.one.algorithm;

import lab.one.util.Segment;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleFunction;

public abstract class Algorithm {

    protected static final double PRECISENESS = 0.001;
    protected int numUpdates;
    protected final double eps;
    protected final List<Segment> segments;
    protected final DoubleFunction<Double> func;

    protected abstract Segment step(Segment segment);
    protected abstract boolean done(Segment segment);
    protected abstract double getMinX(Segment segment);
    protected abstract void init(Segment segment);

    public double apply(Segment segment) {
        Segment currentSegment = segment;
        init(segment);
        while (!done(currentSegment)) {
            Segment newSegment = step(currentSegment);
            segments.add(currentSegment);
            currentSegment = newSegment;
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
        return String.format("Num updates:%d\nSegments:\n%s\n==============",
                numUpdates, segments.toString());
    }

}
