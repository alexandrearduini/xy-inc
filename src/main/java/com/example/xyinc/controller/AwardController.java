package com.example.xyinc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.xyinc.model.Award;
import com.example.xyinc.service.AwardService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class AwardController {

	@JsonView(value=Award.class)
	@RequestMapping(value = "/award", method = RequestMethod.POST)
    public Award insert(@RequestBody Award award) throws Exception {

		if (award.getName() == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.createAward(award);
    }

	@JsonView(value=Award.class)
	@RequestMapping(value = "/award", method = RequestMethod.GET)
    public List<Award> findAll() throws Exception {

		return service.findAll(); 	
    }

	@JsonView(value=Award.class)
	@RequestMapping(value = "/award/{id}", method = RequestMethod.GET)
    public ResponseEntity<Award> findById(@PathVariable Long id) throws Exception {

		if (id == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.findById(id); 	
    }

	@JsonView(value=Award.class)
	@RequestMapping(value = "/award", method = RequestMethod.PUT)
    public ResponseEntity<Award> update(@RequestBody Award award) throws Exception {

		if (award.getId() == null || award.getName() == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.update(award); 	
    }

	@JsonView(value=Award.class)
	@RequestMapping(value = "/award/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Award> delete(@PathVariable Long id) throws Exception {

		if (id == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.delete(id); 	
    }

	@Autowired
	AwardService service;
}
