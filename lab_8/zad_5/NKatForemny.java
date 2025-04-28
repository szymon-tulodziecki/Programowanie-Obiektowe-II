import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class NKatForemny extends JPanel {
    private final int n;

    public NKatForemny(int n) {
        this.n = n;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int margin = 40;
        int size = Math.min(getWidth(), getHeight()) - 2 * margin;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = size / 2;

        int[] xPoints = new int[n];
        int[] yPoints = new int[n];
        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n - Math.PI / 2;
            xPoints[i] = centerX + (int) (radius * Math.cos(angle));
            yPoints[i] = centerY + (int) (radius * Math.sin(angle));
        }
        g.drawPolygon(xPoints, yPoints, n);
    }

    public static void main(String[] args) {
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        while (n < 3) {
            System.out.print("Podaj liczbę wierzchołków n (n >= 3): ");
            n = scanner.nextInt();
        }
        JFrame frame = new JFrame("n-kąt foremny");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new NKatForemny(n));
        frame.setVisible(true);
    }
}
