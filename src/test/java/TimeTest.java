import Jogador.*;
import Time.Time;
import Util.Util;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TimeTest extends TestCase {
    public TimeTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TimeTest.class);
    }

    public void test_getArtilheiro() {
        Time time = Util.criaTimeAleatorio();
        assertNull(time.getArtilheiro()); // Nenhum gol, retorna null

        Atacante atc = Util.getRandomAtacante(time.getAtacantes());
        atc.aumentaNumeroGols();
        assertNotNull(time.getArtilheiro()); // Um gol, verifica retornou algo
        assertEquals(atc.getNome(), time.getArtilheiro().getNome()); // Verifica que mesma pessoa que tem mais gols eh o artilheiro
    }

    public void test_numCamisaJaUtilizado() {
        Time time = new Time("Timao");
        Atacante atacante = new Atacante("Garotao", 1, 1, 1);
        assertFalse(time.numCamisaJaUtilizado(1));
        assertFalse(time.numCamisaJaUtilizado(atacante));

        time.adicionaAtacante(atacante);

        assertTrue(time.numCamisaJaUtilizado(1));
        assertTrue(time.numCamisaJaUtilizado(atacante));
    }

    public void test_getJogadores() {
        Time time = new Time("Timao");
        assertEquals(0, time.getJogadores().size());

        Goleiro goleiro = Util.criaGoleiroAleatorio();
        time.adicionaGoleiro(goleiro);
        assertEquals(1, time.getJogadores().size());
        assertEquals(time.getJogadores().get(0).getNome(), goleiro.getNome());
    }

    public void test_removeJogador() {
        Time time = Util.criaTimeAleatorio();
        int nInicialJogadores = time.getJogadores().size();
        assertTrue(nInicialJogadores > 0);

        assertTrue(
            time.removeJogador( // Remover jogador retorna true
                time.getDefensores().get(0).getNumCamisa() // Por ter sido criado aleatoriamente, usa o numero da camiseta de alguem que ja existe
            )
        );

        assertTrue(time.getJogadores().size() < nInicialJogadores);
    }

    public void test_getSomaNotaJogadores() {
        Time time = new Time("Timao");
        assertEquals(0, time.getSomaNotaJogadores());

        Atacante atc = Util.criaAtacanteAleatorio();
        Defensor def = Util.criaDefensorAleatorio();
        Goleiro gol = Util.criaGoleiroAleatorio();

        time.adicionaAtacante(atc);
        time.adicionaDefensor(def);
        time.adicionaGoleiro(gol);

        assertEquals(
            (atc.getNota() + def.getNota() + gol.getNota()),
            time.getSomaNotaJogadores()
        );
    }

    public void test_addPontos() {
        Time time = Util.criaTimeAleatorio();
        assertEquals(0, time.getPontos());

        time.addPontos("vitoria");
        assertEquals(3, time.getPontos());

        time.addPontos("empate");
        assertEquals(4, time.getPontos());
    }
}
