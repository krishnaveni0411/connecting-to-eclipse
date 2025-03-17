package reportgeneration;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;


@Listeners(TestListener.class)

public class TestListener implements ITestListener {
	private String reportFolderPath = "path_to_your_report_folder";  // Set the folder path for reports

    @Override
    public void onStart(ITestContext context) {
        // Optionally, any setup before the test suite starts
    }

    @Override
    public void onFinish(ITestContext context) {
        // After all tests are finished, you can backup and save the new report
        ReportUtils.backupOldReport(reportFolderPath);
        // Assuming the test report is generated here:
        try {
            String reportContent = "<html><body><h1>Test Results</h1></body></html>";  // Just a placeholder
            ReportUtils.saveNewReport(reportFolderPath, reportContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Optionally, any actions before a test starts
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Any actions on success, if needed
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Actions on failure, if needed
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Actions on skipped test, if needed
    }
}
