package br.com.next.beans;

import java.util.UUID;

public class CartaoDebito extends Cartao {
	
	public CartaoDebito(String senha, double limiteTransacao) {
		super(senha);
		this.limiteTransacao = limiteTransacao;
	}

	private double limiteTransacao;

	public double getLimiteTransacao() {
		return limiteTransacao;
	}

	public void setLimiteTransacao(double limiteTransacao) {
		this.limiteTransacao = limiteTransacao;
	}

	
	
	

}
