package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro I100 – CONFIRMEI.
public class ModeloI10000 extends Modelo {

	ModeloI10000 modeloI10000 = null;
	List<ModeloI10000> listModelo  = null;

	public ModeloI10000() {
		this.iniciarRegistro();
	}

	public ModeloI10000(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI10000>();

		modeloI10000 = new ModeloI10000(1,1,4,"X","Tipo_Reg","Out","Registro I100 – CONFIRMEI.");
		listModelo.add(modeloI10000);

		modeloI10000 = new ModeloI10000(2,5,2,"X","Subtipo","Out","Subtipo de registro (fixo = '00').");
		listModelo.add(modeloI10000);

		modeloI10000 = new ModeloI10000(3,7,70,"X","Nome","Out","Nome obtido no Cadastro Base de CPF / CNPJ");
		listModelo.add(modeloI10000);

		modeloI10000 = new ModeloI10000(4,77,8,"X","Data_Confirm","Out","Data do nascimento para pessoa física ou data do CNPJ para pessoa jurídica, no formato DDMMAAAA. Pode conter zeros.");
		listModelo.add(modeloI10000);

		modeloI10000 = new ModeloI10000(5,85,1,"X","Cod_Sit_Cad","Out","Código da situação cadastral do documento na Base de CNPJ :0 (zero) = Inapta / 2 = ativa / 4 = nula / 6 = suspensa / 7 = baixada / 9 = cancelada ou	Código da situação cadastral do documento na Base de CPF : 	2 = regular / 3 = pendente de regularização / 4 = nula / 6 = suspensa / 7 = TITULAR FALECIDO / 9 = cancelada");
		listModelo.add(modeloI10000);

		modeloI10000 = new ModeloI10000(6,86,8,"X","Data_Sit","Out","Data da Situação do CPF / CNPJ no Cadastro Base de CPF / CNPJ.  No formato DDMMAAAA.");
		listModelo.add(modeloI10000);

		modeloI10000 = new ModeloI10000(7,94,2,"N","Cod_Retorno","Out","00 – Consulta feature Confirmei efetuada com sucesso");
		listModelo.add(modeloI10000);

		modeloI10000 = new ModeloI10000(8,96,1,"X","CCF_Indisp","Out","Uso da SERASA.");
		listModelo.add(modeloI10000);

		modeloI10000 = new ModeloI10000(9,97,19,"X","Filler","Out","Uso da SERASA.");
		listModelo.add(modeloI10000);
	}

	public List<ModeloI10000> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI10000> listModelo) {
		this.listModelo = listModelo;
	}

}
