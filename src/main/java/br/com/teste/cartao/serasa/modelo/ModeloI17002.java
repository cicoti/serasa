package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I170 – Detalhe de CCF (Cheques sem fundos – BB)
public class ModeloI17002 extends Modelo {

	ModeloI17002 modeloI17002 = null;
	List<ModeloI17002> listModelo  = null;

	public ModeloI17002() {
		this.iniciarRegistro();
	}

	public ModeloI17002(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI17002>();

		modeloI17002 = new ModeloI17002(1,1,4,"X","Tipo_Reg","Out","Registro I170 – Detalhe de CCF (Cheques sem fundos – BB)");
		listModelo.add(modeloI17002);

		modeloI17002 = new ModeloI17002(2,5,2,"X","Subtipo","Out","Subtipo de registro (fixo = '02').");
		listModelo.add(modeloI17002);

		modeloI17002 = new ModeloI17002(3,7,10,"X","CHV Cadus","Out","Chave cadus");
		listModelo.add(modeloI17002);

		modeloI17002 = new ModeloI17002(4,17,99,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI17002);



	}

	public List<ModeloI17002> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI17002> listModelo) {
		this.listModelo = listModelo;
	}

}
