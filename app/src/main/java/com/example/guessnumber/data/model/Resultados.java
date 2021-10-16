package com.example.guessnumber.data.model;

import java.io.Serializable;

/**
 * Clase que guarda los resultados obtenidos por el jugador en PlayActivity, es decir, si ha
 * conseguido ganar el juego y el número de intentos que el quedaban al terminar la partida.
 */
public class Resultados implements Serializable {
    private boolean partidaGanada;
    private int intentosRestantes;

    public Resultados(boolean partidaGanada, int intentosRestantes) {
        this.partidaGanada = partidaGanada;
        this.intentosRestantes = intentosRestantes;
    }

    public boolean isPartidaGanada() {
        return partidaGanada;
    }

    public void setPartidaGanada(boolean partidaGanada) {
        this.partidaGanada = partidaGanada;
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public void setIntentosRestantes(int intentosRestantes) {
        this.intentosRestantes = intentosRestantes;
    }

    @Override
    public String toString() {
        return "Resultados{" +
                "partidaGanada=" + partidaGanada +
                ", intentosRestantes=" + intentosRestantes +
                '}';
    }
}
