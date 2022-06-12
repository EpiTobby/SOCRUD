package fr.tobby.socrud.model.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonAutoDetect
public final class CreateAccountRequest {
    private final String login;
    private final String password;
}
