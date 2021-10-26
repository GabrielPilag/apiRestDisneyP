package com.disney.apiRest2.seriesAndFilms;

public class SeriesAndFilmsUtils {

	public static SeriesAndFilmsView mapper(SeriesAndFilms s) {
		
		SeriesAndFilmsView seriesAndFilmsView = new SeriesAndFilmsView();
		seriesAndFilmsView.setImage(s.getImage());
		seriesAndFilmsView.setTitle(s.getTitle());
		seriesAndFilmsView.setCreationDate(s.getCreationDate());
		
		return seriesAndFilmsView;
	}
	
	public static int ascOrder(SeriesAndFilms obj1, SeriesAndFilms obj2) {
		return obj1.getCreationDate().compareTo(obj2.getCreationDate());
	}
	
	public static int descOrder(SeriesAndFilms obj1, SeriesAndFilms obj2) {
		return obj2.getCreationDate().compareTo(obj1.getCreationDate());
	}
	
}
 