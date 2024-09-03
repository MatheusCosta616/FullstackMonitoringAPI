package com.fullstackmonitoring.service;

import com.fullstackmonitoring.dto.AlertDTO;
import com.fullstackmonitoring.model.AlertModel;
import com.fullstackmonitoring.model.DeviceModel;
import com.fullstackmonitoring.repositories.AlertRepository;
import com.fullstackmonitoring.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public ResponseEntity<Object> createAlert(AlertDTO alertDTO) {
        Optional<DeviceModel> deviceOpt = deviceRepository.findById(alertDTO.deviceId());
        if (deviceOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado");
        }

        AlertModel alertModel = new AlertModel();
        alertModel.setDevice(deviceOpt.get());
        alertModel.setCondition(alertDTO.condition());
        alertModel.setMessage(alertDTO.message());

        AlertModel savedAlert = alertRepository.save(alertModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAlert);
    }

    public ResponseEntity<List<AlertModel>> getAlertsByDeviceId(UUID deviceId) {
        if (!deviceRepository.existsById(deviceId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<AlertModel> alerts = alertRepository.findByDeviceId(deviceId);
        return ResponseEntity.ok(alerts);
    }
}