import java.util.Scanner;

public class Zad_3 {

    public static void wczytaj(){
        int r;

        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();

        int[] tab = new int[r];

        for(int i = 0; i < r; i++){
            tab[i] = sc.nextInt();
        }

        sortuj(tab);
    }

    public static void sortuj(int[] tab) {
        int n = tab.length;
        int porownania = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean zamiana = false;

            for (int j = 0; j < n - 1 - i; j++) {
                porownania++;
                if (tab[j] > tab[j + 1]) {
                    tab[j] = tab[j] ^ tab[j + 1];
                    tab[j + 1] = tab[j] ^ tab[j + 1];
                    tab[j] = tab[j] ^ tab[j + 1];
                    zamiana = true;
                }
            }

            if (!zamiana) {
                break;
            }
        }

        System.out.println("Porównań: " + porownania);
    }

    public static void main(String[] args) {
        wczytaj();
    }
}
