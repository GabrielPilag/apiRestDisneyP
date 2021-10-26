package com.disney.apiRest2.character;


import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/characters")
@AllArgsConstructor
public class CharacterController {
	
	private final CharacterService characterService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
	public ResponseEntity<List<CharacterView>> getCharacters(){
		return new ResponseEntity<>(characterService.getCharacters(), HttpStatus.OK);
	}
	
	@GetMapping(params = "name")
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
	public ResponseEntity<List<Character>> getCharacterByName(@RequestParam(name = "name") String name){
		return new ResponseEntity<>(characterService.getCharactersByName(name), HttpStatus.OK);
	}
	
	@GetMapping(params = "age")
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
	public ResponseEntity<List<Character>> getCharacterByAge(@RequestParam(name = "age") Integer age){
		return new ResponseEntity<>(characterService.getCharactersByAge(age), HttpStatus.OK);
	}
	
	@GetMapping(params = "weight")
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
	public ResponseEntity<List<Character>> getCharacterByWeight(@RequestParam(name = "weight") Double weight){
		return new ResponseEntity<>(characterService.getCharactersByWeight(weight), HttpStatus.OK);
	}
	@GetMapping(params = "movies")
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
	public ResponseEntity<List<Character>> getCharacterBySeriesAndFilms(@RequestParam(name = "movies") Long idMovie){
		return new ResponseEntity<>(characterService.getCharactersBySeriesAndFilms(idMovie), HttpStatus.OK);

	}

	@DeleteMapping(path = "{idCharacter}")
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")//Debería tener permiso solo el ADMIN
	public ResponseEntity<String> deleteCharater(@PathVariable("idCharacter") Long idCharacter){
		return new ResponseEntity<>(characterService.deleteCharacter(idCharacter), HttpStatus.OK);
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")//Debería tener permiso solo el ADMIN
	public ResponseEntity<Character> updateCharacter(@Valid @RequestBody CharacterRequestWithID character){
		return new ResponseEntity<>(characterService.updateCharacter(character), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")//Debería tener permiso solo el ADMIN
	public ResponseEntity<Character> addCharacter(@Valid @RequestBody CharacterRequest character){
		return new ResponseEntity<>(characterService.addCharacter(character), HttpStatus.CREATED);
	}
	

	
	
}
