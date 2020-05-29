//http://www.java2s.com/Code/Java/PDF-RTF/PdfPTableWriteSelectedRows.htm

package br.com.teste.cartao;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import org.apache.commons.beanutils.BeanUtils;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import br.com.teste.cartao.serasa.entidade.B49C;
import br.com.teste.cartao.serasa.entidade.I10500;
import br.com.teste.cartao.serasa.entidade.I16000;
import br.com.teste.cartao.serasa.entidade.I22000;
import br.com.teste.cartao.serasa.entidade.P002;
import br.com.teste.cartao.serasa.modelo.Modelo;
import br.com.teste.cartao.serasa.modelo.ModeloB49C;
import br.com.teste.cartao.serasa.modelo.ModeloI10500;
import br.com.teste.cartao.serasa.modelo.ModeloI16000;
import br.com.teste.cartao.serasa.modelo.ModeloI22000;
import br.com.teste.cartao.serasa.modelo.ModeloP002;

public class SerasaBack2 {

	public static final String SERASA_LOGO = "D:\\silvio\\projetos2\\workspace\\CARTAO\\logo_serasa_experian_peq.png";
	public static final String LEGENDA_EXCLAMACAO = "D:\\silvio\\projetos2\\workspace\\CARTAO\\legenda_excalamacao_peq.png";



