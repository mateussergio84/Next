package br.com.next.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.next.beans.Apolice;
import br.com.next.beans.Cartao;
import br.com.next.beans.CartaoCredito;
import br.com.next.beans.Conta;
import br.com.next.beans.Pix;
import br.com.next.beans.Seguro;

public class Dados {

	public static Map<String, Conta> banco_dados = new HashMap<String, Conta>();



	public static Conta buscaConta(String numeroConta) {
		Conta conta = Dados.banco_dados.get(numeroConta);
		if (conta == null) {
			System.out.println("Conta invalida!");
			return null;
		} else {
			return conta;
		}
	}
	

	
	public static Conta buscaContaPorCpf(String cpf) {
		Conta conta = Dados.banco_dados.get(cpf);
		if (conta == null) {
			System.out.println("Conta invalida!");
			return null;
		} else {
			return conta;
		}
	}
	

	public static Conta buscaContaPor(String num) {
		Conta conta = Dados.banco_dados.get(num);
		if (conta == null) {
			System.out.println("Conta invalida!");
			return null;
		} else {
			return conta;
		}
	}
	
	public static boolean valida(String num) {
		Conta conta = Dados.banco_dados.get(num);
		List<Cartao> listaCartao = conta.getCartoes();
		for(Cartao c : listaCartao) {
			if(c.getNumero().equals(num)) {
				if(c.getClass().getSimpleName().toLowerCase().contains("debito")) {
					System.out.println("Debito");
					return true;
				}
			}else {
				System.out.println("Credito");
				return false;
			}
		}
		return false;
	}
	
	
	

	public static List<Conta> buscarTodasAsContas(String cpf) {
		List<Conta> listConta = new ArrayList<Conta>();

		for (Map.Entry<String, Conta> mapaConta : Dados.banco_dados.entrySet()) {
			Conta conta = mapaConta.getValue();
			listConta.add(conta);
		}

		return listConta;
	}

	public static void insereConta(String numeroConta, Conta conta) {
		Dados.banco_dados.put(numeroConta, conta);
	}
	
	

	public static List<Conta> buscarTodasAsContas() {
		List<Conta> listConta = new ArrayList<Conta>();

		for (Map.Entry<String, Conta> mapaConta : Dados.banco_dados.entrySet()) {
			Conta conta = mapaConta.getValue();
			listConta.add(conta);
		}

		return listConta;

	}

	public static Conta getContaPorPix(String chave) {
		for (Map.Entry<String, Conta> mapaConta : Dados.banco_dados.entrySet()) {
			Conta conta = mapaConta.getValue();
			Pix pix = conta.getPix();
			if (pix != null) {
				if (pix.getChave().equals(chave)) {
					return conta;
				}
			}
		}
		return null;

	}
	
	public static Seguro addSeguroMorte() {
		Seguro seguro = new Seguro(1, "Morte", "i. Indeniza��o por despesas m�dico-hospitalares, ou por perda parcial ou\r\n"
				+ "total do funcionamento dos membros ou �rg�os;\r\n"
				+ "ii. Reembolso de custos em diagn�stico de doen�as graves, como infarto,\r\n"
				+ "acidente vascular cerebral e c�ncer;\r\n"
				+ "iii. Assist�ncia funeral, para voc� e a sua fam�lia.\r\n"
				+ "iv. O valor do seguro individual � de R$36,00 ao ano.");
		return seguro;
	}
	
	public static Seguro addSeguroDesemprego() {
		Seguro seguro = new Seguro(3, "Desemprego", "i. Necess�rio trabalhar com registro CLT, com o tempo m�nimo de\r\n"
				+ "estabilidade de 12 meses.\r\n"
				+ "ii. V�lido apenas para desligamento involunt�rios e sem justa causa.\r\n"
				+ "iii. N�o � valido em caso de demiss�o em massa (10% ou mais de\r\n"
				+ "demiss�es simult�neas) ou fal�ncia/encerramento das atividades.\r\n"
				+ "iv. O valor do seguro individual � de R$16,00 ao ano.\r\n"
				+ "");
		return seguro;
	}
	
	public static Seguro addSeguroInvalidez() {
		Seguro seguro = new Seguro(2, "Invalidez", "i. Invalidez parcial: ocorre quando h� uma perda parcial das fun��es. Por\r\n"
				+ "exemplo, uma pessoa que sofre um acidente e perda a vis�o em um s�\r\n"
				+ "dos olhos.\r\n"
				+ "ii. Invalidez total: ocorre quando h� uma perda total das fun��es.\r\n"
				+ "Intuitivamente, um bom exemplo seria o caso onde a pessoa sofre um\r\n"
				+ "acidente e perde a vis�o em ambos os olhos.\r\n"
				+ "iii. O valor do seguro individual � de R$26,00 ao ano.");
		return seguro;
	}
	
	public static List<Seguro> lSeguros = new ArrayList<Seguro>();

	
	public static List<Seguro> carregaSeguros() {
		lSeguros.add(Dados.addSeguroMorte());
		lSeguros.add(Dados.addSeguroInvalidez());
		lSeguros.add(Dados.addSeguroDesemprego());
		return lSeguros;
	}
	
	public static Cartao isCred(String numCartao, Conta conta) {
		for (Cartao c : conta.getCartoes()) {
			if (c.getNumero().equals(numCartao)) {
				if (c.getClass().getSimpleName().toLowerCase().contains("credito")) {
					CartaoCredito cc = (CartaoCredito) c;
					return cc;
				}
			} else {
				return null;
			}
		}
		return null;
	}
	
	
	
	

}