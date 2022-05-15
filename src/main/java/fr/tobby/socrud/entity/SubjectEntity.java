package fr.tobby.socrud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
@NoArgsConstructor
@Getter
@Setter
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;

    public SubjectEntity(@NotNull String title,
            @NotNull String description) {
        this.id = null;
        this.title = title;
        this.description = description;
    }
}
