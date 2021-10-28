package me.amrv.filemanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;

public abstract class BaseFile {

	/**
	 * The system-dependant separator used on the paths
	 */
	private static final char SEPARATOR = File.separatorChar;
	private final File file;
	private final String extension;
	private final BasicFileAttributeView attribute;
	private BasicFileAttributes attributes;

	/**
	 * Creates a easy to manage formatted file using the given file
	 * 
	 * @param file - the file to be managed and formatted
	 */
	protected BaseFile(final File file) {
		this.file = file;
		if (file.getName().contains("."))
			extension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
		else
			extension = "";
		attribute = Files.getFileAttributeView(file.toPath(), BasicFileAttributeView.class);
	}

	/**
	 * Tries to read the file attributes, reloading them, however if there is no way
	 * to access them or an error is thrown it will simply return false
	 * 
	 * @return true if the file has attributes and can be accesed
	 */
	public final boolean canAccessAttributes() {

		return setFileAttributes() && attributes != null;
	}

	protected final boolean setFileAttributes() {
		if (attribute == null)
			return false;
		try {
			attributes = attribute.readAttributes();
			return true;
		} catch (IOException x) {
			return false;
		}
	}

	/**
	 * Gets the extension of the file, this method returns the already obtained
	 * value when the file was created and will always return a string, however is
	 * the file does not have an extension it will return an empty string.
	 * 
	 * <p>
	 * In example a file called <b>file.info</b> will return <b>info</b> but a file
	 * called <b>file.INFO</b> will return <b>INFO</b>
	 * 
	 * @return the file extension as it was defined in the file (UPPER or lower
	 *         case) or a empty string it has no extension
	 */
	public final String getExtension() {
		return extension;
	}

	/**
	 * Obtains the whole file name among its extension.
	 * 
	 * <p>
	 * In example if a file is called <b>file.info</b> it will just return
	 * <b>file.info</b>
	 * 
	 * @return the whole file name among it extension or a empty string it the file
	 *         does not have extension
	 */
	public final String getFullName() {
		return file.getName();
	}

	/**
	 * Obtains the name of the file without the extension, if the file does not have
	 * a name or starts with the extension a empty string will be returned
	 * 
	 * <p>
	 * In example if a file is called <b>file.info</b> this will return <b>file</b>,
	 * also a file called <b>.info</b> with return <b>""</b>
	 * 
	 * @return the name of the file without the extension or an empty string if the
	 *         file does not have name or starts with the extension
	 */
	public final String getShortName() {
		return file.getName().replace("." + extension, "");
	}

	// El 'path' que se uso cuando se declaro el nombre de la variable o el camino
	// que sigue hasta llegar al archivo desde donde se ejecuta el programa
	/**
	 * Obtains the path that was given when the file was created with the file name,
	 * however if the file was declared without any path it will still return the
	 * name of the file
	 * 
	 * <p>
	 * In example a file at <b>temp/files/file.info</b> but declared as
	 * <b>file.info<b> wont return the whole path, if you want the whole path use
	 * {@code getAbstractPath()} or {@code getCannonicalPath()} and if you want just
	 * the path to reach the file without the file name use {@code getParent()} or
	 * {@code getLastParent()}
	 * 
	 * @return the whole path among the full name of the file that was used to
	 *         define the file, if no path was given it will return the file whole
	 *         name
	 */
	public final String getPath() {
		return file.getPath();
	}

	// El 'path' desde el disco duro hasta el archivo, incluyendo el nombre completo
	// del archivo
	/**
	 * Gets the file path as it was defined but resoliving it to reach the root of
	 * the path
	 * <p>
	 * In example a file defined using <b>..\file.info</b> located in
	 * <b>temp\files\</b> will indeed be one subfolder behind and the abstract path
	 * will contain the whole path to it but also containing the moves of the
	 * pointer, meaning in conclusion that the file of this example would be located
	 * inside <b>temp\file.info</b> and the abstract path will return
	 * <b>temp\files\..\file.info</b>
	 * <p>
	 * To get the real path use {@code getCannonicalPath()} however this is not
	 * always needed or use {@code getPath()} to obtain the path that was used to
	 * define the file.
	 * <p>
	 * If you want to get the path without the file name use {@code getParent()} or
	 * {@code getLastParent()}
	 * 
	 * @return the relative path that was given in the declaration concatenated with
	 *         the path from the disk to the file
	 */
	public final String getAbstractPath() {
		return file.getAbsolutePath();
	}

