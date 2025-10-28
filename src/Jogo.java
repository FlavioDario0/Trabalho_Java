import java.util.*;

public class Jogo {
    private Scanner sc = new Scanner(System.in);
    private Personagem jogador;

    public void iniciar() {
        System.out.println("🌅 Bem-vindo a ELDION - Sombras de Eldrion 🌌");
        System.out.print("Digite o nome do seu herói: ");
        String nome = sc.nextLine();

        System.out.println("Escolha sua classe:");
        System.out.println("1. Guerreiro");
        System.out.println("2. Mago");
        System.out.println("3. Arqueiro");
        int escolha = lerInt(1,3);

        switch (escolha) {
            case 1: jogador = new Guerreiro(nome); break;
            case 2: jogador = new Mago(nome); break;
            case 3: jogador = new Arqueiro(nome); break;
            default: jogador = new Guerreiro(nome);
        }

        System.out.println("\n--- PRÓLOGO ---");
        System.out.println("Há muito tempo, o Reino de Eldrion caiu nas sombras...");
        System.out.println("Agora, apenas um herói pode reunir os Fragmentos de Solaris e restaurar a luz.");

        // item inicial
        jogador.getInventario().adicionarItem(new Item("Poção de Cura", "Restaura vida", "cura", 2));

        explorarLoop();
    }

    private void explorarLoop() {
        while (jogador.estaVivo()) {
            System.out.println("\nEscolha sua ação:");
            System.out.println("1. Explorar");
            System.out.println("2. Usar item");
            System.out.println("3. Ver inventário"); // <-- opção adicionada
            System.out.println("4. Fugir");
            System.out.println("5. Sair");
            int acao = lerInt(1, 5);

            switch (acao) {
                case 1 -> encontro();
                case 2 -> usarItemFluxo();
                case 3 -> verInventarioFluxo(); // mostra inventário
                case 4 -> tentarFugir();
                case 5 -> {
                    System.out.println("Encerrando jornada...");
                    return;
                }
            }
        }

        System.out.println("\n💀 Você caiu em batalha... mas a lenda continua.");
    }

    private void encontro() {
        Random r = new Random();
        int tipo = r.nextInt(3);

        if (tipo == 0) {
            System.out.println("Você encontrou um Goblin nas Planícies de Cinza!");
            batalhar(new Inimigo("Goblin", 50, 10, 5, 1));
        } else if (tipo == 1) {
            System.out.println("Das sombras surge um Lobo da Névoa!");
            batalhar(new Inimigo("Lobo da Névoa", 70, 12, 6, 1));
        } else {
            System.out.println("Um antigo guardião desperta nas Ruínas de Varyon...");
            batalhar(new Inimigo("Guardião de Pedra", 90, 14, 8, 2));
        }
    }

    private void batalhar(Inimigo inimigo) {
        System.out.println("\n⚔️ BATALHA INICIADA contra " + inimigo.getNome());
        Random dado = new Random();

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\nSeu HP: " + jogador.getPontosVida() + " | Inimigo HP: " + inimigo.getPontosVida());
            System.out.println("1. Atacar");
            System.out.println("2. Habilidade Especial");
            System.out.println("3. Usar Item");
            System.out.println("4. Ver inventário"); // opção adicionada no menu de batalha
            System.out.println("5. Tentar fugir");
            int acao = lerInt(1, 5);

            switch (acao) {
                case 1 -> jogador.atacar(inimigo);
                case 2 -> jogador.habilidadeEspecial(inimigo);
                case 3 -> usarItemFluxo();
                case 4 -> verInventarioFluxo();
                case 5 -> {
                    if (tentarFugirBatalha()) {
                        System.out.println("Você conseguiu escapar da batalha!");
                        return;
                    } else {
                        System.out.println("Falha na fuga! O inimigo aproveita para atacar...");
                    }
                }
            }

            if (inimigo.estaVivo()) inimigo.atacar(jogador);
        }

        if (jogador.estaVivo()) {
            System.out.println("🏆 " + jogador.getNome() + " venceu o combate!");
            // recompensa simples
            jogador.getInventario().adicionarItem(new Item("Poção de Cura", "Restaura vida", "cura", 1));
        } else {
            System.out.println("☠️ " + jogador.getNome() + " foi derrotado...");
        }
    }

    private boolean tentarFugirBatalha() {
        Random r = new Random();
        int dado = r.nextInt(6) + 1;
        System.out.println("Rolagem de fuga: " + dado);
        return dado > 3;
    }

    private void tentarFugir() {
        Random r = new Random();
        int dado = r.nextInt(6) + 1;
        if (dado > 3) {
            System.out.println("Você conseguiu escapar da área por enquanto!");
        } else {
            System.out.println("Você tropeça e é atacado por criaturas das sombras!");
            jogador.receberDano(15);
        }
    }

    /** Fluxo para usar item a partir do menu principal ou de batalha */
    private void usarItemFluxo() {
        System.out.println("\n-- USAR ITEM --");
        jogador.getInventario().listarItens();
        System.out.print("Nome do item a usar (ou vazio para cancelar): ");
        String nomeItem = sc.nextLine().trim();
        if (nomeItem.isEmpty()) {
            System.out.println("Cancelado.");
            return;
        }
        boolean sucesso = jogador.getInventario().usarItem(nomeItem, jogador);
        if (!sucesso) System.out.println("Não foi possível usar o item.");
    }

    /** Mostrar inventário (fluxo) */
    private void verInventarioFluxo() {
        System.out.println("\n-- SEU INVENTÁRIO --");
        jogador.getInventario().listarItens();
    }

    private int lerInt(int min, int max) {
        while (true) {
            try {
                String linha = sc.nextLine().trim();
                int val = Integer.parseInt(linha);
                if (val >= min && val <= max) return val;
            } catch (Exception ignored) {}
            System.out.print("Valor inválido, tente novamente: ");
        }
    }

    // main
    public static void main(String[] args) {
        new Jogo().iniciar();
    }
}
