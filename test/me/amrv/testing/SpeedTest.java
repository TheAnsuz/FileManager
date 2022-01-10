package me.amrv.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Scanner;

import me.amrv.filemanager.FileManager;

public class SpeedTest {

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

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		File small = FileManager.construct(Paths.TEST + "\\smallTest.txt").asPlainFile().getFile();
		File medium = FileManager.construct(Paths.TEST + "\\mediumTest.txt").asPlainFile().getFile();
		File large = FileManager.construct(Paths.TEST + "\\largeTest.txt").asPlainFile().getFile();
		File insane = FileManager.construct(Paths.TEST + "\\insaneTest.txt").asPlainFile().getFile();
		File giga = FileManager.construct(Paths.TEST + "\\gigaTest.txt").asPlainFile().getFile();
		File mega = FileManager.construct(Paths.TEST + "\\megaTest.txt").asPlainFile().getFile();
		File small_lined = FileManager.construct(Paths.TEST + "\\smallTest_lined.txt").asPlainFile().getFile();
		File medium_lined = FileManager.construct(Paths.TEST + "\\mediumTest_lined.txt").asPlainFile().getFile();
		File large_lined = FileManager.construct(Paths.TEST + "\\largeTest_lined.txt").asPlainFile().getFile();
		File insane_lined = FileManager.construct(Paths.TEST + "\\insaneTest_lined.txt").asPlainFile().getFile();
		File giga_lined = FileManager.construct(Paths.TEST + "\\gigaTest_lined.txt").asPlainFile().getFile();
		File mega_lined = FileManager.construct(Paths.TEST + "\\megaTest_lined.txt").asPlainFile().getFile();

		System.out.println("||==== " + small.getName() + " ==== " + small.length() + " Bytes " + " ====||");
		fileReader(small);
		fileInputStream(small);
		bufferedReader(small);
		advancedBufferedReader(small);
		inputStreamReader(small);
		files(small);
		scanner(small);

		s.nextLine();
		System.out.println();
		System.out.println("||==== " + medium.getName() + " ==== " + medium.length() + " Bytes " + " ====||");
		fileReader(medium);
		fileInputStream(medium);
		bufferedReader(medium);
		advancedBufferedReader(medium);
		inputStreamReader(medium);
		files(medium);
		scanner(medium);
		
		s.nextLine();
		System.out.println();
		System.out.println("||==== " + large.getName() + " ==== " + large.length() + " Bytes " + " ====||");
		fileReader(large);
		fileInputStream(large);
		bufferedReader(large);
		advancedBufferedReader(large);
		inputStreamReader(large);
		files(large);
		scanner(large);
		
		s.nextLine();
		System.out.println();
		System.out.println("||==== " + insane.getName() + " ==== " + insane.length() + " Bytes " + " ====||");
		fileReader(insane);
		fileInputStream(insane);
		bufferedReader(insane);
		advancedBufferedReader(insane);
		inputStreamReader(insane);
		files(insane);
		scanner(insane);
		
		s.nextLine();
		System.out.println();
		System.out.println("||==== " + giga.getName() + " ==== " + giga.length() + " Bytes " + " ====||");
		fileReader(giga);
		fileInputStream(giga);
		bufferedReader(giga);
		advancedBufferedReader(giga);
		inputStreamReader(giga);
		files(giga);
		scanner(giga);
		
		s.nextLine();
		System.out.println();
		System.out.println("||==== " + mega.getName() + " ==== " + mega.length() + " Bytes " + " ====||");
		fileReader(mega);
		fileInputStream(mega);
		bufferedReader(mega);
		advancedBufferedReader(mega);
		inputStreamReader(mega);
		files(mega);
		scanner(mega);
		
		s.nextLine();
		System.out.println("||==== " + small_lined.getName() + " ==== " + small_lined.length() + " Bytes " + " ====||");
		fileReader(small_lined);
		fileInputStream(small_lined);
		bufferedReader(small_lined);
		advancedBufferedReader(small_lined);
		inputStreamReader(small_lined);
		files(small_lined);
		scanner(small_lined);