	// El 'path' desde el disco duro hasta el archivo, igual que el getAbstractPath
	// pero convierte
	// los SEPARADORES de cada OS en el correcto
	/**
	 * Gets the real and unique path of the file among the file name and extension,
	 * the cannonical path has nothing to do with the path used to define the file
	 * as it will return the path from the disk to the file in the system
	 * 
	 * <p>
	 * Is this file is a symlink/symbolic link this will try to resolve the path to
	 * reach the real file
	 * 
	 * @return the absolute path from the disk to the file in the system, no matter
	 *         how it was defined
	 * 
	 * @throws IOException - if any error occurs obtaining the file
	 */
	public final String getCannonicalPath() {
		try {
			return file.getCanonicalPath();
		} catch (IOException x) {

			x.printStackTrace();
		}
		return file.getAbsolutePath();
	}

	// raiz en la que esta situado el archivo
	// raiz de temp/data/archivo.txt -> temp/data
	/**
	 * Works as {@code getPath()}, meaning that it will return the path to reach the
	 * file from the current location but it will contain only the path not the file
	 * itself. If the file was declared without any path this will return an empty
	 * string as it wont recognize any folder needed to reach the file
	 * 
	 * @return the path to reach the file from the current location without the file
	 *         name or an empty string if the file is inside the current location
	 */
	public final String getParent() {
		return (file.getParent() == null) ? "" : file.getParent();
	}

	/**
	 * Works as {@code getAbsolutePath()} but it will return the path from the disk
	 * to the previous folder of the file, meaning that the name of the file wont be
	 * given on the returned string
	 * 
	 * @return the path from the disk to the file without the name of the file
	 */
	public final String getWholeParent() {
		final String parent = file.getAbsolutePath();
		String formattedParent = "";

		boolean filename = true;
		int remove = 0;
		for (int i = parent.length() - 1; i >= 0; i--) {

			final char c = parent.charAt(i);

			// Removes the name of the file among the separator that contains it
			if (filename)
				if (c == SEPARATOR) {
					filename = false;
					continue;
				} else
					continue;

			// Checks if the text is a \.. and adds it to the removal queue
			if (c == '.' && parent.charAt(i - 1) == '.' && parent.charAt(i - 2) == SEPARATOR) {
				i -= 2;
				remove++;
			} else {

				// Checks if the character must be in the path and prints it or leaves it
				if (remove > 0)
					if (c == SEPARATOR)
						remove--;
					else
						continue;
				else
					formattedParent = parent.charAt(i) + formattedParent;
			}
		}
		return formattedParent;
	}

	/**
	 * Retrieves the last folder to reach the file working as
	 * {@code getWholeParent()} but returning only the last section of the path
	 * almost as a section of the cannonical path
	 * 
	 * @return the last section of the path to reach the file
	 */
	public final String getLastParent() {
		final String parent = file.getAbsolutePath();
		String formattedParent = "";

		boolean filename = true;
		int remove = 0;
		for (int i = parent.length() - 1; i >= 0; i--) {

			final char c = parent.charAt(i);

			if (filename)
				if (c == SEPARATOR) {
					filename = false;
					continue;
				} else
					continue;

			if (c == '.' && parent.charAt(i - 1) == '.' && parent.charAt(i - 2) == SEPARATOR) {
				i -= 2;
				remove++;
			} else {

				if (remove <= 0) {
					
					if (c == SEPARATOR)
						break;
					else
						formattedParent = parent.charAt(i) + formattedParent;
				} else 
					if (c == SEPARATOR)
						remove--;
					else
						continue;

			}
		}
		return formattedParent;
	}

