package fr.tobby.socrud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "program_subjects")
@NoArgsConstructor
@Getter
public class ProgramSubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private ProgramEntity program;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    @Setter
    private int semesterIndex;

    public ProgramSubjectEntity(ProgramEntity program, SubjectEntity subject, int semesterIndex){
        id = null;
        this.program = program;
        this.subject = subject;
        this.semesterIndex = semesterIndex;
    }
}