		s.nextLine();
		System.out.println();
		System.out.println("||==== " + medium_lined.getName() + " ==== " + medium_lined.length() + " Bytes " + " ====||");
		fileReader(medium_lined);
		fileInputStream(medium_lined);
		bufferedReader(medium_lined);
		advancedBufferedReader(medium_lined);
		inputStreamReader(medium_lined);
		files(medium_lined);
		scanner(medium_lined);
		
		s.nextLine();
		System.out.println();
		System.out.println("||==== " + large_lined.getName() + " ==== " + large_lined.length() + " Bytes " + " ====||");
		fileReader(large_lined);
		fileInputStream(large_lined);
		bufferedReader(large_lined);
		advancedBufferedReader(large_lined);
		inputStreamReader(large_lined);
		files(large_lined);
		scanner(large_lined);
		
		s.nextLine();
		System.out.println();
		System.out.println("||==== " + insane_lined.getName() + " ==== " + insane_lined.length() + " Bytes " + " ====||");
		fileReader(insane_lined);
		fileInputStream(insane_lined);
		bufferedReader(insane_lined);
		advancedBufferedReader(insane_lined);
		inputStreamReader(insane_lined);
		files(insane_lined);
		scanner(insane_lined);
		
		s.nextLine();
		System.out.println();
		System.out.println("||==== " + giga_lined.getName() + " ==== " + giga_lined.length() + " Bytes " + " ====||");
		fileReader(giga_lined);
		fileInputStream(giga_lined);
		bufferedReader(giga_lined);
		advancedBufferedReader(giga_lined);
		inputStreamReader(giga_lined);
		files(giga_lined);
		scanner(giga_lined);
		
		s.nextLine();
		System.out.println();
		System.out.println("||==== " + mega_lined.getName() + " ==== " + mega_lined.length() + " Bytes " + " ====||");
		fileReader(mega_lined);
		fileInputStream(mega_lined);
		bufferedReader(mega_lined);
		advancedBufferedReader(mega_lined);
		inputStreamReader(mega_lined);
		files(mega_lined);
		scanner(mega_lined);

		s.close();
	}

	public static void scanner(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNext())
				chars += scan.nextLine().length();

		} catch (Exception e) {
			e.printStackTrace();
		}
		stamp(chars + "c -" + "scanner");
	}

	public static void files(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
		try {
			byte[] b = Files.readAllBytes(file.toPath());
			chars = b.length;
		} catch (Exception e) {
			e.printStackTrace();
		}
		stamp(chars + "c -" + "files");
	}

	public static void inputStreamReader(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file))) {
			char[] c = new char[(int) file.length()];
			chars = reader.read(c, 0, (int) file.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		stamp(chars + "c -" + "inputStreamReader");
	}

	public static void bufferedReader(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while (reader.ready()) {
				reader.read();
				chars++;
			}
//			char[] c = new char[(int) file.length()];
//			chars = reader.read(c, 0, (int) file.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		stamp(chars + "c -" + "bufferedReader");
	}

	public static void advancedBufferedReader(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			char[] c = new char[(int) file.length()];
			chars = reader.read(c, 0, (int) file.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		stamp(chars + "c -" + "advancedBufferedReader");
	}

	public static void fileReader(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
		try (FileReader reader = new FileReader(file)) {
			while (reader.ready()) {
				reader.read();
				chars++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		stamp(chars + "c -" + "fileReader");
	}

	public static void fileInputStream(File file) {
		lastStanp = System.nanoTime();
		long chars = 0;
		try (FileInputStream reader = new FileInputStream(file)) {

			byte[] c = new byte[(int) file.length()];
			chars = reader.read(c); // Leer dado el numero de bytes
//			for (byte ch : c) {
//				char a = (char) ch;
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
