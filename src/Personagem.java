import java.util.Random;

public abstract class Personagem {
    protected String nome;
    protected int pontosVida;
    protected int ataque;
    protected int defesa;
    protected int nivel;
    protected Inventario inventario;

    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    public Personagem(Personagem outro) {
        this.nome = outro.nome;
        this.pontosVida = outro.pontosVida;
        this.ataque = outro.ataque;
        this.defesa = outro.defesa;
        this.nivel = outro.nivel;
        this.inventario = new Inventario(outro.inventario);
    }

    public boolean estaVivo() {
        return pontosVida > 0;
    }

    public void receberDano(int dano) {
        pontosVida -= dano;
        if (pontosVida < 0) pontosVida = 0;
    }

    public void atacar(Personagem alvo) {
        Random rand = new Random();
        int dado = rand.nextInt(6) + 1;
        int dano = (ataque + dado) - alvo.defesa;
        if (dano > 0) {
            alvo.receberDano(dano);
            System.out.println(nome + " causa " + dano + " de dano a " + alvo.nome + "!");
        } else {
            System.out.println(nome + " atacou, mas " + alvo.nome + " bloqueou o golpe!");
        }
    }

    public abstract void habilidadeEspecial(Personagem alvo);

    public String getNome() { return nome; }
    public int getPontosVida() { return pontosVida; }
    public Inventario getInventario() { return inventario; }
}
