import javax.swing.*;
import java.awt.*;

public class Koperta extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int margin = 60;
        int size = Math.min(getWidth(), getHeight()) - 2 * margin;
        int x0 = margin;
        int y0 = margin;
        int x1 = x0 + size;
        int y1 = y0 + size;

        int xA = x0, yA = y0;
        int xB = x1, yB = y0;
        int xC = x1, yC = y1;
        int xD = x0, yD = y1;

        g.drawRect(xA, yA, size, size);

        g.drawLine(xA, yA, xC, yC);
        g.drawLine(xB, yB, xD, yD); 
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Koperta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new Koperta());
        frame.setVisible(true);
    }
}
