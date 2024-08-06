package com.frankxiaofei.incident_managment.service;

import com.frankxiaofei.incident_managment.model.Incident;
import com.frankxiaofei.incident_managment.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    @Cacheable(value = "incidents", key = "#id")
    public Incident getIncidentById(Long id) {
        return incidentRepository.findById(id).orElse(null);
    }

    public Incident createIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    public Incident updateIncident(Long id, Incident incident) {
        Incident existingIncident = getIncidentById(id);
        if (existingIncident != null) {
            existingIncident.setTitle(incident.getTitle());
            existingIncident.setDescription(incident.getDescription());
            existingIncident.setStatus(incident.getStatus());
            return incidentRepository.save(existingIncident);
        }
        return null;
    }

    public Page<Incident> getAllByPage(Pageable pageable) {
        return incidentRepository.findAll(pageable);
    }

    @CacheEvict(value = "incidents", key = "#id")
    public void deleteIncident(Long id) {
        incidentRepository.deleteById(id);
    }

    public Incident getIncidentByTitle(String title) {
        return incidentRepository.findByTitle(title);
    }
}
