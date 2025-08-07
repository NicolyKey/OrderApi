package com.example.demo.dto.Order;

import com.example.demo.dto.Costumer.CostumerDTO;
import com.example.demo.dto.Product.ProductDTO;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
   private UUID id;
   private Integer orderNumber;
   private CostumerDTO cliente;
   private List<ProductDTO> produtos;
}
