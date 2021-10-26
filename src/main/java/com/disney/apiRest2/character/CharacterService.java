package com.disney.apiRest2.character;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.disney.apiRest2.handlers.NotFoundException;
import com.disney.apiRest2.seriesAndFilms.SeriesAndFilms;
import com.disney.apiRest2.seriesAndFilms.SeriesAndFilmsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CharacterService {
	
	private final CharacterRepository characterRepository;
	private final SeriesAndFilmsRepository seriesAndFilmsRepository;
	
	public List<CharacterView> getCharacters() {
		// TODO Auto-generated method stub
	
		return characterRepository.findAll()
								.stream()
								.map(CharacterUtils::mapper)
								.collect(Collectors.toList());
	}

	
	public String deleteCharacter(Long idCharacter){
		
		Character character = characterRepository.findById(idCharacter)
									.orElseThrow(()->new NotFoundException(String.format("character with id %s does not exists", idCharacter)));
		
		characterRepository.delete(character);
		
		return String.format("character with id %s was deleted", idCharacter);
	}
	
	
	@Transactional
	public Character updateCharacter(CharacterRequestWithID character) {

		Character characterToUptdate = characterRepository.findById(character.getId())
									.orElseThrow(()->new NotFoundException(String.format("character with id %s does not exists", character.getId())));
		
		if(character.getImage() != null) {
			characterToUptdate.setImage(character.getImage());
		}
		characterToUptdate.setName(character.getName());
		characterToUptdate.setAge(character.getAge());
		characterToUptdate.setWeight(character.getWeight());
		characterToUptdate.setHistory(character.getHistory());
		
		return characterToUptdate;
	}

	@Transactional
	public Character addCharacter(CharacterRequest characterRequest) {
	
		Character character = new Character();
		
		character.setImage(characterRequest.getImage());
		character.setName(characterRequest.getName());
		character.setAge(characterRequest.getAge());
		character.setWeight(characterRequest.getWeight());
		character.setHistory(characterRequest.getHistory());
		
		return characterRepository.save(character);
	}

	
	public List<Character> getCharactersByName(String name) {

		List<Character> characters = characterRepository.findAllByName(name);
		if(characters.isEmpty()) {
			throw new NotFoundException(String.format("character with name %s does not exists", name));
		}
		return characters;
	}

	
	public List<Character> getCharactersByAge(Integer age) {
		
		List<Character> characters = characterRepository.findAllByAge(age);
		if(characters.isEmpty()) {
			throw new NotFoundException(String.format("character with age %s does not exists", age));
		}
		return characters;
	}

	
	public List<Character> getCharactersByWeight(Double weight) {
		
		List<Character> characters = characterRepository.findAllByWeight(weight);
		if(characters.isEmpty()) {
			throw new NotFoundException(String.format("character with weight %s does not exists", weight));
		}
		return characters;
	}

	
	public List<Character> getCharactersBySeriesAndFilms(Long idMovie) {
		
		
		SeriesAndFilms seriesAndFilms = seriesAndFilmsRepository.findById(idMovie)
												.orElseThrow(()->new NotFoundException(String.format("Series or films with id %s does not exists", idMovie)));
		
		return seriesAndFilms.getCharacters();
	}
	
	
	
	
	

}

