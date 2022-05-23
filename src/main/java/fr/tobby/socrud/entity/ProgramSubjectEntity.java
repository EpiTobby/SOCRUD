package fr.tobby.socrud.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "program_subjects")
@Getter
public class ProgramSubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private ProgramEntity program;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    private int semester;
}
