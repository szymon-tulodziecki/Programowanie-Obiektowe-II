package lab_5.zad_3;

import java.util.*;


class Drz<T> {
  private T wierzch;
  private ArrayList<Drz<T>> dzieci = new ArrayList<Drz<T>>();
  
  // konstruktor (sam korzen bez dzieci):
  public Drz(T el) { wierzch = el; }

  // selektory:
  public T wierzch() { return wierzch; }
  public int ileDzieci() { return dzieci.size(); }
  public Drz<T> dziecko(int nr) { // n-te poddrzewo od lewej
    return dzieci.get(nr);
  }

  // dolaczenie nowego poddrzewa:
  public void nowe(Drz<T> d) { dzieci.add(d); }
}


class Drzewo<T> extends Drz<T> {
  public Drzewo(T el) { super(el); }

  public int ileWezlow() {
    int ile = 0;

    for (int i = 0; i < ileDzieci(); i++) {
        Drzewo<T> d = (Drzewo<T>) dziecko(i);
        ile += d.ileWezlow();
    }

    return ile + 1;
  }

  public int ilePokolen() {
        int ile = 0;
        if(ileDzieci() == 0) {
            return 1;
        }
        for (int i = 0; i < ileDzieci(); i++) {
            Drzewo<T> d = (Drzewo<T>) dziecko(i);
            int pokolenie = d.ilePokolen();
            if(pokolenie > ile) {
                ile = pokolenie;
            }
        }
        return ile + 1;
  }

}


class Drzewa {
  public static void main(String[] args) {
    // tu wpisac konstrukcje drzew i wydruk ich
    // liczby wezlow i pokolen
    Drzewo<Double> drzewo = new Drzewo<Double>(5.3);
    Drzewo<Double> drzewo1 = new Drzewo<Double>(3.14);
    Drzewo<Double> drzewo2 = new Drzewo<Double>(-2.00);
    Drzewo<Double> drzewo3 = new Drzewo<Double>(7.6);

    drzewo.nowe(drzewo1);
    drzewo.nowe(drzewo2);
    drzewo.nowe(drzewo3);

    drzewo1.nowe(new Drzewo<Double>(0.0));
    drzewo1.nowe(new Drzewo<Double>(0.1));

    Drzewo<Double> drzewo4 = new Drzewo<Double>(13.0);

    drzewo2.nowe(drzewo4);

    drzewo4.nowe(new Drzewo<Double>(4.1));

    Drzewo<Double> drzewo5 = new Drzewo<Double>(4.2);

    drzewo4.nowe(drzewo5);
    drzewo5.nowe(new Drzewo<Double>(6.8));

    System.out.println("Liczba wezlow: " + drzewo.ileWezlow());
    System.out.println("Liczba pokolen: " + drzewo.ilePokolen());


    System.out.println("-----------------------------------------------");

    Drzewo<String> drzewko = new Drzewo<String>("Mieszko I");
    drzewko.nowe(new Drzewo<String>("Lambert"));
    drzewko.nowe(new Drzewo<String>("Swietopełk"));
    Drzewo<String> drzewko1 = new Drzewo<String>("Boleslaw Chrobry");

    drzewko.nowe(drzewko1);

    drzewko1.nowe(new Drzewo<String>("Bezprym"));
    Drzewo <String> drzewko2 = new Drzewo<String>("Mieszko II");
    drzewko1.nowe(drzewko2);
    drzewko2.nowe(new Drzewo<String>("Kazimierz Odnowiciel"));
    drzewko1.nowe(new Drzewo<String>("Otto"));

    System.out.println("Liczba wezlow: " + drzewko.ileWezlow());
    System.out.println("Liczba pokolen: " + drzewko.ilePokolen());

    System.out.println("-----------------------------------------------");}
}