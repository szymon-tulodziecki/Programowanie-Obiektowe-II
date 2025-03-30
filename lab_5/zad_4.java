package lab_5;
import java.util.ArrayList;
import java.util.List;

class Krawedz {
    private final int cel;
    private final int waga;

    public Krawedz(int cel, int waga) {
        this.cel = cel;
        this.waga = waga;
    }

    public int getCel() {
        return cel;
    }

    public int getWaga() {
        return waga;
    }

    @Override
    public String toString() {
        return String.format("->%d (%d)", cel, waga);
    }
}

class ListaIncydencji {
    private final int liczbaWierzcholkow;
    private final List<List<Krawedz>> lista;

    public ListaIncydencji(int liczbaWierzcholkow) {
        if (liczbaWierzcholkow <= 0)
            throw new IllegalArgumentException("Liczba wierzchołków musi być dodatnia");
        
        this.lista = new ArrayList<>(liczbaWierzcholkow + 1);
        this.liczbaWierzcholkow = liczbaWierzcholkow;
        
        for (int i = 0; i <= liczbaWierzcholkow; i++) {
            lista.add(new ArrayList<>());
        }
    }

    public int getLiczbaWierzcholkow() {
        return liczbaWierzcholkow;
    }

    public List<Krawedz> getSasiedzi(int wierzcholek) {
        sprawdzWierzcholek(wierzcholek);
        return lista.get(wierzcholek);
    }

    private void sprawdzWierzcholek(int wierzcholek) {
        if (wierzcholek < 1 || wierzcholek > liczbaWierzcholkow)
            throw new IllegalArgumentException(
                String.format("Wierzchołek %d poza zakresem [1-%d]", wierzcholek, liczbaWierzcholkow)
            );
    }

    public void dodajKrawedz(int zrodlo, int cel, int waga) {
        sprawdzWierzcholek(zrodlo);
        sprawdzWierzcholek(cel);
        lista.get(zrodlo).add(new Krawedz(cel, waga));
    }
    
    public void wyswietlListe() {        
        System.out.println("\nLISTA INCYDENCJI:");
        for (int i = 1; i <= liczbaWierzcholkow; i++) {
            System.out.printf("%2d | ", i);
            for (Krawedz k : lista.get(i)) {
                System.out.print(k + " ");
            }
            System.out.println("\n-------------------------------");
        }
    }

    public static ListaIncydencji konwertujZMacierzy(MacierzSasiedztwa macierz) {
        int n = macierz.getLiczbaWierzcholkow();
        ListaIncydencji lista = new ListaIncydencji(n);
        
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {  
                int waga = macierz.getWaga(i, j);
                if(waga != 0) {
                    lista.dodajKrawedz(i, j, waga);
                }
            }
        }
        return lista;
    }
}

class MacierzSasiedztwa {
    private final int liczbaWierzcholkow;
    private final int[][] macierz;
    
    public MacierzSasiedztwa(int liczbaWierzcholkow) {
        if (liczbaWierzcholkow <= 0)
            throw new IllegalArgumentException("Liczba wierzchołków musi być dodatnia");
        
        this.liczbaWierzcholkow = liczbaWierzcholkow;
        this.macierz = new int[liczbaWierzcholkow + 1][liczbaWierzcholkow + 1];
        
        for (int i = 1; i <= liczbaWierzcholkow; i++) {
            macierz[0][i] = i;
            macierz[i][0] = i;
        }
    }

    public int getLiczbaWierzcholkow() {
        return liczbaWierzcholkow;
    }

    public int getWaga(int wierzcholek1, int wierzcholek2) {
        sprawdzWierzcholek(wierzcholek1);
        sprawdzWierzcholek(wierzcholek2);
        return macierz[wierzcholek1][wierzcholek2];
    }
    
    private void sprawdzWierzcholek(int wierzcholek) {
        if (wierzcholek < 1 || wierzcholek > liczbaWierzcholkow)
            throw new IllegalArgumentException("Nieprawidłowy wierzchołek: " + wierzcholek + ". Dopuszczalny zakres: 1-" + liczbaWierzcholkow);
    }

    public void dodajKrawedz(int zrodlo, int cel, int waga) {
        sprawdzWierzcholek(zrodlo);
        sprawdzWierzcholek(cel);
        macierz[zrodlo][cel] = waga;
    }
    
    public void wyswietlMacierz() {
        System.out.print("   |");
        for (int j = 1; j <= liczbaWierzcholkow; j++) {
            System.out.printf("%3d|", macierz[0][j]);
        }
        System.out.println();
    
        for (int i = 0; i < 3 + 4 * liczbaWierzcholkow; i++) {
            System.out.print("-");
        }
        System.out.println();
    
        for (int i = 1; i <= liczbaWierzcholkow; i++) {
            System.out.printf("%2d |", macierz[i][0]);
            for (int j = 1; j <= liczbaWierzcholkow; j++) {
                System.out.printf("%3s|", macierz[i][j] == 0 ? " " : macierz[i][j]);
            }
            System.out.println();
    
            for (int k = 0; k < 3 + 4 * liczbaWierzcholkow; k++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }    

    public static MacierzSasiedztwa konwertujZListy(ListaIncydencji lista) {
        int n = lista.getLiczbaWierzcholkow();
        MacierzSasiedztwa macierz = new MacierzSasiedztwa(n);
        
        for (int i = 1; i <= n; i++) {
            for (Krawedz k : lista.getSasiedzi(i)) {
                macierz.dodajKrawedz(i, k.getCel(), k.getWaga());
            }
        }
        return macierz;
    }
}

public class zad_4 {
    public static void main(String[] args) {
        System.out.println("Macierz A:");
        MacierzSasiedztwa macierz = new MacierzSasiedztwa(5);
        macierz.dodajKrawedz(1, 2, 1);
        macierz.dodajKrawedz(1, 3, 2);
        macierz.dodajKrawedz(2, 3, 3);
        macierz.dodajKrawedz(2, 4, 4);
        macierz.dodajKrawedz(3, 5, 5);
        macierz.wyswietlMacierz();

        System.out.println("\nKONWERSJA MACIERZ A -> LISTA A:");
        ListaIncydencji listaZMacierzy = ListaIncydencji.konwertujZMacierzy(macierz);
        listaZMacierzy.wyswietlListe();

        System.out.println("\nLista B:");
        ListaIncydencji lista = new ListaIncydencji(5);
        lista.dodajKrawedz(1, 2, 1);
        lista.dodajKrawedz(1, 3, 2);
        lista.dodajKrawedz(2, 3, 3);
        lista.dodajKrawedz(2, 4, 4);
        lista.dodajKrawedz(3, 5, 5);
        lista.wyswietlListe();

        System.out.println("\nKONWERSJA LISTA B -> MACIERZ B:");
        MacierzSasiedztwa macierzZListy = MacierzSasiedztwa.konwertujZListy(lista);
        macierzZListy.wyswietlMacierz();
    }
}


