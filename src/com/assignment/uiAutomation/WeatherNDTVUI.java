package com.assignment.uiAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WeatherNDTVUI {
    @Test
    public void checkWeather() {
        System.setProperty("webdriver.chrome.driver", "C:\\work\\npmwork\\node_modules\\chromedriver\\lib\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://social.ndtv.com/static/Weather/report/?pfrom=home-topsubnavigation");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String cityName = "Ahmedabad";
        driver.findElement(By.id("searchBox")).sendKeys(cityName);
        driver.findElement(By.id(cityName)).click();
        driver.findElement(By.xpath("//div[text()='" + cityName + "']")).click();
        String[] values = new String[5];
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@style='margin-bottom:10px']")));
        values[0] = driver.findElement(By.xpath("//span[@style='margin-bottom:10px']")).getText();
        System.out.println(values[0]);
        values[1] = driver.findElement(By.xpath("//b[contains(text(),'Condition')]")).getText();
        values[2] = driver.findElement(By.xpath("//b[contains(text(),'Wind')]")).getText();
        values[3] = driver.findElement(By.xpath("//b[contains(text(),'Temp in Degrees')]")).getText();
        values[4] = driver.findElement(By.xpath("//b[contains(text(),'Temp in Fa')]")).getText();

        //Validate that selected city is giving the weather details or not
        for (int i = 1; i < values.length; i++) {
            String[] splitedValue = values[i].split(":");
            String formattedValue = splitedValue[1];
            boolean checkCondition = false;

            //System.out.println(formattedValue);
            if (formattedValue.isEmpty() == false) {
                checkCondition = true;
                Assert.assertTrue(checkCondition);
            }
            checkCondition = false;
        }

        driver.close();

    }
}
