package br.com.teste.cartao;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class WriteSelectedRowsPDF {

	public static final String SERASA_LOGO = "D:\\silvio\\projetos2\\workspace\\CARTAO\\logoserasaexperianpeq.png";


  public static void main(String[] args) {
    Document document = new Document(PageSize.A4, 10, 10, 10, 10);
    try {
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
          "D:\\silvio\\WriteSelectedRowsPDF.pdf"));
      document.open();

      File serasaLogoFile = new File(SERASA_LOGO);
	    BufferedImage serasaLogoBufferedImage = ImageIO.read(serasaLogoFile);
		Image img = Image.getInstance(serasaLogoBufferedImage , null);
		img.setAbsolutePosition(10, 780);
		document.add(img);
/*
		PdfContentByte canvas = writer.getDirectContent();
      Rectangle rect = new Rectangle(430, 840, 590, 820);
      rect.setBorder(Rectangle.BOX);
      rect.setBorderWidth(1);
      canvas.rectangle(rect);
*/
		ColumnText ct = new ColumnText(writer.getDirectContent());
      ct.setSimpleColumn(430, 840, 590, 820);
      Font font = new Font(Font.HELVETICA,8,Font.COURIER,Color.BLACK);
      ct.setText(new Phrase("Confidencial para: PME ALUGUEAGORA", font));
      ct.go();


/*
		PdfContentByte canvas = writer.getDirectContent();
      Rectangle rect = new Rectangle(40, 768, 220, 748);
      rect.setBorder(Rectangle.BOX);
      rect.setBorderWidth(1);
      canvas.rectangle(rect);
*/
		ct = new ColumnText(writer.getDirectContent());
      ct.setSimpleColumn(40, 768, 220, 748);
      font = new Font(Font.HELVETICA,18,Font.COURIER,Color.BLACK);
      ct.setText(new Phrase("Concentre - Resumo", font));
      ct.go();
/*
      canvas = writer.getDirectContent();
      rect = new Rectangle(360, 768, 560, 748);
      rect.setBorder(Rectangle.BOX);
      rect.setBorderWidth(1);
      canvas.rectangle(rect);
*/

      ct = new ColumnText(writer.getDirectContent());
      ct.setSimpleColumn(360, 768, 560, 748);
      font = new Font(Font.HELVETICA,12,Font.COURIER,Color.BLACK);
      ct.setText(new Phrase("29 de Abril de 2020 às 13:24", font));
      ct.go();

      PdfPTable table = new PdfPTable(4);
      table.setTotalWidth(document.right() - document.left());

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

      table.setTotalWidth(document.right() - document.left());
      table.writeSelectedRows(0, -1, 10, 500, writer.getDirectContent());

    } catch (Exception de) {
      de.printStackTrace();
    }
    document.close();
  }
}