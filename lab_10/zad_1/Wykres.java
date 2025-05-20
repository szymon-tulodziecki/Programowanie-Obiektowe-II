import java.awt.*;
import javax.swing.*;


interface Fun { double fun(double x); }

class WykrRys extends JPanel {
  private Fun f;
  private double xMin, xMax;

  public WykrRys(Fun f, double xMin, double xMax) {
      this.f = f;
      this.xMin = xMin;
      this.xMax = xMax;
  }

  @Override
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      rysujOsie(g);
      rysujWykres(g);
  }

  private void rysujOsie(Graphics g) {
      g.setColor(Color.GRAY);
      int zeroX = przeliczX(0);
      int zeroY = przeliczY(0);

      g.drawLine(0, zeroY, getWidth(), zeroY);
      g.drawLine(zeroX, 0, zeroX, getHeight());
  }

  private void rysujWykres(Graphics g) {
      g.setColor(Color.BLUE);
      double krok = (xMax - xMin) / (double) getWidth();

      for (int i = 0; i < getWidth() - 1; i++) {
          double x1 = xMin + i * krok;
          double x2 = xMin + (i + 1) * krok;

          double y1 = f.fun(x1);
          double y2 = f.fun(x2);

          int px1 = przeliczX(x1);
          int py1 = przeliczY(y1);
          int px2 = przeliczX(x2);
          int py2 = przeliczY(y2);

          g.drawLine(px1, py1, px2, py2);
      }
  }

  private int przeliczX(double x) {
      double proporcja = (x - xMin) / (xMax - xMin);
      return (int) (Wykres.przesX + proporcja * Wykres.szer);
  }

  private int przeliczY(double y) {
      double yMin = -1.5, yMax = 1.5;
      double proporcja = (y - yMin) / (yMax - yMin);
      return (int) (Wykres.przesY + (1 - proporcja) * Wykres.wys);
  }
}


class Wykres {
  public static int szer=500, wys=500;
  public static int przesX=5, przesY=5;

  public static void main(String[] args) {
    JFrame ramka = new JFrame();
    ramka.setSize(szer+przesX*2+5,wys+przesY*2+30);
    ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ramka.add(new WykrRys(
//      x -> x , -4, 4
//      x -> 2*x-1 , -3, 5
//      x -> -x*x+1 , -1.5 , 1.5
//      x -> Math.sin(x) , -Math.PI, Math.PI
//      x -> Math.sin(2*x) , -Math.PI, Math.PI
//      x -> Math.sin(2*x)*x/2 , -4*Math.PI , 0
//      x -> 1.5*Math.exp(-x*x) , -1.75 , 1.75
x -> Math.log(x), -1, 1.5
    ));

    ramka.setVisible(true);
  }
}
