package com.assignment.comparing;

import com.assignment.apiAutomation.OpenWeatherMapAPIValidation;
import com.assignment.uiAutomation.WeatherNDTVUI;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CompareTemperature {

    @Test(dependsOnGroups = "WeatherCollect")
    public void compareTempInDegree(){
        WeatherNDTVUI wnu = new WeatherNDTVUI();
        Double tempFromUI=  Double.parseDouble(wnu.getTempInDegree());
        OpenWeatherMapAPIValidation omav = new OpenWeatherMapAPIValidation();
        Double tempFromAPI= Double.parseDouble(omav.getTemp());
        Double difference=0.0;
        if(tempFromAPI > tempFromUI)
            difference=tempFromAPI-tempFromUI;
        else
            difference = tempFromUI -tempFromAPI;
        System.out.println("difference is: "+difference);

        if(difference<=2.0)
            Assert.assertTrue(true);
        else
            Assert.assertTrue(false);

    }
}
