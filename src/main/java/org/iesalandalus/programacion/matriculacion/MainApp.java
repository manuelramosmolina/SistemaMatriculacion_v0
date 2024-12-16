package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;
import org.iesalandalus.programacion.matriculacion.vista.Consola;

import java.time.LocalDate;


public class MainApp {
    /*
    //public static final int CAPACIDAD=3;
    private static Alumno[] alumnos = new Alumno[3];
    private static Asignatura[] asignaturas = new Asignatura[3];
    private static CicloFormativo[] ciclosFormativos = new CicloFormativo[3];
    private static Matricula[] matriculas = new Matricula[3];

    private static int numAlumnos = 0;
    private static int numAsignaturas = 0;
    private static int numCiclos = 0;
    private static int numMatriculas = 0;


    private static void ejecutarOpcion(Opcion opcion) throws Exception {
        switch (opcion) {
            case INSERTAR_ALUMNO -> insertarAlumno();
            case BUSCAR_ALUMNO -> buscarAlumno();
            case BORRAR_ALUMNO -> borrarAlumno();
            case MOSTRAR_ALUMNOS -> mostrarAlumnos();
            case INSERTAR_ASIGNATURA -> insertarAsignatura();
            case BUSCAR_ASIGNATURA -> buscarAsignatura();
            case BORRAR_ASIGNATURA -> borrarAsignatura();
            case MOSTRAR_ASIGNATURAS -> mostrarAsignaturas();
            case INSERTAR_CICLO_FORMATIVO -> insertarCicloFormativo();
            case BUSCAR_CICLO_FORMATIVO -> buscarCicloFormativo();
            case BORRAR_CICLO_FORMATIVO -> borrarCicloFormativo();
            case MOSTRAR_CICLOS_FORMATIVOS -> mostrarCiclosFormativos();
            case INSERTAR_MATRICULA -> insertarMatricula();
            case BUSCAR_MATRICULA -> buscarMatricula();
            case ANULAR_MATRICULA -> anularMatricula();
            case MOSTRAR_MATRICULAS -> mostrarMatriculas();
            case MOSTRAR_MATRICULAS_ALUMNO -> mostrarMatriculasPorAlumno();
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO -> mostrarMatriculasPorCicloFormativo();
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO -> mostrarMatriculasPorCursoAcademico();
            case SALIR -> System.out.println("Saliendo de la aplicación. ¡Hasta pronto!");
        }
    }


    private static void insertarAlumno() throws Exception {
        Alumno alumno = Consola.leerAlumno();
        for (int i = 0; i < numAlumnos; i++) {
            if (alumnos[i].getDni().equals(alumno.getDni())) {
                throw new IllegalArgumentException("ERROR: Ya existe un alumno con ese DNI.");
            }
        }
        alumnos[numAlumnos++] = alumno;
        System.out.println("Alumno insertado correctamente.");
    }

    private static void buscarAlumno() throws Exception {
        Alumno alumno = Consola.getAlumnoPorDni();
        for (int i = 0; i < numAlumnos; i++) {
            if (alumnos[i].getDni().equals(alumno.getDni())) {
                System.out.println(alumnos[i]);
                return;
            }
        }
        throw new IllegalArgumentException("ERROR: Alumno no encontrado.");
    }

    private static void borrarAlumno() throws Exception {
        Alumno alumno = Consola.getAlumnoPorDni();
        for (int i = 0; i < numAlumnos; i++) {
            if (alumnos[i].getDni().equals(alumno.getDni())) {
                alumnos[i] = alumnos[--numAlumnos];
                alumnos[numAlumnos] = null;
                System.out.println("Alumno borrado correctamente.");
                return;
            }
        }
        throw new IllegalArgumentException("ERROR: Alumno no encontrado.");
    }

    private static void mostrarAlumnos() {
        if (numAlumnos == 0) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (int i = 0; i < numAlumnos; i++) {
                System.out.println(alumnos[i]);
            }
        }
    }

    private static void insertarAsignatura() throws Exception {
        Asignatura asignatura = Consola.leerAsignatura();
        for (int i = 0; i < numAsignaturas; i++) {
            if (asignaturas[i].getCodigo().equals(asignatura.getCodigo())) {
                throw new IllegalArgumentException("ERROR: Ya existe una asignatura con ese código.");
            }
        }
        asignaturas[numAsignaturas++] = asignatura;
        System.out.println("Asignatura insertada correctamente.");
    }

    private static void buscarAsignatura() throws Exception {
        Asignatura asignatura = Consola.getAsignaturaPorCodigo();
        for (int i = 0; i < numAsignaturas; i++) {
            if (asignaturas[i].getCodigo().equals(asignatura.getCodigo())) {
                System.out.println(asignaturas[i]);
                return;
            }
        }
        throw new IllegalArgumentException("ERROR: Asignatura no encontrada.");
    }

    private static void borrarAsignatura() throws Exception {
        Asignatura asignatura = Consola.getAsignaturaPorCodigo();
        for (int i = 0; i < numAsignaturas; i++) {
            if (asignaturas[i].getCodigo().equals(asignatura.getCodigo())) {
                asignaturas[i] = asignaturas[--numAsignaturas];
                asignaturas[numAsignaturas] = null;
                System.out.println("Asignatura borrada correctamente.");
                return;
            }
        }
        throw new IllegalArgumentException("ERROR: Asignatura no encontrada.");
    }

    private static void mostrarAsignaturas() {
        if (numAsignaturas == 0) {
            System.out.println("No hay asignaturas registradas.");
        } else {
            for (int i = 0; i < numAsignaturas; i++) {
                System.out.println(asignaturas[i]);
            }
        }
    }

    private static void insertarCicloFormativo() throws Exception {
        CicloFormativo cicloFormativo = Consola.leerCicloFormativo();
        for (int i = 0; i < numCiclos; i++) {
            if (ciclosFormativos[i].getCodigo()==(cicloFormativo.getCodigo())) {
                throw new IllegalArgumentException("ERROR: Ya existe un ciclo formativo con ese código.");
            }
        }
        ciclosFormativos[numCiclos++] = cicloFormativo;
        System.out.println("Ciclo formativo insertado correctamente.");
    }


    private static void buscarCicloFormativo() throws Exception {
        String codigo = Consola.leerCodigoCicloFormativo();
        for (int i = 0; i < numCiclos; i++) {
            if (ciclosFormativos[i].getCodigo().equals(codigo)) {
                System.out.println(ciclosFormativos[i]);
                return;
            }
        }
        throw new IllegalArgumentException("ERROR: Ciclo formativo no encontrado.");
    }


    private static void borrarCicloFormativo() throws Exception {
        String codigo = Consola.leerCodigoCicloFormativo();
        for (int i = 0; i < numCiclos; i++) {
            if (ciclosFormativos[i].getCodigo().equals(codigo)) {
                ciclosFormativos[i] = ciclosFormativos[--numCiclos];
                ciclosFormativos[numCiclos] = null;
                System.out.println("Ciclo formativo borrado correctamente.");
                return;
            }
        }
        throw new IllegalArgumentException("ERROR: Ciclo formativo no encontrado.");
    }


    private static void mostrarCiclosFormativos() {
        if (numCiclos == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            for (int i = 0; i < numCiclos; i++) {
                System.out.println(ciclosFormativos[i]);
            }
        }
    }


   private static void insertarMatricula() throws Exception {
        Matricula matricula = Consola.leerMatricula();
        for (int i = 0; i < numMatriculas; i++) {
            if (matriculas[i].getIdMatricula().equals(matricula.getIdMatricula())) {
                throw new IllegalArgumentException("ERROR: Ya existe una matrícula con ese identificador.");
            }
        }
        matriculas[numMatriculas++] = matricula;
        System.out.println("Matrícula insertada correctamente.");
    }


    private static void buscarMatricula() throws Exception {
        String id = Consola.leerCodigoCicloFormativo();
        for (int i = 0; i < numMatriculas; i++) {
            //if (matriculas[i].getIdMatricula().equals(id)) {
                System.out.println(matriculas[i]);
                return;
            }
        }
        throw new IllegalArgumentException("ERROR: Matrícula no encontrada.");
    }


    private static void anularMatricula() throws Exception {
        System.out.println("Seleccione una matrícula para anular:");
        mostrarMatriculas();
        String id = Consola.leerCodigoCicloFormativo();
        for (int i = 0; i < numMatriculas; i++) {
            //if (matriculas[i].getIdMatricula().equals(id)) {
                if (matriculas[i].getFechaAnulacion() != null) {
                    System.out.println("Esta matrícula ya está anulada.");
                    return;
                }
                LocalDate fechaAnulacion = Consola.leerCodigoCicloFormativo();
                matriculas[i].setFechaAnulacion(fechaAnulacion);
                System.out.println("Matrícula anulada correctamente.");
                return;
            }
        }
        throw new IllegalArgumentException("ERROR: Matrícula no encontrada o no puede ser anulada.");
    }


    private static void mostrarMatriculas() {
        if (numMatriculas == 0) {
            System.out.println("No hay matrículas registradas.");
        } else {
            for (int i = 0; i < numMatriculas; i++) {
                System.out.println(matriculas[i]);
            }
        }
    }


    private static void mostrarMatriculasPorAlumno() throws Exception {
        String dni = Consola.leerCodigoCicloFormativo();
        boolean encontrado = false;
        for (int i = 0; i < numMatriculas; i++) {
            if (matriculas[i].getAlumno().getDni().equals(dni)) {
                System.out.println(matriculas[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay matrículas para el alumno con DNI " + dni + ".");
        }
    }

    private static void mostrarMatriculasPorCicloFormativo() throws Exception {
        String codigoCiclo = Consola.leerCodigoCicloFormativo();
        boolean encontrado = false;
        for (int i = 0; i < numMatriculas; i++) {
            if (matriculas[i].getCursoAcademico().equals(codigoCiclo)) {
                System.out.println(matriculas[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay matrículas para el ciclo formativo con código " + codigoCiclo + ".");
        }
    }

    private static void mostrarMatriculasPorCursoAcademico() throws Exception {
        String cursoAcademico = Consola.leerCodigoCicloFormativo();
        boolean encontrado = false;
        for (int i = 0; i < numMatriculas; i++) {
            if (matriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                System.out.println(matriculas[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay matrículas para el curso académico " + cursoAcademico + ".");
        }
    }

    */

    public static void main(String[] args) {
        Opcion[] opciones = Opcion.values();
        Opcion opcion;

       /* do {
            try {
                Consola.mostrarMenu(opciones);
                opcion = Consola.elegirOpcion(opciones);
                MainApp.ejecutarOpcion(opcion);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        } while (true);
    }*/


        System.out.println("Hasta luego!!!!");
    }

}







