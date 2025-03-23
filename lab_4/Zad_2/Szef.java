package Zad_2;
import java.util.ArrayList;

import Zad_2.Pracownik;


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

   public void drukujPodwladnych(String przesuniecie){
        for(Pracownik podwladny : podwladni) {
            System.out.println(przesuniecie + podwladny);
            System.out.println(przesuniecie + "_________________________________________________________________\n");

            if (podwladny instanceof Szef) {
                Szef manager = (Szef) podwladny;
                manager.drukujPodwladnych(przesuniecie + "\t|");
        }
   }
}
}


