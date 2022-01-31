package br.com.next.bo;

import java.util.Calendar;
import java.util.Date;

import br.com.next.beans.Apolice;
import br.com.next.beans.Seguro;

public class ApoliceBo {
	
	public Apolice addApolice(String id, String descricaoCondicoes, double valorApolice, Seguro seguro) {
		Apolice ap = new Apolice(id, descricaoCondicoes, valorApolice, new Date(), dataCarrencia(), seguro);
		return ap;
	}

	public Date dataCarrencia() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 15);
		Date dataCarrencia = c.getTime();
		return dataCarrencia;
	}
	
	public String exibeApolice(Apolice ap) {
		String apolice=("\nContrato de seguro de " + ap.getSeguro().getNome() + " contratado na data "
				+ ap.getDataAssinatura() + "" + " Sob as condições abaixo:\n"
				+ ap.getDescricaoCondicoes() + " validas apartir da data " + ap.getDataCarrencia());
		return apolice;
	}

}
