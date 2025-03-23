package Zad_4;
import java.util.*;


enum Wyksztalcenie {
  NIEISTOTNE,
  SREDNIE,
  WYZ_EKON,
  WYZ_TECHN
}

interface Porownianie<Typ> {
  boolean porownaj(Typ a, Typ b);
}

class PorownajAlfabetycznie implements Porownianie<Pracownik> {
  public boolean porownaj(Pracownik a, Pracownik b) {
      int x = a.nazwisko().compareTo(b.nazwisko());
      return x < 0 || (x == 0 && a.imie().compareTo(b.imie()) < 0);
  }
}

class PorownajPensje implements Porownianie<Pracownik> {
  public boolean porownaj(Pracownik a, Pracownik b) {
      return a.pensja() > b.pensja() 
          || (a.pensja() == b.pensja() && a.nazwisko().compareTo(b.nazwisko()) < 0);
  }
}

// Pracownik pomocniczy:
class GenPracownik {
  public static int maxDlNazwiska=20,  maxDlImienia=15;
  public static double maxPensja=100_000_000;

  private String nazwisko, imie;
  private Wyksztalcenie wk;
  private double pensja;

  /* Konstruktor -- losowo tworzy nowego pracownika.
   *   dopuszczalna dlugosc nazwiska: miedzy  3  a  20 ,
   *   dopuszczalna dlugosc imienia: miedzy  3  a  15 ,
   *   dopuszczalna pensja: miedzy  0  a  99 999 999 .
   */
  public GenPracownik() {
    Random rnd = new Random();

    // losowanie dlugosci nazwiska (miedzy 3 a maxDlNazwiska):
    int dl = rnd.nextInt(maxDlNazwiska-3)+3;
    // losowanie pierwszej (duzej) litery nazwiska:
    nazwisko = "" + (char)(rnd.nextInt(26)+(int)'A');
    // losowanie pozostalych liter nazwiska:
    for (int i=1; i<dl; i++)
      nazwisko += (char)(rnd.nextInt(26)+(int)'a');

    // losowanie dlugosci imienia (miedzy 3 a maxDlImienia):
    dl = rnd.nextInt(maxDlImienia-3)+3;
    // losowanie pierwszej (duzej) litery imienia:
    imie = "" + (char)(rnd.nextInt(26)+(int)'A');
    // losowanie pozostalych liter imienia:
    for (int i=1; i<dl; i++)
      imie += (char)(rnd.nextInt(26)+(int)'a');

    // losowanie wyksztalcenia:
       wk = Wyksztalcenie.values()[rnd.nextInt(4)];

    // losowanie wysokosci pensji (miedzy 0 a maxPensja):
    pensja = rnd.nextDouble()*maxPensja;
  }

  // Selektory:
  public String nazwisko() { return nazwisko; }
  public String imie() { return imie; }
  public Wyksztalcenie wk() { return wk; }
  public double pensja() { return pensja; }

}
class Pracownik {
  private String nazwisko, imie;
  private Wyksztalcenie wyksztalcenie;
  private double pensja;

  // konstruktor:
  public Pracownik(String nz, String im,
                   Wyksztalcenie wk, double ps) {
    nazwisko = nz;  imie = im;  wyksztalcenie = wk;  pensja = ps;
  }

  // konstruktor alternatywny:
  public Pracownik() {
    GenPracownik pr = new GenPracownik();
    nazwisko = pr.nazwisko();
    imie = pr.imie();
    wyksztalcenie = pr.wk();
    pensja = pr.pensja();
  }

  // selektory:
  public String nazwisko() { return nazwisko; }
  public String imie() { return imie; }
  public Wyksztalcenie wyksztalcenie() { return wyksztalcenie; }
  public double pensja() { return pensja; }

  // zmiana pensji:
  public void nowaPensja(double ps) { pensja = ps; }

  // tozsamosc:
  public Boolean equals(Pracownik prac) {
    return  nazwisko.equals(prac.nazwisko) && imie.equals(prac.imie);
  }

  // wydruk w tabeli:
  private String spacje(int n) {
    String sp = "";
    for (int i=0; i<n; i++)  sp += " ";
    return sp;
  }
  public String toString() {
    return
      nazwisko + spacje(20 - nazwisko.length()) + " | " +
      imie + spacje(15 - imie.length()) + " | " +
      wyksztalcenie.name() +
        spacje(10 - wyksztalcenie.name().length()) + " | " +
      spacje(9 - Double.valueOf(pensja).toString().length()) +
      Double.valueOf(pensja) ;
  }
  public static void sortuj(List<Pracownik> lista, Porownianie<Pracownik> kryterium) {
    for (int i = 0; i < lista.size() - 1; i++) {
        for (int j = 0; j < lista.size() - i - 1; j++) {
            Pracownik a = lista.get(j);
            Pracownik b = lista.get(j+1);
            
            if (!kryterium.porownaj(a, b)) {
                Pracownik temp = lista.get(j);
                lista.set(j, lista.get(j+1));
                lista.set(j+1, temp);
            }
        }
    }
}

}

