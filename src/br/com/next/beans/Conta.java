package br.com.next.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conta {
	// private Cliente cliente;
	private Cliente cliente;
	private String numeroConta;
	private double saldo;
	private String senha;
	private int idConta;
	private Pix pix;
	private TipoConta tipoConta;
	private Date data;
	private ArrayList<Cartao>cartoes = new ArrayList<Cartao>();


	public ArrayList<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(ArrayList<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	public Pix getPix() {
		return pix;
	}

	public void setPix(Pix pix) {
		this.pix = pix;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void addCartao(Cartao cartao) {
		if (this.cartoes == null) {
			this.cartoes = new ArrayList<Cartao>();
		}
		this.cartoes.add(cartao);
		for (Cartao c : cartoes) {
			System.out.println(c.getNumero());
		}
	}

	public void removeCartao(String num, String senha) {
		for (Cartao c : cartoes) {
			if (c.getNumero().equals(num)) {
				this.cartoes.remove(c);
			} else {
				System.out.println("Cartao n�o encontrado!");
			}
		}
		for (Cartao c : cartoes) {
			System.out.println(c.getNumero());
		}
	}


}
