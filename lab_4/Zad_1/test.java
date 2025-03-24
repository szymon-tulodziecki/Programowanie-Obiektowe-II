package Zad_1;

public class test {
    public static void main(String[] args) {
        Pracownik p = new Pracownik("Kowalski", "Jan", Wyksztalcenie.WYZ_EKON, 5000);

        System.out.println(p);

        Szef s = new Szef(p, 10000);
        System.out.println(s);
    }
}
