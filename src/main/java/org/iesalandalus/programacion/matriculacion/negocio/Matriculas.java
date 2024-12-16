package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

public class Matriculas {
    private int capacidad;
    private int tamano;
    private Matricula[] coleccionMatriculas;

    public Matriculas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionMatriculas = new Matricula[capacidad];
    }

    public Matricula[] get() {

        return copiaProfundaMatriculas();
    }

    private Matricula[] copiaProfundaMatriculas() {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copia;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }
        if (buscar(matricula) != null) {
            throw new IllegalArgumentException("ERROR: La matrícula ya existe.");
        }
        coleccionMatriculas[tamano++] = matricula;
    }

    private int buscarIndice(Matricula matricula) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
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

    public Matricula buscar(Matricula matricula) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) return coleccionMatriculas[i];
        }
        return null;
    }

    public void borrar(Matricula matricula) {
        int indice = -1;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
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
            coleccionMatriculas[i] = coleccionMatriculas[i + 1];
        }
        coleccionMatriculas[tamano - 1] = null;
    }

    /* TODO
    public get1
    public get2
    public get3
     TODO */

    public Matricula[] get(Alumno alumno) {
        if (alumno == null)
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");

        Matricula[] resultado = new Matricula[tamano];
        int indiceResultado = 0;

        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getAlumno().equals(alumno)) {
                resultado[indiceResultado++] = coleccionMatriculas[i];
            }
        }
        return resultado;
    }

    public Matricula[] get(String cursoAcademico) {
        if (cursoAcademico == null)
            throw new NullPointerException("ERROR: El curso académico no puede ser nulo.");
        if (cursoAcademico.isEmpty())
            throw new IllegalArgumentException("ERROR: El curso académico no puede ser vacío.");
        if (cursoAcademico.isBlank())
            throw new IllegalArgumentException("ERROR: El curso académico no es válido.");

        Matricula[] resultado = new Matricula[tamano];
        int indiceResultado = 0;

        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                resultado[indiceResultado++] = coleccionMatriculas[i];
            }
        }
        return resultado;

    }
    public Matricula[] get(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null)
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");

        Matricula[] resultado = new Matricula[tamano];
        int indiceResultado = 0;

        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(cicloFormativo)) {
                resultado[indiceResultado++] = coleccionMatriculas[i];
            }
        }
        return resultado;
    }



}

