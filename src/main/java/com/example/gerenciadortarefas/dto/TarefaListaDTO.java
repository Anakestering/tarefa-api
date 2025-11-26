package com.example.gerenciadortarefas.dto;

import java.time.LocalDate;

public record TarefaListaDTO (
        Long id,
        String titulo,
        String descricao,
        LocalDate dataVencimento,
        String prioridade
){}