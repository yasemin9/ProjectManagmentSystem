
import view.MainFrame;

public class Main {
    
    public static void main(String[] args) {
        // Uygulama başladığında ana çerçeveyi başlatıyoruz
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();  // MainFrame sınıfı GUI'yi başlatır
                frame.setVisible(true);  // Pencereyi görünür yap
            }
        });
    }
}
