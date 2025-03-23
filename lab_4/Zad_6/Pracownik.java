package Zad_6;
import java.util.*;


enum Wyksztalcenie {
  NIEISTOTNE,
  SREDNIE,
  WYZ_EKON,
  WYZ_TECHN
}

/*
 * Interfejs przyjmuje dowolny Typ i definiuje metodę porownaj,
 */

interface Porownianie<Typ> {
  boolean porownaj(Typ a, Typ b);
}

class PorownajAlfabetycznie implements Porownianie<Pracownik> {
  public boolean porownaj(Pracownik a, Pracownik b) {

      //Wczytywanie nazwisko 
      String nazwiskoA = a.nazwisko();
      String nazwiskoB = b.nazwisko();

      /*

        Porównujemy każdą literę nazwiska, aż do momentu, gdy napotkamy różne znaki.
        Jeśli któraś z liter jest mniejsza (wcześniej w alfabecie), to zwracamy true.
        Jeśli są równe, to porównujemy długości nazwisk.

        Gdy nazwiska są taie same przechodzi do porównania imion w analogiczny sposób.
        Jeśli imiona są takie same, to porównujemy długości imion.
        Jeśli imiona są takie same, to zwracamy true.


       */
      for (int i = 0; i < Math.min(nazwiskoA.length(), nazwiskoB.length()); i++) {
          if (nazwiskoA.charAt(i) < nazwiskoB.charAt(i)) return true;
          if (nazwiskoA.charAt(i) > nazwiskoB.charAt(i)) return false;
      }

      if (nazwiskoA.length() != nazwiskoB.length()) {
          return nazwiskoA.length() < nazwiskoB.length();
      }
      
      String imieA = a.imie();
      String imieB = b.imie();

      for (int i = 0; i < Math.min(imieA.length(), imieB.length()); i++) {
          if (imieA.charAt(i) < imieB.charAt(i)) return true;
          if (imieA.charAt(i) > imieB.charAt(i)) return false;
      }
      return imieA.length() < imieB.length();
  }
}

class PorownajPensje implements Porownianie<Pracownik> {
  
  public boolean porownaj(Pracownik a, Pracownik b) {
    /*
     * Jeśli pensje porównymwanych pracownikow 
     * są rózne sprawdzamy, która jest większa.
     * 
     * Jeśli są rowne analogicznie jak w afabetycznym porównaniu
     * porównujemy nazwiska, a jeśli są takie same to imiona.
     */
      if (a.pensja() != b.pensja()) {
          return a.pensja() > b.pensja();
      }
     
      PorownajAlfabetycznie porownanie = new PorownajAlfabetycznie();
      if (porownanie.porownaj(a, b)) {
          return true;
      } else {
          return false;
      }
      
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

    /*
     * Metodą sortująca działa na liście pracowników. 
     * Na podstawie interfejsu Porownianie przjmyje porównywany typ 
     * oraz kryterium (alfabetyczne lub po pensji). 
     */


    if (lista.size() <= 1) return; // Jeśli lista jest pusta lub 1- elementowa nie sortujemy

    int srodek = lista.size() / 2; //Wybieram pivot (srodek listy)

    List<Pracownik> lewa = new ArrayList<>(); // Lew polowa originalnej listy 
    List<Pracownik> prawa = new ArrayList<>(); //Prawa połowa originalnej listy 
    
    for (int i = 0; i < srodek; i++) { // Dodajemy elementy
        lewa.add(lista.get(i));
    }
    for (int i = srodek; i < lista.size(); i++) {
        prawa.add(lista.get(i)); //Dodajemy elementy
    }
    
    sortuj(lewa, kryterium); //Wywołuję tą samą metdę dla lewej i prawej połowy
    sortuj(prawa, kryterium); 

    //Scalanie:

    int l = 0; // indeksy lewej listy
    int p = 0; // indeksy prawej listy
    int x = 0; // indeksy scalanej listy

    while (l < lewa.size() && p < prawa.size()) {
      if (kryterium.porownaj(lewa.get(l), prawa.get(p))) {
          lista.set(x++, lewa.get(l++)); 
          lista.set(x++, prawa.get(p++));
      }
  }

  while (l < lewa.size()) lista.set(x++, lewa.get(l++));
  while (p < prawa.size()) lista.set(x++, prawa.get(p++));
}


}


