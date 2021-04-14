package lab.two.algorithm;

public class ConjugateGradients extends Function {
    private static double[] startPoint;
    private static double eps;

    @Override
    protected void init(double[] startPoint, double eps) {
        ConjugateGradients.startPoint = startPoint;
        ConjugateGradients.eps = eps;
    }

    private double GoldenRatio(double eps, double[] b, double ... a) {
        double left = 0;
        double right = 1e5;
        double x0 = left + 0.5 * (3 - Math.sqrt(5)) * (right - left);
        double x1 = right - x0 + left;

        while (Math.abs(right - left) > eps) {
            double xL = a[0] + x0 * b[0];
            double yL = a[1] + x0 * b[1];

            double xR = a[0] + x1 * b[0];
            double yR = a[1] + x1 * b[1];

            if (func(xL, yL) < func(xR, yR)) {
                right = x1;
            } else {
                left = x0;
            }
            x1 = x0;
            x0 = right + left - x1;
        }
        return (left + right) / 2;
    }

    private double innerproduct(double[] a, double[] b) {
        double ans = 0;
        for (int i = 0; i < a.length; i++) {
            ans += a[i] * b[i];
        } return ans;
    }

    private double[] calculateConjugateGradients(double x, double y, double eps) {
        boolean stop = false;
        int iter = 0;

        double[] p = gradient(x, y);
        for (int i = 0; i < p.length; i++) p[i] = -p[i];
        double[] grad = p;

        while (!stop) {
            iter += 1;

            double alpha = GoldenRatio(eps, p, x, y);
            x = x + alpha * p[0];
            y = y + alpha * p[1];

            double[] grad1 = gradient(x, y);
            for (int i = 0; i < grad1.length; i++) grad1[i] = -grad1[i];

            double beta = iter % 2 == 1 ? innerproduct(grad1, grad1) / innerproduct(grad, grad) : 0;
            for (int i = 0; i < p.length; i++) {
                p[i] = grad1[i] + beta * p[i];
            }

            grad = grad1;
            if (innerproduct(grad, grad) <= eps) {
                stop = true;
            }
        }

        return new double[]{x, y, iter};
    }

    @Override
    public double[] returnAns() {
        double x = startPoint[0];
        double y = startPoint[1];
        return calculateConjugateGradients(x, y, eps);
    }
}
