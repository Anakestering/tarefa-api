package com.example.gerenciadortarefas.dto;

import java.time.LocalDate;

public record TarefaDTO (
        String titulo,
        String descricao,
        LocalDate dataVencimento,
        String prioridade

){}