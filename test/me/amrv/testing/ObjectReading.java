/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author marruiad
 */
public class ObjectReading {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        /*
        Para leer objetos de archivos deben ser serializables, existe un metodo para serializar y deserializar objetos
        pero usarlo directamente en un archivo hará que obtenga el archivo entero como un unico objeto, se debe usar individualmente
        por cada valor
        referencia: https://commons.apache.org/proper/commons-lang/apidocs/src-html/org/apache/commons/lang3/SerializationUtils.html
        */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("")));
        ois.readObject();
    }
}
