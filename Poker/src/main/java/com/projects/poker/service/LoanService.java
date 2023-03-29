package com.projects.poker.service;

import com.projects.poker.domain.Game;
import com.projects.poker.domain.Loan;
import com.projects.poker.domain.Player;
import com.projects.poker.dto.LoanDTO;
import com.projects.poker.repository.GameRepository;
import com.projects.poker.repository.LoanRepository;
import com.projects.poker.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    public LoanDTO createLoan(LoanDTO loanDTO) {
        Player lender = playerRepository.findById(loanDTO.getLenderId())
                .orElseThrow(() -> new ResourceNotFoundException());
        Player borrower = playerRepository.findById(loanDTO.getBorrowerId())
                .orElseThrow(() -> new ResourceNotFoundException());
        Game game = gameRepository.findById(loanDTO.getGameId())
                .orElseThrow(() -> new ResourceNotFoundException());
        Loan loan = new Loan();
        loan.setAmount(loanDTO.getAmount());
        loan.setLender(lender);
        loan.setBorrower(borrower);
        loan.setGame(game);
        loan = loanRepository.save(loan);
        loanDTO.setId(loan.getId());
        return loanDTO;
    }

    public LoanDTO updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        loan.setAmount(loanDTO.getAmount());
        loanRepository.save(loan);
        return mapLoanToDTO(loan);
    }

    public LoanDTO getLoanById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        return mapLoanToDTO(loan);
    }

    public List<LoanDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream()
                .map(this::mapLoanToDTO)
                .collect(Collectors.toList());
    }

    private LoanDTO mapLoanToDTO(Loan loan) {
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(loan.getId());
        loanDTO.setAmount(loan.getAmount());
        loanDTO.setLenderId(loan.getLender().getId());
        loanDTO.setBorrowerId(loan.getBorrower().getId());
        loanDTO.setGameId(loan.getGame().getId());
        return loanDTO;
    }
}
