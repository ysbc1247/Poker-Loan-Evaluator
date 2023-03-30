package com.projects.poker.service;

import com.projects.poker.domain.Player;
import com.projects.poker.domain.Request;
import com.projects.poker.dto.RequestDTO;
import com.projects.poker.repository.PlayerRepository;
import com.projects.poker.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RequestRepository requestRepository;

    public RequestDTO createRequest(RequestDTO requestDTO) {
        Player borrower = playerRepository.findById(requestDTO.getBorrowerId())
                .orElseThrow(ResourceNotFoundException::new);
        Player lender = playerRepository.findById(requestDTO.getLenderId())
                .orElseThrow(ResourceNotFoundException::new);

        Request request = new Request();
        request.setAmount(requestDTO.getAmount());
        request.setBorrower(borrower);
        request.setLender(lender);

        request = requestRepository.save(request);
        return mapRequestToDTO(request);
    }

    public RequestDTO getRequestById(Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return mapRequestToDTO(request);
    }

    public List<RequestDTO> getAllRequests() {
        List<Request> requests = requestRepository.findAll();
        return requests.stream()
                .map(this::mapRequestToDTO)
                .collect(Collectors.toList());
    }

    public RequestDTO updateRequest(Long id, RequestDTO requestDTO) {
        Request request = requestRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        Player borrower = playerRepository.findById(requestDTO.getBorrowerId())
                .orElseThrow(ResourceNotFoundException::new);
        Player lender = playerRepository.findById(requestDTO.getLenderId())
                .orElseThrow(ResourceNotFoundException::new);
        request.setAmount(requestDTO.getAmount());
        request.setBorrower(borrower);
        request.setLender(lender);
        request = requestRepository.save(request);
        return mapRequestToDTO(request);
    }

    public void deleteRequest(Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        requestRepository.delete(request);
    }

    private RequestDTO mapRequestToDTO(Request request) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setId(request.getId());
        requestDTO.setAmount(request.getAmount());
        requestDTO.setBorrowerId(request.getBorrower().getId());
        requestDTO.setLenderId(request.getLender().getId());
        return requestDTO;
    }
}

