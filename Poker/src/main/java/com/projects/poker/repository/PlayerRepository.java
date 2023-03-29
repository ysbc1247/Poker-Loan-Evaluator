package com.projects.poker.repository;

import com.projects.poker.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
