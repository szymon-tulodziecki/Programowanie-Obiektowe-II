import java.math.BigInteger;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        BigInteger p = BigInteger.probablePrime(512, rand);
        BigInteger q = BigInteger.probablePrime(512, rand);
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = BigInteger.valueOf(65537); // Typowy wybór dla RSA
        BigInteger d = e.modInverse(phi);

        String tekst = "Milcz serce";
        BigInteger zaszyfrowana = szyfruj(tekst, e, n);
        System.out.println("Zaszyfrowana liczba: " + zaszyfrowana);

        String odszyfrowany = odszyfruj(zaszyfrowana, d, n);
        System.out.println("Odszyfrowany tekst: " + odszyfrowany);
    }

    public static BigInteger szyfruj(String tekst, BigInteger e, BigInteger n) {
        StringBuilder ascii = new StringBuilder();
        for (char c : tekst.toCharArray()) {
            ascii.append((int) c).append(",");
        }
        String[] asciiArray = ascii.toString().split(",");
        BigInteger wynik = BigInteger.ZERO;

        for (String s : asciiArray) {
            if (!s.isEmpty()) {
                BigInteger b = new BigInteger(s);
                wynik = wynik.shiftLeft(8).add(b);
            }
        }

        return wynik.modPow(e, n);
    }

    public static String odszyfruj(BigInteger zaszyfrowana, BigInteger d, BigInteger n) {
        BigInteger odszyfrowana = zaszyfrowana.modPow(d, n);
        StringBuilder tekst = new StringBuilder();

        while (odszyfrowana.compareTo(BigInteger.ZERO) > 0) {
            BigInteger b = odszyfrowana.and(BigInteger.valueOf(255));
            tekst.insert(0, (char) b.intValue());
            odszyfrowana = odszyfrowana.shiftRight(8);
        }

        return tekst.toString();
    }
}
