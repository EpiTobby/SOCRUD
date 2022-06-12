package fr.tobby.socrud.model.request;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
}
