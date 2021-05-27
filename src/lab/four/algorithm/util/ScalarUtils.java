package lab.four.algorithm.util;

import java.util.Arrays;

public class ScalarUtils {
    private ScalarUtils() {}

    public static double[] mul(double[] s, double x) {
        double[] res = Arrays.copyOf(s, s.length);
        for (int i = 0; i < s.length; i++) {
            res[i] *= x;
        }
        return res;
    }

    public static double[] sum(double[] a, double[] b) {
        double[] res = Arrays.copyOf(a, a.length);
        for (int i = 0; i < a.length; i++) {
            res[i] += b[i];
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

    public static double[] negate(double[] a) {
        double[] b = Arrays.copyOf(a, a.length);
        for (int i = 0; i < b.length; i++) {
            b[i] *= -1;
        }
        return b;
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

    public static double[][] transpose(double[][] m) {
        double[][] t = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                t[i][j] = m[j][i];
            }
        }
        return t;
    }

    public static double scalar(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

}
