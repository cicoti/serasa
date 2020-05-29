package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloI22003 extends Modelo {


	public ModeloI22003() {
		this.iniciarRegistro();
	}

	public ModeloI22003(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	ModeloI22003 modeloI22003 = null;
	List<ModeloI22003> listModelo = null;

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI22003>();

		modeloI22003 = new ModeloI22003(1,1,4,"X","Tipo_Reg","Out","Registro I220 Detalhe de PEFIN  - CREDOR.");
		listModelo.add(modeloI22003);

		modeloI22003 = new ModeloI22003(2,5,2,"X","Subtipo","Out","Subtipo de registro  (fixo = '03').");
		listModelo.add(modeloI22003);

		modeloI22003 = new ModeloI22003(3,7,14,"N","Docto","Out","Documento do credor");
		listModelo.add(modeloI22003);

		modeloI22003 = new ModeloI22003(4,21,70,"X","Nome","Out","Nome do credor");
		listModelo.add(modeloI22003);

		modeloI22003 = new ModeloI22003(5,91,16,"X","Filler","Out","Uso da SERASA");
		listModelo.add(modeloI22003);

		modeloI22003 = new ModeloI22003(6,107,1,"X","Tip-Ocor","Out","Tipo de ocorrência:,'V'  = PEFIN '8'  = PEFIN INTERNO (Consultas na própria origem) ' '   = PEFIN DEVEDOR");
		listModelo.add(modeloI22003);

		modeloI22003 = new ModeloI22003(7,108,1,"X","Cred-partic","Out","Quando existir documento do credor ‘C’  senão ‘P’");
		listModelo.add(modeloI22003);

		modeloI22003 = new ModeloI22003(8,109,7,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI22003);

	}

	public List<ModeloI22003> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI22003> listModelo) {
		this.listModelo = listModelo;
	}

}
