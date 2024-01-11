package BaseTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import PageObject_Android.FormPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AndroidTest {
	
	public AppiumDriver driver;
	private AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass
	public void StartServer() {
		// Set the ANDROID_HOME Path
		HashMap<String, String> env = new HashMap<>();
		env.put("ANDROID_HOME", "/Users/anubhavgupta/Library/Android/sdk");

		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.usingDriverExecutable(new File("/usr/local/bin/node")).withEnvironment(env).usingPort(4723)
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE);
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	@BeforeMethod
	public void initiateDriver() throws MalformedURLException {

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Anubhav Small Phone");
		options.setChromedriverExecutable("/Users/anubhavgupta/Documents/Drivers/chromedriver_103");
		options.setApp(
				"/Users/anubhavgupta/Documents/eclipse-workspace(Java)/AppTestingFramework/src/main/java/APPS/General-Store.apk");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@AfterClass
	public void stopServer() {
		service.stop();
	}

}
