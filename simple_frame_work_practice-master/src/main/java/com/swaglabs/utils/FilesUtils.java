package com.swaglabs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class FilesUtils {
    private static final Logger log = LoggerFactory.getLogger(FilesUtils.class);

    private FilesUtils(){
        super();
    }

    public static File getLatestFile(String FolderPath){
        File folder = new File(FolderPath);
        File[] files  =folder.listFiles();
        if(files== null || files.length==0){
            LogsUtils.warn("No logs found in directory :" + FolderPath);
            return null;
        }
        File latestFile =files[0];
        for(File file:files){
            if(file.lastModified() > latestFile.lastModified())
                latestFile=file ;
        }
        return latestFile ;
    }
    public static void deleteFiles (File dirPath){
            if (dirPath==null || !dirPath.exists()){
                LogsUtils.warn("Failed to list files in :" + dirPath);
                return;
            }

            File[] filesList =dirPath.listFiles();

            if (filesList== null){
            LogsUtils.warn("Failed to list files in :" + dirPath);
            return;
            }

            for(File file:filesList){
                if (file.isDirectory()){
                    deleteFiles(file);
                }
                else{
                    try {
                        Files.delete(file.toPath());
                    }
                    catch (IOException e){
                        LogsUtils.error("Failed to delete file " +file );
                    }
                }
            }
    }
    public static void cleanDirectory(File dirPath) {
        if (dirPath == null || !dirPath.exists() || !dirPath.isDirectory()) {
            LogsUtils.warn("Invalid directory path or directory does not exist: " + dirPath);
            return;
        }

        File[] filesList = dirPath.listFiles();

        if (filesList == null) {
            LogsUtils.warn("Failed to list files in: " + dirPath);
            return;
        }

        for (File file : filesList) {
            if (file.isDirectory()) {
                deleteFiles(file); // Recursively delete files in subdirectories
                try {
                    Files.delete(file.toPath()); // Delete the empty subdirectory
                } catch (IOException e) {
                    LogsUtils.error("Failed to delete directory: " + file);
                }
            } else {
                // Skip files modified in the last 5 minutes
                if (isFileRecentlyModified(file)) {
                    LogsUtils.info("Skipping recently modified file: " + file);
                    continue;
                }

                try {
                    Files.delete(file.toPath()); // Delete the file
                } catch (IOException e) {
                    LogsUtils.error("Failed to delete file: " + file);
                }
            }
        }

        LogsUtils.info("Successfully cleaned directory: " + dirPath);
    }

    /**
     * Check if a file was modified within the last `minutes` minutes.
     *
     * @param file The file to check.
     * @return True if the file was modified within the last `minutes` minutes, otherwise false.
     */
    private static boolean isFileRecentlyModified(File file) {
        if (file == null || !file.exists()) {
            return false;
        }

        // Get the last modified time of the file
        long lastModifiedTime = file.lastModified();
        Instant lastModifiedInstant = Instant.ofEpochMilli(lastModifiedTime);

        // Get the current time
        Instant now = Instant.now();

        // Check if the file was modified within the last `minutes` minutes
        return lastModifiedInstant.isAfter(now.minus(5, ChronoUnit.MINUTES));
    }


}
