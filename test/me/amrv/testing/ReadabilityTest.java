package me.amrv.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Scanner;
import me.amrv.filemanager.FileManager;

public class ReadabilityTest {

	/*
	 * Test para la velocidad de escritura y lectura con los diferentes metodos se
	 * aplicara por defecto el mas rapido y se descartaran los demas o se dejaran
	 * como candidatos a la hora de guardar.
	 * 
	 * Tras esto hay que crear una forma de guardar y obtener objetos directamente
	 * del archivo
	 * 
	 * FileReader - cadenas de caracteres (encoding & buffer predefinidos)
	 * FileInputStream - cadenas de caracteres (encoding & buffer deben ser
	 * espificados) BufferedReader - cadenas de caracteres (buffer por defecto o
	 * especificado) [eficiente en archivos muy grandes] Buffer-8kb
	 * InputStreamReader - obtiene bytes y los convierte en caracteres, sustituir
	 * por BufferedReader Files - cadenas de caracteres o bytes (no usa buffer)
	 * Scanner - cualquier tipo de dato crudo
	 * 
	 */
        private static StringBuilder builder = new StringBuilder();
        private static boolean showContent = false;
        
	public static void main(String[] args) {
                int minChar = 0xfeff0000;
                int maxChar = 0xfeff0408;
            
                System.out.println("Rango: " + minChar + " - " + maxChar);
                for (int c = minChar; c <= maxChar; c++) {
                    System.out.print((char) c);
                    builder.append((char)c);
                }
               
                System.out.println();
		Scanner s = new Scanner(System.in);
		File small = FileManager.construct(Paths.TEST + "\\readability.txt").asPlainFile().getFile();

                // String builder guarda bien los datos pero archivos muy grandes dan error de memoria
                // Cambiar el sistema a Array de StringBuilders
                
		System.out.println("||==== " + small.getName() + " ==== " + small.length() + " Bytes " + " ====||");
		fileReader(small);
                
                // Tiempo muy corto, facil de leer y entiende todos los caracteres
                // su velocidad no es la mejor para archivos muy grandes
                // lee directamente los bytes, es decir, el tamaño entero del archivo, el resto de
                // Streams lee paquetes de bytes, es decir, menos cantidad pero tardan mas igualmente
                System.out.println();
		fileInputStream(small); // cuando se usa solo tarda 4s (no causa OutOfMemoryError)
                
                System.out.println();
		bufferedReader(small); // 38s
//                
//                // Muy bueno para leer archivos pero la codificacion utf16 le cuesta demasiado
                System.out.println();
		advancedBufferedReader(small); // 4s
//                
		System.out.println();
                inputStreamReader(small);
                
                // No lee bien utf-8, de hecho parece solo entender ASCII
		System.out.println();
                files(small);
                
                // El Scanner es literalmente una mierda, no usar bajo ninguna circustancia
//                System.out.println();
//		scanner(small);

		s.close();
	}

	public static void scanner(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
                StringBuilder string = new StringBuilder();
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNext()) {
                            final String line = scan.nextLine();
				chars += line.length();
                                string.append(line);
                        }
		} catch (Exception e) {
			e.printStackTrace();
		}
                System.out.println(builder.toString().equalsIgnoreCase(string.toString()) + ((showContent) ? " - " + string.toString() : ""));
		stamp(chars + "c -" + "scanner");
	}

	public static void files(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
                StringBuilder string = new StringBuilder();
		try {
			byte[] b = Files.readAllBytes(file.toPath());
                        for (byte bit : b) {
                            string.append((char)bit);
                        }
			chars = b.length;
		} catch (Exception e) {
			e.printStackTrace();
		}
                System.out.println(builder.toString().equalsIgnoreCase(string.toString()) + ((showContent) ? " - " + string.toString() : ""));
		stamp(chars + "c -" + "files");
	}

	public static void inputStreamReader(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
                StringBuilder string = new StringBuilder();
		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file))) {
			char[] c = new char[(int) file.length()];
			chars = reader.read(c, 0, (int) file.length());
                        string.append(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
                System.out.println(builder.toString().equalsIgnoreCase(string.toString()) + ((showContent) ? " - " + string.toString() : ""));
		stamp(chars + "c -" + "inputStreamReader");
	}

	public static void bufferedReader(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
                StringBuilder string = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while (reader.ready()) {
				string.append((char) reader.read());
				chars++;
			}
//			char[] c = new char[(int) file.length()];
//			chars = reader.read(c, 0, (int) file.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
                System.out.println(builder.toString().equalsIgnoreCase(string.toString()) + ((showContent) ? " - " + string.toString() : ""));
		stamp(chars + "c -" + "bufferedReader");
	}

	public static void advancedBufferedReader(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
                StringBuilder string = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			char[] c = new char[(int) file.length()];
			chars = reader.read(c, 0, (int) file.length());
                        string.append(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
                System.out.println(builder.toString().equalsIgnoreCase(string.toString()) + ((showContent) ? " - " + string.toString() : ""));
		stamp(chars + "c -" + "advancedBufferedReader");
	}

	public static void fileReader(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
                StringBuilder string = new StringBuilder();
		try (FileReader reader = new FileReader(file)) {
			while (reader.ready()) {
				string.append((char) reader.read());
				chars++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
                System.out.println(builder.toString().equalsIgnoreCase(string.toString()) + ((showContent) ? " - " + string.toString() : ""));
		stamp(chars + "c -" + "fileReader");
	}

	public static void fileInputStream(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
                StringBuilder string = new StringBuilder();
		try (FileInputStream reader = new FileInputStream(file)) {

			byte[] c = new byte[(int) file.length()];
			chars = reader.read(c); // Leer dado el numero de bytes
			string.append(new String(c));
		} catch (Exception e) {
			e.printStackTrace();
		}
                System.out.println(builder.toString().equalsIgnoreCase(string.toString()) + ((showContent) ? " - " + string.toString() : ""));
		stamp(chars + "c -" + "fileInputStream");
	}

	private static long lastStanp = 0L;

	public static void stamp(String callout) {
		final long now = System.nanoTime();
		final double result = now - lastStanp;
		String indent = ".";
		while (indent.length()+callout.length() < 36)
			indent+='.';
		System.out.println(callout +indent+ (result / 1000000) + "ms");
		lastStanp = System.nanoTime();
	}
}
