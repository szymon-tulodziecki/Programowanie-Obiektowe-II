import java.util.Arrays; // Do kopiowania zawartości tablic dla testów
import java.util.Scanner; // Do wczytania danych z konsoli 

abstract class BubbleSort {
    private int porownania;
    
    abstract protected int porownajNapisy(String s1, String s2);
    
    public void sortuj(String[] tab) {
        int n = tab.length;
        int ostatniaZamiana;
        this.porownania = 0;

        do {
            ostatniaZamiana = 0;
            for (int j = 0; j < n - 1; j++) {
                this.porownania++;
                if (porownajNapisy(tab[j], tab[j + 1]) > 0) {
                    String temp = tab[j];
                    tab[j] = tab[j + 1];
                    tab[j + 1] = temp;
                    ostatniaZamiana = j + 1;
                }
            }
            n = ostatniaZamiana;
        } while (ostatniaZamiana > 0);
    }

    public int ilePorownan() {
        return porownania;
    }
}

class alfabetycznie extends BubbleSort {
    @Override
    protected int porownajNapisy(String s1, String s2) {
        int krotszy = (s1.length() < s2.length()) ? s1.length() : s2.length();
        for (int i = 0; i < krotszy; i++) {
            int roznica = s1.charAt(i) - s2.charAt(i);
            if (roznica != 0) return roznica;
        }
        return s1.length() - s2.length();
    }
}

class odwrotnie extends BubbleSort {
    @Override
    protected int porownajNapisy(String s1, String s2) {
        int krotszy = (s1.length() < s2.length()) ? s1.length() : s2.length();
        for (int i = 0; i < krotszy; i++) {
            int roznica = s2.charAt(i) - s1.charAt(i);
            if (roznica != 0) return roznica;
        }
        return s2.length() - s1.length();
    }
}

class atergo extends BubbleSort {
    @Override
    protected int porownajNapisy(String s1, String s2) {
        int krotszy = (s1.length() < s2.length()) ? s1.length() : s2.length();
        for (int i = 1; i <= krotszy; i++) {
            char c1 = s1.charAt(s1.length() - i);  
            char c2 = s2.charAt(s2.length() - i);
            if (c1 != c2) return c1 - c2;
        }
        return s1.length() - s2.length();
    }
}

public class Zad_6 {

    public static String[] wczytajZkonsoli() {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        sc.nextLine();  
    
        String[] tab = new String[r];
    
        for (int i = 0; i < r; i++) {
            tab[i] = sc.nextLine();
        }
        
        return tab;
    }
    
    
    

    public static void main(String[] args) {
        // Porownanie sortowania:

        /* Dla mniejszej tablicy 50- elementow
             String[] tablica = { 
            "XZOeyXsouK", "IivNcGFigx", "iVqOUvcbWP", "DgXLFGrnun", "GYNhpWmruy",
            "ISBCKbVuKL", "WPyTnFWWBT", "MVTUDjbVyW", "AjkoimkJXW", "cGqBeukAla",
            "pWFRgRbGlg", "cYAWgjUfZN", "UTiXvULZEi", "EAsgRppCxC", "xLVhHPEQAB",
            "WitXKeABpi", "IxfLCeQAxM", "hdpNsKUAqD", "hmGhigmRxU", "gKvWKYjMAM",
            "NZhoTFRmaU", "yYsnVmOVLV", "MSCQFBFckX", "RlVJzfTUfi", "OiSBiHuDog",
            "AmzxWflBxo", "CjpzDyjLgP", "fDUXkSIKtA", "aHgpnpsMpF", "wDLpNWXoZJ",
            "mMuDULTIEx", "fItUFSDywL", "GbMGJypzqk", "BlQnRgwHwP", "gyuGqFyepj",
            "AgSCnWBEIR", "JdVveVgMUp", "flGGIBUrOe", "CzHYfsKTSQ", "sNvPouzHQN",
            "CBvqxRCRhA", "CHkATJalqU", "lqgkuPbtWd", "QPGOayGcvq", "PVbhdkbxdu",
            "xruUvwtLPO", "xCWWCLvbKR", "kimBLYfVck", "uhsieIBBEk", "kcDIXcuUDO"
            };
        String[] tablica2 = Arrays.copyOf(tablica, tablica.length); 
        String[] tablica3 = Arrays.copyOf(tablica, tablica.length);
         */


         // Dla danych z: https://unixlab.iis.pwsz.elblag.pl/~stefan/Dydaktyka/ProgObiII/Prog03/dane-slowa-10tys
         // i https://unixlab.iis.pwsz.elblag.pl/~stefan/Dydaktyka/ProgObiII/Prog03/dane-slowa-100tys :


         String[] tablica = wczytajZkonsoli();
         String[] tablica2 = Arrays.copyOf(tablica, tablica.length);
         String[] tablica3 = Arrays.copyOf(tablica, tablica.length);


        BubbleSort sort1 = new alfabetycznie(); // Tworzę obiekt klasy buble sort z metodą porównywania  alfabetycznego
        sort1.sortuj(tablica); 
        System.out.println("Ilość porównań dla sortowania alfabetycznego: " + sort1.ilePorownan());
   
        BubbleSort sort2 = new odwrotnie(); // Analogicznie z metodą sortwania odwrotnego (od końca)
        sort2.sortuj(tablica2);
        System.out.println("Ilość porównań dla sortowania odwrotnego: " + sort2.ilePorownan());

        BubbleSort sort3 = new atergo();
        sort3.sortuj(tablica3); // Analogicznie z metodą sortowania od końca 
        System.out.println("Ilość porównań dla sortowania atergo: " + sort3.ilePorownan());


        
    }
}

