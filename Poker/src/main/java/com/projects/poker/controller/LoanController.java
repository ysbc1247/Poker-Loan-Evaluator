package com.projects.poker.controller;

import com.projects.poker.dto.LoanDTO;
import com.projects.poker.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@ModelAttribute LoanDTO loanRequestDTO) {
        LoanDTO loanDTO = loanService.createLoan(loanRequestDTO);
        return new ResponseEntity<>(loanDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable(value = "id") Long id,
                                              @ModelAttribute LoanDTO loanResponseDTO) {
        LoanDTO loanDTO = loanService.updateLoan(id, loanResponseDTO);
        return ResponseEntity.ok().body(loanDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable(value = "id") Long id) {
        LoanDTO loanDTO = loanService.getLoanById(id);
        return ResponseEntity.ok().body(loanDTO);
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        List<LoanDTO> loanDTOs = loanService.getAllLoans();
        return ResponseEntity.ok().body(loanDTOs);
    }
}
