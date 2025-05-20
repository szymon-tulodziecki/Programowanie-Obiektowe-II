import java.awt.*;
import javax.swing.*;


class Cienie {
  public static void main(String[] args) {
    JFrame ramka = new JFrame();
    ramka.setSize(256+10, 256+30);
    ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /*
    Originalne:
    ramka.add(new JComponent() {
      public void paintComponent(Graphics g) {
        for (int x=0; x<256; x++)
          for (int y=0; y<256; y++) {
            g.setColor(new Color(x,0,0));
            g.fillRect(x,y, 1,1);
          }
      }
    });

     ramka.add(new JComponent() {
  public void paintComponent(Graphics g){
      for(int x = 0; x < 256; x++){
          for(int y = 256; y > 0; y--){
              g.setColor(new Color(0, 0, x));
              g.fillRect(y, x, 1, 1);
          }
      }
  }
});

      ramka.add(new JComponent() {
        public void paintComponent(Graphics g){
            for(int x = 0; x < 256; x++){
                for(int y = 0; y < 256; y++){
                    g.setColor(new Color(x, y, 0));

                    g.setColor(new Color(x, y, 0));
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
    });
 */
ramka.add(new JComponent() {
  public void paintComponent(Graphics g){
    for (int x = 0; x < 256; x++) {
      for (int y = 0; y < 256; y++) {
        int gray = (int)(((x + y) / 2.0));
        if (gray > 255) gray = 255;
        g.setColor(new Color(gray, gray, gray));
        g.fillRect(x, y, 1, 1);
      }
    }
  }
});


    ramka.setVisible(true);
  }
}
