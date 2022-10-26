public class Sphere {
    double[] pos;
    double radius;

    Sphere(double x, double y, double z, double radius) {
        this.pos = new double[]{x, y, z};
        this.radius = radius;
    }

    double checkRayCollision(double[] x, double[] dir) {
        //t^2(d.d) + 2t(d.(x-c)) + (x-c).(x-c) - r^2 = 0
        double[] x_c = Vector.add(x, Vector.negate(pos));
        double b = 2.0 * (Vector.dotProduct(dir, x_c));
        double discriminant = b * b - 4.0 * (Vector.dotProduct(x_c, x_c) - radius * radius);
        if (discriminant < 0.0) {
            return -1;
        } else {
            return Math.min(Math.abs((-b + Math.sqrt(discriminant)) / 2.0), Math.abs((-b - Math.sqrt(discriminant)) / 2.0));
        }
    }

    void move(double[] velocity) {
        pos[0] += velocity[0];
        pos[1] += velocity[1];
        pos[2] += velocity[2];
    }
}
