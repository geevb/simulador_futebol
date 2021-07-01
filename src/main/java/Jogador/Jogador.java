package Jogador;

import java.time.LocalDateTime;

public class Jogador {
    protected String nome;
    protected int nota;
    protected int numCamisa;
    private LocalDateTime dtNascimento;

    public Jogador(String nome, int numCamisa) {
        this.nome = nome;
        this.numCamisa = numCamisa;
        this.dtNascimento = LocalDateTime.now();
    }

    public Jogador(String nome, int numCamisa, LocalDateTime dtNascimento) {
        this.nome = nome;
        this.numCamisa = numCamisa;
        this.dtNascimento = dtNascimento;
    }

    protected int calculaNota() {
        return this.nota;
    }

    public int getNota() {
        return this.nota;
    }

    public void setNota(int nota) { // Usado apenas nos testes p/ facilitar...
        this.nota = nota;
    }

    public String getNome() { return this.nome; }

    public int getNumCamisa() { return this.numCamisa; }

    public void setDtNascimento(LocalDateTime dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public LocalDateTime getDtNascimento() {
        return dtNascimento;
    }
}
