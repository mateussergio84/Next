package br.com.next.beans;

import java.util.Date;

public class CartaoCredito extends Cartao {
	private String id;
	private double limite;
	private double valorFatura;
	private Date vencimento;
	
	public CartaoCredito() {
	}
	
	public CartaoCredito(String senha, double limite, Date vencimento) {
		super(senha);
		this.limite = limite;
		this.valorFatura = 0;
		this.vencimento = vencimento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(double valorFatura) {
		this.valorFatura = valorFatura;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	
	


	
	
	

}
