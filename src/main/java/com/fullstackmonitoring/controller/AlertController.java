package com.fullstackmonitoring.controller;

import com.fullstackmonitoring.dto.AlertDTO;
import com.fullstackmonitoring.model.AlertModel;
import com.fullstackmonitoring.model.DeviceModel;
import com.fullstackmonitoring.repositories.AlertRepository;
import com.fullstackmonitoring.repositories.DeviceRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos alertas.
 * Fornece endpoints para criar alertas e recuperar alertas associados a dispositivos específicos.
 */
@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    /**
     * Cria um novo alerta no sistema.
     *
     * @param alertDTO DTO contendo os dados do novo alerta.
     * @return ResponseEntity com o alerta criado ou uma mensagem de erro se o dispositivo associado não for encontrado.
     */
    @PostMapping
    public ResponseEntity<AlertModel> configureAlert(@RequestBody @Valid AlertDTO alertDTO) {
        Optional<DeviceModel> deviceOpt = deviceRepository.findById(alertDTO.deviceId());
        if (deviceOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AlertModel alertModel = new AlertModel();
        alertModel.setDevice(deviceOpt.get());
        alertModel.setCondition(alertDTO.condition());
        alertModel.setMessage(alertDTO.message());

        return ResponseEntity.status(HttpStatus.CREATED).body(alertRepository.save(alertModel));
    }

    /**
     * Recupera todos os alertas associados a um dispositivo específico.
     *
     * @param deviceId O UUID do dispositivo cujos alertas serão recuperados.
     * @return ResponseEntity contendo uma lista de alertas se o dispositivo for encontrado,
     *         ou uma mensagem de erro se o dispositivo não existir.
     */
    @GetMapping("/device/{deviceId}")
    public ResponseEntity<Object> getAlertsByDeviceId(@PathVariable UUID deviceId) {
        if (!deviceRepository.existsById(deviceId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado");
        }

        List<AlertModel> alerts = alertRepository.findByDeviceId(deviceId);
        return ResponseEntity.ok(alerts);
    }
}
