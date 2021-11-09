package me.amrv.filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class PlainFile extends BaseFile {

    private StringBuilder content;

    protected PlainFile(File file) {
        super(file);

    }

    @Override
    protected boolean readProcess() throws IOException {
        content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                content = content.append((char) reader.read());
            }
            reader.close();
        }
        return true;
    }

    @Override
    protected boolean writeProcessNormal() throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content.toString());
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected boolean writeProcessAlternative() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content.toString());
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtains the data in the file as a StringBuilder, which should be better
     * than a simple String.
     *
     * @see java.lang.StringBuilder
     * @return the conents of the file as a StringBuilder
     */
    public StringBuilder getData() {
        return content;
    }

    /**
     * Obtains the data in the file as a String.
     *
     * @return the conents of the file as a String
     */
    public String getDataString() {
        return content.toString();
    }

    /**
     * Replaces the file contents with the given StringBuilder, overriding all
     * its contents.
     *
     * @see java.lang.StringBuilder
     * @param string - the StringBuilder that will be used to replace the
     * current file contents
     */
    public void setData(StringBuilder string) {
        content = string;
    }

    /**
     * Replaces the file contents with the given string, overriding all its.
     * contents
     *
     * @param string - the String that will replace the actual contents of the
     * file
     */
    public void setData(String string) {
        content = new StringBuilder(string);
    }

    @Override
    public void clear() {
        content = new StringBuilder();
    }

    /**
     * Adds something at the end of the file in the String format.
     *
     * <p>
     * This method is equal to obtaining the object::toString method.
     *
     * @param obj - the object that will be added to the string
     */
    public void append(Object obj) {
        content.append(obj);
    }

    /**
     * Obtains the length of the stored contents of the file.
     *
     * @return the character length
     */
    public int length() {
        return content.length();
    }

}
