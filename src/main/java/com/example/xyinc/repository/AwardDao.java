package com.example.xyinc.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.xyinc.model.Award;

public interface AwardDao extends CrudRepository<Award, Long> {
}
