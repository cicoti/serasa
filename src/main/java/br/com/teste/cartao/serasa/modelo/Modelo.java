package br.com.teste.cartao.serasa.modelo;

public class Modelo {

	private int sequencia;
	private int posicaoInicial;
	private int bytes;
	private String nx;
	private String mnemonico;
	private String inout;
	private String descricao;

	public Modelo() {

	}

	public Modelo(int sequencia, int posicaoInicial, int bytes, String nx, String mneumonico, String inout, String descricao) {
		this.sequencia = sequencia;
		this.posicaoInicial = posicaoInicial;
		this.bytes = bytes;
		this.nx = nx;
		this.mnemonico = mneumonico;
		this.inout = inout;
		this.descricao = descricao;
	}

	public int getSequencia() {
		return sequencia;
	}

	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}

	public int getPosicaoInicial() {
		return posicaoInicial;
	}

	public void setPosicaoInicial(int posicaoInicial) {
		this.posicaoInicial = posicaoInicial;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public String getNx() {
		return nx;
	}

	public void setNx(String nx) {
		this.nx = nx;
	}

	public String getMnemonico() {
		return mnemonico;
	}

	public void setMnemonico(String mnemonico) {
		this.mnemonico = mnemonico;
	}

	public String getInout() {
		return inout;
	}

	public void setInout(String inout) {
		this.inout = inout;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



}
