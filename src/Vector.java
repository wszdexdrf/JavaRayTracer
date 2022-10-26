public class Vector {
    static double dotProduct(double[] a, double[] b) {
        return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
    }

    static double[] add(double[] a, double[] b) {
        return new double[] { a[0] + b[0], a[1] + b[1], a[2] + b[2] };
    }

    static void addToA(double[] a, double[] b) {
        a[0] = a[0] + b[0];
        a[1] = a[1] + b[1];
        a[2] = a[2] + b[2];
    }

    static double[] negate(double[] a) {
        return new double[] { -a[0], -a[1], -a[2] };
    }

    static void normalize(double target, double[] a) {
        double magnitude = Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[2] * a[2]);
        a[0] = a[0] / magnitude * target;
        a[1] = a[1] / magnitude * target;
        a[2] = a[2] / magnitude * target;
    }

    static double[] scalarMultiply(double[] a, double scalar) {
        return new double[] { a[0] * scalar, a[1] * scalar, a[2] * scalar };
    }

    static void display(double[] a) {
        System.out.println(a[0] + ", " + a[1] + ", " + a[2]);
    }
}
