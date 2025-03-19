import java.util.*;


abstract class Srednia {
    
  public abstract double poczatek();
  public abstract double dzialanie(double arg1, double arg2);
  public abstract double dziel(double arg, int n);

  public double srednia(double[] ciag) {
    double pom=poczatek();
    for (int i=0; i<ciag.length; i++)
      pom = dzialanie(pom, ciag[i]);
    return dziel(pom, ciag.length);
  }
}


class Arytm extends Srednia {
  public double poczatek() { return 0.0; }
  public double dzialanie(double arg1, double arg2) {
    return arg1+arg2;
  }
  public double dziel(double arg, int n) { return arg/n; }
}

class Geom extends Srednia {
    public double poczatek(){return 1.0;}
    public double dzialanie(double arg1, double arg2){
        return arg1 * arg2;
    }
    public double dziel(double arg, int n){return Math.pow(arg, 1.0 /n);}
}

class Harm extends Srednia {
    public double poczatek(){return 0.0;}
    public double dzialanie(double arg1, double arg2){
        return arg1 + (1.0 / arg2);
    }
    public double dziel(double arg, int n){return n/arg;}
}

class Max extends Srednia {
    public double poczatek(){return Double.MIN_VALUE;}
    public double dzialanie(double arg1, double arg2){
        return arg1 > arg2 ? arg1 : arg2;
    }
    public double dziel(double arg, int n) {
        return arg; 
    }
}


class Zad_8 {
  public static Arytm arytm = new Arytm();
  public static Geom geom = new Geom();
  public static Harm harm = new Harm();
  public static Max max = new Max();

  public static void main (String[] args) {
    Scanner czyt = new Scanner(System.in);
    czyt.useLocale(Locale.US);

    System.out.print("  dlugosc ciagu == ");
    int n = czyt.nextInt();

    double[] ciag = new double[n];

    for (int i=0; i<n; i++) {
      System.out.print("  ciag[" + i + "] == ");
      ciag[i] = czyt.nextDouble();
    }
    System.out.println("\nWyniki:");

    System.out.println("Średnia arytmetyczna: " + arytm.srednia(ciag));
    
    System.out.println("Średnia geometryczna: " + geom.srednia(ciag));
    
    System.out.println("Średnia harmoniczna: " + harm.srednia(ciag));
    
    System.out.println("Maksimum: " + max.srednia(ciag));

    czyt.close();

  }
}
