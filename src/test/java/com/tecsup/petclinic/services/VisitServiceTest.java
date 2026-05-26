package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    // -------------------------------------------------------
    // TEST: Crear visita
    // -------------------------------------------------------
    @Test
    public void testCreateVisit() {

        int PET_ID      = 1;
        Date VISIT_DATE = new Date();
        String DESC     = "Vacunación anual";

        Visit visit = new Visit();
        visit.setPetId(PET_ID);
        visit.setVisitDate(VISIT_DATE);
        visit.setDescription(DESC);

        Visit visitCreado = visitService.create(visit);
        log.info("Visita creada: {}", visitCreado);

        assertNotNull(visitCreado.getId());
        assertEquals(PET_ID, visitCreado.getPetId());
        assertEquals(DESC,   visitCreado.getDescription());
    }

    // -------------------------------------------------------
    // TEST: Buscar visita por ID
    // -------------------------------------------------------
    @Test
    public void testFindVisitById() throws VisitNotFoundException {

        int VISIT_ID     = 1; // ajusta a un ID que exista en tu BD de prueba
        String DESC_ESPERADA = "rabies shot";

        Visit visit = visitService.findById(VISIT_ID);
        log.info("Visita encontrada: {}", visit);

        assertEquals(VISIT_ID,     visit.getId());
        assertEquals(DESC_ESPERADA, visit.getDescription());
    }

    // -------------------------------------------------------
    // TEST: Actualizar visita
    // -------------------------------------------------------
    @Test
    public void testUpdateVisit() throws VisitNotFoundException {

        // 1. Crear
        Visit visit = new Visit();
        visit.setPetId(2);
        visit.setVisitDate(new Date());
        visit.setDescription("Consulta inicial");
        Visit visitCreado = visitService.create(visit);

        // 2. Modificar
        String NUEVA_DESC = "Consulta de seguimiento";
        visitCreado.setDescription(NUEVA_DESC);
        visitService.update(visitCreado);

        // 3. Verificar
        Visit visitActualizado = visitService.findById(visitCreado.getId());
        log.info("Visita actualizada: {}", visitActualizado);

        assertEquals(NUEVA_DESC, visitActualizado.getDescription());
    }

    // -------------------------------------------------------
    // TEST: Eliminar visita
    // -------------------------------------------------------
    @Test
    public void testDeleteVisit() throws VisitNotFoundException {

        // 1. Crear
        Visit visit = new Visit();
        visit.setPetId(3);
        visit.setVisitDate(new Date());
        visit.setDescription("Visita a eliminar");
        Visit visitCreado = visitService.create(visit);
        Integer id = visitCreado.getId();
        log.info("Visita a eliminar con id: {}", id);

        // 2. Eliminar
        visitService.delete(id);

        // 3. Verificar que ya no existe
        assertThrows(VisitNotFoundException.class, () -> visitService.findById(id));
    }

    // -------------------------------------------------------
    // TEST: Buscar por descripción
    // -------------------------------------------------------
    @Test
    public void testFindByDescription() {

        String DESC = "rabies shot";
        List<Visit> visitas = visitService.findByDescription(DESC);
        log.info("Visitas encontradas: {}", visitas.size());

        assertFalse(visitas.isEmpty());
    }
}