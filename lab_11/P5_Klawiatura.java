import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Nasluch implements KeyListener {
    private static int licznik = 0;
    private static ArrayList<JFrame> okna = new ArrayList<>();

    private static final int OKNA_W_RZEDZIE = 8;
    private static final int OKNO_SZER = 200;
    private static final int OKNO_WYS = 120;
    private static final int ODSTEP = 10;

    public void keyPressed(KeyEvent ev) {
        if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
            licznik++;

            int kolumna = (licznik - 1) % OKNA_W_RZEDZIE;
            int rzad = (licznik - 1) / OKNA_W_RZEDZIE;

            int x = 100 + kolumna * (OKNO_SZER + ODSTEP);
            int y = 100 + rzad * (OKNO_WYS + ODSTEP);

            JFrame noweOkno = new JFrame("Okno #" + licznik);
            Label l = new Label(Integer.toString(licznik));
            l.setFont(new Font("Dialog", Font.PLAIN, 20));
            noweOkno.setSize(OKNO_SZER, OKNO_WYS);
            noweOkno.setLocation(x, y);
            noweOkno.setVisible(true);

            okna.add(noweOkno);

            noweOkno.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    okna.remove(noweOkno);
                    if (okna.isEmpty()) {
                        System.exit(0);
                    }
                }
            });
        }
    }

    public void keyReleased(KeyEvent ev) {}
    public void keyTyped(KeyEvent ev) {}
}


public class P5_Klawiatura {
    public static void main(String[] args) {
        JFrame ramka = new JFrame("Główne okno – naciśnij ENTER");
        ramka.setSize(400, 200);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setVisible(true);
        ramka.addKeyListener(new Nasluch());
        ramka.setFocusable(true);
        ramka.requestFocusInWindow();
    }
}
