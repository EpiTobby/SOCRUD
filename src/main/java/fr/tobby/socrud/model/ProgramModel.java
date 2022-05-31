package fr.tobby.socrud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fr.tobby.socrud.entity.ProgramEntity;
import fr.tobby.socrud.entity.ProgramSubjectEntity;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.*;


@Builder
@Getter
@JsonAutoDetect
public final class ProgramModel implements Searchable {
    private final long id;
    @NotNull
    private final String title;
    @NotNull
    private final String campus;
    @NotNull
    private final Integer durationMonths;
    @NotNull
    private final String degree;
    private final int price;
    private final double remotePercentage;
    @NotNull
    private final Date startDate;
    @NotNull
    private final String description;
    private final Map<Integer, List<SubjectModel>> subjects;

    public static ProgramModel of(ProgramEntity entity)
    {
        Map<Integer, List<SubjectModel>> subjects = new HashMap<>();
        for (final ProgramSubjectEntity subject : entity.getSubjects())
        {
            if (!subjects.containsKey(subject.getSemesterIndex()))
                subjects.put(subject.getSemesterIndex(), new ArrayList<>());
            subjects.get(subject.getSemesterIndex()).add(SubjectModel.of(subject.getSubject()));
        }
        return ProgramModel.builder()
                           .id(entity.getId())
                           .title(entity.getTitle())
                           .campus(entity.getCampus())
                           .durationMonths(entity.getDurationMonths())
                           .degree(entity.getDegree().getTitle())
                           .price(entity.getPrice())
                           .remotePercentage(entity.getRemotePercentage())
                           .startDate(entity.getStartDate())
                           .description(entity.getDescription())
                           .subjects(subjects)
                           .build();
    }
}
