package com.example.xyinc.xyinc;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.xyinc.controller.AwardController;
import com.example.xyinc.model.Award;
import com.example.xyinc.model.Movie;
import com.example.xyinc.repository.MovieDao;
import com.example.xyinc.service.AwardService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AwardControllerTest extends XyIncApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private AwardController awardController;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(awardController).build();
	}

	@Test
	public void testGETAwardController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/award"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGETidAwardController() throws Exception {

		Award award = new Award();
		award.setName("GET_AWARD");
	    award = service.createAward(award);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/award/" + award.getId()))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPOSTAwardController() throws Exception {

		Movie movie = new Movie();
		movie.setName("POST_AWARD_MOVIE");
		movie = movieDao.save(movie);

		Award award = new Award();
		award.setName("POST_AWARD");
	    award.setAwardDate(new Date());
	    award.setMovie(movie);

	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    String json = gson.toJson(award);

		this.mockMvc.perform(MockMvcRequestBuilders.post("/award")
		    .contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPutAwardController() throws Exception {

		Award award = new Award();
		award.setName("PUT_AWARD");
	    award = service.createAward(award);

		Movie movie = new Movie();
		movie.setName("PUT_AWARD_MOVIE");
		movie = movieDao.save(movie);

		award.setName("PUT_AWARD");
	    award.setAwardDate(new Date());
	    award.setMovie(movie);
	    
	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    String json = gson.toJson(award);

		this.mockMvc.perform(MockMvcRequestBuilders.put("/award")
		    .contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testDELETEAwardController() throws Exception {

		Award award = new Award();
		award.setName("DELETE_AWARD");
	    award = service.createAward(award);

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/award/" + award.getId()))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	
	@Autowired 
	AwardService service;
	
	@Autowired
	MovieDao movieDao;
}
