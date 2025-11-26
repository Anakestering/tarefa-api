package com.example.gerenciadortarefas.dto;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TarefaListaDTO (
        Long id,
        String titulo,
        String descricao,
        LocalDateTime dataVencimento,
        String prioridade
){}