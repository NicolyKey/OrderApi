package com.example.demo.service;

import com.example.demo.dto.Order.OrderDTO;
import com.example.demo.dto.Order.OrderRequest;
import com.example.demo.dto.Order.OrderUpdateRequest;
import com.example.demo.dto.Product.ProductDTO;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Costumer;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final Mapper mapper;

    public List<OrderDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }


    public OrderDTO create(OrderRequest dto) {
        Costumer costumer = mapper.toCostumerEntity(dto.getCliente());
        List<Product> products = mapper.toProductEntityList(dto.getProdutos());

        Order order = mapper.toRequestEntity(dto, costumer, products);
        Order saved = repository.save(order);

        return mapper.toDTO(saved);
    }


    public OrderDTO updateOrderProducts(UUID orderId, OrderUpdateRequest request) {
        Order existingOrder = repository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        List<ProductDTO> productDTOs = request.getProducts();

        if (productDTOs == null || productDTOs.isEmpty()) {
            throw new IllegalArgumentException("Product list must not be empty");
        }

        for (ProductDTO productDTO : productDTOs) {
            Product product = existingOrder.getProducts().stream()
                    .filter(p -> p.getId().equals(productDTO.getId()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Product with ID " + productDTO.getId() + " not found in this order"));

            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPreco());
        }

        Order updatedOrder = repository.save(existingOrder);
        return mapper.toDTO(updatedOrder);
    }

    public void delete(UUID orderId) {
        if (!repository.existsById(orderId)) {
            throw new EntityNotFoundException("Order with ID " + orderId + " not found");
        }

        repository.deleteById(orderId);
    }
}
