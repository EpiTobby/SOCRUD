package fr.tobby.socrud.model.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonAutoDetect
public final class CreateAccountRequest {
    private final String login;
    private final String password;

    public CreateAccountRequest(@JsonProperty("login") final String login, @JsonProperty("password") final String password)
    {
        this.login = login;
        this.password = password;
    }
}
