package fr.tobby.socrud.model.request;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

@Getter
@JsonAutoDetect
public class LoginRequest {
    private String login;
    private String password;
}
