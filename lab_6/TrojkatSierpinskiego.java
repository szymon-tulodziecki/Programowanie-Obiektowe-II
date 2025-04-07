import java.io.IOException;
import java.util.Random;

public class TrojkatSierpinskiego {
    public static void main(String[] args) {
        int szer = 80;
        int wys = 40;
        int liczbaIteracji = 100;
        int punktow = 50;

        int[][] wierzcholki = { 
            {szer / 2, 0},
            {0, wys - 1},
            {szer - 1, wys - 1}
        };

        char[][] plaszczyzna = new char[wys][szer];
        
        for (int i = 0; i < wys; i++) {
            for (int j = 0; j < szer; j++) {
                plaszczyzna[i][j] = ' ';
            }
        }

        Random rnd = new Random();

        double x = rnd.nextDouble() * szer;
        double y = rnd.nextDouble() * wys;

        System.out.println("Generowanie Trójkąta Sierpińskiego:");

        for (int iteracja = 0; iteracja < liczbaIteracji; iteracja++) {
            for (int i = 0; i < punktow; i++) {
                int[] w = wierzcholki[rnd.nextInt(3)];
                x = (x + w[0]) / 2;
                y = (y + w[1]) / 2;
                plaszczyzna[(int) y][(int) x] = '*';
            }

            wyczyscKonsola();

            for (int i = 0; i < wys; i++) {
                for (int j = 0; j < szer; j++) {
                    System.out.print(plaszczyzna[i][j]);
                }
                System.out.println();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("Błąd podczas opóźnienia: " + e.getMessage());
            }
        }

        System.out.println("Generowanie zakończone.");
    }

    private static void wyczyscKonsola() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("Nie udało się wyczyścić konsoli: " + e.getMessage());
        }
    }
}
