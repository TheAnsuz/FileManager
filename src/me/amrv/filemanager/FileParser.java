package me.amrv.filemanager;

import java.io.File;
import me.amrv.filemanager.exception.InvalidFileFormat;

public final class FileParser {

    private final File file;
    private final String extension;

    protected FileParser(File file) {
        this.file = file;
        if (file.getName().contains("."))
            extension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
        else
            extension = "";
    }

    protected boolean checkFormat() {
        return true;
    }

    protected boolean checkFormat(String string) {
        if (string.isEmpty())
            return true;
        else
            return extension.equalsIgnoreCase(string);
    }

    protected boolean checkFormat(String... strings) {

        for (String ext : strings) {
            if (extension.equalsIgnoreCase(ext)) {
                return true;
            }
        }

        return false;

    }

    /**
     * Creates an instance of a Plain file, which fomats the data of the file as
     * if it was all in a String, also saving it as a string to the file.
     *
     * @return a new PlainFile object
     */
    public PlainFile asPlainFile() {
        if (checkFormat()) {
            return new PlainFile(file);
        } else {
            throw new InvalidFileFormat("Plain file does not support ." + extension + " file formats");
        }
    }

}
