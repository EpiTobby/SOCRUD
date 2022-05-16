package fr.tobby.socrud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fr.tobby.socrud.entity.SubjectEntity;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Builder
@Getter
@JsonAutoDetect
public final class SubjectModel {
    private final long id;
    @NotNull
    private final String title;
    @NotNull
    private final String description;

    public SubjectModel(long id,
                        @NotNull String title,
                        @NotNull String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public static SubjectModel of(SubjectEntity subjectEntity){
        return SubjectModel.builder()
                .id(subjectEntity.getId())
                .title(subjectEntity.getTitle())
                .description(subjectEntity.getDescription())
                .build();
    }
}
