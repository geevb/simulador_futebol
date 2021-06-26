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
        Goleiro goleiro = new Goleiro("Joaozinho", 999, 180, 75);
        assertEquals(450, goleiro.getNota()); // calculaNota eh protected, mas da pra acessar o valor atraves do valor final do atrib. nota
    }
}
