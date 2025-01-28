package org.iesalandalus.programacion.matriculacion.modelo.dominio;


import java.util.Objects;

public class Asignatura {
    public static final int MAX_NUM_HORAS_ANUALES = 300;
    public static final int MAX_NUM_HORAS_DESDOBLES = 6;
    private static final String ER_CODIGO = "\\d{4}";

    private String codigo;
    private String nombre;
    private int horasAnuales;
    private Curso curso;
    private int horasDesdoble;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;


    public Asignatura(String codigo, String nombre, int horasAnuales, Curso curso,
                      int horasDesdoble, EspecialidadProfesorado especialidadProfesorado, CicloFormativo cicloFormativo) {
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horasAnuales);
        setCurso(curso);
        setHorasDesdoble(horasDesdoble);
        setEspecialidadProfesorado(especialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }


    public Asignatura(Asignatura otraAsignatura) {
        if (otraAsignatura == null) {
            throw new NullPointerException("ERROR: No se puede copiar una asignatura nula.");
        }
        this.codigo = otraAsignatura.codigo;
        this.nombre = otraAsignatura.nombre;
        this.horasAnuales = otraAsignatura.horasAnuales;
        this.curso = otraAsignatura.curso;
        this.horasDesdoble = otraAsignatura.horasDesdoble;
        this.especialidadProfesorado = otraAsignatura.especialidadProfesorado;
        this.cicloFormativo = otraAsignatura.cicloFormativo;
    }

    public String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) {
        if (codigo == null)
            throw new IllegalArgumentException("ERROR: El código no puede ser nulo");
        if (codigo.isEmpty())
            throw new IllegalArgumentException("ERROR: La cadena del nombre no puede ser vacía.");
        if (codigo.isBlank())
            throw new IllegalArgumentException("ERROR: La cadena del correo no es válida.");
        if (!codigo.matches(ER_CODIGO))
            throw new IllegalArgumentException("ERROR: El código debe tener exactamente 4 dígitos.");
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null)
            throw new IllegalArgumentException("ERROR: El nombre no puede ser nulo.");
        if (nombre.isEmpty())
            throw new IllegalArgumentException("ERROR: La cadena del nombre no puede ser vacía.");
        if (nombre.isBlank())
            throw new IllegalArgumentException("ERROR: La cadena del correo no es válida.");

        this.nombre = nombre;
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        if (horasAnuales <= 0)
            throw new IllegalArgumentException("ERROR: Las horas anuales no pueden ser menores o iguales que 0.");
        if (horasAnuales > MAX_NUM_HORAS_ANUALES)
            throw new IllegalArgumentException("ERROR: Las horas anuales no pueden superar " + MAX_NUM_HORAS_ANUALES + ".");

        this.horasAnuales = horasAnuales;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        if (curso == null)
            throw new NullPointerException("ERROR: El curso no puede ser nulo.");
        this.curso = curso;
    }

    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) {
        if(horasDesdoble < 0)
            throw new IllegalArgumentException("ERROR: Las horas de desdoble no pueden ser menores que 0.");

        if (horasDesdoble > MAX_NUM_HORAS_DESDOBLES)
            throw new IllegalArgumentException("ERROR: Las horas de desdoble no pueden superar " + MAX_NUM_HORAS_DESDOBLES + ".");

        this.horasDesdoble = horasDesdoble;
    }

    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        if (especialidadProfesorado == null) {
            throw new NullPointerException("ERROR: La especialidad del profesorado no puede ser nula.");
        }
        this.especialidadProfesorado = especialidadProfesorado;
    }

    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        this.cicloFormativo = cicloFormativo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Asignatura otra = (Asignatura) obj;
        return Objects.equals(codigo, otra.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }


    public String imprimir() {
        return String.format("Código asignatura=%s, nombre asignatura=%s, ciclo formativo=%s",
                codigo, nombre, cicloFormativo.imprimir());
    }



    @Override
    public String toString() {
        return String.format("Código=%s, nombre=%s, horas anuales=%d, curso=%s, horas desdoble=%d, ciclo formativo=%s, especialidad profesorado=%s",
                codigo, nombre, horasAnuales, curso, horasDesdoble, cicloFormativo.imprimir(), especialidadProfesorado);
    }


}

