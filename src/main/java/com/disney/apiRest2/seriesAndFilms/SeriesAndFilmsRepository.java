package com.disney.apiRest2.seriesAndFilms;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesAndFilmsRepository extends JpaRepository<SeriesAndFilms, Long>{
	
	@Query(value ="SELECT * FROM series_and_films WHERE title LIKE %?1% " , nativeQuery = true)
	List<SeriesAndFilms> findAllByTitle(String title);

}
