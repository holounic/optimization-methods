package lab.one.algorithm;

import lab.one.util.Segment;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;

public class Brent extends Algorithm {
    private static final double COEFF = (3 - Math.sqrt(5)) / 2;

    public Brent(DoubleFunction<Double> func, double eps) {
        super(func, eps);
    }

    double x, w, v, u, fx, fw, fv, d, e;
    boolean initializedU = false;

    private boolean areDifferent(double w, double u, double v) {
        return !((w == u) || (w == v) || (u == v));
    }

    private double computeParabolicMin(double x1, double x2, double x3, double y1, double y2, double y3) {
        return x2 - 0.5 * (Math.pow((x2 - x1), 2) * (y2 - y3) - Math.pow((x2 - x3), 2) * (y2 - y1)) / (
                (x2 - x1) * (y2 - y3) - (x2 - x3) * (y2 - y1));
    }

    private final DoubleBinaryOperator xForm = (a, b) -> computeParabolicMin(x, w, v, fx, fw, fv);

    @Override
    protected Segment step(Segment segment) {
        double g = e;
        e = d;

//        double tol = Math.abs(x) * eps + eps / 10;

        if (areDifferent(x, w, v) && areDifferent(fx, fw, fv)) {
            u = segment.computeX(xForm);
            initializedU = true;
        }
        if (initializedU && u >= segment.from() + eps && u <= segment.to() - eps && Math.abs(u - x) < g / 2) {
            d = Math.abs(u - x);
            return segment;
        }
        initializedU = true;
        if (x < (segment.to() + segment.from()) / 2) {
            u = segment.computeX((a, b) -> x + COEFF * (b - x));
            d = segment.computeX((a, b) -> b - x);
        } else {
            u = segment.computeX((a, b) -> x - COEFF * (x - a));
            d = segment.computeX((a, b) -> x - a);
        }
        if (Math.abs(u - x) < eps) {
            u = (u - x >= 0 ? x + eps : x - eps);
        }
        double fu = func.apply(u);
        segment.computedF(fu);
        double updatedFrom, updatedTo;
        if (fu <= fx) {
            if (u >= x) {
                updatedFrom = x;
                updatedTo = segment.to();
            } else {
                updatedTo = x;
                updatedFrom = segment.from();
            }
            v = w;
            w = x;
            x = u;
            fv = fw;
            fw = fx;
            fx = fu;
        } else {
            if (u >= x) {
                updatedTo = x;
                updatedFrom = segment.from();
                System.out.println("4");
            } else {
                System.out.println("3");
                updatedFrom = x;
                updatedTo = segment.to();
            }
            if (fu <= fw || w == x) {
                System.out.println("2");
                v = w;
                w = u;
                fv = fw;
                fw = fu;
            } else if (fu <= fv || v == x || v == w) {
                System.out.println("1");
                v = u;
                fv = fu;
            }
        }
        return new Segment(updatedFrom, updatedTo);

    }

    @Override
    protected boolean done(Segment segment) {
        return (d - segment.from() <= eps || segment.length() <= eps);
    }

    @Override
    protected void init(Segment segment) {
        x = segment.computeX((a, b) -> a + segment.length() * COEFF);
        w = x;
        v = x;
        fx = func.apply(x);
        fx = fw;
        fv = fx;
        segment.computedF(fx);
        d = segment.length();
        e = segment.length();
    }
}
