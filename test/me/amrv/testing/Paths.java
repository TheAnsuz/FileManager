/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.testing;

import me.amrv.filemanager.FilePath;

/**
 *
 * @author marruiad
 */
public class Paths {
    
	/*
	 * User directory:
	 *     windows: %disk%:\Users\%username%
	 */
	
	
    public static void main(String[] args) {
       
        System.out.println("ALL_USER_DATA_PATH "+FilePath.ALL_USER_DATA_PATH);
		System.out.println("COMMON_FILES_64 " + FilePath.COMMON_FILES_64_PATH);
        System.out.println("COMMON_FILES_86 "+FilePath.COMMON_FILES_86_PATH);
        System.out.println("DRIVERDATA_PATH "+FilePath.DRIVERDATA_PATH);
        System.out.println("LOCAL_PATH "+FilePath.LOCAL_PATH);
        System.out.println("LOCALLOW_PATH "+FilePath.LOCALLOW_PATH);
        System.out.println("PROGRAM_FILES_64 "+FilePath.PROGRAM_FILES_64_PATH);
        System.out.println("PROGRAM_FILES_86 "+FilePath.PROGRAM_FILES_86_PATH);
        System.out.println("PUBLIC_PATH "+FilePath.PUBLIC_PATH);
        System.out.println("ROAMING_PATH "+FilePath.ROAMING_PATH);
        System.out.println("SYSTEM_DRIVE "+FilePath.SYSTEM_DRIVE_PATH);
        System.out.println("TEMP_PATH "+FilePath.TEMP_PATH);
        System.out.println("USER_PATH "+FilePath.USER_PATH);
        
//        System.out.println("\n\n\n");
//        System.getProperties().forEach((key,value)-> {
//        	System.out.println(key + " = " + value);
//        });
//        
//        System.out.println("\nEnvironment variables");
//    
//        System.getenv().forEach((key,value)->{
//        	System.out.println(key + " = " + value);
//        });
    }
    
    
}
