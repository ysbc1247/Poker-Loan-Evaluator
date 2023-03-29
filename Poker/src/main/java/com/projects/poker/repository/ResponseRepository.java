package com.projects.poker.repository;

import com.projects.poker.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ResponseRepository extends JpaRepository<Response, Long> {
}
