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

import java.time.LocalDate;

public class TodaysDate {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/dropdown");

    }
    @AfterMethod
    public void afterTest(){
        driver.quit();
    }

    //TODO 1. go to http://practice.cybertekschool.com/dropdown
    //     2. verify that dropdowns under Select your date of birth display current year, month, day

    @Test
    public void test(){
        WebElement yearWE = driver.findElement(By.id("year"));
        Select select = new Select(yearWE);
        int yearInt = Integer.parseInt(select.getFirstSelectedOption().getText());
        int currentYear = LocalDate.now().getYear();
        System.out.println("yearInt = " + yearInt); // TODO just for to see how it is look like
        System.out.println("currentYear = " + currentYear); // TODO just for to see how it is look like
        Assert.assertEquals(yearInt, currentYear);


        WebElement monthWE = driver.findElement(By.id("month"));
        select = new Select(monthWE);
        int monthInt = StringUtility.monthNumber(select.getFirstSelectedOption().getText());
        int currentMonth = LocalDate.now().getMonth().getValue();
        System.out.println("monthInt = " + monthInt); // TODO just for to see how it is look like
        System.out.println("currentMonth = " + currentMonth); // TODO just for to see how it is look like
        Assert.assertEquals(monthInt, currentMonth);

        WebElement dayWE = driver.findElement(By.id("day"));
        select = new Select(dayWE);
        int dayInt = Integer.parseInt(select.getFirstSelectedOption().getText());
        int currentDay = LocalDate.now().getDayOfMonth();
        System.out.println("dayStr = " + dayInt); // TODO just for to see how it is look like
        System.out.println("cuurentDay = " + currentDay); // TODO just for to see how it is look like
        Assert.assertEquals(dayInt, currentDay);
    }

}
