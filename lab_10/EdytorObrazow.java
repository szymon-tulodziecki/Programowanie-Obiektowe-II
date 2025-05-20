import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;

public class EdytorObrazow extends JFrame {

    BufferedImage obrazOryginalny;
    BufferedImage obrazOdbicie;
    BufferedImage obrazNegatyw;
    BufferedImage obrazSzary;

    JLabel[] polaObrazkow = new JLabel[4];
    String[] opisy = {"Oryginał", "Odbicie lustrzane", "Negatyw", "Odcienie szarości"};

    public EdytorObrazow() {
        super("Edytor obrazów");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelObrazki = new JPanel(new GridLayout(1, 4, 10, 10));
        for (int i = 0; i < 4; i++) {
            JPanel panel = new JPanel(new BorderLayout());
            polaObrazkow[i] = new JLabel("", JLabel.CENTER);
            panel.add(polaObrazkow[i], BorderLayout.CENTER);
            panel.add(new JLabel(opisy[i], JLabel.CENTER), BorderLayout.SOUTH);
            panelObrazki.add(panel);
        }

        JButton przyciskWczytaj = new JButton("Wczytaj obraz");
        przyciskWczytaj.setPreferredSize(new Dimension(300, 60));
        przyciskWczytaj.setFont(new Font("Arial", Font.BOLD, 22));
        przyciskWczytaj.addActionListener(e -> wczytajObraz());

        JPanel panelPrzycisk = new JPanel();
        panelPrzycisk.add(przyciskWczytaj);

        JButton przyciskZamknij = new JButton("X");
        przyciskZamknij.setFont(new Font("Arial", Font.BOLD, 24));
        przyciskZamknij.setForeground(Color.WHITE);
        przyciskZamknij.setBackground(Color.RED);
        przyciskZamknij.setFocusPainted(false);
        przyciskZamknij.setBorderPainted(false);
        przyciskZamknij.setOpaque(true);
        przyciskZamknij.setPreferredSize(new Dimension(60, 60));
        przyciskZamknij.addActionListener(e -> System.exit(0));

        JPanel panelZamknij = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelZamknij.setOpaque(false);
        panelZamknij.add(przyciskZamknij);

        add(panelZamknij, BorderLayout.NORTH);
        add(panelObrazki, BorderLayout.CENTER);
        add(panelPrzycisk, BorderLayout.SOUTH);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    private void wczytajObraz() {
        JFileChooser wyborPliku = new JFileChooser();
        wyborPliku.setAcceptAllFileFilterUsed(true);
        wyborPliku.setFileFilter(wyborPliku.getAcceptAllFileFilter());

        if (wyborPliku.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File plik = wyborPliku.getSelectedFile();
                obrazOryginalny = ImageIO.read(plik);
                if (obrazOryginalny == null) throw new Exception("Nieprawidłowy plik graficzny!");

                obrazOdbicie = zrobOdbicieLustrzane(obrazOryginalny);
                obrazNegatyw = zrobNegatyw(obrazOryginalny);
                obrazSzary = zrobSzary(obrazOryginalny);

                polaObrazkow[0].setIcon(new ImageIcon(obrazOryginalny));
                polaObrazkow[1].setIcon(new ImageIcon(obrazOdbicie));
                polaObrazkow[2].setIcon(new ImageIcon(obrazNegatyw));
                polaObrazkow[3].setIcon(new ImageIcon(obrazSzary));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Błąd podczas wczytywania pliku: " + ex.getMessage());
            }
        }
    }

    private BufferedImage zrobOdbicieLustrzane(BufferedImage obraz) {
        int szerokosc = obraz.getWidth();
        int wysokosc = obraz.getHeight();
        BufferedImage odbicie = new BufferedImage(szerokosc, wysokosc, obraz.getType());
        for (int y = 0; y < wysokosc; y++)
            for (int x = 0; x < szerokosc; x++)
                odbicie.setRGB(szerokosc - 1 - x, y, obraz.getRGB(x, y));
        return odbicie;
    }

    private BufferedImage zrobNegatyw(BufferedImage obraz) {
        int szerokosc = obraz.getWidth();
        int wysokosc = obraz.getHeight();
        BufferedImage negatyw = new BufferedImage(szerokosc, wysokosc, obraz.getType());
        for (int y = 0; y < wysokosc; y++)
            for (int x = 0; x < szerokosc; x++) {
                int rgb = obraz.getRGB(x, y);
                int a = (rgb >> 24) & 0xff;
                int r = 255 - ((rgb >> 16) & 0xff);
                int g = 255 - ((rgb >> 8) & 0xff);
                int b = 255 - (rgb & 0xff);
                negatyw.setRGB(x, y, (a << 24) | (r << 16) | (g << 8) | b);
            }
        return negatyw;
    }

    private BufferedImage zrobSzary(BufferedImage obraz) {
        int szerokosc = obraz.getWidth();
        int wysokosc = obraz.getHeight();
        BufferedImage szary = new BufferedImage(szerokosc, wysokosc, obraz.getType());
        for (int y = 0; y < wysokosc; y++)
            for (int x = 0; x < szerokosc; x++) {
                int rgb = obraz.getRGB(x, y);
                int a = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                int srednia = (r + g + b) / 3;
                szary.setRGB(x, y, (a << 24) | (srednia << 16) | (srednia << 8) | srednia);
            }
        return szary;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EdytorObrazow());
    }
}
