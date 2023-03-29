package com.projects.poker.controller;

import com.projects.poker.dto.ResponseDTO;
import com.projects.poker.service.ResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping("/new")
    public ResponseEntity<ResponseDTO> createResponse(@Valid @ModelAttribute ResponseDTO responseDTO) {
        ResponseDTO createdResponseDTO = responseService.createResponse(responseDTO);
        return new ResponseEntity<>(createdResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getResponseById(@PathVariable("id") Long id) {
        ResponseDTO responseDTO = responseService.getResponseById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseDTO>> getAllResponses() {
        List<ResponseDTO> responseDTOs = responseService.getAllResponses();
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateResponse(@PathVariable("id") Long id, @Valid @ModelAttribute ResponseDTO responseDTO) {
        ResponseDTO updatedResponseDTO = responseService.updateResponse(id, responseDTO);
        return new ResponseEntity<>(updatedResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable("id") Long id) {
        responseService.deleteResponse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
