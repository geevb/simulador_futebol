package Jogador;

public class Defensor extends Jogador {
    private int cobertura;
    private int desarme;
    private int rouboDeBola = 0;

    public Defensor(String nome, int numCamisa, int cobertura, int desarme) {
        super(nome, numCamisa);
        this.cobertura = cobertura;
        this.desarme = desarme;
        this.nota = this.calculaNota();
    }

    public void aumentaNumeroRouboDeBola() {
        this.rouboDeBola++;
    }

    public int getRouboDeBola() {
        return rouboDeBola;
    }

    @Override
    protected int calculaNota() {
        return this.cobertura * 6 + this.desarme * 4;
    }
}
