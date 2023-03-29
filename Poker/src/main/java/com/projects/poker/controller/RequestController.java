package com.projects.poker.controller;

import com.projects.poker.dto.RequestDTO;
import com.projects.poker.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/new")
    public ResponseEntity<RequestDTO> createRequest(@Valid @RequestBody RequestDTO requestDTO) {
        RequestDTO createdRequestDTO = requestService.createRequest(requestDTO);
        return new ResponseEntity<>(createdRequestDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDTO> getRequestById(@PathVariable("id") Long id) {
        RequestDTO requestDTO = requestService.getRequestById(id);
        return new ResponseEntity<>(requestDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<RequestDTO>> getAllRequests() {
        List<RequestDTO> requestDTOs = requestService.getAllRequests();
        return new ResponseEntity<>(requestDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestDTO> updateRequest(@PathVariable("id") Long id, @Valid @RequestBody RequestDTO requestDTO) {
        RequestDTO updatedRequestDTO = requestService.updateRequest(id, requestDTO);
        return new ResponseEntity<>(updatedRequestDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable("id") Long id) {
        requestService.deleteRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
