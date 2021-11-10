/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marruiad
 */
public class ListFile extends BaseFile {

    private List<String> content = new ArrayList<String>();

    public ListFile(File file) {
        super(file);
    }

    @Override
    protected void clear() {
        content.clear();
    }

    @Override
    protected boolean readProcess() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            reader.lines().forEach(line -> {
                content.add(line);
            });
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected boolean writeProcessNormal() throws IOException {
        try (PrintWriter writer = new PrintWriter(file)) {
            content.forEach(string -> {
                writer.println(string);
            });
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected boolean writeProcessAdvanced() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (String str : content) {
                writer.write(str);
                writer.newLine();
            }

            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
