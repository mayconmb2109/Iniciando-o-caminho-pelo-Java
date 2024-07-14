package cadastropoo;

import java.io.*;
import java.util.*;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class CadastroPOO {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PessoaFisicaRepo pessoaFisicaRepo = new PessoaFisicaRepo();
    private static final PessoaJuridicaRepo pessoaJuridicaRepo = new PessoaJuridicaRepo();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    incluirOpcao();
                    break;
                case 2:
                    alterarOpcao();
                    break;
                case 3:
                    excluirOpcao();
                    break;
                case 4:
                    exibirPorIdOpcao();
                    break;
                case 5:
                    exibirTodosOpcao();
                    break;
                case 6:
                    salvarDadosOpcao();
                    break;
                case 7:
                    recuperarDadosOpcao();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opcao invalida! Digite novamente.");
            }

            System.out.println();

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("==============================");
        System.out.println("1. Incluir");
        System.out.println("2. Alterar");
        System.out.println("3. Excluir");
        System.out.println("4. Exibir pelo ID");
        System.out.println("5. Exibir todos");
        System.out.println("6. Salvar dados");
        System.out.println("7. Recuperar dados");
        System.out.println("0. Sair");
        System.out.println("==============================");
        System.out.print("Escolha uma opcao: ");
        
    }

    private static void incluirOpcao() {
        System.out.println("### Incluir ###");
        System.out.println("Escolha o tipo:");
        System.out.println("F. Pessoa Fisica");
        System.out.println("J. Pessoa Juridica");
        char tipo = scanner.next().charAt(0);
        scanner.nextLine(); // Limpar o buffer do scanner

        switch (Character.toUpperCase(tipo)) {
            case 'F':
                incluirPessoaFisica();
                break;
            case 'J':
                incluirPessoaJuridica();
                break;
            default:
                System.out.println("Opcao invalida!");
        }
    }

    private static void incluirPessoaFisica() {
        System.out.print("Digite o ID da Pessoa Fisica: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Digite o nome da Pessoa Fisica: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF da Pessoa Fisica: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a idade da Pessoa Fisica: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        PessoaFisica pessoaFisica = new PessoaFisica(id, nome, cpf, idade);
        pessoaFisicaRepo.inserir(pessoaFisica);
        System.out.println("Pessoa Fisica incluida com sucesso!");
    }

    private static void incluirPessoaJuridica() {
        System.out.print("Digite o ID da Pessoa Juridica: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Digite o nome da Pessoa Juridica: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CNPJ da Pessoa Juridica: ");
        String cnpj = scanner.nextLine();

        PessoaJuridica pessoaJuridica = new PessoaJuridica(id, nome, cnpj);
        pessoaJuridicaRepo.inserir(pessoaJuridica);
        System.out.println("Pessoa Juridica incluida com sucesso!");
    }

    private static void alterarOpcao() {
        System.out.println("### Alterar ###");
        System.out.println("Escolha o tipo:");
        System.out.println("1. Pessoa Fisica");
        System.out.println("2. Pessoa Juridica");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        switch (tipo) {
            case 1:
                alterarPessoaFisica();
                break;
            case 2:
                alterarPessoaJuridica();
                break;
            default:
                System.out.println("Opcao invalida!");
                break;
        }
    }

    private static void alterarPessoaFisica() {
        System.out.print("Digite o ID da Pessoa Fisica para alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        PessoaFisica pessoaExistente = pessoaFisicaRepo.obter(id);
        if (pessoaExistente != null) {
            System.out.println("Dados atuais da Pessoa Fisica:");
            exibirPessoa(pessoaExistente);

            System.out.print("Digite o novo nome da Pessoa Fisica: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o novo CPF da Pessoa Fisica: ");
            String cpf = scanner.nextLine();
            System.out.print("Digite a nova idade da Pessoa Fisica: ");
            int idade = scanner.nextInt();
            scanner.nextLine();

            PessoaFisica pessoaAtualizada = new PessoaFisica(id, nome, cpf, idade);
            pessoaFisicaRepo.alterar(pessoaAtualizada);
            System.out.println("Pessoa Fisica alterada com sucesso!");
        } else {
            System.out.println("Pessoa Fisica nao encontrada para o ID informado.");
        }
    }

    private static void alterarPessoaJuridica() {
        System.out.print("Digite o ID da Pessoa Juridica para alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        PessoaJuridica pessoaExistente = pessoaJuridicaRepo.obter(id);
        if (pessoaExistente != null) {
            System.out.println("Dados atuais da Pessoa Juridica:");
            exibirPessoa(pessoaExistente);

            System.out.print("Digite o novo nome da Pessoa Juridica: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o novo CNPJ da Pessoa Juridica: ");
            String cnpj = scanner.nextLine();

            PessoaJuridica pessoaAtualizada = new PessoaJuridica(id, nome, cnpj);
            pessoaJuridicaRepo.alterar(pessoaAtualizada);
            System.out.println("Pessoa Juridica alterada com sucesso!");
        } else {
            System.out.println("Pessoa Juridica nao encontrada para o ID informado.");
        }
    }

    private static void excluirOpcao() {
        System.out.println("### Excluir ###");
        System.out.println("Escolha o tipo:");
        System.out.println("1. Pessoa Fisica");
        System.out.println("2. Pessoa Juridica");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o ID da entidade para excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        switch (tipo) {
            case 1:
                pessoaFisicaRepo.excluir(id);
                System.out.println("Pessoa Fisica excluida com sucesso!");
                break;
            case 2:
                pessoaJuridicaRepo.excluir(id);
                System.out.println("Pessoa Juridica excluida com sucesso!");
                break;
            default:
                System.out.println("Opcao invalida!");
                break;
        }
    }

    private static void exibirPorIdOpcao() {
        System.out.println("### Exibir por ID ###");
        System.out.println("Escolha o tipo:");
        System.out.println("1. Pessoa Fisica");
        System.out.println("2. Pessoa Juridica");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o ID da entidade para exibir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        switch (tipo) {
            case 1:
                {
                    PessoaFisica pessoa = pessoaFisicaRepo.obter(id);
                    if (pessoa != null) {
                        System.out.println("Dados da Pessoa Fisica:");
                        exibirPessoa(pessoa);
                    } else {
                        System.out.println("Pessoa Fisica nao encontrada para o ID informado.");
                    }       break;
                }
            case 2:
                {
                    PessoaJuridica pessoa = pessoaJuridicaRepo.obter(id);
                    if (pessoa != null) {
                        System.out.println("Dados da Pessoa Juridica:");
                        exibirPessoa(pessoa);
                    } else {
                        System.out.println("Pessoa Juridica nao encontrada para o ID informado.");
                    }       break;
                }
            default:
                System.out.println("Opcao invalida!");
                break;
        }
    }

    private static void exibirTodosOpcao() {
        System.out.println("### Exibir todos ###");
        System.out.println("Escolha o tipo:");
        System.out.println("1. Pessoa Fisica");
        System.out.println("2. Pessoa Juridica");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        switch (tipo) {
            case 1:
                {
                    List<PessoaFisica> pessoas = pessoaFisicaRepo.obterTodos();
                    System.out.println("Lista de Pessoas Fisicas:");
                    for (PessoaFisica pessoa : pessoas) {
                        exibirPessoa(pessoa);
                    }       break;
                }
            case 2:
                {
                    List<PessoaJuridica> pessoas = pessoaJuridicaRepo.obterTodos();
                    System.out.println("Lista de Pessoas Juridicas:");
                    for (PessoaJuridica pessoa : pessoas) {
                        exibirPessoa(pessoa);
                    }       break;
                }
            default:
                System.out.println("Opcao invalida!");
                break;
        }
    }

    private static void salvarDadosOpcao() {
        System.out.println("### Salvar dados ###");
        System.out.print("Digite o prefixo dos arquivos: ");
        String prefixo = scanner.nextLine();

        try {
            pessoaFisicaRepo.persistir(prefixo + ".fisica.bin");
            pessoaJuridicaRepo.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso nos arquivos " + prefixo + ".fisica.bin e " + prefixo + ".juridica.bin");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void recuperarDadosOpcao() {
        System.out.println("### Recuperar dados ###");
        System.out.print("Digite o prefixo dos arquivos: ");
        String prefixo = scanner.nextLine();

        try {
            pessoaFisicaRepo.recuperar(prefixo + ".fisica.bin");
            pessoaJuridicaRepo.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso dos arquivos " + prefixo + ".fisica.bin e " + prefixo + ".juridica.bin");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }

    private static void exibirPessoa(Pessoa pessoa) {
        System.out.println("ID: " + pessoa.getId());
        System.out.println("Nome: " + pessoa.getNome());
        if (pessoa instanceof PessoaFisica pf) {
            System.out.println("CPF: " + pf.getCpf());
            System.out.println("Idade: " + pf.getIdade());
        } else if (pessoa instanceof PessoaJuridica pj) {
            System.out.println("CNPJ: " + pj.getCnpj());
        }
        System.out.println("--------------------");
    }
}
