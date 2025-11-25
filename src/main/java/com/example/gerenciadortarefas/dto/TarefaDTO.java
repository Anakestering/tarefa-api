package com.example.gerenciadortarefas.dto;

public record TarefaDTO (
        String nome,
        String email,
        String senha,
        String telefone,
        String dataNascimento
){}