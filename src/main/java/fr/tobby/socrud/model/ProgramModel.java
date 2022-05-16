package fr.tobby.socrud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
    @NotNull
    private final int tarif;
    @NotNull
    private final double remotePercentage;
    @NotNull
    private final Date startDate;
    @NotNull
    private final String description;
}
