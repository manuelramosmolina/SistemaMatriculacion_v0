package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Consola {

    private Consola() {
    }

    public static void mostrarMenu() {
        try {
            System.out.println("Opciones disponibles:");
            for (Opcion opcion : Opcion.values()) {
                System.out.println(opcion);
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: El menú de opciones no se puede mostrar porque es nulo.");
        }
    }

    public static Opcion elegirOpcion() {
        try {
            int eleccion;
            do {
                System.out.print("Elige una opción válida: ");
                eleccion = Entrada.entero();
            } while (eleccion < 0 || eleccion >= Opcion.values().length);
            return Opcion.values()[eleccion];
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Opción no válida. " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("ERROR: Las opciones disponibles no están inicializadas.");
        }
        return null;
    }

    public static Alumno leerAlumno() {
        try {
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

            System.out.print("Introduce la fecha de nacimiento del alumno (AAAA-MM-DD): ");
            fechaNacimiento = LocalDate.parse(Entrada.cadena());

            return new Alumno(nombre);
        } catch (NullPointerException e) {
            System.out.println("ERROR: Se ha encontrado un valor nulo. Asegúrate de completar todos los campos correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Algún dato introducido no es válido. Detalle: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("ERROR: La fecha introducida no tiene el formato correcto. Intenta nuevamente.");
        }
        return null;
    }

    public static Alumno getAlumnoPorDni() {
        try {
            String dni;
            do {
                System.out.print("Introduce el DNI del alumno: ");
                dni = Entrada.cadena();
            } while (dni.isEmpty());

            return new Alumno(dni);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: El DNI proporcionado no es válido. " + e.getMessage());
        }
        return null;
    }

    public static LocalDate leerFecha(String mensaje) {
        try {
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
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede leer la fecha, el mensaje es nulo.");
        }
        return null;
    }

    public static Curso leerCurso() {
        System.out.println("Introduce el curso: ");
        for (Curso curso : Curso.values()) {
            System.out.println(curso.imprimir());
        }
        if (Curso.values()[Entrada.entero()] == null) {
            do {
                System.out.println("ERROR: Opción no valida.");
            } while (Curso.values()[Entrada.entero()] == null);
        }
        return Curso.values()[Entrada.entero()];
    }

    public static CicloFormativo leerCicloFormativo() {
        try {
            String nombre, codigo;

            do {
                System.out.print("Introduce el nombre del ciclo formativo: ");
                nombre = Entrada.cadena();
            } while (nombre.isEmpty());

            do {
                System.out.print("Introduce el código del ciclo formativo: ");
                codigo = Entrada.cadena();
            } while (codigo.isEmpty());

            return new CicloFormativo(nombre, codigo);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Algún dato introducido no es válido. " + e.getMessage());
        }
        return null;
    }

    public static CicloFormativo getCicloFormativoPorCodigo() {
        System.out.print("Introduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();
        return new CicloFormativo(codigo);
    }

    public static Matricula leerMatricula(Alumno alumno, Asignatura asignaturasElegidas) {
        try {
            if (alumno == null) {
                throw new IllegalArgumentException("El alumno no puede ser nulo.");
            }





            int idMatricula = 1;
            String cursoAcademico = "2024-2025";
            LocalDate fechaMatriculacion = LocalDate.now();

            return new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignaturasElegidas);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Algún dato introducido no es válido. " + e.getMessage());
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: Operación no soportada. " + e.getMessage());
        }
        return null;
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.println("Introduce la especialidad: ");
        for (EspecialidadProfesorado especialidadProfesorado : EspecialidadProfesorado.values()) {
            System.out.println(especialidadProfesorado.imprimir(especialidadProfesorado));
        }
        if (EspecialidadProfesorado.values()[Entrada.entero()] == null) {
            do {
                System.out.println("ERROR: Opción no valida.");
            } while (EspecialidadProfesorado.values()[Entrada.entero()] == null);
        }
        return EspecialidadProfesorado.values()[Entrada.entero()];
    }

    public static Asignatura leerAsignatura() {
        System.out.println("Introduce los datos de la asignatura: ");
        System.out.print("Código: ");
        String codigo = Entrada.cadena();
        System.out.print("Nombre: ");
        String nombre = Entrada.cadena();
        System.out.print("Horas anuales: ");
        int horasAnuales = Entrada.entero();
        System.out.print("Horas dobles: ");
        int horasDesdoble = Entrada.entero();
        System.out.print("Curso: ");
        Curso curso = leerCurso();
        System.out.println("Especialidad: ");
        EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();
        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, getCicloFormativoPorCodigo());
    }


    public static Asignatura leerAsignaturaPorCodigo() {
        System.out.println("Introduce el codigo de la asignatura: ");
        String codigo = Entrada.cadena();
        return new Asignatura(codigo, "", 0, Curso.values()[0], 0, EspecialidadProfesorado.values()[0], getCicloFormativoPorCodigo());
    }

    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {

        return getMatriculaPorIdentificador();
    }

}



