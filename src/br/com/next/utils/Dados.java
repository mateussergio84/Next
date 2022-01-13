package br.com.next.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.next.beans.Conta;
import br.com.next.beans.Pix;

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

}