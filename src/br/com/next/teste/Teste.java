package br.com.next.teste;

import java.util.Scanner;

import br.com.next.beans.Apolice;
import br.com.next.beans.Seguro;
import br.com.next.bo.ApoliceBo;
import br.com.next.bo.SeguroBo;
import br.com.next.utils.Dados;

public class Teste {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		SeguroBo sb = new SeguroBo();
		ApoliceBo ApB = new ApoliceBo();
		Seguro seguro;
		
		System.out.println("Deseja ver os seguros disponiveis, digite 1 para sim 2 para não");
		int opc = sc.nextInt();

		if (opc == 1) {
			
			for (Seguro s : sb.carregaSeguros()) {
				System.out.println("Opção " + s.getId() + ":\nSeguro de " + s.getNome() + "\n" + s.getRegras());
				System.out.println("\n------------------------\n");
			}
			System.out.println("Digite: \n1 - para Seguro de Invalidez"
					+ "\n2 - para seguro de morte \n3 - para seguro desemprego \n4 - para Sair");
			int opcao = sc.nextInt();
			if (opcao == 1) {
				Seguro seguro1 = sb.morte;
				Double valorApolice = 36.0;
				String descricaoCondicao = "Seguro: " + seguro1.getNome() + "\nRegras: \n" + seguro1.getRegras()
						+ "Todos os nossos seguros garantem recuperação de 100% do valor investido pelo\r\n"
						+ "segurado.\r\n"
						+ "O valor só poderá ser recuperado se o prazo de carência (15 dias) for cumprido.";
				Apolice ap = ApB.addApolice("01", descricaoCondicao, valorApolice, seguro1);
				System.out.println(ApB.exibeApolice(ap));
			} else if (opcao == 2) {
				Seguro seguro2 = sb.invalidez;
				Double valorApolice = 36.0;
				String descricaoCondicao = "Seguro: " + seguro2.getNome() + "\nRegras: \n" + seguro2.getRegras()
						+ "Todos os nossos seguros garantem recuperação de 100% do valor investido pelo\r\n"
						+ "segurado.\r\n"
						+ "O valor só poderá ser recuperado se o prazo de carência (15 dias) for cumprido.";
				Apolice ap = ApB.addApolice("02", descricaoCondicao, valorApolice, seguro2);
				System.out.println(ApB.exibeApolice(ap));

			}else if(opcao == 3) {
				Seguro seguro3 = sb.desemprego;
				Double valorApolice = 36.0;
				String descricaoCondicao = "Seguro: " + seguro3.getNome() + "\nRegras: \n" + seguro3.getRegras()
						+ "Todos os nossos seguros garantem recuperação de 100% do valor investido pelo\r\n"
						+ "segurado.\r\n"
						+ "O valor só poderá ser recuperado se o prazo de carência (15 dias) for cumprido.";
				Apolice ap = ApB.addApolice("03", descricaoCondicao, valorApolice, seguro3);
				System.out.println(ApB.exibeApolice(ap));

			}
		}


	}

}
