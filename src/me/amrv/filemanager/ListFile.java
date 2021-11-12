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
import java.util.Arrays;
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
        if (content == null)
            content = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            reader.lines().forEach(content::add);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected boolean writeProcessNormal() throws IOException {
        try (PrintWriter writer = new PrintWriter(file)) {
            content.subList(0, content.size() - 1).forEach(string -> {
                writer.println(string);
            });
            writer.print(content.get(content.size() - 1));
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

            for (String str : content.subList(0, content.size() - 1)) {
                writer.write(str);
                writer.newLine();
            }
            writer.write(content.get(content.size() - 1));
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    protected boolean writeProcessAppend() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (this.size() > 0)
                writer.newLine();
            for (String str : content.subList(0, content.size() - 1)) {
                writer.write(str);
                writer.newLine();
            }
            writer.write(content.get(content.size() - 1));
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Replaces the contents of the file with the given list
     *
     * @param list - the list that will override the contents of the file
     */
    public void set(List<String> list) {
        content = (list == null) ? new ArrayList<String>() : list;
    }

    /**
     * Replaces the contents of the file with the given array
     *
     * @param array - the array that will override the contents of the file
     * @return true if the operation was succesful, false otherwise
     */
    public boolean set(String[] array) {
        content.clear();
        return content.addAll((array == null) ? Arrays.asList(new String[0]) : Arrays.asList(array));
    }

    /**
     * Replaces the line in the given location
     *
     * @param index - the position where the string will be replaced
     * @param str - the string that will replace the content of the file
     */
    public void replace(int index, String str) {
        if (index < 0 || index >= content.size())
            throw new IndexOutOfBoundsException("Cannot replace a string positioned outside the minimun or maximun size of the list");
        content.set(index, (str == null) ? "" : str);
    }

    /**
     * Adds the given string at the end of the list
     *
     * @param str
     */
    public void add(String str) {
        content.add((str == null) ? "" : str);
    }

    public void add(String str, int index) {
        content.add(index, (str == null) ? "" : str);
    }

    public String get(int index) {
        return content.get(index);
    }

    public List<String> get() {
        return content;
    }

    public String[] toArray() {
        return (String[]) content.toArray();
    }

    public boolean contains(String str) {
        if (str == null)
            return false;
        return content.contains(str);
    }

    public int indexOf(String str) {
        if (str == null)
            return -1;
        return content.indexOf(str);
    }

    public int lastIndexOf(String str) {
        if (str == null)
            return -1;
        return content.lastIndexOf(str);
    }

    public int length() {
        return content.size();
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }

}
