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
        Atacante atcForte = new Atacante("Pedrin", 999, 999, 999);
        Atacante atcFraco = new Atacante("Pedrin", 999, 1, 1);

        Defensor defForte = new Defensor("Pedrin", 999, 999, 999);
        Defensor defFraco = new Defensor("Pedrin", 999, 1, 1);

        Goleiro golForte = new Goleiro("Pedrin", 999, 999, 9999);
        Goleiro golFraco = new Goleiro("Pedrin", 999, 100, 1);

        assertEquals(1, Partida.simulaJogada(atcForte, defFraco, golFraco)); // Gol
        assertEquals(2, Partida.simulaJogada(atcForte, defFraco, golForte)); // Defesa
        assertEquals(3, Partida.simulaJogada(atcFraco, defForte, golFraco)); // Roubo de bola
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
    }
}
