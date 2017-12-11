package com.example.xyinc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.xyinc.model.Category;
import com.example.xyinc.model.Movie;
import com.example.xyinc.repository.CategoryDao;
import com.example.xyinc.repository.MovieDao;

@Service
public class CategoryService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private CategoryDao dao;
	
	@Autowired
	private MovieDao movieDao;

	public Category createCategory(Category category) {
		return dao.save(category);
	}

	public List<Category> findAll() {
		return (List<Category>) dao.findAll();
	}

	public ResponseEntity<Category> findById(Long id) {
		Category category = dao.findOne(id);
	    if(category == null) {
			logger.error("Error trying to find category. Id " + id + " not found");
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(category);
	}

	public ResponseEntity<Category> update(Category category) {
		Category dbCategory = dao.findOne(category.getId());

		if (dbCategory == null) {
			logger.error("Error trying to update category. Id " + category.getId() + " not found");
	        return ResponseEntity.notFound().build();
		}

		dbCategory.setName(category.getName());
		dbCategory.setDescription(category.getDescription());
		dbCategory = dao.save(dbCategory);

	    return ResponseEntity.ok().body(category);
	}

	public ResponseEntity<Category> delete(Long id) throws Exception {

		List<Movie> movies = movieDao.findByCategoryId(id);
		
		if (movies != null && !movies.isEmpty()) {
			throw new Exception("There are movies referencing the category " + id);
		}

		Category dbCategory = dao.findOne(id);

		if (dbCategory == null) {
			logger.error("Error trying to delete category. Id " + id + " not found");
	        return ResponseEntity.notFound().build();
		}

		dao.delete(dbCategory);
	    return ResponseEntity.ok().build();
	}
}
