package com.example.guessnumber.data.model;

import java.io.Serializable;

/**
 * Clase que guarda la configuración inicial del juego introducida por el usuario en ConfigActivity,
 * es decir, su nombre y el número de intentos que quiere tener.
 */
public class Configuracion implements Serializable {
    private String nombre;
    private int numIntentos;

    public Configuracion(String nombre, int numIntentos) {
        this.nombre = nombre;
        this.numIntentos = numIntentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumIntentos() {
        return numIntentos;
    }

    public void setNumIntentos(int numIntentos) {
        this.numIntentos = numIntentos;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "nombre='" + nombre + '\'' +
                ", numIntentos=" + numIntentos +
                '}';
    }
}
