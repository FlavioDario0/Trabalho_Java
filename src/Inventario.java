import java.util.*;

public class Inventario {
    private List<Item> itens = new ArrayList<>();

    public Inventario() {}

    public Inventario(Inventario outro) {
        for (Item i : outro.itens) {
            itens.add(new Item(i.getNome(), "", "", i.getQuantidade()));
        }
    }

    // Em Inventario.java - MÉTODO CORRIGIDO
    public void adicionarItem(Item novo) {
        // Usamos um loop por índice (int i) para poder modificar a lista
        for (int i = 0; i < itens.size(); i++) {
            Item itemExistente = itens.get(i);

            // Se encontramos um item com o mesmo nome
            if (itemExistente.equals(novo)) {

                // Criamos um novo objeto Item com a quantidade somada
                // (Usando a mesma lógica que você tinha, de "" para desc/efeito)
                Item itemAtualizado = new Item(
                        itemExistente.getNome(),
                        "",
                        "",
                        itemExistente.getQuantidade() + novo.getQuantidade()
                );

                // CORREÇÃO: Usamos 'itens.set()' para substituir o item antigo
                // na posição 'i' pelo item atualizado.
                itens.set(i, itemAtualizado);
                return; // Item foi empilhado, podemos sair.
            }
        }

        // Se o loop terminou sem achar o item, adicionamos ele como novo na lista.
        itens.add(novo);
    }

    public void listarItens() {
        Collections.sort(itens);
        for (Item i : itens) System.out.println(i);
    }

    public boolean usarItem(String nome, Personagem p) {
        for (Item i : itens) {
            if (i.getNome().equalsIgnoreCase(nome) && i.estaDisponivel()) {
                i.usar(p);
                return true;
            }
        }

        System.out.println("Item não encontrado!");
        return false;
    }

}
