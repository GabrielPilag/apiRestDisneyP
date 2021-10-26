package com.disney.apiRest2.character;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long>{
	
	@Query(value ="SELECT * FROM characters WHERE name LIKE %?1% " , nativeQuery = true)
	List<Character> findAllByName(String name);
	
	List<Character> findAllByAge(Integer age);
	
	List<Character> findAllByWeight(Double weight);
	
}

