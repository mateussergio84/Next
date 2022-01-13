package br.com.next.beans;

public class CartaoCredito extends Cartao {
	private String id;
	private int limite;
	private int valorFatura;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(int valorFatura) {
		this.valorFatura = valorFatura;
	}

}
