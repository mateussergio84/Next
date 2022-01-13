package br.com.next.bo;

import java.util.Date;

import br.com.next.beans.Cliente;
import br.com.next.beans.Endereco;
import br.com.next.beans.TipoCliente;

public class ClienteBo {

	public Cliente cadastraCliente(String nome, String cpf, Date dataNascimento, Endereco endereco) {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setDataNascimento(dataNascimento);
		cliente.setEndereco(endereco);
		cliente.setTipo(TipoCliente.Comum);
		return cliente;
	}

}
