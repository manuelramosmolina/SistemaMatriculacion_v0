package org.iesalandalus.programacion.matriculacion.modelo.negocio;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

public class Asignaturas {
    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;


    public Asignaturas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAsignaturas = new Asignatura[capacidad];
    }


    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copia = new Asignatura[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Asignatura(coleccionAsignaturas[i]);
        }
        return copia;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }
        if (buscar(asignatura) != null) {
            throw new IllegalArgumentException("ERROR: La asignatura ya existe.");
        }
        if (tamano >= capacidad) {
            throw new IllegalArgumentException("ERROR: No se pueden insertar más asignaturas, capacidad alcanzada.");
        }
        coleccionAsignaturas[tamano++] = asignatura;
    }

    private int buscarIndice(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar el índice de una asignatura nula.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAsignaturas[i].equals(asignatura)) {
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

    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            return null;
        } else {
            return coleccionAsignaturas[indice];
        }
    }


    public void borrar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: La asignatura a borrar no existe.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }


    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
        }
        coleccionAsignaturas[tamano - 1] = null;
    }
}

