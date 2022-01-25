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
			"i. Indeniza��o por despesas m�dico-hospitalares, ou por perda parcial ou\r\n"
					+ "total do funcionamento dos membros ou �rg�os;\r\n"
					+ "ii. Reembolso de custos em diagn�stico de doen�as graves, como infarto,\r\n"
					+ "acidente vascular cerebral e c�ncer;\r\n"
					+ "iii. Assist�ncia funeral, para voc� e a sua fam�lia.\r\n"
					+ "iv. O valor do seguro individual � de R$36,00 ao ano.");

	public Seguro desemprego = new Seguro(3, "Desemprego",
			"i. Necess�rio trabalhar com registro CLT, com o tempo m�nimo de\r\n" + "estabilidade de 12 meses.\r\n"
					+ "ii. V�lido apenas para desligamento involunt�rios e sem justa causa.\r\n"
					+ "iii. N�o � valido em caso de demiss�o em massa (10% ou mais de\r\n"
					+ "demiss�es simult�neas) ou fal�ncia/encerramento das atividades.\r\n"
					+ "iv. O valor do seguro individual � de R$16,00 ao ano.\r\n" + "");

	public Seguro invalidez = new Seguro(2, "Invalidez",
			"i. Invalidez parcial: ocorre quando h� uma perda parcial das fun��es. Por\r\n"
					+ "exemplo, uma pessoa que sofre um acidente e perda a vis�o em um s�\r\n" + "dos olhos.\r\n"
					+ "ii. Invalidez total: ocorre quando h� uma perda total das fun��es.\r\n"
					+ "Intuitivamente, um bom exemplo seria o caso onde a pessoa sofre um\r\n"
					+ "acidente e perde a vis�o em ambos os olhos.\r\n"
					+ "iii. O valor do seguro individual � de R$26,00 ao ano.");
	
	public List<Seguro> carregaSeguros() {
	    List<Seguro> lSeguro = new ArrayList<Seguro>();
	    lSeguro.add(morte);
	    lSeguro.add(invalidez);
	    lSeguro.add(desemprego);
		return lSeguro;
	}
	


}