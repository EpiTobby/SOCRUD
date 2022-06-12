package fr.tobby.socrud.model.request;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Builder
@Getter
@JsonAutoDetect
public final class LoginRequest {
    @NotNull
    private final String login;
    @NotNull
    private final String password;

    public LoginRequest(@JsonProperty("login") @NotNull final String login, @JsonProperty("password") @NotNull final String password)
    {
        this.login = login;
        this.password = password;
    }
}
