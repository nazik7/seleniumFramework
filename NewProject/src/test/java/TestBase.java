import Utils.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class TestBase {
    static WebDriver driver;
    public static SoftAssert softAssert;

    public static ExtentReports extentReports;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentTest extentTest;

    @Parameters("DriverName")
    @BeforeTest(alwaysRun = true)
    public void setUpDriver(String DriverName){
        driver = Driver.getDriver(DriverName);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        softAssert=new SoftAssert();

        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\myReport.html");
        htmlReporter.config().setDocumentTitle("OpenMRS Automation Report");
        htmlReporter.config().setReportName("Registration Functionality Validation");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports=new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("OS","Windows 10 OS");
        extentReports.setSystemInfo("Browser","Chrome");
        extentReports.setSystemInfo("Environment","Tets Environment");
        extentReports.setSystemInfo("Domain","com.amazonaws.s3.calculator");

    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            extentReports.flush();
            //  driver.close();
        }
    }
    /*
    Modify mouse action class with TestBase to launch your driver from one location
     */
}
