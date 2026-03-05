import java.util.Scanner;

public class SistemaUsuario {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GerenciadorUsuario gerenciadorUsuario = new GerenciadorUsuario();
        int respostaInput;
        int posicaoUsuario;

        do {
            System.out.println("\n1 - Criar usuario");
            System.out.println("2 - Listar usuario");
            System.out.println("3 - Listar usuarios");
            System.out.println("4 - Editar usuario");
            System.out.println("5 - Apagar usuario");
            System.out.println("6 - Sair\n");

            System.out.print("Digite qual operação deseja realizar: ");
            try {
                String entrada = input.nextLine();
                respostaInput = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                respostaInput = 0;
            }

            switch (respostaInput) {
                case 1:
                    System.out.print("\nNome: ");
                    String nome = input.nextLine();
                    System.out.print("Email: ");
                    String email = input.nextLine();
                    System.out.print("Senha: ");
                    String senha = input.nextLine();

                    gerenciadorUsuario.adicionarUsuario(new Usuario(nome, email, senha));
                    break;
                case 2:
                    System.out.print("\nDigite a posição do usuario: ");
                    posicaoUsuario = input.nextInt();
                    input.nextLine();

                    gerenciadorUsuario.listarUsuario(posicaoUsuario);
                    break;
                case 3:
                    gerenciadorUsuario.listarTodosUsuarios();
                    break;
                case 4:
                    System.out.print("\nDigite a posição do usuario: ");
                    posicaoUsuario = input.nextInt();
                    input.nextLine();

                    if (gerenciadorUsuario.posicaoInvalida(posicaoUsuario)) {
                        System.out.println("\nErro: Usuário na posição " + posicaoUsuario + " não existe!");
                        break;
                    }

                    System.out.print("\nNovo nome: ");
                    String novoNome = input.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = input.nextLine();
                    System.out.print("Nova senha: ");
                    String novaSenha = input.nextLine();

                    gerenciadorUsuario.editarUsuario(posicaoUsuario, new Usuario(novoNome, novoEmail, novaSenha));
                    break;
                case 5:
                    System.out.print("\nDigite a posição que deseja apagar: ");
                    posicaoUsuario = input.nextInt();
                    input.nextLine();

                    gerenciadorUsuario.removerUsuario(posicaoUsuario);
                    break;
                case 6:
                    System.out.println("\nPrograma encerrado com sucesso!");
                    break;
                default:
                    System.out.println("\nOpção inválida! Escolha entre 1 e 6.");
            }
        } while (respostaInput != 6);
        input.close();
    }
}
