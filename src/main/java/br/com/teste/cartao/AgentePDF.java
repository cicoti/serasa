package br.com.teste.cartao;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.ElementTags;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

public class AgentePDF {
	public static void main(String args[]) throws FileNotFoundException {
		try {
			
			 try {
			        Image img = Image.getInstance("d:\\silvio\\cartaoAgente.png");
			        //Image img = Image.getInstance(bufeBufferedImage, null);
			        Document document = new Document(img);
			        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:\\silvio\\cartaoAgente.pdf"));
			        //--
			        document.open();
			        img.setAbsolutePosition(0, 0);
			        //--
			        document.add(img);
			        //--
			        document.close();
			    } catch (DocumentException | IOException e) {
			        System.out.println("Intern Log : " + e.getMessage());
			    }  
		    
		} catch (DocumentException de) {
		    de.printStackTrace();
		}
	}
	

}

