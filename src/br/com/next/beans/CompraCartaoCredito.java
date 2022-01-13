package br.com.next.beans;

public class CompraCartaoCredito {
	private String id;
	private double valorFatura;
	private double limite;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(double valorFatura) {
		this.valorFatura = valorFatura;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

}
