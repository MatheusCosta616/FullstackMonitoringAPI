package com.fullstackmonitoring.service;

import com.fullstackmonitoring.dto.DeviceDTO;
import com.fullstackmonitoring.model.DeviceModel;
import com.fullstackmonitoring.repositories.DeviceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public ResponseEntity<DeviceModel> saveDevice(DeviceDTO deviceDTO) {
        DeviceModel deviceModel = new DeviceModel();
        BeanUtils.copyProperties(deviceDTO, deviceModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceRepository.save(deviceModel));
    }

    public ResponseEntity<List<DeviceModel>> getAllDevices() {
        return ResponseEntity.ok(deviceRepository.findAll());
    }

    public ResponseEntity<Object> getOneDevice(UUID deviceId) {
        Optional<DeviceModel> deviceOptional = deviceRepository.findById(deviceId);
        if (deviceOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado");
        }
        return ResponseEntity.ok(deviceOptional.get());
    }

    public ResponseEntity<Object> updateDevice(UUID deviceId, DeviceDTO deviceDTO) {
        Optional<DeviceModel> deviceOptional = deviceRepository.findById(deviceId);
        if (deviceOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado");
        }
        DeviceModel device = deviceOptional.get();
        BeanUtils.copyProperties(deviceDTO, device);
        return ResponseEntity.ok(deviceRepository.save(device));
    }

    public ResponseEntity<Object> deleteDevice(UUID deviceId) {
        Optional<DeviceModel> deviceOptional = deviceRepository.findById(deviceId);
        if (deviceOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado");
        }
        deviceRepository.delete(deviceOptional.get());
        return ResponseEntity.ok("Dispositivo deletado com sucesso");
    }
}