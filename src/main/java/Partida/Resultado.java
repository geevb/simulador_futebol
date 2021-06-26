package Partida;

import java.util.Map;

public class Resultado {
    private final Map<Integer, String> mapResultadosJogadas = Map.of(1, "gol", 2, "defesa", 3, "rouboDeBola");
    private int gols = 0;
    private int defesas = 0;
    private int rouboesDeBola = 0;

    public Resultado() {}
    public void atualizaResultado(int tipoJogada) {
        String jogada = mapResultadosJogadas.get(tipoJogada);
        if (jogada.equals("gol")) {
            gols++;
        }
        if (jogada.equals("defesa")) {
            defesas++;
        }
        if (jogada.equals("rouboDeBola")) {
            rouboesDeBola++;
        }
    }

    @Override
    public String toString() {
        return "Gols: " + getGols() + ", Defesas: " + getDefesas() + ", Roubos de bola: " + getRouboesDeBola();
    }

    public int getGols() {
        return gols;
    }

    public int getDefesas() {
        return defesas;
    }

    public int getRouboesDeBola() {
        return rouboesDeBola;
    }
}
