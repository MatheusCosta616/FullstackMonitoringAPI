package com.fullstackmonitoring.service;

import com.fullstackmonitoring.dto.AlertDTO;
import com.fullstackmonitoring.model.AlertModel;
import com.fullstackmonitoring.model.DeviceModel;
import com.fullstackmonitoring.repositories.AlertRepository;
import com.fullstackmonitoring.repositories.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlertServiceTest {

    @Mock
    private AlertRepository alertRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private AlertService alertService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAlert_Success() {
        UUID deviceId = UUID.randomUUID();
        DeviceModel device = new DeviceModel(deviceId, "Test Device", "Active", "2023-04-15T10:30:00Z", "Location", "Logs");
        AlertDTO alertDTO = new AlertDTO(deviceId, "Temperature > 30", "High temperature alert");
        AlertModel savedAlert = new AlertModel();
        savedAlert.setId(UUID.randomUUID());
        savedAlert.setDevice(device);
        savedAlert.setCondition(alertDTO.condition());
        savedAlert.setMessage(alertDTO.message());

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));
        when(alertRepository.save(any(AlertModel.class))).thenReturn(savedAlert);

        ResponseEntity<Object> response = alertService.createAlert(alertDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof AlertModel);
        assertEquals(alertDTO.condition(), ((AlertModel) response.getBody()).getCondition());
    }

    @Test
    void testCreateAlert_DeviceNotFound() {
        UUID deviceId = UUID.randomUUID();
        AlertDTO alertDTO = new AlertDTO(deviceId, "Temperature > 30", "High temperature alert");

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = alertService.createAlert(alertDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Dispositivo não encontrado", response.getBody());
    }

    @Test
    void testGetAlertsByDeviceId_Success() {
        UUID deviceId = UUID.randomUUID();
        List<AlertModel> alerts = Arrays.asList(
            new AlertModel(UUID.randomUUID(), null, "Condition 1", "Threshold 1", "Message 1"),
            new AlertModel(UUID.randomUUID(), null, "Condition 2", "Threshold 2", "Message 2")
        );

        when(deviceRepository.existsById(deviceId)).thenReturn(true);
        when(alertRepository.findByDeviceId(deviceId)).thenReturn(alerts);

        ResponseEntity<List<AlertModel>> response = alertService.getAlertsByDeviceId(deviceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetAlertsByDeviceId_DeviceNotFound() {
        UUID deviceId = UUID.randomUUID();

        when(deviceRepository.existsById(deviceId)).thenReturn(false);

        ResponseEntity<List<AlertModel>> response = alertService.getAlertsByDeviceId(deviceId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}