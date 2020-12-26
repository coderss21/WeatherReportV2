package com.assignment.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class NDTVWeatherRepository {
    WebDriver driver;

   public NDTVWeatherRepository(WebDriver driver){
        this.driver=driver;
       PageFactory.initElements(driver,this);
    }


   @FindBy(id="searchBox")
    WebElement searchBox;

    @FindBy(xpath ="//span[@style='margin-bottom:10px']")
    WebElement nameofCity;

    @FindBy(xpath ="//b[contains(text(),'Condition')]")
    WebElement conditionOfWeather;

    @FindBy(xpath="//b[contains(text(),'Wind')]")
    WebElement wind;

    @FindBy(xpath = "//b[contains(text(),'Temp in Degrees')]")
    WebElement tempInDegrees;

    @FindBy(xpath ="//b[contains(text(),'Temp in Fa')]")
    WebElement tempInFahrenheit;

   public WebElement getSearchBox(){
       return searchBox;
   }

   public WebElement getNameofCity(){
       return nameofCity;
   }

   public WebElement getConditionOfWeather(){ return conditionOfWeather;}

   public WebElement getWind(){
       return wind;
   }

   public WebElement getTempInDegrees(){
       return tempInDegrees;
   }

  public WebElement getTempInFahrenheit(){
       return tempInFahrenheit;
  }



}
