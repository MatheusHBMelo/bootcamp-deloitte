import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuario {
    public List<Usuario> listaDeUsuarios = new ArrayList<>();

    public void adicionarUsuario(Usuario usuario) {
        listaDeUsuarios.add(usuario);
        System.out.println("\nUsuário adicionado com sucesso!");
    }

    public void listarUsuario(int posicao) {
        if (posicaoInvalida(posicao)) {
            System.out.println("\nErro: Usuário na posição " + posicao + " não existe!");
            return;
        }
        System.out.println("\nUsuário " + posicao + " - " + listaDeUsuarios.get(posicao));
    }

    public void listarTodosUsuarios() {
        if (listaDeUsuarios.isEmpty()) {
            System.out.println("\nA lista de usuários está vazia!");
            return;
        }
        System.out.println("\nLista de Usuários:");
        for (int i = 0; i < listaDeUsuarios.size(); i++) {
            System.out.println("Usuário " + i + " - " + listaDeUsuarios.get(i));
        }
    }

    public void removerUsuario(int posicao) {
        if (posicaoInvalida(posicao)) {
            System.out.println("\nErro: Usuário na posição " + posicao + " não existe!");
            return;
        }
        listaDeUsuarios.remove(posicao);
        System.out.println("\nUsuário removido com sucesso!");
    }

    public void editarUsuario(int posicao, Usuario novoUsuario) {
        listaDeUsuarios.set(posicao, novoUsuario);
        System.out.println("\nUsuário editado com sucesso!");
    }

    public boolean posicaoInvalida(int posicao) {
        return posicao < 0 || posicao >= listaDeUsuarios.size();
    }
}
