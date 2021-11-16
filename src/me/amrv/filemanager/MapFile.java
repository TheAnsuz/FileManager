/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marruiad
 */
public class MapFile extends BaseFile {

    private Map<String, String> content;
    private final char SEPARATOR = '=';
    
    public MapFile(File file) {
        super(file);
        content = new HashMap<>();
    }

    @Override
    public void clear() {
        content.clear();
    }

    @Override
    protected boolean readProcess() throws IOException {
        if (content == null)
            content = new HashMap<>();
        else
            content.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            reader.lines().forEach((String line) -> {
                System.out.println(line);
                if (line.contains(SEPARATOR+""))
                    content.put(line.substring(0, line.indexOf(SEPARATOR)), line.substring(line.indexOf(SEPARATOR)));
                else
                    content.put(line, "");
            });
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, String> getSource() {
        return content;
    }

    @Override
    protected boolean writeProcessNormal() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean writeProcessAdvanced() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean writeProcessAppend() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean writeProcessAppendAdvanced() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
