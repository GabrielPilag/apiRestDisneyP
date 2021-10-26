package com.disney.apiRest2.seriesAndFilms;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeriesAndFilmsView {

	private byte[] image;
	private String title;
	private LocalDate creationDate;

	
}
