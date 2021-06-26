import Jogador.Defensor;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DefensorTest extends TestCase {
    public DefensorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(DefensorTest.class);
    }

    public void test_calculaNota() {
        Defensor defensor = new Defensor("Joaozinho", 999, 100, 100);
        assertEquals(1000, defensor.getNota()); // calculaNota eh protected, mas da pra acessar o valor atraves do valor final do atrib. nota
    }
}
