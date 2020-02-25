package Utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import Utils.Driver;

public class Driver {
        /*
        Singleton Design Pattern
        I want to instantiate my object only one time in all projects.
         */
        private static WebDriver driver;

        private Driver(){

        }
        public static WebDriver getDriver(String driverName){
            if(driver!=null){
                return driver;
            }
            switch (driverName){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
//            default:
//                WebDriverManager.chromedriver().setup();
//                driver=new ChromeDriver();
//                break;
            }return driver;
        }

    }


