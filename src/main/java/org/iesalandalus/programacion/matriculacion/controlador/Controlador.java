package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import javax.naming.OperationNotSupportedException;

public class Controlador {

    private Modelo modelo;
    private Vista vista;
    private Asignatura[] asignaturas;
    private CicloFormativo[] ciclosFormativos;
    private Matricula[] matriculas;

    public Controlador(Modelo modelo, Vista vista) {
        if (modelo == null) {
            throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
        }
        if (vista == null) {
            throw new NullPointerException("ERROR: La vista no puede ser nula.");
        }
        this.modelo = modelo;
        this.vista = vista;
        vista.setControlador(this);
    }

    public void comenzar() throws OperationNotSupportedException {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    public void insertarAlumno(Alumno alumno) throws OperationNotSupportedException {
        modelo.insertar(alumno);
    }

    public void insertarAsignatura(Asignatura asignatura) throws OperationNotSupportedException {
        modelo.insertar(asignatura);
    }

    public void insertarCicloFormativo(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        modelo.insertar(cicloFormativo);
    }

    public void insertarMatricula(Matricula matricula) throws OperationNotSupportedException {
        modelo.insertar(matricula);
    }

    public Alumno buscarAlumno(Alumno alumno)  {

        return modelo.buscar(alumno);
    }

    public Asignatura buscarAsignatura(Asignatura asignatura) {

        return modelo.buscar(asignatura);
    }

    public CicloFormativo buscarCicloFormativo(CicloFormativo cicloFormativo) {

        return modelo.buscar(cicloFormativo);
    }

    public Matricula buscarMatricula(Matricula matricula) throws OperationNotSupportedException {
        return modelo.buscar(matricula);
    }

    public void borrarAlumno(Alumno alumno) throws OperationNotSupportedException {
        modelo.borrar(alumno);
    }

    public void borrarAsignatura(Asignatura asignatura) throws OperationNotSupportedException {
        modelo.borrar(asignatura);
    }

    public void borrarCicloFormativo(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        modelo.borrar(cicloFormativo);
    }

    public void borrarMatricula(Matricula matricula) throws OperationNotSupportedException {
        modelo.borrar(matricula);
    }

    public Matriculas[] getMatricula(){
        if (matriculas==null)
            throw new NullPointerException("ERROR: el alumno no puede ser nulo.");
        return new Matriculas[0];
    }
    public Alumno[] getAlumnos() {
        return this.modelo.getAlumnos();
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        this.modelo.insertar(asignatura);
    }


    public Asignatura[] getAsignaturas() {
        return asignaturas;
    }

    public CicloFormativo[] getCiclosFormativos() {
        return ciclosFormativos;
    }

    public Matricula[] getMatriculas() {
        return matriculas;
    }
}
