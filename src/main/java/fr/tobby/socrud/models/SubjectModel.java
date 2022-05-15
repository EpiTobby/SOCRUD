package fr.tobby.socrud.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

@Getter
@JsonAutoDetect
public final class SubjectModel {
    private final long id;
    private final String title;
    private final String description;

    public SubjectModel(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
