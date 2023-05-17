package com.qacart.todo.testcases;

import com.qacart.todo.api.UserApi;
import com.qacart.todo.data.ErrorMessages;
import com.qacart.todo.models.User;
import com.qacart.todo.steps.UserSteps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class UserTest {
    @Test
    public void shouldBeAbleToRegister(){
        User user= UserSteps.generateUser();
        Response response = UserApi.register(user);
        User returnUser = response.body().as(User.class);
        assertThat(response.statusCode(),equalTo(201));
        assertThat(returnUser.getFirstName(),equalTo(user.getFirstName()));
    }
    @Test
    public void shouldNotBeAbleToRegisterWithTheSameEmail(){
        User user = UserSteps.getRegisteredUser();
       Response response=UserApi.register(user);
       assertThat(response.statusCode(),equalTo(400));
       Error errorMessage = response.body().as(Error.class);
       assertThat(errorMessage.getMessage(),equalTo(ErrorMessages.EMAIL_ALREADY_EXIST));
    }
    @Test
    public void shouldBeAbleToLogin(){
        User user =UserSteps.getRegisteredUser();
        User loginData =new User(user.getEmail(),user.getPassword());
       Response response = UserApi.login(loginData);
       User returnedUser= response.body().as(User.class);
       assertThat(returnedUser.getFirstName(),equalTo(user.getFirstName()));
        assertThat(returnedUser.getAccessToken(),not(equalTo(null)));
    }
    @Test
    public void shouldNotBeAbleToLogin(){
        User user =UserSteps.getRegisteredUser();
        User loginData = new User(user.getEmail(),"WrongPassword");
      Response response= UserApi.login(loginData);
      Error errorMessage = response.body().as(Error.class);
       assertThat(response.statusCode(),equalTo(401));
        assertThat(errorMessage.getMessage(),equalTo(ErrorMessages.EMAIL_PASSWORD_WRONG));

    }
}
