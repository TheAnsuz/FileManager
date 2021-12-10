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
import java.util.Collection;
import java.util.List;

/**
 *
 * @author marruiad
 */
public class ListFile extends BaseFile {

    private List<String> content;

    public ListFile(File file) {
        super(file);
        content = new ArrayList<>();
    }

    @Override
    public void clear() {
        content.clear();
    }

    @Override
    protected boolean readProcess() throws IOException {
        if (content == null)
            content = new ArrayList<>();
        else
            content.clear();
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
        try (FileWriter writer = new FileWriter(file, true)) {
            if (this.size() > 0)
                writer.write(System.lineSeparator());
            for (String str : content.subList(0, content.size() - 1)) {
                writer.write(str + System.lineSeparator());
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
    protected boolean writeProcessAppendAdvanced() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (this.size() > 0)
                writer.newLine();
            for (String str : content.subList(0, content.size() - 1)) {
                writer.write(str + System.lineSeparator());
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
     * Replaces the contents of the file with the given list, if a null value is
     * given, it will create an empty one.
     *
     * @param list the list that will override the contents of the file
     */
    public void set(List<String> list) {
        content = (list == null) ? new ArrayList<>() : list;
    }

    /**
     * Replaces the contents of the file with the given array, if the array is
     * null, an empty one will be used.
     *
     * @param array the array that will override the contents of the file
     * @return true if the operation was succesful, false otherwise
     */
    public boolean set(String[] array) {
        content.clear();
        return content.addAll((array == null) ? Arrays.asList(new String[0]) : Arrays.asList(array));
    }

    /**
     * Replaces the line in the given location, if the string is null an empty
     * one will be placed.
     *
     * @param index the position where the string will be replaced
     * @param str the string that will replace the content of the file or null
     * for an empty string
     * @throws IndexOutOfBoundsException if the index is outside the file
     * <tt>(index &lt; 0 || index &gt;= size)</tt>
     */
    public void replace(int index, String str) {
        if (index < 0 || index >= content.size())
            throw new IndexOutOfBoundsException(
                    "Cannot replace a string positioned outside the file index");
        content.set(index, (str == null) ? "" : str);
    }

    /**
     * Adds the given string at the end of the file or an empty string if the
     * given value is null.
     *
     * @param str the string to be added
     */
    public void add(String str) {
        content.add((str == null) ? "" : str);
    }

    /**
     * Inserts the given string just before the line in the given index, in
     * example a file containing <tt>"first", "second", "third"</tt> that adds a
     * <tt> "hello"</tt> at position 1 will result on
     * <tt>"first", "hello", "second", "third"</tt>.
     *
     * <p>
     * If a null string is given it will be replaced with an empty string.
     *
     * @param str the string that will be inserted
     * @param index the position where the operation will take place
     *
     * @throws IndexOutOfBoundsException if the index is outside the file
     * <tt>(index &lt; 0 || index &gt;= size)</tt>
     */
    public void add(String str, int index) {
        if (index < 0 || index >= content.size())
            throw new IndexOutOfBoundsException(
                    "Cannot add a string positioned outside the file index");
        content.add(index, (str == null) ? "" : str);
    }

    /**
     * Gets the line in the given position of the file.
     *
     * @param index the position of the string in the file
     * @return the line in the given index
     *
     * @throws IndexOutOfBoundsException if the index is outside the range of
     * the file lines
     */
    public String get(int index) {
        if (index < 0 || index >= content.size())
            throw new IndexOutOfBoundsException(
                    "Cannot get a string positioned outside the file index");
        return content.get(index);
    }

    /**
     * Obtains the lines file as if they were a <tt>List&lt;String&gt;</tt>.
     *
     * @return a list of strings with the contents of the file
     */
    public List<String> get() {
        return content;
    }

    /**
     * Obtains the lines of the file as an array of strings.
     *
     * @return an array of strings
     */
    public String[] toArray() {
        return content.toArray(new String[content.size() - 1]);
    }

    /**
     * Checks if the file contains the given string, note that this method will
     * compare using <tt>.equalsTo()</tt>.
     * <p>
     * Any null string will be replaced with an empty string
     *
     * @param str the exact string that will be searched
     * @return true if the file contains exactly the given string, false
     * otherwise
     */
    public boolean contains(String str) {
        if (str == null)
            str = "";
        return content.contains(str);
    }

    /**
     * Checks if the file contains the given string, note that this method will
     * compare using <tt>.equalsIgnoreCase</tt>.
     *
     * @param str the exact ignoring case string that will be searched
     * @return true if the file contains exactly (no case sensitive) the given
     * string, false otherwise
     */
    public boolean containsIgnoreCase(String str) {
        if (str == null)
            return content.contains("");
        return content.stream().anyMatch((line) -> (line.equalsIgnoreCase(str)));
    }

    /**
     * Returns the index of the first occurence of the given string in the file.
     * or -1 if the string is not found
     *
     * @param str the String that will be searched
     * @return the index of the first occurence, -1 if the file does not contain
     * the string or <tt>Integer.MAX_VALUE</tt> if the index is greater than the
     * int size
     */
    public int indexOf(String str) {
        return content.indexOf(str);
    }

    /**
     * Returns the index of the last occurence of the given string in the file.
     *
     * @param str the String that will be searched
     * @return the index of the last occurence, -1 if the file does not contain
     * the string or <tt>Integer.MAX_VALUE</tt> if the index is greater than the
     * int size
     */
    public int lastIndexOf(String str) {
        return content.lastIndexOf(str);
    }

    /**
     * Returns the amount of indexes of the file, also known as the amount of
     * lines.
     *
     * @return the amount of files
     */
    public int length() {
        return content.size();
    }

    /**
     * Checks if the file is empty.
     *
     * @return true if the stored contents of the file are empty, false
     * otherwise
     */
    public boolean isEmpty() {
        return content.isEmpty();
    }

    /**
     * Removes the item at the given position of the file, returning the removed
     * element, however if the file does not contain that index <tt>(index &lt;
     * 0 || index &gt;= size)</tt> this will do nothing and return an empty
     * string.
     *
     * @param index the position from the file
     * @return the removed value or an empty string if the index is nos valid
     */
    public String remove(int index) {
        if (index < 0 || index >= content.size())
            return "";
        return content.remove(index);
    }

    /**
     * Removes the first occurence of the file that matches exactly the given
     * one. If the file does not contain the string it will simply do nothing.
     *
     * @param str the string to remove
     * @return true if the operation succeded, false othersie
     */
    public boolean removeFirst(String str) {
        if (str == null)
            str = "";
        return content.remove(str);
    }

    /**
     * Removes all occurences of the given collection of strings from the file,
     * note that this method is case sensitive.
     *
     * @param c the collection of string that will be removed
     * @return true if there was at least one removal operation, false otherwise
     */
    public boolean removeAll(Collection<String> c) {
        if (c == null)
            return false;
        return content.removeAll(c);
    }

    /**
     * Removes all occurences of the given strings from the file, note that this
     * method is case sensitive.
     *
     * @param str the strings that will be removed
     * @return true if there was at least one removal operation, false otherwise
     */
    public boolean removeAll(String... str) {
        if (str == null)
            return false;
        return content.removeAll(Arrays.asList(str));
    }

    /**
     * Removes all occurences of the given string from the file, note that this
     * method is case sentisive.
     *
     * @param str the string that will be removed
     * @return true if the operation succeded, false otherwise
     */
    public boolean removeAll(String str) {
        if (str == null)
            return false;
        return content.removeAll(Arrays.asList(str));
    }

    /**
     * Removes the last occurence of the file string that matches exactly the
     * given one. If the file does not contain the string it will simply do
     * nothing.
     *
     * @param str the string to remove
     * @return the string that was removed
     */
    public String removeLast(String str) {
        if (str == null)
            return "";
        if (content.contains(str))
            return content.remove(content.lastIndexOf(str));
        else
            return "";
    }
}
