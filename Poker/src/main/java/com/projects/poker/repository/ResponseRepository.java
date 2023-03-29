package com.projects.poker.repository;

import com.projects.poker.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
