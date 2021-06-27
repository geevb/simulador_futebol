package Partida;

import Jogador.Atacante;
import Jogador.Defensor;
import Jogador.Goleiro;
import Time.Time;
import Util.Util;

import java.util.List;

public class Partida {
    private final Time timeDaCasa;
    private final Time timeDeFora;
    private final Resultado resultadoTimeDaCasa = new Resultado();
    private final Resultado resultadoTimeDeFora = new Resultado();
    private final int duracao = 90;

    public Partida(Time timeDaCasa, Time timeDeFora) {
        this.timeDaCasa = timeDaCasa;
        this.timeDeFora = timeDeFora;
    }

    public static int simulaJogada(Atacante atacante, Defensor defensor, Goleiro goleiro) { // Static soh pra poder testar sem criar um Time
        if (atacante.getNota() > defensor.getNota()) {
            if (atacante.getNota() > goleiro.getNota()) {
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
        System.out.println(">>> Resultado <<<");
        System.out.println("Time (" + timeDaCasa.getNome() + "): " + resultadoTimeDaCasa);
        System.out.println("Time (" + timeDeFora.getNome() + "): " + resultadoTimeDeFora);
        System.out.println("Resultado: " + (timeVencedor == null ? "Empate" : timeVencedor.getNome() + " venceu"));
        System.out.println("-------------------------------------");
    }

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

    public int getDuracao() {
        return duracao;
    }
}

