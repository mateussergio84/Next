package br.com.next.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import br.com.next.beans.Apolice;
import br.com.next.beans.Cartao;
import br.com.next.beans.CartaoCredito;
import br.com.next.beans.CartaoDebito;
import br.com.next.beans.Cliente;
import br.com.next.beans.Compra;
import br.com.next.beans.Conta;
import br.com.next.beans.Endereco;
import br.com.next.beans.Pix;
import br.com.next.beans.Seguro;
import br.com.next.beans.TipoChavePix;
import br.com.next.beans.TipoCliente;
import br.com.next.beans.TipoConta;
import br.com.next.bo.ApoliceBo;
import br.com.next.bo.ClienteBo;
import br.com.next.bo.ContaBo;
import br.com.next.bo.SeguroBo;
import br.com.next.utils.Dados;

public class Application {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// boolean isCred = false;

		int opcao = 5;
		System.out.println("Bem vido ao Next");
		while (opcao != 0) {
			System.out.println(
					"----------------------------\n1 - Cadastro Cliente \n2 - Login \n3 - Sair \n----------------------------");
			opcao = sc.nextInt();
			if (opcao == 1) {
				Cliente cliente = Application.cadastrarCliente(sc);

				if (cliente == null) {
					continue;
				}

				System.out.println("Digite 1 para conta corrente 2 para poupança 3 para ambos");
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
				ContaBo cB = new ContaBo();
				if (opcaoConta == 1) {
					Conta conta = cB.criaConta(cliente, TipoConta.CORRENTE, senha);
					System.out.println(
							"Bem vindo ao Next " + conta.getCliente().getNome() + "\nConta " + conta.getTipoConta()
									+ " criada com sucesso" + "\nNúmero da conta " + conta.getNumeroConta());
				} else if (opcaoConta == 2) {
					Conta conta = cB.criaConta(cliente, TipoConta.POUPANCA, senha);
					System.out.println(
							"Bem vindo ao Next " + conta.getCliente().getNome() + "\nConta " + conta.getTipoConta()
									+ " criada com sucesso" + "\nNúmero da conta " + conta.getNumeroConta());
				} else if (opcaoConta == 3) {
					Conta conta = cB.criaConta(cliente, TipoConta.POUPANCA, senha);
					Conta conta1 = cB.criaConta(cliente, TipoConta.CORRENTE, senha);
					System.out.println("Bem vindo ao Next " + conta.getCliente().getNome()
							+ "\nContas criada com sucesso" + "\nConta Poupança " + conta.getNumeroConta()
							+ "\nConta Poupança " + conta1.getNumeroConta());
				} else {
					System.out.println("Opção invalida! Tente novamente");
					opcaoConta = sc.nextInt();
				}
			} else if (opcao == 2) {
				System.out.println("Conta ");
				String numConta = sc.next();
				System.out.println("Senha ");
				String senha = sc.next();
				Conta conta = Dados.buscaConta(numConta);
				if (conta == null) {
					continue;
				}
				while (!numConta.equals(conta.getNumeroConta()) || !senha.equals(conta.getSenha())) {
					System.out.println("Senha invalida! Tente novamente");
					senha = sc.next();
				}

				ContaBo contaB = new ContaBo(conta);
				int opc = 9;
				while (opc != 0) {
					System.out.println("------------Menu------------"
							+ "\n1 - Deposito\n2 - Transferencia \n3 - Consulta \n4 - Cadastrar chave PIX "
							+ "\n5 - Transferencia Pix \n6 - Cartões" + "\n10 - Sair"
							+ "\n----------------------------");
					opc = sc.nextInt();
					if (opc == 1) {
						System.out.println("Qual o valor R$");
						Double valor = sc.nextDouble();
						contaB.deposito(valor);
					} else if (opc == 2) {
						System.out.println("Número da conta a receber: ");
						String numContaRecebe = sc.next();
						Conta contaRecebe = Dados.buscaConta(numContaRecebe);
						if (contaRecebe == null) {
							continue;
						}

						System.out.println("Informe o valor ");
						Double valor = sc.nextDouble();
						contaB.transferir(contaRecebe, valor);
						if (contaB.transferir(contaRecebe, valor) == false) {
							System.out.println("Valor insuficinete");
						} else {
							System.out.println(
									"Transferido com sucesso \n" + "R$" + valor + " Para conta " + numContaRecebe);
						}
					} else if (opc == 3) {
						System.out.println(contaB.exibeSaldo());
					} else if (opc == 4) {
						System.out.println(
								"Qual o tipo de pix \n1 para cpf \n2 email \n3 para telefone \n4 para aleatorio");
						int tipoPix = sc.nextInt();

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

						Pix pix = new Pix(tcp, chavePix, true);

						conta.setPix(pix);
						System.out.println(pix.getChave());

					} else if (opc == 5) {
						System.out.print("Informe a chave do pix: ");
						String chavePix = sc.next();

						Conta contaRecebe = Dados.getContaPorPix(chavePix);

						if (contaRecebe == null) {
							System.out.println("Chave pix não encontrada");
							continue;
						}

						System.out.print("Informe o valor: R$");
						double valor = sc.nextDouble();

						contaB.transferir(contaRecebe, valor);
					} else if (opc == 6) {
						int opcC = 12;
						while (opcC != 0) {
							System.out.println("----------Cartões----------"
									+ "\n1 - Solicitar cartão \n2 - Bloquear cartão \n3 - Pagar com Cartão \n"
									+ "4 - Fatura Cartão de Credito \n5 - Seguros \n6 - Recuperar seguro"
									+ "\n7 - Voltar ao menu" + " \n---------------------------");
							opcC = sc.nextInt();
							if (opcC == 1) {
								int opCartao;
								System.out.println("1 para credito 2 para debito");
								opCartao = sc.nextInt();
								System.out.println("senha: ");
								String senha1 = sc.next();
								if (opCartao == 1) {
									CartaoCredito credito = new CartaoCredito();
									try {
										System.out.println("Data de vencimento ");
										String data = sc.next();
										SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
										Date vencimento = sdf.parse(data);
										conta.getCliente().getTipo();
										if (conta.getCliente().getTipo() == TipoCliente.Comum) {
											credito = new CartaoCredito(senha, 1000, vencimento);
										} else if (conta.getCliente().getTipo() == TipoCliente.Premium) {
											credito = new CartaoCredito(senha, 5000, vencimento);
										} else if (conta.getCliente().getTipo() == TipoCliente.Super) {
											credito = new CartaoCredito(senha, 1000.5, vencimento);
										}
										System.out.println("Cartão expedido \nNúmero " + credito.getNumero()
												+ "\nBandeira " + credito.getBandeira() + "\nLimite "
												+ credito.getLimite() + "\nVencimento " + credito.getVencimento());
										ContaBo contab = new ContaBo(conta);
										contab.addCartao(credito);
									} catch (ParseException e) {
										System.out.println("Data invalida!");
									}

								} else if (opCartao == 2) {
									System.out.println("Limite mensal ");
									double limite = sc.nextDouble();
									CartaoDebito debito = new CartaoDebito(senha1, limite);
									System.out.println("Cartão expedido \nNúmero " + debito.getNumero() + "\nBandeira "
											+ debito.getBandeira() + "\nLimite " + debito.getLimiteTransacao());
									ContaBo contab = new ContaBo(conta);
									contab.addCartao(debito);
								} else {
									System.out.println("Opção invalida");
								}

							} else if (opcC == 2) {
								System.out.println("Número do cartão ");
								String numCartao = sc.next();
								System.out.println("Senha ");
								String senha2 = sc.next();
								ContaBo contab = new ContaBo(conta);
								contab.removerCartao(numCartao, senha2);

							} else if (opcC == 3) {
								System.out.println("1 - Debito\n2 - Credito");
								int opcaoCompra = sc.nextInt();
								if (opcaoCompra == 1) {
									System.out.println("Número do cartão ");
									String numCartao = sc.next();
									for (Cartao c : conta.getCartoes()) {
										if (c.getNumero().equals(numCartao)) {
											if (c.getClass().getSimpleName().toLowerCase().contains("debito")) {
												System.out.println("Valor da compra ");
												double valor = sc.nextDouble();
												// contaB.comparDebito(c, valor);
												if (contaB.comparAprovadaDebito(c, valor) == true) {
													System.out.println("Compra aprovada\n");
													contaB.exibeSaldo();
												} else {
													System.out.println("Saldo insuficiente");
												}
											}
										} else {
											System.out.println("Cartão não encontrado!");
										}
									}
								} else if (opcaoCompra == 2) {
									System.out.println("Número do cartão ");
									String numCartao = sc.next();
									Dados.isCred(numCartao, conta);
									if (Dados.isCred(numCartao, conta) != null) {
										System.out.println("Valor da compra ");
										double valor = sc.nextDouble();
										CartaoCredito CC = (CartaoCredito) Dados.isCred(numCartao, conta);
										if (contaB.compraAprovadaCredito(CC, valor) == true) {
											System.out
													.println("Compra aprovada!\nLimite disponivel R$" + CC.getLimite());
										} else {
											System.out.println("Compra não autorizada, excede seu limite ");
										}
									} else {
										System.out.println("error");
									}
								}

							} else if (opcC == 4) {
								System.out.println("Número do cartão ");
								String numCartao = sc.next();
								for (Cartao c : conta.getCartoes()) {
									if (c.getNumero().equals(numCartao)) {
										if (c.getClass().getSimpleName().toLowerCase().contains("credito")) {
											CartaoCredito cartaoC = (CartaoCredito) c;
											// contaB.exibeFatura(c);
											List<Compra> compras = cartaoC.getCompras();
											SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
											for (Compra compra : compras) {
												String dataCompra = sdf.format(compra.getDate());
												System.out.println("Compra no valor de R$" + compra.getValor()
														+ " na data de " + dataCompra);
											}
											String dataVencimento = sdf.format(cartaoC.getVencimento());
											System.out.println("Total R$" + cartaoC.getValorFatura());
											System.out.println("Vencimento " + dataVencimento);
											System.out.println("Limite R$" + cartaoC.getLimite());
										}
									} else {
										System.out.println("Cartão não encontrado!");
									}
								}

							} else if (opcC == 5) {
								System.out.println("Informe número do cartão");
								String numCartao = sc.next();
								if (Dados.isCred(numCartao, conta) != null) {
									CartaoCredito CC = (CartaoCredito) Dados.isCred(numCartao, conta);
									SeguroBo sb = new SeguroBo();
									ApoliceBo ApB = new ApoliceBo();
									System.out
											.println("Deseja ver os seguros disponiveis, digite 1 para sim 2 para não");
									int lS = sc.nextInt();
									if (lS == 1) {
										for (Seguro s : sb.carregaSeguros()) {
											System.out.println("Opção " + s.getId() + ":\nSeguro de " + s.getNome()
													+ "\n" + s.getRegras());
											System.out.println("\n------------------------\n");
										}
										System.out.println("Digite: \n1 - para Seguro de Invalidez"
												+ "\n2 - para seguro de morte \n3 - para seguro desemprego \n4 - para Sair");
										int opS = sc.nextInt();
										if (opS == 1) {
											Seguro seguro1 = sb.morte;
											Double valorApolice = 36.0;
											String descricaoCondicao = "Seguro: " + seguro1.getNome() + "\nRegras: \n"
													+ seguro1.getRegras()
													+ "Todos os nossos seguros garantem recuperação de 100% do valor investido pelo\r\n"
													+ "segurado.\r\n"
													+ "O valor só poderá ser recuperado se o prazo de carência (15 dias) for cumprido.";
											Apolice ap = ApB.addApolice("01", descricaoCondicao, valorApolice, seguro1);
											System.out.println(ApB.exibeApolice(ap));
											CC.setApolice(ap);
											contaB.compraAprovadaCredito(CC, ap.getValorApolice());
										} else if (opS == 2) {
											Seguro seguro2 = sb.invalidez;
											Double valorApolice = 36.0;
											String descricaoCondicao = "Seguro: " + seguro2.getNome() + "\nRegras: \n"
													+ seguro2.getRegras()
													+ "Todos os nossos seguros garantem recuperação de 100% do valor investido pelo\r\n"
													+ "segurado.\r\n"
													+ "O valor só poderá ser recuperado se o prazo de carência (15 dias) for cumprido.";
											Apolice ap = ApB.addApolice("02", descricaoCondicao, valorApolice, seguro2);
											System.out.println(ApB.exibeApolice(ap));
											CC.setApolice(ap);
											contaB.compraAprovadaCredito(CC, ap.getValorApolice());
										} else if (opS == 3) {
											Seguro seguro3 = sb.desemprego;
											Double valorApolice = 36.0;
											String descricaoCondicao = "Seguro: " + seguro3.getNome() + "\nRegras: \n"
													+ seguro3.getRegras()
													+ "Todos os nossos seguros garantem recuperação de 100% do valor investido pelo\r\n"
													+ "segurado.\r\n"
													+ "O valor só poderá ser recuperado se o prazo de carência (15 dias) for cumprido.";
											Apolice ap = ApB.addApolice("03", descricaoCondicao, valorApolice, seguro3);
											System.out.println(ApB.exibeApolice(ap));
											CC.setApolice(ap);
											contaB.compraAprovadaCredito(CC, ap.getValorApolice());
										}
									}

								}

							} else if (opcC == 6) {
								System.out.println("Informe número do cartão");
								String numC = sc.next();
								CartaoCredito CC = (CartaoCredito) Dados.isCred(numC, conta);
								if (Dados.isCred(numC, conta) != null && CC.getApolice() != null) {
									Date data = new Date();
									Date dataC = CC.getApolice().getDataCarrencia();
									int comparacao = data.compareTo(dataC);
									if (comparacao > 0) {
										conta.setSaldo(conta.getSaldo() + CC.getApolice().getValorApolice());
										System.out.println("Valor da apolice ressarcido");
									} else {
										System.out.println("Invalido!");
									}
								} else {
									System.out.println("Operação invalida!");
								}
							} else if (opcC == 7) {
								break;
							} else {
								System.out.println("Indisponivel");
							}
						}
					} else if (opc == 10) {
						break;
					} else {
						System.out.println("Opção invalida!");
					}
				}

			} else if (opcao == 3) {
				System.out.println("Saindo...");
				System.exit(0);
			}
		}
		sc.close();
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
