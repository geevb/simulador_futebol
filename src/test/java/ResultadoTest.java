import Partida.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ResultadoTest extends TestCase {
    public ResultadoTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ResultadoTest.class);
    }

    public void test_atualizaResultado() {
        Resultado res = new Resultado();
        assertEquals(0, res.getGols());
        assertEquals(0, res.getDefesas());
        assertEquals(0, res.getRouboesDeBola());

        res.atualizaResultado(1);
        assertEquals(1, res.getGols());

        res.atualizaResultado(2);
        assertEquals(1, res.getDefesas());

        res.atualizaResultado(3);
        assertEquals(1, res.getRouboesDeBola());
    }
}
