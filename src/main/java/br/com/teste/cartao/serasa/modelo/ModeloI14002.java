package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I140 – Resumo de REFIN.
public class ModeloI14002 extends Modelo {

	ModeloI14002 modelo = null;
	List<ModeloI14002> listModelo  = null;

	public ModeloI14002() {
		this.iniciarRegistro();
	}

	public ModeloI14002(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI14002>();

		modelo = new ModeloI14002(1,1,4,"X","Tipo_Reg","Out","Registro I140 – Detalhe de REFIN.");
		listModelo.add(modelo);

		modelo = new ModeloI14002(2,5,2,"X","Subtipo","Out","Subtipo de registro    (fixo = '02').");
		listModelo.add(modelo);

		modelo = new ModeloI14002(3,7,1,"X","Sub-judice","Out","Anotação SUB JUDICE = 'S' normal  = ' ' (branco)");
		listModelo.add(modelo);

		modelo = new ModeloI14002(4,8,4,"N","Filial_CNPJ","Out","Número da filial do CNPJ consultado. Poderá conter zeros.");
		listModelo.add(modelo);

		modelo = new ModeloI14002(5,12,2,"N","Dig_Doc","Out","Dígito verificador do documento consultado. Poderá conter zeros.");
		listModelo.add(modelo);

		modelo = new ModeloI14002(6,14,8,"N","Data_Inclusão","Out","Data da Inclusão da anotação, formato AAAAMMDD");
		listModelo.add(modelo);

		modelo = new ModeloI14002(7,22,6,"N","Hora_Inclus","Out","Hora da Inclusão da anotação, formato HHMMSS");
		listModelo.add(modelo);

		modelo = new ModeloI14002(8,28,16,"X","Contr","Out","Código do contrato");
		listModelo.add(modelo);

		modelo = new ModeloI14002(9,44,30,"X","Modalidade","Out","Descrição da modalidade (natureza)");
		listModelo.add(modelo);

		modelo = new ModeloI14002(10,74,42,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modelo);

	}

	public List<ModeloI14002> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI14002> listModelo) {
		this.listModelo = listModelo;
	}

}
