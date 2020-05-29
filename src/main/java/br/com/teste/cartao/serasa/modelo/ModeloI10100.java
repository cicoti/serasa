package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I101 – CONFIRMEI.
public class ModeloI10100 extends Modelo {

	ModeloI10100 modeloI10100 = null;
	List<ModeloI10100> listModelo  = null;

	public ModeloI10100() {
		this.iniciarRegistro();
	}

	public ModeloI10100(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI10100>();

		modeloI10100 = new ModeloI10100(1,1,4,"X","Tipo_Reg","Out","Registro I101 – CONFIRMEI.");
		listModelo.add(modeloI10100);

		modeloI10100 = new ModeloI10100(2,5,2,"X","Subtipo","Out","Subtipo de registro (fixo = '00').");
		listModelo.add(modeloI10100);

		modeloI10100 = new ModeloI10100(3,7,60,"X","Nome_Mãe","Out","Nome da mãe para pessoa física ou Nome Fantasia para pessoa jurídica. Poderá ser brancos.");
		listModelo.add(modeloI10100);

		modeloI10100 = new ModeloI10100(4,67,49,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI10100);

	}

	public List<ModeloI10100> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI10100> listModelo) {
		this.listModelo = listModelo;
	}

}
