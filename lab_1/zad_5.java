
class Macierze{
    int x, y;
    private double[][] macierz;

    public Macierze(int x, int y){
        this.x = x;
        this.y =  y;
        macierz = new double[x][y];
    }
    
    //-----------------------------------
    private void walidujWstawianie(int i, int j) throws IndexOutOfBoundsException{
        if(i < 0 || i > x - 1 || j < 0 || j > y -1){
            throw new IndexOutOfBoundsException("["+i+"]["+j+"]" + " Indeks Poza zakresem! \n");
        }
    }
    //-----------------------------------
    public void wypelnij(int i, int j, double wartosc){
        try{
            walidujWstawianie(i, j);
            macierz[i][j] = wartosc;
        }catch(IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

    }
    //-----------------------------------
    public void drukujMacierz(){
        for(double[] wiersz : macierz){
           for(double v : wiersz){
                System.out.print(v + "   ");
           }
           System.out.println();
        }
    }
    //-----------------------------------
    public Macierze transponujMacierz(){
        Macierze t = new Macierze(y, x);

        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                t.wypelnij(j, i, macierz[i][j]);
            }
        }

        return t;
    }
    //-----------------------------------
    public Macierze dodajMacierz(Macierze macierz_b){
        if(macierz_b.x != this.x || macierz_b.y != this.y){
            throw new IllegalArgumentException("Macierze mają różny rozmiar!");
        }

        Macierze wynikowa = new Macierze(x, y);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                wynikowa.wypelnij(i, j, this.macierz[i][j] + macierz_b.macierz[i][j]);
            }
        }
        return wynikowa;
    }
    //-----------------------------------
    public Macierze mnozMacierz(Macierze macierz_b) {
        if (this.y != macierz_b.x) {
            throw new IllegalArgumentException("Niepoprawne wymiary!");
        }
    
        Macierze wynikowa = new Macierze(this.x, macierz_b.y);
    
        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < macierz_b.y; j++) {
                double sum = 0;
                for (int k = 0; k < this.y; k++) {
                    sum += this.macierz[i][k] * macierz_b.macierz[k][j];
                }
                wynikowa.wypelnij(i, j, sum);
            }
        }
    
        return wynikowa;
    }
    //-----------------------------------
    public double obliczWyznacznik() {
        if (x != y) {
            throw new IllegalStateException("Macierz musi być kwadratowa do obliczenia wyznacznika!");
        }
        return obliczWyznacznikRekurencyjnie(this.macierz);
    }
    
    private double obliczWyznacznikRekurencyjnie(double[][] macierz) {
        int n = macierz.length;
    
        if (n == 1) {
            return macierz[0][0];
        }
    
        if (n == 2) {
            return macierz[0][0] * macierz[1][1] - macierz[0][1] * macierz[1][0];
        }
    
        double wyznacznik = 0;
        for (int j = 0; j < n; j++) {
            wyznacznik += Math.pow(-1, j) * macierz[0][j] * obliczWyznacznikRekurencyjnie(getPodmacierz(macierz, 0, j));
        }
        return wyznacznik;
    }
    
    private double[][] getPodmacierz(double[][] macierz, int wiersz, int kolumna) {
        int n = macierz.length;
        double[][] podmacierz = new double[n-1][n-1];
        int r = 0, k = 0;
        for (int i = 0; i < n; i++) {
            if (i == wiersz) continue;
            k = 0;
            for (int j = 0; j < n; j++) {
                if (j == kolumna) continue;
                podmacierz[r][k] = macierz[i][j];
                k++;
            }
            r++;
        }
        return podmacierz;
    }
    
}
public class zad_5 {
    public static void main(String[] args) {
        Macierze m1 = new Macierze(2, 2);
        m1.wypelnij(0, 0, 1);
        m1.wypelnij(0, 1, 2);
        m1.wypelnij(1, 0, 3);
        m1.wypelnij(1, 1, 4);
        
        System.out.println("Macierz m1:");
        m1.drukujMacierz();
        
        Macierze m1T = m1.transponujMacierz();
        System.out.println("\nTransponowana macierz m1:");
        m1T.drukujMacierz();
        
        Macierze m2 = new Macierze(2, 2);
        m2.wypelnij(0, 0, 5);
        m2.wypelnij(0, 1, 6);
        m2.wypelnij(1, 0, 7);
        m2.wypelnij(1, 1, 8);
        
        System.out.println("\nMacierz m2:");
        m2.drukujMacierz();
        
        Macierze suma = m1.dodajMacierz(m2);
        System.out.println("\nSuma m1 i m2:");
        suma.drukujMacierz();
        
        Macierze iloczyn = m1.mnozMacierz(m2);
        System.out.println("\nIloczyn m1 i m2:");
        iloczyn.drukujMacierz();
        
        Macierze m3 = new Macierze(3, 3);
        m3.wypelnij(0, 0, 2);
        m3.wypelnij(0, 1, 3);
        m3.wypelnij(0, 2, 1);
        m3.wypelnij(1, 0, 4);
        m3.wypelnij(1, 1, 5);
        m3.wypelnij(1, 2, 6);
        m3.wypelnij(2, 0, 7);
        m3.wypelnij(2, 1, 8);
        m3.wypelnij(2, 2, 9);
        
        System.out.println("\nMacierz m3:");
        m3.drukujMacierz();
        
        try {
            double wyznacznik = m3.obliczWyznacznik();
            System.out.println("\nWyznacznik macierzy m3: " + wyznacznik);
        } catch (IllegalStateException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
        
        try {
            Macierze niepoprawna = new Macierze(2, 3);
            niepoprawna.obliczWyznacznik();
        } catch (IllegalStateException e) {
            System.out.println("\nObsłużony błąd: " + e.getMessage());
        }
        
        try {
            m1.wypelnij(5, 5, 10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nObsłużony błąd: " + e.getMessage());
        }
    }
}

