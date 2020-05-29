package br.com.teste.cartao;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



/**
 *
 *  How To Create New Pages In Pdf Using Itext Example -
 *  Using iText library - core java tutorial
 *
 */
public class CreateNewPagesInPdfUsingItextExample {

  public static void main(String[] args) {

	  Document document = new Document(PageSize.A4, 10, 10, 10, 10);

      try {

    	   String SERASA_LOGO = "D:\\silvio\\projetos2\\workspace\\CARTAO\\logoserasaexperianpeq.png";

           PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\silvio\\SerasaMultiplePages.pdf"));

           document.open();

           File serasaLogoFile = new File(SERASA_LOGO);
			BufferedImage serasaLogoBufferedImage = ImageIO.read(serasaLogoFile);
			Image img = Image.getInstance(serasaLogoBufferedImage, null);
			img.setAbsolutePosition(10, 780);
			document.add(img);
			/*
			 * PdfContentByte canvas = writer.getDirectContent(); Rectangle rect = new
			 * Rectangle(430, 840, 590, 820); rect.setBorder(Rectangle.BOX);
			 * rect.setBorderWidth(1); canvas.rectangle(rect);
			 */
			ColumnText ct = new ColumnText(writer.getDirectContent());
			ct.setSimpleColumn(430, 840, 590, 820);
			Font font = new Font(Font.HELVETICA, 8, Font.COURIER, Color.BLACK);
			ct.setText(new Phrase("Confidencial para: PME ALUGUEAGORA", font));
			ct.go();

			/*
			 * PdfContentByte canvas = writer.getDirectContent(); Rectangle rect = new
			 * Rectangle(30, 768, 200, 748); rect.setBorder(Rectangle.BOX);
			 * rect.setBorderWidth(1); canvas.rectangle(rect);
			 */

			ct = new ColumnText(writer.getDirectContent());
			ct.setSimpleColumn(30, 768, 200, 748);
			font = new Font(Font.HELVETICA, 18, Font.COURIER, Color.decode("#C3A27A"));
			ct.setText(new Phrase("Concentre - Resumo", font));
			ct.go();
			/*
			 * canvas = writer.getDirectContent(); rect = new Rectangle(360, 768, 560, 748);
			 * rect.setBorder(Rectangle.BOX); rect.setBorderWidth(1);
			 * canvas.rectangle(rect);
			 */
			Color colorData = Color.decode("#8C8C8C");
			Font fontData = new Font(Font.HELVETICA, 12, Font.NORMAL, colorData);
			Phrase phraseData = new Phrase("29 de Dezembro de 2020 às 13:24", fontData);

			PdfPTable tableData = new PdfPTable(1);
			tableData.setTotalWidth(200f);
			PdfPCell cellData = new PdfPCell(phraseData);
			cellData.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellData.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellData.setBorder(0);
			tableData.addCell(cellData);
			tableData.writeSelectedRows(0, -1, 350, 768, writer.getDirectContent());
			tableData.completeRow();

			Color colorBackgroundCabecalho = Color.decode("#DFE5EA");
			Color colorBackgroundCabecalhoDetalhe = Color.decode("#E6E6E6");
			Color colorFontCabecalho = Color.decode("#235493");
			Color colorFontCabecalhoDetalhe = Color.decode("#7A7273");
			Color colorTituloColuna = Color.decode("#908A8B");
			Color colorDadoColuna = Color.decode("#6E6E6E");
			Color colorBackgroundTituloColuna = Color.decode("#E6E6E6");
			Color colorBackgroundTituloColunaDetalhe = Color.decode("#F1F1F1");
			Color colorBackgroundDadoColuna = Color.decode("#FFFFFF");
			Color colorBackgroundDadoColunaZebra = Color.decode("#FAFAFA");
			Color colorBorder = Color.decode("#DFE5EA");
			Color colorBackgroundResumo = Color.decode("#ECF1F7");
			Color colorFontResumo = Color.decode("#235493");

			Font fontCabecalho = new Font(Font.HELVETICA, 16, Font.NORMAL, colorFontCabecalho);
			Font fontCabecalhoDetalhe = new Font(Font.HELVETICA, 16, Font.NORMAL, colorFontCabecalhoDetalhe);
			Font fontResumo = new Font(Font.HELVETICA, 12, Font.NORMAL, colorFontResumo);
			Font fontTituloColuna = new Font(Font.HELVETICA, 12, Font.NORMAL, colorTituloColuna);
			Font fontDadoColuna = new Font(Font.HELVETICA, 10, Font.NORMAL, colorDadoColuna);

			PdfPTable tableIdentificacao = new PdfPTable(4);
			tableIdentificacao.setTotalWidth(520f);
			float[] larguraColuna = new float[] { 180f, 80f, 120f, 140f };
			tableIdentificacao.setWidths(larguraColuna);

			PdfPCell cellCabecalho = new PdfPCell(new Phrase("Identificação", fontCabecalho));
			cellCabecalho.setPaddingLeft(5f);
			cellCabecalho.setBorderColor(colorBorder);
			cellCabecalho.setMinimumHeight(30f);
			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellCabecalho.setBackgroundColor(colorBackgroundCabecalho);
			cellCabecalho.setColspan(4);
			tableIdentificacao.addCell(cellCabecalho);

			PdfPCell cellTituloColuna = new PdfPCell();
			cellTituloColuna.setPaddingLeft(5f);
			cellTituloColuna.setBorderColor(colorBorder);
			cellTituloColuna.setMinimumHeight(30f);
			cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTituloColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellTituloColuna.setBackgroundColor(colorBackgroundTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Nome", fontTituloColuna));
			tableIdentificacao.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("CPF", fontTituloColuna));
			tableIdentificacao.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Data de Nascimento", fontTituloColuna));
			tableIdentificacao.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Nome da Mãe", fontTituloColuna));
			tableIdentificacao.addCell(cellTituloColuna);

			PdfPCell cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("RODRIGO FRANCISCO RIOS", fontDadoColuna));
			tableIdentificacao.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("046.467.009-80", fontDadoColuna));
			tableIdentificacao.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("28/11/1983", fontDadoColuna));
			tableIdentificacao.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("GLORIA MARIA COVEZZI", fontDadoColuna));
			tableIdentificacao.addCell(cellDadoColuna);

			cellCabecalho = new PdfPCell(new Phrase("Status do Documento", fontCabecalho));
			cellCabecalho.setPaddingLeft(5f);
			cellCabecalho.setBorderColor(colorBorder);
			cellCabecalho.setMinimumHeight(30f);
			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellCabecalho.setBackgroundColor(colorBackgroundCabecalho);
			cellCabecalho.setColspan(4);
			tableIdentificacao.addCell(cellCabecalho);

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Situação do CPF em 24/09/2019 : regular", fontDadoColuna));
			cellDadoColuna.setColspan(4);
			tableIdentificacao.addCell(cellDadoColuna);
			tableIdentificacao.writeSelectedRows(0, -1, 30, 730, writer.getDirectContent());

			document.add(tableIdentificacao);

			document.newPage();
			document.add(new Paragraph("This is Page 2"));

			document.newPage();
			document.add(new Paragraph("This is Page 3"));


      } catch (Exception e) {

           e.printStackTrace();
      }

      document.close();
  }
}

/* OUTPUT of above program/Example -

PDF created in >> e:/Create New Pages in Pdf Using Itext Example.pdf

*/