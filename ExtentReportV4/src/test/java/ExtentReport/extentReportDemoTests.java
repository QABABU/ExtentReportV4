package ExtentReport;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class extentReportDemoTests extends BaseClass {

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("http://www.google.com");
	}

	@Test
	public void verifyTitle() {

		testLogger = extent.createTest("verifyTitle", "This test verifies title");

		System.out.println(driver.getTitle());

		assertEquals("Google", driver.getTitle());

	}

	@Test
	public void skipTest() {

		testLogger = extent.createTest("skipTest", "This test will be skipped");

		throw new SkipException("This test will be skipped");

	}

	@Test
	public void failTest() {

		testLogger = extent.createTest("failTest", "This test will be failed");

		assertEquals("QA BABU", "QABABU");

	}

	@Test
	public void navigateToGmail() {

		testLogger = extent.createTest("navigateToGmail", "This test navigates to Gmail from HomePage");

		driver.findElement(By.xpath("//a[@class='gb_P' and contains(text(),'Gmail')]")).click();

		System.out.println(driver.getTitle());

		assertEquals("Gmail - Free Storage and Email from Google", driver.getTitle());
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {

			testLogger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case FAILED", ExtentColor.RED));
			
			String screenShotLocation = takeScreenshot(driver);			
			
			testLogger.fail("Test Case failed check screenshot below"+testLogger.addScreenCaptureFromPath(screenShotLocation));

		} else if (result.getStatus() == ITestResult.SKIP) {

			testLogger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case SKIPPED", ExtentColor.YELLOW));

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			testLogger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - Test Case PASSED", ExtentColor.GREEN));

		}

		driver.quit();

	}

}
