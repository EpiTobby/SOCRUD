package fr.tobby.socrud.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}
