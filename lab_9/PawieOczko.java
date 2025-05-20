import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class PawieOczko extends JPanel {
    private int n;

    public PawieOczko(int n) {
        this.n = n;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        g.setColor(new Color(220, 220, 220));
        g.fillRect(0, 0, w, h);

        g.setColor(Color.BLACK);

        int centerY = h / 2;
        int x0 = 0; 
        int y0 = 0; 

        int R = Math.min(350, 350);

        R = Math.min(R, 2 * Math.min(centerY, h - centerY));

        int cx = x0;
        int cy = centerY;

        for (int i = 0; i < n; i++) {
            int r = R / (1 << i); 
            int x = cx;
            int y = cy - r;
            g.setColor(Color.BLACK);
            g.drawOval(x, y, 2 * r, 2 * r);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj n: ");
        int n = sc.nextInt();
        JFrame f = new JFrame("Pawie oczko");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.add(new PawieOczko(n));
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
