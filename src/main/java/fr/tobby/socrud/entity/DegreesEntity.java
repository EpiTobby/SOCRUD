package fr.tobby.socrud.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "degrees")
@Getter
@NoArgsConstructor
public class DegreesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    public DegreesEntity(String title){
        this.id = null;
        this.title = title;
    }
}
