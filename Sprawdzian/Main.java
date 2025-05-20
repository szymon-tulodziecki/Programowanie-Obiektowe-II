import java.util.*;

interface Inter {
  double skladnik(int i);
}

class Szereg {
  private double suma;
  public Szereg(Inter s) {
    suma = 0;
    for (int i=0; i<100; i++)
      suma += s.skladnik(i);
  }
  public double suma100() { return suma; }
}

class Potega implements Inter {
  private double x;
  public Potega(double x) {
    this.x = x;
  }
  public double skladnik(int i) {
    return Math.pow(x, i);
  }
}
class Main {
  public static void main(String[] args) {
    Scanner czyt = new Scanner(System.in);
    czyt.useLocale(Locale.US);
    System.out.print("\n  x == ");
    double x = czyt.nextDouble();
    System.out.println(
      "  x^0 + x^1 + x^2 + ... + x^99 == " +
      new Szereg(new Potega(x)).suma100() +
      "\n"
    );
  }
}
    