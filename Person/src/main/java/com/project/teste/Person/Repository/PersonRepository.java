package com.project.teste.Person.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.teste.Person.model.Person;

public interface PersonRepository  extends JpaRepository<Person, Long> {

}
