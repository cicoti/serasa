package br.com.teste.cartao;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class ExemploImagemRedonda {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ExemploImagemRedonda programa = new ExemploImagemRedonda();
            programa.executar();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static BufferedImage arredondar(BufferedImage imagemRetangular) {
        int largura = imagemRetangular.getWidth();
        int altura = imagemRetangular.getHeight();
        int raio = largura / (double) altura > 0 ? altura : largura;
        BufferedImage imagemRedonda = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = imagemRedonda.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setClip(new Area(new Ellipse2D.Double(0, 0, raio, raio)));
        graphics.drawImage(imagemRetangular, 0, 0, null);
        graphics.dispose();
        return imagemRedonda;
    }

    public void executar() throws IOException {
    	
    	InputStream arquivo = new BufferedInputStream(new FileInputStream("d:\\silvio\\foto.png"));
        BufferedImage imagemRetangular = ImageIO.read(arquivo);
        BufferedImage imagemRedonda = arredondar(imagemRetangular);
        apresentarImagem(imagemRedonda);
    }

    private void apresentarImagem(BufferedImage imagem) {
        JFrame janela = new JFrame("Exemplo Imagem Redonda");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(800, 800);
        Container container = janela.getContentPane();
        ImageIcon icone = new ImageIcon(imagem);
        JLabel labelImagem = new JLabel(icone, SwingConstants.CENTER);
        container.add(labelImagem, BorderLayout.CENTER);
        janela.setVisible(true);
    }
}