package com.fullstackmonitoring.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO (Data Transfer Object) para transferência de dados de dispositivo.
 * Utilizado para criar ou atualizar dispositivos no sistema.
 *
 * @param name Nome do dispositivo.
 * @param status Status atual do dispositivo.
 * @param lastPing Timestamp do último ping recebido do dispositivo.
 * @param location Localização física do dispositivo.
 * @param logs Logs associados ao dispositivo.
 */
public record DeviceDTO(
        @NotBlank String name,
        @NotBlank String status,
        @NotBlank String lastPing,
        String location,
        @NotBlank String logs
) {}
