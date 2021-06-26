import Jogador.Atacante;
import Jogador.Defensor;
import Time.Time;
import Util.Util;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;

public class UtilTest extends TestCase {
    public UtilTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(UtilTest.class);
    }

    public void test_getRandomIntInRange() {
        int i = Util.getRandomIntInRange(0, 100);
        assertTrue(i >= 0 && i <= 100);
    }

    public void test_getRandomAtacante() {
        List<Atacante> atacantes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            atacantes.add(Util.criaAtacanteAleatorio());
        }
        assertEquals(3, atacantes.size());
        
        Atacante atc = Util.getRandomAtacante(atacantes);
        assertNotNull(atc);
        assertEquals(3, atacantes.size()); // Assert length do array nao muda
    }

    public void test_getRandomDefensor() {
        List<Defensor> defensores = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            defensores.add(Util.criaDefensorAleatorio());
        }
        assertEquals(3, defensores.size());

        Defensor atc = Util.getRandomDefensor(defensores);
        assertNotNull(atc);
        assertEquals(3, defensores.size()); // Assert length do array nao muda
    }

    public void test_criaTimeAleatorio() {
        Time time = Util.criaTimeAleatorio();

        assertNotNull(time.getAtacantes());
        assertNotNull(time.getDefensores());
        assertNotNull(time.getGoleiro());

        assertTrue(time.getAtacantes().size() > 0 && time.getAtacantes().size() < 10);
        assertTrue(time.getDefensores().size() > 0 && time.getDefensores().size() < 10);

        assertEquals(10, time.getAtacantes().size() + time.getDefensores().size());
    }
}
