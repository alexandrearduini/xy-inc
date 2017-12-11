package com.example.xyinc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.xyinc.model.Category;
import com.example.xyinc.service.CategoryService;

@RestController
public class CategoryController {

	@RequestMapping(value = "/category", method = RequestMethod.POST)
    public Category insert(@RequestBody Category category) throws Exception {

		if (category.getName() == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.createCategory(category); 	
    }

	@RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<Category> findAll() throws Exception {

		return service.findAll(); 	
    }

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> findById(@PathVariable Long id) throws Exception {

		if (id == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.findById(id); 	
    }

	@RequestMapping(value = "/category", method = RequestMethod.PUT)
    public ResponseEntity<Category> update(@RequestBody Category category) throws Exception {

		if (category.getId() == null || category.getName() == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.update(category); 	
    }

	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> delete(@PathVariable Long id) throws Exception {

		if (id == null) {			
			throw new Exception("Required parameters not found");
		}

		return service.delete(id); 	
    }

	@Autowired
	CategoryService service;
}
