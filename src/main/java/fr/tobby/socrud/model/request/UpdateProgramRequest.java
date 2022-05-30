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
    private final String durationMonths;
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

    public static UpdateProgramRequest of(ProgramEntity entity) {
        return UpdateProgramRequest.builder()
                .title(entity.getTitle())
                .campus(entity.getCampus())
                .durationMonths(entity.getDurationMonths())
                .degree(entity.getDegree().getTitle())
                .price(entity.getPrice())
                .remotePercentage(entity.getRemotePercentage())
                .startDate(entity.getStartDate())
                .description(entity.getDescription()).build();
    }
}
