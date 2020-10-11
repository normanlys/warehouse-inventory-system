package com.warehouse.rest;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.warehouse.rest.model.ProductCount;
import com.warehouse.rest.model.ProductEntry;
import com.warehouse.rest.model.ProductEntryRepository;
import com.warehouse.rest.model.ProductRepository;

@AutoConfigureMockMvc
@SpringBootTest
class RestApplicationTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ProductEntryRepository entryRepository;

	@Autowired
	private ProductRepository productRepository;

	@BeforeEach
	public void setUp() {
		entryRepository.deleteAll();
		productRepository.deleteAll();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void getGreetings() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/")).andExpect(status().isOk())
				.andExpect(content().string(equalTo("hello world")));
	};

	@Test
	public void getProductCount_Empty_Success() throws Exception
	{
		final String productCode = "p0";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/product-count/" + productCode).accept("application/json"))
			.andExpect(status().isOk())
			.andReturn();
		
		Gson gson = new Gson();
		ProductCount[] actual = gson.fromJson(result.getResponse().getContentAsString(), ProductCount[].class);

		assertEquals(0, actual.length);
	}

	@Test
	public void getProductCount_TwoCounts_Success() throws Exception
	{
		final String productCode = "p0";
		final String location0 = "loc0";
		final String location1 = "loc1";
		ProductEntry entry0 = new ProductEntry(productCode, "loc0", 1);
		ProductEntry entry1 = new ProductEntry(productCode, "loc1", 1);
		entryRepository.save(entry0);
		entryRepository.save(entry1);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/product-count/" + productCode).accept("application/json"))
			.andExpect(status().isOk())
			.andReturn();
		
		Gson gson = new Gson();
		ProductCount[] actual = gson.fromJson(result.getResponse().getContentAsString(), ProductCount[].class);

		assertEquals(2, actual.length);

		assertEquals(1, actual[0].weight);
		assertEquals(location0, actual[0].location);

		assertEquals(1, actual[1].weight);
		assertEquals(location1, actual[1].location);
	}


}
