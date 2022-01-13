package br.com.next.beans;

public enum TipoCliente {
	Comum(0), Super(1), Premium(3);

	private final int tipo;

	TipoCliente(int tipocliente) {
		tipo = tipocliente;
	}

	public int getTipo() {
		return tipo;
	}

}
