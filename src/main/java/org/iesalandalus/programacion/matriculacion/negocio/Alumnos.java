package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;


public class Alumnos {
    private int capacidad;
    private int tamano;
    private Alumno[] coleccionAlumnos;

    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAlumnos = new Alumno[capacidad];
    }

    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copia = new Alumno[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copia;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
        if (buscar(alumno) != null) {
            throw new IllegalArgumentException("ERROR: El alumno ya existe en la colección.");
        }
        if (tamanoSuperado(tamano)) {
            throw new IllegalStateException("ERROR: No se pueden insertar más alumnos, la capacidad está completa.");
        }
        coleccionAlumnos[tamano] = alumno;
        tamano++;
    }

    private int buscarIndice(Alumno alumno) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
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

    public Alumno buscar(Alumno alumno) {
        int indice = buscarIndice(alumno);
        return (indice == -1) ? null : coleccionAlumnos[indice];
    }

    public void borrar(Alumno alumno) {
        if (alumno == null)
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");

        int indice = buscarIndice(alumno);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: No existe el alumno que se quiere borrar.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        coleccionAlumnos[tamano - 1] = null; // Eliminar referencia sobrante
    }
}
