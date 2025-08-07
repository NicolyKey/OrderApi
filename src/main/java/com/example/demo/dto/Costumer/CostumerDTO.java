package com.example.demo.dto.Costumer;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CostumerDTO {
    private String idUsuario;
    private String nomeUsuario;
    private Integer telefoneUsuario;
    private String emailUsuario;
    private String senhaUsuario;
}
