package Zad_2;

public class Firma {

    public static void main(String[] args) {

        Pracownik szef = new Pracownik("Kopernik", "Mikołaj", Wyksztalcenie.WYZ_TECHN, 190_000);
        Szef prezes = new Szef(szef, 200_000);
        
        Pracownik[] pracownicy = new Pracownik[15];
        
        pracownicy[0] = new Pracownik("Nowak", "Adam", Wyksztalcenie.WYZ_TECHN, 50_000);
        pracownicy[1] = new Pracownik("Kowalska", "Ewa", Wyksztalcenie.SREDNIE, 40_000);
        pracownicy[2] = new Pracownik("Zielinski", "Piotr", Wyksztalcenie.NIEISTOTNE, 30_000);
        pracownicy[3] = new Pracownik("Nowicka", "Anna", Wyksztalcenie.WYZ_EKON, 60_000);
        pracownicy[4] = new Pracownik("Wojcik", "Tomasz", Wyksztalcenie.WYZ_TECHN, 70_000);
        pracownicy[5] = new Pracownik("Kowal", "Katarzyna", Wyksztalcenie.SREDNIE, 80_000);
        pracownicy[6] = new Pracownik("Baran", "Marek", Wyksztalcenie.NIEISTOTNE, 90_000);
        pracownicy[7] = new Pracownik("Michalczyk", "Zofia", Wyksztalcenie.WYZ_EKON, 100_000);
        pracownicy[8] = new Pracownik("Kamiński", "Jakub", Wyksztalcenie.WYZ_TECHN, 110_000);
        pracownicy[9] = new Pracownik("Szybka", "Natalia", Wyksztalcenie.SREDNIE, 120_000);
        pracownicy[10] = new Pracownik("Wolny", "Michał", Wyksztalcenie.NIEISTOTNE, 130_000);
        pracownicy[11] = new Pracownik("Góra", "Krzysztof", Wyksztalcenie.WYZ_EKON, 140_000);
        pracownicy[12] = new Pracownik("Sroka", "Barbara", Wyksztalcenie.WYZ_TECHN, 150_000);
        pracownicy[13] = new Pracownik("Grzyb", "Wojciech", Wyksztalcenie.SREDNIE, 160_000);
        pracownicy[14] = new Pracownik("Niemieszko", "Agnieszka", Wyksztalcenie.NIEISTOTNE, 170_000);

        Szef manager1 = new Szef(pracownicy[0], 180_000);
        Szef manager2 = new Szef(pracownicy[1], 190_000);


        prezes.dodajPodwladnego(manager1);
        prezes.dodajPodwladnego(manager2);

        for(int i=2; i<=6; i++) {
            manager1.dodajPodwladnego(pracownicy[i]);
        }

        for(int i=7; i<=14; i++) {
            manager2.dodajPodwladnego(pracownicy[i]);
        }
        
        System.out.println("\n\n\n");
        System.out.println(prezes);
        System.out.println("_________________________________________________________________");
        prezes.drukujPodwladnych("\t|");
    }
}





