package com.arquiteturaweb.app.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arquiteturaweb.app.Entities.PessoaDTO;
import com.arquiteturaweb.app.Services.PessoaService;

@RestController
@RequestMapping("/api")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    @GetMapping("/pessoa")
    public ResponseEntity<List<PessoaDTO>> read() {
        return ResponseEntity.ok(pessoaService.read());
    }

    @PostMapping("/pessoa")
    public ResponseEntity<String> create(@RequestBody PessoaDTO pessoa) {
        pessoaService.create(pessoa);
        return ResponseEntity.ok("Pessoa criada com sucesso");
    }

    @PutMapping("/pessoa/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PessoaDTO pessoa) {
        pessoaService.update(id, pessoa);
        return ResponseEntity.ok("Pessoa atualizada com sucesso");
    }

    @DeleteMapping("/pessoa/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.ok("Pessoa deletada com sucesso");
    }
}

