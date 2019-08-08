package com.interview.serviceone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.serviceone.valueobject.StructureWorkForce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkForceOptimizerApplicationTests {

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getOptimizedWorkForceTestSet1() throws Exception {

		Integer[] rooms = {35,21,17,28};
		int senior = 10;
		int junior = 6;
		mockMvc.perform( MockMvcRequestBuilders
				.post("/workforce")
				.content(objectMapper.writeValueAsString(new StructureWorkForce(rooms, senior,junior)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$[0].senior", is(3)))
				.andExpect(jsonPath("$[0].junior", is(1)))
				.andExpect(jsonPath("$[1].senior", is(1)))
				.andExpect(jsonPath("$[1].junior", is(2)))
				.andExpect(jsonPath("$[2].senior", is(2)))
				.andExpect(jsonPath("$[2].junior", is(0)))
				.andExpect(jsonPath("$[3].senior", is(1)))
				.andExpect(jsonPath("$[3].junior", is(3)));
	}

	@Test
	public void getOptimizedWorkForceTestSet2() throws Exception {

		Integer[] rooms = {24,28};
		int senior = 11;
		int junior = 6;

		mockMvc.perform( MockMvcRequestBuilders
				.post("/workforce")
				.content(objectMapper.writeValueAsString(new StructureWorkForce(rooms, senior,junior)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$[0].senior", is(2)))
				.andExpect(jsonPath("$[0].junior", is(1)))
				.andExpect(jsonPath("$[1].senior", is(2)))
				.andExpect(jsonPath("$[1].junior", is(1)));
	}

	@Test
	public void getOptimizedWorkForceTestSet3() throws Exception {

		Integer[] rooms = IntStream.range(1,100).boxed().toArray(Integer[]::new);
		int senior = 10;
		int junior = 6;
		mockMvc.perform( MockMvcRequestBuilders
				.post("/workforce")
				.content(objectMapper.writeValueAsString(new StructureWorkForce(rooms, senior,junior)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void getOptimizedWorkForceTestInvalidSet1() throws Exception {

		Integer[] rooms = {101,100};
		int senior = 11;
		int junior = 6;

		mockMvc.perform( MockMvcRequestBuilders
				.post("/workforce")
				.content(objectMapper.writeValueAsString(new StructureWorkForce(rooms, senior,junior)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void getOptimizedWorkForceTestInvalidSet2() throws Exception {

		Integer[] rooms = {-101,-100};
		int senior = -11;
		int junior = -6;

		mockMvc.perform( MockMvcRequestBuilders
				.post("/workforce")
				.content(objectMapper.writeValueAsString(new StructureWorkForce(rooms, senior,junior)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}
}