import javax.swing.*;
import java.awt.*;

public class Buzka extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth(), h = getHeight();
        g.drawOval(w/4, h/4, w/2, h/2);
        g.fillOval(w/3, h/2 - 30, 20, 20);
        g.fillOval(w*2/3 - 20, h/2 - 30, 20, 20);
        g.drawArc(w/3, h/2 + 20, w/3, 30, 180, 180);
        g.drawString("To jest buźka!", w/2 - 40, h - 20);
    }
    public static void main(String[] args) {
        JFrame f = new JFrame("Buźka");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 400);
        f.add(new Buzka());
        f.setVisible(true);
    }
}
