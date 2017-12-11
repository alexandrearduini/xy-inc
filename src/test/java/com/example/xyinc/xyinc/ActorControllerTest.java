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

import com.example.xyinc.controller.ActorController;
import com.example.xyinc.enums.GenderEnum;
import com.example.xyinc.model.Actor;
import com.example.xyinc.service.ActorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ActorControllerTest extends XyIncApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private ActorController actorController;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(actorController).build();
	}

	@Test
	public void testGETActorController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/actor"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGETidActorController() throws Exception {

		Actor actor = new Actor();
		actor.setName("GET_ACTOR");
	    actor = service.createActor(actor);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/actor/" + actor.getId()))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPOSTActorController() throws Exception {

		Actor actor = new Actor();
		actor.setName("POST_ACTOR");
	    actor.setBirthDate(new Date());
	    actor.setGender(GenderEnum.M);
	    actor.setNationality("PUT_NATIONALITY");

	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    String json = gson.toJson(actor);

		this.mockMvc.perform(MockMvcRequestBuilders.post("/actor")
		    .contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPutActorController() throws Exception {

		Actor actor = new Actor();
		actor.setName("PUT_ACTOR");
	    actor = service.createActor(actor);

	    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    actor.setBirthDate(new Date());
	    actor.setGender(GenderEnum.M);
	    actor.setNationality("PUT_NATIONALITY");
	    String json = gson.toJson(actor);

		this.mockMvc.perform(MockMvcRequestBuilders.put("/actor")
		    .contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testDELETEActorController() throws Exception {

		Actor actor = new Actor();
		actor.setName("DELETE_ACTOR");
	    actor = service.createActor(actor);

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/actor/" + actor.getId()))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	
	@Autowired 
	ActorService service;
}
