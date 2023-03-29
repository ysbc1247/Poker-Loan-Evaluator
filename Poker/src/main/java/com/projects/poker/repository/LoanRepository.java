package com.projects.poker.repository;

import com.projects.poker.domain.Loan;
import com.projects.poker.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByGameId(Long gameId);

    List<Loan> findByLender(Player lender);
    List<Loan> findByBorrower(Player borrower);
    List<Loan> findByLenderAndBorrower(Player lender, Player borrower);
}
