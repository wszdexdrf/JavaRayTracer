import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Scratch extends JPanel {
    int[][] map;
    int width, height;

    Scratch(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(width, height));
        this.setFocusable(true);
        KeyListener listener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        this.addKeyListener(listener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Scratch");
        Scratch game = new Scratch(1600, 900);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}