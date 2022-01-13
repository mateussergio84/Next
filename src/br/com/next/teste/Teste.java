package br.com.next.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import br.com.next.beans.Cliente;
import br.com.next.beans.Conta;
import br.com.next.beans.Endereco;
import br.com.next.beans.Pix;
import br.com.next.beans.TipoChavePix;
import br.com.next.beans.TipoConta;
import br.com.next.bo.ClienteBo;
import br.com.next.bo.ContaBo;
import br.com.next.utils.Dados;

public class Teste {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int opcao = 5;
		System.out.println("Bem vido ao Next");
		while (opcao != 0) {
			System.out.println(
					"----------------------------\n1 - Cadastro Cliente \n2 - Transferencia \n3 - Deposito \n4 - Consulta \n5 - Credito/Debito \n6 - Cadastrar chave PIX \n7 - Transferencia Pix \n8 - Sair \n----------------------------");

			opcao = sc.nextInt();
			if (opcao == 1) {
				Cliente cliente = Teste.cadastrarCliente(sc);
				if (cliente == null) {
					continue;
				}

				System.out.println("Digite 1 para conta corrente ou 2 para poupança");
				int opcaoConta = sc.nextInt();

				String cpf = cliente.getCpf();

				List<Conta> listConta = Dados.buscarTodasAsContas(cpf);
				for (Conta conta : listConta) {
					if (opcaoConta == conta.getTipoConta().getId()) {
						System.out.println("Conta já foi criada ");
						continue;
					}
				}
				System.out.println("Senha: ");
				String senha = sc.next();

				if (opcaoConta == 1) {
					new ContaBo(cliente, TipoConta.CORRENTE, senha);
				} else if (opcaoConta == 2) {
					new ContaBo(cliente, TipoConta.POUPANCA, senha);
				} else if (opcaoConta == 3) {
					new ContaBo(cliente, TipoConta.POUPANCA, senha);
					new ContaBo(cliente, TipoConta.CORRENTE, senha);
				} else {
					System.out.println("Opção invalida! Tente novamente");
					opcaoConta = sc.nextInt();
				}
			}

			else if (opcao == 2) {

				System.out.println("Número da conta a receber: ");
				String numContaRecebe = sc.next();
				Conta contaRecebe = Dados.buscaConta(numContaRecebe);
				if (contaRecebe == null) {
					continue;
				}

				System.out.println("Número da sua conta: ");
				String numContaPaga = sc.next();
				Conta contaPaga = Dados.buscaConta(numContaPaga);
				if (contaPaga == null) {
					continue;
				}

				System.out.println("Informe o valor ");
				Double valor = sc.nextDouble();

				ContaBo contaB = new ContaBo(contaPaga);
				contaB.transferir(contaRecebe, valor);

			} else if (opcao == 3) {
				System.out.println("Número da sua conta: ");
				String numContaPaga = sc.next();

				Conta conta = Dados.buscaConta(numContaPaga);

				if (conta == null) {
					continue;
				}

				System.out.println("Qual o valor R$");
				Double valor = sc.nextDouble();

				ContaBo contaB = new ContaBo(conta);
				contaB.deposito(valor);
			} else if (opcao == 4) {
				System.out.println("Número da sua conta: ");
				String numContaPaga = sc.next();

				Conta conta = Dados.buscaConta(numContaPaga);

				if (conta == null) {
					continue;
				}
				ContaBo contaB = new ContaBo(conta);
				contaB.exibeSaldo();

			} else if (opcao == 5) {
				List<Conta> listConta = Dados.buscarTodasAsContas();

				for (Conta conta : listConta) {
					ContaBo contaB = new ContaBo(conta);
					contaB.debitoCredito();
				}

			} else if (opcao == 6) {
				System.out.println("Número da sua conta: ");
				String numConta = sc.next();
				Conta conta = Dados.buscaConta(numConta);

				System.out.println("Qual o tipo de pix \n1 para cpf \n2 email \n3 para telefone \n4 para aleatorio");
				int tipoPix = sc.nextInt();

				if (conta == null) {
					continue;
				}

				String chavePix;
				TipoChavePix tcp = null;
				if (tipoPix == 4) {
					chavePix = UUID.randomUUID().toString();
					tcp = TipoChavePix.aleatorio;
				} else if (tipoPix == 1) {
					chavePix = conta.getCliente().getCpf();
					tcp = TipoChavePix.cpf;
				} else {
					System.out.println("Informe a chave: ");
					chavePix = sc.next();
					if (tipoPix == 2) {
						tcp = TipoChavePix.email;
					} else if (tipoPix == 3) {
						tcp = TipoChavePix.telefone;
					} else {
						System.out.println("Opção invalida!");
					}
				}

				Pix pix = new Pix();
				pix.setAtivo(true);
				pix.setTipoChave(tcp);
				pix.setChave(chavePix);
				pix.setTipoChave(tcp);

				ContaBo contaBo = new ContaBo(conta);
				conta.setPix(pix);
				System.out.println(pix.getChave());
				// contaBo.adicionarPix(pix);
			} else if (opcao == 7) {
				System.out.println("Número da conta recebedora: ");
				String numConta = sc.next();
				Conta conta = Dados.buscaConta(numConta);
				if (conta == null) {
					continue;
				}
				System.out.print("Informe a chave do pix: ");
				String chavePix = sc.next();

				Conta contaRecebe = Dados.getContaPorPix(chavePix);

				if (contaRecebe == null) {
					System.out.println("Chave pix não encontrada");
					continue;
				}

				System.out.print("Informe o valor: R$");
				double valor = sc.nextDouble();

				ContaBo contaB = new ContaBo(conta);
				contaB.transferir(contaRecebe, valor);

			} else if (opcao == 8) {
				System.out.println("Saindo ...");
				System.exit(0);
			} else {
				System.out.println("Opcao invalida!");
			}
		}

	}

	public static Cliente cadastrarCliente(Scanner sc) {
		ClienteBo clienteB = new ClienteBo();
		System.out.println("Informe seu nome: ");
		String nome = sc.next();
		System.out.println("Informe seu cpf: ");
		String cpf = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		System.out.println("Informe sua data de nascimento: ");
		String data = sc.next();
		Date dataNascimento;
		System.out.println("Informe sua Rua: ");
		String logradouro = sc.next();
		System.out.println("Informe o numero da casa: ");
		String numero = sc.next();
		System.out.println("Informe seu bairro: ");
		String bairro = sc.next();
		System.out.println("Informe seu cep: ");
		String cep = sc.next();
		System.out.println("Informe sua cidade: ");
		String cidade = sc.next();
		System.out.println("Informe seu estado: ");
		String estado = sc.next();
		while (cpf.length() != 11 || !cpf.matches("[0-9]*") || cep.length() != 8 || !cep.matches("[0-9]*")) {
			if (cpf.length() != 11 || !cpf.matches("[0-9]*")) {
				System.out.println("CPF invalido tente novamente! \nCPF: ");
				cpf = sc.next();
			} else {
				System.out.println("Cep invalido tente novamente! \nCep: ");
				cep = sc.next();
			}
		}
		try {
			Endereco endereco = new Endereco(logradouro, numero, bairro, cep, cidade, estado);
			dataNascimento = sdf.parse(data);
			return clienteB.cadastraCliente(nome, cpf, dataNascimento, endereco);

		} catch (ParseException e) {
			System.out.println("Cliente não cadastrado");
			return null;
		}

	}

}
