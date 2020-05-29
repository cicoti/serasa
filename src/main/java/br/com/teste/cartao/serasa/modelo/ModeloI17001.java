package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I170 – Detalhe de CCF (Cheques sem fundos – BB)
public class ModeloI17001 extends Modelo {

	ModeloI17001 modeloI17001 = null;
	List<ModeloI17001> listModelo  = null;

	public ModeloI17001() {
		this.iniciarRegistro();
	}

	public ModeloI17001(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI17001>();

		modeloI17001 = new ModeloI17001(1,1,4,"X","Tipo_Reg","Out","Registro I170 – Detalhe de CCF (Cheques sem fundos – BB)");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(2,5,2,"X","Subtipo","Out","Subtipo de registro    (fixo = '01').");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(3,7,8,"N","Data_Ocorr","Out","Data da ocorrência , no formato AAAAMMDD");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(4,15,3,"N","Banco","Out","Código do banco");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(5,18,4,"N","Agência","Out","Código da agência");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(6,22,9,"N","Qtde_Cheq","Out","Quantidade de cheques para CCF");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(7,31,4,"X","Praça","Out","Código da praça Embratel (localidade)");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(8,35,2,"X","UF","Out","Sigla da Unidade da Federação");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(9,37,20,"X","Nome_Bco","Out","Nome fantasia do banco");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(10,57,30,"X","Nome_Cidade","Out","Nome da cidade (localidade)");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(11,87,2,"X","Natureza","Out","Código da natureza");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(12,89,4,"N","Filial_CNPJ","Out","Numero da filial");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(13,93,2,"N","Dig_Doc","Out","Digito de controle");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(14,95,8,"N","Data_Inclusão","Out","Data da Inclusão da anotação, formato AAAAMMDD");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(15,103,6,"N","Hora_Inclus","Out","Hora da Inclusão da anotação, formato HHMMSS");
		listModelo.add(modeloI17001);

		modeloI17001 = new ModeloI17001(16,109,7,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI17001);


	}

	public List<ModeloI17001> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI17001> listModelo) {
		this.listModelo = listModelo;
	}

}
