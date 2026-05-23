package com.arquiteturaweb.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arquiteturaweb.app.Entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
}
