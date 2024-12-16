package org.iesalandalus.programacion.matriculacion.dominio;

public enum EspecialidadProfesorado {

    INFORMATICA("Informática"),
    SISTEMAS("Sistemas"),
    FOL("FOL");

    private String cadenaAMostrar;

    private EspecialidadProfesorado(String cadenaAMostrar) {
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