	// el maximo de espacio en el disco o particion de disco del archivo,
	// reconocible es 8388608 Terabytes
	/**
	 * Obtains, in bytes, the aviable space of the disk unit this file is located
	 * in, or 0L if the file is not in a disk unit or the size cant be measured.
	 * However this method is not completely accurate
	 * <p>
	 * Values over <b>8388608 Terabytes</b> might cause errors and if the size is
	 * greater than {@code Long.MAX_VALUE} then that value will be returned
	 * 
	 * @deprecated Obtaining the disk's free space is a pretty useless method for a
	 *             file
	 * @return
	 */
	@Deprecated
	public final long getFreeSpace() {
		return file.getFreeSpace();
	}

	/**
	 * Obtains, in bytes, the total space of the disk unit this file is located in,
	 * or 0L if the file is not in a disk unit or the size cant be measured. However
	 * this method is not completely accurate
	 * <p>
	 * Values over <b>8388608 Terabytes</b> might cause errors and if the size is
	 * greater than {@code Long.MAX_VALUE} then that value will be returned
	 * 
	 * @deprecated Obtaining the disk space is a pretty useless method for a file
	 * @return
	 */
	@Deprecated
	public final long getTotalSpace() {
		return file.getTotalSpace();
	}

	/**
	 * Obtains the file of the size in bytes of the file or {@code 0L} if the size
	 * cant be measured
	 * 
	 * @return the size in bytes of the file or {@code 0L} if it cant be measured
	 */
	public final long getFileSpace() {
		return file.length();
	}

	/**
	 * Obtains the aviable disk space aviable for this virtual machine, providing a
	 * more accurate measure
	 * <p>
	 * Values over <b>8388608 Terabytes</b> might cause errors and if the size is
	 * greater than {@code Long.MAX_VALUE} then that value will be returned
	 * 
	 * @deprecated Obtaining the disk's usable space is a pretty useless method for
	 *             a file
	 * @return
	 */
	@Deprecated
	public final long getUsableSpace() {
		return file.getUsableSpace();
	}

	// Comprobadores de disponibilidad
	/**
	 * Checks if the file can be executed and the program has permision to do so
	 * <p>
	 * However this method will say if the program has permission to excute but that
	 * does not mean that the file is a executable file
	 * 
	 * @return true if the file exists and the virtual machine can execute it
	 */
	public final boolean canExecute() {
		return file.canExecute();
	}

	/**
	 * Changes the executable flag of the file to be able of being executed or not,
	 * however this does not mean that it will make any file become a executable or
	 * vice versa
	 * <p>
	 * This method works as {@code setExecutable(executable, true)}
	 * 
	 * @param executable - if the file should be able to be executed or not
	 */
	public final void setExecutable(boolean executable) {
		file.setExecutable(executable, true);
	}

	/**
	 * Changes the executable flag of the file to be able of being executed or not,
	 * however this does not mean that it will make any file become a executable or
	 * vice versa
	 * 
	 * @param executable - if the file should be able to be executed or not
	 * @param ownerOnly  - if this operation should be applied to the current
	 *                   user/owner or to everybody
	 */
	public final void setExecutable(boolean executable, boolean ownerOnly) {
		file.setExecutable(executable, ownerOnly);
	}

	/**
	 * Checks if the file can do read operations, this checks for access so it wont
	 * check if any exception could happen
	 * 
	 * @return true if the file exists and the virtual machine can do read
	 *         operations
	 */
	public final boolean canRead() {
		return file.canRead();
	}

	/**
	 * Changes the readable flag of the file allowing or disallowing it to do read
	 * operations
	 * <p>
	 * This method works as {@code setReadable(readable, true)}
	 * 
	 * @param executable - if the file should be able to do read operations
	 */
	public final void setReadable(boolean readable) {
		file.setReadable(readable, true);
	}

