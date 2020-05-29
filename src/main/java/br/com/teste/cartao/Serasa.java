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
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

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

import br.com.teste.cartao.serasa.entidade.A900;
import br.com.teste.cartao.serasa.entidade.B49C;
import br.com.teste.cartao.serasa.entidade.I00100;
import br.com.teste.cartao.serasa.entidade.I10000;
import br.com.teste.cartao.serasa.entidade.I10100;
import br.com.teste.cartao.serasa.entidade.I10500;
import br.com.teste.cartao.serasa.entidade.I11000;
import br.com.teste.cartao.serasa.entidade.I11001;
import br.com.teste.cartao.serasa.entidade.I12000;
import br.com.teste.cartao.serasa.entidade.I12001;
import br.com.teste.cartao.serasa.entidade.I12002;
import br.com.teste.cartao.serasa.entidade.I14000;
import br.com.teste.cartao.serasa.entidade.I14001;
import br.com.teste.cartao.serasa.entidade.I14002;
import br.com.teste.cartao.serasa.entidade.I16000;
import br.com.teste.cartao.serasa.entidade.I17001;
import br.com.teste.cartao.serasa.entidade.I17002;
import br.com.teste.cartao.serasa.entidade.I22000;
import br.com.teste.cartao.serasa.entidade.I22001;
import br.com.teste.cartao.serasa.entidade.I22002;
import br.com.teste.cartao.serasa.entidade.I22003;
import br.com.teste.cartao.serasa.entidade.P002;
import br.com.teste.cartao.serasa.entidade.T999;
import br.com.teste.cartao.serasa.modelo.Modelo;
import br.com.teste.cartao.serasa.modelo.ModeloA900;
import br.com.teste.cartao.serasa.modelo.ModeloB49C;
import br.com.teste.cartao.serasa.modelo.ModeloI00100;
import br.com.teste.cartao.serasa.modelo.ModeloI10000;
import br.com.teste.cartao.serasa.modelo.ModeloI10100;
import br.com.teste.cartao.serasa.modelo.ModeloI10500;
import br.com.teste.cartao.serasa.modelo.ModeloI11000;
import br.com.teste.cartao.serasa.modelo.ModeloI11001;
import br.com.teste.cartao.serasa.modelo.ModeloI12000;
import br.com.teste.cartao.serasa.modelo.ModeloI12001;
import br.com.teste.cartao.serasa.modelo.ModeloI12002;
import br.com.teste.cartao.serasa.modelo.ModeloI14000;
import br.com.teste.cartao.serasa.modelo.ModeloI14001;
import br.com.teste.cartao.serasa.modelo.ModeloI14002;
import br.com.teste.cartao.serasa.modelo.ModeloI16000;
import br.com.teste.cartao.serasa.modelo.ModeloI17001;
import br.com.teste.cartao.serasa.modelo.ModeloI17002;
import br.com.teste.cartao.serasa.modelo.ModeloI22000;
import br.com.teste.cartao.serasa.modelo.ModeloI22001;
import br.com.teste.cartao.serasa.modelo.ModeloI22002;
import br.com.teste.cartao.serasa.modelo.ModeloI22003;
import br.com.teste.cartao.serasa.modelo.ModeloP002;
import br.com.teste.cartao.serasa.modelo.ModeloT999;
import br.com.teste.cartao.serasa.util.Util;

public class Serasa {

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

		String linhaResposa = null;


		/*
		BufferedReader bufferedReaderArquivoResposta = new BufferedReader(new FileReader ("D:\\silvio\\projetos2\\workspace\\CARTAO\\src\\main\\java\\br\\com\\teste\\cartao\\consulta_serasa_pj.txt"));

	    try {

	    	linhaResposa = bufferedReaderArquivoResposta.readLine();

	    } finally {
	    	bufferedReaderArquivoResposta.close();
	    }
	    */

        	HttpPost post = new HttpPost("https://mqlinuxext.serasa.com.br/Homologa/consultahttps");

        	String documento = "000000004933419";

