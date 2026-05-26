package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

	@Autowired
	private SpecialtyService specialtyService;

	@Test
	public void testFindSpecialtyById() {

		Integer ID = 1;
		String NAME_EXPECTED = "radiology";

		Specialty specialty = null;

		try {
			specialty = this.specialtyService.findById(ID);
		} catch (SpecialtyNotFoundException e) {
			fail(e.getMessage());
		}

		log.info("" + specialty);
		assertEquals(NAME_EXPECTED, specialty.getName());
	}

	@Test
	public void testFindSpecialtyByName() {

		String FIND_NAME = "surgery";
		int SIZE_EXPECTED = 1;

		List<Specialty> specialties = this.specialtyService.findByName(FIND_NAME);

		assertEquals(SIZE_EXPECTED, specialties.size());
	}

	@Test
	public void testCreateSpecialty() {

		String NAME = "cardiology";
		String OFFICE = "Building A";
		int H_OPEN = 8;
		int H_CLOSE = 17;

		Specialty specialty = new Specialty(null, NAME, OFFICE, H_OPEN, H_CLOSE);

		Specialty created = this.specialtyService.create(specialty);
		log.info("CREATED: " + created);

		assertNotNull(created.getId());
		assertEquals(NAME, created.getName());
		assertEquals(OFFICE, created.getOffice());
		assertEquals(H_OPEN, created.getHOpen());
		assertEquals(H_CLOSE, created.getHClose());
	}

	@Test
	public void testUpdateSpecialty() {

		String NAME = "neurology";
		String OFFICE = "Block B";
		int H_OPEN = 9;
		int H_CLOSE = 18;

		String UP_NAME = "neurology updated";
		String UP_OFFICE = "Block C";
		int UP_H_OPEN = 10;
		int UP_H_CLOSE = 20;

		Specialty specialty = new Specialty(null, NAME, OFFICE, H_OPEN, H_CLOSE);

		Specialty created = this.specialtyService.create(specialty);
		log.info("CREATED: " + created);

		created.setName(UP_NAME);
		created.setOffice(UP_OFFICE);
		created.setHOpen(UP_H_OPEN);
		created.setHClose(UP_H_CLOSE);

		Specialty updated = this.specialtyService.update(created);
		log.info("UPDATED: " + updated);

		assertEquals(UP_NAME, updated.getName());
		assertEquals(UP_OFFICE, updated.getOffice());
		assertEquals(UP_H_OPEN, updated.getHOpen());
		assertEquals(UP_H_CLOSE, updated.getHClose());
	}

	@Test
	public void testDeleteSpecialty() {

		String NAME = "oncology";
		String OFFICE = "Wing D";
		int H_OPEN = 7;
		int H_CLOSE = 15;

		Specialty specialty = new Specialty(null, NAME, OFFICE, H_OPEN, H_CLOSE);
		Specialty created = this.specialtyService.create(specialty);
		log.info("CREATED: " + created);

		try {
			this.specialtyService.delete(created.getId());
		} catch (SpecialtyNotFoundException e) {
			fail(e.getMessage());
		}

		try {
			this.specialtyService.findById(created.getId());
			assertTrue(false);
		} catch (SpecialtyNotFoundException e) {
			assertTrue(true);
		}
	}
}