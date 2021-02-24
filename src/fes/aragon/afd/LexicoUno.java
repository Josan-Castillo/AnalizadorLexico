package fes.aragon.afd;

import fes.aragon.herramientas.ErrorLexico;
import fes.aragon.herramientas.Herramientas;
import fes.aragon.herramientas.RangoDeValores;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LexicoUno {
    public static void main(String[] args) {
        int estado = 1;
        Herramientas hr = new Herramientas(); //Creo mis herramientas
        ArrayList<String> lineas = null; //ArrayList donde se guardara el archivo que se leera
        char simbolo = ' ';
        int linea = 0;
        
        try {
            lineas = hr.lectura(); //Lee el archivo y se lo asigna a lineas
            while(linea<lineas.size()){
                hr.setPalabra(lineas.get(linea)); //Le envia la palabra para poder leerla
                simbolo = hr.siguienteCaracter(); //Lee el siguiente caracter
                while(simbolo != 32){ //Si el simbolo es diferente a elemento vacio
                    switch(estado){
                        case 1:
                            if(RangoDeValores.es_Letra(simbolo)){ //Si es letra
                                estado = 3; //Se va al estado 3
                            }
                            else if(RangoDeValores.es_NumeroEnteroPositivo(simbolo)){ //Si es entero positivo
                            estado = 2; //Se va al estado 2
                            }
                            else{
                                throw new ErrorLexico("Caracter no valido, linea " +(linea+1)); //Imprime el erro con la linea de ubicacion
                            }
                            break;
                        case 2:
                            throw new ErrorLexico("Caracter no valido, linea " +(linea+1)); //Imprime el error con la linea de ubicacion
                        case 3:
                            if(RangoDeValores.es_Letra(simbolo)){
                                estado = 3;
                            }
                            else if(RangoDeValores.es_NumeroEnteroPositivo(simbolo)){
                                estado = 3;
                            }
                            else{
                                throw new ErrorLexico("Caracter no valido, linea " +(linea+1)); //Imprime el erro con la linea de ubicacion
                            }
                            break;
                    }
                        simbolo = hr.siguienteCaracter();
                }
                if(estado == 3){
                    System.out.println("Variable valida, linea "+(linea+1));
                }
                else{
                    System.out.println("Variable no valida, linea "+(linea+1));
                }
                linea++;
                estado = 1;
                simbolo = ' ';
            } 
        } 
        catch (ErrorLexico ex) {
            ex.printStackTrace(); //Para que el programador vea lo que pasa
            JOptionPane.showMessageDialog(null, ex.getMessage()); //Muestra el mensaje de error
        }
        catch (IOException ex) {
            ex.printStackTrace(); //Para que el programador vea lo que pasa
            JOptionPane.showMessageDialog(null, "Error en el archivo"); //Muestra el mensaje de error
        }
    }           
}