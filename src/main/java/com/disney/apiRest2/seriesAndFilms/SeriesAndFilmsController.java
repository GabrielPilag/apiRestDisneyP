package com.disney.apiRest2.seriesAndFilms;


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
@RequestMapping("/movies")
@AllArgsConstructor
public class SeriesAndFilmsController {
	
	private final SeriesAndFilmsService seriesAndFilmsService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
	public ResponseEntity<List<SeriesAndFilmsView>> getSeriesAndFilms(){
		System.out.println("paso2");
		return new ResponseEntity<>(seriesAndFilmsService.getAllSeriesAndFilms(), HttpStatus.OK);
	}
	
	
	@GetMapping(params = {"title"})
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
	public ResponseEntity<List<SeriesAndFilms>> getSeriesAndFilmsByName
												(@RequestParam(name = "title") String title, 
												 @RequestParam(name = "order", required = false) String order){
		
		return new ResponseEntity<>(seriesAndFilmsService.getSeriesAndFilmsByName(title, order), HttpStatus.OK);
		
	}
	
	
	@GetMapping(params = {"genre"})
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
	public ResponseEntity<List<SeriesAndFilms>> getSeriesAndFilmsByGenre
												(@RequestParam(name = "genre") Long idGenre, 
												 @RequestParam(name = "order", required = false) String order){
		return new ResponseEntity<>(seriesAndFilmsService.getSeriesAndFilmsByGenre(idGenre, order), HttpStatus.OK);
		
	}
	
	
	@DeleteMapping(path = "{idSeriesAndFilms}")
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")//Debería tener permiso solo el ADMIN
	public ResponseEntity<String> deleteSeriesAndFilms(@PathVariable("idSeriesAndFilms") Long idSeriesAndFilms){
		return new ResponseEntity<>(seriesAndFilmsService.deleteSeriesAndFilms(idSeriesAndFilms), HttpStatus.OK);
	}
	
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")//Debería tener permiso solo el ADMIN
	public ResponseEntity<SeriesAndFilms> updateSeriesAndFilms(@Valid @RequestBody SeriesAndFilmsRequestWithId seriesAndFilms){
		return new ResponseEntity<>(seriesAndFilmsService.updateSeriesAndFilms(seriesAndFilms), HttpStatus.OK);
	}
	
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")//Debería tener permiso solo el ADMIN
	public ResponseEntity<SeriesAndFilms> addSeriesAndFilms(@Valid @RequestBody SeriesAndFilmsRequest seriesAndFilmsRequest){
		return new ResponseEntity<>(seriesAndFilmsService.addSeriesAndFilms(seriesAndFilmsRequest), HttpStatus.CREATED);
	}
	
	
	
	
}
