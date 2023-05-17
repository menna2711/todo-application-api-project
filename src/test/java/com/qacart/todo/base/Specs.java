package com.qacart.todo.base;

import com.qacart.todo.data.Route;
import io.restassured.http.ContentType;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specs {
    public static RequestSpecification getRequestSpec(){
        return given()
                .baseUri(Route.BASEURI)
                .contentType(ContentType.JSON)
                .log().all();
    }
}
