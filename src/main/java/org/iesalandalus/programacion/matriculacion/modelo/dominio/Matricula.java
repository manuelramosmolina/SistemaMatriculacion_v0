package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

public class Matricula {
    public static final int MAXIMO_MESES_ANTERIOR_ANULACION = 6;
    public static final int MAXIMO_DIAS_ANTERIOR_MATRICULA = 15;
    public static final int MAXIMO_NUMERO_HORAS_MATRICULA = 1000;
    public static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = 10;
    public static final String FORMATO_FECHA = "dd-MM-yyyy";
    private static final String ER_CURSO_ACADEMICO = "\\d{2}-\\d{2}";

    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private Alumno alumno;
    private Asignatura[] coleccionAsignaturas;


    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura asignaturas) throws OperationNotSupportedException {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(new Asignatura[]{asignaturas});
    }


    public Matricula(Matricula matricula) throws OperationNotSupportedException{
        if (matricula == null)
            throw new NullPointerException("ERROR: No se puede copiar una matrícula nula.");

        this.idMatricula = matricula.idMatricula;
        this.cursoAcademico = matricula.cursoAcademico;
        this.fechaMatriculacion = matricula.fechaMatriculacion;
        this.fechaAnulacion = (matricula.fechaAnulacion != null) ? matricula.fechaAnulacion : null;
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

        if (fechaMatriculacion.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser posterior a hoy.");

        this.fechaMatriculacion = fechaMatriculacion;
    }



    public LocalDate getFechaAnulacion() {

        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        if (fechaAnulacion == null)
            throw new NullPointerException("ERROR: La fecha de anulación no puede ser nula.");

        if (fechaAnulacion.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser posterior a hoy.");

        if (fechaAnulacion.isBefore(fechaMatriculacion))
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");

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

    public void setColeccionAsignaturas(Asignatura[] asignaturas) throws OperationNotSupportedException {
        if (asignaturas == null)
            throw new NullPointerException("ERROR: El número de asignaturas no puede ser nulo.");

        if (asignaturas.length > MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA)
            throw new IllegalArgumentException("ERROR: El número de asignaturas no puede ser superior a " + MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA);

        int totalHoras = 0;
        for (Asignatura asignatura : asignaturas) {
            if (asignatura == null)
                throw new NullPointerException("ERROR: No se permiten asignaturas nulas en la matrícula.");
            totalHoras += asignatura.getHorasAnuales();
        }
//TODO VALIDAR MAXIMO NUMERO HORAS CORRECTAMENTE Y LANZAR OPERATION NOT SUPPORTED
        if (totalHoras > MAXIMO_NUMERO_HORAS_MATRICULA)
            throw new IllegalArgumentException("ERROR: El total de horas anuales supera el máximo permitido de " + MAXIMO_NUMERO_HORAS_MATRICULA + ".");


        this.coleccionAsignaturas = new Asignatura[asignaturas.length];
        for (int i = 0; i < asignaturas.length; i++) {
            this.coleccionAsignaturas[i] = new Asignatura(asignaturas[i]);
        }
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
        for (Asignatura asignatura : coleccionAsignaturas) {
            if (asignatura != null) {
                totalHoras += asignatura.getHorasAnuales();
            }
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
        String fechaMatriculacionFormateada = String.format("%02d-%02d-%d",
                fechaMatriculacion.getDayOfMonth(),
                fechaMatriculacion.getMonthValue(),
                fechaMatriculacion.getYear());

        return String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s, alumno={%s}",
                idMatricula,
                cursoAcademico,
                fechaMatriculacionFormateada,
                alumno.imprimir());
    }


    @Override
    public String toString() {
        StringBuilder asignaturasString = new StringBuilder();
        for (Asignatura asignatura : coleccionAsignaturas) {
            if (asignatura != null) {
                asignaturasString.append(asignatura.imprimir()).append(", ");
            }
        }

        if (asignaturasString.length() > 0) {
            asignaturasString.setLength(asignaturasString.length() - 2);
        }

        String fechaMatriculacionFormateada;
        if (fechaMatriculacion != null) {
            fechaMatriculacionFormateada = String.format("%02d-%02d-%d",
                    fechaMatriculacion.getDayOfMonth(),
                    fechaMatriculacion.getMonthValue(),
                    fechaMatriculacion.getYear());
        } else {
            fechaMatriculacionFormateada = "N/A";
        }

        String fechaAnulacionFormateada;
        if (fechaAnulacion != null) {
            fechaAnulacionFormateada = String.format("%02d-%02d-%d",
                    fechaAnulacion.getDayOfMonth(),
                    fechaAnulacion.getMonthValue(),
                    fechaAnulacion.getYear());
        } else {
            fechaAnulacionFormateada = null;
        }

        String alumnoFormateado;
        if (alumno != null) {
            alumnoFormateado = alumno.imprimir();
        } else {
            alumnoFormateado = "N/A";
        }

        String cadena;
        if (fechaAnulacionFormateada != null) {
            cadena = String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s, fecha anulación=%s, alumno=%s, Asignaturas={ %s }",
                    idMatricula,
                    cursoAcademico,
                    fechaMatriculacionFormateada,
                    fechaAnulacionFormateada,
                    alumnoFormateado,
                    asignaturasString.toString());
        } else {
            cadena = String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s, alumno=%s, Asignaturas={ %s }",
                    idMatricula,
                    cursoAcademico,
                    fechaMatriculacionFormateada,
                    alumnoFormateado,
                    asignaturasString.toString());
        }

        return cadena;
    }


}

