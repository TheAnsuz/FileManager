package me.amrv.testing;

public class SpeedTest {

	/*
	 * Test para la velocidad de escritura y lectura con los diferentes metodos
	 * se aplicara por defecto el mas rapido y se descartaran los demas o se
	 * dejaran como candidatos a la hora de guardar.
	 * 
	 * Tras esto hay que crear una forma de guardar y obtener objetos directamente
	 * del archivo
	 * 
	 * FileReader - cadenas de caracteres (encoding & buffer predefinidos)
	 * FileInputStream - cadenas de caracteres (encoding & buffer deben ser espificados)
	 * BufferedReader - cadenas de caracteres (buffer por defecto o especificado) [eficiente en archivos muy grandes] Buffer-8kb
	 * InputStreamReader - obtiene bytes y los convierte en caracteres, sustituir por BufferedReader
	 * Files - cadenas de caracteres o bytes (no usa buffer)
	 * Scanner - cualquier tipo de dato crudo
	 * 
	 */
	
	public static void main(String[] args) {
		
	}
	
	
	private static void getBufferSize(BaseFile file) {
		
	}
}
