package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I160 – Resumo de ACHEI + CCF.
public class ModeloI16000 extends Modelo {

	public ModeloI16000() {
		this.iniciarRegistro();
	}

	public ModeloI16000(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	ModeloI16000 modeloI160R = null;
	List<ModeloI16000> listModelo = null;

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI16000>();

		modeloI160R = new ModeloI16000(1,1,4,"X","Tipo_Reg","Out","Registro I160 – Resumo de ACHEI + CCF.");
		listModelo.add(modeloI160R);

		modeloI160R = new ModeloI16000(2,5,2,"X","Subtipo","Out","Subtipo de registro    (fixo = '00').");
		listModelo.add(modeloI160R);

		modeloI160R = new ModeloI16000(3,7,8,"N","Data_Inicial","Out","Data inicial, no formato AAAAMMDD.");
		listModelo.add(modeloI160R);

		modeloI160R = new ModeloI16000(4,15,8,"N","Data_Final","Out","Data final , no formato AAAAMMDD.");
		listModelo.add(modeloI160R);

		modeloI160R = new ModeloI16000(5,23,15,"N","Valor","Out","Valor da anotação mais recente,  em reais,  com 2 decimais.");
		listModelo.add(modeloI160R);

		modeloI160R = new ModeloI16000(6,38,9,"N","Qtde_Total","Out","Quantidade total.");
		listModelo.add(modeloI160R);

		modeloI160R = new ModeloI16000(7,47,16,"X","Origem","Out","Nome do banco da anotação mais recente.");
		listModelo.add(modeloI160R);

		modeloI160R = new ModeloI16000(8,63,53,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI160R);

	}

	public List<ModeloI16000> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI16000> listModelo) {
		this.listModelo = listModelo;
	}

}
