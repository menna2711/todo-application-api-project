package com.qacart.todo.testcases;

import com.qacart.todo.api.TodoApi;
import com.qacart.todo.data.ErrorMessages;
import com.qacart.todo.models.Todo;
import com.qacart.todo.steps.TodoSteps;
import com.qacart.todo.steps.UserSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TodoTest {
    @Test
    public void shouldBeAddTask(){
        String token= UserSteps.getToken();
        Todo todo= TodoSteps.generateTodo();
        Response response= TodoApi.addTasks(todo,token);
        assertThat(response.statusCode(),equalTo(201));
        Todo returnTodo = response.body().as(Todo.class);
        assertThat(returnTodo.getItem(),equalTo(todo.getItem()));
        assertThat(returnTodo.getIsCompleted(),equalTo(todo.getIsCompleted()));
    }

    @Test
    public void shouldNotBeAddTask(){
        Todo todo=new Todo("Learn Appium");
        String token=UserSteps.getToken();
      Response response=TodoApi.addTasks(todo,token);
      assertThat(response.statusCode(),equalTo(400));
      Error errorReturn = response.body().as(Error.class);
      assertThat(errorReturn.getMessage(),equalTo(ErrorMessages.ISCOMPLETED_REQUIRED));


    }

    @Test
    public void shouldBeGetTaskByID(){

        String token=UserSteps.getToken();
        Todo todo= TodoSteps.generateTodo();
        String taskID=TodoSteps.getTodoID(todo,token);
        Response response =TodoApi.getTask(token,taskID);

        Todo returnTodo = response.body().as(Todo.class);
        assertThat(response.statusCode(),equalTo(200));
        assertThat(returnTodo.getItem(),equalTo(todo.getItem()));
        assertThat(returnTodo.getIsCompleted(),equalTo(false));
    }

    @Test
    public void shouldBeDeleteTaskByID(){
        String token=UserSteps.getToken();
        Todo todo= TodoSteps.generateTodo();
        String taskID=TodoSteps.getTodoID(todo,token);

        Response response= TodoApi.deleteTask(token,taskID);
        assertThat(response.statusCode(),equalTo(200));
        Todo returnTodo = response.body().as(Todo.class);
        assertThat(returnTodo.getItem(),equalTo(todo.getItem()));
        assertThat(returnTodo.getIsCompleted(),equalTo(false));
    }

}
