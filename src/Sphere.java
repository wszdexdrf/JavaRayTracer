public class Sphere {
    Vector pos;
    double radius;

    Sphere(double x, double y, double z, double radius) {
        this.pos = new Vector(x, y, z);
        this.radius = radius;
    }

    double checkRayCollision(Vector x, Vector dir) {
        // t^2(d.d) + 2t(d.(x-c)) + (x-c).(x-c) - r^2 = 0
        Vector x_c = x.add(pos.negative());
        double b = 2.0 * dir.dotProduct(x_c);
        double discriminant = b * b - 4.0 * (x_c.dotProduct(x_c) - radius * radius);
        if (discriminant < 0.0) {
            return -1;
        } else {
            return Math.min(Math.abs((-b + Math.sqrt(discriminant)) / 2.0),
                    Math.abs((-b - Math.sqrt(discriminant)) / 2.0));
        }
    }

    void move(Vector velocity) {
        pos.addToSelf(velocity);
    }
}
