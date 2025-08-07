package com.example.demo.repository;

import com.example.demo.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, UUID> {
    Optional<Costumer> findByEmail(String email);
}
