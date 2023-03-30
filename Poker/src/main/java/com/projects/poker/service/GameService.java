package com.projects.poker.service;

import com.projects.poker.domain.Game;
import com.projects.poker.domain.Loan;
import com.projects.poker.domain.Player;
import com.projects.poker.dto.GameDTO;
import com.projects.poker.dto.LoanDTO;
import com.projects.poker.repository.GameRepository;
import com.projects.poker.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private LoanRepository loanRepository;

    public GameDTO startGame() {
        Game game = new Game();
        game.setStartTime(LocalDateTime.now());
        game = gameRepository.save(game);
        return mapGameToDTO(game);
    }

    public GameDTO endGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        game.setEndTime(LocalDateTime.now());
        game = gameRepository.save(game);
        calculateAndSaveLoans(game);
        return mapGameToDTO(game);
    }

    public List<LoanDTO> getLoansByGameId(Long gameId) {
        List<Loan> loans = loanRepository.findByGameId(gameId);
        return loans.stream()
                .map(this::mapLoanToDTO)
                .collect(Collectors.toList());
    }

    private void calculateAndSaveLoans(Game game) {
        List<Player> players = game.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            Player lender = players.get(i);
            for (int j = 0; j < players.size(); j++) {
                if (i == j) {
                    continue;
                }
                Player borrower = players.get(j);
                double amount = calculateAmountOwed(lender, borrower);
                if (amount > 0) {
                    Loan loan = new Loan();
                    loan.setAmount(amount);
                    loan.setLender(lender);
                    loan.setBorrower(borrower);
                    loan.setGame(game);
                    loanRepository.save(loan);
                }
            }
        }
    }

    private double calculateAmountOwed(Player lender, Player borrower) {
        double amountOwed = 0;
        List<Loan> lenderLoans = loanRepository.findByLenderAndBorrower(lender, borrower);
        List<Loan> borrowerLoans = loanRepository.findByLenderAndBorrower(borrower, lender);
        for (Loan loan : lenderLoans) {
            amountOwed += loan.getAmount();
        }
        for (Loan loan : borrowerLoans) {
            amountOwed -= loan.getAmount();
        }
        return amountOwed;
    }

    private GameDTO mapGameToDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setStartTime(game.getStartTime());
        gameDTO.setEndTime(game.getEndTime());
        return gameDTO;
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
