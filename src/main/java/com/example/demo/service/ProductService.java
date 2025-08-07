package com.example.demo.service;


import com.example.demo.dto.Product.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public ProductDTO create(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPreco());

        Product saved = repository.save(product);

        ProductDTO response = new ProductDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setPreco(saved.getPrice());

        return response;
    }

    public List<ProductDTO> getAll() {
        return repository.findAll().stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPreco(product.getPrice());
            return dto;
        }).collect(Collectors.toList());
    }
}
