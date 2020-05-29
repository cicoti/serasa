package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloI12000 extends Modelo {

	ModeloI12000 modeloI12000 = null;
	List<ModeloI12000> listModelo  = null;

	public ModeloI12000() {
		this.iniciarRegistro();
	}

	public ModeloI12000(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI12000>();

		modeloI12000 = new ModeloI12000(1,1,4,"X","Tipo_Reg","Out","Registro I120 – Resumo de AÇÕES JUDICIAIS.");
		listModelo.add(modeloI12000);

		modeloI12000 = new ModeloI12000(2,5,2,"X","Subtipo","Out","Subtipo de registro (fixo = '00').");
		listModelo.add(modeloI12000);

		modeloI12000 = new ModeloI12000(3,7,8,"N","Data_Inicial","Out","Data inicial no formato AAAAMMDD.");
		listModelo.add(modeloI12000);

		modeloI12000 = new ModeloI12000(4,15,8,"N","Data_Final","Out","Data final no formato AAAAMMDD.");
		listModelo.add(modeloI12000);

		modeloI12000 = new ModeloI12000(5,23,9,"N","Qtde_Total","Out","Quantidade total.");
		listModelo.add(modeloI12000);

		modeloI12000 = new ModeloI12000(6,32,15,"N","Valor","Out","Valor da anotação mais recente, em reais, com 2 (duas) casas decimais.");
		listModelo.add(modeloI12000);

		modeloI12000 = new ModeloI12000(7,47,30,"X","Origem","Out","Nome da cidade de origem da anotação mais recente.");
		listModelo.add(modeloI12000);

		modeloI12000 = new ModeloI12000(8,77,39,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI12000);

	}

	public List<ModeloI12000> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI12000> listModelo) {
		this.listModelo = listModelo;
	}

}
