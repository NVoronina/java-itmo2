package com.example.javaitmo2.entity;

import com.example.javaitmo2.dto.Driver;
import com.example.javaitmo2.dto.response.ResponseInterface;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String vinNumber;

    private String brand;

    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Integer seatsCount = 0;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    Set<CarEntity> drivers;
}
