package fr.tobby.socrud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fr.tobby.socrud.entity.ProgramEntity;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;


@Builder
@Getter
@JsonAutoDetect
public final class ProgramModel {
    private final long id;
    @NotNull
    private final String title;
    @NotNull
    private final String campus;
    @NotNull
    private final String durationMonths;
    @NotNull
    private final String deegree;
    private final int price;
    private final double remotePercentage;
    @NotNull
    private final Date startDate;
    @NotNull
    private final String description;

    public static ProgramModel of(ProgramEntity entity){
        return ProgramModel.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .campus(entity.getCampus())
                .durationMonths(entity.getDurationMonths())
                .deegree(entity.getDeegre().getTitle())
                .price(entity.getPrice())
                .remotePercentage(entity.getRemotePercentage())
                .startDate(entity.getStartDate())
                .description(entity.getDescription()).build();
    }
}
