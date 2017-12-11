package com.example.xyinc.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.xyinc.model.Actor;

public interface ActorDao extends CrudRepository<Actor, Long> {
}
