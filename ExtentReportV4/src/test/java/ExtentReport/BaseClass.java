package ExtentReport;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.utils.FileUtil;

public class BaseClass {	
	
	public static ExtentHtmlReporter htmlReporter;
	
	public static ExtentReports extent;
	
	public static ExtentTest testLogger;
	
	@BeforeClass
	public void Initialization() {
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\ExtentReport.html");
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		//config
		htmlReporter.config().setDocumentTitle("ExtentReports V4");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setReportName("Automation Results Report");
		
		//Add additional information to report
		extent.setSystemInfo("Application Name", "Googe Serach Engine");
		extent.setSystemInfo("Environment", "Staging");
		extent.setSystemInfo("OS", "Windows 7 64 bit");
		extent.setSystemInfo("Tester", "QA BABU");
	}
	
	@AfterClass
	public void WindUp() {
		
		extent.flush();
		
	}
	
	
	public String takeScreenshot(WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		//Taking screenshot
		File Screenshot = ts.getScreenshotAs(OutputType.FILE);
		//Creating the path to save the file
		String destinationPath = System.getProperty("user.dir")+"\\Screenshots\\img_name.png";		
		//creating destination file to save the screenshot
		File destinationFile = new File(destinationPath);
		//copying Screenshot to destination file
		FileUtils.copyFile(Screenshot, destinationFile);
		
		return destinationPath;
		
	}

}
