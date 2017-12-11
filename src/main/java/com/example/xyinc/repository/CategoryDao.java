package com.example.xyinc.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.xyinc.model.Category;

public interface CategoryDao extends CrudRepository<Category, Long> {
}
