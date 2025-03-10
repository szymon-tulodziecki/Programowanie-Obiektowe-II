
import java.math.BigInteger;

class Klucz {
    private final BigInteger p, q, r, e, d;

    public Klucz(BigInteger p, BigInteger q, BigInteger e) {
        if (!p.isProbablePrime(20) || !q.isProbablePrime(20)) {
            throw new IllegalArgumentException("p i q muszą być liczbami pierwszymi!");
        }

        this.p = p;
        this.q = q;
        this.r = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); 

        if (!e.gcd(phi).equals(BigInteger.ONE)) {
            throw new IllegalArgumentException("e musi być względnie pierwsze z φ(n)!");
        }

        this.e = e; 
        this.d = e.modInverse(phi); 
    }

    public BigInteger getR() {
        return r;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }
}

public class zad_5 {

    public static BigInteger[] koduj(String tekst, Klucz k) {
        BigInteger r = k.getR();
        BigInteger e = k.getE();
        BigInteger[] zaszyfrowane = new BigInteger[tekst.length()];

        for (int i = 0; i < tekst.length(); i++) {
            BigInteger hlp = BigInteger.valueOf(tekst.charAt(i));

            if (hlp.compareTo(r) >= 0) {
                throw new IllegalArgumentException("Kod ASCII znaku jest zbyt duży dla podanego modułu!");
            }

            zaszyfrowane[i] = hlp.modPow(e, r); 
        }

        return zaszyfrowane;
    }

    public static String dekoduj(BigInteger[] zaszyfrowane, Klucz k) {
        BigInteger r = k.getR();
        BigInteger d = k.getD();
        StringBuilder odszyfrowanyTekst = new StringBuilder();

        for (BigInteger zaszyfrowany : zaszyfrowane) {
            BigInteger tekst = zaszyfrowany.modPow(d, r); 
            odszyfrowanyTekst.append((char) tekst.intValue());
        }

        return odszyfrowanyTekst.toString();
    }

    public static void main(String[] args) {
        try {
            BigInteger p = new BigInteger("17");
            BigInteger q = new BigInteger("23");
            BigInteger e = new BigInteger("7");

            Klucz klucz = new Klucz(p, q, e);

            String tekst = "SZYMON 21312";
            System.out.println("Oryginalna wiadomość: " + tekst);

            BigInteger[] zakodowane = koduj(tekst, klucz);

            System.out.print("Zaszyfrowana wiadomość: ");
            for (BigInteger zakodowana : zakodowane) {
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