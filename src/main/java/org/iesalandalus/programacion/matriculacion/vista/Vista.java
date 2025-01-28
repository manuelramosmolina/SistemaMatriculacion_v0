package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;

import javax.naming.OperationNotSupportedException;

public class Vista {

    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
        }
        this.controlador = controlador;
    }

    public void comenzar() throws OperationNotSupportedException {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        controlador.terminar();
        System.out.println("Gracias por usar la aplicación. ¡Hasta pronto!");
    }

    private void ejecutarOpcion(Opcion opcion) throws OperationNotSupportedException {
        switch (opcion) {
            case INSERTAR_ALUMNO -> insertarAlumno();
            case BORRAR_ALUMNO -> borrarAlumno();
            case BUSCAR_ALUMNO -> buscarAlumno();
            case MOSTRAR_ALUMNOS -> mostrarAlumnos();
            case INSERTAR_ASIGNATURA -> insertarAsignatura();
            case BORRAR_ASIGNATURA -> borrarAsignatura();
            case BUSCAR_ASIGNATURA -> buscarAsignatura();
            case MOSTRAR_ASIGNATURAS -> mostrarAsignaturas();
            case INSERTAR_CICLO_FORMATIVO -> insertarCicloFormativo();
            case BORRAR_CICLO_FORMATIVO -> borrarCicloFormativo();
            case MOSTRAR_CICLOS_FORMATIVOS -> mostrarCiclosFormativos();
            case INSERTAR_MATRICULA -> insertarMatricula();
            case ANULAR_MATRICULA -> anularMatricula();
            case BUSCAR_MATRICULA -> buscarMatricula();
            case MOSTRAR_MATRICULAS -> mostrarMatriculas();
            case MOSTRAR_MATRICULAS_ALUMNO -> mostrarMatriculasPorAlumno();
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO -> mostrarMatriculasPorCicloFormativo();
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO -> mostrarMatriculasPorCursoAcademico();
            default -> throw new IllegalArgumentException("Opción no válida.");
        }
    }

    private void insertarAlumno() {
        Alumno alumno = Consola.leerAlumno();
        try {
            controlador.insertarAlumno(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarAlumno() {
        Alumno alumno = Consola.leerAlumno();
        try {
            controlador.borrarAlumno(alumno);
            System.out.println("Alumno borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarAlumno() {
        Alumno alumno = Consola.leerAlumno();
        Alumno encontrado = controlador.buscarAlumno(alumno);
        String mensaje = (encontrado != null) ? encontrado.toString() : "No se ha encontrado al alumno.";
        System.out.println(mensaje);
    }

    private void mostrarAlumnos() {
        Alumno[] alumnos = controlador.getAlumnos();
        if (alumnos.length == 0) {
            System.out.println("No hay alumnos para mostrar.");
        } else {
            for (Alumno alumno : alumnos) {
                System.out.println(alumno);
            }
        }
    }

    private void insertarAsignatura() {
        Asignatura asignatura = Consola.leerAsignatura();
        try {
            controlador.insertarAsignatura(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarAsignatura() {
        Asignatura asignatura = Consola.leerAsignaturaPorCodigo();
        try {
            controlador.borrarAsignatura(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarAsignatura() {
        Asignatura asignatura = Consola.leerAsignatura();
        Asignatura encontrada = controlador.buscarAsignatura(asignatura);
        String mensaje = (encontrada != null) ? encontrada.toString() : "No se ha encontrado la asignatura.";
        System.out.println(mensaje);
    }

    private void mostrarAsignaturas() {
        Asignatura[] asignaturas = controlador.getAsignaturas();
        if (asignaturas.length == 0) {
            System.out.println("No hay asignaturas para mostrar.");
        } else {
            for (Asignatura asignatura : asignaturas) {
                System.out.println(asignatura);
            }
        }
    }

    private void insertarCicloFormativo() {
        CicloFormativo ciclo = Consola.leerCicloFormativo();
        try {
            controlador.insertarCicloFormativo(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarCicloFormativo() {
        CicloFormativo ciclo = Consola.leerCicloFormativo();
        try {
            controlador.borrarCicloFormativo(ciclo);
            System.out.println("Ciclo formativo borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarCiclosFormativos() {
        CicloFormativo[] ciclos = controlador.getCiclosFormativos();
        if (ciclos.length == 0) {
            System.out.println("No hay ciclos formativos para mostrar.");
        } else {
            for (CicloFormativo ciclo : ciclos) {
                System.out.println(ciclo);
            }
        }
    }

    private void insertarMatricula() {
        try {
            Alumno alumno = Consola.leerAlumno();
            Asignatura[] asignaturasDisponibles = controlador.getAsignaturas();
            Asignatura asignaturasElegidas = Consola.leerAsignatura();
            Matricula matricula = Consola.leerMatricula(alumno, asignaturasElegidas);
            controlador.insertarMatricula(matricula);
            System.out.println("Matrícula insertada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void anularMatricula() throws OperationNotSupportedException {
        Matricula matricula = Consola.getMatriculaPorIdentificador();
        try {
            controlador.borrarMatricula(matricula);
            System.out.println("Matrícula anulada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarMatricula() throws OperationNotSupportedException {
        Matricula matricula = Consola.getMatriculaPorIdentificador();
        Matricula encontrada = controlador.buscarMatricula(matricula);
        String mensaje = (encontrada != null) ? encontrada.toString() : "No se ha encontrado la matrícula.";
        System.out.println(mensaje);
    }

    private void mostrarMatriculas() throws OperationNotSupportedException {
        Matricula[] matriculas = controlador.getMatriculas();
        if (matriculas.length == 0) {
            System.out.println("No hay matrículas para mostrar.");
        } else {
            for (Matricula matricula : matriculas) {
                System.out.println(matricula);
            }
        }
    }

    private void mostrarMatriculasPorAlumno() {
        Alumno alumno = Consola.leerAlumno();
        Matriculas[] matriculas = controlador.getMatricula();
        if (matriculas.length == 0) {
            System.out.println("No hay matrículas para este alumno.");
            throw new IllegalArgumentException("ERROR");
        } else {
            for (Matriculas matricula : matriculas) {
                System.out.println(matricula);
            }
        }
    }

    private void mostrarMatriculasPorCicloFormativo() {
        CicloFormativo ciclo = Consola.leerCicloFormativo();
        Matricula[] matriculas = controlador.getMatriculas();
        if (matriculas.length == 0) {
            System.out.println("No hay matrículas para este ciclo formativo.");
        } else {
            for (Matricula matricula : matriculas) {
                System.out.println(matricula);
            }
        }
    }

    private void mostrarMatriculasPorCursoAcademico() {
        Curso cursoAcademico = Consola.leerCurso();
        Matricula[] matriculas = controlador.getMatriculas();
        if (matriculas.length == 0) {
            System.out.println("No hay matrículas para este curso académico.");
        } else {
            for (Matricula matricula : matriculas) {
                System.out.println(matricula);
            }
        }
    }
}
