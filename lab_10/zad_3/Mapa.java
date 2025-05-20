import javax.swing.*;

class Mapa {
  // Rozmiary mapy bez legendy -- mozna je zmienic:
  public static final int  szer=700, wys=700;
  public double fun(double x, double y) {
    double r = x * x + y * y - 1;
    double z = r * r * r - x * x * y * y * y;
    return -z + 10;
}

  public static void main(String[] args) {
    JFrame rama = new JFrame();
    rama.setBounds(0,0,szer+120,wys+35);
    rama.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    rama.add(new Wykres(
      new Fcja(){
        (a * a) * (x * x + y *y);
      },
      1.0
    ));

    rama.setVisible(true);
  }
}
