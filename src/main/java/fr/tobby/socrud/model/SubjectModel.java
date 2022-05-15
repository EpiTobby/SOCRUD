package fr.tobby.socrud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

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
}
