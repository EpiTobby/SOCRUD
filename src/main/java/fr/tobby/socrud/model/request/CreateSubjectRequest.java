package fr.tobby.socrud.model.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Builder
@Getter
@JsonAutoDetect
public final class CreateSubjectRequest {
    @NotNull
    private final String title;
    @NotNull
    private final String description;
}
