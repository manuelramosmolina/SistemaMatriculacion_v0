package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

import java.util.ArrayList;
import java.util.List;

public class CiclosFormativos {

    private int capacidad;
    private int tamano;
    private CicloFormativo[] coleccionCiclosFormativos;

    public CiclosFormativos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos();
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos() {
        CicloFormativo[] copia = new CicloFormativo[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }
        return copia;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }


    public void insertar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }
        if (buscar(cicloFormativo) != null) {
            throw new IllegalArgumentException("ERROR: El ciclo formativo ya existe.");
        }
        coleccionCiclosFormativos[tamano++] = cicloFormativo;
    }


    private int buscarIndice(CicloFormativo cicloFormativo) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                return i;
            }
        }
        return -1;
    }


    private boolean tamanoSuperado(int indice) {

        return indice >= capacidad;
    }

    private boolean capacidadSuperada(int indice) {

        return indice >= capacidad;
    }


    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) return coleccionCiclosFormativos[i];
        }
        return null;
    }
    public void borrar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        int indice = -1;
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                indice = i;
                break;
            }
        }
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: La matrícula a borrar no existe.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; i < tamano - 1; i++) {
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i + 1];
        }
        coleccionCiclosFormativos[tamano - 1] = null;
    }



}

