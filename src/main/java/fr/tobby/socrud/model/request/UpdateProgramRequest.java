package fr.tobby.socrud.model.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fr.tobby.socrud.entity.ProgramEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
@JsonAutoDetect
public class UpdateProgramRequest {
    private final String title;
    private final String campus;
    private final String durationMonths;
    private final String degree;
    private final Integer price;
    private final Double remotePercentage;
    private final Date startDate;
    private final String description;

    public static UpdateProgramRequest of(ProgramEntity entity) {
        return UpdateProgramRequest.builder()
                .title(entity.getTitle())
                .campus(entity.getCampus())
                .durationMonths(entity.getDurationMonths())
                .degree(entity.getDeegre().getTitle())
                .price(entity.getPrice())
                .remotePercentage(entity.getRemotePercentage())
                .startDate(entity.getStartDate())
                .description(entity.getDescription()).build();
    }
}
