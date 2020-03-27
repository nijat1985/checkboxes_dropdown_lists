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

import java.util.List;

public class MainDepartments {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/gp/site-directory");

    }
    @AfterMethod
    public void afterTest(){
        driver.quit();
    }

    //TODO 1. go to https://www.amazon.com/gp/site-directory
    //     2. verify that every main department name (indicated by blue rectangles in the picture) is
    //        present in the All departments dropdown (indicated by green rectangle in the picture)

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(2000);
        List<String> departmentNames = StringUtility.getElementsText(driver.findElements(By.className("fsdDeptTitle")));//TODO getting all the main departments name and converting to string


        WebElement dropdown = driver.findElement(By.id("searchDropdownBox"));
        Select select = new Select(dropdown);
        List<String> departmentNamesDropdown = StringUtility.getElementsText(select.getOptions()); //TODO getting all options and converting to string

        int expected_match = departmentNames.size();
        int actual_match = 0;
        for (int i = 0; i < departmentNames.size(); i++) {
            for (int j = 0; j < departmentNamesDropdown.size(); j++) {
                if (departmentNames.get(i).trim().contains(departmentNamesDropdown.get(j).trim())){
                    actual_match++;
                }
            }
        }
        Assert.assertEquals(actual_match,expected_match);

    }

}
