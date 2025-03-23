package Zad_4;

import java.util.List;
import java.util.Arrays;

public class TestSortowania {
    public static void main(String[] args) {
        List<Pracownik> pracownicy = Arrays.asList(
            new Pracownik("Nowak", "Adam", Wyksztalcenie.WYZ_TECHN, 5000),
            new Pracownik("Kowalski", "Jan", Wyksztalcenie.WYZ_EKON, 7000),
            new Pracownik("Nowak", "Anna", Wyksztalcenie.SREDNIE, 5000),
            new Pracownik("Adamczyk", "Piotr", Wyksztalcenie.SREDNIE, 5000)

        );

        System.out.println("Sortowanie alfabetyczne:");
        Pracownik.sortuj(pracownicy, new PorownajAlfabetycznie());
        pracownicy.forEach(System.out::println);

        System.out.println("\nSortowanie po pensji:");
        Pracownik.sortuj(pracownicy, new PorownajPensje());
        pracownicy.forEach(System.out::println);
    }
}
