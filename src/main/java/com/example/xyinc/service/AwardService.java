package com.example.xyinc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.xyinc.model.Award;
import com.example.xyinc.repository.AwardDao;

@Service
public class AwardService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private AwardDao dao;
	
	public Award createAward(Award award) {
		return dao.save(award);
	}

	public List<Award> findAll() {
		return (List<Award>) dao.findAll();
	}

	public ResponseEntity<Award> findById(Long id) {
		Award award = dao.findOne(id);
	    if(award == null) {
			logger.error("Error trying to find award. Id " + id + " not found");
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(award);
	}

	public ResponseEntity<Award> update(Award award) {
		Award dbAward = dao.findOne(award.getId());

		if (dbAward == null) {
			logger.error("Error trying to update award. Id " + award.getId() + " not found");
	        return ResponseEntity.notFound().build();
		}

		dbAward.setName(award.getName());
		dbAward.setAwardDate(award.getAwardDate());
		dbAward.setMovie(award.getMovie());
		dbAward = dao.save(dbAward);

	    return ResponseEntity.ok().body(award);
	}

	public ResponseEntity<Award> delete(Long id) throws Exception {

		Award dbAward = dao.findOne(id);

		if (dbAward == null) {
			logger.error("Error trying to delete award. Id " + id + " not found");
	        return ResponseEntity.notFound().build();
		}

		dao.delete(dbAward);
	    return ResponseEntity.ok().build();
	}
}
