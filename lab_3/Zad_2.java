import java.util.Arrays;

abstract class Fcja {
  abstract double fcja(double x);
}
//----------------------------------------------------------------------
public class Zad_2 {

  static double calka(Fcja f, double dol, double gora, double krok) {
    double cl=0.0;
    for (double x = dol; x < gora; x += krok)
      cl += krok * f.fcja(x);
    return cl;
  }

  static void rysujWykres(Fcja f, double dol, double gora) {
    final int SZEROKOSC = 40;
    final int WYSOKOSC = 20;
    char[][] wykres = new char[WYSOKOSC][SZEROKOSC];
    
    for (int i = 0; i < WYSOKOSC; i++) {
        Arrays.fill(wykres[i], ' ');
    }

    for (int i = 0; i < WYSOKOSC; i++) {
        wykres[i][SZEROKOSC / 2] = '|';
    }

    if (dol <= 0 && gora >= 0) {
        int yZeroPos = WYSOKOSC - 1 - (int) Math.round((-dol) / (gora - dol) * (WYSOKOSC - 1));
        wykres[yZeroPos][SZEROKOSC / 2] = '+';
    }

    double minX = Double.MAX_VALUE;
    double maxX = -Double.MAX_VALUE;
    for (int i = 0; i < WYSOKOSC; i++) {
        double y = dol + (gora - dol) * i / (WYSOKOSC - 1);
        double x = f.fcja(y);
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
    }
    
    double zakresX = Math.max(Math.abs(maxX), Math.abs(minX));
    if (zakresX == 0) zakresX = 1;

    for (int i = 0; i < WYSOKOSC; i++) {
        double y = dol + (gora - dol) * i / (WYSOKOSC - 1);
        double x = f.fcja(y);

        int xPos = (int) Math.round((x / zakresX) * (SZEROKOSC / 2 - 1)) + SZEROKOSC / 2;
        int yPos = WYSOKOSC - 1 - i;

        if (xPos >= 0 && xPos < SZEROKOSC && yPos >= 0 && yPos < WYSOKOSC) {
            wykres[yPos][xPos] = '.'; 
        }
    }

    for (char[] rzad : wykres) {
        System.out.println(new String(rzad));
    }
}


  public static void main(String[] args) {

    System.out.println("\n 1. (Krok: 0.1)    [-2.0, 2.0] ∫x^2dx = " +
      calka(
        new Fcja(){
          public double fcja(double x) { return x*x; }
        }, -2.0, 2.0, 0.1
      )
    );
    System.out.println("\nWykres f = x^2 <-2.0, 2.0> : \n");
    rysujWykres(new Fcja() {
      public double fcja(double x) { return x * x; }
    }, -2, 2);
    System.out.println("-----------------------------------------------------------------------");

    
    
    System.out.println("\n 2a. (Krok: 0.1)     [0.0, π] ∫sin(x)dx = " +
    calka(
      new Fcja(){
        public double fcja(double x) { return Math.sin(x); }
      }, 0.0, Math.PI, 0.1
    )
  );

   
  System.out.println("\n 2b. (Krok: 0.06)     [0.0, π] ∫sin(x)dx = " +
  calka(
    new Fcja(){
      public double fcja(double x) { return Math.sin(x); }
    }, 0.0, Math.PI, 0.06
  )
);

  System.out.println("\n Wykres f = sin(x) <0.0, π >: ");
  
  rysujWykres(new Fcja() {
    public double fcja(double x) { return Math.sin(x); }
  }, 0.0, Math.PI);
  System.out.println("-----------------------------------------------------------------------");



  System.out.println("\n 3. (Krok: 0.1)    [-2.0, 2.0] ∫sin(3*x)dx = " +
  calka(
    new Fcja(){
      public double fcja(double x) { return Math.sin(3*x); }
    }, -2.0, 2.0, 0.1
  )
);
System.out.println("\nWykres f = sin(3*x) <-2.0, 2.0> : \n");
rysujWykres(new Fcja() {
  public double fcja(double x) { return Math.sin(3 * x); }
}, -2, 2);
System.out.println("-----------------------------------------------------------------------");


  }
}
