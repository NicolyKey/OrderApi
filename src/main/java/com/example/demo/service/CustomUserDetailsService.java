package com.example.demo.service;

import com.example.demo.model.Costumer;
import com.example.demo.repository.CostumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CostumerRepository costumerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Costumer costumer = costumerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado: " + email));

        // Aqui convertemos para o UserDetails do Spring
        return User.builder()
                .username(costumer.getEmail())
                .password(costumer.getPassword())
                .roles("USER") // Pode mudar para ADMIN, etc., se quiser
                .build();
    }
}
