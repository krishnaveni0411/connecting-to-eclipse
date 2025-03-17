package reportgeneration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportUtils {
	
	// Backup old report with timestamp
    public static void backupOldReport(String reportFolderPath) {
        File reportFolder = new File(reportFolderPath);
        if (reportFolder.exists() && reportFolder.isDirectory()) {
            File[] files = reportFolder.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    if (file.getName().endsWith(".html")) { // assuming the report is in HTML format
                        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        File backupFile = new File(reportFolderPath + File.separator + file.getName().replace(".html", "_" + timestamp + ".html"));
                        file.renameTo(backupFile);  // Renaming the file with a timestamp
                    }
                }
            }
        }
    }

    // Save the new report with timestamp
    public static void saveNewReport(String reportFolderPath, String reportContent) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File newReport = new File(reportFolderPath + File.separator + "TestReport_" + timestamp + ".html");
        // Assuming the content is HTML or you can directly save the report content in the file
        // You can use FileWriter or any appropriate method to save the report content
    }


}
