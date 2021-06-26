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

        System.out.println("Artilheiro " + time1.getNome() + ": " + time1.getArtilheiro());
        System.out.println("Artilheiro " + time2.getNome() + ": " + time2.getArtilheiro());
        System.out.println("Artilheiro " + time3.getNome() + ": " + time3.getArtilheiro());
    }
}
