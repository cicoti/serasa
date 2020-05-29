package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloI22001 extends Modelo {

	public ModeloI22001() {
		this.iniciarRegistro();
	}

	public ModeloI22001(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	ModeloI22001 modeloI22001 = null;
	List<ModeloI22001> listModelo = null;

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI22001>();

		modeloI22001 = new ModeloI22001(1,1,4,"X","Tipo_Reg","Out","Registro I220 – Detalhe de PEFIN.");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(2,5,2,"X","Subtipo","Out","Subtipo de registro    (fixo = '01').");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(3,7,8,"N","Data_Ocorr","Out","Data da ocorrência no formato AAAAMMDD.");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(4,15,3,"X","Natureza","Out","Código da natureza. (vide ANEXO III)");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(5,18,15,"N","Valor","Out","Valor da ocorrência com 2 decimais.");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(6,33,4,"X","Praça","Out","Código da praça Embratel (localidade).");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(7,37,32,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(8,69,1,"X","Principal","Out","S – principal N – avalista.");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(9,70,16,"X","Contrato","Out","Código do contrato.");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(10,86,1,"X","Sub-Judice","Out","Anotação SUB JUDICE = 'S' normal  = '   ' (branco).");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(11,87,8,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(12,95,1,"X","Serie Cadus","Out","Série da Chave Cadus. Poderá conter branco.");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(13,96,10,"X","CHV Cadus","Out","Chave cadus.");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(14,106,1,"X","Tip-Ocor","Out","Tipo de ocorrência: 'V'  = PEFIN '8'  = PEFIN INTERNO (Consultas na própria origem) ' '   = PEFIN DEVEDOR.");
		listModelo.add(modeloI22001);

		modeloI22001 = new ModeloI22001(15,107,9,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI22001);

	}

	public List<ModeloI22001> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI22001> listModelo) {
		this.listModelo = listModelo;
	}

}
