package lab.four.algorithm.util;

public class ScalarUtils {
    private ScalarUtils() {}

    public static double scalar(double[] a, double[] b) {
        double res = 0;
        for (int i = 0; i < a.length; i++) {
            res += a[i] * b[i];
        }
        return res;
    }

    public static double[][] mulMatrix(double[][] a, double[] s) {
        double[][] ans = new double[a.length][s.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
               ans[i][j] = s[j] * a[i][j];
            }
        }
        return ans;
    }

    public static void negate(double[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] *= -1;
        }
    }

    public static void negate(double[][] a) {
        for (double[] doubles : a) {
            negate(doubles);
        }
    }

    public static double[] diff(double[] a, double[] b) {
        double[] res = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            res[i] = a[i] - b[i];
        }
        return res;
    }

    public static double norm(double[] s) {
        double sum = 0;
        for (double c : s) {
            sum += c * c;
        }
        return Math.sqrt(sum);
    }

}
