package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I520 –  PARTICIPAÇÃO SOCIETÁRIA.
public class ModeloI52099 extends Modelo {

	ModeloI52099 modelo = null;
	List<ModeloI52099> listModelo  = null;

	public ModeloI52099() {
		this.iniciarRegistro();
	}

	public ModeloI52099(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI52099>();

		modelo = new ModeloI52099(1,1,4,"X","Tipo_Reg","Out","Registro I520 –  PARTICIPAÇÃO SOCIETÁRIA.");
		listModelo.add(modelo);

		modelo = new ModeloI52099(2,5,2,"X","Subtipo","Out","Subtipo de registro (fixo = '99').");
		listModelo.add(modelo);

		modelo = new ModeloI52099(4,7,80,"X","Mensagem","Out","Mensagem 'NÃO EXISTEM OCORRÊNCIAS'");
		listModelo.add(modelo);

		modelo = new ModeloI52099(5,87,29,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modelo);

	}

	public List<ModeloI52099> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI52099> listModelo) {
		this.listModelo = listModelo;
	}

}
