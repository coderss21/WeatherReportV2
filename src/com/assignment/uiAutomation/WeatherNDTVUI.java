package com.assignment.uiAutomation;

import com.assignment.objectRepository.NDTVWeatherRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WeatherNDTVUI {

    @Parameters({"URL","cityName"})
    @Test
    public void checkWeather(String url,String cityName) {
        System.setProperty("webdriver.chrome.driver", "C:\\work\\npmwork\\node_modules\\chromedriver\\lib\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        NDTVWeatherRepository nvr = new NDTVWeatherRepository(driver);
        nvr.getSearchBox().sendKeys(cityName);

        if(!(driver.findElement(By.id(cityName)).isSelected())){
            driver.findElement(By.id(cityName)).click();
        }

        driver.findElement(By.xpath("//div[text()='" + cityName + "']")).click();
        String[] values = new String[5];

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@style='margin-bottom:10px']")));
        values[0] = nvr.getNameofCity().getText();
        System.out.println(values[0]);
        values[1] = nvr.getConditionOfWeather().getText();
        values[2] = nvr.getWind().getText();
        values[3] = nvr.getTempInDegrees().getText();
        values[4] = nvr.getTempInFahrenheit().getText();

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
