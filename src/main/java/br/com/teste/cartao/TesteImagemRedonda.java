package br.com.teste.cartao;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class TesteImagemRedonda {


		public static void main(String args[]) throws IOException {
			TesteImagemRedonda teste = new TesteImagemRedonda();
			teste.imagem();
		}

		public void imagem() throws IOException {

	 	BufferedImage master = ImageIO.read(new File("d:\\silvio\\fotoAgente.png"));

	 	Graphics2D g2 = master.createGraphics();
	 	applyQualityRenderingHints(g2);
        g2.setPaint(Color.WHITE);
        g2.setStroke(new BasicStroke(40.0f));
        g2.draw(new Ellipse2D.Double(0, 0, master.getWidth()-5, master.getHeight()-5));
        g2.dispose();


	 	int type = master.getType() == 0? BufferedImage.TYPE_INT_ARGB
                 : master.getType();

	 	BufferedImage resizeImage = resizeImage(master, type, 180, 180);




        int diameter = Math.min(resizeImage.getWidth(), resizeImage.getHeight());

	    BufferedImage mask = new BufferedImage(resizeImage.getWidth(), resizeImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = mask.createGraphics();
	    applyQualityRenderingHints(g2d);
	    g2d.fillOval(0, 0, diameter - 1, diameter - 1);
	    g2d.dispose();

	    BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	    g2d = masked.createGraphics();

	    applyQualityRenderingHints(g2d);
	    int x = (diameter - resizeImage.getWidth()) / 2;
	    int y = (diameter - resizeImage.getHeight()) / 2;

	    g2d.drawImage(resizeImage, x, y, null);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));

	    g2d.drawImage(mask, 0, 0, null);

	    g2d.dispose();



	    JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(masked)));

		}

		public void circulo () throws IOException {

			BufferedImage master = ImageIO.read(new File("d:\\silvio\\fotoAgente.png"));
			int diameter = Math.min(master.getWidth(), master.getHeight());
			BufferedImage maskcirculo = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2dc = maskcirculo.createGraphics();
		    applyQualityRenderingHints(g2dc);
		    g2dc.setPaint(Color.WHITE);
		    g2dc.fillOval(0,0,diameter + 10,diameter + 10);
		    g2dc.drawImage(maskcirculo, 0, 0, null);
		    g2dc.dispose();

		    JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(maskcirculo)));
		}

	    public static void applyQualityRenderingHints(Graphics2D g2d) {

	        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	    }

	    private static BufferedImage resizeImage(BufferedImage originalImage, int type,
                Integer img_width, Integer img_height)	{
			BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, img_width, img_height, null);
			g.dispose();

			return resizedImage;
	    }

}
