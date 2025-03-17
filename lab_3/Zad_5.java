import java.util.Scanner;

public class Zad_5 {

    public static void wczytaj() {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();

        String[] tab = new String[r];

        for (int i = 0; i < r; i++) {
            tab[i] = sc.nextLine();
        }

        sortuj(tab);
    }

    public static void sortuj(String[] tab) {
        int n = tab.length, porownania = 0, ostatniaZamiana;
        do {
            ostatniaZamiana = 0;
            for (int j = 0; j < n - 1; j++) {
                porownania++;
                if (porownajNapisy(tab[j], tab[j + 1]) > 0) {
                    String temp = tab[j];
                    tab[j] = tab[j + 1];
                    tab[j + 1] = temp;
                    ostatniaZamiana = j + 1;
                }
            }
            n = ostatniaZamiana;
        } while (ostatniaZamiana > 0);

        System.out.println("Porównań: " + porownania);
    
    }

    public static int porownajNapisy(String s1, String s2) {
        int krotszy = (s1.length() < s2.length()) ? s1.length() : s2.length();

        for (int i = 0; i < krotszy; i++) {
            int roznica = s1.charAt(i) - s2.charAt(i);
            if (roznica != 0) {
                return roznica;
            }
        }
        return s1.length() - s2.length(); 
    }

    public static void main(String[] args) {
        wczytaj();
    }
}
