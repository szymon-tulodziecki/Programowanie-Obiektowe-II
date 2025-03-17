import java.util.Scanner;

public class Zad_4 {

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
        int n = tab.length, porownania = 0, ostatniaZamiana;


        do {
            ostatniaZamiana = 0;
            for (int j = 0; j < n - 1; j++) {
                porownania++;
                if (tab[j] > tab[j + 1]) {
                    tab[j] = tab[j] ^ tab[j + 1];
                    tab[j + 1] = tab[j] ^ tab[j + 1];
                    tab[j] = tab[j] ^ tab[j + 1];
                }
                ostatniaZamiana = j + 1;
            }
            n = ostatniaZamiana;
        }while(ostatniaZamiana > 0);


        System.out.println("Porównań: " + porownania);
    }

    public static void main(String[] args) {
        wczytaj();
    }
}
