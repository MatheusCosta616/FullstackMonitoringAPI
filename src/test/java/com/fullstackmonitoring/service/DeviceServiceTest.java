package com.fullstackmonitoring.service;

import com.fullstackmonitoring.dto.DeviceDTO;
import com.fullstackmonitoring.model.DeviceModel;
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

class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDevice() {
        DeviceDTO deviceDTO = new DeviceDTO("Test Device", "Active", "2023-04-15T10:30:00Z", "Location", "Logs");
        DeviceModel deviceModel = new DeviceModel();
        deviceModel.setId(UUID.randomUUID());
        deviceModel.setName(deviceDTO.name());

        when(deviceRepository.save(any(DeviceModel.class))).thenReturn(deviceModel);

        ResponseEntity<DeviceModel> response = deviceService.saveDevice(deviceDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(deviceDTO.name(), response.getBody().getName());
    }

    @Test
    void testGetAllDevices() {
        List<DeviceModel> devices = Arrays.asList(
            new DeviceModel(UUID.randomUUID(), "Device 1", "Active", "2023-04-15T10:30:00Z", "Location 1", "Logs 1"),
            new DeviceModel(UUID.randomUUID(), "Device 2", "Inactive", "2023-04-15T11:30:00Z", "Location 2", "Logs 2")
        );

        when(deviceRepository.findAll()).thenReturn(devices);

        ResponseEntity<List<DeviceModel>> response = deviceService.getAllDevices();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetOneDevice_Found() {
        UUID deviceId = UUID.randomUUID();
        DeviceModel device = new DeviceModel(deviceId, "Test Device", "Active", "2023-04-15T10:30:00Z", "Location", "Logs");

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));

        ResponseEntity<Object> response = deviceService.getOneDevice(deviceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof DeviceModel);
        assertEquals(deviceId, ((DeviceModel) response.getBody()).getId());
    }

    @Test
    void testGetOneDevice_NotFound() {
        UUID deviceId = UUID.randomUUID();

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = deviceService.getOneDevice(deviceId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Dispositivo não encontrado", response.getBody());
    }

}