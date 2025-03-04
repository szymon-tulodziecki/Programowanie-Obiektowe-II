class Sprzedawcy{
    private static double uzysk = 0.0;

    public void sprzedaz(double suma){
        uzysk += suma;
    }

    public static double uzysk(){
        return uzysk;
    }
}

public class zad_4 {
    public static void main(String[ ] args) {
        
        Sprzedawcy Jacek = new Sprzedawcy( ), Iwona = new Sprzedawcy( ),
        Roman= new Sprzedawcy( );
        Jacek.sprzedaz(5.50);
        Roman.sprzedaz(10.80);
        Roman.sprzedaz(7.00);
        Iwona.sprzedaz(11.20);
        Jacek.sprzedaz(2.30);

        System.out.println("\n Sumaryczny przychod: " + Sprzedawcy.uzysk( ) + "\n");

    }
}
