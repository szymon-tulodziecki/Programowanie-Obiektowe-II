import java.awt.*;
import javax.swing.*;

public class Sciaga extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);


        g.drawLine(50, 50, 200, 50);

        g.drawRect(60, 60, 80, 40);

        g.fillOval(160, 60, 40, 40);

        int[] xPoints = {120, 140, 130};
        int[] yPoints = {150, 150, 170};
        g.fillPolygon(xPoints, yPoints, 3);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sciaga - przykładowe rysowanie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);
        frame.add(new Sciaga());
        frame.setVisible(true);
    }
}
