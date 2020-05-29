package br.com.teste.cartao;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WaterMark {
    public static void main(String[] args) throws IOException {
        BufferedImage im = ImageIO.read(new File("d:\\silvio\\fotoAgente.png"));

        im =  redimencionarImagem(im, im.getType(), 180, 180);

       // BufferedImage im2 = ImageIO.read(new File("d:\\silvio\\phone.png"));

        Graphics2D g2 = im.createGraphics();
        applyQualityRenderingHints(g2);
        g2.setPaint(Color.WHITE);
        g2.setStroke(new BasicStroke(20.0f));
        g2.draw(new Ellipse2D.Double(0, 0, 180, 180));
        g2.dispose();



        //Graphics2D g = im.createGraphics();
       // g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
       // g.drawImage(im2, (im.getWidth()-im2.getWidth())/2, (im.getHeight()-im2.getHeight())/2, null);
       // g.dispose();

        im = arredondarImagem(im);

        display(im);
        ImageIO.write(im, "jpeg", new File("sample_output.jpeg"));
    }

    public static BufferedImage redimencionarImagem(BufferedImage originalImage, int type,
            Integer img_width, Integer img_height)	{
		BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, img_width, img_height, null);
		g.dispose();

		return resizedImage;
    }

    public static BufferedImage arredondarImagem(BufferedImage master) throws IOException {



	 	int type = master.getType() == 0? BufferedImage.TYPE_INT_ARGB
                 : master.getType();

	 	//BufferedImage resizeImage = redimencionarImagem(master, type, 180, 180);

	 	int diameter = Math.min(master.getWidth(), master.getHeight());

	    BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = mask.createGraphics();
	    applyQualityRenderingHints(g2d);
	    g2d.fillOval(0, 0, diameter - 1, diameter - 1);
	    g2d.dispose();

	    BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	    g2d = masked.createGraphics();

	    //applyQualityRenderingHints(g2d);
	    int x = (diameter - master.getWidth()) / 2;
	    int y = (diameter - master.getHeight()) / 2;

	    g2d.drawImage(master, x, y, null);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));

	    g2d.drawImage(mask, 0, 0, null);

	    g2d.dispose();

	    return masked;

}


    public static void display(BufferedImage image) {
        JFrame f = new JFrame("WaterMark");
        f.setPreferredSize(new Dimension(400,500));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new JLabel(new ImageIcon(image)));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    protected static void applyQualityRenderingHints(Graphics2D g2d) {

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    }
}