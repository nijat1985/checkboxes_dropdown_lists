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

public class MoreSpoons {
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
    //     3. remember all Brand names on the left
    //     4. select Prime checkbox on the left
    //     5. verify that same Brand names are still displayed

    @Test
    public void test() throws InterruptedException {
        WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys("wooden spoon" + Keys.ENTER);

        Thread.sleep(2000);
        List<WebElement> brandWE = driver.findElements(By.xpath("//div[@id='brandsRefinements']//li"));
        List<String> brandFirst = StringUtility.getElementsText(brandWE); //TODO getting brands before prime and converting String


        Thread.sleep(2000);
        WebElement prime = driver.findElement(By.xpath("//li[@aria-label='Prime Eligible']//label/i"));
        prime.click();//TODO clicking prime

        Thread.sleep(2000);
        List<WebElement> brandPrimeWE = driver.findElements(By.xpath("//div[@id='brandsRefinements']//li"));
        List<String> brandSecond = StringUtility.getElementsText(brandPrimeWE); //TODO getting brands after prime and converting String

        //TODO Assertion happening here
        for (int i = 0; i < brandFirst.size(); i++) {
            Assert.assertEquals(brandFirst.get(i), brandSecond.get(i));
        }

    }

}
