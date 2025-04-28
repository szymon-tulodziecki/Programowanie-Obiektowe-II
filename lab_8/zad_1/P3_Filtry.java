import java.util.*;
import java.io.*;
import java.nio.file.*;

class P3a_Slowa {
    private ArrayList<String> slowa = new ArrayList<>();

    // Konstruktor: wczytuje słowa z pliku tekstowego
    public P3a_Slowa(String nazwaPliku) {
        try {
            // Wczytaj cały tekst z pliku
            String tekst = new String(Files.readAllBytes(Paths.get(nazwaPliku)));
            // Podziel tekst na słowa (zachowując znaki przestankowe przy słowie)
            // np. "słowo,", "pies.", "dom"
            String[] podzielone = tekst.split("\\s+");
            slowa.addAll(Arrays.asList(podzielone));
        } catch (IOException e) {
            System.err.println("Błąd wczytywania pliku: " + e.getMessage());
        }
    }

    public ArrayList<String> tekscik() {
        return slowa;
    }
}

public class P3_Filtry {
    private static int licznik = 0;

    private static void licz() { licznik++; }

    // Filtr (a): słowa kończące się na "a"
    private static boolean konczyNaA(String sl) {
        return sl.endsWith("a");
    }

    // Filtr (b): słowa o parzystej liczbie znaków
    private static boolean parzystaLiczbaZnakow(String sl) {
        return sl.length() % 2 == 0;
    }

    // Filtr (c): słowa z parzystą liczbą liter (ignorując końcowe znaki nieliterowe)
    private static boolean parzystaLiczbaLiter(String sl) {
        // Usuwamy końcowe nieliterowe znaki
        String bezPrzestankowych = sl.replaceAll("[^\\p{L}]+$", "");
        long liczbaLiter = bezPrzestankowych.chars().filter(Character::isLetter).count();
        return liczbaLiter % 2 == 0;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Użycie: java P3_Filtry plik.txt");
            return;
        }

        ArrayList<String> tk = new P3a_Slowa(args[0]).tekscik();

        System.out.println("=== (a) Słowa kończące się na 'a' ===");
        licznik = 0;
        tk.stream()
            .filter(P3_Filtry::konczyNaA)
            .forEach(sl -> { licz(); System.out.print(sl + " "); });
        System.out.println("\nRazem: " + licznik + " słów.\n");

        System.out.println("=== (b) Słowa o parzystej liczbie znaków ===");
        licznik = 0;
        tk.stream()
            .filter(P3_Filtry::parzystaLiczbaZnakow)
            .forEach(sl -> { licz(); System.out.print(sl + " "); });
        System.out.println("\nRazem: " + licznik + " słów.\n");

        System.out.println("=== (c) Słowa z parzystą liczbą liter (ignorując końcowe znaki nieliterowe) ===");
        licznik = 0;
        tk.stream()
            .filter(P3_Filtry::parzystaLiczbaLiter)
            .forEach(sl -> { licz(); System.out.print(sl + " "); });
        System.out.println("\nRazem: " + licznik + " słów.\n");
    }
}