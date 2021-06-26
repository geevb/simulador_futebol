package Util;

import Jogador.Atacante;
import Jogador.Defensor;
import Jogador.Goleiro;
import Time.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import com.github.javafaker.Faker;

public class Util {
    private static final Faker faker = new Faker();

    public static int getRandomIntInRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static Atacante getRandomAtacante(List<Atacante> atacantes) {
        int rnd = new Random().nextInt(atacantes.size());
        return atacantes.get(rnd);
    }

    public static Defensor getRandomDefensor(List<Defensor> defensores) {
        int rnd = new Random().nextInt(defensores.size());
        return defensores.get(rnd);
    }

    public static Time criaTimeAleatorio() {
        int numAtacantes = getRandomIntInRange(1, 9);
        int numDefensores = 10 - numAtacantes; // Se atacantes = 3, defensores sera = 7.

        Time time = new Time(faker.country().name());
        for (int i = 0; i < numAtacantes; i++) {
            time.adicionaAtacante(criaAtacanteAleatorio());
        }
        for (int i = 0; i < numDefensores; i++) {
            time.adicionaDefensor(criaDefensorAleatorio());
        }

        time.adicionaGoleiro(criaGoleiroAleatorio());

        return time;
    }

    public static Atacante criaAtacanteAleatorio() {
        return new Atacante(
            faker.name().firstName(),
            getRandomIntInRange(0, 9999),
            getRandomIntInRange(1, 100),
            getRandomIntInRange(1, 100)
        );
    }

    public static Defensor criaDefensorAleatorio() {
        return new Defensor(
            faker.name().firstName(),
            getRandomIntInRange(0, 9999),
            getRandomIntInRange(1, 100),
            getRandomIntInRange(1, 100)
        );
    }

    public static Goleiro criaGoleiroAleatorio() {
        return new Goleiro(
            faker.name().firstName(),
            getRandomIntInRange(0, 9999),
            getRandomIntInRange(1, 210),
            getRandomIntInRange(1, 100)
        );
    }

}
