package latex;

import lab.one.algorithm.Algorithm;
import lab.one.util.Segment;
import java.io.PrintStream;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;

public class TableBuilder {
    public void algoTableBuilder(PrintStream out, Algorithm performedAlgo, DoubleFunction<Double> func) {
        List<Segment> segments = performedAlgo.getSegments();
        // & segment & length & x & f(x)
        for (Segment segment : segments) {
            String xs =
                    segment.getComputedXs().stream().map(Object::toString).collect(Collectors.joining(", "));
            String fs =
                    segment.getComputedFs().stream().map(Object::toString).collect(Collectors.joining(", "));
            out.println(String.format("& [%f, %f] & %f & %s & %s \\\\",
                    segment.from(), segment.to(), segment.length(), xs, fs));
        }
    }
}
