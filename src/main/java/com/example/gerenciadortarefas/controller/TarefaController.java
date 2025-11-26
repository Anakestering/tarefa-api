package com.example.gerenciadortarefas.controller;

import com.example.gerenciadortarefas.dto.TarefaDTO;
import com.example.gerenciadortarefas.dto.TarefaListaDTO;
import com.example.gerenciadortarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @DeleteMapping("/deletarUsuario/{id}")
    public ResponseEntity<String> deletarTarefa(@PathVariable Long id){
        try{
            tarefaService.deletarTarefa(id);
            return ResponseEntity.ok("Tarefa deletada com sucesso");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao deletar tarefa. Verifique os dados e tente novamente");
        }
    }

    @PostMapping("/cadastro/usuario")
    public ResponseEntity<String> cadastroUsuario(@RequestBody TarefaDTO tarefa){

        try{
            tarefaService.cadastrarTarefa(tarefa);
            String mensagemSucesso = "Tarefa " + tarefa.titulo() + " cadastrado com sucesso";
            return ResponseEntity.status(HttpStatus.OK).body(mensagemSucesso);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar: " + e.getMessage());
        }
    }

    @GetMapping("/listaUsuarios")
    public ResponseEntity<List<TarefaListaDTO>> listaTarefas(){
        List<TarefaListaDTO> tarefas = tarefaService.listarTodos();

        return ResponseEntity.ok(tarefas);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<String> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO){
        try{
            tarefaService.atualizarTarefa(id, tarefaDTO);
            return ResponseEntity.ok("Tarefa atualizada!!");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
