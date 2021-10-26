package com.disney.apiRest2.genre;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name="genres")
public class Genre {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Lob
	private byte[] image;
	private String name;
	
	@JsonIgnoreProperties("characters")
	@OneToMany(mappedBy = "genre")
	private List<SeriesAndFilms> seriesAndFilms;
		
	
}
