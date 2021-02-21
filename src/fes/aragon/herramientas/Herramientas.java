package fes.aragon.herramientas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Herramientas {
    //Variables privadas (solo de esta clase)
    private int longitudPalabra = 0;
    private int indicePalabra = 0;
    private String palabra;
    
    public Herramientas(){ //Constructor 
    }
    
    public ArrayList<String> lectura() throws FileNotFoundException, IOException{ //Alamcena la cadena que vamos a leer en un Array
        JFileChooser archivo = new JFileChooser(System.getProperty("user.dir")); //Abre una venta para elegir el archivo en la ruta donde esta guardado esto
        archivo.setMultiSelectionEnabled(false); //Para que no agarre dos archivos
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de texto","txt"); //Solo agarre txt
        archivo.setFileFilter(filtro); //Le damos el filtro
        ArrayList<String> lineas = new ArrayList<>(); //Crea el array list que retornaremos
        int seleccion = archivo.showOpenDialog(null); //Selecciona un archivo
        if(seleccion==JFileChooser.APPROVE_OPTION){ //Si se da la aprovacion del filtro
            File f = archivo.getSelectedFile();//Abre el archivo
            FileReader fr = new FileReader(f);//Listo para leerlo
            BufferedReader bf = new BufferedReader(fr); //Buffer para trabajar con el flujo bytes
            String cad = "";
            while((cad=bf.readLine()) != null){//Lee la linea hasta que encuente un valor null
                lineas.add(cad); //Le añade a lineas el valor de la cadena                
                cad = bf.readLine();
            }
            //Se cierran los canales de comunicacion
            bf.close();
            fr.close();
        }
        return lineas;
    }
    
    public void setPalabra(String palabra){ //Asigna valores para despues leer la palabra
        this.palabra = palabra;
        this.longitudPalabra = palabra.length();
        this.indicePalabra = 0;
    }
    
    public char siguienteCaracter(){ //Nos dara el siguiente caracter
        char c = ' ';
        
        if(indicePalabra<longitudPalabra){
            c = palabra.charAt(indicePalabra);
            indicePalabra++;
        }
    return c;
    }
}