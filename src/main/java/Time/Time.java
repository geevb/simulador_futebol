package Time;

import Jogador.*;
import java.util.ArrayList;
import java.util.List;

public class Time {
    private List<Atacante> atacantes;
    private List<Defensor> defensores;
    private Goleiro goleiro;
    private String nome;

    public Time (String nome) {
        this.atacantes = new ArrayList<>();
        this.defensores = new ArrayList<>();
        this.goleiro = null;
        this.nome = nome;
    }

    public Time (List<Atacante> atacantes, List<Defensor> defensores, Goleiro goleiro, String nome) {
       this.atacantes = atacantes;
       this.defensores = defensores;
       this.goleiro = goleiro;
       this.nome = nome;
    }

    public List<Atacante> getAtacantes() {
        return atacantes;
    }

    public List<Defensor> getDefensores() {
        return defensores;
    }

    public Goleiro getGoleiro() {
        return goleiro;
    }

    public String getNome() {
        return nome;
    }

    public Atacante getArtilheiro() {
        Atacante artilheiro = null;
        int maxGols = 0;
        for (var atc : atacantes) {
            if (atc.getGols() > maxGols) {
                artilheiro = atc;
            }
        }

        return artilheiro;
    }

    public boolean removeJogador(int numCamisa) {
        boolean removeu;

        removeu = atacantes.removeIf(atq -> atq.getNumCamisa() == numCamisa);
        if (!removeu) defensores.removeIf(def -> def.getNumCamisa() == numCamisa);
        if (!removeu && goleiro.getNumCamisa() == numCamisa) this.goleiro = null;

        return removeu;
    }

    public boolean numCamisaJaUtilizado(int numCamisa) {
        return getJogadores().stream().anyMatch(j -> j.getNumCamisa() == numCamisa);
    }

    public boolean numCamisaJaUtilizado(Jogador jogador) {
        return getJogadores().stream().anyMatch(j -> j.getNumCamisa() == jogador.getNumCamisa());
    }

    public List<Jogador> getJogadores() {
        List<Jogador> jogadores = new ArrayList<>();
        jogadores.addAll(getAtacantes());
        jogadores.addAll(getDefensores());
        if (getGoleiro() != null) jogadores.add(getGoleiro());
        return jogadores;
    }

    public List<Atacante> adicionaAtacante(Atacante atacante) {
        if (numCamisaJaUtilizado(atacante)) return null;
        atacantes.add(atacante);
        return atacantes;
    }

    public List<Defensor> adicionaDefensor(Defensor defensor) {
        if (numCamisaJaUtilizado(defensor)) return null;
        defensores.add(defensor);
        return defensores;
    }

    public Goleiro adicionaGoleiro(Goleiro goleiro) {
        if (numCamisaJaUtilizado(goleiro)) return null;
        this.goleiro = goleiro;
        return this.goleiro;
    }
}
