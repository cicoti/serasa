package br.com.teste.cartao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ArcoScore2 {

    private static int WINDOW_WIDTH  = 800;
    private static int WINDOW_HEIGHT = 800;



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Arco> listaArcoExterno = new ArrayList<Arco>();
	    List<Arco> listaArcoInterno = new ArrayList<Arco>();

		BufferedImage bufferImagem = new BufferedImage( WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB );

		Graphics2D graphics = bufferImagem.createGraphics();

		listaArcoExterno = drawCircleInImage(graphics, 400, 400, 300);

		listaArcoInterno = drawCircleInImage(graphics, 400, 400, 200);
/*
		int[] polygonXs = { -20, 0, +20, 0};
		int[] polygonYs = { 20, 10, 20, -20};
		Shape shape = new Polygon(polygonXs, polygonYs, polygonXs.length);
*/

		int[] polygonXs;
		int[] polygonYs;


		for(int i = 0 ; i<60 ; i++) {

			polygonXs = new int[] { listaArcoExterno.get(i).getX(), listaArcoInterno.get(i).getX(), listaArcoInterno.get(i+1).getX(), listaArcoExterno.get(i+1).getX()};
			polygonYs = new int[] { listaArcoExterno.get(i).getY(), listaArcoInterno.get(i).getY(), listaArcoInterno.get(i+1).getY(), listaArcoExterno.get(i+1).getY()};
			Shape shape = new Polygon(polygonXs, polygonYs, polygonXs.length);

			System.out.println(i%6);

			graphics.setColor(Color.RED);
			graphics.draw(shape);

		}

		//graphics.fill(shape);

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
		for (int i = 180; i >= 0; i -= 3) {

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

			System.out.println("i: " + i + " X: " + x + " - " + "Y: " + y);
/*
			try {
			//imagem.setRGB(x, y, cor.getRGB());
				int xa = 3;
				int ya = 3;
				imagem.setColor(Color.WHITE);
				imagem.fillOval(x, y, xa, ya);


			}catch(Exception e) {

			}
	*/
		}

		return listArco;
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
