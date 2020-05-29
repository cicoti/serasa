package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I220 – Resumo de PEFIN.
public class ModeloI22000 extends Modelo {

	public ModeloI22000() {
		this.iniciarRegistro();
	}

	public ModeloI22000(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	ModeloI22000 modeloI220R = null;
	List<ModeloI22000> listModelo = null;

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI22000>();

		modeloI220R = new ModeloI22000(1,1,4,"X","Tipo_Reg","Out","Registro I220 – Resumo de PEFIN.");
		listModelo.add(modeloI220R);

		modeloI220R = new ModeloI22000(2,5,2,"X","Subtipo","Out","Subtipo de registro    (fixo = '00').");
		listModelo.add(modeloI220R);

		modeloI220R = new ModeloI22000(3,7,8,"N","Data_Inicial","Out","Data inicial , no formato AAAAMMDD.");
		listModelo.add(modeloI220R);

		modeloI220R = new ModeloI22000(4,15,8,"N","Data_Final","Out","Data final , no formato AAAAMMDD.");
		listModelo.add(modeloI220R);

		modeloI220R = new ModeloI22000(5,23,9,"N","Qtde_Total","Out","Quantidade total.");
		listModelo.add(modeloI220R);

		modeloI220R = new ModeloI22000(6,32,15,"N","Valor","Out","Valor da anotação mais recente, em reais, com 2 (duas) casas decimais.");
		listModelo.add(modeloI220R);

		modeloI220R = new ModeloI22000(7,47,1,"X","Tip-Ocor","Out","Tipo de ocorrência: 'V'  = PEFIN  '8'  = PEFIN INTERNO (Consultas na própria origem) ' '   = PEFIN DEVEDOR");
		listModelo.add(modeloI220R);

		modeloI220R = new ModeloI22000(8,48,16,"X","Origem","Out","Nome do banco ou credor da anotação mais recente.");
		listModelo.add(modeloI220R);

		modeloI220R = new ModeloI22000(9,64,52,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI220R);

	}

	public List<ModeloI22000> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI22000> listModelo) {
		this.listModelo = listModelo;
	}

}
