package br.com.teste.cartao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



/**
 *
 *  How To Create New Pages In Pdf Using Itext Example -
 *  Using iText library - core java tutorial
 *
 */
public class SerasaMultiplePages {

  public static void main(String[] args) {
      try {
           String pdfFilePath = "D:\\silvio\\MultiplePages.pdf";

           OutputStream fos = new FileOutputStream(new File(pdfFilePath));

           Document document = new Document(PageSize.A4);

           PdfWriter.getInstance(document, fos);

           document.open();

           PdfPTable table1 = new PdfPTable(4);
           PdfPCell cell1 = new PdfPCell(new Phrase("Teste1"));
           cell1.setMinimumHeight(50);
           cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
           cell1.setColspan(4);
           table1.addCell(cell1);
           table1.completeRow();

           document.add(table1);

           document.newPage();

           PdfPTable table2 = new PdfPTable(4);
           PdfPCell cell2 = new PdfPCell(new Phrase("Teste2"));
           cell2.setMinimumHeight(50);
           cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
           cell2.setColspan(4);
           table2.addCell(cell2);
           table2.completeRow();

           document.add(table2);

           document.newPage();

           PdfPTable table3 = new PdfPTable(4);
           PdfPCell cell3 = new PdfPCell(new Phrase("Teste3"));
           cell3.setMinimumHeight(50);
           cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
           cell3.setColspan(4);
           table3.addCell(cell3);
           table3.completeRow();

           document.add(table2);

           document.close();
           fos.close();



      } catch (Exception e) {

           e.printStackTrace();
      }
  }
}
