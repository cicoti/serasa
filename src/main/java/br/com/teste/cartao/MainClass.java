package br.com.teste.cartao;
import java.awt.Color;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class MainClass {
  public static void main(String[] args) throws Exception {
    Document document = new Document(PageSize.A4.rotate());
    PdfWriter.getInstance(document, new FileOutputStream("D:\\silvio\\bordercolor.pdf"));
    document.open();
    PdfPTable table = new PdfPTable(4);
    table.setWidthPercentage(100);
    PdfPCell cell;
    cell = new PdfPCell(new Paragraph("t"));
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph("d"));
    cell.setBorder(Rectangle.TOP);
    cell.setUseBorderPadding(true);
    cell.setBorderWidthTop(5f);
    cell.setBorderColorTop(Color.cyan);
    cell.setBackgroundColor(Color.blue);
        cell.setBorder(Rectangle.BOTTOM);
    cell.setBorderColorBottom(Color.magenta);
    cell.setBorderWidthBottom(5f);
    cell.setBackgroundColor(Color.green);
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph("r"));
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph("b"));
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph("G:"));
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph("0.25"));
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph("0.5"));
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph("0.75"));
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph(":"));
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph("a"));
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph("b"));
    table.addCell(cell);
    cell = new PdfPCell(new Paragraph("o"));
    table.addCell(cell);
    document.add(table);

    document.close();
  }
}