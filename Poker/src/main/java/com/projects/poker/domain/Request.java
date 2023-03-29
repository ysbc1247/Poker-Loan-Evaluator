package com.projects.poker.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "loan_request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "borrower_id")
    private Player borrower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lender_id")
    private Player lender;

}