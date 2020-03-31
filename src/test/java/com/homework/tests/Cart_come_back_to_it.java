package com.homework.tests;

import com.homework.tests.utility.StringUtility;
import com.homework.tests.utility.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cart_come_back_to_it {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://amazon.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @AfterMethod
    public void afterTest(){
        driver.quit();
    }

    //TODO 1. go to https://amazon.com
    //     2. search for "wooden spoon"
    //     3. click search
    //     4. remember the name and the price of a random result
    //     5. click on that random result
    //     6. verify default quantity of items is 1
    //     7. verify that the name and the price is the same as the one from step 5
    //     8. verify button "Add to Cart" is visible


    @Test
    public void test(){
        WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys("wooden spoon" + Keys.ENTER);

        List<WebElement> elements = driver.findElements(By.cssSelector("span[class='a-size-base-plus a-color-base a-text-normal']"));

        List<WebElement> price = driver.findElements(By.xpath("//a[@class='a-size-base a-link-normal s-no-hover a-text-normal']/span[1]/span[1]"));

        Random rd = new Random(0);
        int random_number = rd.nextInt(elements.size());
        String random_item = elements.get(random_number).getText();
        String price_of_random_item = price.get(random_number).getAttribute("innerHTML");

        elements.get(random_number).click();

        WebElement price_of_clicked_item = driver.findElement(By.xpath("//span[starts-with(@id,'priceblock_')]"));
        WebElement name_of_clicked_item = driver.findElement(By.id("productTitle"));
        WebElement add_to_cart = driver.findElement(By.id("add-to-cart-button"));
        WebElement location_of_quantity_of_clicked_item = driver.findElement(By.id("quantity"));
        Select quantity_dropdown = new Select(location_of_quantity_of_clicked_item);

        Assert.assertEquals(quantity_dropdown.getFirstSelectedOption().getText().trim(),"1");
        Assert.assertEquals(name_of_clicked_item.getText(),random_item);
        Assert.assertEquals(price_of_clicked_item.getText(), price_of_random_item);
        Assert.assertTrue(add_to_cart.isEnabled());
    }

}
