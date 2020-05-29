package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Trailer - Reg T999
public class ModeloT999 extends Modelo {

	public ModeloT999() {
		this.iniciarRegistro();
	}

	public ModeloT999(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
	}

	ModeloT999 modeloT999 = null;
	List<ModeloT999> listModelo = null;

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloT999>();

		modeloT999 = new ModeloT999(1,1,4,"X","In/Out","TIPO-REG","Registro:  T999 - MENSAGEM");
		listModelo.add(modeloT999);

		modeloT999 = new ModeloT999(2,5,3,"N","In/Out","CÓDIGO","Código da mensagem");
		listModelo.add(modeloT999);

		modeloT999 = new ModeloT999(3,8,70,"X","In/Out","MENSAGEM","Mensagem (ver Tabela de Código de Mensagem)");
		listModelo.add(modeloT999);

		modeloT999 = new ModeloT999(4,78,38,"X","In/Out","FILLER","");
		listModelo.add(modeloT999);

	}

	public List<ModeloT999> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloT999> listModelo) {
		this.listModelo = listModelo;
	}

}
