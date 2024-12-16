package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {

    private static final String ER_TELEFONO = "\\d{9}";
    private static final String ER_CORREO = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
    private static final String ER_DNI = "\\d{8}[A-HJ-NP-TV-Z]";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";
    private static final String ER_NIA = "\\d+";
    private static final int MIN_EDAD_ALUMNADO = 16;

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;


    public Alumno(String nombre, String telefono, String correo, String dni, LocalDate fechaNacimiento){

        setNombre(nombre);
        setTelefono(telefono);
        setCorreo(correo);
        setDni(dni);
        setFechaNacimiento(fechaNacimiento);
        setNia(nombre.substring(0, Math.min(nombre.length(), 4)) + dni.substring(dni.length() - 3));
    }

    public Alumno (Alumno alumno){

        if(alumno==null)
            throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");

        setNombre(alumno.getNombre());
        setTelefono(alumno.getTelefono());
        setDni(alumno.getDni());
        setFechaNacimiento(alumno.getFechaNacimiento());

        if (nombre == null || dni == null)
            throw new NullPointerException("ERROR: Nombre o DNI no pueden ser nulos.");
        setNia(nombre.substring(0, Math.min(nombre.length(), 4)) + dni.substring(dni.length() - 3));
    }



    private String formateaNombre(String nombre) {
        if (nombre == null)
            throw new NullPointerException("ERROR: Se ha introducido un nombre nulo.");

        if (nombre.isEmpty())
            throw new IllegalArgumentException("ERROR: La cadena del nombre no puede ser vacía.");

        if (nombre.isBlank())
            throw new IllegalArgumentException("ERROR: Ha introducido un nombre no válido");

        String nombreFormateado="";
        String nombreModificado = nombre.toLowerCase().trim();
        String[] nombreArray = nombreModificado.split("\\s+");

        for (int i = 0; i<nombreArray.length; i++)
            nombreFormateado += nombreArray[i].substring(0,1).toUpperCase() + nombreArray[i].substring(1)+" ";

        return nombreFormateado.trim();
    }

    private boolean comprobarLetraDni(String dni){

        if (dni == null)
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");

        if (dni.isEmpty())
            throw new IllegalArgumentException("ERROR: La cadena del DNI no puede ser vacía.");

        if (dni.isBlank())
            throw new IllegalArgumentException("ERROR: La cadena del DNI no es válida");

        Pattern patronDNI = Pattern.compile(ER_DNI);
        Matcher m;
        m = patronDNI.matcher(dni);

        if (!m.matches())
            throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");

        int numeroDni = Integer.parseInt(m.group(1));

        int resultadoDivision = numeroDni%23;
        String[] tablaLetras = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};

        if (m.group(2).equals(tablaLetras[resultadoDivision]))
            return true;

        return false;
    }


    public String getNia() {
        return nia;
    }

    private void setNia() {
       if (nia == null)
            throw new NullPointerException("ERROR: el Nia es nulo.");

        if (nia.isEmpty())
            throw new IllegalArgumentException("ERROR: La cadena del Nia no puede ser vacía.");

        if (nia.isBlank())
            throw new IllegalArgumentException("ERROR: Nia no válido");

        this.nia = getNia();
    }

    private void setNia(String nia){
        if (nia == null)
            throw new NullPointerException("ERROR: el Nia es nulo.");

        if (nia.isEmpty())
            throw new IllegalArgumentException("ERROR: La cadena del Nia no puede ser vacía.");

        if (nia.isBlank())
            throw new IllegalArgumentException("ERROR: Nia no válido");

        String primerosCuatro = nombre.length() >= 4 ? nombre.substring(0, 4).toLowerCase() : "";
        String tresUltimosDni = dni.length() >= 8 ? dni.substring(dni.length() - 3) : "";
        this.nia = primerosCuatro + tresUltimosDni;
        if (!nia.matches(ER_NIA))
            this.nia = nia;
        else
            throw new IllegalArgumentException("ERROR: El nia no cumple el patrón");

    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        if (nombre == null)
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");

        if (nombre.isEmpty())
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");

        if (nombre.isBlank())
            throw new IllegalArgumentException("ERROR: Ha introducido un nombre no válido");

        this.nombre = formateaNombre(nombre);
    }

    public String getTelefono() {

        return telefono;
    }

    public void setTelefono(String telefono) {

        if (telefono==null)
            throw new NullPointerException("ERROR: El teléfono de un alumno no puede ser nulo.");
        if (telefono.isEmpty())
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");
        if (telefono.isBlank())
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");
        if (!telefono.matches(ER_TELEFONO))
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");

        this.telefono = telefono;

    }

    public String getCorreo() {

        return correo;
    }

    public void setCorreo(String correo) {

        if (correo==null)
            throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");
        if (correo.isEmpty())
            throw new IllegalArgumentException("ERROR: La cadena del nombre no puede ser vacía.");
        if (correo.isBlank())
            throw new IllegalArgumentException("ERROR: La cadena del correo no es válida.");
        if (!correo.matches(ER_CORREO))
            throw new IllegalArgumentException("ERROR: el correo no cumple patrón");

        this.correo = correo;
    }

    public String getDni() {

        return dni;
    }

    private void setDni(String dni){

        if (dni == null)
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");

        if (dni.isEmpty())
            throw new IllegalArgumentException("ERROR: La cadena del DNI no puede ser vacía.");

        if (dni.isBlank())
            throw new IllegalArgumentException("ERROR: La cadena del DNI no válido");

        if (!dni.matches(ER_DNI))
            throw new IllegalArgumentException("ERROR: el dni no cumple el patrón");

        this.dni = getDni();

    }

    public LocalDate getFechaNacimiento() {

        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento) {

        if (fechaNacimiento==null)
            throw new NullPointerException("ERROR: Fecha nacimiento nula.");
        if (fechaNacimiento.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("ERROR: La fecha de nacimiento no puedeser posterior.");
        if (fechaNacimiento.isBefore(LocalDate.of(1900, 1, 1)))
            throw new IllegalArgumentException("ERROR: Edad no válida");

        if (!fechaNacimiento.isAfter(LocalDate.now().minusYears(MIN_EDAD_ALUMNADO))) {
            throw new IllegalArgumentException("ERROR: El alumno debe tener al menos " + MIN_EDAD_ALUMNADO + " años.");
        }

        this.fechaNacimiento = fechaNacimiento;
    }

    private String getIniciales(){

        String[] nombreArray = this.getNombre().split(" ");
        String iniciales = "";

        for (int i=0; i< nombreArray.length;i++)
            iniciales += nombreArray[i].substring(0,1);

        return iniciales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(dni, alumno.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    public String imprimir() {
        return String.format("Dni: %d, Nombre: %s, Nia: %s", dni, nombre, nia);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", nia='" + nia + '\'' +
                '}';
    }
}


