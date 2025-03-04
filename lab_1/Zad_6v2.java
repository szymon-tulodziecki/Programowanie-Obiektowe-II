class Trojkat {
    public double x1, y1, x2, y2, x3, y3;

    Trojkat(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
        this.x3 = x3; this.y3 = y3;
    }
}

class Trojkaty {
    static double pole(Trojkat t) {
        double a = dlugoscBoku(t.x1, t.y1, t.x2, t.y2);
        double b = dlugoscBoku(t.x2, t.y2, t.x3, t.y3);
        double c = dlugoscBoku(t.x3, t.y3, t.x1, t.y1);
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p-a) * (p-b) * (p-c));
    }

    static double obwod(Trojkat t) {
        return dlugoscBoku(t.x1, t.y1, t.x2, t.y2) +
               dlugoscBoku(t.x2, t.y2, t.x3, t.y3) +
               dlugoscBoku(t.x3, t.y3, t.x1, t.y1);
    }

    private static double dlugoscBoku(double xa, double ya, double xb, double yb) {
        return Math.sqrt(Math.pow(xb-xa, 2) + Math.pow(yb-ya, 2));
    }
}

public class Zad_6v2 {
    public static void main(String[] args) {
        Trojkat trojkat = new Trojkat(0, 0, 3, 0, 0, 4);
        System.out.println("Pole trójkąta: " + Trojkaty.pole(trojkat));
        System.out.println("Obwód trójkąta: " + Trojkaty.obwod(trojkat));
    }
}
