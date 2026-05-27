package com.arquiteturaweb.app.Services;

import java.util.List;
import java.util.Optional;

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

    public List<PessoaDTO> read() {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        List<PessoaDTO> pessoasDTO = pessoas
                .stream()
                .map(p -> new PessoaDTO(p.getNome(), p.getIdade()))
                .toList();

        return pessoasDTO;
    }

    public void create(PessoaDTO pessoa) {
        Pessoa p = new Pessoa(pessoa.getNome(), pessoa.getIdade());
        pessoaRepository.save(p);
    }

    public void update(Long id, PessoaDTO pessoa) {
        Optional<Pessoa> p = pessoaRepository.findById(id);

        if (!p.isPresent()) {
            throw new RuntimeException("Pessoa com o ID: " + id + " não encontrado");
        }

        Pessoa pessoaUpdate = p.get();

        pessoaUpdate.setNome(pessoa.getNome());
        pessoaUpdate.setIdade(pessoa.getIdade());

        pessoaRepository.save(pessoaUpdate);
    }

    public void delete(Long id) {

        if (!pessoaRepository.existsById(id)) {
            throw new RuntimeException("Pessoa com ID: " + id + " não encontrado");
        }

        pessoaRepository.deleteById(id);
    }
}
