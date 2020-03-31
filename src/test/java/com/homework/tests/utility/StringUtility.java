package com.homework.tests.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class StringUtility {
    /**
     * Method returns month number based on month
     */

    public static int monthNumber (String month){
        int monthNumber = 0;
       switch (month){
           case "January":
               monthNumber = 1;
               break;
           case "February":
               monthNumber = 2;
               break;
           case "March":
               monthNumber = 3;
               break;
           case "Aprel":
               monthNumber = 4;
               break;
           case "May":
               monthNumber = 5;
               break;
           case "June":
               monthNumber = 6;
               break;
           case "July":
               monthNumber = 7;
               break;
           case "August":
               monthNumber = 8;
               break;
           case "September":
               monthNumber = 9;
               break;
           case "October":
               monthNumber = 10;
               break;
           case "November":
               monthNumber = 11;
               break;
           case "December":
               monthNumber = 12;
               break;
       }

       return monthNumber;
    }


    /**
     * Method returns number of days based on month and year
     */

    public static int numberOfDaysInMonth (String month, int year){
        int numberOfDays = 0;
        switch (month){
            case "January": case "March": case "May": case "July": case "August": case "October": case "December":
                numberOfDays = 31;
                break;

            case "April": case "June": case "September": case "November":
                numberOfDays = 30;
                break;

            case "February":
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
                    numberOfDays = 29;
                }else {
                    numberOfDays = 28;
                }
        }

        return numberOfDays;
    }

    public static int randomNumberBetweenTwoNumbers(int minimum, int maximum){
        int num = 0;
        Random rd = new Random();
        while (num < minimum){
            num = rd.nextInt(maximum+1);
        }


        return num;
    }

    /**
     * takes a list of web elements
     * returns a trimmed list of Strings
     */

    public static List<String> getElementsText(List<WebElement> listEl){
        List<String> listSt = new ArrayList<>();
        for (WebElement element : listEl){
            listSt.add(element.getText().trim());
        }
        return listSt;
    }

}
