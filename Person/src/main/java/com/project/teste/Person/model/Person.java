package com.project.teste.Person.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "Pessoa")
public class Person {
 
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nome;
  @Column(name = "cpf", unique = true)
  @CPF
  private String cpf;
  private LocalDate dataNascimento;
  private String email;
   

     public  Person (){

    }

      public  Person(String nome, String cpf, LocalDate dataNascimento, String email) {
            this.nome = nome;
            this.cpf = cpf;
            this.dataNascimento = dataNascimento;
            this.email = email;
        } 

      public Long getId() {
          return id;
      }

      public void setId(Long id) {
          this.id = id;
      }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
  
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

      @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person pessoa = (Person) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}