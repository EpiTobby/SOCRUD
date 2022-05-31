package fr.tobby.socrud.model.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@JsonSerialize
@JsonAutoDetect
@Builder
@Getter
public final class LoginResponse {
    @NotNull
    private final String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}
