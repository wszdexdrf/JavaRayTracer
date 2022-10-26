public class Vector {
    double x, y, z;

    Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    void createFromPolarCoordinates(double r, double phi, double theta) {
        this.x = r * Math.sin(theta) * Math.cos(phi);
        this.y = r * Math.sin(theta) * Math.sin(phi);
        this.z = r * Math.cos(theta);
    }

    double dotProduct(Vector b) {
        return this.x * b.x + this.y * b.y + this.z * b.z;
    }

    Vector scalarMulti(double lambda) {
        return new Vector(lambda * x, lambda * y, lambda * z);
    }

    Vector add(Vector b) {
        return new Vector(this.x + b.x, this.y + b.y, this.z + b.z);
    }

    Vector negative() {
        return new Vector(-x, -y, -z);
    }

    void addToSelf(Vector b) {
        this.x += b.x;
        this.y += b.y;
        this.z += b.z;
    }

    void rotateZ(double cosTheta, double sinTheta) {
        this.x = x * cosTheta - y * sinTheta;
        this.y = x * sinTheta + y * cosTheta;
    }

    void rotateY(double cosTheta, double sinTheta) {
        this.z = z * cosTheta - x * sinTheta;
        this.x = z * sinTheta + x * cosTheta;
    }

    void rotateX(double cosTheta, double sinTheta) {
        this.y = y * cosTheta - z * sinTheta;
        this.z = y * sinTheta + z * cosTheta;
    }

    void normalize(double magnitude) {
        double curMagn = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        this.x = this.x * magnitude / curMagn;
        this.y = this.y * magnitude / curMagn;
        this.z = this.z * magnitude / curMagn;
    }
}
