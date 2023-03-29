package com.projects.poker.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "lender_id")
    private Player lender;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private Player borrower;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    // Constructors, getters, and setters omitted for brevity
}
