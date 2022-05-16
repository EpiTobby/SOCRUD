package fr.tobby.socrud.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;

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
    @JoinColumn(name = "deegres")
    private DegreesEntity deegre;

    @Column(name = "tarif")
    private int price;

    @Column(name = "remote_percentage")
    private double remotePercentage;

    @Setter
    private Date startDate;

    @Setter
    private String description;

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
