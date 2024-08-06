package com.frankxiaofei.incident_managment.controller;

import com.frankxiaofei.incident_managment.exceptions.BizException;
import com.frankxiaofei.incident_managment.model.Incident;
import com.frankxiaofei.incident_managment.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.frankxiaofei.incident_managment.errorcodes.ErrorCodes.ENTITY_ALREADY_EXISTS;
import static com.frankxiaofei.incident_managment.errorcodes.ErrorCodes.ENTITY_NOT_FOUND;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        Incident incident = incidentService.getIncidentById(id);
        return incident != null ? ResponseEntity.ok(incident) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
        if (incidentService.getIncidentByTitle(incident.getTitle()) != null) {
            throw new BizException(ENTITY_ALREADY_EXISTS.getCode(), ENTITY_ALREADY_EXISTS.getMessage());
        }

        Incident createdIncident = incidentService.createIncident(incident);
        return new ResponseEntity<>(createdIncident, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long id, @RequestBody Incident incident) {
        if (incidentService.getIncidentById(id) == null) {
            throw new BizException(ENTITY_NOT_FOUND.getCode(), ENTITY_NOT_FOUND.getMessage());
        }
        Incident updatedIncident = incidentService.updateIncident(id, incident);
        return updatedIncident != null ? ResponseEntity.ok(updatedIncident) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        if (incidentService.getIncidentById(id) == null) {
            throw new BizException(ENTITY_NOT_FOUND.getCode(), ENTITY_NOT_FOUND.getMessage());
        }
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/incidents")
    public Page<Incident> getIncidents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return incidentService.getAllByPage(pageable);
    }
}
