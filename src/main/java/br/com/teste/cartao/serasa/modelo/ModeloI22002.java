package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloI22002 extends Modelo {


	public ModeloI22002() {
		this.iniciarRegistro();
	}

	public ModeloI22002(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	ModeloI22002 modeloI22002 = null;
	List<ModeloI22002> listModelo = null;

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI22002>();

		modeloI22002 = new ModeloI22002(1,1,4,"X","Tipo_Reg","Out","Registro I220 – Detalhe de PEFIN.");
		listModelo.add(modeloI22002);

		modeloI22002 = new ModeloI22002(2,5,2,"X","Subtipo","Out","Subtipo de registro    (fixo = '02').");
		listModelo.add(modeloI22002);

		modeloI22002 = new ModeloI22002(3,7,4,"N","Filial_CNPJ","Out","Número da filial do CNPJ consultado. Poderá conter zeros.");
		listModelo.add(modeloI22002);

		modeloI22002 = new ModeloI22002(4,11,2,"N","Dig_Doc","Out","Dígito verificador do documento consultado. Poderá conter zeros.");
		listModelo.add(modeloI22002);

		modeloI22002 = new ModeloI22002(5,13,8,"N","Data_Inclusão","Out","Data da Inclusão da anotação formato AAAAMMDD");
		listModelo.add(modeloI22002);

		modeloI22002 = new ModeloI22002(6,21,6,"N","Hora_Inclus","Out","Hora da Inclusão da anotação formato HHMMSS");
		listModelo.add(modeloI22002);

		modeloI22002 = new ModeloI22002(7,27,1,"X","Tip-Ocor","Out","Tipo de ocorrência: 'V'  = PEFIN '8'  = PEFIN INTERNO (Consultas na própria origem) ' ' = PEFIN DEVEDOR");
		listModelo.add(modeloI22002);

		modeloI22002 = new ModeloI22002(8,28,12,"X","Modalidade","Out","Modalidade");
		listModelo.add(modeloI22002);

		modeloI22002 = new ModeloI22002(9,40,76,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI22002);

	}

	public List<ModeloI22002> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI22002> listModelo) {
		this.listModelo = listModelo;
	}

}
