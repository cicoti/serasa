package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Grafias - Reg I105
public class ModeloI10500 extends Modelo {

	ModeloI10500 modeloI105 = null;
	List<ModeloI10500> listModelo  = null;

	public ModeloI10500() {
		this.iniciarRegistro();
	}

	public ModeloI10500(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI10500>();

		modeloI105 = new ModeloI10500(1,1,4,"X","Tipo_Reg","Out","Registro I105 – GRAFIAS.");
		listModelo.add(modeloI105);

		modeloI105 = new ModeloI10500(2,5,2,"X","Subtipo","Out","Subtipo de registro (sequência da grafia iniciando de  “01” e acrescentando +1. Exemplo ‘01’,  ‘02’, ’03 , ...)");
		listModelo.add(modeloI105);

		modeloI105 = new ModeloI10500(3,7,70,"X","Nome","Out","Nome");
		listModelo.add(modeloI105);

		modeloI105 = new ModeloI10500(4,77,39,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI105);

	}

	public List<ModeloI10500> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI10500> listModelo) {
		this.listModelo = listModelo;
	}

/*
	Seq.	Inic	Byte	N/X	Mnemônico	In/Out	Descrição
	1		1		4		X	Tipo_Reg	Out		Registro I105 – GRAFIAS.
	2		5		2		X	Subtipo		Out		Subtipo de registro (sequência da grafia iniciando de  “01” e acrescentando +1. Exemplo ‘01’,  ‘02’, ’03 , ...).
	3		7		70		X	Nome		Out		Nome
	4		77		39		X	Filler		Out		Uso da SERASA.
*/

}
