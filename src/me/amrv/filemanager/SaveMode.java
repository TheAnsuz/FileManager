/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.filemanager;

/**
 *
 * @author marruiad
 */
public enum SaveMode {

    /**
     * Uses a method to save files that most of the times results on a less
     * efficient format, however this might be useful for older devices that
     * dont have enough buffer or for small processing.
     */
    LEGACY_OVERWRITE,
    /**
     * Uses a method to save files that most of the times result on the use of
     * buffers, however some file formats also change how the process is
     * executed, most of the times this is the best option to save files.
     */
    OVERWRITE,
    /**
     * Uses the same save method as {@code LEGACY_OVERWRITE} but instad of
     * aplying the save to the whole file, it will add all the stored contents
     * on the virtual machine to the file, resulting on an adition to the end of
     * the file and most of the times this wont be needed.
     */
    LEGACY_APPEND,
    /**
     * Uses the same save method as {@code OVERWRITE} but instad of
     * aplying the save to the whole file, it will add all the stored contents
     * on the virtual machine to the file, resulting on an adition to the end of
     * the file and most of the times this wont be needed.
     */
    APPEND

}
