package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I520 –  PARTICIPAÇÃO SOCIETÁRIA.
public class ModeloI52000 extends Modelo {

	ModeloI52000 modelo = null;
	List<ModeloI52000> listModelo  = null;

	public ModeloI52000() {
		this.iniciarRegistro();
	}

	public ModeloI52000(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI52000>();

		modelo = new ModeloI52000(1,1,4,"X","Tipo_Reg","Out","Registro I520 –  PARTICIPAÇÃO SOCIETÁRIA");
		listModelo.add(modelo);

		modelo = new ModeloI52000(2,5,2,"X","Subtipo","Out","Subtipo de registro (fixo = '00').");
		listModelo.add(modelo);

		modelo = new ModeloI52000(3,7,70,"X","Nome","Out","Nome da empresa");
		listModelo.add(modelo);

		modelo = new ModeloI52000(4,77,14,"N","CNPJ","Out","CNPJ da empresa. ");
		listModelo.add(modelo);

		modelo = new ModeloI52000(5,91,4,"N","Nivel","Out","Nível de participação");
		listModelo.add(modelo);

		modelo = new ModeloI52000(6,95,8,"N","Data_Desde","Out","Data de fundacao no formato AAAAMMDD.");
		listModelo.add(modelo);

		modelo = new ModeloI52000(7,103,8,"N","Data_Atual","Out","Data de atualização no formato AAAAMMDD.");
		listModelo.add(modelo);

		modelo = new ModeloI52000(8,111,2,"X","UF","Out","UF");
		listModelo.add(modelo);

		modelo = new ModeloI52000(9,113,1,"X","RESTRICAO","Out","Participante tem restrição – S ou N");
		listModelo.add(modelo);

		modelo = new ModeloI52000(10,114,2,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modelo);

	}

	public List<ModeloI52000> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI52000> listModelo) {
		this.listModelo = listModelo;
	}

}
