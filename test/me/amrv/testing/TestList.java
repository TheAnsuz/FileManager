/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.testing;

import me.amrv.filemanager.FileManager;
import me.amrv.filemanager.ListFile;
import me.amrv.filemanager.SaveMode;

/**
 *
 * @author marruiad
 */
public class TestList {
    
    public static void main(String[] args) {
        
        ListFile file = FileManager.construct("test\\me\\amrv\\testing\\files\\file.list").asListFile();
        file.add("add");
        file.add("add-1");
        
        file.add("add-index", 0);
        file.save(SaveMode.APPEND);
    }
}
