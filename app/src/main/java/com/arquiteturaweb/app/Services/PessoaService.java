package com.arquiteturaweb.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.app.Entities.Pessoa;
import com.arquiteturaweb.app.Entities.PessoaDTO;
import com.arquiteturaweb.app.Repositories.PessoaRepository;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public ResponseEntity<List<PessoaDTO>> read() {
        try {
            List<Pessoa> pessoas = pessoaRepository.findAll();

            List<PessoaDTO> pessoasDTO = pessoas
                .stream()
                .map(p -> new PessoaDTO(p.getNome(), p.getIdade()))
                .toList();
            
            return ResponseEntity.ok(pessoasDTO);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    public ResponseEntity<String> create(PessoaDTO pessoa) {
        Pessoa p = new Pessoa(pessoa.getNome(), pessoa.getIdade());

        try {
            pessoaRepository.save(p);
            return ResponseEntity.ok("Pessoa salva com sucesso");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao persistir no banco de dados");

        }
    }

    public ResponseEntity<String> update(Long id, PessoaDTO pessoa) {
        Optional<Pessoa> p = pessoaRepository.findById(id);

        if (!p.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa com o ID: " + id + " não encontrado");
        }

        Pessoa pessoaUpdate = p.get();

        pessoaUpdate.setNome(pessoa.getNome());
        pessoaUpdate.setIdade(pessoa.getIdade());

        try {
            pessoaRepository.save(pessoaUpdate);
            return ResponseEntity.ok("Pessoa atualizada com sucesso");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao persistir no banco de dados");
        }
    }

    public ResponseEntity<String> delete(Long id) {

        if (!pessoaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa com ID: " + id + " não encontrado");
        }

        try {
            pessoaRepository.deleteById(id);
            return ResponseEntity.ok("Pessoa deletada com sucesso");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao persistir no banco de dados");
        }
    }
}
