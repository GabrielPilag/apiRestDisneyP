package com.disney.apiRest2.seriesAndFilms;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.disney.apiRest2.genre.Genre;
import com.disney.apiRest2.genre.GenreService;
import com.disney.apiRest2.handlers.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeriesAndFilmsService {
	
	private final SeriesAndFilmsRepository seriesAndFilmsRepository;
	private final GenreService genreService;
	
	
	public List<SeriesAndFilmsView> getAllSeriesAndFilms() { 
		// TODO Auto-generated method stub
	
		return seriesAndFilmsRepository.findAll()
								.stream()
								.map(SeriesAndFilmsUtils::mapper)
								.collect(Collectors.toList());
	}
	
	
	public List<SeriesAndFilms> getSeriesAndFilmsByName(String title, String order) {
		
		List<SeriesAndFilms> seriesAndFilms = seriesAndFilmsRepository.findAllByTitle(title);
		if (seriesAndFilms.isEmpty()) {
			throw new NotFoundException(String.format("Series or films with title %s does not exists", title));
		}
		if(order!= null) {
			if (order.equals("ASC")) {
				seriesAndFilms = seriesAndFilms.stream()
												.sorted(SeriesAndFilmsUtils::ascOrder)
												.collect(Collectors.toList());

			}else if(order.equals("DESC")) {
				seriesAndFilms = seriesAndFilms.stream()
												.sorted(SeriesAndFilmsUtils::descOrder)
												.collect(Collectors.toList());
			}
		}
		
		return seriesAndFilms;
	}
	
	
	public List<SeriesAndFilms> getSeriesAndFilmsByGenre(Long idGenre, String order) {
		
		List<SeriesAndFilms> seriesAndFilms = genreService.findById(idGenre).getSeriesAndFilms();
		
		if(seriesAndFilms.isEmpty()) {
			throw new NotFoundException(String.format("Series or films with genre %s does not exists", idGenre));
		}
		
		if(order!= null) {
			if (order.equals("ASC")) {
				seriesAndFilms = seriesAndFilms.stream()
												.sorted(SeriesAndFilmsUtils::ascOrder)
												.collect(Collectors.toList());

			}else if(order.equals("DESC")) {
				seriesAndFilms = seriesAndFilms.stream()
												.sorted(SeriesAndFilmsUtils::descOrder)
												.collect(Collectors.toList());
			}
		}
		return seriesAndFilms;
	}
	
	
	public String deleteSeriesAndFilms(Long idSeriesAndFilms){
		
		SeriesAndFilms SeriesAndFilms = seriesAndFilmsRepository.findById(idSeriesAndFilms)
									.orElseThrow(()->new NotFoundException(String.format("series or films with id %s does not exists", idSeriesAndFilms)));
		seriesAndFilmsRepository.delete(SeriesAndFilms); 
		return String.format("series or films with id %s was deleted", idSeriesAndFilms);
	}
	

	@Transactional
	public SeriesAndFilms updateSeriesAndFilms(SeriesAndFilmsRequestWithId seriesAndFilms) {
		
		SeriesAndFilms seriesAndFilmsToUptdate = seriesAndFilmsRepository.findById(seriesAndFilms.getId())
				.orElseThrow(()->new NotFoundException(String.format("character with id %s does not exists", seriesAndFilms.getId())));
		
		seriesAndFilmsToUptdate.setImage(seriesAndFilms.getImage());
		seriesAndFilmsToUptdate.setTitle(seriesAndFilms.getTitle());
		seriesAndFilmsToUptdate.setCreationDate(seriesAndFilms.getCreationDate());
		seriesAndFilmsToUptdate.setReview(seriesAndFilms.getReview());
		
		Genre genre = genreService.findById(seriesAndFilms.getIdGenre());
		seriesAndFilmsToUptdate.setGenre(genre);

		return seriesAndFilmsToUptdate;
		
	}
	
	
	@Transactional
	public SeriesAndFilms addSeriesAndFilms(SeriesAndFilmsRequest seriesAndFilmsRequest) {

		SeriesAndFilms seriesAndFilm = new SeriesAndFilms(); 
		
		seriesAndFilm.setImage(seriesAndFilmsRequest.getImage());
		seriesAndFilm.setTitle(seriesAndFilmsRequest.getTitle());
		seriesAndFilm.setCreationDate(seriesAndFilmsRequest.getCreationDate());
		seriesAndFilm.setReview(seriesAndFilmsRequest.getReview());
		
		Genre genre = genreService.findById(seriesAndFilmsRequest.getIdGenre());
		seriesAndFilm.setGenre(genre);
		
		return seriesAndFilmsRepository.save(seriesAndFilm);
	}

	
	
	
	
	
	

}

