package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I140 – Resumo de REFIN.
public class ModeloI14000 extends Modelo {

	ModeloI14000 modeloI14000 = null;
	List<ModeloI14000> listModelo  = null;

	public ModeloI14000() {
		this.iniciarRegistro();
	}

	public ModeloI14000(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI14000>();

		modeloI14000 = new ModeloI14000(1,1,4,"X","Tipo_Reg","Out","Registro I140 – Resumo de REFIN.");
		listModelo.add(modeloI14000);

		modeloI14000 = new ModeloI14000(2,5,2,"X","Subtipo","Out","Subtipo de registro (fixo = '00').");
		listModelo.add(modeloI14000);

		modeloI14000 = new ModeloI14000(3,7,8,"N","Data_Inicial","Out","Data inicial no formato AAAAMMDD.");
		listModelo.add(modeloI14000);

		modeloI14000 = new ModeloI14000(4,15,8,"N","Data_Final","Out","Data final no formato AAAAMMDD.");
		listModelo.add(modeloI14000);

		modeloI14000 = new ModeloI14000(5,23,9,"N","Qtde_Total","Out","Quantidade total.");
		listModelo.add(modeloI14000);

		modeloI14000 = new ModeloI14000(6,32,15,"N","Valor","Out","Valor da anotação mais recente, em reais, com 2 (duas) casas decimais.");
		listModelo.add(modeloI14000);

		modeloI14000 = new ModeloI14000(7,47,16,"N","Origem","Out","Nome do banco ou credor da anotação mais recente");
		listModelo.add(modeloI14000);

		modeloI14000 = new ModeloI14000(8,63,53,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI14000);

	}

	public List<ModeloI14000> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI14000> listModelo) {
		this.listModelo = listModelo;
	}

}
