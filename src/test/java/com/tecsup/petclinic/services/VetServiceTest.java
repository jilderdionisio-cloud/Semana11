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

}
