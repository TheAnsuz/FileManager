package me.amrv.testing;

import java.io.File;
import java.util.Scanner;
import me.amrv.filemanager.FileManager;
import me.amrv.filemanager.PlainFile;
import me.amrv.filemanager.SafeFileOperation;

public class FileOperations {

    private static final Scanner scan = new Scanner(System.in);

    @SuppressWarnings("deprecation")
    @SafeFileOperation
    public static void main(String[] args) {
        int lasts = 3;
//        System.out.println(">>>"+System.getProperty("user.dir"));
        try {
            PlainFile file = FileManager.construct("C:\\Users\\Adrian\\Saved Games\\Testing\\name.file").asPlainFile();
            if (Long.MAX_VALUE >= 16492674416640l)
                System.out.println("15TB permitidos (" + Long.MAX_VALUE + ")");
            System.out.println("Root: " + File.separator + " | Separator: " + File.pathSeparator);
            System.out.println("Nombre completo: " + file.getFullName());
            System.out.println("Nombre corto: " + file.getShortName());
            System.out.println("Extension: " + file.getExtension());
            System.out.println("Path: " + file.getPath());
            System.out.println("Abstract path: " + file.getAbstractPath());
            long t1 = System.nanoTime();
            System.out.println("Cannonicalized: " + file.getCannonicalPath());
            System.out.println("T: " + (System.nanoTime() - t1));
            long t2 = System.nanoTime();
            System.out.println(lasts + " lasts: " + file.getPathSection(lasts));
            System.out.println("T: " + (System.nanoTime() - t2));
            System.out.println("Parent: " + file.getParent());
            System.out.println("Whole Parent: " + file.getWholeParent());
            System.out.println("Last Parent: " + file.getLastParent());
            System.out.println("Free space: " + file.getFreeSpace());
            System.out.println("Usable space: " + file.getUsableSpace());
            System.out.println("Total space: " + file.getTotalSpace());
            System.out.println("Used space: " + file.size());
            System.out.println("Exists: " + file.exists());
            System.out.println("Can execute: " + file.canExecute());
            System.out.println("Can write: " + file.canWrite());
            System.out.println("Can read: " + file.canRead());
            System.out.println("Is absolute: " + file.isAbsolute());
            System.out.println("Is directory: " + file.isDirectory());
            System.out.println("Is file: " + file.isFile());
            System.out.println("Is hidden: " + file.isHidden());
            System.out.println("Is symbolicLink: " + file.isSynbolicLink());
            System.out.println("Last modified time: " + file.getLastModifiedTime() + " - " + file.getLastModifiedDate());
            System.out.println("Creation time: " + file.getCreationTime() + " - " + file.getCreationDate());
            System.out.println("Last access time: " + file.getLastAccessTime() + " - " + file.getLastAccessDate());
            System.out.println("Debe borrarse: " + file.isQueuedForDelete());
            file.setCreationTime(-922337203685477580l);
            file.setLastModifiedTime(0l);
            file.setLastAccessTime(922337203685477580l);
            file.queueForDelete(true);
//            file.rename("newname.newfile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
