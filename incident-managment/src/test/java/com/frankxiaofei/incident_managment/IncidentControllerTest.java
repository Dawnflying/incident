package com.frankxiaofei.incident_managment;

import com.frankxiaofei.incident_managment.controller.IncidentController;
import com.frankxiaofei.incident_managment.model.Incident;
import com.frankxiaofei.incident_managment.service.IncidentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IncidentControllerTest {

    @Mock
    private IncidentService incidentService;

    @InjectMocks
    private ExceptionHandlingAspect exceptionHandlingAspect;

    @InjectMocks
    private IncidentController incidentController;

    @Test
    public void testGetAllIncidents() {
        List<Incident> incidents = Arrays.asList(
                new Incident(1L, "Incident 1", "Description 1", "Open"),
                new Incident(2L, "Incident 2", "Description 2", "Closed")
        );

        when(incidentService.getAllIncidents()).thenReturn(incidents);

        List<Incident> result = incidentController.getAllIncidents();

        assertEquals(2, result.size());
        assertEquals("Incident 1", result.get(0).getTitle());
    }

    @Test
    public void testCreateIncident() {
        Incident incident = new Incident(1L, "Incident 1", "Description 1", "Open");

        when(incidentService.createIncident(Mockito.any(Incident.class))).thenReturn(incident);

        ResponseEntity<Incident> response = incidentController.createIncident(incident);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Incident 1", response.getBody().getTitle());
    }

    @Test
    public void testUpdateIncident() {
        Incident incident = new Incident(1L, "Incident 1", "Description 1", "Open");

        when(incidentService.updateIncident(Mockito.anyLong(), Mockito.any(Incident.class))).thenReturn(incident);

        ResponseEntity<Incident> response = incidentController.updateIncident(1L, incident);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Incident 1", response.getBody().getTitle());
    }

    @Test
    public void testDeleteIncident() {
        ResponseEntity<Void> response = incidentController.deleteIncident(1L);

        assertEquals(40401, response.getStatusCodeValue());
    }

    @Test
    public void testGetIncidentById() {
        Incident incident = new Incident(1L, "Incident 1", "Description 1", "Open");

        when(incidentService.getIncidentById(Mockito.anyLong())).thenReturn(incident);

        ResponseEntity<Incident> response = incidentController.getIncidentById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Incident 1", response.getBody().getTitle());
    }

    @Test
    public void testGetIncidents() {
        List<Incident> incidents = Arrays.asList(
                new Incident(1L, "Incident 1", "Description 1", "Open"),
                new Incident(2L, "Incident 2", "Description 2", "Closed")
        );

        Page<Incident> pageIncidents = new PageImpl<>(incidents);

        when(incidentService.getAllByPage(Mockito.any())).thenReturn(pageIncidents);

        List<Incident> result = incidentController.getIncidents(0, 10).getContent();

        assertEquals(2, result.size());
        assertEquals("Incident 1", result.get(0).getTitle());
    }


}

