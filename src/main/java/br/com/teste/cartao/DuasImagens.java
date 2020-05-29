package br.com.teste.cartao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DuasImagens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Image img01 = null;
		Image img02 = null;

		try {

			img02 = ImageHelper.readImage(Paths.get("D:\\silvio\\phone.png"),0);
			img01 = ImageHelper.readImage(Paths.get("D:\\silvio\\fotoAgente.png"),0);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		int widthImg01 = img01.getWidth(null);
		int widthImg02 = img02.getWidth(null);
		int heightImg01 = img01.getHeight(null);

		int totalwidth = widthImg01 + widthImg02;
		int totalHeigh = heightImg01;

		BufferedImage bfi = new BufferedImage(totalwidth, totalHeigh, BufferedImage.TYPE_INT_ARGB);

                //Ajustes de Imagem
		Graphics g = bfi.getGraphics();

		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// Preenche o plano de fundo da pagina com branco
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, totalwidth,totalHeigh);

		g.drawImage(img01, 0, 0, null);
		g.drawImage(img02, widthImg01, 0, null);

		//Exibe o resultado final
		JFrame fr = new JFrame("Merge de imagens");
		fr.getContentPane().setLayout(new BorderLayout());
		JLabel jl = new JLabel(new ImageIcon(bfi));
		fr.getContentPane().add(jl,BorderLayout.CENTER);
		fr.pack();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setVisible(true);

	}

}
