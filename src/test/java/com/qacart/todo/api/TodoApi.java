package com.qacart.todo.api;

import com.qacart.todo.base.Specs;
import com.qacart.todo.data.Route;
import com.qacart.todo.models.Todo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TodoApi {
    public static Response addTasks(Todo todo,String token){
        return  given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .body(todo)
                .when().post(Route.TASKS_ROUTE)
                .then()
                .extract().response();
    }

    public static Response getTask(String token,String taskID){
        return   given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .when().get(Route.TASKS_ROUTE+"/"+taskID)
                .then()
                .extract().response();
    }

    public static Response deleteTask(String token,String taskID){
        return  given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .when().delete(Route.TASKS_ROUTE+"/"+taskID)
                .then()
                .extract().response();
    }

}
