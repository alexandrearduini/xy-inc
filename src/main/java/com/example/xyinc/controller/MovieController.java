package com.example.xyinc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.xyinc.model.Movie;
import com.example.xyinc.service.MovieService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class MovieController {

	@JsonView(Movie.class)
	@RequestMapping(value = "/movie", method = RequestMethod.POST)
    public ResponseEntity<Movie> insert(@RequestBody Movie movie) throws Exception {

		if (movie.getName() == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.createMovie(movie); 	
    }

	@JsonView(Movie.class)
	@RequestMapping(value = "/movie", method = RequestMethod.GET)
    public List<Movie> findAll() throws Exception {

		return service.findAll(); 	
    }

	@JsonView(Movie.class)
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public ResponseEntity<Movie> findById(@PathVariable Long id) throws Exception {

		if (id == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.findById(id); 	
    }

	@JsonView(Movie.class)
	@RequestMapping(value = "/movie", method = RequestMethod.PUT)
    public ResponseEntity<Movie> update(@RequestBody Movie movie) throws Exception {

		if (movie.getId() == null || movie.getName() == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.update(movie); 	
    }

	@JsonView(Movie.class)
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Movie> delete(@PathVariable Long id) throws Exception {

		if (id == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.delete(id); 	
    }

	@Autowired
	MovieService service;
}
