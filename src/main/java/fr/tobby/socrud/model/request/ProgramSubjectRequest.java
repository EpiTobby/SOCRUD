package fr.tobby.socrud.model.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Builder
@Getter
@JsonAutoDetect
public final class ProgramSubjectRequest {
    @NotNull
    private final Long subjectId;
    @NotNull
    private final Integer semesterIndex;
}
