package com.devsenior.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.devsenior.exceptions.AsientoInvalidoException;
import com.devsenior.exceptions.AsientoLibreException;
import com.devsenior.exceptions.AsientoOcupadoException;
import com.devsenior.model.EstadoAsiento;
import com.devsenior.service.Teatro;
public class menu {
    
    //Creamos atributos del teatro
    private Teatro teatro;
    private Scanner entrada;


    public menu() {
        this.teatro = new Teatro();
        this.entrada = new Scanner(System.in);
    }

    public void ejecutar() {
        int opcion = 0;
        while (opcion !=5) {
            MostrarMenu();

        try {
            opcion = entrada.nextInt();

            entrada.nextLine();

            switch (opcion) {
                case 1:

                mostrarMapasAsientos();
                break;
                    
                  case 2:
                    gestionarReserva();
                    break;

                      case 3:
                        gestionarCancelacion();
                        break;

                        case 4:
                        mostrarEstadisticas();
                        break; 

                        case 5:
                            System.out.println("Gracias por utilizar el sistema. Hasta pronto");
                            break;
            
                default:
                System.out.println("Opcion no valida. Por favor intente de nuevo ");
                    break;
            }
        } catch (InputMismatchException e) {
             System.out.println("ERROR: debe ingresar un numero del menu ");
             entrada.nextLine();
             opcion =0;  //Resetear las opciones
          
        }
            
        }
       
       
}

 private void MostrarMenu() {
            System.out.println("\n---Sistema de reservacion de tratro---");
            System.out.println("1. Ver mapa de asientos");
            System.out.println("2. Reservar un asientos");
            System.out.println("3. Cancelar una reserva");
            System.out.println("4. Ver estadisticas de ocupacion");
            System.out.println("5. Salir ");

             System.out.println("Seleccione una opcion ");

        }

 //Mostrar el mapa de asientos
     private void mostrarMapasAsientos() {

    System.out.println("\n--- mapa de asientos (L = libre, X ocupado) ---");

    EstadoAsiento[][] estado = teatro.obtenerEstadoAsientos();

    // Encabezado de columnas
    System.out.print("   ");
    for (int j = 0; j < estado[0].length; j++) {
        System.out.print(j + "  ");
    }
    System.out.println();

    System.out.println("   " + "--- ".repeat(estado[0].length));

    // Filas
    for (int i = 0; i < estado.length; i++) {
        System.out.printf("%2d| ", i);

        for (int j = 0; j < estado[i].length; j++) {
            char simbolo = (estado[i][j] == EstadoAsiento.LIBRE) ? 'L' : 'X';
            System.out.print(simbolo + "  ");
        }
        System.out.println(); // salto al final de la fila
    }
}
        //Metodo para gestionar reserva

        private void gestionarReserva() {

            try {
                System.out.println("Ingrese el numero de la fila para reservar ");
                int fila = entrada.nextInt();

                System.out.println("Ingrese el numero de la columna para reservar ");
                int columna = entrada.nextInt();

                teatro.reservarAsiento(fila, columna);
                System.out.println("Reserva fue realizada con exito ");


            } catch (InputMismatchException e) {
                 System.out.println("Error: fila y columna deben ser numeros ");
                 entrada.nextLine();
            }
            catch(AsientoOcupadoException | AsientoInvalidoException e) {
                System.out.println("Error al reservar " + e.getMessage());
            }
        }

        // Metodo para gestionar cancelacion
        private void gestionarCancelacion() {

            try {
                System.out.println("Ingrese el numero de fila para cancelar ");
                 int fila = entrada.nextInt();

                 System.out.println("Ingrese el numero de columna para cancelar ");
                    int columna = entrada.nextInt();

                    //LLamar al Metodo para que intente hacer la cancelacion
                    teatro.cancerlarReserva(fila, columna);
                    System.out.println("Cancelacion realizada con exito ");

            } catch (InputMismatchException e) {
                 System.out.println("Error la fila y columna deber ser numeros ");
                  entrada.nextLine();
               
            } catch (AsientoLibreException | AsientoInvalidoException e) {
                 System.out.println("Error al cancelar  " + e.getMessage());
            }
        }

        private void mostrarEstadisticas() {
            String estadistica = teatro.CalcularEstadisticas();
             System.out.println("Las estadisticas son " + estadistica);
        }
}