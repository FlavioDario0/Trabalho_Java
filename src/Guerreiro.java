public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome, 120, 15, 10, 1);
    }

    public Guerreiro(Guerreiro outro) {
        super(outro);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        System.out.println(nome + " usa GOLPE DE ESPADA!");
        alvo.receberDano(ataque * 2);
    }
}
