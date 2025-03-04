class Trojkat {
    private double x1, y1, x2, y2, x3, y3;

    public Trojkat(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
        this.x3 = x3; this.y3 = y3;
    }

    public double pole() {
        double a = dlugoscBoku(x1, y1, x2, y2);
        double b = dlugoscBoku(x2, y2, x3, y3);
        double c = dlugoscBoku(x3, y3, x1, y1);
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p-a) * (p-b) * (p-c));
    }

    public double obwod() {
        return dlugoscBoku(x1, y1, x2, y2) +
               dlugoscBoku(x2, y2, x3, y3) +
               dlugoscBoku(x3, y3, x1, y1);
    }

    private double dlugoscBoku(double xa, double ya, double xb, double yb) {
        return Math.sqrt(Math.pow(xb-xa, 2) + Math.pow(yb-ya, 2));
    }
}

public class Zad_6v1 {
    public static void main(String[] args) {
        Trojkat trojkat = new Trojkat(0, 0, 3, 0, 0, 4);
        System.out.println("Pole trójkąta: " + trojkat.pole());
        System.out.println("Obwód trójkąta: " + trojkat.obwod());
    }
}
