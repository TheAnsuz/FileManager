package me.amrv.filemanager;

import java.io.File;

import me.amrv.filemanager.exception.InvalidFileFormat;

public final class FileParser {

	private final File file;
	private final String extension;

	protected FileParser(File file) {
		this.file = file;
		if (file.getName().contains("."))
			extension = file.getName().substring(file.getName().lastIndexOf('.')+1);
		else
			extension = "";
	}
	
	@SuppressWarnings("unused")
	private boolean checkFormat() {
		return true;
	}
	
	private boolean checkFormat(String string) {
		if (string == "")
			return true;
		return extension.equalsIgnoreCase(string);
	}
	
	@SuppressWarnings("unused")
	private boolean checkFormat(String ...strings) {
		if (strings == null)
			return true;

		for (String ext : strings) {
			if (extension.equalsIgnoreCase(ext)) {
				return true;
			}
		}

		return false;
		
	}
	
	/*
	 *	Simple file formats 
	 */

	public PlainFile asPlainFile() {
		if (checkFormat("")) {
			return new PlainFile(file);
		} else {
			throw new InvalidFileFormat("Plain file does not support ." + extension + " file formats");
		}
	}
	
}
