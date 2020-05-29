package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Protocolo B49C
public class ModeloB49C extends Modelo {

	public ModeloB49C() {
		this.iniciarRegistro();
	}

	public ModeloB49C(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
	}

	ModeloB49C b49c = null;
	List<ModeloB49C> listModelo = null;

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloB49C>();

		b49c = new ModeloB49C(1,1,4,"X","FILLER","IN","Preencher com 'B49C'");
		listModelo.add(b49c);

		b49c = new ModeloB49C(2,5,6,"X","FILLER","IN","Preencher com o código da estação chamadora no cliente (identificação de terminal ou computador). Campo opcional.");
		listModelo.add(b49c);

		b49c = new ModeloB49C(3,11,15,"N","NUM DOC","IN","Informar o número do CPF (com 11 dígitos) ou o número do CNPJ (com 15 dígitos) a ser consultado. Ajuste à direita e preenchido com zeros à esquerda.");
		listModelo.add(b49c);

		b49c = new ModeloB49C(4,26,1,"X","TIPO PESSOA","IN","Informar 'F' = pessoa física;      'J' = pessoa jurídica.");
		listModelo.add(b49c);

		b49c = new ModeloB49C(5,27,6,"X","BASE CONS","IN","Informar 'C'");
		listModelo.add(b49c);

		b49c = new ModeloB49C(6,33,2,"X","MODALIDADE","IN","Informar 'FI'");
		listModelo.add(b49c);

		b49c = new ModeloB49C(7,35,7,"N","VLR CONSUL","IN","Uso da SERASA");
		listModelo.add(b49c);

		b49c = new ModeloB49C(8,42,12,"X","CENTRO CUST","IN","Informar Centro de Custo Obs.: Como é opcional, informar brancos se não for usar esse dado.");
		listModelo.add(b49c);

		b49c = new ModeloB49C(9,54,1,"X","CODIFICADO","IN","Informar 'S'");
		listModelo.add(b49c);

		b49c = new ModeloB49C(10,55,2,"N","QTD REG","IN","Indicar a quantidade de registros por transmissão de dados que deseja receber (até 99).");
		listModelo.add(b49c);

		b49c = new ModeloB49C(11,57,1,"X","CONVERSA","IN","Informar se deseja diálogo de comunicação? (S/N) Obs.: > Quando a resposta à pergunta for 'N', o sistema enviará até 31 registros (recebidos e consultados), além do protocolo e encerrará a consulta ao CPF/CNPJ solicitado. > Quando a resposta for 'S', o sistema vai devolver o Protocolo a cada consulta, informando 'FIM' na seqüência 12 se todos os dados do CPF/CNPJ consultado foram enviados, 'CON' se houver mais dados sobre o CPF/CNPJ. O código 'ERR' significa que houve erro e, nesse caso, o sistema devolve o Protocolo e o registro T999 com códigos e mensagens de erro.");
		listModelo.add(b49c);

		b49c = new ModeloB49C(12,58,3,"X","FUNÇÃO","IN","Usar as funções: INI, CON, ENC, ERR, FIM. Informar 'INI' para iniciar uma nova consulta de CPF/CNPJ, 'CON' para receber a continuação do retorno de dados de um CPF/CNPJ  e 'ENC' para encerrar a consulta ao CPF/CNPJ e não receber mais dados. 'FIM'  e 'ERR' são retornados conforme descrito na sequência 11, campo anterior.");
		listModelo.add(b49c);

		b49c = new ModeloB49C(13,61,1,"X","TP CONSULTA","IN","Informar 'A'");
		listModelo.add(b49c);

		b49c = new ModeloB49C(14,62,1,"X","ATUALIZA","IN","Informar 'N'");
		listModelo.add(b49c);

		b49c = new ModeloB49C(15,63,18,"X","IDENT_TERM","IN","Uso da SERASA");
		listModelo.add(b49c);

		b49c = new ModeloB49C(16,81,10,"X","RESCLI","IN","Uso da SERASA");
		listModelo.add(b49c);

		b49c = new ModeloB49C(17,91,1,"X","DELTS","IN","Uso da SERASA");
		listModelo.add(b49c);

		b49c = new ModeloB49C(18,92,1,"X","COBRA","IN","Uso da SERASA");
		listModelo.add(b49c);

