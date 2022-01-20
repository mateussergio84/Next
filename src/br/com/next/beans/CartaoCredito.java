package br.com.next.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartaoCredito extends Cartao {
	private String id;
	private double limite;
	private double valorFatura;
	private Date vencimento;
	private List<Compra>compras;
	Apolice apolice;
	

	
	
	public CartaoCredito() {
	}
	
	public CartaoCredito(String senha, double limite, Date vencimento) {
		super(senha);
		this.limite = limite;
		this.valorFatura = 0;
		this.compras = new ArrayList<Compra>();
		this.vencimento = vencimento;
	}	

	public Apolice getApolice() {
		return apolice;
	}

	public void setApolice(Apolice apolice) {
		this.apolice = apolice;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Compra compra) {
		this.compras.add(compra);
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

	@Override
	public String toString() {
		return "CartaoCredito [id=" + id + ", limite=" + limite + ", valorFatura=" + valorFatura + ", vencimento="
				+ vencimento + ", compras=" + compras + ", apolice=" + apolice + ", getApolice()=" + getApolice()
				+ ", getCompras()=" + getCompras() + ", getId()=" + getId() + ", getLimite()=" + getLimite()
				+ ", getValorFatura()=" + getValorFatura() + ", getVencimento()=" + getVencimento() + "]";
	}
	
	
	
	
	


	
	
	

}
