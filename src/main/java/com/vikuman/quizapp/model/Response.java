package com.vikuman.quizapp.model;

import lombok.Data;

@Data
public class Response {
    public Integer id;
    public String response;

    public Response(Integer id, String response) {
        this.id = id;
        this.response = response;
    }
}
