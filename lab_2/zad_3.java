package lab_2;
public class zad_3 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("\n Argumentem ma byc jedna liczba szesnastkowa!\n");
        } else {
            try {
                long decimalValue = Long.parseLong(args[0], 16);
                System.out.println(" " + decimalValue);
            } catch (NumberFormatException e) {
                System.out.println("\n Niepoprawna liczba szesnastkowa!\n");
            }
        }
    }
}

    
