package com.projects.poker.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GameDTO {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    // Constructors, getters, and setters omitted for brevity
}

