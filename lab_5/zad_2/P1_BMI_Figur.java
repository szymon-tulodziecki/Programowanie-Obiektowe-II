
interface Figura{
  double pole();
  double obwod();
  double bmi();
}

abstract class FiguraImpl implements Figura {
   public double bmi() {
    return this.pole() / (this.obwod()*this.obwod());   
  }
}

class Kolo extends FiguraImpl {
  private double r; 
  public Kolo(double r) { this.r = r; }

  public double pole() { return Math.PI*r*r; }
  public double obwod() { return 2*Math.PI*r; }
}

class Prostokat extends FiguraImpl {
  private double a, b; 
  public Prostokat(double a, double b) { this.a = a; this.b = b; }

  public double pole() { return a*b; }
  public double obwod() { return 2*(a+b); }
}

class Trojkat extends FiguraImpl {
  private double a, b, c; 
  public Trojkat(double a, double b, double c) {this.a = a; this.b = b; this.c = c; }

  public double pole() { double s = (a + b + c) / 2;
    return Math.sqrt(s * (s - a) * (s - b) * (s - c)); }

  public double obwod() {
    return a + b + c;
  }
}

class OdcinekParab extends FiguraImpl {
  private double a, b, h; 
  public OdcinekParab(double a, double b, double h) { this.a = a; this.b = b; this.h = h; }

  public double pole() { return (a * a) / (2 * h * Math.sinh(2 * h / a));}
  public double obwod() { return Math.sqrt(a*a + b*b); }
}

public class P1_BMI_Figur {
  public static void main (String[] args) {
    Figura kolo = new Kolo(5);  
    Figura prostokat = new Prostokat(4, 6); 
    Figura trojkat = new Trojkat(3, 4, 5);  
    OdcinekParab odcinek = new OdcinekParab(5, 3, 2);  

    System.out.println("BMI dla koła: " + kolo.bmi());
    System.out.println("BMI dla prostokąta: " + prostokat.bmi());
    System.out.println("BMI dla trójkąta: " + trojkat.bmi());
    System.out.println("BMI dla odcinka paraboli: " + odcinek.bmi());
  }
}