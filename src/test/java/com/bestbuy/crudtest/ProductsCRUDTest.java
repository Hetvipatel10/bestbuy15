package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBase {
    int idNumber;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }
    @Test// get all list
    public void GetAllRecord(){

        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);

    }


    @Test// post new and retrive id
    public void GetById(){
        ProductPojo pojo=new ProductPojo();
        pojo.setName("hetvi10");
        pojo.setType("kbhlvs");
        pojo.setPrice(1800);
        pojo.setShipping(23);
        pojo.setUpc("4235");
        pojo.setDescription("desp1");
        pojo.setManufacturer("aulbdi");
        pojo.setModel("fordxf");
        pojo.setUrl("dbd");
        pojo.setImg("jhkjb");


        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(pojo)
                .post();
        response.then().statusCode(201);
        int idNumber = response.then().extract().path("id");

        System.out.println(idNumber);
    }

    @Test//update id
    public void CreateProduct(){
        ProductPojo pojo = new ProductPojo();
        pojo.setPrice(1700);
        pojo.setShipping(230);
        pojo.setManufacturer("audi");
        pojo.setModel("ford");

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", "9999702")
                .when()
                .body(pojo)
                .patch("/{id}");
        response.then().statusCode(200);

    }



    @Test//delete
    public void DeleteProduct(){
        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", "9999702")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }

    @Test// retrive id and validate
    public void test005() {
        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", "9999702")
                .when()
                .get("/{id}");
        response.then().statusCode(404);


    }


}
