class Ksiazka{
    int jaka;
    public Ksiazka(int jaka){
        this.jaka = jaka;
    }
}

class Polka{
    int jaka;
    int ileKsiazek  = 0;

    public Polka(int jaka){
        this.jaka = jaka;
    }

    void dodajKsiazke(Ksiazka nowaKsiazka){
        ileKsiazek++;
    }
}

class Regal{
    int ile = 0;

    public void dodajPolke(Polka p){
        ile++;
    }

    public int ilePolek(){
        return ile;
    }

}

public class zad_3{
    public static void main(String[] args){
        Regal r = new Regal( );
        r.dodajPolke(new Polka(20));
        Polka p = new Polka(20);
        p.dodajKsiazke(new Ksiazka(1111));
        r.dodajPolke(p);
        System.out.println(r.ilePolek());
        System.out.println(p.ileKsiazek);
    }
}