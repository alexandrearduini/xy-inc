package com.example.xyinc.xyinc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.xyinc.controller.CategoryController;
import com.example.xyinc.model.Category;
import com.example.xyinc.service.CategoryService;
import com.google.gson.Gson;

public class CategoryControllerTest extends XyIncApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private CategoryController categoryController;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}

	@Test
	public void testGETCategoryController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/category"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGETidCategoryController() throws Exception {

		Category cat = new Category();
		cat.setName("GET_CATEGORY");
	    cat.setDescription("GET_DESCRIPTION");
	    cat = service.createCategory(cat);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/category/" + cat.getId()))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPOSTCategortController() throws Exception {

		Category cat = new Category();
		cat.setName("POST_CATEGORY");
	    cat.setDescription("POST_DESCRIPTION");
	    String json = new Gson().toJson(cat);

		this.mockMvc.perform(MockMvcRequestBuilders.post("/category")
		    .contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testPutCategortController() throws Exception {

		Category cat = new Category();
		cat.setName("CATEGORY");

	    cat = service.createCategory(cat);

	    cat.setDescription("DESCRIPTION");
	    Gson gson = new Gson();
	    String json = gson.toJson(cat);

		this.mockMvc.perform(MockMvcRequestBuilders.put("/category")
		    .contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testDELETECategortController() throws Exception {

		Category cat = new Category();
		cat.setName("DELETE_CATEGORY");
	    cat.setDescription("DELETE_DESCRIPTION");
	    cat = service.createCategory(cat);

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/category/" + cat.getId()))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	
	@Autowired 
	CategoryService service;
}
