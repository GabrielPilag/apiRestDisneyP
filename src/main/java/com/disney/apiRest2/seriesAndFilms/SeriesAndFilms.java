package com.disney.apiRest2.seriesAndFilms;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.disney.apiRest2.character.Character;
import com.disney.apiRest2.genre.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="series_and_films")
public class SeriesAndFilms {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Lob
	private byte[] image;
	private String title;
	private LocalDate creationDate;
	private Integer review;
	
	@ManyToOne
	@JoinColumn(name="idGenre")
	@JsonIgnoreProperties("seriesAndFilms")
	private Genre genre;
	
	@JsonIgnoreProperties("seriesAndFilms")
	@ManyToMany(mappedBy = "seriesAndFilms")
	private List<Character> characters;
	
}
