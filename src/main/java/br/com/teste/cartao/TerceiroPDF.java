package br.com.teste.cartao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.ElementTags;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.List;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class TerceiroPDF {
	public static void main(String args[]) {
		try {
			
			Document document = new Document();
		    PdfWriter.getInstance(document, new FileOutputStream("openpdf.pdf"));
		    document.open();
		     
		    //marcador normal
		    List list = new List();
		    list.add("Item 1");
		    list.add("Item 2");
		    list.add("Item 3");
		    list.add("Item 4");
		    document.add(list);
		             
		    document.add( Chunk.NEWLINE ); // nova linha
		 
		    //marcador numérico ordenado numero
		    List listOrderedNumero = new List(List.ORDERED,List.NUMERICAL);
		    listOrderedNumero.add("Item 1");
		    listOrderedNumero.add("Item 2");
		    listOrderedNumero.add("Item 3");
		    listOrderedNumero.add("Item 4");
		    document.add( listOrderedNumero );
		             
		    document.add( Chunk.NEWLINE ); // nova linha
		             
		    //marcador numérico ordenado letra
		    List listOrderedLetra = new List(List.ORDERED,List.ALPHABETICAL);
		    listOrderedLetra.add("Item 1");
		    listOrderedLetra.add("Item 2");
		    listOrderedLetra.add("Item 3");
		    listOrderedLetra.add("Item 4");
		    document.add( listOrderedLetra );
		             
		    document.add( Chunk.NEWLINE ); // nova linha
		 
		    //marcador com simbolo 
		    List listItem = new List();
		    listItem.setListSymbol("\u001A"); 
		             
		    listItem.add("Item 1");
		    listItem.add("Item 2");
		    listItem.add("Item 3");
		    listItem.add("Item 4");
		    document.add(listItem);
		         
		    document.add( Chunk.NEWLINE ); // nova linha
		             
		    document.close();
		    
		} catch (FileNotFoundException fe) {
		   fe.printStackTrace();
		} catch (DocumentException de) {
		    de.printStackTrace();
		}
	}
}

