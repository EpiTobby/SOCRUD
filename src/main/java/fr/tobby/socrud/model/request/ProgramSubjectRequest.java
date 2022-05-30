package fr.tobby.socrud.model.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Builder
@Getter
@JsonAutoDetect
public class ProgramSubjectRequest {
    @NotNull
    private final Integer subjectId;
    @NotNull
    private final Integer semesterIndex;
}
