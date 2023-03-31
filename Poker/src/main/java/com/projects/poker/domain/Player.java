package com.projects.poker.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "player")

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "cash")
    private Long cash;

    @Column(name = "password")
    private String password;

}
