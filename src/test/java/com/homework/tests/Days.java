package com.homework.tests;

import com.homework.tests.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Random;

public class Days {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");

    }
    @AfterMethod
    public void afterTest(){
        driver.quit();
    }

    //TODO 1. go to http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox
    //     2. Randomly select a checkbox. As soon as you check any day, print the name of the day
    // and uncheck immediately.
    // After you check and uncheck Friday for the third time, exit the program.
    // NOTE: Remember some checkboxes are not selectable. You need to find a way to ignore them when they are randomly selected.
    // It has to be dynamic. Do not hard code Saturday and Sunday. Use values of certain attributes.

    @Test
    public void days() throws InterruptedException {
        HashMap<Integer, String> check_boxes = new HashMap<>();
        check_boxes.put(0, "gwt-debug-cwCheckBox-Monday-input");
        check_boxes.put(1, "gwt-debug-cwCheckBox-Tuesday-input");
        check_boxes.put(2, "gwt-debug-cwCheckBox-Wednesday-input");
        check_boxes.put(3, "gwt-debug-cwCheckBox-Thursday-input");
        check_boxes.put(4, "gwt-debug-cwCheckBox-Friday-input");
        check_boxes.put(5, "gwt-debug-cwCheckBox-Saturday-input");
        check_boxes.put(6, "gwt-debug-cwCheckBox-Sunday-input");

        Random rd = new Random();
        WebElement checkB;
        int count = 0;
        Thread.sleep(2000);
        while (count != 3) {
            int i = rd.nextInt(7);
            checkB = driver.findElement(By.id(check_boxes.get(i)));
            if (!Boolean.parseBoolean(checkB.getAttribute("disabled"))) {
                Thread.sleep(2000);
                checkB.click();
                String[] days = checkB.getAttribute("outerHTML").split("-");
                System.out.println(days[3]);
                checkB.click();
            }
            if (i == 4){
                count++;
            }
        }
    }

}
