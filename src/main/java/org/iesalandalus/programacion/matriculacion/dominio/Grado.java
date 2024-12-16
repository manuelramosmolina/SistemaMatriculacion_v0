package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {

    GDCFGB ("Grado Básico"),
    GDCFGM ("Grado Medio"),
    GDCFGS ("Grado Superior");

    private String cadenaAMostrar;

    private Grado(String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir(int posicion) {
       return String.format("%d.-%s", posicion, cadenaAMostrar);

    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}
