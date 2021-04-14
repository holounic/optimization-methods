package lab.two.algorithm;

public class SteepestDescent extends Function {
    private static double[] startPoint;
    private static double eps;

    private double[] GoldenRatio(double eps, double[] a, double[] b) {
        double t1 = 0.381966, t2 = 1 - t1;

        double[] x1 = new double[]{
                a[0] + (b[0] - a[0]) * t1,
                a[1] + (b[1] - a[1]) * t1
        };
        double[] x2 = new double[]{
                a[0] + (b[0] - a[0]) * t2,
                a[1] + (b[1] - a[1]) * t2
        };

        double f1 = func(x1[0] - eps, x1[1] - eps);
        double f2 = func(x2[0] + eps, x2[1] + eps);

        while (Math.sqrt((b[0] - a[0]) * (b[0] - a[0]) + (b[1] - a[1]) * (b[1] - a[1])) > eps) {
            if (f1 < f2) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1[0] = a[0] + (b[0] - a[0]) * t1;
                x1[1] = a[1] + (b[1] - a[1]) * t1;
                f1 = func(x1[0], x1[1]);
            } else {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2[0] = a[0] + (b[0] - a[0]) * t2;
                x2[1] = a[1] + (b[1] - a[1]) * t2;
                f2 = func(x2[0], x2[1]);
            }
        }
        return new double[]{(a[0] + b[0]) / 2, (a[1] + b[1]) / 2};
    }

    private double[] calculateSteepestDescent(double x, double y, double eps) {
        boolean stop = false;
        int iter = 0;
        double x0 = x, y0 = y;

        while (!stop) {
            double[] grad = gradient(x0, y0);
            double[] point = GoldenRatio(
                    eps,
                    new double[]{x0, y0},
                    new double[]{-grad[0], -grad[1]});

            double x1 = point[0], y1 = point[1];
            double dist = Math.pow((x1 - x0), 2) + Math.pow((y1 - y0), 2);

            if (dist < eps * eps && Math.abs(func(x0, y0) - func(x1, y1)) < eps) {
                stop = true;
            }

            x0 = x1;
            y0 = y1;
            iter += 1;
        }
        return new double[]{x0, y0, iter};
    }


    @Override
    protected void init(double[] startPoint, double eps) {
        SteepestDescent.startPoint = startPoint;
        SteepestDescent.eps = eps;
    }

    @Override
    protected double[] returnAns() {
        double x = startPoint[0];
        double y = startPoint[1];
        return calculateSteepestDescent(x, y, eps);
    }
}
