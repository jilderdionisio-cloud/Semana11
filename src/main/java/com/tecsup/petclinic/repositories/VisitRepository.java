package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Integer> {

    List<Visit> findByPetId(Integer petId);

    List<Visit> findByDescription(String description);
}