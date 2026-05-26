package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    @Test
    public void testFindVetById() {

        final String FIRST_NAME_EXPECTED = "James";
        final String LAST_NAME_EXPECTED = "Carter";

        Integer ID = 1;

        VetDTO vet = null;

        try {
            vet = this.vetService.findById(ID);
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        assertEquals(FIRST_NAME_EXPECTED, vet.getFirstName());
        assertEquals(LAST_NAME_EXPECTED, vet.getLastName());
    }

    @Test
    public void testFindVetByLastName() {

        String FIND_LAST_NAME = "Carter";
        int SIZE_EXPECTED = 1;

        List<VetDTO> vets = this.vetService.findByLastName(FIND_LAST_NAME);

        assertEquals(SIZE_EXPECTED, vets.size());
    }

    @Test
    public void testCreateVet() {

        String FIRST_NAME = "Robert";
        String LAST_NAME = "Wilson";
        String EMAIL = "robert.wilson@petclinic.com";
        String PHONE = "6085557777";
        Boolean ACTIVE = true;

        VetDTO vetDTO = VetDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .phone(PHONE)
                .active(ACTIVE)
                .build();

        VetDTO newVetDTO = this.vetService.create(vetDTO);

        log.info("VET CREATED :" + newVetDTO.toString());

        assertNotNull(newVetDTO.getId());
        assertEquals(FIRST_NAME, newVetDTO.getFirstName());
        assertEquals(LAST_NAME, newVetDTO.getLastName());
        assertEquals(EMAIL, newVetDTO.getEmail());
        assertEquals(PHONE, newVetDTO.getPhone());
        assertEquals(ACTIVE, newVetDTO.getActive());
    }

    @Test
    public void testUpdateVet() {

        String FIRST_NAME = "Mark";
        String LAST_NAME = "Brown";
        String EMAIL = "mark.brown@petclinic.com";
        String PHONE = "6085558888";
        Boolean ACTIVE = true;

        String UP_FIRST_NAME = "Markus";
        String UP_LAST_NAME = "Brownson";
        String UP_EMAIL = "markus.brownson@petclinic.com";
        String UP_PHONE = "6085559999";
        Boolean UP_ACTIVE = false;

        VetDTO vetDTO = VetDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .phone(PHONE)
                .active(ACTIVE)
                .build();

        // ------------ Create ---------------

        log.info(">" + vetDTO);
        VetDTO vetDTOCreated = this.vetService.create(vetDTO);
        log.info(">>" + vetDTOCreated);

        // ------------ Update ---------------

        vetDTOCreated.setFirstName(UP_FIRST_NAME);
        vetDTOCreated.setLastName(UP_LAST_NAME);
        vetDTOCreated.setEmail(UP_EMAIL);
        vetDTOCreated.setPhone(UP_PHONE);
        vetDTOCreated.setActive(UP_ACTIVE);

        VetDTO upgradeVetDTO = this.vetService.update(vetDTOCreated);
        log.info(">>>>" + upgradeVetDTO);

        assertEquals(UP_FIRST_NAME, upgradeVetDTO.getFirstName());
        assertEquals(UP_LAST_NAME, upgradeVetDTO.getLastName());
        assertEquals(UP_EMAIL, upgradeVetDTO.getEmail());
        assertEquals(UP_PHONE, upgradeVetDTO.getPhone());
        assertEquals(UP_ACTIVE, upgradeVetDTO.getActive());
    }

    @Test
    public void testDeleteVet() {

        String FIRST_NAME = "Delete";
        String LAST_NAME = "Vet";
        String EMAIL = "delete.vet@petclinic.com";
        String PHONE = "6085550000";
        Boolean ACTIVE = true;

        // ------------ Create ---------------

        VetDTO vetDTO = VetDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .phone(PHONE)
                .active(ACTIVE)
                .build();

        VetDTO newVetDTO = this.vetService.create(vetDTO);
        log.info("" + vetDTO);

        // ------------ Delete ---------------

        try {
            this.vetService.delete(newVetDTO.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        // ------------ Validation ---------------

        try {
            this.vetService.findById(newVetDTO.getId());
            assertTrue(false);
        } catch (VetNotFoundException e) {
            assertTrue(true);
        }
    }

}
