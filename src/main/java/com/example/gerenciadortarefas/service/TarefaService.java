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
        novoTarefa.setNome(dto.nome());
        novoTarefa.setEmail(dto.email());
        novoTarefa.setSenha(dto.senha());
        novoTarefa.setTelefone(dto.telefone());
        novoTarefa.setDataNascimento(dto.dataNascimento());

        return tarefaRepository.save(novoTarefa);
    }

    public List<TarefaListaDTO> listarTodos(){
        List<TarefaModel> tarefa = tarefaRepository.findAll();
        return tarefa.stream().map(tarefaModel ->
                new TarefaListaDTO(
                        tarefaModel.getId(),
                        tarefaModel.getNome(),
                        tarefaModel.getEmail(),
                        tarefaModel.getTelefone(),
                        tarefaModel.getDataNascimento()
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

        tarefaExistente.setNome(tarefaDTO.nome());
        tarefaExistente.setEmail(tarefaDTO.email());
        tarefaExistente.setTelefone(tarefaDTO.telefone());

        tarefaRepository.save(tarefaExistente);
    }





}
