package fr.tobby.socrud.model.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fr.tobby.socrud.entity.ProgramEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@JsonAutoDetect
public class CreateProgramRequest {
    private final String title;
    private final String campus;
    private final Integer durationMonths;
    private final String degree;
    private final Integer price;
    private final Double remotePercentage;
    private final Date startDate;
    private final String description;
    private final List<ProgramSubjectRequest> subjects;
}
