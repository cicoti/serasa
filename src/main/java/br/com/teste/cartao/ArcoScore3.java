package br.com.teste.cartao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.lowagie.text.Font;

public class ArcoScore3 {

    private static int WINDOW_WIDTH  = 800;
    private static int WINDOW_HEIGHT = 550;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Arco> listaArcoExterno = new ArrayList<Arco>();
	    List<Arco> listaArcoInterno = new ArrayList<Arco>();

		BufferedImage bufferImagem = new BufferedImage( WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB );

		Graphics2D graphics = bufferImagem.createGraphics();
		graphics.setPaint ( Color.WHITE);//new Color ( 255, 255, 255 ) );
		graphics.fillRect ( 0, 0, bufferImagem.getWidth(), bufferImagem.getHeight() );

		listaArcoExterno = drawCircleInImage(graphics, 400, 400, 300);

		listaArcoInterno = drawCircleInImage(graphics, 400, 400, 200);
/*
		int[] polygonXs = { -20, 0, +20, 0};
		int[] polygonYs = { 20, 10, 20, -20};
		Shape shape = new Polygon(polygonXs, polygonYs, polygonXs.length);
*/
		String paletaCor[] = {
				"#E3F2FD",
				"#BBDEFB",
				"#90CAF9",
				"#64B5F6",
				"#42A5F5",
				"#2196F3",
				"#1E88E5",
				"#1976D2",
				"#1565C0",
				"#0D47A1"
		};

		int[] polygonXs;
		int[] polygonYs;

		int j = 0;
		for(int i = 0 ; i<60 ; i+=6) {

			polygonXs = new int[] {
					listaArcoExterno.get(i).getX(),
					listaArcoExterno.get(i+1).getX(),
					listaArcoExterno.get(i+2).getX(),
					listaArcoExterno.get(i+3).getX(),
					listaArcoExterno.get(i+4).getX(),
					listaArcoExterno.get(i+5).getX(),
					listaArcoInterno.get(i+5).getX(),
					listaArcoInterno.get(i+4).getX(),
					listaArcoInterno.get(i+3).getX(),
					listaArcoInterno.get(i+2).getX(),
					listaArcoInterno.get(i+1).getX(),
					listaArcoInterno.get(i).getX(),


			};
			polygonYs = new int[] {
					listaArcoExterno.get(i).getY(),
					listaArcoExterno.get(i+1).getY(),
					listaArcoExterno.get(i+2).getY(),
					listaArcoExterno.get(i+3).getY(),
					listaArcoExterno.get(i+4).getY(),
					listaArcoExterno.get(i+5).getY(),
					listaArcoInterno.get(i+5).getY(),
					listaArcoInterno.get(i+4).getY(),
					listaArcoInterno.get(i+3).getY(),
					listaArcoInterno.get(i+2).getY(),
					listaArcoInterno.get(i+1).getY(),
					listaArcoInterno.get(i).getY(),
			};

			Shape shape = new Polygon(polygonXs, polygonYs, polygonXs.length);
			graphics.draw(shape);



			/*
			Color color1 = Color.decode("#E3F2FD");
	        Color color2 = Color.decode("#0D47A1");

	        	j = j + 1;

	            float ratio = (float) j / (float) 10;
	            int red = (int) (color2.getRed() * ratio + color1.getRed() * (1 - ratio));
	            int green = (int) (color2.getGreen() * ratio + color1.getGreen() * (1 - ratio));
	            int blue = (int) (color2.getBlue() * ratio + color1.getBlue() * (1 - ratio));
	            Color stepColor = new Color(red, green, blue);
	        */

	        graphics.setColor(Color.decode(paletaCor[j++]));

			graphics.fill(shape);


		}

