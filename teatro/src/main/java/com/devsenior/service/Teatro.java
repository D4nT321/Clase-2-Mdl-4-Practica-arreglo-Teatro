package com.devsenior.service;

import java.nio.channels.IllegalSelectorException;

import com.devsenior.exceptions.AsientoInvalidoException;
import com.devsenior.exceptions.AsientoOcupadoException;
import com.devsenior.model.Asiento;
import com.devsenior.model.EstadoAsiento;

public class Teatro {
    
    private final int FILAS = 10;
    private final int COLUMNAS = 6;
    private Asiento [] [] asientos;



    public Teatro() {
        asientos = new Asiento[FILAS][COLUMNAS];
        inicializarAsientos();
    }


    private void inicializarAsientos() {
        for(int i = 0; i < FILAS; i ++) {
            for(int j = 0; j < COLUMNAS; j++) {
                asientos[i][j] = new Asiento();
            }
        }
    }
    // Metodo para ver los Estados de Asientos
    public EstadoAsiento[][] obtenerEstadoAsientos() {
        EstadoAsiento[][] estadoAsientos = new EstadoAsiento[FILAS][COLUMNAS];
        for(int i = 0; i < FILAS; i++) {
            for(int j = 0; j < COLUMNAS; i++) {
                estadoAsientos[i][j] = asientos [i][j].getEstado(); //Asientos es todo nuestro teatro
                // i y j son los recorriedos que ellos toman para saber si esta Libre o Ocupado.
            }
        }
        return estadoAsientos;
    }
     
    // Metodo para reservar asiento
    public boolean reservarAsiento(int fila, int columna) throws AsientoInvalidoException, AsientoOcupadoException {
        if(fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("Fila o columna fuera de rango");
        }
        if(asientos[fila][columna].isLibre()) {
            asientos[fila][columna].setEstado(EstadoAsiento.OCUPADO);
            return true;
        } else{
            throw new AsientoOcupadoException("El asiento ya esta reservado");
        }
    }
 


}
