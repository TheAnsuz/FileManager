/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.testing;

import me.amrv.filemanager.FileManager;
import me.amrv.filemanager.MapFile;

/**
 *
 * @author marruiad
 */
public class MapTesting {
    
    public static void main(String[] args) {
        
        MapFile map = FileManager.construct(Paths.TEST + "file.map").asMapFile();
        
        map.getSource().forEach((String k, String v)-> {
            System.out.println(k + " = " + v);
        });
        
    }
    
}
