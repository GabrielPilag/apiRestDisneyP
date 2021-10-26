package com.disney.apiRest2.seriesAndFilms;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SeriesAndFilmsRequest {

	@NotNull(message = "cannot be null")
	private byte[] image;
	
	@NotBlank(message = "cannot be blank")
	private String title;
	
	@NotNull(message = "cannot be null")
	private LocalDate creationDate;
	
	@NotNull(message = "cannot be null")
	@Min(value = 1, message = "must be greater than 1")
	@Max(value = 5, message = "must be less than 5")
	private Integer review;
	
	@NotNull(message = "cannot be null")
	private Long idGenre;

}
