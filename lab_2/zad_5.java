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
        String tekst = "KOT KOT .";
        System.out.println("\nOryginalna wiadomość: " + tekst);

        try {
            BigInteger zaszyfrowana = rsa.szyfruj(tekst);
            System.out.println("\nZaszyfrowana wiadomość: " + zaszyfrowana);
            
            String odszyfrowana = rsa.deszyfruj(zaszyfrowana);
            System.out.println("\nOdszyfrowana wiadomość: " + odszyfrowana);
        } catch (IllegalArgumentException ex) {
            System.out.println("\nBłąd szyfrowania: " + ex.getMessage());
        }


        System.out.println("\n \n \n");
        BigInteger wklejonaLiczba = new BigInteger(
            "8113215864280423455698701528975575613562080825585299662542186714182157159361526533595131145867017993804983084833045110011537083213227589801601166085822256904967362301031361916870343647406016270389319876674692261555697375581744474088287382695653082755881530775516019073134722529802705387332923129738215293214652454297186540999983667864402494407458537394747447087609168478410585340688245765618030306797185625034634481943047137682337139947060472794790263372745936091151912503566093747223450687250603237234940625514673832398643582662669613150563983896809018690408899200931427038873160212637221475239850559036602941048800"
        );
        
        try {
            String wynik = rsa.deszyfruj(wklejonaLiczba);
            System.out.println("\nOdszyfrowana wiadomość: " + wynik);
        } catch (IllegalArgumentException ex) {
            System.out.println("Błąd: " + ex.getMessage());
        }
    }
}
