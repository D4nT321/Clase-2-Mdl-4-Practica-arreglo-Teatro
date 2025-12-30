package com.devsenior.model;

public class Asiento {
    // Atributos

    private EstadoAsiento estado;

    public Asiento () {
        this.estado =  EstadoAsiento.LIBRE; //Estado asiento debe iniciar libre, para que mas adelante cambie de estado
    }

    public EstadoAsiento getEstado() {
        return estado;

    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado; 

    }


    @Override
    public String toString() {
        return "Asiento{"+
        "estado=" + estado +
        "}";
    }

    public boolean isLibre() {
        return this.estado == EstadoAsiento.LIBRE; 
    }
}
