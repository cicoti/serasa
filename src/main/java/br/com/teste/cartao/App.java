package br.com.teste.cartao;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class App extends JFrame {
    private static int WINDOW_WIDTH  = 400;
    private static int WINDOW_HEIGHT = 400;

    @Override public void paint(Graphics g) {
        super.paint(g);

        BufferedImage imagem = new BufferedImage( WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB );

        drawCircleInImage(imagem, 200, 200, 100, 120, Color.WHITE);

        g.drawImage(imagem, 0, 0, null);
    }

    public void drawCircleInImage(BufferedImage imagem, int posicaoCentroX, int posicaoCentroY,
                           int raio, int quantidadeDePontos, Color cor) {

        double distanciaEntrePontos = 2 * Math.PI / quantidadeDePontos;

        for (int i = 0; i < quantidadeDePontos; i++) {
            double cos = Math.cos(i * distanciaEntrePontos);
            double sin = Math.sin(i * distanciaEntrePontos);

            int x = (int) ( cos * raio + posicaoCentroX );
            int y = (int) ( sin * raio + posicaoCentroY );

            imagem.setRGB(x, y, cor.getRGB());
        }
    }

    public static void main(String... args) {
        JFrame frame = new App();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}