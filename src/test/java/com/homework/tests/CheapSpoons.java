package com.homework.tests;

import com.homework.tests.utility.StringUtility;
import com.homework.tests.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CheapSpoons {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://amazon.com");

    }
    @AfterMethod
    public void afterTest(){
        driver.quit();
    }

    //TODO 1. go to https://amazon.com
    //     2. search for "wooden spoon"
    //     3. click on Price option Under $25 on the left
    //     4. verify that all results are cheaper than $25

    @Test
    public void test() throws InterruptedException {
        WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys("wooden spoon" + Keys.ENTER);

        Thread.sleep(2000);
        WebElement under25 = driver.findElement(By.linkText("Under $25"));
        under25.click();

        Thread.sleep(2000);
        List<WebElement> priceWE = driver.findElements(By.xpath("//span[@class='a-price' and @data-a-size ='l']"));
        List<String> priceString = StringUtility.getElementsText(priceWE);//TODO converting to String

        for (String price : priceString){
            Assert.assertTrue(Double.parseDouble(price.replace("$","").replace("\n",".")) < 25.0);//TODO getting rid of $ sign and asserting
        }


    }

}
