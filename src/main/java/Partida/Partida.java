package Partida;

import Jogador.Atacante;
import Jogador.Defensor;
import Jogador.Goleiro;
import Jogador.Jogador;
import Time.Time;
import Util.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Partida {
    private final Time timeDaCasa;
    private final Time timeDeFora;
    private final Resultado resultadoTimeDaCasa = new Resultado();
    private final Resultado resultadoTimeDeFora = new Resultado();
    private final int duracao = 90;
    private final LocalDateTime data = LocalDateTime.now();

    public Partida(Time timeDaCasa, Time timeDeFora) {
        this.timeDaCasa = timeDaCasa;
        this.timeDeFora = timeDeFora;
    }

    /**
     * Simula a jogada atual, i.e. o ataque de um time contra outro
     * Antes de simular o ataque, atribui-se pontos(nota) adicionais aos jogadores que estao jogando no timeDaCasa, A nota adicional esta definida dentro de cada Time.
     * O atacante da jogada a ser simulada tem que passar por dois checks pra fazer um gol
     * O primeiro, contra o defensor. Compara-se a nota dos dois, caso seja maior o atacante vai chutar ao gol. Caso menor, houve um rouboDeBola.
     * O segundo, contra o goleiro. Compara-se a nota dos dois, caso seja maior o atacante marcou um gol. Caso menor, houve uma defesa.
     * @param atacante O atacante do time atacando
     * @param defensor O defensor do time defendendo
     * @param goleiro O goleiro do time defendendo
     * @return int numero que significa o tipo do resultado da jogada. i.e. 1 == gol, 2 == defesa, 3 == rouboDeBola
     */
    public int simulaJogada(Atacante atacante, Defensor defensor, Goleiro goleiro) {
        int pontosAtacante = ehDaCasa(atacante, getTimeDaCasa()) ? atacante.getNota() + timeDaCasa.getBonusNotaJogadoresJogandoEmCasa() : atacante.getNota();
        int pontosDefensor = ehDaCasa(defensor, getTimeDaCasa()) ? defensor.getNota() + timeDaCasa.getBonusNotaJogadoresJogandoEmCasa() : defensor.getNota();
        int pontosGoleiro  = ehDaCasa(goleiro,  getTimeDaCasa()) ? goleiro.getNota()  + timeDaCasa.getBonusNotaJogadoresJogandoEmCasa() : goleiro.getNota();

        if (pontosAtacante > pontosDefensor) {
            if (pontosAtacante > pontosGoleiro) {
                atacante.aumentaNumeroGols();
                return 1;
            }

            goleiro.aumentaNumeroDefesas();
            return 2;
        }

        defensor.aumentaNumeroRouboDeBola();
        return 3;
    }

    public Time verificaVencedor(Resultado resTimeDaCasa, Resultado resTimeDeFora) {
        int golsTimeDaCasa = resTimeDaCasa.getGols();
        int golsTimeDeFora = resTimeDeFora.getGols();

        if (golsTimeDaCasa > golsTimeDeFora) {
            return timeDaCasa;
        }
        if (golsTimeDeFora > golsTimeDaCasa) {
            return timeDeFora;
        }

        return null;
    }

    public void printaResultadoPartida(Time timeVencedor) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
        System.out.println(">>> Resultado da Partida " + dtf.format(getData()) + " <<<");
        System.out.println("Time (" + timeDaCasa.getNome() + "): " + resultadoTimeDaCasa);
        System.out.println("Time (" + timeDeFora.getNome() + "): " + resultadoTimeDeFora);
        System.out.println("Resultado: " + (timeVencedor == null ? "Empate" : timeVencedor.getNome() + " venceu"));
        System.out.println("-------------------------------------");
    }

    /**
     * Simula a partida atual.
     * Dura 90 iteracoes (this.duracao), em cada iteracao cada time tem uma change aleatoria de atacar
     * Apos o final da duracao, computa-se o resultado final e a
     * atribui, deste resultado, a pontuacao da vitoria ou empate e a quantidade de gols aos times que disputaram a partida.
     * @return Time time que venceu a partida
     */
    public Time simulaPartida() {
        List<Atacante> atacantesTimeDaCasa = timeDaCasa.getAtacantes();
        List<Atacante> atacantesTimeDeFora = timeDeFora.getAtacantes();

        List<Defensor> defensoresTimeDaCasa = timeDaCasa.getDefensores();
        List<Defensor> defensoresTimeDeFora = timeDeFora.getDefensores();

        Goleiro goleiroTimeDaCasa = timeDaCasa.getGoleiro();
        Goleiro goleiroTimeDeFora = timeDeFora.getGoleiro();

        for (int i = 0; i < getDuracao(); i++) {
            if (Math.random() < 0.5) { //  Ataque time 1 contra time 2
                int nJogada = simulaJogada(
                    Util.getRandomAtacante(atacantesTimeDaCasa),
                    Util.getRandomDefensor(defensoresTimeDeFora),
                    goleiroTimeDeFora
                );
                resultadoTimeDaCasa.atualizaResultado(nJogada);
            } else { // Ataque time 2 contra time 1
                int nJogada = simulaJogada(
                    Util.getRandomAtacante(atacantesTimeDeFora),
                    Util.getRandomDefensor(defensoresTimeDaCasa),
                    goleiroTimeDaCasa
                );
                resultadoTimeDeFora.atualizaResultado(nJogada);
            }
        }

        Time vencedor = verificaVencedor(resultadoTimeDaCasa, resultadoTimeDeFora);
        if (vencedor == null) {
            timeDaCasa.addPontos("empate");
            timeDeFora.addPontos("empate");
        } else {
            vencedor.addPontos("vitoria");
        }

        timeDaCasa.addGols(resultadoTimeDaCasa.getGols());
        timeDeFora.addGols(resultadoTimeDeFora.getGols());
        printaResultadoPartida(vencedor);
        return vencedor;
    }

    public LocalDateTime getData() { return data; }

    public int getDuracao() {
        return duracao;
    }

    public boolean ehDaCasa(Jogador jogador, Time timeDaCasa) {
        for (var jogadorDaCasa : timeDaCasa.getJogadores()) {
            if (jogadorDaCasa == jogador) return true;
        }

        return false;
    }

    public Time getTimeDaCasa() {
        return timeDaCasa;
    }
}

