import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.Arrays;
import java.util.Random;

public class Application extends JPanel {
    double[][][] rays;

    Camera camera;
    BufferedImage image;
    byte[] imagePixels;
    BufferedImage drawableImage;
    Sphere[] balls;
    int hs, vs;

    static final float KERNEL_BIT = 1.0f / 3.0f;
    static final int WIDTH = 900, HEIGHT = 900;
    static final Kernel filter = new Kernel(3, 3, new float[] { KERNEL_BIT, KERNEL_BIT, KERNEL_BIT, KERNEL_BIT,
            KERNEL_BIT, KERNEL_BIT, KERNEL_BIT, KERNEL_BIT, KERNEL_BIT });
    static final ConvolveOp conv2d = new ConvolveOp(filter);

    Application(double fov, int numRaysPerSide) {
        this.camera = new Camera();
        this.rays = new double[numRaysPerSide][numRaysPerSide][3];
        for (int i = 0; i < numRaysPerSide; i++) {
            double theta = (i * Math.PI) / numRaysPerSide;
            for (int j = 0; j < numRaysPerSide; j++) {
                double phi = (j * fov) / numRaysPerSide - fov / 2;
                rays[i][j] = new double[] { Math.sin(phi) * Math.cos(theta), Math.sin(phi) * Math.sin(theta),
                        Math.cos(phi) };
            }
        }
        this.balls = new Sphere[10];
        Random r = new Random();
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Sphere(r.nextDouble() * 200.0 - 100.0, r.nextDouble() * 200.0 - 100.0, -200.0,
                    r.nextDouble() * 25.0 + 25.0);
        }
        long l = Math.round(100 * Math.tan(fov / 2));
        this.hs = (int) (WIDTH / 2.0 / l);
        this.vs = (int) (HEIGHT / 2.0 / l);
        this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        this.imagePixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        this.drawableImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(drawableImage, 0, 0, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("3D Engine");
        Application game = new Application(Math.toRadians(90), 1000);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boolean isDrawing = true;
        long time = System.currentTimeMillis();
        while (isDrawing) {
            game.repaint();
            game.render();
            System.out.print(1000 / (System.currentTimeMillis() - time) + " frames per second \r");
            time = System.currentTimeMillis();
        }

    }

    void imageRefresh() {
        Arrays.fill(imagePixels, (byte) 0);
    }

    void render() {
        imageRefresh();
        Arrays.stream(rays).parallel().forEach((rx) -> {
            int red = 0;
            for (double[] ray : rx) {
                int[] x_y = camera.checkPoint(camera.focus, ray, hs, vs);
                if (x_y[0] >= 900 || x_y[0] < 0 || x_y[1] >= 900 || x_y[1] < 0)
                    continue;
                for (Sphere ball : balls) {
                    double dist = ball.checkRayCollision(camera.focus, ray);
                    if (dist != -1) {
                        int val = (int) Math.round(150.0 / dist * 150.0 / dist * 255);
                        if (val > 255)
                            val = 255;
                        if (val < 0)
                            val = 0;
                        int index = (x_y[0] * WIDTH + x_y[1]) * 3 + 2;
                        red = Byte.toUnsignedInt(imagePixels[index]);
                        if (red < val) {
                            imagePixels[index] = (byte) val;
                        }
                    }
                }
            }
        });
        conv2d.filter(image, drawableImage);
        Random r = new Random();
        for (Sphere ball : balls) {
            double[] vel = { r.nextGaussian(), r.nextGaussian(), r.nextGaussian() };
            Vector.normalize(1.0, vel);
            ball.move(vel);
        }
    }
}
