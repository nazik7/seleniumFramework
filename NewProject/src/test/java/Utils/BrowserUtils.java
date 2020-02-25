package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class BrowserUtils {

    public static void waitForVisibility(WebDriver driver, WebElement element, int timeUnit){
        WebDriverWait wait =new WebDriverWait(driver,timeUnit);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void takeScreenShot(WebDriver driver) throws IOException {
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File desFile=new File("ScreenshotFile\\"+System.currentTimeMillis()+""+".png");
        FileUtils.copyFile(src,desFile);
    }

    public static void waitSec(int time){
        time=time*1000;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
