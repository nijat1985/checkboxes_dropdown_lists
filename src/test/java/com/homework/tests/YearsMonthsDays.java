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

public class YearsMonthsDays {
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
    //     2. select a random year under Select your date of birth
    //     3. select month January
    //     4. verify that days dropdown has current number of days
    //     5. repeat steps 3, 4 for all the months
    //     NOTE: if you randomly select a leap year, verify February has 29 days

    @Test
    public void test() throws InterruptedException {

        //TODO select random year
        WebElement yearWE = driver.findElement(By.id("year"));
        Select select_year = new Select(yearWE);
        List<WebElement> options = select_year.getOptions(); //TODO getting all options
        int maxYear = Integer.parseInt(options.get(0).getText()); //TODO from options getting maxYear
        int minYear = Integer.parseInt(options.get(options.size()-1).getText()); //TODO from options getting minYear
        String randomYear = "" + StringUtility.randomNumberBetweenTwoNumbers(minYear, maxYear); //TODO getting random year between minimum and maximum year inside the list. For this I am using method
        select_year.selectByVisibleText(randomYear); //TODO selecting random year
        System.out.println("randomYear = " + randomYear);//TODO just for to see what happening
        Thread.sleep(2000);

        //TODO repeat steps 3, 4 for all the months
        for (int i = 0; i < 12; i++) {
            //TODO select month
            WebElement monthWE = driver.findElement(By.id("month"));
            Select select_month = new Select(monthWE);
            List<WebElement> optionsMonth = select_month.getOptions(); //TODO getting all month options
            String month = optionsMonth.get(i).getText(); //TODO getting different month each time
            System.out.println("month = " + month); //TODO just for to see what happening
            select_month.selectByVisibleText(month); //TODO selecting the month based on text
            Thread.sleep(2000);
            int expectedDays = StringUtility.numberOfDaysInMonth(month, Integer.parseInt(randomYear));

            //TODO find out days in day dropdown
            WebElement dayWE = driver.findElement(By.id("day"));
            Select select_day = new Select(dayWE);
            List<WebElement> optionsDay = select_day.getOptions();//TODO options
            int actualDays = Integer.parseInt(optionsDay.get(optionsDay.size() - 1).getText()); //TODO from options maxDay
            System.out.println("actualDays = " + actualDays);//TODO just for to see what happening
            Assert.assertEquals(expectedDays, actualDays);//TODO verifying the days
        }
    }





}
