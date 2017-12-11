package com.example.xyinc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.xyinc.model.Actor;
import com.example.xyinc.model.Movie;
import com.example.xyinc.service.ActorService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ActorController {

	@JsonView(Movie.class)
	@RequestMapping(value = "/actor", method = RequestMethod.POST)
    public Actor insert(@RequestBody Actor actor) throws Exception {

		if (actor.getName() == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.createActor(actor);
    }

	@JsonView(Movie.class)
	@RequestMapping(value = "/actor", method = RequestMethod.GET)
    public List<Actor> findAll() throws Exception {

		return service.findAll(); 	
    }

	@JsonView(Movie.class)
	@RequestMapping(value = "/actor/{id}", method = RequestMethod.GET)
    public ResponseEntity<Actor> findById(@PathVariable Long id) throws Exception {

		if (id == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.findById(id); 	
    }

	@JsonView(Movie.class)
	@RequestMapping(value = "/actor", method = RequestMethod.PUT)
    public ResponseEntity<Actor> update(@RequestBody Actor actor) throws Exception {

		if (actor.getId() == null || actor.getName() == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.update(actor); 	
    }

	@JsonView(Movie.class)
	@RequestMapping(value = "/actor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Actor> delete(@PathVariable Long id) throws Exception {

		if (id == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.delete(id); 	
    }

	@Autowired
	ActorService service;
}
