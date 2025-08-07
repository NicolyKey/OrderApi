package com.example.demo.service;

import com.example.demo.dto.Costumer.CostumerDTO;
import com.example.demo.mapper.CostumerMapper;
import com.example.demo.model.Costumer;
import com.example.demo.repository.CostumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CostumerService {

    private final CostumerRepository repository;
    private final CostumerMapper mapper;

    public CostumerDTO create(CostumerDTO dto) {
        Costumer costumer = mapper.toEntity(dto);
        Costumer saved = repository.save(costumer);
        return mapper.toDTO(saved);
    }

    public List<CostumerDTO> getAll() {
        List<Costumer> costumers = repository.findAll();
        return mapper.toDTOList(costumers);
    }
}

