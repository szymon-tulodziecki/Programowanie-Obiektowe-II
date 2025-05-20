import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Plansza extends JPanel {
    private int x = 100;
    private int y = 100;
    private final int r = 30;

    public void przesun(int dx, int dy) {
        x += dx;
        y += dy;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, r, r);
    }
}

class P8_KodyKlawiszy {
    public static void main(String[] args) {
        JFrame ramka = new JFrame("Poruszanie kółkiem");
        ramka.setSize(400, 400);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Plansza plansza = new Plansza();
        ramka.add(plansza);
        ramka.setVisible(true);

        Timer timer = new Timer(20, null); // co 20ms — ok. 50 FPS

        boolean[] kierunki = new boolean[4]; // lewo, prawo, góra, dół

        timer.addActionListener(e -> {
            int dx = 0, dy = 0;
            if (kierunki[0]) dx -= 2;
            if (kierunki[1]) dx += 2;
            if (kierunki[2]) dy -= 2;
            if (kierunki[3]) dy += 2;
            plansza.przesun(dx, dy);
        });
        timer.start();

        ramka.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ev) {
                switch (ev.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> kierunki[0] = true;
                    case KeyEvent.VK_RIGHT -> kierunki[1] = true;
                    case KeyEvent.VK_UP -> kierunki[2] = true;
                    case KeyEvent.VK_DOWN -> kierunki[3] = true;
                }
            }

            public void keyReleased(KeyEvent ev) {
                switch (ev.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> kierunki[0] = false;
                    case KeyEvent.VK_RIGHT -> kierunki[1] = false;
                    case KeyEvent.VK_UP -> kierunki[2] = false;
                    case KeyEvent.VK_DOWN -> kierunki[3] = false;
                }
            }
        });

        ramka.setFocusable(true);
        ramka.requestFocusInWindow();
    }
}
