package com.example.demo.mapper;

import com.example.demo.dto.Costumer.CostumerDTO;
import com.example.demo.dto.Order.OrderDTO;
import com.example.demo.dto.Order.OrderRequest;
import com.example.demo.dto.Product.ProductDTO;
import com.example.demo.model.Costumer;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.CostumerRepository;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class Mapper {
    private final ProductRepository productRepository;
    private final CostumerRepository costumerRepository;

    public  OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());

        if (order.getCostumer() != null) {
            Costumer costumer = order.getCostumer();
            CostumerDTO costumerDTO = new CostumerDTO();
            costumerDTO.setIdUsuario(costumer.getId().toString());
            costumerDTO.setNomeUsuario(costumer.getName());
            costumerDTO.setTelefoneUsuario(costumer.getPhoneNumber());
            dto.setCliente(costumerDTO);
        }

        List<ProductDTO> products = order.getProducts().stream().map(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPreco(product.getPrice());
            return productDTO;
        }).collect(Collectors.toList());

        dto.setProdutos(products);

        return dto;
    }

    public  Order toEntity(OrderDTO dto, Costumer costumer, List<Product> products) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setCostumer(costumer);
        order.setProducts(products);
        return order;
    }

    public Order toRequestEntity(OrderRequest dto, Costumer costumer, List<Product> products) {
        Order order = new Order();
        order.setCostumer(costumer);
        order.setProducts(products);
        order.setOrderNumber(dto.getNumeroPedido());
        return order;
    }


    public  List<Product> toProductEntityList(List<ProductDTO> productDTOs) {
        List<Product> products = new ArrayList<>();

        for (ProductDTO dto : productDTOs) {
            Product product = productRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product with ID " + dto.getId() + " not found"));
            products.add(product);
        }

        return products;
    }

    public  Costumer toCostumerEntity(CostumerDTO dto) {
        if (dto == null || dto.getIdUsuario() == null) {
            throw new IllegalArgumentException("Customer ID must not be null");
        }

        UUID costumerId = UUID.fromString(dto.getIdUsuario());

        return costumerRepository.findById(costumerId)
                .orElseThrow(() -> new EntityNotFoundException("Costumer with ID " + dto.getIdUsuario() + " not found"));
    }
}
