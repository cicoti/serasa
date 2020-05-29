package br.com.teste.cartao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfWriter;

public class PrimeiroPDF {
	public static void main(String args[]) {
		try {
			
		    Document document = new Document();
		    PdfWriter.getInstance(document, new FileOutputStream("openpdf.pdf"));
		    document.open();
		                 
		    Chunk c = new Chunk("Teste Primeiro PDF" );
		                 
		    Font fontChunk = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
		    c.setFont(fontChunk);
		                 
		    document.add(c);
		 
		    document.close();
		    
		} catch (FileNotFoundException fe) {
		   fe.printStackTrace();
		} catch (DocumentException de) {
		    de.printStackTrace();
		}
	}
}

