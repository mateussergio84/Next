package br.com.next.teste;

import java.util.Scanner;

import br.com.next.beans.Apolice;
import br.com.next.beans.Seguro;
import br.com.next.utils.Dados;

public class Teste {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Dados.carregaSeguros();
		System.out.println("Deseja ver os seguros disponiveis, digite 1 para sim 2 para n�o");
		int opc = sc.nextInt();
		if (opc == 1) {
			for (Seguro s : Dados.lSeguros) {
				System.out.println("Op��o " + s.getId() + ":\nSeguro de " + s.getNome() + "\n" + s.getRegras());
				System.out.println("\n------------------------\n");
			}
			System.out.println("Digite: \n1 - para Seguro de Invalidez"
					+ "\n2 - para seguro de morte \n3 - para seguro desemprego \n4 - para Sair");
			int opcao = sc.nextInt();
			if (opcao == 1) {
				Seguro seguro = Dados.addSeguroMorte();
				Double valorApolice = 36.0;
				String descricaoCondicao = "Seguro: " + seguro.getNome() + "\nRegras: \n" + seguro.getRegras()
						+ "Todos os nossos seguros garantem recupera��o de 100% do valor investido pelo\r\n"
						+ "segurado.\r\n"
						+ "O valor s� poder� ser recuperado se o prazo de car�ncia (15 dias) for cumprido.";
				Apolice apolice = new Apolice("01", descricaoCondicao, valorApolice, seguro);
				System.out.println("\nContrato de seguro de " + seguro.getNome() + " contratado na data "
						+ apolice.getDataAssinatura() + "" + " Sob as condi��es abaixo:\n"
						+ apolice.getDescricaoCondicoes() + " validas apartir da data " + apolice.getDataCarrencia());
			} else if (opcao == 2) {
				Seguro seguro = Dados.addSeguroInvalidez();
				Double valorApolice = 36.0;
				String descricaoCondicao = "Seguro: " + seguro.getNome() + "\nRegras: \n" + seguro.getRegras()
						+ "Todos os nossos seguros garantem recupera��o de 100% do valor investido pelo\r\n"
						+ "segurado.\r\n"
						+ "O valor s� poder� ser recuperado se o prazo de car�ncia (15 dias) for cumprido.";
				Apolice apolice = new Apolice("01", descricaoCondicao, valorApolice, seguro);
				System.out.println("\nContrato de seguro de " + seguro.getNome() + " contratado na data "
						+ apolice.getDataAssinatura() + "" + " Sob as condi��es abaixo:\n"
						+ apolice.getDescricaoCondicoes() + " validas apartir da data " + apolice.getDataCarrencia());
			}else if(opcao == 3) {
				Seguro seguro = Dados.addSeguroDesemprego();
				Double valorApolice = 36.0;
				String descricaoCondicao = "Seguro: " + seguro.getNome() + "\nRegras: \n" + seguro.getRegras()
						+ "Todos os nossos seguros garantem recupera��o de 100% do valor investido pelo\r\n"
						+ "segurado.\r\n"
						+ "O valor s� poder� ser recuperado se o prazo de car�ncia (15 dias) for cumprido.";
				Apolice apolice = new Apolice("01", descricaoCondicao, valorApolice, seguro);
				System.out.println("\nContrato de seguro de " + seguro.getNome() + " contratado na data "
						+ apolice.getDataAssinatura() + "" + " Sob as condi��es abaixo:\n"
						+ apolice.getDescricaoCondicoes() + " validas apartir da data " + apolice.getDataCarrencia());
			}
		}

		Seguro s1 = Dados.addSeguroMorte();

	}

}
