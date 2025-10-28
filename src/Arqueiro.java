public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        super(nome, 100, 18, 7, 1);
    }

    public Arqueiro(Arqueiro outro) {
        super(outro);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        System.out.println(nome + " dispara CHUVA DE FLECHAS!");
        alvo.receberDano(ataque + 10);
    }
}
