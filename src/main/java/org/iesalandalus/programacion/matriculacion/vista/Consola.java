package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Consola {

    private Consola() {

    }

    public static void mostrarMenu(Opcion[] opciones) {
        System.out.println("Opciones disponibles:");
        for (int i = 0; i < Opcion.values().length; i++) {
            System.out.println(Opcion.values()[i]);
        }
    }

    public static Opcion elegirOpcion(Opcion[] opciones) {
        int eleccion;
        do {
            System.out.print("Elige una opción válida: ");
            eleccion = Entrada.entero();
        } while (eleccion < 0 || eleccion >= Opcion.values().length);
        return Opcion.values()[eleccion];
    }

    public static Alumno leerAlumno(){
        String nombre, telefono, correo, dni;
        LocalDate fechaNacimiento;

        System.out.print("Introduce el nombre del alumno: ");
        nombre = Entrada.cadena();

        System.out.print("Introduce el teléfono del alumno: ");
        telefono = Entrada.cadena();

        System.out.print("Introduce el correo del alumno: ");
        correo = Entrada.cadena();

        System.out.print("Introduce el DNI del alumno: ");
        dni = Entrada.cadena();

        System.out.print("Introduce la fecha de nacimiento del alumno: ");
        fechaNacimiento = LocalDate.parse(Entrada.cadena());


        return new Alumno(nombre, telefono, correo, dni, fechaNacimiento);
    }

    public static Alumno getAlumnoPorDni()throws IllegalArgumentException {
        String dni;
        do {
            System.out.print("Introduce el DNI del alumno: ");
            dni = Entrada.cadena();
        } while (dni.isEmpty());

        return new Alumno(getAlumnoPorDni());
    }

    public static String leerCodigoCicloFormativo() {
        return null;
    }

    public LocalDate leerFecha(String mensaje) throws IllegalArgumentException{
        LocalDate fecha = null;
        boolean fechaCorrecta = false;

        do {
            try {
                System.out.print(mensaje + " (Formato: AAAA-MM-DD): ");
                String fechaString = Entrada.cadena();
                fecha = LocalDate.parse(fechaString);
                fechaCorrecta = true;
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: Formato de fecha incorrecto. Inténtalo de nuevo.");
            }
        } while (!fechaCorrecta);

        return fecha;
    }

    public String leerGrado() {
        String grado;
        do {
            System.out.print("Introduce el grado (Básico, Medio, Superior): ");
            grado = Entrada.cadena();
        } while (!(grado.equalsIgnoreCase("Básico") || grado.equalsIgnoreCase("Medio") || grado.equalsIgnoreCase("Superior")));
        return grado;
    }

    public static CicloFormativo leerCicloFormativo() {
        String nombre, codigo;
        do {
            System.out.print("Introduce el nombre del ciclo formativo: ");
            nombre = Entrada.cadena();
        } while (nombre.isEmpty());

        do {
            System.out.print("Introduce el código del ciclo formativo: ");
            codigo = Entrada.cadena();
        } while (codigo.isEmpty());

        return new CicloFormativo(getCicloFormativoPorCodigo());
    }

    public void mostrarCiclosFormativos(CicloFormativo[] ciclos) {
        System.out.println("Ciclos formativos disponibles:");
        for (int i = 0; i < ciclos.length; i++) {
            System.out.println(ciclos[i]);
        }
    }

    public static CicloFormativo getCicloFormativoPorCodigo() {
        String codigo;
        do {
            System.out.print("Introduce el código del ciclo formativo: ");
            codigo = Entrada.cadena();
        } while (codigo.isEmpty());

        return new CicloFormativo(getCicloFormativoPorCodigo());
    }

    public String leerCurso() {
        String curso;
        do {
            System.out.print("Introduce el curso (ej: 1º, 2º, 3º): ");
            curso = Entrada.cadena();
        } while (curso.isEmpty());
        return curso;
    }

    public String leerEspecialidadProfesorado() {
        String especialidad;
        do {
            System.out.print("Introduce la especialidad del profesorado: ");
            especialidad = Entrada.cadena();
        } while (especialidad.isEmpty());
        return especialidad;
    }

    public static Asignatura leerAsignatura() {
        String codigo, nombre;
        int horas;

        do {
            System.out.print("Introduce el código de la asignatura: ");
            codigo = Entrada.cadena();
        } while (codigo.isEmpty());

        do {
            System.out.print("Introduce el nombre de la asignatura: ");
            nombre = Entrada.cadena();
        } while (nombre.isEmpty());

        do {
            System.out.print("Introduce el número de horas: ");
            horas = Entrada.entero();
        } while (horas <= 0);

        return new Asignatura(getAsignaturaPorCodigo());
    }

    public static Asignatura getAsignaturaPorCodigo() {
        String codigo;
        do {
            System.out.print("Introduce el código de la asignatura: ");
            codigo = Entrada.cadena();
        } while (codigo.isEmpty());

        return new Asignatura(getAsignaturaPorCodigo());
    }

    private void mostrarAsignaturas(Asignatura[] asignaturas) {
        System.out.println("Lista de asignaturas:");
        for (int i = 0; i < asignaturas.length; i++) {
            System.out.println(asignaturas[i]);
        }
    }

    private boolean asignaturaYaMatriculada(Asignatura[] listaAsignaturas, Asignatura asignatura) {
        for (int i = 0; i < listaAsignaturas.length; i++) {
            if (listaAsignaturas[i].equals(asignatura)) {
                return true;
            }
        }
        return false;
    }

    public Matricula leerMatricula(Alumno[] alumnos, Asignatura[] asignaturas) {
        int indiceAlumno, indiceAsignatura;

        do {
            System.out.println("Elige un alumno por su índice:");
            for (int i = 0; i < alumnos.length; i++) {
                System.out.println(i + " - " + alumnos[i]);
            }
            indiceAlumno = Entrada.entero();
        } while (indiceAlumno < 0 || indiceAlumno >= alumnos.length);

        do {
            System.out.println("Elige una asignatura por su índice:");
            for (int i = 0; i < asignaturas.length; i++) {
                System.out.println(i + " - " + asignaturas[i]);
            }
            indiceAsignatura = Entrada.entero();
        } while (indiceAsignatura < 0 || indiceAsignatura >= asignaturas.length);

        return new Matricula(getMatriculaPorIdentificador());
    }

    public Matricula getMatriculaPorIdentificador() {
        String identificador;
        System.out.print("Introduce el identificador de la matrícula: ");
        identificador = Entrada.cadena();

        return new Matricula(getMatriculaPorIdentificador());
    }

}


