package com.project.teste.Person.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.project.teste.Person.Repository.PersonRepository;
import com.project.teste.Person.model.Person;

import dto.PersonDto;

@Service
public class PersonServices {
	
	@Autowired
	private PersonRepository repository;
	
	public List<PersonDto> findAlls(){
		List<Person> persons = repository.findAll();
		
		return  persons.stream().map(person -> new ModelMapper().map(person, PersonDto.class)) .collect(Collectors.toList());
				
		
	}
	
	
	 public Optional<PersonDto> findById(Long id){
	        

	            Optional<Person> persons = repository.findById(id);

	        
	            if (persons.isEmpty()){
	                throw  new ResourceAccessException("Person com id " + id + "não encontrado");
	            }

	       
	            PersonDto dto = new ModelMapper().map(persons.get(),PersonDto.class);          

	     
	            return  Optional.of(dto);
	    }


	   public  PersonDto createPerson(PersonDto personDto){

	        personDto.setId(null);

	   
	        ModelMapper mapper = new ModelMapper();

	      
	        Person persons = mapper.map(personDto, Person.class);


	    
	        persons = repository.save(persons);
	        personDto.setId(persons.getId());


	     

	        return  personDto;


	    }



	    public  void  delete(Long id) throws Exception {

	      
	        Optional<Person> persons = repository.findById(id);

	        if (persons.isEmpty()){
	          

	           throw   new Exception("não foi possível deletar o produto com o id: " + id + "Produto não existe");

	        }

	        repository.deleteById(id);
	    }


	    public PersonDto atualizar(Long id, PersonDto personDto){
	        
	        personDto.setId(id);

	       
	        ModelMapper mapper = new ModelMapper();


	  
	        Person produto = mapper.map(personDto, Person.class);

	       
	        repository.save(produto);


	       

	            return  personDto;
	    }

}
