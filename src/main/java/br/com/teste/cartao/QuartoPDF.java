package br.com.teste.cartao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.print.attribute.Size2DSyntax;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.ElementTags;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class QuartoPDF {
	public static void main(String args[]) throws FileNotFoundException {
		try {

			 try {
			        Image img = Image.getInstance("d:\\silvio\\coin.png");
			        //Image img = Image.getInstance(bufeBufferedImage, null);
			        Document document = new Document(PageSize.A4);
			        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:\\silvio\\coin.pdf"));
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

