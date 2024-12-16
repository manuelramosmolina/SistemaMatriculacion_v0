package org.iesalandalus.programacion.matriculacion.dominio;

import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import java.time.LocalDate;
import java.util.Objects;

public class Matricula {
    public static final int MAXIMO_MESES_ANTERIOR_ANULACION = 6;
    public static final int MAXIMO_DIAS_ANTERIOR_MATRICULA = 15;
    public static final int MAXIMO_NUMERO_HORAS_MATRICULA = 1000;
    public static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = 10;
    public static final String FORMATO_FECHA = "dd-MM-yyyy";
    public static final String ER_CURSO_ACADEMICO = "\\d{2}-\\d{2}";

    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private Alumno alumno;
    private Asignatura[] coleccionAsignaturas;


    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] asignaturas) {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(asignaturas);
    }


    public Matricula(Matricula matricula) {
        if (matricula == null)
            throw new NullPointerException("ERROR: No se puede copiar una matrícula nula.");

        this.idMatricula = matricula.idMatricula;
        this.cursoAcademico = matricula.cursoAcademico;
        this.fechaMatriculacion = matricula.fechaMatriculacion;
        this.fechaAnulacion = matricula.fechaAnulacion;
        this.alumno = new Alumno(matricula.alumno);
        this.coleccionAsignaturas = matricula.coleccionAsignaturas.clone();
    }

    public int getIdMatricula() {
        return idMatricula;
    }
    public void setIdMatricula(int idMatricula) {
        if (idMatricula <= 0) {
            throw new IllegalArgumentException("ERROR: El identificador debe ser positivo.");
        }
        this.idMatricula = idMatricula;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        if (cursoAcademico == null)
            throw new NullPointerException("ERROR: El curso académico no puede ser nulo.");
        if (cursoAcademico.isEmpty())
            throw new IllegalArgumentException("ERROR: La cadena del curso no puede ser vacía.");
        if (cursoAcademico.isBlank())
            throw new IllegalArgumentException("ERROR: La cadena del curso no es válida.");
        if (!cursoAcademico.matches(ER_CURSO_ACADEMICO))
            throw new IllegalArgumentException("ERROR: El formato del curso académico no es válido.");

        this.cursoAcademico = cursoAcademico;
    }

    public LocalDate getFechaMatriculacion(){
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {

        if (fechaMatriculacion == null)
            throw new NullPointerException("ERROR: La fecha de matriculación no puede ser nula.");

        LocalDate limiteFechaAntiguedad = LocalDate.now().minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA);
        if (fechaMatriculacion.isBefore(limiteFechaAntiguedad))
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede tener más de 15 días de antigüedad.");

        this.fechaMatriculacion = fechaMatriculacion;
    }


    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        if (fechaAnulacion == null)
            throw new NullPointerException("ERROR: La fecha de anulación no puede ser nula.");

        LocalDate limiteFechaAnulacion = LocalDate.now().minusMonths(MAXIMO_MESES_ANTERIOR_ANULACION);
        if (fechaMatriculacion.isBefore(limiteFechaAnulacion))
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede superar los 6 meses");

        this.fechaAnulacion = fechaAnulacion;
    }

    public Alumno getAlumno(){
        return alumno;

    }

    public void setAlumno(Alumno alumno) {
        if (alumno == null)
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");

        this.alumno = alumno;
    }

    public Asignatura[] getColeccionAsignaturas() {
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(Asignatura[] asignaturas) {
        if (asignaturas == null)
            throw new IllegalArgumentException("ERROR: El número de asignaturas no puede ser nulo.");

        if (asignaturas.length > MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA)
            throw new IllegalArgumentException("ERROR: El número de asignaturas no puede ser superior a " + MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA);

        this.coleccionAsignaturas = new Asignatura[asignaturas.length];
    }

    private String asignaturasMatricula() {
        String resultado = "Asignaturas de la matrícula:\n";
        for (int i = 0; i < coleccionAsignaturas.length; i++) {
            resultado += coleccionAsignaturas[i] + "\n";
        }
        return resultado;
    }


    private boolean superaMaximoNumeroHorasMatricula() {
        int totalHoras = 0;
        for (int i = 0; i < coleccionAsignaturas.length; i++) {
            totalHoras += coleccionAsignaturas[i].getHorasAnuales();
        }
        return totalHoras > MAXIMO_NUMERO_HORAS_MATRICULA;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matricula other = (Matricula) obj;
        return idMatricula == other.idMatricula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMatricula);
    }

    public String imprimir() {
        return String.format("Matrícula ID: %d, Curso: %s, Fecha: %s", idMatricula, cursoAcademico, fechaMatriculacion);
    }

    @Override
    public String toString() {
        return String.format("Matricula{id=%d, cursoAcademico='%s', fechaMatriculacion=%s, alumno=%s}", idMatricula, cursoAcademico, fechaMatriculacion, alumno);
    }
}

