package br.com.next.beans;

import java.util.ArrayList;
import java.util.List;

public class Seguro {
	public Seguro(int id, String nome, String regras) {
		super();
		this.id = id;
		this.nome = nome;
		this.regras = regras;
	}

	private int id;
	private String nome;
	private String regras;
	private List<Seguro> lSeguros = new ArrayList<Seguro>();
	
	


	public List<Seguro> getlSeguros() {
		return lSeguros;
	}

	public void addSeguro(Seguro seguro) {
		if (this.lSeguros == null) {
			this.lSeguros = new ArrayList<Seguro>();
		}
		this.lSeguros.add(seguro);
		for (Seguro s : lSeguros) {
			System.out.println(s.nome);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegras() {
		return regras;
	}

	public void setRegras(String regras) {
		this.regras = regras;
	}

}
