package com.example.xyinc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.xyinc.model.Actor;
import com.example.xyinc.model.Category;
import com.example.xyinc.model.Movie;
import com.example.xyinc.repository.ActorDao;
import com.example.xyinc.repository.CategoryDao;
import com.example.xyinc.repository.MovieDao;

@Service
public class MovieService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private MovieDao dao;
	
	@Autowired
	CategoryDao categoryDao;

	@Autowired
	ActorDao actorDao;

	public ResponseEntity<Movie> createMovie(Movie movie) {

		if (movie.getActors() != null) {
			for (Actor actor : movie.getActors()) {
				if (actorDao.findOne(actor.getId()) == null) {
					logger.error("Error trying to crete movie. Actor " + actor.getId() + " not found");
					return ResponseEntity.notFound().build();
				}
			}
		}

		if (movie.getCategory() != null) {
			Category category = categoryDao.findOne(movie.getCategory().getId());
			
			if (category == null) {
				logger.error("Error trying to crete movie. Category " + movie.getCategory().getId() + " not found");
		        return ResponseEntity.notFound().build();
			}
		}

		movie = dao.save(movie);

	    return ResponseEntity.ok().body(movie);
	}

	public List<Movie> findAll() {
		return (List<Movie>) dao.findAll();
	}

	public ResponseEntity<Movie> findById(Long id) {
		Movie movie = dao.findOne(id);
	    if(movie == null) {
			logger.error("Error trying to find movie. Id " + id + " not found");
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(movie);
	}

	public ResponseEntity<Movie> update(Movie movie) {
		Movie dbMovie = dao.findOne(movie.getId());

		if (dbMovie == null) {
			logger.error("Error trying to update movie. Id " + movie.getId() + " not found");
	        return ResponseEntity.notFound().build();
		}

		dbMovie.setName(movie.getName());
		dbMovie.setDescription(movie.getDescription());
		dbMovie.setReleasingDate(movie.getReleasingDate());
		dbMovie.setBudget(movie.getBudget());
		dbMovie.setActors(movie.getActors());
		dbMovie = dao.save(dbMovie);

	    return ResponseEntity.ok().body(movie);
	}

	public ResponseEntity<Movie> delete(Long id) {
		Movie dbMovie = dao.findOne(id);

		if (dbMovie == null) {
			logger.error("Error trying to update movie. Id " + id + " not found");
	        return ResponseEntity.notFound().build();
		}

		dao.delete(dbMovie);
	    return ResponseEntity.ok().build();
	}
}
