import java.util.*;


public class Zad_7{
    public static double[] wczytaj_tablice(){
        Scanner sc = new Scanner(System.in);

        int rozmiar;

        System.out.print("\nPodaj ilość elementów: ");
        rozmiar = sc.nextInt();

        double[] tab = new double[rozmiar];

        for(int i = 0; i < rozmiar; i++){
            System.out.print("\nPodaj " + i + " element: ");
            tab[i] = sc.nextDouble();
        }

        return tab;
    }
    public static double srednia_arytmetyczna(double[] liczby){
        double suma = 0.0;

        for(double v : liczby){
            suma += v;
        }

        return suma / liczby.length;
    }

    public static double srednia_geometryczna(double[] liczby){
        double iloczyn = 1;

        for(double v : liczby){
            iloczyn *= v;
        }
        
        return Math.pow(iloczyn, 1.0/liczby.length);
    }

    public static double srednia_harmoniczna(double[] liczby){
        double suma = 0.0;

        for(double v  : liczby){
            suma += 1.0 / v;
        }

        return liczby.length / suma;
    }
    public static void main(String[] args){

        double[] tab = wczytaj_tablice();
        
        System.out.println();
        System.out.println();
        System.out.println("Liczby: " + Arrays.toString(tab));
        System.out.println("------------------------------------------------------------");
        System.out.println("Średnia arytmetyczna liczb: " + srednia_arytmetyczna(tab));
        System.out.println("Średnia geometryczna liczb: " + srednia_geometryczna(tab));
        System.out.println("Średnia harmoniczna liczb: " + srednia_harmoniczna(tab));


    }
}