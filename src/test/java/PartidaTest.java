import Jogador.Atacante;
import Jogador.Defensor;
import Jogador.Goleiro;
import Partida.*;
import Time.Time;
import Util.Util;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PartidaTest extends TestCase {
    public PartidaTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(PartidaTest.class);
    }

    public void test_simulaJogada() {
        Time time1 = new Time("time1");
        Time time2 = new Time("time2");
        Partida partida = new Partida(time1, time2);

        Atacante atcForte = new Atacante("Pedrin", 999, 999, 999);
        Atacante atcFraco = new Atacante("Pedrin", 999, 1, 1);

        Defensor defForte = new Defensor("Pedrin", 999, 999, 999);
        Defensor defFraco = new Defensor("Pedrin", 999, 1, 1);

        Goleiro golForte = new Goleiro("Pedrin", 999, 999, 9999);
        Goleiro golFraco = new Goleiro("Pedrin", 999, 100, 1);

        assertEquals(1, partida.simulaJogada(atcForte, defFraco, golFraco)); // Gol
        assertEquals(2, partida.simulaJogada(atcForte, defFraco, golForte)); // Defesa
        assertEquals(3, partida.simulaJogada(atcFraco, defForte, golFraco)); // Roubo de bola
    }

    public void test_simulaJogada_bonusDaCasa() {
        // Todos os jogadores possuem as exatas mesmas notas
        Time time1 = new Time("time1");
        Atacante atcDaCasa = new Atacante("Pedrin", 1, 999, 999);
        atcDaCasa.setNota(100);
        Defensor defDaCasa = new Defensor("Pedrin", 2, 999, 999);
        defDaCasa.setNota(100);
        Goleiro golDaCasa = new Goleiro("Pedrin", 3, 999, 9999);
        golDaCasa.setNota(100);
        time1.adicionaAtacante(atcDaCasa);
        time1.adicionaDefensor(defDaCasa);
        time1.adicionaGoleiro(golDaCasa);

        Time time2 = new Time("time2");
        Atacante atcDeFora = new Atacante("Pedrin", 1, 999, 999);
        atcDeFora.setNota(100);
        Defensor defDeFora = new Defensor("Pedrin", 2, 999, 999);
        defDeFora.setNota(100);
        Goleiro golDeFora = new Goleiro("Pedrin", 3, 999, 9999);
        golDeFora.setNota(100);
        time2.adicionaAtacante(atcDeFora);
        time2.adicionaDefensor(defDeFora);
        time2.adicionaGoleiro(golDeFora);

        Partida partida = new Partida(time1, time2);

        // Time da casa ataca, faz gol porque eh da casa
        assertEquals(1, partida.simulaJogada(atcDaCasa, defDeFora, golDeFora)); // Gol

        // Time de Fora ataca, perde a bola porque eh de fora
        assertEquals(3, partida.simulaJogada(atcDeFora, defDaCasa, golDaCasa)); // Roubo de bola
    }

    public void test_verificaVencedor() {
        Time time1 = Util.criaTimeAleatorio();
        Time time2 = Util.criaTimeAleatorio();

        Partida partida = new Partida(time1, time2);
        Resultado resTime1 = new Resultado();
        Resultado resTime2 = new Resultado();

        resTime1.atualizaResultado(1); // Adiciona um gol no Time 1
        assertEquals(
            time1.getNome(),
            partida.verificaVencedor(resTime1, resTime2).getNome()
        );

        resTime2.atualizaResultado(1); // Adiciona um gol no Time 2
        assertNull(partida.verificaVencedor(resTime1, resTime2));

        resTime2.atualizaResultado(1); // Adiciona um gol no Time 2
        assertEquals(
            time2.getNome(),
            partida.verificaVencedor(resTime1, resTime2).getNome()
        );
    }

    public void test_simulaPartida() {
        Time time1 = Util.criaTimeAleatorio();
        Time time2 = Util.criaTimeAleatorio();

        Partida partida = new Partida(time1, time2);
        Time timeVencedor = partida.simulaPartida();
        assertTrue(
    timeVencedor == null
            || timeVencedor.getNome().equals(time1.getNome())
            || timeVencedor.getNome().equals(time2.getNome())
        );

        if (timeVencedor == null) {
            assertEquals(time1.getPontos(), 1);
            assertEquals(time2.getPontos(), 1);
        } else {
            assertEquals(timeVencedor.getPontos(), 3);
        }
    }
}
