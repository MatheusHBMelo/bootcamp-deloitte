import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaUsuario {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> usuarios = new ArrayList<>();
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

            respostaInput = input.nextInt();
            input.nextLine();

            switch (respostaInput) {

                case 1:
                    System.out.print("\nDigite o nome do novo usuario: ");
                    String nomeNovoUsuario = input.nextLine();

                    usuarios.add(nomeNovoUsuario);

                    System.out.println("\nUsuario cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("\nDigite a posição do usuario: ");
                    posicaoUsuario = input.nextInt();
                    input.nextLine();

                    if (posicaoUsuario < 0 || posicaoUsuario >= usuarios.size()) {
                        System.out.println("\nEsse usuário não existe!");
                        continue;
                    }

                    System.out.println("\nUsuario " + posicaoUsuario + ": " + usuarios.get(posicaoUsuario));
                    break;

                case 3:
                    if (usuarios.isEmpty()) {
                        System.out.println("\nA lista de usuarios está vazia!");
                        continue;
                    }

                    System.out.println("\nLista de usuarios: \n");
                    for (int i = 0; i < usuarios.size(); i++) {
                        System.out.println("Usuario " + i + ": " + usuarios.get(i));
                    }

                    break;

                case 4:
                    System.out.print("\nDigite a posição do usuario: ");
                    posicaoUsuario = input.nextInt();
                    input.nextLine();

                    if (posicaoUsuario < 0 || posicaoUsuario >= usuarios.size()) {
                        System.out.println("\nEsse usuário não existe!");
                        continue;
                    }

                    System.out.print("\nDigite o novo nome do usuario: ");
                    String novoNome = input.nextLine();

                    usuarios.set(posicaoUsuario, novoNome);

                    System.out.println("\nUsuario editado com sucesso!");
                    break;

                case 5:
                    System.out.print("\nDigite a posição do usuario: ");
                    posicaoUsuario = input.nextInt();
                    input.nextLine();

                    if (usuarios.isEmpty() || posicaoUsuario < 0 || posicaoUsuario >= usuarios.size()) {
                        System.out.println("\nEsse usuário não existe!");
                        continue;
                    }

                    usuarios.remove(posicaoUsuario);

                    System.out.println("\nUsuario removido com sucesso!");
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
