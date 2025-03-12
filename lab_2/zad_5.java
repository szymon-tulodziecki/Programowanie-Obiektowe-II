class Klucz {
    private final int p, q, r, e, d;

    public Klucz(int p, int q, int e) {
        if (!isProbablePrime(p) || !isProbablePrime(q)) {
            throw new IllegalArgumentException("p i q muszą być liczbami pierwszymi!");
        }

        this.p = p;
        this.q = q;
        this.r = p * q;
        int phi = (p - 1) * (q - 1);

        if (gcd(e, phi) != 1) {
            throw new IllegalArgumentException("e musi być względnie pierwsze z φ(n)!");
        }

        this.e = e; 
        this.d = modInverse(e, phi); 
    }

    private boolean isProbablePrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int modInverse(int a, int m) {
        int m0 = m, t, q;
        int x0 = 0, x1 = 1;

        if (m == 1) return 0;

        while (a > 1) {
            q = a / m;
            t = m;
            m = a % m;
            a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0) x1 += m0;

        return x1;
    }

    public int getR() {
        return r;
    }

    public int getE() {
        return e;
    }

    public int getD() {
        return d;
    }
}

public class zad_5 {

    public static int[] koduj(String tekst, Klucz k) {
        int r = k.getR();
        int e = k.getE();
        int[] zaszyfrowane = new int[tekst.length()];

        for (int i = 0; i < tekst.length(); i++) {
            int hlp = (int) tekst.charAt(i);

            if (hlp >= r) {
                throw new IllegalArgumentException("Kod ASCII znaku jest zbyt duży dla podanego modułu!");
            }

            zaszyfrowane[i] = modPow(hlp, e, r); 
        }

        return zaszyfrowane;
    }

    public static String dekoduj(int[] zaszyfrowane, Klucz k) {
        int r = k.getR();
        int d = k.getD();
        StringBuilder odszyfrowanyTekst = new StringBuilder();

        for (int zaszyfrowany : zaszyfrowane) {
            int tekst = modPow(zaszyfrowany, d, r); 
            odszyfrowanyTekst.append((char) tekst);
        }

        return odszyfrowanyTekst.toString();
    }

    private static int modPow(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;
            exp >>= 1;
            base = (base * base) % mod;
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            int p = 17, q = 23, e = 7;

            Klucz klucz = new Klucz(p, q, e);

            String tekst = "SZYMON 21312";
            System.out.println("Oryginalna wiadomość: " + tekst);

            int[] zakodowane = koduj(tekst, klucz);

            System.out.print("Zaszyfrowana wiadomość: ");
            for (int zakodowana : zakodowane) {
                System.out.print(zakodowana + " ");
            }

            System.out.println();

            String odkodowane = dekoduj(zakodowane, klucz);
            System.out.println("Odszyfrowana wiadomość: " + odkodowane);

        } catch (IllegalArgumentException ex) {
            System.out.println("Błąd: " + ex.getMessage());
        }
    }
}