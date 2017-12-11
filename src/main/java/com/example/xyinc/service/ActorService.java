package com.example.xyinc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.xyinc.model.Actor;
import com.example.xyinc.repository.ActorDao;

@Service
public class ActorService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ActorDao dao;
	
	public Actor createActor(Actor actor) {
		return dao.save(actor);
	}

	public List<Actor> findAll() {
		return (List<Actor>) dao.findAll();
	}

	public ResponseEntity<Actor> findById(Long id) {
		Actor actor = dao.findOne(id);
	    if(actor == null) {
			logger.error("Error trying to find actor. Id " + id + " not found");
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(actor);
	}

	public ResponseEntity<Actor> update(Actor actor) {
		Actor dbActor = dao.findOne(actor.getId());

		if (dbActor == null) {
			logger.error("Error trying to update actor. Id " + actor.getId() + " not found");
	        return ResponseEntity.notFound().build();
		}

		dbActor.setName(actor.getName());
		dbActor.setBirthDate(actor.getBirthDate());
		dbActor.setGender(actor.getGender());
		dbActor.setNationality(actor.getNationality());
		dbActor = dao.save(dbActor);

	    return ResponseEntity.ok().body(actor);
	}

	public ResponseEntity<Actor> delete(Long id) throws Exception {

		Actor dbActor = dao.findOne(id);

		if (dbActor == null) {
			logger.error("Error trying to delete actor. Id " + id + " not found");
	        return ResponseEntity.notFound().build();
		}

		dao.delete(dbActor);
	    return ResponseEntity.ok().build();
	}
}
