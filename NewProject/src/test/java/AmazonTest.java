import Pages.AmazonPages;
import Utils.BrowserUtils;
import com.aventstack.extentreports.Status;



import org.openqa.selenium.support.ui.Select;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AmazonTest extends TestBase {


    @Test
    public void login() {

        extentTest=extentReports.createTest("This is Price Validation tests");
        driver.get("http://calculator.s3.amazonaws.com/index.html");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        AmazonPages page = new AmazonPages(driver);
        page.addEC2Instance.click();
        page.typeButtonEC2Instance.get(0).click();
        page.T1microRadioBtn.click();
        page.saveAndClose.click();
        page.discriptionEC2Instance.get(0).sendKeys("T1-LinuxMicro(free)");
        page.addEC2Instance.click();
        page.typeButtonEC2Instance.get(1).click();
        page.t2microRadioBtn.click();
        page.saveAndClose.click();
        page.discriptionEC2Instance.get(1).sendKeys("T2 Linux Micro 2");
        page.addEC2Instance.click();
        page.typeButtonEC2Instance.get(2).click();
        page.t2Medium.click();
        page.saveAndClose.click();
        page.discriptionEC2Instance.get(2).sendKeys("T2 Linux Medium");
        page.addEC2Instance.click();
        page.typeButtonEC2Instance.get(3).click();
        page.t2Large.click();
        page.saveAndClose.click();
        page.discriptionEC2Instance.get(3).sendKeys("T2 Linux Large");
        page.addEC2Instance.click();
        page.typeButtonEC2Instance.get(4).click();
        page.t2XLarge.click();
        page.saveAndClose.click();
        page.discriptionEC2Instance.get(4).sendKeys("T2 Linux XtraLarge");

        double sum=AmazonPages.getTotalMonthlyCost(page.monthlyCosts);

        double linuxLargePriceUtilized=AmazonPages.getPriceDouble(page.monthlyCosts.get(3));


        page.estimateMonthlyBill.click();

       Double monthlyBill=Double.parseDouble(AmazonPages.getNumber(page.estimateMonthlyBill));

        extentTest.log(Status.INFO,"Monthly price is processing");
       softAssert.assertEquals(monthlyBill,sum);
        extentTest.log(Status.INFO,"Monthly price is validated");


        page.serviceTab.click();

//        for (WebElement select : page.selectUsage) {
//            Select st = new Select(select);
//            st.selectByVisibleText("Hours/Week");
//        }

        Select st=new Select(page.selectUsage.get(3));
        st.selectByVisibleText("Hours/Week");
        BrowserUtils.waitSec(3);
      double linuxLargePriceHours=  AmazonPages.getPriceDouble(page.monthlyCosts.get(3));

        extentTest.log(Status.INFO,"T2 Linux Large Usage is changed from Utilized/Month to Hours/Week");


        softAssert.assertFalse(linuxLargePriceUtilized==linuxLargePriceHours);
    //  softAssert.assertEquals(linuxLargePriceUtilized,linuxLargePriceHours);

        extentTest.log(Status.INFO,"Price is not same validated");

        page.estimateMonthlyBill.click();
        page.saveAndShare.click();
        page.nameInput.sendKeys("EC2Test Configuration");
        page.includesInput.sendKeys("Includes 5 EC2 Instances");
        page.okButton.click();
        driver.get(page.saveUrl.getAttribute("href"));

        double AWSCalcResult = AmazonPages.getPriceDouble(page.monthlyCostAws);
       // System.out.println("AWSCalcResult "+AWSCalcResult);

        double estimatedCostPrice = Double.parseDouble(AmazonPages.getNumber(page.estimateMonthlyBill));
       // System.out.println("estimatedCostPrice "+estimatedCostPrice);


        extentTest.log(Status.INFO,"Aws calculator Monthly price is processing");
        softAssert.assertEquals(AWSCalcResult, estimatedCostPrice);
        extentTest.log(Status.INFO," Aws calculator Monthly price is Validated");

        softAssert.assertAll();
    }
//    @AfterMethod
//    public void takeScreenShot(ITestResult result) throws IOException {
//        if(result.getStatus()==ITestResult.FAILURE){
//            BrowserUtils.takeScreenShot(driver);
//        }
//
//    }

}
