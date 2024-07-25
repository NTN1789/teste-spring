package com.project.teste.Person.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.teste.Person.Services.PersonServices;

import dto.PersonDto;
import io.swagger.v3.oas.annotations.Operation;
import models.PersonRequest;
import models.PersonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "api/person")

public class PersonController {

	@Autowired
	private PersonServices  servicos ; 
	
	@GetMapping(
	
			produces =   { "application/json", "application/xml", "application/x-yaml" }) 
	   @Operation(summary =  "finds all person ", description = "pegar pessoas " , 
	   tags =  {"Person"},
	   responses = { 
			   @ApiResponse( description = "Success" ,responseCode = "200"  , content = {
					    @Content(
					    			mediaType =   "application/json",
					    			array =  @ArraySchema(schema =  @Schema(implementation = PersonDto.class))
					    		)
			   }),
			   @ApiResponse( description = "Bad Request" ,responseCode = "400"  , content = @Content),
			   @ApiResponse( description = "Unauthorized" ,responseCode = "401"  , content = @Content),
			   @ApiResponse( description = "Not found" ,responseCode = "404"  , content = @Content),
			   @ApiResponse( description = "Internal error" ,responseCode = "500"  , content = @Content)
	   }
			)
	public ResponseEntity<List<PersonResponse>> findByAll(){
		List<PersonDto> persons = servicos.findAlls();
		
		ModelMapper mapper = new ModelMapper();
		
		List <PersonResponse> response = persons.stream().map(personDto -> mapper.map(personDto, PersonResponse.class))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(response);
		
	}
	
			
	
	
	@PostMapping(
			produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    
    @Operation(summary =    "create a person ", description = "criar pessoas  ", 
    tags =  {"Person"},
    		responses = { 
    				   @ApiResponse( description = "Success" ,responseCode = "200"  ,content = @Content(
    					schema =  @Schema(implementation = PersonDto.class))),
    				   @ApiResponse( description = "No content" ,responseCode = "204"  , content = @Content),
    				   @ApiResponse( description = "Bad Request" ,responseCode = "400"  , content = @Content),
    				   @ApiResponse( description = "Unauthorized" ,responseCode = "401"  , content = @Content),
    				   @ApiResponse( description = "Not found" ,responseCode = "404"  , content = @Content),
    				   @ApiResponse( description = "Internal error" ,responseCode = "500"  , content = @Content)
    		   }
			)
	
	
	
	public ResponseEntity<PersonResponse> addPerson(@RequestBody PersonRequest  personReq){
		
			ModelMapper mapper = new ModelMapper();
			
			PersonDto personDto = mapper.map(personReq, PersonDto.class);
			
			personDto = servicos.createPerson(personDto);
			
			
			return  new ResponseEntity<>(mapper.map(personDto, PersonResponse.class), HttpStatus.CREATED);
	}
	
	
	@GetMapping(path = "/{id}", 
 produces = { "application/json", "application/xml", "application/x-yaml" })
    
    
    @Operation(summary =    "find a person ", description = "pegar pessoas por id  ", 
    tags =  {"Person"},
    		responses = { 
    				   @ApiResponse( description = "Success" ,responseCode = "200"  ,content = @Content(
    					schema =  @Schema(implementation = PersonDto.class))),
    				   @ApiResponse( description = "No content" ,responseCode = "204"  , content = @Content),
    				   @ApiResponse( description = "Bad Request" ,responseCode = "400"  , content = @Content),
    				   @ApiResponse( description = "Unauthorized" ,responseCode = "401"  , content = @Content),
    				   @ApiResponse( description = "Not found" ,responseCode = "404"  , content = @Content),
    				   @ApiResponse( description = "Internal error" ,responseCode = "500"  , content = @Content)
    		   }
			)
	public ResponseEntity <Optional<PersonResponse>> findPersonById(@PathVariable Long id){
		
			try {
				 Optional<PersonDto> personDto = servicos.findById(id);
				 PersonResponse personResponse = new ModelMapper().map(personDto.get( ),PersonResponse.class);
			
				 return  new ResponseEntity<>(Optional.of(personResponse), HttpStatus.OK );
			} catch (Exception e) {
				 return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
	}
	
	@DeleteMapping("/{id}")
    public  ResponseEntity <?>apagar(@PathVariable Long id){
    try {
        servicos.delete(id);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
	
	
	
	@PutMapping("/{id}")
    public  ResponseEntity <PersonResponse> atualizar(@RequestBody  PersonRequest personReq, @PathVariable Long id){
        ModelMapper mapper = new ModelMapper();
        PersonDto personDto = mapper.map(personReq,PersonDto.class);


      personDto =   servicos.atualizar(id,personDto);

      return  new ResponseEntity<>(mapper.map(personDto,PersonResponse.class), HttpStatus.OK);

}

	
}
