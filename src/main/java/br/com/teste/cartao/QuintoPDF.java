package br.com.teste.cartao;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class QuintoPDF {
	public static void main(String args[]) throws IOException {
		try {
			
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("openpdf.pdf"));
			document.open();
			             
			PdfPTable table = new PdfPTable(4);
			             
			PdfPCell cellHeader = new PdfPCell(new Paragraph("Linha de cabeçalho."));
			cellHeader.setColspan(4);
			table.addCell(cellHeader);
			             
			table.addCell("1 - 1");
			table.addCell("1 - 2");
			table.addCell("1 - 3");
			table.addCell("1 - 4");
			         
			table.addCell("2 - 1");
			table.addCell("2 - 2");
			table.addCell("2 - 3");
			table.addCell("2 - 4");
			             
			PdfPCell cell = new PdfPCell(new Paragraph("Celula 1"));
			cell.setBorderColor(new Color(255, 0, 0));
			table.addCell(cell);
			         
			cell = new PdfPCell(new Paragraph("Celula 2"));
			cell.setColspan(3);
			cell.setBackgroundColor(new Color(192, 192, 192));
			cell.setHorizontalAlignment( PdfPCell.ALIGN_CENTER);
			table.addCell(cell);
			             
			document.add(table);
			          
			document.close();
		    
		} catch (FileNotFoundException fe) {
		   fe.printStackTrace();
		} catch (DocumentException de) {
		    de.printStackTrace();
		}
	}
}

