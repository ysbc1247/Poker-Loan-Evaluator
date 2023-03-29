package com.projects.poker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

    private Long id;

    @NotNull
    private double amount;

    @NotNull
    private Boolean confirm;

    @NotNull
    private Long requestId;

}