	/**
	 * Changes the readable flag of the file allowing or disallowing it to do read
	 * operations
	 * 
	 * @param executable - if the file should be able to do read operations or not
	 * @param ownerOnly  - if this operation should be applied to the current
	 *                   user/owner or to everybody
	 */
	public final void setReadable(boolean readable, boolean ownerOnly) {
		file.setReadable(readable, ownerOnly);
	}

	/**
	 * Checks if the file can do write operations, this checks for access so it wont
	 * check if any exception could happen
	 * 
	 * @return true if the file exists and the virtual machine can do write
	 *         operations
	 */
	public final boolean canWrite() {
		return file.canWrite();
	}

	/**
	 * Changes the writable flag of the file allowing or disallowing it to do write
	 * operations
	 * <p>
	 * This method works as {@code setWritable(writable, true)}
	 * 
	 * @param executable - if the file should be able to do read operations
	 */
	public final void setWritable(boolean writable) {
		file.setWritable(writable, true);
	}

	/**
	 * Changes the writable flag of the file allowing or disallowing it to do write
	 * operations
	 * 
	 * @param executable - if the file should be able to do write operations or not
	 * @param ownerOnly  - if this operation should be applied to the current
	 *                   user/owner or to everybody
	 */
	public final void setWritable(boolean writable, boolean ownerOnly) {
		file.setWritable(writable, ownerOnly);
	}

	/**
	 * Checks if the file exists
	 * 
	 * @return true if the file exists
	 */
	public final boolean exists() {
		return file.exists();
	}

	/**
	 * Checks if the pathname of the file is absolute, meaning that will return true
	 * if at the time the file was defined it was used a path that started on the
	 * disk unit
	 * 
	 * @return true if the defined path to the file started at the disk unit
	 */
	public final boolean isAbsolute() {
		return file.isAbsolute();
	}

	/**
	 * Checks if the file is a directory
	 * 
	 * @return true if this is a directory instead of a file
	 */
	public final boolean isDirectory() {
		return file.isDirectory();
	}

	// Tries to check if the file is a synbolic link, if it isnt it will return true
	// if its not a directory or a file
	/**
	 * Checks if the file is a link to a file, however if the system cant tell if it
	 * is or not the method will just check if the file is not a file or directory
	 * and will return true if that condition is met
	 * 
	 * <p>
	 * This method might not work as if you construct the file it will try to create
	 * it and using that file instead of any possible link on the location
	 * 
	 * @return true if is a symbolic link or true if there is no way to know if it
	 *         is a link and its not a file or directory
	 */
	public final boolean isSynbolicLink() {
		if (canAccessAttributes())
			return attributes.isSymbolicLink();
		return (!file.isDirectory() && !file.isFile());
	}

	/**
	 * Checks if the file is indeed a file, anyway this should be a file given the
	 * fact that you are constructing it with a library dedicated to manipulate
	 * files
	 * 
	 * @return true if the file is indeed a file
	 */
	public final boolean isFile() {
		return file.isFile();
	}

	/**
	 * Checks if the file is hidden, this is denoted by the system and is
	 * system-dependent
	 * 
	 * @return if the file is considered hidden by the OS
	 */
	public final boolean isHidden() {
		return file.isHidden();
	}

	// Eliminacion del archivo
	/**
	 * Deletes the file or directory
	 * <p>
	 * If the file points to a directory it will only be removed if its empty
	 * 
	 * @return true if the operation was successfully completed false otherwise
	 * @throws IOException if any error occurs while removing it (this is hardcoded
	 *                     to be thrown)
	 */
	public final boolean delete() {
		return file.delete();
	}

	/**
	 * Deletes the file or directory when the virtual machine aka program ends
	 * normally, once the deletion is requested and put to queue there is no way to
	 * cancel it, anyway this works with the same conditions as {@code delete()}
	 * <p>
	 * If multiple files are queued to deletion, they will attempt to be removed on
	 * the inverse order they were queued
	 */
	public final void deleteOnExit() {
		file.deleteOnExit();
	}

