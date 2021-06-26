package Jogador;

public class Jogador {
    protected String nome;
    protected int nota;
    protected int numCamisa;

    public Jogador(String nome, int numCamisa) {
        this.nome = nome;
        this.numCamisa = numCamisa;
    }

    protected int calculaNota() {
        return this.nota;
    }

    public int getNota() {
        return this.nota;
    }

    public String getNome() { return this.nome; }

    public int getNumCamisa() { return this.numCamisa; }
}
