package com.devsenior.service;

import com.devsenior.exceptions.AsientoInvalidoException;
import com.devsenior.exceptions.AsientoLibreException;
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
            for(int j = 0; j < COLUMNAS; j++) {
                estadoAsientos[i][j] = asientos [i][j].getEstado(); //Asientos es todo nuestro teatro
                // i y j son los recorriedos que ellos toman para saber si esta Libre o Ocupado.
            }
        }
        return estadoAsientos;
    }
     
    // Metodo para reservar asiento
    public void reservarAsiento(int fila, int columna) throws AsientoInvalidoException, AsientoOcupadoException {
        if(fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS) {
            throw new AsientoInvalidoException("Coordernas fuera de rango. Seleccione una fila entre 0=" + (FILAS - 1)
            + "Y una columna entre =o" + (COLUMNAS -1) + ".");
        }
        
        if(!asientos[fila][columna].isLibre()) {
             throw new AsientoOcupadoException(
                "El asieto en la fila " + fila + " y columna " + columna + "Ya esta ocupado.");
        
             }

            asientos[fila][columna].setEstado(EstadoAsiento.OCUPADO);
          }
    // Metodo para cancerlar reserva
    public void cancerlarReserva(int fila, int columna) throws AsientoInvalidoException, AsientoLibreException {
        if(fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS) {
             throw new AsientoInvalidoException("Selecciono un asiento que no existe.");
        }
        if(asientos[fila][columna].isLibre()) {
             throw new AsientoInvalidoException("El asiento en la fila " + fila + " y columna " + columna + " ya esta libre");
        }
        asientos[fila][columna].setEstado(EstadoAsiento.LIBRE);
    }

    //Metodo para devolver las estadisticas de ocupacion de los asientos
    public String CalcularEstadisticas() {
        int ocupados = 0;
        int libre = 0;
        int totalAsientos = FILAS *COLUMNAS;

        for(int i = 0; i<FILAS; i++) {  //Arryas Bidimencionales 
            for(int j = 0; j < COLUMNAS; j++) { //Esto incrementa 
                if(asientos[i][j].isLibre()) {
                    libre++;
                } else {
                    ocupados++;
                }
                
            }
        }

        double porcentajeOcupacion = (double) ocupados/totalAsientos *100;
        return String.format("--- Estadistica de ocupacion --- \n " + "asientos ocupados %d\n" +
        "asientos libres %d\n" + "porcentaje de ocupacion %.2f%%\n",
        ocupados, libre, porcentajeOcupacion);

    }
}