	// atributos del archivo
	/**
	 * Obtains the date in which the file was last modified in miliseconds since the
	 * epoch or 0L if any errror occurs, negative values indicate time before the
	 * epoch
	 * 
	 * 
	 * @return the date in miliseconds since the file was last modified or 0L if any
	 *         error occurs
	 */
	public final long getLastModifiedTime() {
		return file.lastModified();
	}

	/**
	 * Obtains the date in which the file was last modified using a Date format
	 * 
	 * @return the date in which the file was last modified
	 * @see java.util.Date
	 */
	public final Date getLastModifiedDate() {
		return new Date(file.lastModified());
	}

	/**
	 * Sets the time in miliseconds since the file was last modified
	 * 
	 * @param miliseconds - the time to be set as last modified time
	 */
	public final void setLastModifiedTime(long miliseconds) {
		file.setLastModified(miliseconds);
	}

	/**
	 * Obtains the date in which the file was created in miliseconds since the epoch
	 * or 0L if any errror occurs, negative values indicate time before the epoch
	 * <p>
	 * Some systems may not save this value on files
	 * 
	 * @return the date in miliseconds since the file was created or 0L if any error
	 *         occurs
	 * @throws UnsupportedOperationException if the file attributes couldnt be
	 *                                       obtained
	 */
	public final long getCreationTime() {
		if (canAccessAttributes())
			return attributes.creationTime().toMillis();
		else
			throw new UnsupportedOperationException("Cant obtain file attributes");
	}

	/**
	 * Obtains the date in which the file was created using a Date format
	 * <p>
	 * Some systems may not save this value on files
	 * 
	 * @return the date in which the file was created
	 * @see java.util.Date
	 */
	public final Date getCreationDate() {
		return new Date(getCreationTime());
	}

	/**
	 * Sets the time in miliseconds since the file was created
	 * <p>
	 * Some systems may not save this value on files
	 * 
	 * @param miliseconds - the time to be set as last modified time
	 * @throws UnsupportedOperationException if the file attributes couldnt be
	 *                                       obtained
	 */
	public final void setCreationTime(long miliseconds) {
		if (canAccessAttributes())
			try {
				attribute.setTimes(null, null, FileTime.fromMillis(miliseconds));
			} catch (IOException x) {
				x.printStackTrace();
			}
		else
			throw new UnsupportedOperationException("Cant modify file attributes");
	}

	/**
	 * Obtains the date in which the file was last accessed in miliseconds since the
	 * epoch or 0L if any errror occurs, negative values indicate time before the
	 * epoch
	 * <p>
	 * Some systems may not save this value on files
	 * 
	 * @return the date in miliseconds since the file was last accessed or 0L if any
	 *         error occurs
	 * @throws UnsupportedOperationException if the file attributes couldnt be
	 *                                       obtained
	 */
	public final long getLastAccessTime() {
		if (canAccessAttributes())
			return attributes.lastAccessTime().toMillis();
		else
			throw new UnsupportedOperationException("Cant obtain file attributes");
	}

	/**
	 * Obtains the date in which the file was last accessed using a Date format
	 * <p>
	 * Some systems may not save this value on files
	 * 
	 * @return the date in which the file last accessed
	 * @see java.util.Date
	 */
	public final Date getLastAccessDate() {
		return new Date(getLastAccessTime());
	}

	/**
	 * Sets the time in miliseconds since the file was last accessed
	 * <p>
	 * Some systems may not save this value on files
	 * 
	 * @param miliseconds - the time to be set as last accessed time
	 * @throws UnsupportedOperationException if the file attributes couldnt be
	 *                                       obtained
	 */
	public final void setLastAccessTime(long miliseconds) {
		if (canAccessAttributes())
			try {
				attribute.setTimes(null, FileTime.fromMillis(miliseconds), null);
			} catch (IOException x) {
				x.printStackTrace();
			}
		else
			throw new UnsupportedOperationException("Cant modify file attributes");
	}

}
