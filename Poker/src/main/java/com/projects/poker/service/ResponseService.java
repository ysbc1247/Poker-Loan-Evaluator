package com.projects.poker.service;

import com.projects.poker.domain.Request;
import com.projects.poker.domain.Response;
import com.projects.poker.dto.ResponseDTO;
import com.projects.poker.repository.RequestRepository;
import com.projects.poker.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ResponseRepository responseRepository;

    public ResponseDTO createResponse(ResponseDTO responseDTO) {
        Request request = requestRepository.findById(responseDTO.getRequestId())
                .orElseThrow(() -> new ResourceNotFoundException());

        Response response = new Response();
        response.setAmount(responseDTO.getAmount());
        response.setConfirm(responseDTO.getConfirm());
        response.setRequestId(request.getId());

        response = responseRepository.save(response);
        return mapResponseToDTO(response);
    }

    public ResponseDTO getResponseById(Long id) {
        Response response = responseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        return mapResponseToDTO(response);
    }

    public List<ResponseDTO> getAllResponses() {
        List<Response> responses = responseRepository.findAll();
        return responses.stream()
                .map(this::mapResponseToDTO)
                .collect(Collectors.toList());
    }

    public ResponseDTO updateResponse(Long id, ResponseDTO responseDTO) {
        Response response = responseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        Request request = requestRepository.findById(responseDTO.getRequestId())
                .orElseThrow(() -> new ResourceNotFoundException());
        response.setAmount(responseDTO.getAmount());
        response.setConfirm(responseDTO.getConfirm());
        response.setRequestId(request.getId());

        response = responseRepository.save(response);
        return mapResponseToDTO(response);
    }

    public void deleteResponse(Long id) {
        Response response = responseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        responseRepository.delete(response);
    }

    private ResponseDTO mapResponseToDTO(Response response) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setId(response.getId());
        responseDTO.setAmount(response.getAmount());
        responseDTO.setConfirm(response.getConfirm());
        responseDTO.setRequestId(response.getRequestId());
        return responseDTO;
    }
}