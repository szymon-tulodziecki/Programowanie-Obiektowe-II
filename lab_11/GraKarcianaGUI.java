import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

enum Figura { DZIEWIATKA, DZIESIATKA, WALET, DAMA, KROL, AS }
enum Kolor { PIK, KIER, KARO, TREFL }

class Karta {
    private Figura figura;
    private Kolor kolor;
    private int punkty;

    Karta(Figura figura, Kolor kolor) {
        this.figura = figura;
        this.kolor = kolor;
        switch (figura) {
            case DZIEWIATKA: punkty = 0; break;
            case DZIESIATKA: punkty = 10; break;
            case WALET: punkty = 2; break;
            case DAMA: punkty = 3; break;
            case KROL: punkty = 4; break;
            case AS: punkty = 11; break;
        }
    }

    Figura figura() { return figura; }
    Kolor kolor() { return kolor; }
    int punkty() { return punkty; }

    public String toString() {
        String ozn = "";
        switch (figura) {
            case DZIEWIATKA: ozn = "9"; break;
            case DZIESIATKA: ozn = "10"; break;
            case WALET: ozn = "J"; break;
            case DAMA: ozn = "Q"; break;
            case KROL: ozn = "K"; break;
            case AS: ozn = "A"; break;
        }
        switch (kolor) {
            case PIK: ozn += "♠"; break;
            case KIER: ozn += "♥"; break;
            case KARO: ozn += "♦"; break;
            case TREFL: ozn += "♣"; break;
        }
        return ozn;
    }
}

class Talia {
    private ArrayList<Karta> talia;

    Talia() {
        talia = new ArrayList<>(24);
        for (Figura figura : Figura.values())
            for (Kolor kolor : Kolor.values())
                talia.add(new Karta(figura, kolor));
        Collections.shuffle(talia);
    }

    ArrayList<Karta> karty() { return talia; }
}

class KartaGUI extends JButton {
    private Karta karta;
    private boolean odkryta;

    public KartaGUI(Karta karta) {
        this.karta = karta;
        this.odkryta = false;
        setText("?");
        setFont(new Font("Arial", Font.BOLD, 28));
        setPreferredSize(new Dimension(80, 120));
        setFocusPainted(false);
        setMargin(new Insets(0,0,0,0));
        setBackground(Color.WHITE);
    }

    public void odkryj() {
        odkryta = true;
        setText(karta.toString());
        switch (karta.kolor()) {
            case KIER:
                setForeground(Color.RED);
                setBackground(new Color(255, 200, 200));
                break;
            case KARO:
                setForeground(Color.RED);
                setBackground(new Color(255, 230, 180));
                break;
            case PIK:
                setForeground(Color.BLACK);
                setBackground(Color.LIGHT_GRAY);
                break;
            case TREFL:
                setForeground(Color.BLACK);
                setBackground(new Color(200, 255, 200));
                break;
        }
        setEnabled(false);
    }

    public Karta getKarta() {
        return karta;
    }

    public boolean jestOdkryta() {
        return odkryta;
    }
}

public class GraKarcianaGUI extends JFrame {
    private int n;
    private int wymagane;
    private int sumaPunktow;
    private int liczbaOdkrytych;
    private JLabel instrukcjaLabel;
    private JPanel kartyPanel;
    private ArrayList<KartaGUI> kartyGUI;

    public GraKarcianaGUI(int n) {
        super("Gra Karciana");
        this.n = n;
        this.wymagane = 5 * n;
        this.sumaPunktow = 0;
        this.liczbaOdkrytych = 0;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel instrukcjaPanel = new JPanel();
        instrukcjaLabel = new JLabel(
            "Wybierz " + n + " kart. Suma punktów musi przekroczyć " + wymagane +
            ". Punktacja: 9=0, 10=10, J=2, Q=3, K=4, A=11."
        );
        instrukcjaPanel.add(instrukcjaLabel);
        add(instrukcjaPanel, BorderLayout.NORTH);

        kartyPanel = new JPanel();
        kartyPanel.setLayout(new GridLayout(4, 6, 8, 8));
        kartyPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        kartyGUI = new ArrayList<>();
        dodajNoweKarty();

        add(kartyPanel, BorderLayout.CENTER);

        JPanel podsumowaniePanel = new JPanel();
        JLabel podsumowanieLabel = new JLabel("Odkryto: 0/" + n + " kart. Suma punktów: 0");
        podsumowaniePanel.add(podsumowanieLabel);
        add(podsumowaniePanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void dodajNoweKarty() {
        kartyPanel.removeAll();
        kartyGUI.clear();
        Talia talia = new Talia();
        for (Karta karta : talia.karty()) {
            KartaGUI kartaGUI = new KartaGUI(karta);
            kartaGUI.addActionListener(e -> odkryjKarte(kartaGUI));
            kartyGUI.add(kartaGUI);
            kartyPanel.add(kartaGUI);
        }
        kartyPanel.revalidate();
        kartyPanel.repaint();
    }

    private void odkryjKarte(KartaGUI kartaGUI) {
        if (liczbaOdkrytych < n) {
            kartaGUI.odkryj();
            sumaPunktow += kartaGUI.getKarta().punkty();
            liczbaOdkrytych++;

            instrukcjaLabel.setText(
                "Odkryto: " + liczbaOdkrytych + "/" + n +
                " kart. Suma punktów: " + sumaPunktow +
                ". Wymagane: " + wymagane
            );

            if (liczbaOdkrytych == n) {
                javax.swing.Timer timer = new javax.swing.Timer(600, e -> pokazWynik());
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    private void resetujGre() {
        sumaPunktow = 0;
        liczbaOdkrytych = 0;
        instrukcjaLabel.setText(
            "Wybierz " + n + " kart. Suma punktów musi przekroczyć " + wymagane +
            ". Punktacja: 9=0, 10=10, J=2, Q=3, K=4, A=11."
        );
        dodajNoweKarty();
    }

    private void pokazWynik() {
        String tytul = sumaPunktow > wymagane ? "WYGRANA!" : "PRZEGRANA!";
        String wiadomosc = "Uzyskane punkty: " + sumaPunktow + "\n" +
                "Wymagane punkty: " + wymagane + "\n\n" +
                (sumaPunktow > wymagane ? "GRATULACJE! Wygrałeś!" : "Niestety, przegrałeś.");

        Object[] options = {"Zagraj ponownie", "Wyjdź"};
        int wybor = JOptionPane.showOptionDialog(
                this,
                wiadomosc,
                tytul,
                JOptionPane.YES_NO_OPTION,
                sumaPunktow > wymagane ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[0]
        );

        if (wybor == JOptionPane.YES_OPTION) {
            resetujGre();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        if (args.length > 0) {
            try {
                int temp = Integer.parseInt(args[0]);
                if (temp <= 0 || temp > 12) {
                    System.out.println("Liczba kart musi być z zakresu 1-12.");
                    return;
                }
                n = temp;
            } catch (NumberFormatException e) {
                System.out.println("Argument musi być liczbą całkowitą.");
                return;
            }
        }
        final int finalN = n;
        SwingUtilities.invokeLater(() -> new GraKarcianaGUI(finalN));
    }
}
