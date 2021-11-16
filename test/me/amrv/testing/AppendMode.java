/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.testing;

import me.amrv.filemanager.FileManager;
import me.amrv.filemanager.ListFile;
import me.amrv.filemanager.PlainFile;
import me.amrv.filemanager.SaveMode;

/**
 *
 * @author marruiad
 */
public class AppendMode {

    public static void main(String[] args) {

        PlainFile file = FileManager.construct("D:\\plain.file").asPlainFile();

        file.set("Hola mundo");

        for (int i = 0; i < 100; i++)
            file.save(SaveMode.APPEND);

        System.out.println("DONE - plain");

        ListFile list = FileManager.construct("D:\\list.file").asListFile();

        list.add("Hola buenos dias");

        for (int i = 0; i < 100; i++)
            list.save(SaveMode.APPEND);

        System.out.println("DONE - list");
    }

}
