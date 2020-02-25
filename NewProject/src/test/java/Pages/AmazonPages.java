package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import java.text.DecimalFormat;
import java.util.List;

public class AmazonPages {
    public AmazonPages(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@class='cell Instances rowsSection']//child::div[@class='gwt-PushButton small gwt-PushButton-up']")
    public WebElement addEC2Instance;

    @FindBy(xpath ="//tr[@class = 'EC2InstanceRow itemsTableDataRow table']//input[@class='gwt-TextBox input']")
    public List<WebElement> discriptionEC2Instance;

    @FindBy(xpath = "//div[@class='gwt-HTML field SF_EC2_INSTANCE_FIELD_TYPE instanceTypeField rowDialogSelectorFieldView']/..//following-sibling::td//img")
    public List<WebElement> typeButtonEC2Instance;

    @FindBy(xpath = "//div[.='t1.micro']/..//preceding-sibling::td//span/input")
    public WebElement T1microRadioBtn;

    @FindBy(xpath = "//button[.='Close and Save']")
    public WebElement saveAndClose;

    @FindBy(xpath = "//div[.='t2.micro']/..//preceding-sibling::td//span/input")
    public WebElement t2microRadioBtn;

    @FindBy(xpath = "//div[.='t2.medium']/..//preceding-sibling::td//span//input")
    public WebElement t2Medium;

    @FindBy(xpath ="//div[.='t2.large']/..//preceding-sibling::td//span//input")
    public WebElement t2Large;

    @FindBy(xpath ="//div[.='t2.xlarge']/..//preceding-sibling::td//span//input")
    public WebElement t2XLarge;

    @FindBy(xpath = "//div[@class='billLabel']")
    public WebElement estimateMonthlyBill;

    @FindBy(xpath = "//div[@class='gwt-HTML DynamicPrice DynamicPricePricing']")
    public List<WebElement> monthlyCosts;

    @FindBy(xpath = "//div[@class='restLabel']")
    public WebElement serviceTab;

    @FindBy(xpath = "//tr[@class='EC2InstanceRow itemsTableDataRow table']//select[@class='gwt-ListBox unit']")
    public List<WebElement> selectUsage;

    @FindBy(xpath = "//button[.='Save and Share']")
    public WebElement saveAndShare;

    @FindBy(xpath = "//input[@class='gwt-TextBox SC_SOLUTION_Input']")
    public WebElement nameInput;

    @FindBy(xpath = "//textArea[@class='gwt-TextArea SC_INCLUDES_Input']")
    public WebElement includesInput;

    @FindBy(xpath = "//button[.='OK']")
    public WebElement okButton;

    @FindBy(xpath = "//a[@id='saveURL']")
    public WebElement saveUrl;

    @FindBy(xpath = "//div[@class='gwt-HTML SC_Price']")
    public WebElement monthlyCostAws;

    @FindBy(xpath = "//div[.='Total Monthly Payment:']//parent::td//following-sibling::td//child::td//input")
    public WebElement totalMonthlyPayment;

    @FindBy(xpath = "//a[@href='http://aws.amazon.com/free']/../../..//following-sibling::td//child::td")
    public WebElement discount;


    public static String getNumber(WebElement element){
        String text=element.getText();
        String str1="";
        for(int i=0;i<text.length();i++){
            if(Character.isDigit(text.charAt(i)) || text.charAt(i)=='.'){
                str1+=""+text.charAt(i);
            }
        }
        //double number=Double.parseDouble(str1);
        return str1;
    }

    public static double getTotalMonthlyCost(List<WebElement> list){

        double sum=0.0;

        for(int i=0;i<list.size();i++){
         sum+=Double.parseDouble(list.get(i).getText().substring(2));
        }
        DecimalFormat df = new DecimalFormat("#.##");
        sum=sum-14.85;
        sum=Double.valueOf(df.format(sum));

        return sum;
    }

    public static double getPriceDouble(WebElement element){
        double price=Double.parseDouble(element.getText().substring(2));
        return price;
    }


}
