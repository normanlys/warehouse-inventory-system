package com.warehouse.rest;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.internal.Iterables;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;
import com.warehouse.rest.model.*;

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

	private <T> Integer sizeOfIterable(Iterable<T> iterable) 
	{
		Integer counter = 0;
		for (T i: iterable) {
			counter++;
		}
		return counter;
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

	@Test
	public void createProductEntry_NoEntry_Success() throws Exception
	{
		final ProductEntryUpdate[] updates = {};
		final Gson gson = new Gson();
		final String body = gson.toJson(updates);

		mvc.perform(MockMvcRequestBuilders.put("/api/product-entry").accept("application/json").contentType("application/json").content(body))
			.andExpect(status().isOk());

		Iterable<ProductEntry> entries = entryRepository.findAll();
		assertTrue(!entries.iterator().hasNext());

		Iterable<Product> products = productRepository.findAll();
		assertTrue(!products.iterator().hasNext());
	}

	@Test
	public void createProductEntry_OneEntry_Success() throws Exception
	{
		final String productCode = "p0";
		final String location = "loc";
		final String name = "name";
		final Integer weight = 2;

		final ProductEntryUpdate[] updates = { new ProductEntryUpdate(productCode, location, name, weight) };
		final Gson gson = new Gson();
		final String body = gson.toJson(updates);

		mvc.perform(MockMvcRequestBuilders.put("/api/product-entry").accept("application/json").contentType("application/json").content(body))
			.andExpect(status().isOk());

		Iterable<ProductEntry> entries = entryRepository.findAll();
		assertEquals(1, sizeOfIterable(entries));

		ProductEntryId entryId = new ProductEntryId(productCode, location);
		Optional<ProductEntry> entry = entryRepository.findById(entryId);

		assertTrue(entry.isPresent());
		assertEquals(productCode, entry.get().code);
		assertEquals(location, entry.get().location);
		assertEquals(weight, entry.get().weight);

		Iterable<Product> products = productRepository.findAll();
		assertEquals(1, sizeOfIterable(products));

		Optional<Product> product = productRepository.findById(productCode);
		assertTrue(product.isPresent());
		assertEquals(name, product.get().name);
	}

	@Test
	public void createProductEntry_MultipleSameEntries_Success() throws Exception
	{
		final String productCode = "p0";
		final String location = "loc";
		final String name = "name";
		final Integer weight = 2;

		final Integer totalWeight = 2*3;
		final Integer numberOfEntries = 1;
		final Integer numberOfProducts = 1;

		final ProductEntryUpdate[] updates = { 
			new ProductEntryUpdate(productCode, location, name, weight),
			new ProductEntryUpdate(productCode, location, name, weight),
			new ProductEntryUpdate(productCode, location, name, weight),
		};
		final Gson gson = new Gson();
		final String body = gson.toJson(updates);

		mvc.perform(MockMvcRequestBuilders.put("/api/product-entry").accept("application/json").contentType("application/json").content(body))
			.andExpect(status().isOk());

		Iterable<ProductEntry> entries = entryRepository.findAll();
		assertEquals(numberOfEntries, sizeOfIterable(entries));

		ProductEntryId entryId = new ProductEntryId(productCode, location);
		Optional<ProductEntry> entry = entryRepository.findById(entryId);

		assertTrue(entry.isPresent());
		assertEquals(productCode, entry.get().code);
		assertEquals(location, entry.get().location);
		assertEquals(totalWeight, entry.get().weight);

		Iterable<Product> products = productRepository.findAll();
		assertEquals(numberOfProducts, sizeOfIterable(products));

		Optional<Product> product = productRepository.findById(productCode);
		assertTrue(product.isPresent());
		assertEquals(name, product.get().name);
	}


	@Test
	public void createProductEntry_MultipleEntriesWithDifferentProductCodes_Success() throws Exception
	{
		final String productCode0 = "p0";
		final String productCode1 = "p1";
		final String location = "loc";
		final String name = "name";
		final Integer weight = 2;

		final Integer weight0 = 2;
		final Integer weight1 = 4;
		final Integer numberOfEntries = 2;
		final Integer numberOfProducts = 2;

		final ProductEntryUpdate[] updates = { 
			new ProductEntryUpdate(productCode0, location, name, weight),
			new ProductEntryUpdate(productCode1, location, name, weight),
			new ProductEntryUpdate(productCode1, location, name, weight),
		};
		final Gson gson = new Gson();
		final String body = gson.toJson(updates);

		mvc.perform(MockMvcRequestBuilders.put("/api/product-entry").accept("application/json").contentType("application/json").content(body))
			.andExpect(status().isOk());

		Iterable<ProductEntry> entries = entryRepository.findAll();
		assertEquals(numberOfEntries, sizeOfIterable(entries));

		ProductEntryId entryId0 = new ProductEntryId(productCode0, location);
		Optional<ProductEntry> entry0 = entryRepository.findById(entryId0);
		assertTrue(entry0.isPresent());
		assertEquals(productCode0, entry0.get().code);
		assertEquals(location, entry0.get().location);
		assertEquals(weight0, entry0.get().weight);

		ProductEntryId entryId1 = new ProductEntryId(productCode1, location);
		Optional<ProductEntry> entry1 = entryRepository.findById(entryId1);
		assertTrue(entry1.isPresent());
		assertEquals(productCode1, entry1.get().code);
		assertEquals(location, entry1.get().location);
		assertEquals(weight1, entry1.get().weight);

		Iterable<Product> products = productRepository.findAll();
		assertEquals(numberOfProducts, sizeOfIterable(products));

		Optional<Product> product0 = productRepository.findById(productCode0);
		assertTrue(product0.isPresent());
		assertEquals(name, product0.get().name);

		Optional<Product> product1 = productRepository.findById(productCode1);
		assertTrue(product1.isPresent());
		assertEquals(name, product1.get().name);
	}
}
