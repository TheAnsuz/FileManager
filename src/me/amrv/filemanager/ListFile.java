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

	private List<String> content;

	public ListFile(File file) {
		super(file);
	}

	@Override
	public void clear() {
		content.clear();
	}

	@Override
	protected boolean readProcess() throws IOException {
		if (content == null)
			content = new ArrayList<String>();
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
				if (content.get(content.size() - 1) == string)
					writer.print(string);
				else
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
				if (content.get(content.size() - 1) != str)
					writer.newLine();
			}

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
	 * @param str   - the string that will replace the content of the file
	 */
	public void replace(int index, String str) {
		if (index < 0 || index >= content.size())
			throw new IndexOutOfBoundsException(
					"Cannot replace a string positioned outside the minimun or maximun size of the list");
		content.set(index, (str == null) ? "" : str);
	}

	/**
	 * Adds the given string at the end of the list
	 *
	 * @param str - the string to be added
	 */
	public void add(String str) {
		content.add((str == null) ? "" : str);
	}

	/**
	 * Inserts the given string just before the line in the given index, in example
	 * a list containing <code>{"first", "second", "third"}</code> that adds a
	 * {@code "hello"} at position 1 will result on
	 * <code>{"first", "hello", "second", "third"}</code>
	 * 
	 * @param str   - the string that will be inserted
	 * @param index - the position where the operation will take place
	 * 
	 * @throws IndexOutOfBoundsException if the index is not in range of the
	 *                                   contents of the list
	 */
	public void add(String str, int index) {
		if (index < 0 || index >= content.size())
			throw new IndexOutOfBoundsException(
					"Cannot replace a string positioned outside the minimun or maximun size of the list");
		content.add(index, (str == null) ? "" : str);
	}

	/**
	 * Gets the line in the given position of the file
	 * 
	 * @param index - the position of the string in the file
	 * @return the line in the given index
	 * 
	 * @throws IndexOutOfBoundsException if the index is outside the range of the
	 *                                   file lines
	 */
	public String get(int index) {
		if (index < 0 || index >= content.size())
			throw new IndexOutOfBoundsException(
					"Cannot replace a string positioned outside the minimun or maximun size of the list");
		return content.get(index);
	}

	/**
	 * Obtains the lines file as if they were a {@code List<String> }
	 * 
	 * @return a List of strings with the contents of the file
	 */
	public List<String> get() {
		return content;
	}

	/**
	 * Obtains the lines of the file as an array of strings
	 * 
	 * @return an array of strings
	 */
	public String[] toArray() {
		return content.toArray(new String[content.size() - 1]);
	}

	/**
	 * Checks if the file contains the given string, note that this method will
	 * compare using {@code .equalsTo()}
	 * 
	 * @param str - the exact string that will be searched
	 * @return true if the file contains exactly the given string, false otherwise
	 */
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
