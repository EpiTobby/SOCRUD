package fr.tobby.socrud.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "degrees")
@Getter
public class DegreesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
}
