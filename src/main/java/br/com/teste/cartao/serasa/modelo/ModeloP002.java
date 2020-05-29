package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//REGISTROS ADICIONAIS AO PROTOCOLO
//OUTRAS OPCOES DE CONSULTA - REG.: P002
public class ModeloP002 extends Modelo {

	ModeloP002 p002 = null;
	List<ModeloP002> listModelo = null;

	public ModeloP002() {
		this.iniciarRegistro();
	}

	public ModeloP002(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloP002>();

		p002 = new ModeloP002(1,1,4,"X","TIPO–REG","IN","Registro P002 – OUTRAS OPÇÕES DE CONSULTAS Usar esse registro quando desejar consultar também outros produtos, features e bases. Obs.: Esse registro pode se repetir.");
		listModelo.add(p002);

		p002 = new ModeloP002(2,5,4,"X","COD1","IN","Informar  'RSPU'");
		listModelo.add(p002);

		p002 = new ModeloP002(3,9,21,"X","CHAVE1","IN","- Chave de acesso para consultar a 1ª opção se necessário (Ex.: nº DDD e telefone)");
		listModelo.add(p002);

		p002 = new ModeloP002(4,30,4,"X","COD2","IN","Código da 2ª opção para consultar outro produto, feature ou base");
		listModelo.add(p002);

		p002 = new ModeloP002(5,34,21,"X","CHAVE2","IN","– Chave de acesso para consultar a 2ª opção se necessário");
		listModelo.add(p002);

		p002 = new ModeloP002(6,55,4,"X","COD3","IN","Código da 3ª opção para consultar outro produto, feature ou base");
		listModelo.add(p002);

		p002 = new ModeloP002(7,59,21,"X","CHAVE3","IN","– Chave de acesso para consultar a 3ª opção se necessário");
		listModelo.add(p002);

		p002 = new ModeloP002(8,80,4,"X","COD4","IN","Código da 4ª opção para consultar outro produto, feature ou base");
		listModelo.add(p002);

		p002 = new ModeloP002(9,84,21,"X","CHAVE4","IN","– Chave de acesso para consultar a 4ª opção se necessário");
		listModelo.add(p002);

		p002 = new ModeloP002(10,105,11,"X","FILLER","IN","");
		listModelo.add(p002);

    }

	public List<ModeloP002> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloP002> listModelo) {
		this.listModelo = listModelo;
	}

/*
	Seq.	Inic	Byte	N/X	Mnemônico	In/Out	Descrição
	1		1		4		X	TIPO–REG	IN		Registro P002 – OUTRAS OPÇÕES DE CONSULTAS Usar esse registro quando desejar consultar também outros produtos, features e bases. Obs.: Esse registro pode se repetir.
	2		5		4		X	COD1		IN 		Informar  “RSPU”
	3		9		21		X	CHAVE1		IN 	 	– Chave de acesso para consultar a 1ª opção se necessário (Ex.: nº DDD e telefone)
	4		30		4		X	COD2		IN 		Código da 2ª opção para consultar outro produto, feature ou base
	5		34		21		X	CHAVE2		IN 	 	– Chave de acesso para consultar a 2ª opção se necessário
	6		55		4		X	COD3		IN 		Código da 3ª opção para consultar outro produto, feature ou base
	7		59		21		X	CHAVE3		IN 	 	– Chave de acesso para consultar a 3ª opção se necessário
	8		80		4		X	COD4		IN 		Código da 4ª opção para consultar outro produto, feature ou base
	9		84		21		X	CHAVE4		IN 	 	– Chave de acesso para consultar a 4ª opção se necessário
	10		105		11		X	FILLER		IN
*/

}
