public class Scratch2 {

    public static void main(String[] args) {
        Vector horz = new Vector(1.0, 0.0, 0.0);
        Vector vert = new Vector(0.0, 1.0, 0.0);
        Vector outw = new Vector(0.0, 0.0, 1.0);
        Thing thing1 = new Thing(100, 89, 0, horz, vert, outw, 10);
        Vector x = new Vector(0, 0, 0);
        Vector d = new Vector(0.70710678118, 0.70710678118, 0);
        // d.rotate(Math.cos(0.1), -Math.sin(0.1));
        System.out.println(thing1.checkCollision(x, d));
    }
}
