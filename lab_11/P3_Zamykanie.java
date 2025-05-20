import javax.swing.*;
import java.util.*;
import java.awt.Color;
import java.awt.event.*;

class P3_Zamykanie {
  public static void main(String[] args) throws InterruptedException {
    JFrame ramka = new JFrame();
    ramka.setSize(300,200);
    ramka.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    final int[] i = {0};

    ramka.addWindowListener(
      // Metoda WindowListenera (tylko ta jedna):
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            if(i[0] == 1){
                ramka.getContentPane().setBackground(Color.BLUE);
            }
            if(i[0] == 2){
                ramka.getContentPane().setBackground(Color.GREEN);
            }
            if(i[0] > 2 ){
                ramka.getContentPane().setBackground(Color.WHITE);
                i[0] = 0;
            }
            i[0]++;
        }
      }
    );

    ramka.setVisible(true);

    int n=0;
    do {
      System.out.printf("  PROGRAM DZIALA: %4d\n", n);
      n++;
      Thread.sleep(500);
    } while (true);

  }
}
