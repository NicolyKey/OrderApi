package com.example.demo.mapper;

import com.example.demo.dto.Costumer.CostumerDTO;
import com.example.demo.model.Costumer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CostumerMapper {

    public Costumer toEntity(CostumerDTO dto) {
        Costumer entity = new Costumer();
        entity.setName(dto.getNomeUsuario());
        entity.setPhoneNumber(dto.getTelefoneUsuario());
        entity.setEmail(dto.getEmailUsuario());
        entity.setPassword(dto.getSenhaUsuario());

        return entity;
    }

    public CostumerDTO toDTO(Costumer entity) {
        CostumerDTO dto = new CostumerDTO();
        dto.setIdUsuario(entity.getId().toString());
        dto.setNomeUsuario(entity.getName());
        dto.setTelefoneUsuario(entity.getPhoneNumber());
        dto.setEmailUsuario(entity.getEmail());
        return dto;
    }

    public List<CostumerDTO> toDTOList(List<Costumer> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
