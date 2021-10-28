package me.amrv.filemanager;

import java.io.File;
import java.io.IOException;

public final class FileManager {
	
	public static long getFreeSpace() {
		return 0;

	}
	
	/**
	 * Obtains the respective {@code FileParser} given the name of the file that
	 * must be obtained.
	 * 
	 * @param name - the name of the file to be used for
	 * @return a raw FileParser
	 */
	public static FileParser obtain(String name) {
		return obtain(new File(name));
	}

	/**
	 * Obtains the respective {@code FileParser} of the given file without any
	 * checks of existance.
	 * 
	 * @param file - the file to obtain the {@code FileParser} from
	 * @return a raw FileParser
	 */
	public static FileParser obtain(File file) {
		return new FileParser(file);
	}

	/**
	 * Makes sure the file with the given name exists or at least can be created
	 * before creating the {@code FileParser} from it.
	 * 
	 * @param name - the name of the file that will be checked and obtained
	 * @return a safer FileParser
	 */
	public static FileParser construct(String name) {
		return FileManager.construct(new File(name));
	}

	/**
	 * Makes sure the file exists or at least can be created before creating the
	 * {@code FileParser} from it.
	 * 
	 * @param file - the file that will be checked and obtained
	 * @return a safer FileParser
	 */
	public static FileParser construct(File file) {
		File temp = file.getParentFile();

		if (temp != null && !temp.isDirectory()) {
			temp.mkdirs();
		}

		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException x) {
				x.printStackTrace();
			}
		return new FileParser(file);
	}

	// En vez de crear un archivo nuevo en la ubicacion, comprueba si es un link y obtiene la ubicacion del archivo
	// o en caso de que sea una carpeta accede al archivo con mas posibilidad de compatibilidad (null, txt, config...)
	/**
	 * @throws UnsupportedOperationException as this is not yet implemented on this version of the code
	 */
	public static FileParser resolve(File file) {
		throw new UnsupportedOperationException("This operation is not yet implemented");
	}
}


