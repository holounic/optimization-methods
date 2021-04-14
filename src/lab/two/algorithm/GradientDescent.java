package lab.two.algorithm;

public class GradientDescent extends Function {
    private static double[] startPoint;
    private static double eps;

    private double[] calculateGradientDescent(double x, double y, double eps) {
        boolean stop = false;
        double lambda = 0.01;
        int iter = 0;
        double x0 = x, y0 = y;

        while (!stop) {
            double[] grad = gradient(x0, y0);
            double x1 = x0 - grad[0] * lambda;
            double y1 = y0 - grad[1] * lambda;
            double dist = (x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0);

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
        GradientDescent.startPoint = startPoint;
        GradientDescent.eps = eps;
    }

    @Override
    protected double[] returnAns() {
        double x = startPoint[0];
        double y = startPoint[1];
        return calculateGradientDescent(x, y, eps);
    }
}