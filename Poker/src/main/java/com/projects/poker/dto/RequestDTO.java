package com.projects.poker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {

    private Long id;

    @NotNull
    private double amount;

    @NotNull
    private Long borrowerId;

    @NotNull
    private Long lenderId;
}
