package com.example.demo.dto.Order;

import com.example.demo.dto.Costumer.CostumerDTO;
import com.example.demo.dto.Product.ProductDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class OrderResponse {
    private UUID id;
    private CostumerDTO cliente;
    private List<ProductDTO> produtos;
}
