package fr.tobby.socrud.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "deegree")
@Getter
public class DeegreeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
}
