public class Camera {
    Vector focus;
    Vector normalToViewingPlane;
    Vector pointOnViewingPlane;

    Camera() {
        this.focus = new Vector(0.0, 0.0, 0.0);
        this.normalToViewingPlane = new Vector(0.0, 0.0, -1.0);
        this.pointOnViewingPlane = new Vector(0.0, 0.0, -100.0);
    }

    int[] checkPoint(Vector x, Vector d, int hs, int vs) {
        double t = normalToViewingPlane.dotProduct(pointOnViewingPlane.add(x.negative()))
                / normalToViewingPlane.dotProduct(d);
        Vector r = x.add(d.scalarMulti(t));
        int v1 = (int) Math.round(r.x) * hs + 450;
        int v2 = (int) Math.round(r.y) * vs + 450;
        return new int[] { v1, v2 };
    }
}
