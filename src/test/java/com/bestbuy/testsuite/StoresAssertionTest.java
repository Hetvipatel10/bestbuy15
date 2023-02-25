package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1) Verify the if the total is equal to 1561
    @Test
    public void verifyTotal() {
        response.body("total",equalTo(1561));
    }
    //Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void verifyStoreLimit(){
        response.body("limit",equalTo(10));

    }
    //Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void CheckSingleName(){
        response.body("data.name",hasItem("Inver Grove Heights"));
    }
    //Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void CheckMultipleName(){
        response.body("data.name",hasItems("Roseville", "Burnsville", "Maplewood"));

    }
    //Verify the storied=7 inside storeservices of the third store of second services
    @Test
    public void verifyStoreService(){
        response.body("data[2].services[2].storeservices.storeId",equalTo(7));
    }
    //Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void verifyStoreName(){
        response.body("data[2].services[2].storeservices.createdAt",equalTo("2016-11-17T17:57:09.417Z"));

    }
    //Verify the state = MN of forth store
    @Test
    public void verifyState(){
     response.body("data[4].state",equalTo("MN"));
    }
    //Verify the store name = Rochester of 9th store
    @Test
    public void verifyRochesterName(){
        response.body("data.name",hasItem("Rochester"));

    }
   //Verify the storeId = 11 for the 6th store
    @Test
    public void verifyStoreId(){
          response.body("data[5].id",equalTo(11));
    }
    //Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void verifyServiceId(){
         response.body("data[6].services[3].id",equalTo(4));
    }
}

