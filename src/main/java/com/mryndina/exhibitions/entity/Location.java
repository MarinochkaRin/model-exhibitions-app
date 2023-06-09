package com.mryndina.exhibitions.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="LOCATIONS")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID", nullable = false)
    private int id;
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "locations")
    private List<Exhibition> exhibitions;


    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
