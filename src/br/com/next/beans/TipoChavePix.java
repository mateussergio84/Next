package br.com.next.beans;

public enum TipoChavePix {
	cpf(0), email(1), telefone(2), aleatorio(3);

	private final int chave;

	TipoChavePix(int tipochavepix) {
		chave = tipochavepix;
	}

	public int getChave() {
		return chave;
	}
}
