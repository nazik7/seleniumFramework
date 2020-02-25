import Pages.AmazonPages;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Project extends TestBase{
    @Test
    public void login() {
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

        page.estimateMonthlyBill.click();
        Double monthlyBill=Double.parseDouble(AmazonPages.getNumber(page.estimateMonthlyBill));
        softAssert.assertEquals(monthlyBill,sum);

        softAssert.assertAll();

        page.serviceTab.click();




        Select st=new Select(page.selectUsage.get(3));
        st.selectByVisibleText("Hours/Week");

        page.estimateMonthlyBill.click();
        page.saveAndShare.click();
        page.nameInput.sendKeys("EC2Test Configuration");
        page.includesInput.sendKeys("Includes 5 EC2 Instances");
        page.okButton.click();
        driver.get(page.saveUrl.getAttribute("href"));
        System.out.println("1 "+page.monthlyCostAws.getText());
        double AWSCalcResult = Double.parseDouble(page.monthlyCostAws.getText().substring(2));
        System.out.println("2 "+AWSCalcResult);
        double estimatedCostPrice = Double.parseDouble(page.estimateMonthlyBill.getText().substring(2));
        System.out.println("3 "+estimatedCostPrice);
        Assert.assertEquals(AWSCalcResult, estimatedCostPrice);

    }
}
