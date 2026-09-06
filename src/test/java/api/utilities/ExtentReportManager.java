package api.utilities;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext context)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS").format(new Date());
		repName = "Test-Report-"+timeStamp+" "+context.getClass()+ ".html";
		
		Path path=Paths.get(System.getProperty("user.dir"), "reports",repName);
		
		sparkReporter=new ExtentSparkReporter(path.toString());
		
		sparkReporter.config().setDocumentTitle("Rest Assured Automation Project");
		sparkReporter.config().setReportName("Pet Stores API");
		sparkReporter.config().setTheme(Theme.DARK);
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

		sparkReporter.config().setDocumentTitle("RestAssured Automation Project");
		sparkReporter.config().setReportName("Pet Store Users API");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getName());

		test.log(Status.PASS, "Test Passed");
		test.assignCategory(result.getMethod().getGroups());
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getName());

		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable());

		// Attach Screenshot (if available)
		String screenshotPath = ".\\Screenshots\\" + result.getName() + ".png";

		File file = new File(screenshotPath);

		if (file.exists()) {
			try {
				test.addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getName());

		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();

		System.out.println("Extent Report Generated Successfully...");
	}
}

