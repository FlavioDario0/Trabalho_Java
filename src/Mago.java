public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 90, 20, 5, 1);
    }

    public Mago(Mago outro) {
        super(outro);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        System.out.println(nome + " conjura BOLA DE FOGO!");
        alvo.receberDano(ataque * 2);
    }
}
