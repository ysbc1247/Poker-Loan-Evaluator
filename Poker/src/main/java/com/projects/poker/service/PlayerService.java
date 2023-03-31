package com.projects.poker.service;

import com.projects.poker.domain.Loan;
import com.projects.poker.domain.Player;
import com.projects.poker.dto.PlayerDTO;
import com.projects.poker.repository.LoanRepository;
import com.projects.poker.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LoanRepository loanRepository;
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setCash(playerDTO.getCash());
        player = playerRepository.save(player);
        return mapPlayerToDTO(player);
    }

    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return mapPlayerToDTO(player);
    }

    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(this::mapPlayerToDTO)
                .collect(Collectors.toList());
    }

    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        player.setName(playerDTO.getName());
        player.setCash(playerDTO.getCash());
        player = playerRepository.save(player);
        return mapPlayerToDTO(player);
    }

    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        playerRepository.delete(player);
    }

    public double getPlayerBalance(Player player) {
        List<Loan> lenderLoans = loanRepository.findByLender(player);
        List<Loan> borrowerLoans = loanRepository.findByBorrower(player);
        double cash = 0;
        for(Loan loan: lenderLoans){
            cash += loan.getAmount();
        }
        for (Loan loan : borrowerLoans){
            cash -= loan.getAmount();
        }
        return cash;
    }

    public Boolean validPassword(Long id, String password){
        Player player = playerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return Objects.equals(player.getPassword(), password);
    }

    private PlayerDTO mapPlayerToDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setCash(player.getCash());
        return playerDTO;
    }
}
