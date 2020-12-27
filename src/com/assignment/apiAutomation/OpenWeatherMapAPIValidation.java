package com.assignment.apiAutomation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;


public class OpenWeatherMapAPIValidation {
    public String apiKey="7fe67bf08c80ded756e598d6f8fedaea";

    @Parameters({"URL1","cityName","countryCode","units"})
    @Test
    public void testWeatherAPI(String URL,String cityName, String countryCode,String units){
        RestAssured.baseURI =URL;
       String getResponse= given().log().all().queryParam("appid",apiKey).
                queryParam("q",cityName,countryCode).
                queryParam("units",units).when().get().then().assertThat().log().all()
                .statusCode(200).extract().response().asString();

          JsonPath js = new JsonPath(getResponse);
          String city= js.getString("name");
          String tempInDegree = js.getString("main.temp");

        System.out.println(city);
        System.out.println(tempInDegree);


    }


}
