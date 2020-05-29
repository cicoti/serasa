package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloI12002 extends Modelo {

	ModeloI12002 modeloI12002 = null;
	List<ModeloI12002> listModelo  = null;

	public ModeloI12002() {
		this.iniciarRegistro();
	}

	public ModeloI12002(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI12002>();

		modeloI12002 = new ModeloI12002(1,1,4,"X","Tipo_Reg","Out","Registro I120 - Detalhe de AÇÕES JUDICIAIS");
		listModelo.add(modeloI12002);

		modeloI12002 = new ModeloI12002(2,5,2,"X","Subtipo","Out","Subtipo de registro    (fixo = '02').");
		listModelo.add(modeloI12002);

		modeloI12002 = new ModeloI12002(3,7,30,"X","Desc Natureza","Out","Descrição da natureza");
		listModelo.add(modeloI12002);

		modeloI12002 = new ModeloI12002(4,37,79,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI12002);

	}

	public List<ModeloI12002> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI12002> listModelo) {
		this.listModelo = listModelo;
	}

}
