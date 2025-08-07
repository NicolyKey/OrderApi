package com.example.demo.controller;

import com.example.demo.dto.Costumer.CostumerDTO;
import com.example.demo.service.CostumerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class CostumerController {

    private final CostumerService costumerService;

    @PostMapping
    public ResponseEntity<CostumerDTO> create(@RequestBody CostumerDTO dto) {
        CostumerDTO created = costumerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<CostumerDTO>> getAll() {
        List<CostumerDTO> list = costumerService.getAll();
        return ResponseEntity.ok(list);
    }
}
