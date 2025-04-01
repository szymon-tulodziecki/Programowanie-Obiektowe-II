import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class RSA {
    private final BigInteger p;
    private final BigInteger q;
    public final BigInteger n;
    public final BigInteger e;
    private final BigInteger d;

    public RSA(BigInteger p, BigInteger q, BigInteger e) {
        if (!p.isProbablePrime(100) || !q.isProbablePrime(100)) {
            throw new IllegalArgumentException("Oba parametry p i q muszą być liczbami pierwszymi");
        }

        this.p = p;
        this.q = q;
        this.n = p.multiply(q);
        this.e = e;

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        
        if (!e.gcd(phi).equals(BigInteger.ONE)) {
            throw new IllegalArgumentException("e musi być względnie pierwsze z wartością φ(n)");
        }

        this.d = e.modInverse(phi);
        System.out.println("___________________________________________________________________________________________\n");
        System.out.println("Klucz prywatny: " + d);
        System.out.println("___________________________________________________________________________________________\n");
    }

    public BigInteger szyfruj(String tekst) {
        byte[] tekstBajty = tekst.getBytes(StandardCharsets.UTF_8);
        int maxDlugosc = (n.bitLength() + 7) / 8 - 1;

        if (tekstBajty.length > maxDlugosc) {
            throw new IllegalArgumentException("Tekst zbyt długi. Maksymalna długość: " + maxDlugosc + " bajtów");
        }

        BigInteger tekstInt = new BigInteger(1, tekstBajty);
        return tekstInt.modPow(e, n);
    }

    public String deszyfruj(BigInteger liczba) {
        BigInteger odszyfrowanyInt = liczba.modPow(d, n);
        byte[] odszyfrowaneBajty = odszyfrowanyInt.toByteArray();

        int nonZeroIndex = 0;
        while (nonZeroIndex < odszyfrowaneBajty.length && odszyfrowaneBajty[nonZeroIndex] == 0) {
            nonZeroIndex++;
        }
        
        byte[] trimmedBytes = Arrays.copyOfRange(odszyfrowaneBajty, nonZeroIndex, odszyfrowaneBajty.length);
        
        try {
            return new String(trimmedBytes, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Błąd dekodowania", ex);
        }
    }
}


public class zad_5 {
public static void main(String[] args) {
        BigInteger P = new BigInteger(
            "176897372882635987275889773432181556648728946202747932157927040748686463885209870085463625211254276129699599518299969391191355317422076373607867367861862529758264338800409693234977972196526541496518986872840579204021277140838456934687842315202989813674484166713327370221517985737028962029391688753639797535757"
        );
        
        BigInteger Q = new BigInteger(
            "157076046644720725538647759301613898052353238593474669201498505034149665144409972183337363974131801268070252067376597552875950883908684415804694571497446806800533706474362729761457161180674990196308220409468623813309786096319493066102924556234943113909399960279404008353089220302399954039930110163429863335743"
        );
        
        BigInteger E = new BigInteger("65537");

        RSA rsa = new RSA(P, Q, E);

        System.out.println("Klucz publiczny: (" + rsa.e + ", " + rsa.n + ")");
        System.out.println("___________________________________________________________________________________________");

        String tekst = "Kto miłości nie zna, ten żyje szczęśliwy, I noc ma spokojną, i dzień nietęskliwy.";
        System.out.println("\nOryginalna wiadomość: " + tekst);

        try {
            BigInteger zaszyfrowana = rsa.szyfruj(tekst);
            System.out.println("\nZaszyfrowana wiadomość: " + zaszyfrowana);
            
            String odszyfrowana = rsa.deszyfruj(zaszyfrowana);
            System.out.println("\nOdszyfrowana wiadomość: " + odszyfrowana);
        } catch (IllegalArgumentException ex) {
            System.out.println("\nBłąd szyfrowania: " + ex.getMessage());
        }
        System.out.println("___________________________________________________________________________________________");
        
        /* 
        System.out.println("\n \n \n");
        BigInteger wiadomosc = new BigInteger(
            "22452436410359781118622482541579205874168549008972663100878973816524541026044118066857112616108618728419493960091163235331377220479790157131646664306075875953153320354165628096169200498728857465324168607158257303563615927093796082160860441259948407435750548075511949342794555881818278934243250811934156109353174410122874548686035182069158603057344321273159712618278940865925046119743168768694554825781308460514479970689557042460900150127043249458519776797557827947521297133843415073456736324198020524445359718958723155351221285258418920910343869921269703654887047901755558474040055177825481024794990730186267702959123"
        );
        
        try {
            String wynik = rsa.deszyfruj(wiadomosc);
            System.out.println("\nOdszyfrowana wiadomość: " + wynik);
        } catch (IllegalArgumentException ex) {
            System.out.println("Błąd: " + ex.getMessage());
        }
        */
    }
}