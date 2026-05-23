package com.arquiteturaweb.app.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Nome;    
    private int Idade;


    public Pessoa(String Nome, int Idade){
        this.Nome = Nome;
        this.Idade = Idade;
    }

    public Pessoa(){
        
    }


    public Long getId() {
        return Id;
    }


    public void setId(Long id) {
        Id = id;
    }


    public String getNome() {
        return Nome;
    }


    public void setNome(String nome) {
        Nome = nome;
    }


    public int getIdade() {
        return Idade;
    }


    public void setIdade(int idade) {
        Idade = idade;
    }

    
}
