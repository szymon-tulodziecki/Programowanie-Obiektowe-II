package lab_2;

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
        String tekst = "SZYMON";
        BigInteger k = new BigInteger("200309");

        BigInteger zakodowana = koduj(tekst, k);

        dekoduj(zakodowana, k);
    }
}
