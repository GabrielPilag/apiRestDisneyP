package com.disney.apiRest2.character;

import java.util.List;


import javax.persistence.*;

import com.disney.apiRest2.character.Character;
import com.disney.apiRest2.seriesAndFilms.SeriesAndFilms;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="characters")
public class Character {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Lob
	@Column(nullable = false)
	
	private byte[] image;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Integer age;
	@Column(nullable = false)
	private Double 	weight;
	@Column(nullable = false)
	private String history;
	
	@JsonIgnoreProperties("characters")
	@ManyToMany()
	@JoinTable(
			name = "relations_characters_series_films",
			joinColumns = {@JoinColumn(name = "id_character")},
			inverseJoinColumns = {@JoinColumn(name="id_series_and_films")}
			)
	private List<SeriesAndFilms> seriesAndFilms;

	
	
}
