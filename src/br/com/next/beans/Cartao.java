package br.com.next.beans;

import java.util.UUID;

public abstract class Cartao  {
	private String numero;
	private String bandeira;
	private String senha;
	private boolean ativo;


	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Cartao(String senha) {
		this.numero = UUID.randomUUID().toString();
		this.bandeira = "Mastercard";
		this.senha = senha;
		this.ativo = true;
	}
	
	public Cartao() {
	}
	
	
	

}
