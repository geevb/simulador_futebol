package Main;

import Partida.Partida;
import Time.Time;
import Util.Util;

public class Main {
    public static void main(String[] args) {
        Time time1 = Util.criaTimeAleatorio();
        Time time2 = Util.criaTimeAleatorio();
        Time time3 = Util.criaTimeAleatorio();

        // Time 1 vs Time 2
        Partida partida = new Partida(time1, time2);
        partida.simulaPartida();

        // Time 1 vs Time 3
        partida = new Partida(time1, time3);
        partida.simulaPartida();

        // Time 2 vs Time 3
        partida = new Partida(time2, time3);
        partida.simulaPartida();

        System.out.println(time1);
        System.out.println("-----------------");
        System.out.println(time2);
        System.out.println("-----------------");
        System.out.println(time3);
        System.out.println("-----------------");
    }
}
