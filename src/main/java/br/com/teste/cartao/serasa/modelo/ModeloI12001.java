package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloI12001 extends Modelo {

	ModeloI12001 modeloI12001 = null;
	List<ModeloI12001> listModelo  = null;

	public ModeloI12001() {
		this.iniciarRegistro();
	}

	public ModeloI12001(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI12001>();

		modeloI12001 = new ModeloI12001(1,1,4,"X","Tipo_Reg","Out","Registro I120 – Detalhe de AÇÕES JUDICIAIS.");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(2,5,2,"X","Subtipo","Out","Subtipo de registro    (fixo = '01').");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(3,7,8,"N","Data_Ocorr","Out","Data da ocorrência no formato AAAAMMDD");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(4,15,4,"X","Vara_Civel","Out","Número da vara cível. Para vara única este campo contém 'UN'.");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(5,19,4,"X","Num_Distrib","Out","Número do distribuidor. Para distribuidor único este campo contém 'UN'");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(6,23,3,"N","Natureza","Out","Código da natureza (vide ANEXO I)");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(7,26,15,"X","Valor","Out","Valor da ação (com 2 decimais)");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(8,41,4,"X","Praça","Out","Código da praça Embratel (localidade)");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(9,45,2,"X","UF","Out","Sigla da Unidade da Federação");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(10,47,30,"X","Cidade","Out","Nome da cidade (localidade)");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(11,77,1,"N","Principal","Out","S – principal    N – coobrigado");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(12,78,1,"X","Sub_Judice","Out","Anotação SUB JUDICE = 'S' normal  = ' ' (branco)");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(13,79,4,"N","Filial_CNPJ","Out","Número da filial do CNPJ consultado. Poderá conter zeros.");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(14,83,2,"N","Dig_Doc","Out","Dígito verificador do documento consultado. Poderá conter zeros.");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(15,85,8,"N","Data_Inclusão","Out","Data da Inclusão da anotação, formato AAAAMMDD");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(16,93,6,"N","Hora_Inclus","Out","Hora da Inclusão da anotação, formato HHMMSS");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(17,99,10,"X","CHV Cadus","Out","Chave cadus");
		listModelo.add(modeloI12001);

		modeloI12001 = new ModeloI12001(18,109,7,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI12001);

	}

	public List<ModeloI12001> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI12001> listModelo) {
		this.listModelo = listModelo;
	}

}
