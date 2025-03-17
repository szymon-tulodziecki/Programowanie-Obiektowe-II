import java.util.*;

class Punkt {
    private double x, y;
    
    public Punkt(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double x() { return x; }
    public double y() { return y; }
    
    public double odl(Punkt pt) {
        double odlPoz = pt.x() - x;
        double odlPio = pt.y() - y;
        return Math.sqrt(odlPoz * odlPoz + odlPio * odlPio);
    }
}

abstract class Wielokat {
    protected Punkt[] wierzch;
    
    public Wielokat(Punkt[] wierzch) {
        this.wierzch = new Punkt[wierzch.length];
        for (int i = 0; i < wierzch.length; i++) {
            this.wierzch[i] = new Punkt(wierzch[i].x(), wierzch[i].y());
        }
    }
    
    public Punkt w(int nr) { return wierzch[nr]; }
    
    public double obwod() {
        double obw = 0;
        for (int i = 0; i < wierzch.length; i++) {
            obw += wierzch[i].odl(wierzch[(i + 1) % wierzch.length]);
        }
        return obw;
    }
    
    public abstract double pole();
}

class Trojkat extends Wielokat {
    public Trojkat(Punkt[] wierzch) {
        super(wierzch);
        if (wierzch.length != 3) {
            throw new IllegalArgumentException("Trojkat musi miec 3 wierzcholki");
        }
    }
    
    public double pole() {
        double p = obwod() / 2;
        return Math.sqrt(
            p * (p - w(0).odl(w(1))) * (p - w(1).odl(w(2))) * (p - w(2).odl(w(0)))
        );
    }
}

class Czworokat extends Wielokat {
    public Czworokat(Punkt[] wierzch) {
        super(wierzch);
        if (wierzch.length != 4) {
            throw new IllegalArgumentException("Czworokat musi miec 4 wierzcholki");
        }
    }
    
    public double pole() {
        double suma = 0;
        for (int i = 0; i < 4; i++) {
            Punkt obecny = w(i);
            Punkt nastepny = w((i + 1) % 4);
            suma += obecny.x() * nastepny.y() - nastepny.x() * obecny.y();
        }
        return Math.abs(suma) / 2.0;
    }
}
/*
 * 
 * wzór sznurowy, wierzchołki zapisane w kolejności zgodnej z ruchem wskazówek zegara
 * jest 1/2 abs(suma od i = 1 do n) po (xiyi+1 - xi+1 +yi)
 */
class Pieciokat extends Wielokat {
    public Pieciokat(Punkt[] wierzch) {
        super(wierzch);
        if (wierzch.length != 5) {
            throw new IllegalArgumentException("Pieciokat musi miec 5 wierzcholkow");
        }
    }
    
    public double pole() {
        double suma = 0;
        for (int i = 0; i < 5; i++) {
            Punkt obecny = w(i);
            Punkt nastepny = w((i + 1) % 5);
            suma += obecny.x() * nastepny.y() - nastepny.x() * obecny.y();
        }
        return Math.abs(suma) / 2.0;
    }
}

public class Zad_1 {
    public static void main(String[] args) {
        Scanner czyt = new Scanner(System.in);
        czyt.useLocale(Locale.US);

        int n;
        do {
            System.out.println("\n  OBWOD I POLE WIELOKATA");
            System.out.println("  (zle liczy pole dla samoprzeciec)");
            System.out.println("  Wybrac:");
            System.out.println("    3. trojkat");
            System.out.println("    4. czworokat");
            System.out.println("    5. pieciokat");

            System.out.print("                 ?  ");
            n = czyt.nextInt();
        } while (n < 3 || n > 5);

        Punkt[] ww = new Punkt[n];
        System.out.println("\n  Poprosze o wspolrzedne wierzcholkow:");
        for (int i = 0; i < n; i++) {
            System.out.print("\n  wierzcholek " + i + ":  x == ");
            double x = czyt.nextDouble();
            System.out.print("                  y == ");
            double y = czyt.nextDouble();
            ww[i] = new Punkt(x, y);
        }
        
        switch (n) {
            case 3:
                Trojkat tr = new Trojkat(ww);
                System.out.println("\n  pole trojkata  == " + tr.pole());
                System.out.println("  obwod trojkata == " + tr.obwod());
                break;
            case 4:
                Czworokat cz = new Czworokat(ww);
                System.out.println("\n  pole czworokata  == " + cz.pole());
                System.out.println("  obwod czworokata == " + cz.obwod());
                break;
            case 5: 
                Pieciokat p = new Pieciokat(ww);
                System.out.println("\n  pole pieciokata  == " + p.pole());
                System.out.println("  obwod pieciokata  == " + p.obwod());
                break;
        }
        
        czyt.close();
    }
}