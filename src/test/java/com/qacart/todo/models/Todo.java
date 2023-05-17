package com.qacart.todo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Todo {
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    public Todo(){}

    public Todo(Boolean isCompleted, String item) {
        this.isCompleted = isCompleted;
        this.item = item;
    }
    public Todo(String item){
        this.item=item;
    }
    private String item;
    private String userID;
    private String createdAt;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("__v")
    private String v;

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setISCompleted(Boolean isCompleted) {
       this.isCompleted = isCompleted;
    }
    @JsonProperty("_id")
    public String getId() {
        return id;
    }
    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("__v")
    public String getV() {
        return v;
    }

    @JsonProperty("__v")
    public void setV(String v) {
        this.v = v;
    }








}
