package dto;

import java.time.LocalDate;
import java.util.Objects;

import com.project.teste.Person.model.Person;

public class PersonDto {
    // vai ser usado em controller e service
    private  Long id ;

    private String nome ;

    private String cpf;
    private LocalDate dataNascimento;
    private String email;


private Person person;

 
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

public Person getPerson() {
	return person;
}

public void setPerson(Person person) {
	this.person = person;
}


@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonDto person = (PersonDto) o;
    return Objects.equals(id, person.id);
}

@Override
public int hashCode() {
    return Objects.hash(id);
}
}
