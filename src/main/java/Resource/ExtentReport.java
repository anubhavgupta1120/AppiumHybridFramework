package Resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	private static ExtentReports report;

	public static ExtentReports configExtentReport(String TesterName, String Title) {
		final String Path = System.getProperty("user.dir")+"/Reports/TestReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(Path);
		reporter.config().setDocumentTitle("Test Case Report");
		reporter.config().setReportName(Title);
		reporter.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", TesterName);
		return report;

	}

}
