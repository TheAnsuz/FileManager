/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.testing;

import me.amrv.filemanager.FileManager;

/**
 *
 * @author marruiad
 */
public class PlainFile {
    
    
    public static void main(String[] args) {
        
        me.amrv.filemanager.PlainFile file = FileManager.construct(Paths.TEST + "\\file.str").asPlainFile();
        
        file.reload();
        
        System.out.println("> " + file.getData());
    }
}
