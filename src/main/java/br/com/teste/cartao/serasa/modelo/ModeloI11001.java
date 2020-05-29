package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloI11001 extends Modelo {

	ModeloI11001 modeloI11001 = null;
	List<ModeloI11001> listModelo  = null;

	public ModeloI11001() {
		this.iniciarRegistro();
	}

	public ModeloI11001(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI11001>();

		modeloI11001 = new ModeloI11001(1,1,4,"X","Tipo_Reg","Out","Registro I110 – Detalhe de PROTESTOS.");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(2,5,2,"X","Subtipo","Out","Subtipo de registro    (fixo = '01').");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(3,7,8,"N","Data_Ocorr","Out","Data da anotação no formato AAAAMMDD.");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(4,15,4,"X","Num_Cartor","Out","Número do cartório.  Para cartório único,  este campo contem 'UN'");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(5,19,3,"X","Natureza","Out","Código da Natureza.  Pode estar em branco.");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(6,22,15,"N","Valor","Out","Valor da anotação, em Reais, com 2 posições decimais.");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(7,37,4,"X","Praça","Out","Código da praça localidade do cartório.");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(8,41,2,"X","UF","Out","UF sigla da unidade da federação da localidade do cartório.");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(9,43,30,"X","Cidade","Out","Nome da localidade do cartório.");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(10,73,1,"X","Sub_Judice","Out","'  ' branco)  =  dívida não contestada. 'S' =  dívida sub-júdice / contestação judicial. 'C' = recebimento docs. do credor ref. pagto. da dívida;  título permanece protestado 'E' = recebimento carta de anuência eletrônica do credor ref. pagto. da dívida; título permanece protestado 'D' = recebimento docs. do devedor ref. pagto. da  dívida; título permanece protestado");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(11,74,8,"N","Data_Carta","Out","Data de recebimento da carta de anuência , no formato AAAAMMDD, se campo Sub_Judice for 'C', 'E' ou 'D'.");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(12,82,4,"N","Filial_CNPJ","Out","Número da filial do CNPJ consultado. Poderá conter zeros.");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(13,86,2,"N","Dig_Doc","Out","Dígito verificador do documento consultado. Poderá conter zeros.");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(14,88,8,"N","Data_Inclusão","Out","Data da Inclusão da anotação, formato AAAAMMDD");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(15,96,6,"N","Hora_Inclus","Out","Hora da Inclusão da anotação, formato HHMMSS");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(16,102,10,"X","CHV Cadus","Out","Chave cadus");
		listModelo.add(modeloI11001);

		modeloI11001 = new ModeloI11001(17,112,4,"X","Filler","Out","Filler");
		listModelo.add(modeloI11001);

	}

	public List<ModeloI11001> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI11001> listModelo) {
		this.listModelo = listModelo;
	}

}
