import Jogador.Atacante;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AtacanteTest extends TestCase {
    public AtacanteTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AtacanteTest.class);
    }

    public void test_calculaNota() {
        Atacante atacante = new Atacante("Joaozinho", 999, 100, 100);
        assertEquals(1000, atacante.getNota()); // calculaNota eh protected, mas da pra acessar o valor atraves do valor final do atrib. nota
    }
}
