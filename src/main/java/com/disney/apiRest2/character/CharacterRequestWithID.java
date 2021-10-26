package com.disney.apiRest2.character;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterRequestWithID {
	
	@NotNull(message = "cannot be null")
	private Long id;
	
	private byte[] image;
	
	@NotBlank(message = "cannot be blank")
	private String name;
	
	@NotNull(message = "cannot be null")
	@Min(value = 0, message = "cannot be negative")
	private Integer age;
	
	@NotNull(message = "cannot be null")
	@Min(value = 0, message = "cannot be negative")
	private Double 	weight;
	
	@NotBlank(message = "cannot be blank")
	private String history;

}
