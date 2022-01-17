package br.com.next.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import br.com.next.beans.Cartao;
import br.com.next.beans.CartaoDebito;
import br.com.next.beans.Cliente;
import br.com.next.beans.Conta;
import br.com.next.beans.TipoCliente;
import br.com.next.beans.TipoConta;
import br.com.next.utils.Dados;

public class ContaBo {
	private Conta conta;

	public ContaBo(Conta conta) {
		this.conta = conta;
		// System.out.println(conta);
	}

	public ContaBo(Cliente cliente, TipoConta tipoConta, String senha) {
		this.conta = this.criaConta(cliente, tipoConta, senha);
	}

	public Conta criaConta(Cliente cliente, TipoConta tipoconta, String senha) {
		Conta conta = new Conta();
		conta.setNumeroConta(UUID.randomUUID().toString());
		conta.setSaldo(0.0);
		conta.setCliente(cliente);
		conta.setTipoConta(tipoconta);
		conta.setSenha(senha);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date date = cal.getTime();
		conta.setData(date);
		Dados.insereConta(conta.getNumeroConta(), conta);
		System.out.println("Cliente " + cliente.getNome() + " numero da conta " + conta.getNumeroConta());
		System.out.println(conta.getTipoConta());
		return conta;
	}

	public boolean transferir(Conta contaRecebida, double valor) {
		if (this.conta.getSaldo() > valor) {
			double saldoAtual = this.conta.getSaldo();
			saldoAtual -= valor;
			this.conta.setSaldo(saldoAtual);

			double saldoOutraConta = contaRecebida.getSaldo();
			saldoOutraConta += valor;
			contaRecebida.setSaldo(saldoOutraConta);
			Dados.insereConta(contaRecebida.getNumeroConta(), contaRecebida);
			Dados.insereConta(conta.getNumeroConta(), conta);
			this.exibeSaldo();
			return true;
		} else {
			System.out.println("Saldo insuficiente!");
			return false;
		}
	}

	public void exibeSaldo() {
		this.debitoCredito();
		String nome = this.conta.getCliente().getNome();
		String cpf = this.conta.getCliente().getCpf();
		double valor = this.conta.getSaldo();
		if (valor > 5000 && valor < 14999) {
			this.conta.getCliente().setTipo(TipoCliente.Premium);
		} else if (valor > 15000) {
			this.conta.getCliente().setTipo(TipoCliente.Super);
		}

		System.out.println("Cliente " + nome + "com Cpf " + cpf + " saldo disponivel R$" + valor + "\nCliente "
				+ conta.getCliente().getTipo() + " " + conta.getTipoConta().name());
	}

	public void deposito(double deposito) {
		this.conta.setSaldo(this.conta.getSaldo() + deposito);
		Dados.insereConta(conta.getNumeroConta(), conta);
		this.exibeSaldo();
	}

	public void saque(double saque) {
		this.conta.setSaldo(this.conta.getSaldo() - saque);
		Dados.insereConta(conta.getNumeroConta(), conta);
		this.exibeSaldo();

	}

	public Date getDateAdd1Month() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date data = cal.getTime();

		return data;
	}

	public void debitoCredito() {

		if (this.conta.getData().before(new Date())) {

			if (this.conta.getTipoConta() == TipoConta.CORRENTE) {
				double valor = this.conta.getSaldo();
				valor -= valor * 0.45;
				this.conta.setSaldo(valor);
			} else {
				double valor = this.conta.getSaldo();
				valor = valor * 0.03;
				this.deposito(valor);
			}

			Date data = this.getDateAdd1Month();
			this.conta.setData(data);

			Dados.insereConta(this.conta.getNumeroConta(), this.conta);
		}

	}

	public void addCartao(Cartao cartao) {
		conta.addCartao(cartao);
		Dados.insereConta(this.conta.getNumeroConta(), conta);
	}

	public void removerCartao(String num, String senha) {
		conta.removeCartao(num, senha);
		Dados.insereConta(this.conta.getNumeroConta(), conta);		
	}
	
	
	
	public void verificaCartao(String num) {
		conta.verificaCartao(num);
		if(conta.isCred()== true) {
			System.out.println("ABC");
		}else {
			System.out.println("DFG");
		}
	} 
	


	
	

	/*
	 * public void adicionarPix(Pix pix) { this.conta.setPix(pix);
	 * Dados.insereConta(this.conta.getNumeroConta(), this.conta);
	 * 
	 * }
	 */
}
