package Zad_3;

public class TestPracownik {
    public static void main(String[] args) {
        testWczytywaniaDanych();
    }

    public static void testWczytywaniaDanych() {
        Pracownik pracownik = new Pracownik();
        
        System.out.println("\nWczytane dane pracownika:");
        System.out.println("Nazwisko: " + pracownik.nazwisko());
        System.out.println("Imię: " + pracownik.imie());
        System.out.println("Wykształcenie: " + pracownik.wyksztalcenie());
        System.out.println("Pensja: " + pracownik.pensja());
        
        System.out.println("\nWydruk w formie tabeli:");
        System.out.println(pracownik.toString());
    }
}
