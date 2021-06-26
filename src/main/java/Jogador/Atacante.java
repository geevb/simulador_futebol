package Jogador;

public class Atacante extends Jogador {
    private final int velocidade;
    private final int tecnica;
    private int gols = 0;

    public Atacante(String nome, int numCamisa, int velocidade, int tecnica) {
        super(nome, numCamisa);
        this.velocidade = velocidade;
        this.tecnica = tecnica;
        this.nota = this.calculaNota();
    }

    public void aumentaNumeroGols() {
        this.gols++;
    }

    @Override
    protected int calculaNota() {
        return this.velocidade * 4 + this.tecnica * 6;
    }

    public int getGols() {
        return gols;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
