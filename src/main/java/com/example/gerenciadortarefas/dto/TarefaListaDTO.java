package com.example.gerenciadortarefas.dto;

public record TarefaListaDTO (
        Long id,
        String nome,
        String email,
        String telefone,
        String dataNascimento
){}