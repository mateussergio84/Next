package br.com.next.beans;

import java.util.Date;

public class Cliente extends Endereco {

	public Cliente(String nome, String cpf, TipoCliente tipo, Endereco endereco, Date dataNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.tipo = tipo;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
	}

	public Cliente(String logradouro, String numero, String cep, String bairro, String cidade, String estado) {
	}

	private String nome;
	private String cpf;
	private TipoCliente tipo;
	private Endereco endereco;
	private Date dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoCliente getTipo() {
		return tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", tipo=" + tipo + ", endereco=" + endereco
				+ ", dataNascimento=" + dataNascimento + "]";
	}

}
