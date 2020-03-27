package com.homework.tests;

import com.homework.tests.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Links {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/");

    }
    @AfterMethod
    public void afterTest(){
        driver.quit();
    }

    //TODO 1. go to https://www.w3schools.com/
    //     2. find all the elements in the page with the tag a
    //     3. for each of those elements, if it is displayed on the page, print the text and the href of that
    //        element.

    @Test
    public void test(){
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        int count = 1;
        for (WebElement link : allLinks){
            if (link.getText().length() > 0) {
                System.out.println(count + " -  text: -->> " + link.getText().trim() + "  |  href -->> " + link.getAttribute("href"));
                count++;
            }
        }
    }


}
