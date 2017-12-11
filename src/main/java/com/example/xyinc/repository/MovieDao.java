package com.example.xyinc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.xyinc.model.Movie;

public interface MovieDao extends CrudRepository<Movie, Long> {
	List<Movie> findByCategoryId(Long id);
}
