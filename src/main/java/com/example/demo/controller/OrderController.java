package com.example.demo.controller;

import com.example.demo.dto.Order.OrderDTO;
import com.example.demo.dto.Order.OrderRequest;
import com.example.demo.dto.Order.OrderUpdateRequest;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/comandas")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable UUID id) {
        OrderDTO orderDTO = orderService.getById(id);
        return ResponseEntity.ok(orderDTO);
    }


    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequest dto) {
        OrderDTO created = orderService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}/products")
    public ResponseEntity<OrderDTO> updateOrderProducts(
            @PathVariable UUID id,
            @RequestBody OrderUpdateRequest request) {
        OrderDTO updated = orderService.updateOrderProducts(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, Map<String, String>>> deleteOrder(@PathVariable UUID id) {
        orderService.delete(id);

        Map<String, String> successMessage = Map.of("text", "comanda removida");
        Map<String, Map<String, String>> response = Map.of("success", successMessage);

        return ResponseEntity.ok(response);
    }

}
