package fes.aragon.afd;

import fes.aragon.herramientas.ErrorLexico;
import fes.aragon.herramientas.Herramientas;
import fes.aragon.herramientas.RangoDeValores;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LexicoDos {
    public static void main(String[] args) throws ErrorLexico {
        int estado = 0;
        Herramientas hr = new Herramientas(); //Creo mis herramientas
        ArrayList<String> lineas = null; //ArrayList donde se guardara el archivo que se leera
        char simbolo = ' ';
        int linea = 0;
        String[][] tabla  = {{"2","1","0"},{"1","1","0"},{"2","2","1"}}; //Tabla de transcicion, el numero en la posicion 2 de cada fila es aceptar (1) o error (0)
        int entrada = 0;
        
        try {
            lineas = hr.lectura(); //Lee el archivo y se lo asigna a lineas
            while(linea<lineas.size()){
            hr.setPalabra(lineas.get(linea)); //Le envia la palabra para poder leerla
            do{
                simbolo = hr.siguienteCaracter();
                if(RangoDeValores.es_Letra(simbolo)){
                    entrada = 0;
                }
                else if(RangoDeValores.es_NumeroEnteroPositivo(simbolo)){
                    entrada = 1;
                }
                else if(RangoDeValores.fin_Cadena(simbolo)) {
                    entrada  = 2;
                }
                else{
                    throw new ErrorLexico("Caracter no valido, linea "+(linea+1));
                }
                int valor = Integer.valueOf(tabla[estado][entrada]);
                estado = valor;
                if(estado==0){
                    throw new ErrorLexico("Caracter no valido, linea "+(linea+1));
                }
            }while(simbolo!=59);
            if (entrada==2 && estado==1){
                System.out.println("Variable valida, linea "+(linea+1));
            }
            else if (entrada==2 && estado==0){
                System.out.println("Variable no valida, linea "+(linea+1));
            }
            estado = 0;
            linea++;
        }    
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en el archvio");
        } catch (ErrorLexico ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
            }
        
    }
