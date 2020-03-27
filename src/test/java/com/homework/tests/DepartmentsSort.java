package com.homework.tests;

import com.homework.tests.utility.StringUtility;
import com.homework.tests.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class DepartmentsSort {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com");

    }
    @AfterMethod
    public void afterTest(){
        driver.quit();
    }


    //TODO 1. go to https://www.amazon.com
    //     2. verify that default value of the All departments dropdown is All
    //     3. verify that options in the All departments dropdown are not sorted alphabetically

    @Test
    public void test(){
        WebElement dropdown = driver.findElement(By.id("searchDropdownBox"));
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions(); //TODO getting all options
        System.out.println(options.get(0).getText());//TODO just for to see what happening
        Assert.assertEquals(options.get(0).getText(), "All Departments");//TODO getting firstOption and asserting

        List<String> expectedList = StringUtility.getElementsText(options);
        List<String> elementsText = StringUtility.getElementsText(options);
        Collections.sort(elementsText);



        for (int i = 0; i < expectedList.size(); i++) {
            Assert.assertFalse(expectedList.get(i).equals(elementsText.get(i))); //TODO if earlier assertion fails no need to check all of them. Already not sorted alphabetically
            break;
        }

    }

}
