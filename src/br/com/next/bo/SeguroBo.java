package br.com.next.bo;


import java.util.ArrayList;
import java.util.List;

import br.com.next.beans.Seguro;

public class SeguroBo {
	Seguro seguro;

	public Seguro addSeguro(int id, String nome, String regras) {
		Seguro seguro = new Seguro(id, regras, regras);
		seguro.addSeguro(seguro);
		return seguro;
	}

	public Seguro morte = new Seguro(1, "Morte",
			"i. Indenização por despesas médico-hospitalares, ou por perda parcial ou\r\n"
					+ "total do funcionamento dos membros ou órgãos;\r\n"
					+ "ii. Reembolso de custos em diagnóstico de doenças graves, como infarto,\r\n"
					+ "acidente vascular cerebral e câncer;\r\n"
					+ "iii. Assistência funeral, para você e a sua família.\r\n"
					+ "iv. O valor do seguro individual é de R$36,00 ao ano.");

	public Seguro desemprego = new Seguro(3, "Desemprego",
			"i. Necessário trabalhar com registro CLT, com o tempo mínimo de\r\n" + "estabilidade de 12 meses.\r\n"
					+ "ii. Válido apenas para desligamento involuntários e sem justa causa.\r\n"
					+ "iii. Não é valido em caso de demissão em massa (10% ou mais de\r\n"
					+ "demissões simultâneas) ou falência/encerramento das atividades.\r\n"
					+ "iv. O valor do seguro individual é de R$16,00 ao ano.\r\n" + "");

	public Seguro invalidez = new Seguro(2, "Invalidez",
			"i. Invalidez parcial: ocorre quando há uma perda parcial das funções. Por\r\n"
					+ "exemplo, uma pessoa que sofre um acidente e perda a visão em um só\r\n" + "dos olhos.\r\n"
					+ "ii. Invalidez total: ocorre quando há uma perda total das funções.\r\n"
					+ "Intuitivamente, um bom exemplo seria o caso onde a pessoa sofre um\r\n"
					+ "acidente e perde a visão em ambos os olhos.\r\n"
					+ "iii. O valor do seguro individual é de R$26,00 ao ano.");
	
	public List<Seguro> carregaSeguros() {
	    List<Seguro> lSeguro = new ArrayList<Seguro>();
	    lSeguro.add(morte);
	    lSeguro.add(invalidez);
	    lSeguro.add(desemprego);
		return lSeguro;
	}
	


}