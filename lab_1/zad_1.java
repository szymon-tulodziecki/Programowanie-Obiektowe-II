public class zad_1{
    public static void main(String[] args){
        System.out.println("Niepoprawnie: ");
        System.out.println("\n sin(30) == " + Math.sin(30) + "\n");
        System.out.println("Poprawnie: ");
        System.out.println("\n sin(30) == " + Math.sin(Math.toRadians(30)) + "\n");

    }
}