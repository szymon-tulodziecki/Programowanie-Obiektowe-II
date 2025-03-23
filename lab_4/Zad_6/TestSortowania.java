package Zad_6;

import java.util.*;

public class TestSortowania {
    public static void main(String[] args) {
        List<Pracownik> pracownicy = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            pracownicy.add(new Pracownik());
        }

        System.out.println("Lista przed sortowaniem:");
        wyswietlPracownikow(pracownicy);

        System.out.println("\nSortowanie alfabetyczne (nazwisko, imię):");
        Pracownik.sortuj(pracownicy, new PorownajAlfabetycznie());
        wyswietlPracownikow(pracownicy);

        System.out.println("\nSortowanie po pensji (malejąco):");
        Pracownik.sortuj(pracownicy, new PorownajPensje());
        wyswietlPracownikow(pracownicy);
    }

    private static void wyswietlPracownikow(List<Pracownik> pracownicy) {
        System.out.println("Nazwisko              | Imię          | Wykształcenie | Pensja");
        System.out.println("---------------------------------------------------------------");
        for (Pracownik p : pracownicy) {
            System.out.println(p);
        }
    }
}
