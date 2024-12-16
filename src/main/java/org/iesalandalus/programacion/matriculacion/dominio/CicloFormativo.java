package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.Objects;

public class CicloFormativo {

    public static final int MAX_HORAS = 2000;


    private int codigo;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;

    public CicloFormativo(int codigo, String familiaProfesional, Grado grado, String nombre, int horas) {
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }


    public CicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null)
            throw new NullPointerException("No se puede copiar un ciclo formativo nulo.");

        this.codigo = cicloFormativo.codigo;
        this.familiaProfesional = cicloFormativo.familiaProfesional;
        this.grado = cicloFormativo.grado;
        this.nombre = cicloFormativo.nombre;
        this.horas = cicloFormativo.horas;
    }


    public int getCodigo() {
        return codigo;
    }

    private void setCodigo(int identificador) {
        if (identificador < 1000 || identificador > 9999)
            throw new IllegalArgumentException("El código debe ser un número de cuatro dígitos.");

        this.codigo = codigo;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new NullPointerException("El nombre no puede ser nulo.");

        if (nombre.isEmpty())
            throw new IllegalArgumentException("ERROR: La cadena del nombre no puede ser vacía.");

        if (nombre.isBlank())
            throw new IllegalArgumentException("ERROR: La cadena del nombre no es válida");

        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        if (horas <= 0)
            throw new IllegalArgumentException("El número de horas debe ser un número mayor que 0.");

        if (horas > MAX_HORAS)
            throw new IllegalArgumentException("Las horas deben ser mayores que 0 y no superar " + MAX_HORAS + ".");

        this.horas = horas;
    }

    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) {
        if (familiaProfesional==null)
            throw new NullPointerException("ERROR: La familia prosfesional no pued ser nula.");
        if (familiaProfesional.isEmpty())
            throw new IllegalArgumentException("ERROR: La familia profesional está vacía.");
        if (familiaProfesional.isBlank())
            throw new IllegalArgumentException("ERROR: La familia profesional no es válida.");

        this.familiaProfesional = familiaProfesional;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        if (grado==null)
            throw new NullPointerException("El grado no puede ser nulo.");
        this.grado = grado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloFormativo that = (CicloFormativo) o;
        return codigo == that.codigo && horas == that.horas && Objects.equals(familiaProfesional, that.familiaProfesional) && grado == that.grado && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, familiaProfesional, grado, nombre, horas);
    }


    @Override
    public String toString() {
        return "CicloFormativo{" +
                "codigo=" + codigo +
                ", familiaProfesional='" + familiaProfesional + '\'' +
                ", grado=" + grado +
                ", nombre='" + nombre + '\'' +
                ", horas=" + horas +
                '}';
    }

    public String imprimir() {

        return toString();
    }
}

