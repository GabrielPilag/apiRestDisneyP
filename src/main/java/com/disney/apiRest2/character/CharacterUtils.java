package com.disney.apiRest2.character;

public class CharacterUtils {

	public static CharacterView mapper(Character c) {
		
		CharacterView characterView = new CharacterView();
		characterView.setImage(c.getImage());
		characterView.setName(c.getName());
		return characterView;
		
	}
	
	
	
}
