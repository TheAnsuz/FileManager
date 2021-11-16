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

        try {
            ListFile file = FileManager.construct("test\\me\\amrv\\testing\\files\\file.list").asListFile();
//            file.clear();
            file.add("add0");
            file.add("add1");
            file.add("add2");
            file.add("add-index-0", 0);
            file.save(SaveMode.APPEND);

            System.out.println("NULL: " + file.contains(null));
            System.out.println("INDEX: " + file.indexOf(null));

            for (String str : file.toArray())
                System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