		b49c = new ModeloB49C(19,93,1,"X","PASSA","IN","Informar  D , se a consulta é de distribuidores");
		listModelo.add(b49c);

		b49c = new ModeloB49C(20,94,1,"X","CONS.COLLEC","IN","Informar   'N'");
		listModelo.add(b49c);

		b49c = new ModeloB49C(21,95,57,"X","FILLER","","FILLER");
		listModelo.add(b49c);

		b49c = new ModeloB49C(22,152,15,"N","CONSULTANTE","IN","Informar o CNPJ da empresa consultante com 15 posições (com filial e dígito). Ex: 99.999.999/0001–99 –> informar 099999999000199 (Exclusivo para Distribuidores Do Credit Bureau)");
		listModelo.add(b49c);

		b49c = new ModeloB49C(23,167,234,"X","FILLER","","FILLER");
		listModelo.add(b49c);

	}

	public List<ModeloB49C> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloB49C> listModelo) {
		this.listModelo = listModelo;
	}

	/*
	Seq.	Inic	Byte	N/X	Mnemônico	In/Out	Descrição
	1		1		4		X	FILLER		IN		Preencher com 'ProtocoloB49C'
	2		5		6	X	FILLER			IN		Preencher com o código da estação chamadora no cliente (identificação de terminal ou computador). Campo opcional.
	3		11		15	N	NUM DOC			IN		Informar o número do CPF (com 11 dígitos) ou o número do CNPJ (com 15 dígitos) a ser consultado. Ajuste à direita e preenchido com zeros à esquerda.
	4		26		1	X	TIPO PESSOA		IN		Informar      "F" = pessoa física;      'J' = pessoa jurídica.
	5		27		6	X	BASE CONS		IN		Informar    'C'
	6		33		2	X	MODALIDADE		IN		Informar    'FI'
	7		35		7	N	VLR CONSUL		IN		Uso da SERASA
	8		42		12	X	CENTRO CUST		IN		Informar Centro de Custo Obs.: Como é opcional, informar brancos se não for usar esse dado.
	9		54		1	X	CODIFICADO		IN		Informar    'S'
	10		55		2	N	QTD REG			IN		Indicar a quantidade de registros por transmissão de dados que deseja receber (até 99).
	11		57		1	X	CONVERSA		IN		Informar se deseja diálogo de comunicação? (S/N) Obs.: > Quando a resposta à pergunta for 'N', o sistema enviará até 31 registros (recebidos e consultados), além do protocolo e encerrará a consulta ao CPF/CNPJ solicitado. > Quando a resposta for 'S', o sistema vai devolver o Protocolo a cada consulta, informando 'FIM' na seqüência 12 se todos os dados do CPF/CNPJ consultado foram enviados, 'CON' se houver mais dados sobre o CPF/CNPJ. O código 'ERR' significa que houve erro e, nesse caso, o sistema devolve o Protocolo e o registro T999 com códigos e mensagens de erro.
	12		58		3	X	FUNÇÃO			IN		Usar as funções: INI, CON, ENC, ERR, FIM. Informar 'INI' para iniciar uma nova consulta de CPF/CNPJ, 'CON' para receber a continuação do retorno de dados de um CPF/CNPJ  e 'ENC' para encerrar a consulta ao CPF/CNPJ e não receber mais dados. 'FIM'  e 'ERR' são retornados conforme descrito na sequência 11, campo anterior.
	13		61		1	X	TP CONSULTA		IN		Informar   'A'
	14		62		1	X	ATUALIZA		IN		Informar   'N'
	15		63		18	X	IDENT_TERM		IN		Uso da SERASA
	16		81		10	X	RESCLI			IN		Uso da SERASA
	17		91		1	X	DELTS			IN		Uso da SERASA
	18		92		1	X	COBRA			IN		Uso da SERASA
	19		93		1	X	PASSA			IN		Informar  D , se a consulta é de distribuidores
	20		94		1	X	CONS.COLLEC		IN		Informar   'N'
	21		95		57	X	FILLER					FILLER
	22		152		15	N	CONSULTANTE		IN		Informar o CNPJ da empresa consultante com 15 posições (com filial e dígito). Ex: 99.999.999/0001–99 –> informar 099999999000199 (Exclusivo para Distribuidores Do Credit Bureau)
	23		167		234	X	FILLER					FILLER

	*/



}
