package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloI11000 extends Modelo {

	ModeloI11000 modeloI11000 = null;
	List<ModeloI11000> listModelo  = null;

	public ModeloI11000() {
		this.iniciarRegistro();
	}

	public ModeloI11000(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI11000>();

		modeloI11000 = new ModeloI11000(1,1,4,"X","Tipo_Reg","Out","Registro I110 – Resumo de PROTESTOS.");
		listModelo.add(modeloI11000);

		modeloI11000 = new ModeloI11000(2,5,2,"X","Subtipo","Out","Subtipo de registro (fixo = '00').");
		listModelo.add(modeloI11000);

		modeloI11000 = new ModeloI11000(3,7,8,"N","Data_Inicial","Out","Data inicial no formato AAAAMMDD.");
		listModelo.add(modeloI11000);

		modeloI11000 = new ModeloI11000(4,15,8,"N","Data_Final","Out","Data final no formato AAAAMMDD.");
		listModelo.add(modeloI11000);

		modeloI11000 = new ModeloI11000(5,23,9,"N","Qtde_Total","Out","Quantidade total.");
		listModelo.add(modeloI11000);

		modeloI11000 = new ModeloI11000(6,32,15,"N","Valor","Out","Valor da anotação mais recente, em reais, com 2 (duas) casas decimais.");
		listModelo.add(modeloI11000);

		modeloI11000 = new ModeloI11000(7,47,30,"X","ORIGEM","Out","Nome da cidade de origem da anotação mais recente");
		listModelo.add(modeloI11000);

		modeloI11000 = new ModeloI11000(8,77,39,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI11000);

	}

	public List<ModeloI11000> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI11000> listModelo) {
		this.listModelo = listModelo;
	}

}
