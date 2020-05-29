package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I140 – Resumo de REFIN.
public class ModeloI14001 extends Modelo {

	ModeloI14001 modelo = null;
	List<ModeloI14001> listModelo  = null;

	public ModeloI14001() {
		this.iniciarRegistro();
	}

	public ModeloI14001(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI14001>();

		modelo = new ModeloI14001(1,1,4,"X","Tipo_Reg","Out","Registro I140 – Detalhe de REFIN.");
		listModelo.add(modelo);

		modelo = new ModeloI14001(2,5,2,"X","Subtipo","Out","Subtipo de registro (fixo = '01').");
		listModelo.add(modelo);

		modelo = new ModeloI14001(3,7,8,"N","Data_Ocorr","Out","Data da ocorrência no formato AAAAMMDD");
		listModelo.add(modelo);

		modelo = new ModeloI14001(4,15,3,"X","Natureza","Out","Código da natureza. (vide ANEXO II)");
		listModelo.add(modelo);

		modelo = new ModeloI14001(5,18,15,"N","CNPJ_origem","Out","CNPJ do participante. Ajustado à direita e preenchido com zeros à esquerda.");
		listModelo.add(modelo);

		modelo = new ModeloI14001(6,33,15,"N","Valor","Out","Valor da ocorrência (com 2 decimais)");
		listModelo.add(modelo);

		modelo = new ModeloI14001(7,48,4,"X","Praça","Out","Código da praça Embratel (localidade)");
		listModelo.add(modelo);

		modelo = new ModeloI14001(8,52,2,"X","UF","Out","Sigla da Unidade da Federação");
		listModelo.add(modelo);

		modelo = new ModeloI14001(9,54,20,"X","Nome_Empr","Out","Nome fantasia da empresa origem");
		listModelo.add(modelo);

		modelo = new ModeloI14001(10,74,30,"X","Cidade","Out","Nome da cidade (localidade)");
		listModelo.add(modelo);

		modelo = new ModeloI14001(11,104,1,"X","Principal","Out","S – principal N – avalista");
		listModelo.add(modelo);

		modelo = new ModeloI14001(12,105,1,"X","Serie Cadus","Out","Série da Chave Cadus. Poderá conter branco.");
		listModelo.add(modelo);

		modelo = new ModeloI14001(13,106,10,"X","CHV Cadus","Out","Chave cadus");
		listModelo.add(modelo);

	}

	public List<ModeloI14001> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI14001> listModelo) {
		this.listModelo = listModelo;
	}

}
