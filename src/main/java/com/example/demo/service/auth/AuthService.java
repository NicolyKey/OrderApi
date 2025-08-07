package com.example.demo.service.auth;

import com.example.demo.exception.BusinessException;
import com.example.demo.model.Costumer;
import com.example.demo.repository.CostumerRepository;
import com.example.demo.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final CostumerRepository costumerRepository;
    private final JwtUtil jwtUtil;

    public String login(String email, String password) {
        Costumer costumer = costumerRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Email não encontrado"));

        if (!costumer.getPassword().equals(password)) {
            throw new BusinessException("Senha incorreta");
        }

        return jwtUtil.generateToken(costumer.getEmail());
    }
}
