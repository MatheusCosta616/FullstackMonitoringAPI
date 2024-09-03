package com.fullstackmonitoring.controller;

import com.fullstackmonitoring.dto.DeviceDTO;
import com.fullstackmonitoring.model.DeviceModel;
import com.fullstackmonitoring.repositories.DeviceRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos dispositivos.
 * Fornece endpoints para criar, recuperar, atualizar e gerenciar dispositivos no sistema.
 */
@RestController
public class DeviceController {

    @Autowired
    DeviceRepository deviceRepository;

    /**
     * Cria um novo dispositivo no sistema.
     *
     * @param deviceDTO DTO contendo os dados do novo dispositivo.
     * @return ResponseEntity com o dispositivo criado e status HTTP 201 (CREATED).
     */
    @PostMapping("/devices")
    public ResponseEntity<DeviceModel> saveDevice(@RequestBody @Valid DeviceDTO deviceDTO) {
        DeviceModel deviceModel = new DeviceModel();
        BeanUtils.copyProperties(deviceDTO, deviceModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceRepository.save(deviceModel));
    }

    /**
     * Recupera todos os dispositivos cadastrados no sistema.
     *
     * @return ResponseEntity com a lista de todos os dispositivos e status HTTP 200 (OK).
     */
    @GetMapping("/devices")
    public ResponseEntity<List<DeviceModel>> getAllDevices() {
        return ResponseEntity.status(HttpStatus.OK).body(deviceRepository.findAll());
    }

    /**
     * Recupera um dispositivo específico pelo seu ID.
     *
     * @param deviceId UUID do dispositivo a ser recuperado.
     * @return ResponseEntity com o dispositivo encontrado ou uma mensagem de erro se não for encontrado.
     */
    @GetMapping("/devices/{deviceId}")
    public ResponseEntity<Object> getOneDevice(@PathVariable(value = "deviceId") UUID deviceId) {
        Optional<DeviceModel> device = deviceRepository.findById(deviceId);
        if (device.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(device.get());
    }

    /**
     * Atualiza um dispositivo existente.
     *
     * @param deviceId UUID do dispositivo a ser atualizado.
     * @param deviceDTO DTO contendo os novos dados do dispositivo.
     * @return ResponseEntity com o dispositivo atualizado ou uma mensagem de erro se não for encontrado.
     */
    @PutMapping("/devices/{deviceId}")
    public ResponseEntity<Object> updateDevice(@PathVariable(value = "deviceId") UUID deviceId,
                                               @RequestBody @Valid DeviceDTO deviceDTO) {
        Optional<DeviceModel> device = deviceRepository.findById(deviceId);
        if (device.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado");
        }
        DeviceModel deviceModel = device.get();
        BeanUtils.copyProperties(deviceDTO, deviceModel);
        return ResponseEntity.status(HttpStatus.OK).body(deviceRepository.save(deviceModel));
    }

    /**
     * Exclui um dispositivo do sistema.
     *
     * @param deviceId UUID do dispositivo a ser excluído.
     * @return ResponseEntity com uma mensagem de sucesso ou erro.
     */
    @DeleteMapping("/devices/{deviceId}")
    public ResponseEntity<Object> deleteDevice(@PathVariable(value = "deviceId") UUID deviceId) {
        Optional<DeviceModel> device = deviceRepository.findById(deviceId);
        if (device.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado");
        }
        deviceRepository.delete(device.get());
        return ResponseEntity.status(HttpStatus.OK).body("Dispositivo deletado com sucesso");
    }
}
