package br.com.teste.cartao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.ElementTags;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class SegundoPDF {
	public static void main(String args[]) {
		try {
			
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream("openpdf.pdf"));
	        document.open();
	                     
	        Font font = FontFactory.getFont(FontFactory.TIMES, 16, Font.NORMAL);
	                     
	        Paragraph p1 = new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris cursus velit ligula, eu pretium odio sodales nec. Nulla aliquam posuere leo ac sollicitudin. Vestibulum neque magna, auctor a ex et, gravida vestibulum sapien." , font   );
	        p1.setSpacingAfter(10);
	        p1.setFirstLineIndent(30);
	        document.add( p1 );
	                     
	        Paragraph p2 = new Paragraph("Proin sed justo aliquam, ornare justo at, ultricies sapien. Vestibulum sit amet lorem vel velit bibendum elementum. Integer euismod in sapien a rutrum. Mauris viverra odio in efficitur semper. Nullam eu leo rhoncus, vehicula tellus nec, placerat enim." , font   );
	        p2.setSpacingAfter(10);
	        p2.setAlignment(ElementTags.ALIGN_CENTER);
	        document.add( p2 );
	                     
	        Paragraph p3 = new Paragraph("Duis mattis fermentum nulla, sed egestas ante iaculis vel. Vestibulum ac felis ac ipsum euismod consectetur. Vivamus sit amet turpis eget magna tempus malesuada in at ex." , font   );
	        p3.setSpacingAfter(10);
	        document.add( p3 );
	                     
	        document.close();
		    
		} catch (FileNotFoundException fe) {
		   fe.printStackTrace();
		} catch (DocumentException de) {
		    de.printStackTrace();
		}
	}
}

