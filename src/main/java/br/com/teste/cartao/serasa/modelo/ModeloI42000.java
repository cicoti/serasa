package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I420 – CONCENTRE SCORING.
public class ModeloI42000 extends Modelo {

	ModeloI42000 modelo = null;
	List<ModeloI42000> listModelo  = null;

	public ModeloI42000() {
		this.iniciarRegistro();
	}

	public ModeloI42000(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI42000>();

		modelo = new ModeloI42000(1,1,4,"X","Tipo_Reg","Out","Registro I420 – CONCENTRE SCORING.");
		listModelo.add(modelo);

		modelo = new ModeloI42000(2,5,2,"X","Subtipo","Out","Subtipo de registro (fixo = '00').");
		listModelo.add(modelo);

		modelo = new ModeloI42000(3,7,2,"N","Cod_Retorno","Out","Código de retorno da Consulta. 00 – Consulta concluída OK, scoring calculado.  01 – Consulta concluída OK, scoring NÃO calculado.");
		listModelo.add(modelo);

		modelo = new ModeloI42000(4,9,4,"X","Cod_Scoring","Out","Código de Scoring solicitado.");
		listModelo.add(modelo);

		modelo = new ModeloI42000(5,13,6,"N","Pontuacao","Out","Pontuação. Pode ser zero.");
		listModelo.add(modelo);

		modelo = new ModeloI42000(6,19,78,"X","Scor_Mens","Out","Mensagem.");
		listModelo.add(modelo);

		modelo = new ModeloI42000(7,97,5,"N","Percentual","Out","Percentual com 2 decimais 999v99");
		listModelo.add(modelo);

		modelo = new ModeloI42000(8,102,14,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modelo);

	}

	public List<ModeloI42000> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI42000> listModelo) {
		this.listModelo = listModelo;
	}

}
