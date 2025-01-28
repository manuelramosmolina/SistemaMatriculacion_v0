package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;


import javax.naming.OperationNotSupportedException;

public class Modelo {

    public static final int CAPACIDAD = 3;
    private Alumnos alumnos;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;
    private Matriculas matriculas;



    public void comenzar() {
        this.alumnos = new Alumnos(CAPACIDAD);
        this.asignaturas = new Asignaturas(CAPACIDAD);
        this.ciclosFormativos = new CiclosFormativos(CAPACIDAD);
        this.matriculas = new Matriculas(CAPACIDAD);
    }

    public void terminar() {
        System.out.println("Modelo terminado.");
    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        this.alumnos.insertar(alumno);
    }


    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        this.asignaturas.insertar(asignatura);
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        this.ciclosFormativos.insertar(cicloFormativo);
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        this.matriculas.insertar(matricula);
    }

    public Alumno buscar(Alumno alumno) {
        return this.alumnos.buscar(alumno);
    }

    public Asignatura buscar(Asignatura asignatura) {
        return this.asignaturas.buscar(asignatura);
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        return this.ciclosFormativos.buscar(cicloFormativo);
    }

    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        return this.matriculas.buscar(matricula);
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        this.alumnos.borrar(alumno);
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        this.asignaturas.borrar(asignatura);
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        this.ciclosFormativos.borrar(cicloFormativo);
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        this.matriculas.borrar(matricula);
    }

    public Matricula[] getMatriculas() throws OperationNotSupportedException {
        return this.matriculas.get();
    }

    public Matricula[] getMatriculas(Alumno alumno) throws OperationNotSupportedException {
        return this.matriculas.get(alumno);
    }

    public Matricula[] getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        return this.matriculas.get(cicloFormativo);
    }

    public Matricula[] getMatriculas(String cursoAcademico) throws OperationNotSupportedException {
        return this.matriculas.get(cursoAcademico);
    }

    public Alumno[] getAlumnos() {
        return alumnos.get();
    }
}
