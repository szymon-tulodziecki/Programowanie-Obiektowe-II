package Zad_5;
import java.util.ArrayList;


class Osoba {
  private String nazwisko;
  private Osoba ojciec;
  private ArrayList<Osoba> synowie;

  public Osoba(String nazwisko, Osoba ojciec) {
    this.nazwisko = nazwisko;
    this.ojciec = ojciec;
    this.synowie = new ArrayList<Osoba>();
  }

  // dodawanie syna:
  public void nowySyn(Osoba syn) {
    synowie.add(syn);
  }

  // selektory:
  public String nazwisko() { return nazwisko; }
  public Osoba ojciec() { return ojciec; }
  public ArrayList<Osoba> synowie() { return synowie; }

  // postac tekstowa:
  public String toString() {
    String napis = nazwisko + "\n";
    for (int i=0; i<synowie.size(); i++) {
      napis += "|\n";
      String[] wiersze = synowie.get(i).toString().split("\n");
      for (int j=0; j<wiersze.length; j++) {
        if (i == synowie.size()-1)
          if (j == 0)  napis += "`-";
          else  napis += "  ";
        else
          if (j == 0)  napis += "|-";
          else  napis += "| ";
        napis += wiersze[j] + "\n";
      }
    }
    return napis;
  }
}


class Piastowie {
  public static void main(String[] args) {
    Osoba piast = new Osoba("Piast", null);
    Osoba siemowit = new Osoba("Siemowit", piast);
    Osoba lestek = new Osoba("Lestek", siemowit);
    Osoba siemomysl = new Osoba("Siemomysl", lestek);
    Osoba mieszkoI = new Osoba("Mieszko I", siemomysl);
    Osoba czcibor = new Osoba("Czcibor", siemomysl);
    Osoba mieszko = new Osoba("Mieszko", mieszkoI);
    Osoba lambert = new Osoba("Lambert", mieszkoI);
    Osoba swietopelk = new Osoba("Swietopelk", mieszkoI);
    Osoba chrobry = new Osoba("Boleslaw I Chrobry", mieszkoI);
    Osoba bezprym = new Osoba("Bezprym", chrobry);
    Osoba mieszkoII = new Osoba("MieszkoII", chrobry);
    Osoba otto = new Osoba("Otto", chrobry);

    Osoba kazimierzOdnowiciel = new Osoba("Kazimierz I Odnowiciel", mieszkoII);
    Osoba herman = new Osoba("Wladyslaw I Herman", kazimierzOdnowiciel);
    Osoba szczodry = new Osoba("Boleslaw II Szczodry", kazimierzOdnowiciel);
    Osoba krzywoustyy = new Osoba("Boleslaw III Krzywousty", herman);


    piast.nowySyn(siemowit);
    siemowit.nowySyn(lestek);
    lestek.nowySyn(siemomysl);
    siemomysl.nowySyn(mieszkoI);

    mieszkoI.nowySyn(mieszko);
    mieszkoI.nowySyn(lambert);
    mieszkoI.nowySyn(swietopelk);
    mieszkoI.nowySyn(chrobry);

    chrobry.nowySyn(bezprym);
    chrobry.nowySyn(mieszkoII);
    chrobry.nowySyn(otto);

    mieszkoII.nowySyn(kazimierzOdnowiciel);
    kazimierzOdnowiciel.nowySyn(herman);
    kazimierzOdnowiciel.nowySyn(szczodry);

    herman.nowySyn(krzywoustyy);
    siemomysl.nowySyn(czcibor);

    System.out.println("\n" + piast.toString());
  
  }
}
