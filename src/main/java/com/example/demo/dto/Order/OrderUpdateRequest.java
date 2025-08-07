package com.example.demo.dto.Order;

import com.example.demo.dto.Product.ProductDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderUpdateRequest {
    List<ProductDTO> products;
}
