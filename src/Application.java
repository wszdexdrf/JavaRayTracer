import java.io.FileNotFoundException;

import javax.swing.*;

class Application {
    static Thing[] things;
    static final int blocksPerUnit = 1;

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("Application");
        things = new Thing[1];
        Vector horz = new Vector(1.0, 0.0, 0.0);
        Vector vert = new Vector(0.0, 1.0, 0.0);
        Vector outw = new Vector(0.0, 0.0, 1.0);
        things[0] = new Thing(1, 0, 0, horz, vert, outw, 1);
        Observer game = new Observer(1600, 900, 0, 0, Math.toRadians(60), things);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    game.render();
                }
            }
        });
        thread.start();
    }

}