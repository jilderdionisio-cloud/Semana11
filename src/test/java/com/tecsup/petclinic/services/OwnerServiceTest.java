package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void testFindOwnerById() {

        final String FIRST_NAME_EXPECTED = "George";
        final String LAST_NAME_EXPECTED = "Franklin";

        Integer ID = 1;

        OwnerDTO owner = null;

        try {
            owner = this.ownerService.findById(ID);
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }

        assertEquals(FIRST_NAME_EXPECTED, owner.getFirstName());
        assertEquals(LAST_NAME_EXPECTED, owner.getLastName());
    }

    @Test
    public void testFindOwnerByLastName() {

        String FIND_LAST_NAME = "Davis";
        int SIZE_EXPECTED = 2;

        List<OwnerDTO> owners = this.ownerService.findByLastName(FIND_LAST_NAME);

        assertEquals(SIZE_EXPECTED, owners.size());
    }

    @Test
    public void testCreateOwner() {

        String FIRST_NAME = "Luis";
        String LAST_NAME = "Garcia";
        String ADDRESS = "123 Main St.";
        String CITY = "Lima";
        String TELEPHONE = "999888777";

        OwnerDTO ownerDTO = OwnerDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .address(ADDRESS)
                .city(CITY)
                .telephone(TELEPHONE)
                .build();

        OwnerDTO newOwnerDTO = this.ownerService.create(ownerDTO);

        log.info("OWNER CREATED :" + newOwnerDTO.toString());

        assertNotNull(newOwnerDTO.getId());
        assertEquals(FIRST_NAME, newOwnerDTO.getFirstName());
        assertEquals(LAST_NAME, newOwnerDTO.getLastName());
        assertEquals(ADDRESS, newOwnerDTO.getAddress());
        assertEquals(CITY, newOwnerDTO.getCity());
        assertEquals(TELEPHONE, newOwnerDTO.getTelephone());
    }

}
