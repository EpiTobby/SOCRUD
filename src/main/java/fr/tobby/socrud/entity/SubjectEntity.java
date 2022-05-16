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
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Setter
    private String title;
    @NotNull
    @Setter
    private String description;

    public SubjectEntity(@NotNull String title,
            @NotNull String description) {
        this.id = null;
        this.title = title;
        this.description = description;
    }
}
