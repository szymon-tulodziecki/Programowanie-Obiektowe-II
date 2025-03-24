package Zad_1;
import java.util.ArrayList;

import Zad_1.Pracownik;


class Szef extends Pracownik {
    private ArrayList<Pracownik> podwladni;

    public Szef(Pracownik p, double nowaPensja) {
        super(
            p.nazwisko(),
            p.imie(),
            p.wyksztalcenie(),
            nowaPensja
        );        
        podwladni = new ArrayList<Pracownik>();
    }

    public void dodajPodwladnego(Pracownik p) {
        podwladni.add(p);
    }

    public void sprawdzCzyjestPodwladnym(Pracownik p) {
        for(Pracownik Podwladny : podwladni) {
            if (Podwladny.equals(p)) {
                System.out.println("Jest podwladnym");
                return;
            }
        }

        System.out.println("Nie jest podwladnym");
   }
}


