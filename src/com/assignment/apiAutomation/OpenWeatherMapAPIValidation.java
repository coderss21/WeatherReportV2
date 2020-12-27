package com.assignment.apiAutomation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;


public class OpenWeatherMapAPIValidation {
    public String apiKey="7fe67bf08c80ded756e598d6f8fedaea";
    private static String temp;
    private String city;

    @Parameters({"URL1","cityName","countryCode","units"})
    @Test
    public void testWeatherAPI(String URL,String cityName, String countryCode,String units){
        RestAssured.baseURI =URL;
       String getResponse= given().queryParam("appid",apiKey).
                queryParam("q",cityName,countryCode).
                queryParam("units",units).when().get().then().assertThat()
                .statusCode(200).extract().response().asString();

          JsonPath js = new JsonPath(getResponse);
          city= js.getString("name");
          temp = js.getString("main.temp");
          System.out.println(city);
          System.out.println(temp);
    }


    public String getTemp(){
        return temp;
    }


}
