package br.com.next.beans;

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
