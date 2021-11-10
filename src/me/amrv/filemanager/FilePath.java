package me.amrv.filemanager;

import java.io.File;

public class FilePath {

    /**
     * Returns the path of the user folder, something like
     * {@code C:\Users\Username\}
     */
    public static final String USER_PATH = System.getProperty("user.home") + File.separatorChar;

    /**
     * Returns the path of the temporal files folder, ending with the system
     * dependant folder separator, in windows this will return something like
     * {@code C:\Users\Username\AppData\Local\Temp\}
     */
    public static final String TEMP_PATH = System.getProperty("java.io.tmpdir");

    /**
     * Returns the path of the system drive folder
     * {@code C:\Windows\System32\Drivers\DriverData\}
     * <p>
     * This has only been tested to work on windows and on other OS this wont
     * work in the current version
     */
    public static final String SYSTEM_DRIVE_PATH = System.getenv("SYSTEMDRIVE") + File.separatorChar;

    public static final String ROAMING_PATH = System.getProperty("user.home") + File.separatorChar + "AppData"
            + File.separatorChar + "Roaming" + File.separatorChar;
    public static final String LOCAL_PATH = System.getProperty("user.home") + File.separatorChar + "AppData"
            + File.separatorChar + "Local" + File.separatorChar;
    public static final String LOCALLOW_PATH = System.getProperty("user.home") + File.separatorChar + "AppData"
            + File.separatorChar + "LocalLow" + File.separatorChar;
    public static final String PROGRAM_FILES_86_PATH = System.getenv("PROGRAMFILES(X86)") + File.separatorChar;
    public static final String PROGRAM_FILES_64_PATH = System.getenv("PROGRAMFILES") + File.separatorChar;
    public static final String ALL_USER_DATA_PATH = System.getenv("ALLUSERSPROFILE") + File.separatorChar;
    public static final String COMMON_FILES_86_PATH = System.getenv("COMMONPROGRAMFILES(X86)") + File.separatorChar;
    public static final String COMMON_FILES_64_PATH = System.getenv("COMMONPROGRAMFILES") + File.separatorChar;
    public static final String PUBLIC_PATH = System.getenv("PUBLIC") + File.separatorChar;
    public static final String DRIVERDATA_PATH = System.getenv("DRIVERDATA") + File.separatorChar;

//        ALL_USER_DATA_PATH C:\ProgramData\
//COMMON_FILES_64 C:\Program Files\Common Files\
//COMMON_FILES_86 C:\Program Files (x86)\Common Files\
//DRIVERDATA_PATH C:\Windows\System32\Drivers\DriverData\
//LOCAL_PATH C:\Users\marruiad\AppData\Local\
//LOCALLOW_PATH C:\Users\marruiad\AppData\LocalLow\
//PROGRAM_FILES_64 C:\Program Files\
//PROGRAM_FILES_86 C:\Program Files (x86)\
//PUBLIC_PATH C:\Users\Public\
//ROAMING_PATH C:\Users\marruiad\AppData\Roaming\
//SYSTEM_DRIVE C:\
//TEMP_PATH C:\Users\marruiad\AppData\Local\Temp\
//USER_PATH C:\Users\marruiad\
//	Not needed as at the time you create a file it will ignore multiple separators at once so no problem putting hem 
//
//	public static final String TEMP_DIRECTORY = System.getProperty("java.io.tmpdir").substring(0,
//			System.getProperty("java.io.tmpdir").length() - 1);
//	public static final String SYSTEM_DRIVE_DIRECTORY = System.getenv("SYSTEMDRIVE");
//	public static final String ROAMING_DIRECTORY = System.getProperty("user.home") + File.separatorChar + "AppData"
//			+ File.separatorChar + "Roaming";
//	public static final String LOCAL_DIRECTORY = System.getProperty("user.home") + File.separatorChar + "AppData"
//			+ File.separatorChar + "Local";
//	public static final String LOCALLOW_DIRECTORY = System.getProperty("user.home") + File.separatorChar + "AppData"
//			+ File.separatorChar + "LocalLow";
//	public static final String PROGRAM_FILES_86_DIRECTORY = System.getenv("PROGRAMFILES(X86)");
//	public static final String PROGRAM_FILES_64_DIRECTORY = System.getenv("PROGRAMFILES");
//	public static final String ALL_USER_DATA_DIRECTORY = System.getenv("ALLUSERSPROFILE");
//	public static final String COMMON_FILES_86_DIRECTORY = System.getenv("COMMONPROGRAMFILES(X86)");
//	public static final String COMMON_FILES_64_DIRECTORY = System.getenv("COMMONPROGRAMFILES");
//	public static final String PUBLIC_DIRECTORY = System.getenv("PUBLIC");
//	public static final String DRIVERDATA_DIRECTORY = System.getenv("DRIVERDATA");
}