		AffineTransform tx = new AffineTransform();
		tx.rotate(0.028, bufferImagem.getWidth() / 2, bufferImagem.getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(tx,
		    AffineTransformOp.TYPE_BILINEAR);
		bufferImagem = op.filter(bufferImagem, null);

		graphics = bufferImagem.createGraphics();


		   java.awt.Font fontSerasaPontuacao = new java.awt.Font("Impact", Font.HELVETICA, 35);
		   graphics.setColor(Color.BLACK);
		   graphics.setFont(fontSerasaPontuacao);

		   graphics.drawString("0", 75, 384);
		   graphics.drawString("100", 55, 307);
		   graphics.drawString("200", 95, 223);
		   graphics.drawString("300", 162, 157);
		   graphics.drawString("400", 250, 114);
		   graphics.drawString("500", 365, 90);
		   graphics.drawString("600", 492, 114);
		   graphics.drawString("700", 576, 157);
		   graphics.drawString("800", 642, 223);
		   graphics.drawString("900", 685, 307);
		   graphics.drawString("1000", 699, 384);




		   int score = 900;





	       // Desenho da flecha
	       int[] flechaXs = { -20, 0, +20, 0};
		   int[] flechaYs = { 20, 10, 20, -20};
		   Shape shape = new Polygon(flechaXs, flechaYs, flechaXs.length);

		   double indice = 0.1789;
		   int posicaoCentroX = 400;
		   int posicaoCentroY = 400;
		   int raio = 200;

		   String textScore = String.valueOf(score);
		   java.awt.Font fontSerasaScore = new java.awt.Font("Helvetica", Font.HELVETICA, 54);
			  graphics.setColor(Color.decode("#1394D6"));
			  graphics.setFont(fontSerasaScore);
			  int posicao = (bufferImagem.getWidth()/2) - ((textScore.length()*36)/2);
			  graphics.drawString(textScore, posicao, posicaoCentroY);

		   int graus = (int) (180 - (score * indice)); // quantos graus por indice
			  double angulo = ((score/1000f) * 180) + 270; // angulo que a flecha será direcionada
			  double xd = Math.cos(1 * Math.PI / 180 * -graus); // coordenada onde a fecha ficará no arco.
			  double yd = Math.sin(1 * Math.PI / 180 * -graus); // coordenada onde a fecha ficará no arco.
			  int x = (int) (xd * raio + posicaoCentroX); // correcao da posicao da flexa dentro da imagem.
			  int y = (int) (yd * raio + posicaoCentroY); // correcao da posicao da flexa dentro da imagem.

			  AffineTransform saveTransform = graphics.getTransform();
			  AffineTransform identity = new AffineTransform();
			  graphics.setTransform(identity);
			  graphics.setColor(Color.BLACK);
			  graphics.translate(x, y);
			  graphics.rotate(Math.toRadians(angulo));
			  graphics.scale(0.95, 0.95);
			  graphics.fill(shape);
			  graphics.dispose();



		apresentarImagem(bufferImagem);

	}

	public static List<Arco> drawCircleInImage(Graphics2D imagem, int posicaoCentroX, int posicaoCentroY, int raio) {

		List<Arco> listArco = new ArrayList<Arco>();
		//int i = 25;
		//int i = 60;
	    //posicaoCentroY = 270;
		//posicaoCentroX = 260;
		//raio = 150;

		//for (double i = 55; i < 110; i += 0.5) {
		int j = 0;

		for (int i = 180; i >= 3; i -= 3) {

			//final double angle = Math.toRadians(((double) i / 100) * 360d);

			//double angle = Math.toRadians(((double)i/100)*360d);

			double xd = Math.cos(1 * Math.PI / 180 * -i);
			double yd = Math.sin(1 * Math.PI / 180 * -i);

			int x = (int) (xd * raio + posicaoCentroX);
			int y = (int) (yd * raio + posicaoCentroY);

			Arco arco = new Arco();
			arco.setX(x);
			arco.setY(y);

			listArco.add(arco);
/*
			System.out.println("j: " + j++ + " i: " + i + " X: " + x + " - " + "Y: " + y);


				try {
				//imagem.setRGB(x, y, cor.getRGB());
					int xa = 5;
					int ya = 5;
					imagem.setColor(Color.RED);
					imagem.fillOval(x, y, xa, ya);


				}catch(Exception e) {

				}
*/

		}

		return listArco;
	}

	private static void apresentarImagem(BufferedImage bufferImagem) {

		JFrame janela = new JFrame("Posicionar Seta");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(800, 550);
		janela.getContentPane().setBackground(Color.WHITE);
		Container container = janela.getContentPane();

		ImageIcon icone = new ImageIcon(bufferImagem);

		JLabel labelImagem = new JLabel(icone, SwingConstants.CENTER);
		container.add(labelImagem, BorderLayout.CENTER);
		janela.setVisible(true);
	}

}
