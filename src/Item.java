public class Item implements Comparable<Item> {
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    public Item(String nome, String descricao, String efeito, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }

    public void usar(Personagem p) {
        switch (efeito.toLowerCase()) {
            case "cura":
                p.receberDano(-30);
                System.out.println(p.getNome() + " recuperou 30 pontos de vida!");
                break;
            case "ataque":
                System.out.println(p.getNome() + " sente seu poder aumentar!");
                break;
            default:
                System.out.println("Nada aconteceu...");
        }
        quantidade--;
    }

    public boolean estaDisponivel() {
        return quantidade > 0;
    }

    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item)) return false;
        Item outro = (Item) obj;
        return this.nome.equalsIgnoreCase(outro.nome);
    }

    @Override
    public int compareTo(Item outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public String toString() {
        return nome + " (" + efeito + ") x" + quantidade;
    }
}
