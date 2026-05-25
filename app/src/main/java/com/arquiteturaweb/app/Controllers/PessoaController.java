package com.arquiteturaweb.app.Controllers;

import java.util.List;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arquiteturaweb.app.Repositories.PessoaRepository;

import com.arquiteturaweb.app.Entities.Pessoa;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaRepository Context;

    public PessoaController(PessoaRepository pessoaRepository){
        this.Context = pessoaRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Pessoa>> getAll(){
        try{
            List<Pessoa> pessoas = Context.findAll();
            return ResponseEntity.ok(pessoas);
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Pessoa pessoa){
        Pessoa p = new Pessoa(pessoa.getNome(), pessoa.getIdade());

        try{
            Context.save(p);
            return ResponseEntity.ok("Pessoa salva com sucesso");
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Pessoa pessoa){
        Optional<Pessoa> p = Context.findById(pessoa.getId());
        
        if(!p.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa com o ID: " + pessoa.getId() + " não encontrado");
        }

        Pessoa pessoaUpdate = p.get();

        pessoaUpdate.setNome(pessoa.getNome());
        pessoaUpdate.setIdade(pessoa.getIdade());
        
        try{
            Context.save(pessoaUpdate);
            return ResponseEntity.ok("Pessoa atualizada com sucesso");
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("Erro ao persistir no banco");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long Id){

        if(!Context.existsById(Id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa com ID: " + Id + " não encontrado");
        }

        try{
            Context.deleteById(Id);
            return ResponseEntity.ok("Pessoa deletada com sucesso");
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("Erro ao persistir no banco de dados");
        }
    }
}
