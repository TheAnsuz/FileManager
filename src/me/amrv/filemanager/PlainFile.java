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

    public StringBuilder getData() {
        return content;
    }
    
    public String getDataString() {
        return content.toString();
    }
    
    public void setData(StringBuilder string) {
        content = string;
    }
    
    public void setData(String string) {
        content = new StringBuilder(string);
    }
    // metodo para caracteres
    
    

}
