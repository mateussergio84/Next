package br.com.next.beans;

import java.util.Date;

public class Compra {
	public Compra(Date date, double valor) {
		super();
		this.date = date;
		this.valor = valor;
	}
	
	Date date;
	double valor;
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
	

}
