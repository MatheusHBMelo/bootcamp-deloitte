import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaUsuario {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> usuarios = new ArrayList<>();
        int respostaInput;

        do {
            System.out.println("\nDigite qual operação deseja realizar: \n");
            System.out.println("1 - Criar usuario");
            System.out.println("2 - Listar usuario");
            System.out.println("3 - Listar usuarios");
            System.out.println("4 - Editar usuario");
            System.out.println("5 - Apagar usuario");
            System.out.println("6 - Sair\n");

            respostaInput = input.nextInt();
            input.nextLine();

            if (respostaInput == 1) {
                System.out.println("\nDigite o nome do novo usuario: ");
                String nomeNovoUsuario = input.nextLine();
                usuarios.add(nomeNovoUsuario);
                System.out.println("Usuario cadastrado com sucesso!\n");
            } else if (respostaInput == 2) {
                System.out.println("\nDigite a posição do usuario: ");
                int posicaoUsuario = input.nextInt();
                input.nextLine();
                if (posicaoUsuario > usuarios.size()) {
                    System.out.println("Esse usuário não existe!");
                    continue;
                }
                System.out.println("Usuario " + posicaoUsuario + ": " + usuarios.get(posicaoUsuario));
            }  else if (respostaInput == 3) {
                if (usuarios.isEmpty()) {
                    System.out.println("\nA lista de usuarios está vazia!");
                    continue;
                }
                System.out.println("\nLista de usuarios: \n");
                for (int i = 0; i < usuarios.size(); i++) {
                    System.out.println("Usuario " + i + ": " + usuarios.get(i));
                }
            } else if (respostaInput == 4) {
                System.out.println("\nDigite a posição do usuario: ");
                int posicaoUsuario = input.nextInt();
                input.nextLine();
                System.out.println("Digite o novo nome do usuario: ");
                String novoNome = input.nextLine();
                usuarios.set(posicaoUsuario, novoNome);
                System.out.println("Usuario editado com sucesso!");
            } else if (respostaInput == 5) {
                System.out.println("\nDigite a posição do usuario: ");
                int posicaoUsuario = input.nextInt();
                if (usuarios.isEmpty() || posicaoUsuario > usuarios.size()) {
                    System.out.println("\nNão existe");
                    continue;
                }
                usuarios.remove(posicaoUsuario);
                System.out.println("Usuario removido com sucesso!");
            }
        } while (respostaInput != 6);
        input.close();
    }
}
