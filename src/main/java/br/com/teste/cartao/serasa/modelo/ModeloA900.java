package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Mensagens Informativas – Reg A900
public class ModeloA900 extends Modelo {

	public ModeloA900() {
		this.iniciarRegistro();
	}

	public ModeloA900(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
	}

	ModeloA900 modeloA900 = null;
	List<ModeloA900> listModelo = null;

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloA900>();

		modeloA900 = new ModeloA900(1,1,4,"X","TPREG","Out","Tipo de registro = A900");
		listModelo.add(modeloA900);

		modeloA900 = new ModeloA900(2,5,10,"N","COD MSG","Out","Código da mensagem  = 2024");
		listModelo.add(modeloA900);

		modeloA900 = new ModeloA900(3,11,32,"X","MENS REDUZ","Out","Mensagem reduzida");
		listModelo.add(modeloA900);

		modeloA900 = new ModeloA900(4,43,70,"X","MENS COMPL","Out","Mensagem completa : NADA CONSTA PARA O DOC CONSULTADO .  Este campo poderá retornar em branco.");
		listModelo.add(modeloA900);

		modeloA900 = new ModeloA900(5,113,3,"X","FILLER","Out","");
		listModelo.add(modeloA900);

	}

	public List<ModeloA900> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloA900> listModelo) {
		this.listModelo = listModelo;
	}

}
