
import java.math.BigInteger;

public class zad_4 {

    public static BigInteger koduj(String tekst, BigInteger kod){
        int[] kody = new int[tekst.length()];
        
        for(int i = 0; i < tekst.length(); i++){
            kody[i] = (int)(tekst.charAt(i));
        }

        BigInteger zakodowana = BigInteger.ZERO;

        for(int i = 0; i < kody.length; i++){
            zakodowana = zakodowana.shiftLeft(8).or(BigInteger.valueOf(kody[i]));
        }

        zakodowana = zakodowana.multiply(kod);

        return zakodowana;
    }

    //-----------------------------------------------------------------------
    public static void dekoduj(BigInteger zakodowana, BigInteger klucz){
        BigInteger dekodowana = zakodowana.divide(klucz);

        StringBuilder tekst = new StringBuilder();

        while(dekodowana.compareTo(BigInteger.ZERO) > 0){
            char znak = (char)(dekodowana.and(BigInteger.valueOf(255)).intValue());
            tekst.insert(0, znak);
            dekodowana = dekodowana.shiftRight(8);
        }

        System.out.println(tekst);
    }

    //-----------------------------------------------------------------------
    public static void main(String[] args) {
        String tekst = "15529762349885268897239528117969073938557777338742947701227238313051428723249234645262789000891527899592699665843056228183831919424611657814566568296515035805967417989565921145291799627553174504602874764198467495203134587480189975203768989296696785151688640976935234041447393360392605748638682203873031923672049890700091399811965361335485220411011465349188460508444544836091931518879674086678095197711434999117975755690444098511186757122074002823776400093798603405395077138927112896881670932936895965672198408990448678626506571099336427988342531602537476253246105685608897836869995693925012411737922378635613170970652";
        BigInteger k = new BigInteger("22452436410359781118622482541579205874168549008972663100878973816524541026044118066857112");

        BigInteger zakodowana = koduj(tekst, k);

        dekoduj(zakodowana, k);
    }
}
