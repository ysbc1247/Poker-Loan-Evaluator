package com.projects.poker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanDTO {

    private Long id;

    @NotNull
    private double amount;

    @NotNull
    private Long lenderId;

    @NotNull
    private Long borrowerId;

    @NotNull
    private Long gameId;

    // Constructors, getters, and setters omitted for brevity
}
