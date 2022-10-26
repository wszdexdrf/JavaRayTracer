public class Thing {
    Vector pos;
    Vector[] n;
    double dimension;

    Thing(int x, int y, int z, Vector n1, Vector n2, Vector n3, double dimension) {
        pos = new Vector(x, y, z);
        n = new Vector[6];
        this.n[0] = n1;
        this.n[1] = n2;
        this.n[2] = n3;
        this.n[3] = n1.negative();
        this.n[4] = n2.negative();
        this.n[5] = n3.negative();
        this.dimension = dimension;
    }

    int checkCollision(Vector x, Vector d) {
        Vector r = null;
        // boolean flag = false;
        int rVal = -1;
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            double t = (dimension / 2 + n[i].dotProduct(pos.add(x.negative()))) / d.dotProduct(n[i]);
            if (Math.abs(t) < minDist) {
                r = x.add(d.scalarMulti(t));
                r.addToSelf(pos.negative());
                if (r.x * r.x + r.y * r.y + r.z * r.z <= dimension * dimension / 2) {
                    // flag = true;
                    rVal = i;
                    minDist = Math.abs(t);
                }
            }
        }
        return rVal;
    }
}
