package com.example.gerenciadortarefas.service;


import com.example.gerenciadortarefas.Model.TarefaModel;
import com.example.gerenciadortarefas.dto.TarefaDTO;
import com.example.gerenciadortarefas.dto.TarefaListaDTO;
import com.example.gerenciadortarefas.repository.TarefaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public TarefaModel cadastrarTarefa(TarefaDTO dto){
        TarefaModel novoTarefa = new TarefaModel();
        novoTarefa.setTitulo(dto.titulo());
        novoTarefa.setDescricao(dto.descricao());
        novoTarefa.setDataVencimento(dto.dataVencimento());
        novoTarefa.setPrioridade(dto.prioridade());

        return tarefaRepository.save(novoTarefa);
    }

    public List<TarefaListaDTO> listarTodos(){
        List<TarefaModel> tarefa = tarefaRepository.findAll();
        return tarefa.stream().map(tarefaModel ->
                new TarefaListaDTO(
                        tarefaModel.getId(),
                        tarefaModel.getTitulo(),
                        tarefaModel.getDescricao(),
                        tarefaModel.getDataVencimento(),
                        tarefaModel.getDataCriacao(),
                        tarefaModel.getPrioridade()
                )
        ).collect(Collectors.toList());
    }

    public void deletarTarefa(Long id){
        boolean existe = tarefaRepository.existsById(id);

        if(!existe){
            throw new RuntimeException("Tarefa não encontrado");
        }
        tarefaRepository.deleteById(id);
    }

    public void atualizarTarefa(Long id, TarefaDTO tarefaDTO){
        Optional<TarefaModel> optionalTarefa = tarefaRepository.findById(id);

        if(optionalTarefa.isEmpty()){
            throw new RuntimeException("Tarefa não encontrada");
        }

        TarefaModel tarefaExistente = optionalTarefa.get();

        tarefaExistente.setTitulo(tarefaDTO.titulo());
        tarefaExistente.setDescricao(tarefaDTO.descricao());
        tarefaExistente.setDataVencimento(tarefaDTO.dataVencimento());
        tarefaExistente.setPrioridade(tarefaDTO.prioridade());

        tarefaRepository.save(tarefaExistente);
    }





}
