import javax.swing.*;
import java.awt.*;


interface Fcja {
  double fun(double x, double y);
}


class Wykres extends JComponent {
  private Fcja f;
  private double rozm;
  private JPanel pan;
  private int szer, wys;

  // Konstruktor:
  public Wykres(Fcja f, double rozm) {
    this.f = f;  this.rozm = rozm;
    this.szer = Mapa.szer;  this.wys = Mapa.wys;
    pan = new JPanel();  pan.setSize(szer, wys);
  }

  // Funkcja  f  przetworzona do celow rysowania wykresu:
  private double ff(int x, int y) {
    return f.fun((2.0*x/szer-1)*rozm, (1-2.0*y/wys)*rozm);
  }

  // Metoda rysujaca:
  public void paintComponent(Graphics g) {
    double[][] obraz = new double[szer][wys];
    // Przygotowanie wykresu w tablicy z jednoczesnym policzeniem
    // max  i  min  funkcji:
    double min, max;
    min = max = ff(0,0);
    for (int y=0; y<wys; y++)
      for (int x=0; x<szer; x++) {
        obraz[y][x] = ff(x,y);
        if (obraz[y][x] < min)  min = obraz[y][x];  else
        if (obraz[y][x] > max)  max = obraz[y][x];
      }
    // Obliczenie koloru:
    double mnoz = 255/(max-min);
    double licz = max+min;
    for (int y=0; y<wys; y++)
      for (int x=0; x<szer; x++) {
        int kol = (int)((2*obraz[y][x]-licz)*mnoz);
        if (kol>=0)
          g.setColor(new Color(255, 255-kol, 255-kol));
        else
          g.setColor(new Color(255+kol, 255, 255+kol));
        g.fillRect(x,y,1,1);
      }
    // Osie wspolrzednych:
    g.setColor(Color.BLACK);
    g.drawLine(0,wys/2,szer-1,wys/2);
    g.drawLine(szer/2,0,szer/2,wys-1);
    // Barwna legenda wysokosci:
    g.drawString(""+Math.round(max*100)/100.0, szer+45, 25);
    g.drawString(""+Math.round((min+max)*50)/100.0, szer+45, wys/2+5);
    g.drawString(""+Math.round(min*100)/100.0, szer+45, wys-15);
    for (int y=wys/2; y>5; y--) {
      int kol;
      kol = 510*y/wys;
      g.setColor(new Color(255, kol, kol));
      g.drawLine(szer+10,y+10,szer+40,y+10);
      g.setColor(new Color(kol, 255, kol));
      g.drawLine(szer+10,wys-y-10,szer+40,wys-y-10);
    }
  }
  
}
