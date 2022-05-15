package fr.tobby.socrud.model.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Builder
@Getter
@JsonAutoDetect
public class UpdateSubjectRequest {
    @Nullable
    private final String title;
    @Nullable
    private final String description;
}
