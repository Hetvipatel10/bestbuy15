package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import groovy.lang.DelegatesTo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest extends TestBase {
    int idNumber;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }

    @Test
    public void GetAllRecord(){
        Response response=given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }


    @Test
    public void GetById(){
        Response response=given()
                .pathParam("id","6")
                .when()
                .get("{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void CreateStore(){

        StorePojo pojo=new StorePojo();
        pojo.setName("hetvi");
        pojo.setType("Bigbox");
        pojo.setAddress("Ste Anne Road");
        pojo.setAddress2("");
        pojo.setCity("Sudbury");
        pojo.setState("Ontario");
        pojo.setZip("P3C5M4");
        pojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");



        Response response= given()
                .header("Content-Type","application/json")
                .body(pojo)
                .post();
        response.then().log().all().statusCode(201);
        response.prettyPrint();


    }

    @Test
    public void PutStore(){

        StorePojo pojo=new StorePojo();
        pojo.setName("Mona1");
        pojo.setType("BigBox");
        pojo.setAddress("Ste Anne");
        pojo.setAddress2("");
        pojo.setCity("Sudbury");
        pojo.setState("Ontario");
        pojo.setZip("P3C5M4");
        pojo.setHours("Mon: 10-9; Tue: 10-8; Wed: 10-8; Thurs: 10-9; Fri: 10-8; Sat: 10-5; Sun: 10-5");


        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id","8921")
                .body(pojo)
                .put("/{id}");
        response.then().log().all().statusCode(200);
        response.prettyPrint();


    }

    @Test
    public void DeleteStore(){

        Response response= given()
                .pathParam("id","8921")
                .when()
                .delete("/{id}");
        response.then().statusCode(404);
        response.prettyPrint();
    }
}
