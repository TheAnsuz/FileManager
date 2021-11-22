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

    private static boolean FORMAT_LINES = true;
    private static String DEFAULT_VALUE = "";
    private static boolean REPLACE_ON_REPEAT = true;

    /**
     * Enables or disables the line formatter wich will trim the key and value
     * of the contents.
     *
     * @param formatLines if the lines should be formatted or not
     */
    public static void setOptionFormatLines(boolean formatLines) {
        FORMAT_LINES = formatLines;
    }

    /**
     * Sets the default value that any content without value/separator will
     * have.
     *
     * @param defaultValue a string containing the default value
     */
    public static void setOptionDefaultValue(String defaultValue) {
        DEFAULT_VALUE = defaultValue;
    }

    /**
     * Allows or disallows the replacement of repeated values of the file with
     * the newers or keeps the old one.
     *
     * @param replace if the key should be replaced with the newer one
     */
    public static void setOptionReplaceRepeated(boolean replace) {
        REPLACE_ON_REPEAT = replace;
    }

    private Map<String, String> content = new HashMap<>();
    private final String SEPARATOR;

    public MapFile(File file, String separator) {
        super(file);
        SEPARATOR = separator;
    }

    @Override
    public void clear() {
        content.clear();
    }

    @Override
    protected boolean readProcess() throws IOException {
        System.out.println("Map reloading");
        if (content == null)
            content = new HashMap<>();
        else
            content.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            reader.lines().forEach((String line) -> {

                if (line.contains(SEPARATOR)) {
                    final String key = (FORMAT_LINES) ? line.substring(0, line.indexOf(SEPARATOR)).trim()
                            : line.substring(0, line.indexOf(SEPARATOR));
                    final String value = (FORMAT_LINES) ? line.substring(line.indexOf(SEPARATOR) + 1).trim()
                                    : line.substring(line.indexOf(SEPARATOR) + 1);
                    if (content.containsKey(key) && REPLACE_ON_REPEAT || content.isEmpty() || !content.containsKey(key))
                        content.put(key, value);
                } else {
                    content.put((FORMAT_LINES) ? line.trim() : line, DEFAULT_VALUE);
                }
            });
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, String> getSource() {
        System.out.println(content.size());
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