        	//0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111122222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222223333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111
        	//0000000000000000000000000000000000000000000111111111122222222223333333333444444444455555555556666666666777777777788888888889999999999000000000011111111112222222222333333333344444444445555555555666666666677777777778888888888999999999900000000001111111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000111111111122222222223333333333444444444455555555556666666666777777777788888888889999999999000000000011111111112222222222333333333344444444445555555555666666666677777777778888888888999999999900000000001111110000000001111111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000111111
        	//0000000000000000000000000000000000123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123451234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345
        	//PF
        	//String p = "71645060AdvxH19@        B49C      000000000000272FC     FI                   S99SINIAN                               N                                                                                                                                                                                                                                                                                                                  P002RSPU                                                                                                           I00100RS                                                                                                           T999";

        	//PF - Cheque sem fundo
        	//String p = "71645060AdvxH19@        B49C      000000006280005FC     FI                   S99SINIAN                               N                                                                                                                                                                                                                                                                                                                  P002RSPU                                                                                                           I00100RS                                                                                                           T999";
        	String p = "71645060AdvxH19@        B49C      "+documento+"FC     FI                   S99SINIAN                               N                                                                                                                                                                                                                                                                                                                  P002RSPU                                                                                                           I00100RS                                                                                                           T999";


        	//PJ
        	//String p = "71645060AdvxH19@        B49C      000002220000154JC     FI                   S99SINIAN                               N                                                                                                                                                                                                                                                                                                                  P002RSPU                                                                                                           I00100R                                                                                                            T999";
        	List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("p", p));
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {
            	linhaResposa = EntityUtils.toString(response.getEntity());
            }

            System.out.println(">>>");
            System.out.println(linhaResposa);
            System.out.println("<<<");

		// B49C - PROTOCOLO
		B49C b49c = new B49C();
		//  I100 – CONFIRMEI.
		I10000 confirmeiI10000 = new I10000();
		// I101 - CONFIRMEI.
		I10100 confirmeiI10100 = new I10100();
		// I110 – Resumo de PROTESTOS.
		I11000 resumoProtestos = new I11000();
		// I120 – Resumo de AÇÕES JUDICIAIS.
		I12000 resumoAcoesJudiciais_I12000 = new I12000();
		// I140 - Resumo de RESTRIÇÃO FINANCEIRA - REFIN
		I14000 resumoREFIN_I14000 = new I14000();
		// I220 - Resumo de PENDENCIA FINANCEIRA - PEFIN
		I22000 resumoPEFIN_I22000 = null;


		// DETALHES
		ArrayList<I11001> listI11001 = new ArrayList<I11001>();
		ArrayList<I12001> listI12001 = new ArrayList<I12001>();
		ArrayList<I12002> listI12002 = new ArrayList<I12002>();
		ArrayList<I14001> listI14001 = new ArrayList<I14001>();
		ArrayList<I14002> listI14002 = new ArrayList<I14002>();
		ArrayList<I17001> listI17001 = new ArrayList<I17001>();
		ArrayList<I17002> listI17002 = new ArrayList<I17002>();

		// I22001 - Detalhe da PENDENCIA FINANCEIRA - PEFIN
		I22001 detalhePEFIN_I22001 = null;
		// I22002 - Detalhe da PENDENCIA FINANCEIRA - PEFIN
		I22002 detalhePEFIN_I22002 = null;
		// I22003 - Detalhe da PENDENCIA FINANCEIRA - PEFIN - CREDOR
		I22003 detalhePEFIN_I22003 = null;

	    Modelo modelo = null;
	    String tipoRegistro = null;
	    String registro = null;
	    String subtipo = null;

	    String texto = "";

	    boolean fimProcessamento = false;

	    while(fimProcessamento==false) {

	    	try {

	    		tipoRegistro = linhaResposa.substring(0,4);

		    	subtipo = linhaResposa.substring(4,6);

			    if(tipoRegistro.charAt(0)=='I') {

			    	registro = tipoRegistro.concat(subtipo).trim();

			    }else {

			    	registro = tipoRegistro.trim();

			    }

			    modelo = recuperarModelo(registro);

			    String linha = linhaResposa.substring(0, modelo.getPosicaoInicial()+modelo.getBytes()-1);

			    texto = texto + linha + "\n";

			    if(registro.equals("B49C")){

					ModeloB49C modeloB49C = new ModeloB49C();
					List<ModeloB49C> listaModeloB49C = modeloB49C.getListModelo();
					b49c = new B49C();
					popularObjeto(" ".concat(linha), b49c, listaModeloB49C.toArray());
					System.out.println(listaModeloB49C.get(0).getDescricao().concat(" ").concat(listaModeloB49C.get(1).getDescricao()));

	    		}

			    if(registro.equals("P002")){

					ModeloP002 modeloP002 = new ModeloP002();
					List<ModeloP002> listaModeloP002 = modeloP002.getListModelo();
					P002 p002 = new P002();
					popularObjeto(" ".concat(linha), p002, listaModeloP002.toArray());
					System.out.println(listaModeloP002.get(0).getDescricao().concat(" ").concat(listaModeloP002.get(1).getDescricao()));

	    		}

			    if(registro.equals("T999")){

					ModeloT999 modeloT999 = new ModeloT999();
					List<ModeloT999> listaModeloT999 = modeloT999.getListModelo();
					T999 t999 = new T999();
					popularObjeto(" ".concat(linha), t999, listaModeloT999.toArray());
					System.out.println(listaModeloT999.get(0).getDescricao().concat(" ").concat(listaModeloT999.get(1).getDescricao()));
					fimProcessamento = true;
	    		}

			    if(registro.equals("A900")){

					ModeloA900 modeloA900 = new ModeloA900();
					List<ModeloA900> listaModeloA900 = modeloA900.getListModelo();
					A900 a900 = new A900();
					popularObjeto(" ".concat(linha), a900, listaModeloA900.toArray());
					System.out.println(listaModeloA900.get(0).getDescricao().concat(" ").concat(listaModeloA900.get(1).getDescricao()));

			    }

			    if(registro.equals("I00100")){

					ModeloI00100 modeloI00100 = new ModeloI00100();
					List<ModeloI00100> listaModeloI00100 = modeloI00100.getListModelo();
					I00100 i00100 = new I00100();
					popularObjeto(" ".concat(linha), i00100, listaModeloI00100.toArray());
					System.out.println(listaModeloI00100.get(0).getDescricao().concat(" ").concat(listaModeloI00100.get(1).getDescricao()));

	    		}

			    if(registro.equals("I10000")){

			    	ModeloI10000 modeloI10000 = new ModeloI10000();
					List<ModeloI10000> listaModeloI10000 = modeloI10000.getListModelo();
					popularObjeto(" ".concat(linha), confirmeiI10000, listaModeloI10000.toArray());
					System.out.println(listaModeloI10000.get(0).getDescricao().concat(" ").concat(listaModeloI10000.get(1).getDescricao()));

	    		}

			    if(registro.equals("I10100")){

			    	ModeloI10100 modeloI10100 = new ModeloI10100();
					List<ModeloI10100> listaModeloI10100 = modeloI10100.getListModelo();
					popularObjeto(" ".concat(linha), confirmeiI10100, listaModeloI10100.toArray());
					System.out.println(listaModeloI10100.get(0).getDescricao().concat(" ").concat(listaModeloI10100.get(1).getDescricao()));

	    		}

			    if(registro.equals("I11000")){

			    	ModeloI11000 modeloI11000 = new ModeloI11000();
					List<ModeloI11000> listaModeloI11000 = modeloI11000.getListModelo();
					popularObjeto(" ".concat(linha), resumoProtestos, listaModeloI11000.toArray());
					System.out.println(listaModeloI11000.get(0).getDescricao().concat(" ").concat(listaModeloI11000.get(1).getDescricao()));

	    		}

			    if(registro.equals("I11001")){

			    	ModeloI11001 modeloI11001 = new ModeloI11001();
					List<ModeloI11001> listaModeloI11001 = modeloI11001.getListModelo();
					I11001 i11001 = new I11001();
					popularObjeto(" ".concat(linha), i11001, listaModeloI11001.toArray());
					System.out.println(listaModeloI11001.get(0).getDescricao().concat(" ").concat(listaModeloI11001.get(1).getDescricao()));
					listI11001.add(i11001);

	    		}

			    if(registro.equals("I12000")){

			    	ModeloI12000 modeloI12000 = new ModeloI12000();
					List<ModeloI12000> listaModeloI12000 = modeloI12000.getListModelo();
					popularObjeto(" ".concat(linha), resumoAcoesJudiciais_I12000, listaModeloI12000.toArray());
					System.out.println(listaModeloI12000.get(0).getDescricao().concat(" ").concat(listaModeloI12000.get(1).getDescricao()));
	    		}

			    if(registro.equals("I12001")){

			    	ModeloI12001 modeloI12001 = new ModeloI12001();
					List<ModeloI12001> listaModeloI12001 = modeloI12001.getListModelo();
					I12001 i12001 = new I12001();
					popularObjeto(" ".concat(linha), i12001, listaModeloI12001.toArray());
					System.out.println(listaModeloI12001.get(0).getDescricao().concat(" ").concat(listaModeloI12001.get(1).getDescricao()));
					listI12001.add(i12001);
	    		}

			    if(registro.equals("I12002")){

			    	ModeloI12002 modeloI12002 = new ModeloI12002();
					List<ModeloI12002> listaModeloI12002 = modeloI12002.getListModelo();
					I12002 i12002 = new I12002();
					popularObjeto(" ".concat(linha), i12002, listaModeloI12002.toArray());
					System.out.println(listaModeloI12002.get(0).getDescricao().concat(" ").concat(listaModeloI12002.get(1).getDescricao()));
					listI12002.add(i12002);
	    		}

			    if(registro.equals("I14000")){

			    	ModeloI14000 modeloI14000 = new ModeloI14000();
					List<ModeloI14000> listaModeloI14000 = modeloI14000.getListModelo();
					popularObjeto(" ".concat(linha), resumoREFIN_I14000, listaModeloI14000.toArray());
					System.out.println(listaModeloI14000.get(0).getDescricao().concat(" ").concat(listaModeloI14000.get(1).getDescricao()));

	    		}

			    if(registro.equals("I14001")){

			    	ModeloI14001 modeloI14001 = new ModeloI14001();
					List<ModeloI14001> listaModeloI14001 = modeloI14001.getListModelo();
					I14001 i14001 = new I14001();
					popularObjeto(" ".concat(linha), i14001, listaModeloI14001.toArray());
					System.out.println(listaModeloI14001.get(0).getDescricao().concat(" ").concat(listaModeloI14001.get(1).getDescricao()));
					listI14001.add(i14001);
	    		}

			    if(registro.equals("I14002")){

			    	ModeloI14002 modeloI14002 = new ModeloI14002();
					List<ModeloI14002> listaModeloI14002 = modeloI14002.getListModelo();
					I14002 i14002 = new I14002();
					popularObjeto(" ".concat(linha), i14002, listaModeloI14002.toArray());
					System.out.println(listaModeloI14002.get(0).getDescricao().concat(" ").concat(listaModeloI14002.get(1).getDescricao()));
					listI14002.add(i14002);
	    		}

			    if(registro.contentEquals("I17001")) {

			    	ModeloI17001 modeloI17001 = new ModeloI17001();
					List<ModeloI17001> listaModeloI17001 = modeloI17001.getListModelo();
					I17001 i17001 = new I17001();
					popularObjeto(" ".concat(linha), i17001 , listaModeloI17001.toArray());
					System.out.println(listaModeloI17001.get(0).getDescricao().concat(" ").concat(listaModeloI17001.get(1).getDescricao()));
					listI17001.add(i17001);

			    }

			    if(registro.contentEquals("I17002")) {

			    	ModeloI17002 modeloI17002 = new ModeloI17002();
					List<ModeloI17002> listaModeloI17002 = modeloI17002.getListModelo();
					I17002 i17002 = new I17002();
					popularObjeto(" ".concat(linha), i17002 , listaModeloI17002.toArray());
					System.out.println(listaModeloI17002.get(0).getDescricao().concat(" ").concat(listaModeloI17002.get(1).getDescricao()));
					listI17002.add(i17002);

			    }

			    if(registro.equals("I22000")){ // RESUMO PEFIN

			    	ModeloI22000 modeloI22000 = new ModeloI22000();
					List<ModeloI22000> listaModeloI22000 = modeloI22000.getListModelo();
					resumoPEFIN_I22000 = new I22000();
					popularObjeto(" ".concat(linha), resumoPEFIN_I22000, listaModeloI22000.toArray());
					System.out.println(listaModeloI22000.get(0).getDescricao().concat(" ").concat(listaModeloI22000.get(1).getDescricao()));

	    		}

			    if(registro.equals("I22001")){ // DETALHE PEFIN

			    	ModeloI22001 modeloI22001 = new ModeloI22001();
					List<ModeloI22001> listaModeloI22001 = modeloI22001.getListModelo();
					detalhePEFIN_I22001 = new I22001();
					popularObjeto(" ".concat(linha), detalhePEFIN_I22001, listaModeloI22001.toArray());
					System.out.println(listaModeloI22001.get(0).getDescricao().concat(" ").concat(listaModeloI22001.get(1).getDescricao()));

			    }

			    if(registro.equals("I22002")){

			    	ModeloI22002 modeloI22002 = new ModeloI22002();
					List<ModeloI22002> listaModeloI22002 = modeloI22002.getListModelo();
					detalhePEFIN_I22002 = new I22002();
					popularObjeto(" ".concat(linha), detalhePEFIN_I22002, listaModeloI22002.toArray());
					System.out.println(listaModeloI22002.get(0).getDescricao().concat(" ").concat(listaModeloI22002.get(1).getDescricao()));
	    		}

			    if(registro.equals("I22003")){

			    	ModeloI22003 modeloI22003 = new ModeloI22003();
					List<ModeloI22003> listaModeloI22003 = modeloI22003.getListModelo();
					detalhePEFIN_I22003 = new I22003();
					popularObjeto(" ".concat(linha), detalhePEFIN_I22003, listaModeloI22003.toArray());
					System.out.println(listaModeloI22003.get(0).getDescricao().concat(" ").concat(listaModeloI22003.get(1).getDescricao()));
	    		}

			    linhaResposa = linhaResposa.substring(linha.length());

	    	}catch(Exception e) {
	    		System.out.println("Ocorreu um erro!");
	    		e.printStackTrace();
	    		break;
	    	}

	    };

	    System.out.println("FIM DO PROCESSAMENTO DO ARQUIVO!");

	    System.out.println(texto);


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
			cellTituloData.setPhrase(new Phrase(new Util().retornaDataHoraCabecalho(), fontData));
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
			cellDadoColuna.setPhrase(new Phrase(confirmeiI10000.getSeq03_nome(), fontDadoColuna));
			tableIdentificacao.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase(new Util().formataCPF(b49c.getSeq03_num_doc()), fontDadoColuna));
			tableIdentificacao.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase(new Util().formataDataPadraoSerasa(confirmeiI10000.getSeq04_data_confirm()), fontDadoColuna));
			tableIdentificacao.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase(new Util().retornaTraco(confirmeiI10100.getSeq03_nome_mae()), fontDadoColuna));
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
			cellDadoColuna.setPhrase(new Phrase("Situação do CPF em " + new Util().formataDataPadraoSerasa(confirmeiI10000.getSeq06_data_sit()) + " : " +  new Util().situacaoCPF(confirmeiI10000.getSeq05_cod_sit_cad()), fontDadoColuna));
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
			// RESUMO PENDENCIAS FINANCIEIRA - PEFIN
			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Pendências Comerciais (PEFIN)", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase(new Util().removeZerosEsquerda(resumoPEFIN_I22000.getSeq05_qtde_total()), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase(new Util().formataDataResumo(resumoPEFIN_I22000.getSeq03_data_inicial()).concat(" a ").concat(new Util().formataDataResumo(resumoPEFIN_I22000.getSeq04_data_final())), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase(new Util().formatarValorMonetario(resumoPEFIN_I22000.getSeq06_valor()), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase(new Util().formataTexto(resumoPEFIN_I22000.getSeq08_origem()), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);

			// Linha 2
			// RESUMO RESTRICAO FINANCEIRA - REFIN
			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);
			cellDadoColuna.setPhrase(new Phrase("Restrição Financeira (REFIN)", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase(new Util().removeZerosEsquerda(resumoREFIN_I14000.getSeq05_qtde_total()), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase(new Util().formataDataResumo(resumoREFIN_I14000.getSeq03_data_inicial()).concat(" a ").concat(new Util().formataDataResumo(resumoREFIN_I14000.getSeq04_data_final())), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase(new Util().formatarValorMonetario(resumoREFIN_I14000.getSeq06_valor()), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase(new Util().formataTexto(resumoREFIN_I14000.getSeq07_origem()), fontDadoColuna));
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

			// Linha 4
			// RESUMO PROTESTOS
			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColunaZebra);
			cellDadoColuna.setPhrase(new Phrase("Protestos", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase(new Util().removeZerosEsquerda(resumoProtestos.getSeq05_qtde_total()), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase(new Util().formataDataResumo(resumoProtestos.getSeq03_data_inicial()).concat(" a ").concat(new Util().formataDataResumo(resumoProtestos.getSeq04_data_final())) , fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase(new Util().formatarValorMonetario(resumoProtestos.getSeq06_valor()), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase(new Util().formataTexto(resumoProtestos.getSeq07_origem()), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);

			// Linha 5
			// RESUMO ACOES JUDICIAIS
			cellDadoColuna = new PdfPCell();
			cellDadoColuna.setPaddingLeft(5f);
			cellDadoColuna.setBorderColor(colorBorder);
			cellDadoColuna.setMinimumHeight(30f);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
			cellDadoColuna.setPhrase(new Phrase("Ações Judiciais", fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase(new Util().removeZerosEsquerda(resumoAcoesJudiciais_I12000.getSeq05_qtde_total()), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setPhrase(new Phrase(new Util().formataDataResumo(resumoAcoesJudiciais_I12000.getSeq03_data_inicial()).concat(" a ").concat(new Util().formataDataResumo(resumoAcoesJudiciais_I12000.getSeq04_data_final())), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellDadoColuna.setPhrase(new Phrase(new Util().formatarValorMonetario(resumoAcoesJudiciais_I12000.getSeq06_valor()), fontDadoColuna));
			tableAnotacoesNegativas.addCell(cellDadoColuna);
			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellDadoColuna.setPhrase(new Phrase(new Util().formataTexto(resumoAcoesJudiciais_I12000.getSeq07_origem()), fontDadoColuna));
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

            if(resumoPEFIN_I22000!=null) {

            	// PENDENCIAS COMERCIAIS - PEFIN = PENDENCIAS FINANCEIRAS

            	PdfPTable tablePendenciasComerciais = new PdfPTable(7);
            	tablePendenciasComerciais.setWidthPercentage(100);
            	tablePendenciasComerciais.setWidths(new int[] { 20, 18, 20, 12, 12, 10, 8 });
    			cellCabecalho = new PdfPCell(new Phrase("Pendências Comerciais (PEFIN)", fontCabecalhoDetalhe));
    			cellCabecalho.setPaddingLeft(5f);
    			cellCabecalho.setBorderColor(colorBorder);
    			cellCabecalho.setMinimumHeight(30f);
    			cellCabecalho.setVerticalAlignment(Element.ALIGN_MIDDLE);
    			cellCabecalho.setBackgroundColor(colorBackgroundCabecalhoDetalhe);
    			cellCabecalho.setColspan(7);
    			tablePendenciasComerciais.addCell(cellCabecalho);

    			cellTituloColuna = new PdfPCell();
    			cellTituloColuna.setPaddingLeft(5f);
    			cellTituloColuna.setBorderColor(colorBorder);
    			cellTituloColuna.setMinimumHeight(30f);
    			cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
    			cellTituloColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
    			cellTituloColuna.setBackgroundColor(colorBackgroundTituloColunaDetalhe);
    			cellTituloColuna.setPhrase(new Phrase("Contrato", fontTituloColuna));
    			tablePendenciasComerciais.addCell(cellTituloColuna);
    			cellTituloColuna.setPhrase(new Phrase("Modalidade", fontTituloColuna));
    			tablePendenciasComerciais.addCell(cellTituloColuna);
    			cellTituloColuna.setPhrase(new Phrase("Empresa", fontTituloColuna));
    			tablePendenciasComerciais.addCell(cellTituloColuna);
    			cellTituloColuna.setPhrase(new Phrase("Data", fontTituloColuna));
    			tablePendenciasComerciais.addCell(cellTituloColuna);
    			cellTituloColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
    			cellTituloColuna.setHorizontalAlignment(Element.ALIGN_CENTER);
    			cellTituloColuna.setPhrase(new Phrase("Valor\n(R$)", fontTituloColuna));
    			tablePendenciasComerciais.addCell(cellTituloColuna);
    			cellTituloColuna.setPhrase(new Phrase("Avalista", fontTituloColuna));
    			tablePendenciasComerciais.addCell(cellTituloColuna);
    			cellTituloColuna.setPhrase(new Phrase("Local", fontTituloColuna));
    			tablePendenciasComerciais.addCell(cellTituloColuna);



	    			cellDadoColuna = new PdfPCell();
	    			cellDadoColuna.setPaddingLeft(5f);
	    			cellDadoColuna.setBorderColor(colorBorder);
	    			cellDadoColuna.setMinimumHeight(30f);
	    			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
	    			cellDadoColuna.setBackgroundColor(colorBackgroundDadoColuna);
	    			// CONTRATO
	    			cellDadoColuna.setPhrase(new Phrase(detalhePEFIN_I22001.getSeq09_contrato(), fontDadoColuna));
	    			tablePendenciasComerciais.addCell(cellDadoColuna);
	    			// MODALIDADE
	    			cellDadoColuna.setPhrase(new Phrase(detalhePEFIN_I22002.getSeq08_modalidade(), fontDadoColuna));
	    			tablePendenciasComerciais.addCell(cellDadoColuna);
	    			// EMPRESA
	    			cellDadoColuna.setPhrase(new Phrase(detalhePEFIN_I22003.getSeq04_nome(), fontDadoColuna));
	    			tablePendenciasComerciais.addCell(cellDadoColuna);
	    			//DATA
	    			cellDadoColuna.setPhrase(new Phrase(new Util().formataDataPadrao(detalhePEFIN_I22001.getSeq03_data_ocorr()), fontDadoColuna));
	    			tablePendenciasComerciais.addCell(cellDadoColuna);
	    			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    			//VALOR
	    			cellDadoColuna.setPhrase(new Phrase(new Util().formatarValorMonetario(detalhePEFIN_I22001.getSeq05_valor()), fontDadoColuna));
	    			tablePendenciasComerciais.addCell(cellDadoColuna);
	    			cellDadoColuna.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    			cellDadoColuna.setHorizontalAlignment(Element.ALIGN_LEFT);
	    			//AVALISTA
	    			cellDadoColuna.setPhrase(new Phrase(new Util().retornaTraco(detalhePEFIN_I22001.getSeq08_principal()), fontDadoColuna));
	    			tablePendenciasComerciais.addCell(cellDadoColuna);
	    			// LOCAL
	    			cellDadoColuna.setPhrase(new Phrase(new Util().retornaTraco(detalhePEFIN_I22001.getSeq06_praca()), fontDadoColuna));
	    			tablePendenciasComerciais.addCell(cellDadoColuna);


    			document.add(tablePendenciasComerciais);
    			tablePendenciasComerciais.completeRow();

            }


            /*
            // PENDENCIAS BANCARIAS - REFIN = RESTRICOES FINANCEIRAS

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

			*/

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