package com.example.xyinc.xyinc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.xyinc.controller.MovieController;
import com.example.xyinc.model.Actor;
import com.example.xyinc.model.Category;
import com.example.xyinc.model.Movie;
import com.example.xyinc.repository.ActorDao;
import com.example.xyinc.repository.CategoryDao;
import com.example.xyinc.repository.MovieDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MovieControllerTest extends XyIncApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private MovieController movieController;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
	}

	@Test
	public void testGETMovieController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/movie"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGETidMovieController() throws Exception {

		Movie movie = new Movie();
		movie.setName("GET_MOVIE");
	    movie = dao.save(movie);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/movie/" + movie.getId()))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPOSTMovieController() throws Exception {

		Category cat = new Category();
		cat.setName("PUT_MOVIE_CATEGORY");
		cat = categoryDao.save(cat);
		
		Actor actor1 = new Actor();
		actor1.setName("PUT_MOVIE_ACTOR_1");
		actor1 = actorDao.save(actor1);

		Actor actor2 = new Actor();
		actor2.setName("PUT_MOVIE_ACTOR_2");
		actor2 = actorDao.save(actor2);
		
		Movie movie = new Movie();
		movie.setName("PUT_ACTOR");
	    movie = dao.save(movie);

	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    movie.setReleasingDate(new Date());
	    movie.setBudget(10000.56);
	    movie.setDescription("PUT_DESCRIPTION");
	    movie.setCategory(cat);
	    
	    List<Actor> actors = new ArrayList<Actor>();
	    actors.add(actor1);
	    actors.add(actor2);
	    movie.setActors(actors);

	    String json = gson.toJson(movie);

		this.mockMvc.perform(MockMvcRequestBuilders.post("/movie")
		    .contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPutMovieController() throws Exception {

		Category cat = new Category();
		cat.setName("PUT_MOVIE_CATEGORY");
		cat = categoryDao.save(cat);
		
		Actor actor1 = new Actor();
		actor1.setName("PUT_MOVIE_ACTOR_1");
		actor1 = actorDao.save(actor1);

		Actor actor2 = new Actor();
		actor2.setName("PUT_MOVIE_ACTOR_2");
		actor2 = actorDao.save(actor2);
		
		Movie movie = new Movie();
		movie.setName("PUT_MOVIE");
	    movie = dao.save(movie);

	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    movie.setReleasingDate(new Date());
	    movie.setBudget(10000.56);
	    movie.setDescription("PUT_MOVIE_DESCRIPTION");
	    movie.setCategory(cat);
	    
	    List<Actor> actors = new ArrayList<Actor>();
	    actors.add(actor1);
	    actors.add(actor2);
	    movie.setActors(actors);

	    String json = gson.toJson(movie);

		this.mockMvc.perform(MockMvcRequestBuilders.put("/movie")
		    .contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testDELETEMovieController() throws Exception {

		Movie movie = new Movie();
		movie.setName("DELETE_MOVIE");
	    movie = dao.save(movie);

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/movie/" + movie.getId()))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	
	@Autowired 
	MovieDao dao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ActorDao actorDao;
}
