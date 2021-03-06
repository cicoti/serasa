package br.com.teste.cartao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.apache.commons.codec.language.bm.Rule.RPattern;

public class GiraFlecha3 extends Frame {

	private static int POINT_SIZE = 10;

	public static void main(String[] args) {

		BufferedImage bufferImagemSerasa;
		BufferedImage bufferImagemFlecha;


		try {
			bufferImagemSerasa = ImageIO.read(new File("d:\\silvio\\serasa_score_imagem.png"));
			bufferImagemFlecha = ImageIO.read(new File("d:\\silvio\\flecha_peq.gif"));

			Graphics2D graphics = bufferImagemSerasa.createGraphics();

			int posicaoCentroX = 239;
			 int posicaoCentroY = 250;
				int raio = 115;

			drawCircleInImage(graphics, posicaoCentroX, posicaoCentroY, raio, 100, Color.RED);

			//double i = 52.50;//0 // 180
			//double i = 74.50;//500 // 90
			//double i = 96.50; // 1000 // 0

			int score = 175;

			int i = (int) (180 - (score * 0.18));

			//double i = (double) (50 + (score * 0.054));

			double angulo = 180 - (score * 0.18);



			bufferImagemFlecha = new GiraFlecha3().rotateImageByDegrees(bufferImagemFlecha, i+1);



			double xd = Math.cos(1 * Math.PI / 180 * -i);
			double yd = Math.sin(1 * Math.PI / 180 * -i);

			int x = (int) (xd * raio + posicaoCentroX);
			int y = (int) (yd * raio + posicaoCentroY);

			System.out.println("i: " + i + " X: " + x + " - " + "Y: " + y);

			try {
			//imagem.setRGB(x, y, cor.getRGB());
				int xa = 5;
				int ya = 5;
				graphics.setColor(Color.BLACK);
				graphics.fillOval(x, y, xa, ya);


			}catch(Exception e) {

			}

				System.out.println("i: " + i + " X: " + x + " - " + "Y: " + y);


			graphics.drawImage(bufferImagemFlecha, x-15, y, null); // H x V
			graphics.dispose();
		    //ImageIO.write(bufferImagemSerasa, "PNG", new File("d:\\silvio\\imagemSobreposta.png"));
			apresentarImagem(bufferImagemSerasa);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	 public BufferedImage rotateImageByDegrees(BufferedImage image, double angle) {

         double rads = Math.toRadians(angle);

         double sin = Math.abs(Math.sin(rads));
         double cos = Math.abs(Math.cos(rads));
         int w = image.getWidth();
         int h = image.getHeight();
         int newWidth = (int) Math.floor(w * cos + h * sin);
         int newHeight = (int) Math.floor(h * cos + w * sin);

         BufferedImage rotated = new BufferedImage( newWidth,newHeight, BufferedImage.TYPE_INT_ARGB);
         Graphics2D g2d = rotated.createGraphics();
         AffineTransform at = new AffineTransform();
         at.translate((newWidth - w) / 2, (newHeight - h) / 2);

         int x = w / 2;
         int y = h / 2;

         at.rotate(-rads, x, y);
         g2d.setTransform(at);
         g2d.drawImage(image, 0, 0, this);
         g2d.dispose();

         return rotated;




     }

	public static void drawCircleInImage(Graphics2D imagem, int posicaoCentroX, int posicaoCentroY, int raio,
			int quantidadeDePontos, Color cor) {

		//int i = 25;
		//int i = 60;
	    //posicaoCentroY = 270;
		//posicaoCentroX = 260;
		//raio = 150;

		//for (double i = 55; i < 110; i += 0.5) {
		for (int i = 180; i >= 0; i --) {

			//final double angle = Math.toRadians(((double) i / 100) * 360d);

			//double angle = Math.toRadians(((double)i/100)*360d);

			double xd = Math.cos(1 * Math.PI / 180 * -i);
			double yd = Math.sin(1 * Math.PI / 180 * -i);

			int x = (int) (xd * raio + posicaoCentroX);
			int y = (int) (yd * raio + posicaoCentroY);

			System.out.println("i: " + i + " X: " + x + " - " + "Y: " + y);

			try {
			//imagem.setRGB(x, y, cor.getRGB());
				int xa = 3;
				int ya = 3;
				imagem.setColor(cor);
				imagem.fillOval(x, y, xa, ya);


			}catch(Exception e) {

			}
		}
	}

	private static void apresentarImagem(BufferedImage imagem) {
		JFrame janela = new JFrame("Posicionar Seta");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(800, 800);
		janela.getContentPane().setBackground(Color.WHITE);
		Container container = janela.getContentPane();
		ImageIcon icone = new ImageIcon(imagem);
		JLabel labelImagem = new JLabel(icone, SwingConstants.CENTER);
		container.add(labelImagem, BorderLayout.CENTER);
		janela.setVisible(true);
	}
}