public class Inimigo extends Personagem {
    public Inimigo(String nome, int vida, int atk, int def, int nivel) {
        super(nome, vida, atk, def, nivel);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        System.out.println(nome + " ataca com fúria!");
        alvo.receberDano(ataque + 5);
    }
}
