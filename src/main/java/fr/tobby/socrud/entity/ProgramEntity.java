package fr.tobby.socrud.entity;

import fr.tobby.socrud.model.request.CreateProgramRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "programs")
@Getter
public class ProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;
    @Setter
    private String campus;
    @Setter
    @Column(name = "duration_months")
    private String durationMonths;
    @Setter
    @ManyToOne
    @JoinColumn(name = "degree_id")
    private DegreesEntity degree;

    @Column(name = "tarif")
    private int price;

    @Column(name = "remote_percentage")
    private double remotePercentage;

    @Setter
    private Date startDate;

    @Setter
    private String description;

    @OneToMany
    @JoinColumn(name = "track_id")
    private List<ProgramSubjectEntity> subjects;

    public static ProgramEntity of(CreateProgramRequest request) {
        ProgramEntity program = new ProgramEntity();
        program.setTitle(request.getTitle());
        program.setCampus(request.getCampus());
        program.setDurationMonths(request.getDurationMonths());
        program.setPrice(request.getPrice());
        program.setRemotePercentage(request.getRemotePercentage());
        program.setStartDate(request.getStartDate());
        program.setDescription(request.getDescription());
        return program;
    }

    public void setRemotePercentage(double remotePercentage){
        if (remotePercentage < 0 || remotePercentage > 100){
            throw new IllegalArgumentException("The remote percentage must be between 0 and 100");
        }
        this.remotePercentage = remotePercentage;
    }

    public void setPrice(int price){
        if (price < 0){
            throw new IllegalArgumentException("The price of the program cannot be negative");
        }
        this.price = price;
    }
}
