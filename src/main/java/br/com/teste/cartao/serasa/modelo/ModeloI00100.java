package br.com.teste.cartao.serasa.modelo;

import java.util.ArrayList;
import java.util.List;

//Registro de Pedido – Reg I001
public class ModeloI00100 extends Modelo {

	ModeloI00100 modeloI001 = null;
	List<ModeloI00100> listModelo  = null;

	public ModeloI00100() {
		this.iniciarRegistro();
	}

	public ModeloI00100(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout,
			String descricao) {
		super(sequencia, posicaoInicial, bytes, nx, mneumonico, inout, descricao);
		// TODO Auto-generated constructor stub
	}

	private void iniciarRegistro() {

		listModelo = new ArrayList<ModeloI00100>();

		modeloI001 = new ModeloI00100(1,1,4,"X","Tipo_Reg","In","Registro I001 – REGISTRO DE PEDIDO.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(2,5,2,"X","Subtipo","In","Subtipo de registro de pedido '00'.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(3,7,1,"X","Filler","In","Consulta CONCENTRE DETALHE/RESUMO 	('D' ou 'R' ou ' ')). 	'D'-retorna registro resumo e até 99 registros detalhe de cada tipo de anotação; 	'R'-retorna registro resumo e até 5 registros detalhe de cada tipo de anotação; 	' '-não retorna registros do Concentre.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(4,8,1,"X","Filler","In","Consulta feature CONFIRMEI ('S' ou ('N' ou ' ')).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(5,9,1,"X","Filler","In","Consulta feature CONSULTAS À SERASA  Preencher com 'S' para receber a feature Consultas à SERASA.  Preencher com  'V' para receber a feature Consultas à Serasa + feature Consultas VEÍCULOS. 	Preencher com  'C' para receber a feature Consultas à Serasa + feature Consultas VEÍCULOS 'CONJUGADO'. 	Preencher com 'N' ou deixar em branco para não receber esta feature.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(6,10,1,"X","Filler","In","Consulta feature CONCENTRE SCORING 	('S' ou ('N' ou ' ')).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(7,11,4,"X","Filler","In","Modelo de score desejado: 	1 – Para Pessoa Física: Preencher com : ‘RSCP’ para SCORE SERASA modelo CSPA  ou ‘RSSP’ para SCORE SERASA COM SPC (C2PA) ou  deixar em branco. Caso o campo seq = 6 esteja preenchido com 'S' e este campo em branco, será calculado o modelo ‘RSC1’.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(8,15,1,"X","Filler","In","Consulta feature CONFIRMAÇÃO DE TELEFONE ('S' ou ('N' ou ' ')).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(9,16,4,"N","Filler","In","Código do DDD do telefone que deseja confirmar (obrigatório se 'S' na CONFIRMAÇÃO DE TELEFONE).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(10,20,10,"N","Filler","In","Número do TELEFONE que deseja confirmar (obrigatório se 'S' na CONFIRMAÇÃO DE TELEFONE).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(11,30,1,"X","Filler","In","Consulta feature LIMITE DE CRÉDITO ('S' ou ('N' ou ' ')).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(12,31,1,"X","Filler","In","Consulta feature LOCALIZADOR DE ENDEREÇOS                       ('S' ou ('N' ou ' ')).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(13,32,2,"N","Filler","In","QUANTIDADE DE ENDEREÇOS a serem retornados no LOCALIZADOR DE ENDEREÇOS.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(14,34,1,"X","Filler","In","Consulta feature ALERT SCORING (CNPJ) ('S' ou ('N' ou ' ')).  Disponível somente para clientes NÃO distribuidores.  Distribuidores que tentarem acessar esta feature receberão mensagem de indisponibilidade.  Distribuidores terão acesso ao Alerta Identidade PJ, via registro P002, assim como os demais clientes.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(15,35,1,"X","Filler","In","Consulta feature ALERTA IDENTIDADE (CPF) ('S' ou ('N' ou ' ')).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(16,36,1,"X","Filler","In","Consulta feature PARTICIPAÇÃO SOCIETÁRIA ('S' ou ('N' ou ' ')).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(17,37,2,"X","Filler","In","Consulta anotação de AÇÃO POR UF ESPECÍFICO (não disponível).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(18,39,2,"X","Filler","In","Consulta anotação de PROTESTO POR UF ESPECÍFICO (não disponível).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(19,41,2,"X","Filler","In","Consulta anotação de CONVEM DEVEDORES POR UF ESPECÍFICO   (não disponível).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(20,43,2,"X","Filler","In","Consulta anotação de PEFIN POR UF ESPECÍFICO (não disponível).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(21,45,2,"X","Filler","In","Consulta anotação de REFIN POR UF ESPECÍFICO (não disponível).");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(22,47,1,"X","Filler","In","Consulta feature FATURAMENTO PRESUMIDO (CNPJ) ('S' ou  ('N' ou ' '))");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(23,48,1,"X","Filler","In","Uso da SERASA.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(24,49,1,"X","Filler","In","Consulta feature SÓCIOS E ADMINISTRADORES (CNPJ) ('S' ou ('N' ou  ' ')) ou ‘C’ para Sócios e Administradores  com SPC (retorna no reg F900 NRC8), caso seja selecionada 'C' junto com RSQC no P002, será ignorada e exibida as informação na RSQC.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(25,50,1,"X","Filler","In","Consulta feature PARTICIPAÇÕES EM EMPRESAS (CNPJ) ('S' ou ('N' ou ' '))");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(26,51,1,"X","Filler","In","Consulta feature INDICADOR DE OPERACIONALIDADE (CNPJ) ('S' ou ('N' ou ' '))");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(27,52,1,"X","Filler","In","Consulta feature IRM - INDICE RELACIONAMENTO MERCADO ('S' ou ('N' ou ' '))");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(28,53,4,"X","Filler","In","Modelo de Alerta de Identidade desejado(PF): Preencher com o modelo desejado ou deixar em branco. Caso o campo seq = 15 esteja preenchido com 'S' e este campo em branco, será calculado o modelo 'CAFZ'.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(29,57,4,"X","FIller","In","Modelo de Limite de Crédito desejado.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(30,61,1,"X","Filler","In","Meio de acesso 	= W – consulta ao produto via web = ‘  ‘ – outros meios");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(31,62,1,"X","Filler","In","Deseja consultar 'Situação Cadastral do CNPJ' ? (S / N) 'S' - Carrega bloco I520-01, quando solicitada junto com a feature PARTICIPAÇÃO SOCIETÁRIA, registro I001, seq. 16. (Diferente de 'S' ou 'N', o sistema assume 'N')");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(32,63,5,"X","Filler","In","Uso da SERASA.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(33,68,47,"X","Filler","In","Mensagem de erro.");
		listModelo.add(modeloI001);

		modeloI001 = new ModeloI00100(34,115,1,"X","Filler","In","Uso da SERASA.");
		listModelo.add(modeloI001);

	}

	public List<ModeloI00100> getListModelo() {
		return listModelo;
	}

	public void setListModelo(List<ModeloI00100> listModelo) {
		this.listModelo = listModelo;
	}

}
