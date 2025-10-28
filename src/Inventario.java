import java.util.*;

public class Inventario {
    private List<Item> itens = new ArrayList<>();

    public Inventario() {}

    public Inventario(Inventario outro) {
        for (Item i : outro.itens) {
            itens.add(new Item(i.getNome(), "", "", i.getQuantidade()));
        }
    }

    public void adicionarItem(Item novo) {
        for (Item i : itens) {
            if (i.equals(novo)) {
                i = new Item(i.getNome(), "", "", i.getQuantidade() + novo.getQuantidade());
                return;
            }
        }
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
