public class Camera {
    double[] focus;
    double[] normalToViewingPlane;
    double[] pointOnViewingPlane;

    Camera() {
        this.focus = new double[]{0.0, 0.0, 0.0};
        this.normalToViewingPlane = new double[]{0.0, 0.0, -1.0};
        this.pointOnViewingPlane = new double[]{0.0, 0.0, -100.0};
    }

    int[] checkPoint(double[] x, double[] d, int hs, int vs) {
        double t = Vector.dotProduct(Vector.add(pointOnViewingPlane, Vector.negate(x)), normalToViewingPlane) / Vector.dotProduct(d, normalToViewingPlane);
        double[] r = Vector.add(x, Vector.scalarMultiply(d, t));
        int v1 = (int) Math.round(r[0]) * hs + 450;
        int v2 = (int) Math.round(r[1]) * vs + 450;
        return new int[]{v1, v2};
    }
}
