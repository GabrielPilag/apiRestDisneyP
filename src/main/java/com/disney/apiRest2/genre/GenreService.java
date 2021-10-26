package com.disney.apiRest2.genre;

import org.springframework.stereotype.Service;

import com.disney.apiRest2.handlers.NotFoundException;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class GenreService {
	
	private final GenreRepository genreRepository;
	
	public Genre findById(Long idGenre) {
		return genreRepository.findById(idGenre).orElseThrow(()->new NotFoundException(String.format("genre with id %s does not exists", idGenre)));
	}
	
}
