package com.arquiteturaweb.app.Controllers;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arquiteturaweb.app.Repositories.PessoaRepository;

import com.arquiteturaweb.app.Entities.Pessoa;

@RestController
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
        System.out.println(p.getNome());
        System.out.println(p.getIdade());
        System.out.println(p.getId());

        try{
            Context.save(p);
            return ResponseEntity.ok("Pessoa salva com sucesso");
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }

    }
}
