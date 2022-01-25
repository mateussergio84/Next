package br.com.next.beans;

import java.util.Calendar;
import java.util.Date;

public class Apolice {

	
	
	public Apolice(String id, String descricaoCondicoes, double valorApolice, Date dataAssinatura, Date dataCarrencia,
			Seguro seguro) {
		super();
		this.id = id;
		this.descricaoCondicoes = descricaoCondicoes;
		this.valorApolice = valorApolice;
		this.dataAssinatura = dataAssinatura;
		this.dataCarrencia = dataCarrencia;
		this.seguro = seguro;
	}

	private String id;
	private String descricaoCondicoes;
	private double valorApolice;
	private Date dataAssinatura;
	private Date dataCarrencia;
	private Seguro seguro;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Date getDataAssinatura() {
		return dataAssinatura;
	}

	public void setDataAssinatura(Date dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	public Date getDataCarrencia() {
		return dataCarrencia;
	}

	public void setDataCarrencia(Date dataCarrencia) {
		this.dataCarrencia = dataCarrencia;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public String getDescricaoCondicoes() {
		return descricaoCondicoes;
	}

	public void setDescricaoCondicoes(String descricaoCondicoes) {
		this.descricaoCondicoes = descricaoCondicoes;
	}

	public double getValorApolice() {
		return valorApolice;
	}

	public void setValorApolice(double valorApolice) {
		this.valorApolice = valorApolice;
	}

	

}
