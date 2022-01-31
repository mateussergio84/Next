package br.com.next.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import br.com.next.beans.Cartao;
import br.com.next.beans.CartaoCredito;
import br.com.next.beans.CartaoDebito;
import br.com.next.beans.Cliente;
import br.com.next.beans.Compra;
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

	public ContaBo() {
	}

	public Conta criaConta(Cliente cliente, TipoConta tipoconta, String senha) {
		String numeroConta = UUID.randomUUID().toString();
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		Conta conta = new Conta(cliente, numeroConta, 0.0, senha, tipoconta, date);
		Dados.insereConta(conta.getNumeroConta(), conta);
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
			System.out.println(this.exibeSaldo());
			return true;
		} else {
			return false;
		}
	}

	public String exibeSaldo() {
		String nome = this.conta.getCliente().getNome();
		String cpf = this.conta.getCliente().getCpf();
		double valor = this.conta.getSaldo();
		if (valor > 5000 && valor < 14999) {
			this.conta.getCliente().setTipo(TipoCliente.Premium);
		} else if (valor > 15000) {
			this.conta.getCliente().setTipo(TipoCliente.Super);
		}
		String extrato = ("Cliente " + nome +" saldo disponivel R$" + valor + "\nCliente "
				+ conta.getCliente().getTipo() + " conta " + conta.getTipoConta().name());
		return extrato;
	}

	public void deposito(double deposito) {
		this.conta.setSaldo(this.conta.getSaldo() + deposito);
		Dados.insereConta(conta.getNumeroConta(), conta);
		System.out.println(exibeSaldo());
	}

	public void saque(double saque) {
		this.conta.setSaldo(this.conta.getSaldo() - saque);
		Dados.insereConta(conta.getNumeroConta(), conta);
		System.out.println(exibeSaldo());
	}

	/*
	public void debitoCredito() {
		if (this.conta.getTipoConta() == TipoConta.CORRENTE) {
			double valor = this.conta.getSaldo();
			valor -= valor * 0.45;
			this.conta.setSaldo(valor);
		} else {
			double valor = this.conta.getSaldo();
			valor += valor * 0.03;
			this.deposito(valor);
		}
		Dados.insereConta(this.conta.getNumeroConta(), this.conta);
	}*/

	public void addCartao(Cartao cartao) {
		conta.addCartao(cartao);
		Dados.insereConta(this.conta.getNumeroConta(), conta);
	}

	public void removerCartao(String num, String senha) {
		conta.removeCartao(num, senha);
		Dados.insereConta(this.conta.getNumeroConta(), conta);
	}

	public boolean comparAprovadaDebito(Cartao c, double valor) {
		CartaoDebito cartaoD = (CartaoDebito) c;
		if (cartaoD.getLimiteTransacao() > valor) {
			if (conta.getSaldo() > valor) {
				conta.setSaldo(conta.getSaldo() - valor);
				// System.out.println("Compra aprovada!\nSaldo R$"+conta.getSaldo());
				return true;
			} else {
				System.out.println("Saldo insuficiente");
				return false;
			}
		}
		return false;
	}

	public boolean compraAprovadaCredito(Cartao c, double valor) {
		CartaoCredito cartaoC = (CartaoCredito) c;
		if (cartaoC.getLimite() > valor) {
			cartaoC.setValorFatura(cartaoC.getValorFatura() + valor);
			cartaoC.setLimite(cartaoC.getLimite() - valor);
			Compra compra = new Compra(new Date(), valor);
			cartaoC.setCompras(compra);
			return true;
			// System.out.println("Compra aprovada!\nLimite disponivel
			// R$"+cartaoC.getLimite());
			// System.out.println("Compra não autorizada, excede seu limite ");
		} else {
			return false;
		}

	}

	/*
	 * public void exibeFatura(Cartao c) { CartaoCredito cartaoC = (CartaoCredito)c;
	 * List<Compra>compras = cartaoC.getCompras(); SimpleDateFormat sdf = new
	 * SimpleDateFormat("dd/MM/yyyy"); for(Compra compra : compras) { String
	 * dataCompra = sdf.format(compra.getDate());
	 * 
	 * System.out.println("Compra no valor de R$"+compra.getValor()+" na data de "
	 * +dataCompra); } String dataVencimento = sdf.format(cartaoC.getVencimento());
	 * System.out.println("Total R$"+cartaoC.getValorFatura());
	 * System.out.println("Vencimento "+dataVencimento);
	 * System.out.println("Limite R$"+cartaoC.getLimite());
	 * 
	 * }
	 */

}
