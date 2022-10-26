import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Observer extends JPanel {
    Thing[] things;
    Vector pos;
    Vector[] vrays;
    double[][] tanVals;
    double fov;
    int numRays, width, height;
    boolean up, down, left, right;
    static final double moveSpeed = 0.000001, omega = Math.PI / 1200;
    static final double cosTheta = Math.cos(omega), sinTheta = Math.sin(omega);

    Observer(int width, int height, int x, int y, double fov, Thing[] things) throws FileNotFoundException {
        this.things = things;
        this.pos = new Vector(x, y, 0);
        this.fov = fov;
        this.width = width;
        this.height = height;
        this.numRays = 1000;
        this.vrays = new Vector[numRays * numRays];
        this.tanVals = new double[numRays * numRays][];
        // PrintWriter pw = new PrintWriter(new File("1.txt"));
        for (int i = 0; i < numRays; i++) {
            for (int j = 0; j < numRays; j++) {
                vrays[i * numRays + j] = new Vector(0, 0, 0);
                vrays[i * numRays + j].createFromPolarCoordinates(1, fov / 2 - i * fov / numRays,
                        j * fov / numRays - fov / 2 + Math.PI / 2.0);
                tanVals[i * numRays + j] = new double[] { 1.0 / Math.tan(fov / 2 - i * fov / numRays),
                        1.0 / Math.tan(j * fov / numRays - fov / 2) };
                // pw.write(x1 + " " + y1 + ",");
            }
            // pw.write("\n");
        }
        // System.exit(0);
        this.setFocusable(true);
        KeyListener listener = new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    up = true;
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    down = true;
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    left = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    right = true;
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    up = false;
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    down = false;
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    left = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    right = false;
            }
        };
        this.addKeyListener(listener);
        this.setPreferredSize(new Dimension(width, height));
    }
    static final Color[] COLORS = {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE};
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double f = 5.0;
        int maxVal = (int) Math.round(f / Math.tan(fov / 2.0));
        Arrays.stream(things).parallel().forEach((thing) -> {
            int i = 0;
            for (Vector vray : vrays) {
                int face = thing.checkCollision(pos, vray);
                if (face != -1) {
                    int x = (int) Math.round(tanVals[i][0] * f) + maxVal;
                    int y = (int) Math.round(tanVals[i][1] * f) + maxVal;
                    // int y = (int) Math.round(i % numRays / numRays * height);
                    g.setColor(COLORS[face]);
                    g.fillRect(x - 5, y - 5, 10, 10);
                }
                i++;
            }
        });
    }

    void render() {
        if (up) {
            pos.x += moveSpeed;
        } else if (down) {
            pos.x -= moveSpeed;
        }
        if (left) {
            for (Vector dir : vrays) {
                dir.rotateZ(cosTheta, sinTheta);
            }
        } else if (right) {
            for (Vector dir : vrays) {
                dir.rotateZ(cosTheta, -sinTheta);
            }
        }
        repaint();
    }
}
