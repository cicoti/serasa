package br.com.teste.cartao;
 
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfWriter;
 

public class MultipleImagesBack {
    public static final String[] IMAGES = {
        "d:\\silvio\\cartaoAgente.png",
        "d:\\silvio\\fotoAgente.png",
        "d:\\silvio\\qrCode.png",
        "d:\\silvio\\phone.png",
        "d:\\silvio\\email.png"
    };
    public static final String DEST = "d:\\silvio\\cartaoAgente.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new MultipleImagesBack().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Image img = Image.getInstance(IMAGES[0]);
        Document document = new Document(img);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        
        document.open();

        ColumnText ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(180, 760, 600, 200);
        Font font = new Font(Font.HELVETICA,32,Font.BOLD,Color.BLACK);
        ct.setText(new Phrase("Silvio Cicoti", font));
        ct.go();
        
        ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(180, 720, 600, 200);
        font = new Font(Font.HELVETICA,22,Font.ITALIC,Color.BLACK);
        ct.setText(new Phrase("Agente", font));
        ct.go();
        
        ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(180, 625, 600, 200);
        font = new Font(Font.HELVETICA,28,Font.NORMAL,Color.BLACK);
        ct.setText(new Phrase("(11) 97334-8842", font));
        ct.go();
        
        ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(180, 545, 600, 200);
        font = new Font(Font.HELVETICA,28,Font.NORMAL,Color.BLACK);
        ct.setText(new Phrase("silvio.cicoti", font));
        ct.go();
        
        ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(180, 510, 600, 200);
        font = new Font(Font.HELVETICA,28,Font.NORMAL,Color.BLACK);
        ct.setText(new Phrase("@comprealugueagora.com.br", font));
        ct.go();
        
        ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(295, 320, 600, 200);
        font = new Font(Font.HELVETICA,24,Font.NORMAL,Color.BLACK);
        ct.setText(new Phrase("Use o QR Code para", font));
        ct.go();
        
        ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(295, 295, 600, 200);
        font = new Font(Font.HELVETICA,24,Font.NORMAL,Color.BLACK);
        ct.setText(new Phrase("anunciar imóveis", font));
        ct.go();
        
        ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(295, 270, 600, 200);
        font = new Font(Font.HELVETICA,24,Font.NORMAL,Color.BLACK);
        ct.setText(new Phrase("ou cadastrar", font));
        ct.go();
        
        ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(295, 245, 600, 200);
        font = new Font(Font.HELVETICA,24,Font.NORMAL,Color.BLACK);
        ct.setText(new Phrase("pedidos.", font));
        ct.go();
        
        //for (String image : IMAGES) {
        for (int i = 0;i < IMAGES.length; i++) {
        	String image = IMAGES[i];
        	
        	if(i==1) {

        		
        		File inputFile = new File(image);
        	    BufferedImage inputImage = ImageIO.read(inputFile);
        	    int scaledWidth = (int) (inputImage.getWidth() * 0.25);
        	    int scaledHeight = (int) (inputImage.getHeight() * 0.25);
  
                inputImage = ImageIO.read(inputFile);

                BufferedImage outputImage = new BufferedImage(scaledWidth,
                        scaledHeight, inputImage.getType());
                
                Graphics2D g2d = outputImage.createGraphics();
                g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
                g2d.dispose();
        		
        		//BufferedImage bufferedImage = this.arredondar(outputImage);
        		
                BufferedImage bufferedImage = arredondarImagem(outputImage);
                
        		img = Image.getInstance(bufferedImage , null); 
          		
          		
        		
        	}else {
        		
        		 img = Image.getInstance(image);
        	        
        	}
           
            document.setPageSize(img);
            
            if(i==0)img.setAbsolutePosition(0, 0);
            if(i==1)img.setAbsolutePosition(180, 800);
            if(i==2)img.setAbsolutePosition(60, 155);
            if(i==3)img.setAbsolutePosition(125, 600); // phone
            if(i==4)img.setAbsolutePosition(125, 525); // email
            
            document.add(img);
        }
	    
        document.close();
    }
    
    public BufferedImage arredondarImagem(BufferedImage master) throws IOException {
    	
	 	//BufferedImage master = ImageIO.read(new File("d:\\silvio\\fotoAgente.png"));

	    int diameter = Math.min(master.getWidth(), master.getHeight());
	    BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = mask.createGraphics();
	    applyQualityRenderingHints(g2d);
	    g2d.fillOval(0, 0, diameter - 1, diameter - 1);
	    g2d.dispose();

	    BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	    g2d = masked.createGraphics();
	    applyQualityRenderingHints(g2d);
	    int x = (diameter - master.getWidth()) / 2;
	    int y = (diameter - master.getHeight()) / 2;
	    g2d.drawImage(master, x, y, null);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
	    g2d.drawImage(mask, 0, 0, null);
	    g2d.dispose();

	    return masked;
	    
	    //JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(masked)));

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
    
	public static BufferedImage arredondar(BufferedImage imagemRetangular) {
        int largura = imagemRetangular.getWidth();
        int altura = imagemRetangular.getHeight();
        int raio = largura / (double) altura > 0 ? altura : largura;
        BufferedImage imagemRedonda = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D graphics = imagemRedonda.createGraphics();
        graphics.setClip(new Area(new Ellipse2D.Double(0, 0, raio, raio)));
        graphics.drawImage(imagemRetangular, 0, 0, null);
        graphics.dispose();
        
        return imagemRedonda;
    }

    public BufferedImage executar(String pahtFotoAgente) throws IOException {
    	
    	InputStream arquivo = new BufferedInputStream(new FileInputStream(pahtFotoAgente));
        BufferedImage imagemRetangular = ImageIO.read(arquivo);
        BufferedImage imagemRedonda = arredondar(imagemRetangular);
        
        return imagemRedonda;
    }
}