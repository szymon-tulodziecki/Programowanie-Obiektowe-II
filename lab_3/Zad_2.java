abstract class Fcja{
    abstract double fcja(double x);
}

class g() exteds Fcja
class Calka extends Fcja {
    @Override
    public double fcja(double x) {
        return 1; 
    }

    public double liczPole(double dol, double gora, double krok) {
        double wynik = 0.0;
        for(double i = dol; i < gora; i += krok){
            wynik += krok * fcja(i);
        }
        return wynik;
    }
}
public class Zad_2 {
    public static void main(String[] args) {
        Calka c = new Calka();
        System.out.println("Pole: " + c.liczPole(-2.0, 2, 0.01));
    }
}
