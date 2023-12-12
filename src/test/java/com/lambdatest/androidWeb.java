package com.lambdatest;

import io.appium.java_client.MobileBy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class androidWeb {

    String username = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" //Enter the Username here
            : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY"  //Enter the Access key here
            : System.getenv("LT_ACCESS_KEY");
    public static RemoteWebDriver driver = null;
    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";
    public String status = "passed";
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("build", "HYP Native Web automation-"+ System.getenv("JOB_ID"));
        capabilities.setCapability("name", "Java JUnit Android Web Test");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "Galaxy. *,OnePlus. *,Pixel. *"); //Enter the name of the device here
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("dedicatedProxy", true);
        capabilities.setCapability("region", "eu");
        // capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("console",true);
        capabilities.setCapability("network",true);
        capabilities.setCapability("visual",true);
        // capabilities.setCapability("geoLocation", "IT");
        // capabilities.setCapability("udid", "880927d9");
        try
        {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);
        }
        catch (MalformedURLException e)
        {
            System.out.println("Invalid grid URL");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSimple() throws Exception
    {
        try
        {
            // driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
            // driver.get("https://mfml.in/api/getInfo");
            // driver.getWindowHandles().forEach(handle -> System.out.println(handle));
            // WebDriverWait wait = new WebDriverWait(driver, 30);
            // wait.until(ExpectedConditions.elementToBeClickable(By.id("resolution"))).click();

            // wait.until(ExpectedConditions.elementToBeClickable(By.id("location"))).click();
            // wait.until(ExpectedConditions.elementToBeClickable(By.id("details"))).click();
            // wait.until(ExpectedConditions.elementToBeClickable(By.id("timezone"))).click();
            driver.get("https://ifconfig.io/");
            Thread.sleep(10000);
            status="passed";
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            status="failed";
        }
    }
    @After
    public void tearDown() throws Exception
    {
        if (driver != null)
        {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }

}
