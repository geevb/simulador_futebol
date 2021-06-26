package Jogador;

public class Goleiro extends Jogador {
    private final int altura; // Em cm
    private final int reflexo;
    private int defesas = 0;

    public Goleiro(String nome, int numCamisa, int altura, int reflexo) {
        super(nome, numCamisa);
        this.altura = altura;
        this.reflexo = reflexo;
        this.nota = this.calculaNota();
    }

    private int normalizaAltura(int altura) {
        int vMax = 100;
        int vMin = 0;

        return (altura / 210) * (vMax - vMin) + vMin;
    }

    public void aumentaNumeroDefesas() {
        this.defesas++;
    }

    @Override
    protected int calculaNota() {
        return this.normalizaAltura(this.altura) * 4 + this.reflexo * 6;
    }

    @Override
    public String toString() {
        return "Altura: " + getAltura() + ", Reflexo: " + getReflexo() + ", Defesas: " + getDefesas() + ", Nota: " + getNota();

    }
    public int getAltura() {
        return altura;
    }

    public int getReflexo() {
        return reflexo;
    }

    public int getDefesas() {
        return defesas;
    }
}
