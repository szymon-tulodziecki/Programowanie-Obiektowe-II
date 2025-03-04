public class zad_2 {
    public static void main(String[] args) {
        Samochod a = new Samochod();

        a.kola = 4;

        // siedzenia jest własnością, a nie metodą
        // a.siedzenia(5);

        // metoda start powinna być wywołana na obiekcie a
        // start();

        // Błędne: metoda start nie przyjmuje argumentów
        // a.start(2);

        a.start();

        System.out.println(a.start());

        // metoda hamuj jest typu void, więc nie zwraca wartości do wydrukowania
        // System.out.println(a.hamuj(2));

        // metoda start zwraca Boolean, a a jest obiektem klasy Samochod
        // a = a.start();

        // metoda start zwraca Boolean, a nie int
        // int wynik = a.start();

        // metoda przyspiesz jest typu void, więc nie zwraca wartości
        // Boolean wynik = a.przyspiesz();
    }
}

class Samochod {
    public int kola;
    public int siedzenia;
    public int kierownica;

    public Boolean start() {
        // Implementacja metody start
        return true;
    }

    public void hamuj(int jak) {
        // Implementacja metody hamuj
    }

    public void przyspiesz() {
        // Implementacja metody przyspiesz
    }
}