	public static void popularObjeto(String linha, Object obj, Object[] listObject) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Map map = BeanUtils.describe(obj);
		Map<String, String> sortedMap = new TreeMap<String, String>(map);
		Set set = sortedMap.entrySet();
		Iterator it = set.iterator();
		int seq = 0;
		while(it.hasNext()){

		  Entry<String, String> entry = (Entry<String, String>)it.next();

		  try {
		  		BeanUtils.setProperty(obj,entry.getKey(),linha.substring(((Modelo)listObject[seq]).getPosicaoInicial(),((Modelo)listObject[seq]).getPosicaoInicial()+((Modelo)listObject[seq]).getBytes()));
		  }catch(StringIndexOutOfBoundsException e) {
			   try {
				   BeanUtils.setProperty(obj,entry.getKey(),linha.substring(((Modelo)listObject[seq]).getPosicaoInicial(),linha.length()));
			   }catch(Exception e1) {
				   	BeanUtils.setProperty(obj,entry.getKey(),"");
			   }
		  }catch(Exception e) {
			  System.out.println("Ocorreu um erro!");
			  e.printStackTrace();
		  }

		  seq++;

		}


	}

	public static Modelo recuperarModelo(String tipoRegistro) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		Class classe = Class.forName("br.com.teste.cartao.serasa.modelo.Modelo".concat(tipoRegistro));
	    Method metodo = classe.getMethod("getListModelo",null);
	    List<Object> listObject = (List<Object>) metodo.invoke(classe.newInstance());

	    Modelo modelo = (Modelo) listObject.get(listObject.size()-1);

	    return modelo;

	}


	public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		//BufferedReader reader = new BufferedReader(new FileReader ("D:\\silvio\\projetos2\\workspace\\CARTAO\\src\\main\\java\\br\\com\\teste\\cartao\\ConcentreResumoSEMfeaturesPF.txt"));
		//BufferedReader reader = new BufferedReader(new FileReader ("D:\\silvio\\projetos2\\workspace\\CARTAO\\src\\main\\java\\br\\com\\teste\\cartao\\ConcentreDetalheCOMfeaturesPF.txt"));
		//BufferedReader reader = new BufferedReader(new FileReader ("D:\\silvio\\projetos2\\workspace\\CARTAO\\src\\main\\java\\br\\com\\teste\\cartao\\ConcentreResumoSEMfeaturesPJ.txt"));
		BufferedReader reader = new BufferedReader(new FileReader ("D:\\silvio\\projetos2\\workspace\\CARTAO\\src\\main\\java\\br\\com\\teste\\cartao\\consulta_serasa_pj.txt"));

		ArrayList<String> listaLinhas = new ArrayList<String>();

		String         line = null;

	    try {
	        while((line = reader.readLine()) != null) {
	           listaLinhas.add(line);
	        }
	    } finally {
	        reader.close();
	    }

	    /*
	    String linhaInteira = listaLinhas.get(0);
	    String tipoRegistro = linhaInteira.substring(0,4);
	    System.out.println(">>>".concat(tipoRegistro).concat("<<<"));
	    Modelo modelo = recuperarModelo(tipoRegistro);

	    String linha = linhaInteira.substring(0, modelo.getPosicaoInicial()+modelo.getBytes()-1);
	    linhaInteira = linhaInteira.substring(linha.length());

	    tipoRegistro = linhaInteira.substring(0,4);
	    System.out.println(">>>".concat(tipoRegistro).concat("<<<"));
	    modelo = recuperarModelo(tipoRegistro);

	    linha = linhaInteira.substring(0, modelo.getPosicaoInicial()+modelo.getBytes()-1);
	    linhaInteira = linhaInteira.substring(linha.length());

	    System.out.println(linhaInteira);
	    */


	    Modelo modelo = null;
	    String linhaInteira = listaLinhas.get(0);
	    String tipoRegistro = null;
	    String subtipo = null;

	    do {

	    	try {

	    		tipoRegistro = linhaInteira.substring(0,4);

		    	subtipo = linhaInteira.substring(4,6);

			    if(tipoRegistro.charAt(0)=='I') {
			    	 modelo = recuperarModelo(tipoRegistro.concat(subtipo).trim());
			    }else {
			    	modelo = recuperarModelo(tipoRegistro.trim());
			    	subtipo = "";
			    }

   	    	    System.out.println(">>>".concat(tipoRegistro.concat(subtipo).trim()).concat("<<<"));

			    String linha = linhaInteira.substring(0, modelo.getPosicaoInicial()+modelo.getBytes()-1);
			    linhaInteira = linhaInteira.substring(linha.length());
			    //System.out.println(linhaInteira);
	    	}catch(Exception e) {
	    		System.out.println("Ocorreu um erro!");
	    		e.printStackTrace();
	    		break;
	    	}

	    }while(linhaInteira.length()>0);

	    System.out.println("FIM!");

	    /*

	    	for(String linha : listaLinhas) {

	    		String tipoRegistro = linha.substring(0,4);
	    		String subtipo = linha.substring(4,6);

	    		System.out.println("TipoRegistro: " + tipoRegistro);
	    		System.out.println("SubTipo: " + subtipo);

	    		linha = " ".concat(linha);

	    		if(tipoRegistro.equals("B49C")){

					ModeloB49C modeloB49C = new ModeloB49C();
					List<ModeloB49C> listaModeloB49 = modeloB49C.getListB49C();
					B49C b49c = new B49C();
					popularObjeto(linha, b49c, listaModeloB49.toArray());
					System.out.println(b49c.getSeq01_filler());

	    		}

	    		if(tipoRegistro.equals("P002")){

					ModeloP002 modeloP002 = new ModeloP002();
					List<ModeloP002> listaModeloP002 = modeloP002.getListP002();
					P002 p002 = new P002();
					popularObjeto(linha, p002, listaModeloP002.toArray());
					System.out.println(p002.getSeq01_tipo_reg());

	    		}

	    		if(tipoRegistro.equals("I105")){

					ModeloI105 modeloI105 = new ModeloI105();
					List<ModeloI105> listaModeloI105 = modeloI105.getListI105();
					I105 i105 = new I105();
					popularObjeto(linha, i105, listaModeloI105.toArray());
					listaI105.add(i105);
					System.out.println(i105.getSeq01_tipo_reg());
	    		}

	    		if(tipoRegistro.equals("I160")){

	    			if(subtipo.equals("00")){
						ModeloI160R modeloI160R = new ModeloI160R();
						List<ModeloI160R> listaModeloI160R = modeloI160R.getListI160R();
						I160R i160R = new I160R();
						popularObjeto(linha, i160R, listaModeloI160R.toArray());
						System.out.println(i160R.getSeq01_tipo_reg());
	    			}

	    		}

	    		if(tipoRegistro.equals("I220")){

	    			if(subtipo.equals("00")){
						ModeloI220R modeloI220R = new ModeloI220R();
						List<ModeloI220R> listaModeloI220R = modeloI220R.getListI220R();
						I220R i220R = new I220R();
						popularObjeto(linha, i220R, listaModeloI220R.toArray());
						System.out.println(i220R.getSeq01_tipo_reg());
	    			}

	    		}


	    	}

	*/

		Document document = new Document(PageSize.A4);

		try {

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\silvio\\Serasa.pdf"));

			document.open();

			Color colorConfidencial = Color.decode("#6E6E6E");
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
			Color colorBackgroundTotalizadorColuna = Color.decode("#FFFBED");
			Color colorBackgroundLegenda = Color.decode("#FAF6D4");
			Color colorQuadroChancePagamento = Color.decode("#1394D6");
			//Color c = Color.decode("#1394D6");

			Font fontConfidencial = new Font(Font.HELVETICA, 8, Font.COURIER, colorConfidencial);
			Font fontCabecalho = new Font(Font.HELVETICA, 16, Font.NORMAL, colorFontCabecalho);
			Font fontCabecalhoDetalhe = new Font(Font.HELVETICA, 16, Font.NORMAL, colorFontCabecalhoDetalhe);
			Font fontResumo = new Font(Font.HELVETICA, 12, Font.NORMAL, colorFontResumo);
			Font fontTituloColuna = new Font(Font.HELVETICA, 12, Font.NORMAL, colorTituloColuna);
			Font fontDadoColuna = new Font(Font.HELVETICA, 10, Font.NORMAL, colorDadoColuna);
			Font fontTituloDocumento = new Font(Font.HELVETICA, 18, Font.COURIER, Color.decode("#C3A27A"));
			Font fontData = new Font(Font.HELVETICA, 12, Font.NORMAL, Color.decode("#8C8C8C"));
			Font fontQuadroChancePagamento = new Font(Font.HELVETICA, 14, Font.COURIER, Color.decode("#FFFFFF"));
			Font fontQuadroChancePagamentoDescricao = new Font(Font.HELVETICA, 12, Font.COURIER, Color.decode("#1394D6"));

			PdfPTable tableConfidencial = new PdfPTable(1);
			tableConfidencial.setWidthPercentage(100);
			PdfPCell cellData = new PdfPCell(new Phrase("Confidencial para: PME ALUGUEAGORA", fontConfidencial));
			cellData.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellData.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellData.setBorder(0);
			tableConfidencial.addCell(cellData);
			tableConfidencial.completeRow();
			document.add(tableConfidencial);

			File serasaLogoFile = new File(SERASA_LOGO);
			BufferedImage serasaLogoBufferedImage = ImageIO.read(serasaLogoFile);
			Image imageLogo = Image.getInstance(serasaLogoBufferedImage, null);

			PdfPTable tableLogo = new PdfPTable(1);
			tableLogo.setWidthPercentage(100);
			PdfPCell cellLogo = new PdfPCell(imageLogo);
			cellLogo.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellLogo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellLogo.setBorder(0);
			tableLogo.addCell(cellLogo);
			tableLogo.completeRow();
			document.add(tableLogo);

            PdfPTable tableSeparacao = new PdfPTable(1);
			PdfPCell cellSeparacao = new PdfPCell(new Phrase("", fontDadoColuna));
			cellSeparacao.setBorder(PdfCell.NO_BORDER);
			cellSeparacao.setMinimumHeight(20f);
			tableSeparacao.addCell(cellSeparacao);
			tableSeparacao.completeRow();
            document.add(tableSeparacao);

			PdfPTable tableTituloData = new PdfPTable(2);
			tableTituloData.setWidthPercentage(100);
			tableTituloData.setWidths(new int[] { 50, 50});
			PdfPCell cellTituloData = new PdfPCell();
			cellTituloData.setBorderColor(colorBorder);
			cellTituloData.setMinimumHeight(30f);
			cellTituloData.setBorder(0);
			cellTituloData.setColspan(1);
			cellTituloData.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTituloData.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellTituloData.setPhrase(new Phrase("Concentre - Resumo", fontTituloDocumento));
			tableTituloData.addCell(cellTituloData);
			cellTituloData.setColspan(1);
			cellTituloData.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTituloData.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellTituloData.setPhrase(new Phrase("29 de Dezembro de 2020 às 13:24", fontData));
			tableTituloData.addCell(cellTituloData);
			tableTituloData.completeRow();
            document.add(tableTituloData);

            tableSeparacao = new PdfPTable(1);
			cellSeparacao = new PdfPCell(new Phrase("", fontDadoColuna));
			cellSeparacao.setBorder(PdfCell.NO_BORDER);
			cellSeparacao.setMinimumHeight(20f);
			tableSeparacao.addCell(cellSeparacao);
			tableSeparacao.completeRow();
            document.add(tableSeparacao);

			PdfPTable tableIdentificacao = new PdfPTable(4);
			tableIdentificacao.setWidthPercentage(100);
			tableIdentificacao.setWidths(new int[] { 30, 15, 25, 30 });

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
			tableIdentificacao.completeRow();
            document.add(tableIdentificacao);

            tableSeparacao = new PdfPTable(1);
			cellSeparacao = new PdfPCell(new Phrase("", fontDadoColuna));
			cellSeparacao.setBorder(PdfCell.NO_BORDER);
			cellSeparacao.setMinimumHeight(20f);
			tableSeparacao.addCell(cellSeparacao);
			tableSeparacao.completeRow();
            document.add(tableSeparacao);

            PdfPTable tableAnotacoesNegativas = new PdfPTable(5);
			tableAnotacoesNegativas.setWidthPercentage(100);
			tableAnotacoesNegativas.setWidths(new int[] {33, 15, 20, 12, 20});

			cellCabecalho = new PdfPCell(new Phrase("Anotações Negativas", fontCabecalho));
			cellCabecalho.setPaddingLeft(5f);
			cellCabecalho.setBorderColor(colorBorder);
			cellCabecalho.setMinimumHeight(30f);
			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellCabecalho.setBackgroundColor(colorBackgroundCabecalho);
			cellCabecalho.setColspan(5);
			tableAnotacoesNegativas.addCell(cellCabecalho);

			cellCabecalho = new PdfPCell(new Phrase("Resumo", fontResumo));
			cellCabecalho.setPaddingLeft(5f);
			cellCabecalho.setBorderColor(colorBorder);
			cellCabecalho.setMinimumHeight(20f);
			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellCabecalho.setBackgroundColor(colorBackgroundResumo);
			cellCabecalho.setColspan(5);
			tableAnotacoesNegativas.addCell(cellCabecalho);

			cellTituloColuna = new PdfPCell();
			cellTituloColuna.setPaddingLeft(5f);
			cellTituloColuna.setBorderColor(colorBorder);
			cellTituloColuna.setMinimumHeight(30f);
			cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTituloColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellTituloColuna.setBackgroundColor(colorBackgroundTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Ocorrências", fontTituloColuna));
			tableAnotacoesNegativas.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Quantidade", fontTituloColuna));
			tableAnotacoesNegativas.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Período", fontTituloColuna));
			tableAnotacoesNegativas.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Valor (R$)", fontTituloColuna));
			tableAnotacoesNegativas.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Mais Recente", fontTituloColuna));
			tableAnotacoesNegativas.addCell(cellTituloColuna);

			// Linha 1

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Pendências Comerciais (PEFIN)", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("NADA CONSTA", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);

			// Linha 2

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);
			cellDadoColuna.setPhrase(new Phrase("Pendências Bancárias (PEFIN)", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("5", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("set/2019 a out/2019", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("106,11", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("BANCO BRADESCO", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);

			// Linha 3

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Cheques sem fundos", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("2", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("out/2019 a out/2019", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("0,00", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("BANCO BRADESCO", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);

			// Linha 4

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);
			cellDadoColuna.setPhrase(new Phrase("Protestos", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("4", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("mai/2019 a mai/2019", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("1.255,00", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("CASCAVEL", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);

			// Linha 5

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Ações Judiciais", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("NADA CONSTA", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);

			// LINHA 6

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);
			cellDadoColuna.setPhrase(new Phrase("Participação em Falências", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("NADA CONSTA", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);

			// Linha 7

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Dividas Vencidas", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("NADA CONSTA", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);

			// Linha 8

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);
			cellDadoColuna.setPhrase(new Phrase("Falência/Concordata/Recuperação", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("NADA CONSTA", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);

			// Linha 9

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Pendências Internas", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("NADA CONSTA", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			tableAnotacoesNegativas.completeRow();
            document.add(tableAnotacoesNegativas);

            // PULAR PAGINA
            document.newPage();

            // PENDENCIAS BANCARIAS

			PdfPTable tablePendenciasBancarias = new PdfPTable(7);
			tablePendenciasBancarias.setWidthPercentage(100);
			tablePendenciasBancarias.setWidths(new int[] { 20, 18, 20, 12, 12, 10, 8 });
			cellCabecalho = new PdfPCell(new Phrase("Pendências Bancárias", fontCabecalhoDetalhe));
			cellCabecalho.setPaddingLeft(5f);
			cellCabecalho.setBorderColor(colorBorder);
			cellCabecalho.setMinimumHeight(30f);
			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellCabecalho.setBackgroundColor(colorBackgroundCabecalhoDetalhe);
			cellCabecalho.setColspan(7);
			tablePendenciasBancarias.addCell(cellCabecalho);

			cellTituloColuna = new PdfPCell();
			cellTituloColuna.setPaddingLeft(5f);
			cellTituloColuna.setBorderColor(colorBorder);
			cellTituloColuna.setMinimumHeight(30f);
			cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTituloColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellTituloColuna.setBackgroundColor(colorBackgroundTituloColunaDetalhe);
			cellTituloColuna.setPhrase(new Phrase("Contrato", fontTituloColuna));
			tablePendenciasBancarias.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Modalidade", fontTituloColuna));
			tablePendenciasBancarias.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Empresa", fontTituloColuna));
			tablePendenciasBancarias.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Data", fontTituloColuna));
			tablePendenciasBancarias.addCell(cellTituloColuna);
			cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTituloColuna.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellTituloColuna.setPhrase(new Phrase("Valor\n(R$)", fontTituloColuna));
			tablePendenciasBancarias.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Avalista", fontTituloColuna));
			tablePendenciasBancarias.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Local", fontTituloColuna));
			tablePendenciasBancarias.addCell(cellTituloColuna);

			// Linha 1

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("046467009000080A", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("ADIANT CONTA", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("BANCO BRADESCO", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("28/10/2019", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("106,11", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("Não", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);

			// Linha 2

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);
			cellDadoColuna.setPhrase(new Phrase("046467009000080F", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("FINANCIAMENT", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("BANCO BRADESCO", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("24/10/2019", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("122,09", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("Não", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);

			// Linha 3

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("EMP596102", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("FINANCIAMENT", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("SICOOB CREDICAPI", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("20/09/2019", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("2.185,86", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("Não", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);

			// Linha 4

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);
			cellDadoColuna.setPhrase(new Phrase("CCO453315", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("ADIANT CONTA", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("SICOOB CREDICAPI", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("02/09/2019", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("203,76", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("Não", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);

			// Linha 5

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("LMC453315", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("FINANCIAMENT", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("SICOOB CREDICAPI", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("02/09/2019", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("2.056,26", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase("Não", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			tablePendenciasBancarias.completeRow();


		// Totalizador

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setColspan(7);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundTotalizadorColuna);
			cellDadoColuna.setPhrase(new Phrase("Total de Ocorrências: 5", fontDadoColuna));
			tablePendenciasBancarias.addCell(cellDadoColuna);
			tablePendenciasBancarias.completeRow();
			document.add(tablePendenciasBancarias);

		// Separação

            tableSeparacao = new PdfPTable(1);
			cellSeparacao = new PdfPCell(new Phrase("", fontDadoColuna));
			cellSeparacao.setBorder(PdfCell.NO_BORDER);
			cellSeparacao.setMinimumHeight(20f);
			tableSeparacao.addCell(cellSeparacao);
			tableSeparacao.completeRow();
            document.add(tableSeparacao);

         // Cheques Sem Fundo

			PdfPTable tableChequesSemFundo = new PdfPTable(9);
			tableChequesSemFundo.setWidthPercentage(100);
			tableChequesSemFundo.setWidths(new int[] { 17, 10, 12, 5, 10, 10, 14, 12, 10 });
			cellCabecalho = new PdfPCell(new Phrase("Cheques Sem Fundo", fontCabecalhoDetalhe));
			cellCabecalho.setPaddingLeft(5f);
			cellCabecalho.setBorderColor(colorBorder);
			cellCabecalho.setMinimumHeight(30f);
			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellCabecalho.setBackgroundColor(colorBackgroundCabecalhoDetalhe);
			cellCabecalho.setColspan(9);
			tableChequesSemFundo.addCell(cellCabecalho);

			cellTituloColuna = new PdfPCell();
			cellTituloColuna.setPaddingLeft(5f);
			cellTituloColuna.setBorderColor(colorBorder);
			cellTituloColuna.setMinimumHeight(30f);
			cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTituloColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellTituloColuna.setBackgroundColor(colorBackgroundTituloColunaDetalhe);
			cellTituloColuna.setPhrase(new Phrase("Banco", fontTituloColuna));
			tableChequesSemFundo.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Agência", fontTituloColuna));
			tableChequesSemFundo.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Cidade", fontTituloColuna));
			tableChequesSemFundo.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("UF", fontTituloColuna));
			tableChequesSemFundo.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Cheque", fontTituloColuna));
			tableChequesSemFundo.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Alinea", fontTituloColuna));
			tableChequesSemFundo.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Quantidade", fontTituloColuna));
			tableChequesSemFundo.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Data", fontTituloColuna));
			tableChequesSemFundo.addCell(cellTituloColuna);
			cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTituloColuna.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellTituloColuna.setPhrase(new Phrase("Valor", fontTituloColuna));
			tableChequesSemFundo.addCell(cellTituloColuna);

			// Linha 1

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("BRADESCO", fontDadoColuna));
			tableChequesSemFundo.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("0642", fontDadoColuna));
			tableChequesSemFundo.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("CASCAVEL", fontDadoColuna));
			tableChequesSemFundo.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("PR", fontDadoColuna));
			tableChequesSemFundo.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("CCF-BB", fontDadoColuna));
			tableChequesSemFundo.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableChequesSemFundo.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("2", fontDadoColuna));
			tableChequesSemFundo.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("01/10/2019", fontDadoColuna));
			tableChequesSemFundo.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("106,11", fontDadoColuna));
			tableChequesSemFundo.addCell(cellDadoColuna);

			// Totalizador

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setColspan(9);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundTotalizadorColuna);
			cellDadoColuna.setPhrase(new Phrase("Total de Ocorrências: 1", fontDadoColuna));
			tableChequesSemFundo.addCell(cellDadoColuna);
			tableChequesSemFundo.completeRow();
			document.add(tableChequesSemFundo);

			// Separação

            tableSeparacao = new PdfPTable(1);
			cellSeparacao = new PdfPCell(new Phrase("", fontDadoColuna));
			cellSeparacao.setBorder(PdfCell.NO_BORDER);
			cellSeparacao.setMinimumHeight(20f);
			tableSeparacao.addCell(cellSeparacao);
			tableSeparacao.completeRow();
            document.add(tableSeparacao);

            // Protestos

			PdfPTable tableProtestos = new PdfPTable(5);
			tableProtestos.setWidthPercentage(100);
			tableProtestos.setWidths(new int[] { 30,30,10,20,20 });
			cellCabecalho = new PdfPCell(new Phrase("Protestos", fontCabecalhoDetalhe));
			cellCabecalho.setPaddingLeft(5f);
			cellCabecalho.setBorderColor(colorBorder);
			cellCabecalho.setMinimumHeight(30f);
			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellCabecalho.setBackgroundColor(colorBackgroundCabecalhoDetalhe);
			cellCabecalho.setColspan(9);
			tableProtestos.addCell(cellCabecalho);

			cellTituloColuna = new PdfPCell();
			cellTituloColuna.setPaddingLeft(5f);
			cellTituloColuna.setBorderColor(colorBorder);
			cellTituloColuna.setMinimumHeight(30f);
			cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTituloColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellTituloColuna.setBackgroundColor(colorBackgroundTituloColunaDetalhe);
			cellTituloColuna.setPhrase(new Phrase("Cartório", fontTituloColuna));
			tableProtestos.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Cidade", fontTituloColuna));
			tableProtestos.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("UF", fontTituloColuna));
			tableProtestos.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Data", fontTituloColuna));
			tableProtestos.addCell(cellTituloColuna);
			cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTituloColuna.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellTituloColuna.setPhrase(new Phrase("Valor", fontTituloColuna));
			tableProtestos.addCell(cellTituloColuna);

			// Linha 1

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("001", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("CASCAVEL", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("PR", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("20/01/2020", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("1.255,00", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);

			// Linha 2

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);
			cellDadoColuna.setPhrase(new Phrase("002", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("CASCAVEL", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("PR", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("30/12/2020", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("1.267,00", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);

			// Linha 3

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("001", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("CASCAVEL", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("PR", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("05/12/2019", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("1.267,00", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);

			// Linha 4

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);
			cellDadoColuna.setPhrase(new Phrase("001", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("CASCAVEL", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("PR", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("02/05/2019", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase("11.067,88", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);

			// Totalizador

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setColspan(5);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundTotalizadorColuna);
			cellDadoColuna.setPhrase(new Phrase("Total de Ocorrências: 4", fontDadoColuna));
			tableProtestos.addCell(cellDadoColuna);
			tableProtestos.completeRow();
			document.add(tableProtestos);

			// PULAR PAGINA

			 document.newPage();

            // Participação Societária

            PdfPTable tableParticipacaoSocietaria = new PdfPTable(5);
            tableParticipacaoSocietaria.setWidthPercentage(100);
            tableParticipacaoSocietaria.setWidths(new int[] {5,50, 20, 20, 5});

			cellCabecalho = new PdfPCell(new Phrase("Participação Societária", fontCabecalho));
			cellCabecalho.setPaddingLeft(5f);
			cellCabecalho.setBorderColor(colorBorder);
			cellCabecalho.setMinimumHeight(30f);
			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellCabecalho.setBackgroundColor(colorBackgroundCabecalho);
			cellCabecalho.setColspan(5);
			tableParticipacaoSocietaria.addCell(cellCabecalho);

			File legendaExclamacaoFile = new File(LEGENDA_EXCLAMACAO);
			BufferedImage legendaExclamacaoBufferedImage = ImageIO.read(legendaExclamacaoFile);
			Image imagemLegendaExclamacao = Image.getInstance(legendaExclamacaoBufferedImage, null);

			// LEGENDA
			PdfPTable tableLegenda = new PdfPTable(3);
			tableLegenda.setWidthPercentage(100);
			tableLegenda.setWidths(new int[] { 2, 5, 93});
			PdfPCell cellLegendaExclamacao = new PdfPCell();

			cellLegendaExclamacao = new PdfPCell(new Phrase("", fontData));
			cellLegendaExclamacao.setBorder(0);
			tableLegenda.addCell(cellLegendaExclamacao);

			cellLegendaExclamacao = new PdfPCell(imagemLegendaExclamacao);
			cellCabecalho.setMinimumHeight(20f);
			cellLegendaExclamacao.setBorder(0);
			cellLegendaExclamacao.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellLegendaExclamacao.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableLegenda.addCell(cellLegendaExclamacao);

			cellLegendaExclamacao = new PdfPCell();
			cellCabecalho.setMinimumHeight(20f);
			cellLegendaExclamacao.setBorder(0);
			cellLegendaExclamacao.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellLegendaExclamacao.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellLegendaExclamacao.setPhrase(new Phrase("Legenda: Participantes que possuem anotações negativas.", fontData));
			cellLegendaExclamacao.setBackgroundColor(colorBackgroundLegenda);
			tableLegenda.addCell(cellLegendaExclamacao);

			// COLUNA LEGENDA
			cellCabecalho = new PdfPCell(tableLegenda);
			cellCabecalho.setMinimumHeight(20f);
			cellCabecalho.setBorderColor(colorBorder);
			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellCabecalho.setBackgroundColor(colorBackgroundLegenda);
			cellCabecalho.setColspan(5);
			tableParticipacaoSocietaria.addCell(cellCabecalho);

			cellTituloColuna = new PdfPCell();
			cellTituloColuna.setPaddingLeft(5f);
			cellTituloColuna.setBorderColor(colorBorder);
			cellTituloColuna.setMinimumHeight(30f);
			cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellTituloColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellTituloColuna.setBackgroundColor(colorBackgroundTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("", fontTituloColuna));
			tableParticipacaoSocietaria.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Empresa", fontTituloColuna));
			tableParticipacaoSocietaria.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("CNPJ", fontTituloColuna));
			tableParticipacaoSocietaria.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("Participação (%)", fontTituloColuna));
			tableParticipacaoSocietaria.addCell(cellTituloColuna);
			cellTituloColuna.setPhrase(new Phrase("UF", fontTituloColuna));
			tableParticipacaoSocietaria.addCell(cellTituloColuna);

			// Linha 1

			PdfPTable tableLegendaMenor = new PdfPTable(1);
			PdfPCell cellLegendaMenor = new PdfPCell(imagemLegendaExclamacao);
			cellLegendaMenor.setMinimumHeight(5f);
			cellLegendaMenor.setBorder(0);
			cellLegendaMenor.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellLegendaMenor.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableLegendaMenor.addCell(cellLegendaMenor);

			cellDadoColuna = new PdfPCell(tableLegendaMenor);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableParticipacaoSocietaria.addCell(cellDadoColuna);

			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setPhrase(new Phrase("RODRIGO FRANCISCO RIOS ME", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("28.819.226/0001-38", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("100", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("PR", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);

			// Linha 2

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);

			cellDadoColuna.setPhrase(new Phrase("", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("SITUACAO DO CNPJ EM 24/01/2020: ATIVA", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Desde: out/2017", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Última Atualização: jan/2020", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);

			// Linha 3

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);

			cellDadoColuna.setPhrase(new Phrase("", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("PILLA SPORT BRASIL COMERCIO DE ARTIGOS", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("31.188.377/0001-40", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("50", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("PR", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);

			// Linha 4

			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);

			cellDadoColuna.setPhrase(new Phrase("", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("SITUACAO DO CNPJ EM 05/12/2019: BAIXADA", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Desde: ago/2018", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Última Atualização: dez/2019", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			tableParticipacaoSocietaria.addCell(cellDadoColuna);

			tableParticipacaoSocietaria.completeRow();
            document.add(tableParticipacaoSocietaria);

         // Separação

            tableSeparacao = new PdfPTable(1);
			cellSeparacao = new PdfPCell(new Phrase("", fontDadoColuna));
			cellSeparacao.setBorder(PdfCell.NO_BORDER);
			cellSeparacao.setMinimumHeight(20f);
			tableSeparacao.addCell(cellSeparacao);
			tableSeparacao.completeRow();
            document.add(tableSeparacao);

           // Serasa Score

           PdfPTable tableSerasaScore = new PdfPTable(5);
           tableSerasaScore.setWidthPercentage(100);
           tableSerasaScore.setWidths(new int[] {31,1,21,1,41});
           cellCabecalho = new PdfPCell(new Phrase("Serasa Score", fontCabecalho));
           cellCabecalho.setBorder(0);
           cellCabecalho.setPaddingLeft(5f);
           cellCabecalho.setBorderColor(colorBorder);
           cellCabecalho.setMinimumHeight(30f);
           cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
           cellCabecalho.setBackgroundColor(colorBackgroundCabecalho);
           cellCabecalho.setColspan(5);
           tableSerasaScore.addCell(cellCabecalho);

           cellTituloColuna = new PdfPCell();
		   cellTituloColuna.setMinimumHeight(20f);
		   cellTituloColuna.setPhrase(new Phrase("", fontTituloColuna));
		   cellTituloColuna.setColspan(5);
	       cellTituloColuna.setBorder(0);
	       tableSerasaScore.addCell(cellTituloColuna);

	       // Montando a imagem

	       List<Arco> listaArcoExterno = new ArrayList<Arco>();
		   List<Arco> listaArcoInterno = new ArrayList<Arco>();

		   BufferedImage bufferImagemSerasaScore = new BufferedImage( 800, 450, BufferedImage.TYPE_INT_RGB );
		   Graphics2D graphicsSerasaScore = bufferImagemSerasaScore.createGraphics();
		   graphicsSerasaScore.setBackground(Color.WHITE);
		   graphicsSerasaScore.fillRect ( 0, 0, bufferImagemSerasaScore.getWidth(), bufferImagemSerasaScore.getHeight() );

		   listaArcoExterno = calcularArco(graphicsSerasaScore, 400, 400, 300);
		   listaArcoInterno = calcularArco(graphicsSerasaScore, 400, 400, 200);

		   //Cada sessao de cor possui um tom de azul, formando um degradê, do tom mais claro para o tom mais escuro.
		   //Essa peleta foi retirada desse site: https://desarrollador-android.com/material-design/diseno-material-design/estilo/color/
		   String paletaCorSessaoArco[] = {
					"#E3F2FD",
					"#BBDEFB",
					"#90CAF9",
					"#64B5F6",
					"#42A5F5",
					"#2196F3",
					"#1E88E5",
					"#1976D2",
					"#1565C0",
					"#0D47A1"
			};

		    // Essa etapa é a resonsavel por criar a forma do arco resultante.
		    // São 10 sessoes ao todo.

			int[] poligonoX;
			int[] poligonoY;

			int j = 0;
			for(int i = 0 ; i<60 ; i+=6) {

				poligonoX = new int[] {
						listaArcoExterno.get(i).getX(),
						listaArcoExterno.get(i+1).getX(),
						listaArcoExterno.get(i+2).getX(),
						listaArcoExterno.get(i+3).getX(),
						listaArcoExterno.get(i+4).getX(),
						listaArcoExterno.get(i+5).getX(),
						listaArcoInterno.get(i+5).getX(),
						listaArcoInterno.get(i+4).getX(),
						listaArcoInterno.get(i+3).getX(),
						listaArcoInterno.get(i+2).getX(),
						listaArcoInterno.get(i+1).getX(),
						listaArcoInterno.get(i).getX(),


				};
				poligonoY = new int[] {
						listaArcoExterno.get(i).getY(),
						listaArcoExterno.get(i+1).getY(),
						listaArcoExterno.get(i+2).getY(),
						listaArcoExterno.get(i+3).getY(),
						listaArcoExterno.get(i+4).getY(),
						listaArcoExterno.get(i+5).getY(),
						listaArcoInterno.get(i+5).getY(),
						listaArcoInterno.get(i+4).getY(),
						listaArcoInterno.get(i+3).getY(),
						listaArcoInterno.get(i+2).getY(),
						listaArcoInterno.get(i+1).getY(),
						listaArcoInterno.get(i).getY(),
				};

				Shape shapeSessaoArco = new Polygon(poligonoX, poligonoY, poligonoX.length);
				graphicsSerasaScore.draw(shapeSessaoArco);
				graphicsSerasaScore.setColor(Color.decode(paletaCorSessaoArco[j++]));
				graphicsSerasaScore.fill(shapeSessaoArco);

			}

			// Essa etapa executa o posicionamento dos valores no grafico.
			java.awt.Font fontSerasaPontuacao = new java.awt.Font("Impact", Font.HELVETICA, 35);
			graphicsSerasaScore.setColor(Color.BLACK);
			graphicsSerasaScore.setFont(fontSerasaPontuacao);
			graphicsSerasaScore.drawString("0", 75, 384);
			graphicsSerasaScore.drawString("100", 55, 307);
			graphicsSerasaScore.drawString("200", 95, 223);
			graphicsSerasaScore.drawString("300", 162, 157);
			graphicsSerasaScore.drawString("400", 250, 114);
			graphicsSerasaScore.drawString("500", 365, 90);
			graphicsSerasaScore.drawString("600", 492, 114);
			graphicsSerasaScore.drawString("700", 576, 157);
			graphicsSerasaScore.drawString("800", 642, 223);
			graphicsSerasaScore.drawString("900", 685, 307);
			graphicsSerasaScore.drawString("1000", 699, 384);

		    // Desenho da flecha
			int[] poligonoFlechaX = { -20, 0, +20, 0};
			int[] poligonoFlechaY = { 20, 10, 20, -20};
			Shape shapeFlecha = new Polygon(poligonoFlechaX, poligonoFlechaY, poligonoFlechaX.length);

			double indice = 0.1785; // Esse valor é um ajuste por conta de diferenças de pixels, para que a flexa aponte exatamente no local certo.
			int posicaoCentroX = 400;
			int posicaoCentroY = 400;
			int raio = 170;
			int score = 214;

			// Essa etapa desenha o número do score na imagem.
			String textScore = String.valueOf(score);
			java.awt.Font fontSerasaScore = new java.awt.Font("Helvetica", Font.HELVETICA, 74);
			graphicsSerasaScore.setColor(Color.decode("#1394D6"));
			graphicsSerasaScore.setFont(fontSerasaScore);
			int posicao = (bufferImagemSerasaScore.getWidth()/2) - ((textScore.length()*36)/2);
			graphicsSerasaScore.drawString(textScore, posicao, posicaoCentroY-5);

			int graus = (int) (180 - (score * indice)); // quantos graus por indice
			double angulo = ((score/1000f) * 180) + 270; // angulo que a flecha será direcionada
			double xd = Math.cos(1 * Math.PI / 180 * -graus); // coordenada onde a fecha ficará no arco.
			double yd = Math.sin(1 * Math.PI / 180 * -graus); // coordenada onde a fecha ficará no arco.
			int x = (int) (xd * raio + posicaoCentroX); // correcao da posicao da flexa dentro da imagem.
			int y = (int) (yd * raio + posicaoCentroY); // correcao da posicao da flexa dentro da imagem.

			AffineTransform transform = new AffineTransform();
			graphicsSerasaScore.setTransform(transform);
			graphicsSerasaScore.setColor(Color.BLACK);
			graphicsSerasaScore.translate(x, y);
			graphicsSerasaScore.rotate(Math.toRadians(angulo));
			graphicsSerasaScore.scale(1.5, 1.5);
			graphicsSerasaScore.fill(shapeFlecha);
			graphicsSerasaScore.dispose();

		   Image serasaScoreImage = Image.getInstance(bufferImagemSerasaScore, null);
		   serasaScoreImage.scalePercent(20);

           cellTituloColuna = new PdfPCell(serasaScoreImage);
	       cellTituloColuna.setBorder(0);
	       tableSerasaScore.addCell(cellTituloColuna);

	       cellTituloColuna = new PdfPCell();
	       cellTituloColuna.setBorder(0);
	       tableSerasaScore.addCell(cellTituloColuna);

	       cellTituloColuna.setPhrase(new Phrase("17,41% \nChance de \nPagamento", fontQuadroChancePagamento));
	       cellTituloColuna.setBackgroundColor(colorQuadroChancePagamento);
	       cellTituloColuna.setPadding(10f);
	       cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       cellTituloColuna.setHorizontalAlignment(Element.ALIGN_CENTER);
	       cellTituloColuna.setBorder(0);
	       tableSerasaScore.addCell(cellTituloColuna);

	       cellTituloColuna = new PdfPCell();
	       cellTituloColuna.setBorder(0);
	       tableSerasaScore.addCell(cellTituloColuna);

	       cellTituloColuna = new PdfPCell(new Phrase("A chance de um consumidor, com score entre 201 e 300, pagar seus compromissos financeiros nos próximos 12 meses é de 17,41%", fontQuadroChancePagamentoDescricao));
	       cellTituloColuna.setPaddingLeft(20f);
	       cellTituloColuna.setBorderColorTop(colorQuadroChancePagamento);
	       cellTituloColuna.setBorderColorBottom(colorQuadroChancePagamento);
	       cellTituloColuna.setBorderColorLeft(colorQuadroChancePagamento);
	       cellTituloColuna.setBorderColorRight(colorQuadroChancePagamento);
	       cellTituloColuna.setBorderWidthTop(1f);
	       cellTituloColuna.setBorderWidthBottom(1f);
	       cellTituloColuna.setBorderWidthLeft(1f);
	       cellTituloColuna.setBorderWidthRight(1f);
	       cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       cellTituloColuna.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	       tableSerasaScore.addCell(cellTituloColuna);

	       tableSerasaScore.completeRow();
           document.add(tableSerasaScore);

           document.newPage();

           // Informacao geral.

  			PdfPTable tableInformacoesGerais = new PdfPTable(1);
  			tableInformacoesGerais.setWidthPercentage(100);
  			String textoInformacoesGerais = "A decisão da aprovação ou não do crédito é de exclusiva responsabilidade do concedente. As informações prestadas pela Serasa Experian têm como objetivo subsidiar essas decisões e, em hipótese alguma, devem ser utilizadas como justificativa, pelo concedente do crédito, para a tomada da referida decisão.";
  			cellCabecalho = new PdfPCell(new Phrase(textoInformacoesGerais, fontDadoColuna));
  			cellCabecalho.setPadding(10f);
  			cellCabecalho.setBorderColor(colorBorder);
  			cellCabecalho.setMinimumHeight(30f);
  			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
  			cellCabecalho.setBackgroundColor(colorBackgroundCabecalhoDetalhe);
  			tableInformacoesGerais.addCell(cellCabecalho);
  			tableInformacoesGerais.completeRow();
  			document.add(tableInformacoesGerais);

  	         // Separação

  			tableSeparacao = new PdfPTable(1);
			cellSeparacao = new PdfPCell(new Phrase("", fontDadoColuna));
			cellSeparacao.setBorder(PdfCell.NO_BORDER);
			cellSeparacao.setMinimumHeight(670f);
			tableSeparacao.addCell(cellSeparacao);
			tableSeparacao.completeRow();
            document.add(tableSeparacao);

            // Rodapé

            tableInformacoesGerais = new PdfPTable(1);
			tableInformacoesGerais.setWidthPercentage(100);
  			textoInformacoesGerais = "As informações acima, de uso exclusivo do destinatário, são protegidas por sigilo contratual. Sua utilização por outra pessoa, ou para finalidade diversa da contratada, caracteriza ilícito civil, tornando a prova inútil para o processo.";
  			cellCabecalho = new PdfPCell(new Phrase(textoInformacoesGerais, fontDadoColuna));
  			cellCabecalho.setPadding(10f);
  			cellCabecalho.setBorder(0);
  			cellCabecalho.setMinimumHeight(30f);
  			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
  			cellCabecalho.setBackgroundColor(colorBackgroundDadoColuna);
  			tableInformacoesGerais.addCell(cellCabecalho);
  			tableInformacoesGerais.completeRow();
  			document.add(tableInformacoesGerais);

			/*
			 * // Linha 6
			 *
			 * document.newPage();
			 *
			 * tablePendenciasBancarias = new PdfPTable(7); cellCabecalho = new PdfPCell(new
			 * Phrase("Pendências Bancárias", fontCabecalhoDetalhe));
			 * cellCabecalho.setPaddingLeft(5f); cellCabecalho.setBorderColor(colorBorder);
			 * cellCabecalho.setMinimumHeight(30f);
			 * cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 * cellCabecalho.setBackgroundColor(colorBackgroundCabecalhoDetalhe);
			 * cellCabecalho.setColspan(7); tablePendenciasBancarias.addCell(cellCabecalho);
			 *
			 * cellTituloColuna = new PdfPCell(); cellTituloColuna.setPaddingLeft(5f);
			 * cellTituloColuna.setBorderColor(colorBorder);
			 * cellTituloColuna.setMinimumHeight(30f);
			 * cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 * cellTituloColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			 * cellTituloColuna.setBackgroundColor(colorBackgroundTituloColunaDetalhe);
			 * cellTituloColuna.setPhrase(new Phrase("Contrato", fontTituloColuna));
			 * tablePendenciasBancarias.addCell(cellTituloColuna);
			 * cellTituloColuna.setPhrase(new Phrase("Modalidade", fontTituloColuna));
			 * tablePendenciasBancarias.addCell(cellTituloColuna);
			 * cellTituloColuna.setPhrase(new Phrase("Empresa", fontTituloColuna));
			 * tablePendenciasBancarias.addCell(cellTituloColuna);
			 * cellTituloColuna.setPhrase(new Phrase("Data", fontTituloColuna));
			 * tablePendenciasBancarias.addCell(cellTituloColuna);
			 * cellTituloColuna.setPhrase(new Phrase("Valor(R$)", fontTituloColuna));
			 * tablePendenciasBancarias.addCell(cellTituloColuna);
			 * cellTituloColuna.setPhrase(new Phrase("Avalista?", fontTituloColuna));
			 * tablePendenciasBancarias.addCell(cellTituloColuna);
			 * cellTituloColuna.setPhrase(new Phrase("Local", fontTituloColuna));
			 * tablePendenciasBancarias.addCell(cellTituloColuna);
			 *
			 * cellDadoColuna = new PdfPCell(); cellDadoColuna.setPaddingLeft(5f);
			 * cellDadoColuna.setBorderColor(colorBorder);
			 * cellDadoColuna.setMinimumHeight(30f);
			 * cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 * cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			 * cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			 * cellDadoColuna.setPhrase(new Phrase("LMC453315", fontDadoColuna));
			 * tablePendenciasBancarias.addCell(cellDadoColuna);
			 * cellDadoColuna.setPhrase(new Phrase("FINANCIAMENT", fontDadoColuna));
			 * tablePendenciasBancarias.addCell(cellDadoColuna);
			 * cellDadoColuna.setPhrase(new Phrase("SICOOB CREDICAPI", fontDadoColuna));
			 * tablePendenciasBancarias.addCell(cellDadoColuna);
			 * cellDadoColuna.setPhrase(new Phrase("02/09/2019", fontDadoColuna));
			 * tablePendenciasBancarias.addCell(cellDadoColuna);
			 * cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 * cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			 * cellDadoColuna.setPhrase(new Phrase("2.056,26", fontDadoColuna));
			 * tablePendenciasBancarias.addCell(cellDadoColuna);
			 * cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 * cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			 * cellDadoColuna.setPhrase(new Phrase("Não", fontDadoColuna));
			 * tablePendenciasBancarias.addCell(cellDadoColuna);
			 * cellDadoColuna.setPhrase(new Phrase("-", fontDadoColuna));
			 * tablePendenciasBancarias.addCell(cellDadoColuna);
			 * tablePendenciasBancarias.completeRow();
			 * document.add(tablePendenciasBancarias);
			 */



		} catch (Exception de) {
			de.printStackTrace();
		}
		document.close();
	}

	public static List<Arco> calcularArco(Graphics2D imagem, int posicaoCentroX, int posicaoCentroY, int raio) {

		// Este medodo é responsável por calcular os pontos para gerar os arcos da imagem.

		List<Arco> listArco = new ArrayList<Arco>();

		//int j = 0;

		for (int i = 178; i >= 0; i -= 3) {

			// a construção é calculada variando o angulo de 180o a 0o totalizando 60 sessões.
			// apenas por questão de ajuste grafico, é iniciado em 178o.

			double xd = Math.cos(1 * Math.PI / 180 * -i);
			double yd = Math.sin(1 * Math.PI / 180 * -i);

			int x = (int) (xd * raio + posicaoCentroX);
			int y = (int) (yd * raio + posicaoCentroY);

			Arco arco = new Arco();
			arco.setX(x);
			arco.setY(y);
			listArco.add(arco);

// Descomente para verificar os pontos em relação a imagem.
/*
			System.out.println("j: " + j++ + " i: " + i + " X: " + x + " - " + "Y: " + y);


				try {
				//imagem.setRGB(x, y, cor.getRGB());
					int xa = 5;
					int ya = 5;
					imagem.setColor(Color.RED);
					imagem.fillOval(x, y, xa, ya);


				}catch(Exception e) {

				}
*/
		}

		return listArco;
	}

}