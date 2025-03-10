package lab_2;

import java.math.BigInteger;

public class zad_2 {
    static class IsPrime {
        void sprawdz(BigInteger num) {
            boolean isPrime = true;
            for (BigInteger i = BigInteger.valueOf(2); i.compareTo(num) < 0; i = i.add(BigInteger.ONE)) {
                if (num.mod(i).equals(BigInteger.ZERO)) {
                    System.out.println("Nie pierwsza!");
                    isPrime = false;
                    break;
                }
            }
            if (isPrime && num.compareTo(BigInteger.ONE) > 0) {
                System.out.println("Pierwsza");
            } else if (num.equals(BigInteger.ONE)) {
                System.out.println("Jedynka nie jest ani pierwsza, ani złożona.");
            }
        }
    }

    public static void main(String[] args) {
        BigInteger a = new BigInteger("123456787654321123456787654321");
        IsPrime ip = new IsPrime();
        ip.sprawdz(a);
    }
}
