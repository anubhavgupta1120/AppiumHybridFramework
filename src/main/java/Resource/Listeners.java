package Resource;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners implements ITestListener {
	ExtentReports report = ExtentReport.configExtentReport("Anubhav Gupta", "APPs Testing Report");
	ExtentTest Test;
	ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();
	AppiumDriver driver;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		Test = report.createTest(result.getMethod().getMethodName());
		threadLocal.set(Test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		threadLocal.get().log(Status.PASS, "Test Case Paased");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		threadLocal.get().fail(result.getThrowable());
		String Path = "";
		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			Path = Appium_Reusables.getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		threadLocal.get().addScreenCaptureFromPath(Path, result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		threadLocal.get().skip(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();
	}

}
