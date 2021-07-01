import Jogador.Goleiro;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GoleiroTest extends TestCase {
    public GoleiroTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(GoleiroTest.class);
    }

    public void test_calculaNota() {
        Goleiro goleiro = new Goleiro("Joaozinho", 999, 210, 1);
        assertEquals(406, goleiro.getNota()); // Reflexo 1 * 6 + 100 * 4 da normalizacao
    }
}
