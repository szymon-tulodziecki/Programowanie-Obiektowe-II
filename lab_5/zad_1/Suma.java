package lab_5.zad_1;

interface Funkcja { double ff(double x); }

class Suma {

  public static Funkcja sumaFcji(Funkcja f1, Funkcja f2) {
    return new Funkcja() {
      public double ff(double x) {
        return f1.ff(x) + f2.ff(x);
      }
    };
  }

  public static void main(String[] args) {
    Funkcja kwadPlusDwarazy =
      sumaFcji( x -> { return x*x; }, x -> { return 2*x; });

    double x = Double.parseDouble(args[0]);
    System.out.println("  x == " + x);
    System.out.println("  x^2 + 2*x == " + kwadPlusDwarazy.ff(x));
  }
}

