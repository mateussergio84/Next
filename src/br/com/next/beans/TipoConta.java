package br.com.next.beans;

public enum TipoConta {
	CORRENTE(1), POUPANCA(2);

	private int id;

	TipoConta(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

}
