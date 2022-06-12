package fr.tobby.socrud.model.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fr.tobby.socrud.entity.ProgramEntity;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@JsonAutoDetect
public class UpdateProgramRequest {
    @Nullable
    private final String title;
    @Nullable
    private final String campus;
    @Nullable
    private final Integer durationMonths;
    @Nullable
    private final String degree;
    @Nullable
    private final Integer price;
    @Nullable
    private final Double remotePercentage;
    @Nullable
    private final Date startDate;
    @Nullable
    private final String description;
    @Nullable
    private final List<ProgramSubjectRequest> subjects;
    @Nullable
    private List<Long> subjectsToRemoveFromProgram;
}
