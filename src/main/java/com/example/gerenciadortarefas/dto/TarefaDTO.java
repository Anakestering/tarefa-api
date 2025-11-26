package com.example.gerenciadortarefas.dto;


import java.time.LocalDateTime;

public record TarefaDTO (
        String titulo,
        String descricao,
        LocalDateTime dataVencimento,

        String prioridade

){}