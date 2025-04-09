package com.swaglabs.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class AllureUtils {

    public static final String Allure_Result_Bath = "test-outputs/allure-results";

    private AllureUtils(){
        super();
    }
    public static void AttachLogsToAllureReport(){
        try {
             File logfile = FilesUtils.getLatestFile(LogsUtils.logsPath);
            assert logfile != null;
            if(!logfile.exists()){
                 LogsUtils.warn("Log file does not exist :" + LogsUtils.logsPath );
                 return;
             }
            Allure.addAttachment("Logs.log" , Files.readString(Path.of(logfile.getPath())));
        } catch (Exception e) {
            LogsUtils.error("Failed to attach log to Allure report :" + e.getMessage());
        }


    }
    public static void AttachScreenshotToAllureReport(String screenshotName , String screenshotPath){
        try {
            Allure.addAttachment(screenshotName , Files.newInputStream(Path.of(screenshotPath)));

        } catch (Exception e) {
            LogsUtils.error("Failed to attach screenshot to Allure report :" + e.getMessage());
        }

    }
    public static void generateAndServeReport(String allurePath,
                                              String resultsDir,
                                              String reportDir) throws IOException, InterruptedException {

        // 1. Validate paths
        if (!new java.io.File(allurePath).exists()) {
            throw new IOException("Allure not found at: " + allurePath);
        }

        // 2. Generate report
        Process generate = new ProcessBuilder(
                allurePath,
                "generate",
                resultsDir,
                "-o", reportDir,
                "--clean"
        )
                .inheritIO()
                .start();

        if (!generate.waitFor(60, TimeUnit.SECONDS)) {
            throw new RuntimeException("Report generation timed out");
        }

        // 3. Serve report
        Process serve = new ProcessBuilder(
                allurePath,
                "serve",
                resultsDir
        )
                .inheritIO()
                .start();

        // 4. Open browser
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            new ProcessBuilder("cmd", "/c", "start", "http://localhost:5252").start();
        } else {
            System.out.println("Report available at: http://localhost:5252");
        }
    }

}
