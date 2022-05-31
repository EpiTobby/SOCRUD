package fr.tobby.socrud.model.request;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@JsonAutoDetect
public class LoginRequest {
    @NotNull
    private String login;
    @NotNull
    private String password;
}
