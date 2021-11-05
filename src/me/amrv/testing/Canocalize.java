/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.testing;

import me.amrv.filemanager.BaseFile;

/**
 *
 * @author marruiad
 */
public class Canocalize {
    
    public static void main(String[] args) {
        String parent = "\\1\\2\\3\\4\\5\\.\\6\\7\\8\\.\\..\\..\\.\\9\\..\\..\\10\\..\\.amigo.file";
        
        System.out.println(BaseFile.canonicalize(parent, -1, true));
        System.out.println(BaseFile.canonicalize(parent, 0, true));
        System.out.println(BaseFile.canonicalize(parent, 4, true));
        System.out.println(BaseFile.canonicalize(parent, -1, false));
        System.out.println(BaseFile.canonicalize(parent, 0, false));
        System.out.println(BaseFile.canonicalize(parent, 4, false));
        
    }
}
