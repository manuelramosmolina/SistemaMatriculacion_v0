package org.iesalandalus.programacion.matriculacion.dominio;

public enum Curso {
    PRIMERO("Primero"),
    SEGUNDO("Segundo");

    private String cadenaAMostrar;


    private Curso(String cadenaAMostrar) {
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

